#version 150

uniform float WarpTime;

in vec4 vertexColor;
in vec2 texCoord0;
out vec4 fragColor;

#define PI 3.14159265359

const vec3 WARP_VOID = vec3(0.08, 0.01, 0.22); 
const vec3 WARP_PURPLE = vec3(0.42, 0.05, 0.70); 
const vec3 WARP_MAGENTA = vec3(0.78, 0.18, 0.88); 
const vec3 WARP_CORE_GLOW = vec3(0.95, 0.65, 1.00); 
const vec3 WARP_CYAN = vec3(0.15, 0.85, 0.98); 
const vec3 RAY_MAIN_COLOR = vec3(1.00, 1.00, 1.00); 
const vec3 RAY_EFFECT_COLOR = vec3(0.30, 0.60, 1.00); 

float hash3D(vec3 p)
{
    p = fract(p * vec3(0.1031, 0.1030, 0.0973));
    p += dot(p, p.yxz + 33.33);
    return fract((p.x + p.y) * p.z);
}

float hash12(vec2 p)
{
    vec3 p3 = fract(vec3(p.xyx) * 0.1031);
    p3 += dot(p3, p3.yzx + 33.33);
    return fract((p3.x + p3.y) * p3.z);
}

float smoothNoise3D(vec3 p)
{
    vec3 i = floor(p);
    vec3 f = fract(p);
    vec3 u = f * f * f * (f * (f * 6.0 - 15.0) + 10.0);
    return mix(
        mix(mix(hash3D(i + vec3(0,0,0)), hash3D(i + vec3(1,0,0)), u.x),
            mix(hash3D(i + vec3(0,1,0)), hash3D(i + vec3(1,1,0)), u.x), u.y),
        mix(mix(hash3D(i + vec3(0,0,1)), hash3D(i + vec3(1,0,1)), u.x),
            mix(hash3D(i + vec3(0,1,1)), hash3D(i + vec3(1,1,1)), u.x), u.y), u.z
    );
}

float fbm(vec3 p)
{
    float v = 0.0;
    float a = 0.5;
    vec3 shift = vec3(100.0);
    for(int i = 0; i < 4; i++)
    { 
        v += a * smoothNoise3D(p);
        p = p * 2.17 + shift;
        a *= 0.5;
    }
    return v;
}

float sdSegment(vec2 p, vec2 a, vec2 b)
{
    vec2 pa = p - a, ba = b - a;
    float h = clamp(dot(pa, ba) / dot(ba, ba), 0.0, 1.0);
    return length(pa - ba * h);
}

float sdAriesShape(vec2 p)
{
    p.x = abs(p.x);
    
    const vec2 pts[15] = vec2[](
        vec2(0.000, -0.420),
        vec2(0.008, -0.301),
        vec2(0.024, -0.179),
        vec2(0.048, -0.057),
        vec2(0.082,  0.061),
        vec2(0.124,  0.170),
        vec2(0.177,  0.268),
        vec2(0.240,  0.350),
        vec2(0.311,  0.380),
        vec2(0.366,  0.367),
        vec2(0.404,  0.323),
        vec2(0.421,  0.264),
        vec2(0.414,  0.201),
        vec2(0.382,  0.149),
        vec2(0.320,  0.120)
    );

    float d = sdSegment(p, pts[0], pts[1]);
    for(int i = 1; i < 14; i++)
    {
        d = min(d, sdSegment(p, pts[i], pts[i+1]));
    }
    return d;
}

float portalSparks(vec2 normPos, float time)
{
    vec2 st = normPos * 8.0;
    vec2 id = floor(st);
    vec2 f = fract(st);
    
    float sparks = 0.0;
    for(int y = -1; y <= 1; y++)
    {
        for(int x = -1; x <= 1; x++)
        {
            vec2 g = vec2(float(x), float(y));
            vec2 cellId = id + g;
            float rnd = hash12(cellId);
            if(rnd > 0.45) continue; 
            
            float progress = fract(rnd * 7.13 + time * 0.25);
            float lifeFade = sin(progress * PI);
            
            vec2 pos = vec2(
                0.5 + 0.3 * sin(time * 1.2 + rnd * 6.28),
                1.0 - progress
            );
            
            vec2 delta = (g + pos) - f;
            float d = length(delta);
            float radius = 0.06 + 0.03 * sin(rnd * 13.0);
            
            float glow = smoothstep(radius, 0.0, d);
            sparks += glow * lifeFade;
        }
    }

    return sparks;
}

float energyWaves(vec2 pos, float time)
{
    const mat2 rot = mat2(0.80, 0.60, -0.60, 0.80);
    vec2 p2d = pos * 1.3;
    vec3 p = vec3(p2d, time * 0.2);
    float q = fbm(p);
    p2d = rot * p2d;
    float r = fbm(vec3(p2d + vec2(q * 1.2), time * 0.15));
    p2d = rot * p2d;
    float wave = fbm(vec3(p2d + vec2(r * 1.4), time * 0.1));
    return wave;
}

