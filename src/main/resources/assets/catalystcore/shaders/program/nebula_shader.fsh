#version 150

uniform sampler2D DiffuseSampler;
uniform vec2 OutSize;
uniform float NebulaTime;

uniform int NebulaCount;
uniform float NebulaCentersX[10];
uniform float NebulaCentersY[10];
uniform float NebulaRadii[10];
uniform float NebulaIntensities[10];
uniform float NebulaModes[10];
uniform float NebulaColorsA[30]; // 10 vec3 stored as 30 floats (x,y,z)
uniform float NebulaColorsB[30]; // 10 vec3 stored as 30 floats (x,y,z)
uniform float NebulaProgress[10];
uniform float NebulaStarDensity[10]; // star density per nebula (0.9=many stars .. 0.99=few stars)

in vec2 texCoord;
out vec4 fragColor;

const float EVENT_HORIZON_MULT = 1.5;
const float LENS_RADIUS_MULT = 4.0;
const float GRAVITY_STRENGTH_MULT = 1.5;

const float DISK_Y_SCALE = 1.2;
const float DISK_OUTER_LIMIT_MULT = 3.0;

float rand(vec2 p)
{
    return fract(sin(dot(p, vec2(23.53, 44.0))) * 42350.45);
}

float perlin(vec2 p)
{
    vec2 i = floor(p);
    vec2 j = fract(p);
    vec2 coord = smoothstep(0.0, 1.0, j);

    float a = rand(i);
    float b = rand(i + vec2(1.0, 0.0));
    float c = rand(i + vec2(0.0, 1.0));
    float d = rand(i + vec2(1.0, 1.0));

    return mix(mix(a, b, coord.x), mix(c, d, coord.x), coord.y);
}

float fbmCloud(vec2 p, float minimum)
{
    float value = 0.0;
    float scale = 0.5;
    vec2 q = p;

    for(int i = 0; i < 5; i++)
    {
        value += perlin(q) * scale;
        q *= 2.0;
        scale *= 0.5;
    }

    return smoothstep(0.0, 1.0, (smoothstep(minimum, 1.0, value) - minimum) / max(0.001, 1.0 - minimum));
}

float fbmCloud2(vec2 p, float minimum)
{
    float value = 0.0;
    float scale = 0.5;
    vec2 q = p;

    for(int i = 0; i < 5; i++)
    {
        value += perlin(q) * scale;
        q *= 2.0;
        scale *= 0.5;
    }

    return (smoothstep(minimum, 1.0, value) - minimum) / max(0.001, 1.0 - minimum);
}