void main()
{
    vec2 normPos = (texCoord0 - 0.5) * 2.5; 
    
    vec2 warpOffset = vec2(
        fbm(vec3(normPos * 1.2, WarpTime * 0.45)) - 0.5,
        fbm(vec3(normPos * 1.2 + vec2(5.2, 1.3), WarpTime * 0.45 + 2.1)) - 0.5
    );
    
    vec2 warpedNormPos = normPos + warpOffset * 0.18;
    float warpedRiftDist = length(warpedNormPos);

    if(warpedRiftDist > 1.3)
    {
        discard;
    }

    vec3 finalRGB = vec3(0.0);
    float finalAlpha = 0.0;

    float portalCore = smoothstep(1.05, 0.75, warpedRiftDist);
    float thinEdge = exp(-pow((warpedRiftDist - 1.0) / 0.08, 2.0)); 
    float outerFade = smoothstep(1.3, 0.95, warpedRiftDist);

    float alphaVal = clamp((portalCore * 0.98 + thinEdge * 0.95) * outerFade, 0.0, 1.0);

    if(alphaVal > 0.001)
    {
        float wave = energyWaves(normPos, WarpTime);
        float verticalGrad = smoothstep(0.85, -0.75, normPos.y);
        float bottomGlow = pow(verticalGrad, 1.8) * 0.7;

        vec3 warpColor = mix(WARP_VOID, WARP_PURPLE, wave);
        warpColor = mix(warpColor, WARP_MAGENTA, pow(wave, 1.4) * 0.85);
        warpColor = mix(warpColor, WARP_CORE_GLOW, bottomGlow * (0.6 + 0.4 * wave));

        float sparks = portalSparks(normPos, WarpTime) * smoothstep(1.1, 0.2, warpedRiftDist);
        vec3 sparkColor = mix(WARP_CORE_GLOW, RAY_MAIN_COLOR, 0.5) * sparks * 1.3;
        warpColor += sparkColor * portalCore;

        vec3 edgeEnergy = WARP_CYAN * 2.2;
        finalRGB = mix(warpColor, edgeEnergy, thinEdge);
        finalAlpha = alphaVal;
    }

    float rayMask = smoothstep(0.3, 0.8, warpedRiftDist) * smoothstep(1.4, 0.85, warpedRiftDist);
    if(rayMask > 0.001)
    {
        float durationScale = 0.5; // FPS
        float strobeTime = floor(WarpTime * durationScale);
        vec3 totalLightning = vec3(0.0);

        for(int k = 0; k < 6; k++)
        {
            float seed = float(k) * 17.53 + strobeTime * 9.17;
            float activeCheck = hash12(vec2(seed, 1.618));
            if(activeCheck > 0.45) continue;

            float rayCycle = fract(WarpTime * durationScale + hash12(vec2(seed, 4.2)));
            float lifeFade = sin(rayCycle * PI); 

            float rayType = hash12(vec2(seed, 7.77));
            bool isAriesRay = (rayType > 0.9); 

            float lineDist = 0.0;
            float lengthFade = 1.0;

            if(isAriesRay)
            {
                float angle = hash12(vec2(seed, 3.14)) * 2.0 * PI;
                vec2 center = vec2(hash12(vec2(seed, 1.23)) - 0.5, hash12(vec2(seed, 2.34)) - 0.5) * 0.4;
                float scale = 0.7 + 0.4 * hash12(vec2(seed, 9.99));

                vec2 relP = (warpedNormPos - center) / scale;
                float ca = cos(angle), sa = sin(angle);
                vec2 localP = vec2(ca * relP.x + sa * relP.y, -sa * relP.x + ca * relP.y);

                vec3 fbmUV = vec3(warpedNormPos * 3.0, WarpTime * 2.5 + float(k) * 1.5);
                vec2 displacement = vec2(fbm(fbmUV) - 0.5, fbm(fbmUV + vec3(5.2)) - 0.5) * 0.08;

                lineDist = sdAriesShape(localP + displacement) * scale;
                lengthFade = smoothstep(1.2, 0.2, length(localP));
            }
            else
            {
                float dirRoll = hash12(vec2(seed, 8.88));
                vec2 p0;
                vec2 dir;

                if(dirRoll < 0.75)
                {
                    float rayAngle = hash12(vec2(seed, 3.14)) * 2.0 * PI - PI;
                    p0 = vec2(0.0);
                    dir = vec2(cos(rayAngle), sin(rayAngle));
                }
                else
                {
                    float originAngle = hash12(vec2(seed, 1.23)) * 2.0 * PI - PI;
                    float originDist = hash12(vec2(seed, 2.34)) * 0.6;
                    p0 = vec2(cos(originAngle), sin(originAngle)) * originDist;

                    float rayAngle = hash12(vec2(seed, 5.55)) * 2.0 * PI - PI;
                    dir = vec2(cos(rayAngle), sin(rayAngle));
                }

                vec2 perp = vec2(-dir.y, dir.x);
                vec2 relP = warpedNormPos - p0;

                float t = dot(relP, dir);
                float distPerp = dot(relP, perp);

                float curve = sin(t * 3.0 + seed * 2.0) * 0.18;
                vec3 fbmUV = vec3(warpedNormPos * 3.5, WarpTime * 2.5 + float(k) * 1.5);
                float rayDisplacement = (fbm(fbmUV) - 0.5) * 0.35 + curve;

                lineDist = abs(distPerp - rayDisplacement);
                lengthFade = smoothstep(-0.2, 0.1, t) * smoothstep(1.3, 0.5, t);
            }

            float coreGlow = 0.012; 
            float lightning = (coreGlow / (lineDist + 0.0015)) * lifeFade * lengthFade;

            lightning = min(lightning, 5.0) * rayMask;

            float coreMask = smoothstep(0.035, 0.004, lineDist);
            vec3 boltColor = mix(RAY_EFFECT_COLOR, RAY_MAIN_COLOR, coreMask);

            totalLightning += boltColor * lightning;
            finalAlpha = max(finalAlpha, min(lightning * 0.8, 1.0));
        }
        finalRGB += totalLightning;
    }

    if(finalAlpha <= 0.01)
    {
        discard;
    }

    fragColor = vec4(finalRGB, finalAlpha) * vertexColor;
}