void main()
{
    vec2 totalDistortion = vec2(0.0);
    float maxBlackHoleIntensity = 0.0;
    vec4 totalNebulaColor = vec4(0.0);

    float aspectRatio = OutSize.x / OutSize.y;

    for(int i = 0; i < 10; i++)
    {
        if(i >= NebulaCount) break;

        vec2 center = vec2(NebulaCentersX[i], NebulaCentersY[i]);
        if(center.x < 0.0 || center.y < 0.0) continue;

        vec2 dir = texCoord - center;
        vec2 distortedDir = dir;
        distortedDir.x *= aspectRatio;
        float dist = length(distortedDir);

        int mode = int(NebulaModes[i]);
        float progress = NebulaProgress[i];

        vec3 colA = vec3(NebulaColorsA[i * 3], NebulaColorsA[i * 3 + 1], NebulaColorsA[i * 3 + 2]);
        vec3 colB = vec3(NebulaColorsB[i * 3], NebulaColorsB[i * 3 + 1], NebulaColorsB[i * 3 + 2]);

        vec3 primaryCloudCol;
        if(mode == 1)
        {
            primaryCloudCol = colA;
        }
        else if(mode == 2)
        {
            primaryCloudCol = mix(colA, colB, progress);
        }
        else // Mode 3
        {
            vec3 mode2Col = mix(colA, colB, progress);
            primaryCloudCol = mix(mode2Col, vec3(0.05, 0.0, 0.1), progress * 0.7);
        }

        // Lensing distortion only in Mode 3 when progressing to black hole
        float blackHoleFactor = (mode == 3) ? progress : 0.0;
        float eventHorizon = NebulaRadii[i] * EVENT_HORIZON_MULT * blackHoleFactor;
        float lensRadius = NebulaRadii[i] * LENS_RADIUS_MULT * blackHoleFactor;

        if(blackHoleFactor > 0.05 && dist < lensRadius && dist > eventHorizon)
        {
            float distortion = (lensRadius - dist) / lensRadius;
            vec2 normDir = normalize(distortedDir);
            normDir.x /= aspectRatio;
            totalDistortion += normDir * (distortion * distortion) * (GRAVITY_STRENGTH_MULT * blackHoleFactor * NebulaIntensities[i]) * dist;
        }

        vec2 diskDir = dir;
        diskDir.x *= aspectRatio;
        diskDir.y *= DISK_Y_SCALE;
        float diskDist = length(diskDir);

        float diskInnerLimit = 0.0;
        if(mode == 3)
        {
            diskInnerLimit = eventHorizon;
        }

        float diskOuterLimit = NebulaRadii[i] * DISK_OUTER_LIMIT_MULT;

        if(diskDist >= diskInnerLimit && diskDist < diskOuterLimit && NebulaIntensities[i] > 0.05)
        {
            float normDist = diskDist / diskOuterLimit;
            float diskFringe = smoothstep(1.0, 0.5, normDist);

            float timescale = 1.0;
            float timescaled = NebulaTime * timescale;
            float zoomScale = 6.0;

            vec2 UV = diskDir;
            vec2 zoomUV2 = vec2(zoomScale * UV.x + 0.03 * timescaled * sin(0.07 * timescaled), zoomScale * UV.y + 0.03 * timescaled * cos(0.06 * timescaled));
            vec2 zoomUV3 = vec2(zoomScale * UV.x + 0.027 * timescaled * sin(0.07 * timescaled), zoomScale * UV.y + 0.025 * timescaled * cos(0.06 * timescaled));
            vec2 zoomUV4 = vec2(zoomScale * UV.x + 0.021 * timescaled * sin(0.07 * timescaled), zoomScale * UV.y + 0.021 * timescaled * cos(0.07 * timescaled));

            float tide = 0.05 * sin(NebulaTime);
            float tide2 = 0.06 * cos(0.3 * NebulaTime);

            vec4 cloud1Col = vec4(primaryCloudCol, 0.95);
            vec4 cloud2Col = vec4(mix(colB, primaryCloudCol * 0.7, 0.5), 0.9);
            vec4 cloud3Col = vec4(mix(primaryCloudCol, vec3(0.9, 0.4, 0.8), 0.5), 0.95);
            vec4 cloud4Col = vec4(mix(primaryCloudCol * 0.4, colB, 0.6), 0.85);

            vec4 nebulaLayer = vec4(0.0);
            nebulaLayer += fbmCloud2(zoomUV3, 0.18 + tide) * cloud1Col;
            nebulaLayer += fbmCloud(zoomUV2 * 0.8, 0.22 - tide) * cloud2Col;
            nebulaLayer = mix(nebulaLayer, cloud3Col, fbmCloud(vec2(0.8 * zoomUV4.x, 0.8 * zoomUV4.y), 0.18 + tide2));
            nebulaLayer = mix(nebulaLayer, cloud4Col, fbmCloud(zoomUV3 * 0.6 + 1.5, 0.28 + tide2));

            // Star Sparkles with cross/spike rays (4-pointed star shape)
            float starscale = 25.0;
            float size = 8.0;
            // Density: low value = many stars (prob near 0.9), high = few (prob near 0.995)
            // NebulaStarDensity[i] is in range [0,1]: 0 = sparse (0.995), 1 = dense (0.88)
            float prob = mix(0.995, 0.88, clamp(NebulaStarDensity[i], 0.0, 1.0));

            vec2 zoomstar = starscale * zoomUV2;
            vec2 pos = floor(zoomstar / size);
            float starValue = rand(pos);
            if(starValue > prob)
            {
                vec2 centerPos = size * pos + vec2(size, size) * 0.5;
                float phase = (starValue - prob) / (1.0 - prob);
                float t = 0.9 + 0.2 * sin(NebulaTime * 8.0 + phase * 45.0);

                // Distance-based base glow
                float coreDist = distance(zoomstar, centerPos);
                float coreGlow = clamp(1.0 - coreDist / (0.4 * size), 0.0, 1.0);
                coreGlow = pow(coreGlow, 2.5); // tight bright core

                // Horizontal and vertical ray falloff for cross/spike shape
                float dx = abs(zoomstar.x - centerPos.x) + 0.001;
                float dy = abs(zoomstar.y - centerPos.y) + 0.001;
                float rayLen = size * 0.55 * t;
                float hRay = clamp(1.0 - dx / rayLen, 0.0, 1.0) * clamp(1.0 - dy / (rayLen * 0.18), 0.0, 1.0);
                float vRay = clamp(1.0 - dy / rayLen, 0.0, 1.0) * clamp(1.0 - dx / (rayLen * 0.18), 0.0, 1.0);
                
                // Diagonal softer rays for extra sparkle
                float diagScale = 0.65;
                float diag1 = clamp(1.0 - abs(dx - dy) / (rayLen * 0.3), 0.0, 1.0) * clamp(1.0 - coreDist / (rayLen * diagScale), 0.0, 1.0);
                float diag2 = clamp(1.0 - abs(dx - (size - dy)) / (rayLen * 0.3), 0.0, 1.0) * clamp(1.0 - coreDist / (rayLen * diagScale), 0.0, 1.0);

                float starIntensity = clamp(coreGlow + hRay + vRay + (diag1 + diag2) * 0.35, 0.0, 1.0);
                
                // Color: white-blue core, warm orange tips
                vec3 starColor = mix(vec3(1.0, 0.85, 0.5), vec3(0.95, 0.97, 1.0), coreGlow);
                nebulaLayer = mix(nebulaLayer, vec4(starColor, 1.0), starIntensity * t);
            }

            float alpha = clamp(nebulaLayer.a * 1.8, 0.0, 1.0) * diskFringe * NebulaIntensities[i];

            if(mode == 3 && progress > 0.85)
            {
                alpha *= (1.0 - (progress - 0.85) / 0.15);
            }

            vec4 blockNebulaColor = vec4(nebulaLayer.rgb, alpha);

            totalNebulaColor.rgb = mix(totalNebulaColor.rgb, blockNebulaColor.rgb, blockNebulaColor.a);
            totalNebulaColor.a = clamp(totalNebulaColor.a + blockNebulaColor.a * (1.0 - totalNebulaColor.a), 0.0, 1.0);
        }

        if(mode == 3 && blackHoleFactor > 0.1 && dist <= eventHorizon)
        {
            maxBlackHoleIntensity = max(maxBlackHoleIntensity, NebulaIntensities[i] * blackHoleFactor);
        }
    }

    vec2 finalUV = clamp(texCoord - totalDistortion, vec2(0.001), vec2(0.999));
    vec4 sceneColor = texture(DiffuseSampler, finalUV);

    if(maxBlackHoleIntensity > 0.0)
    {
        sceneColor = mix(sceneColor, vec4(0.0, 0.0, 0.0, 1.0), maxBlackHoleIntensity);
    }

    sceneColor.rgb = mix(sceneColor.rgb, totalNebulaColor.rgb, totalNebulaColor.a);

    fragColor = vec4(sceneColor.rgb, 1.0);
}
