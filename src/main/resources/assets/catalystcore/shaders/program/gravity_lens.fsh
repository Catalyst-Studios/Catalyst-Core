#version 150

uniform sampler2D DiffuseSampler;
uniform vec2 OutSize;
uniform float AnomalyTime;

uniform int AnomalyCount;
uniform float AnomalyCentersX[10];
uniform float AnomalyCentersY[10];
uniform float AnomalyRadii[10];
uniform float AnomalyIntensities[10];

in vec2 texCoord;
out vec4 fragColor;

// --- FUNCIONES DE RUIDO 3D ---
vec3 mod289(vec3 x) { return x - floor(x * (1.0 / 289.0)) * 289.0; }
vec4 mod289(vec4 x) { return x - floor(x * (1.0 / 289.0)) * 289.0; }
vec4 permute(vec4 x) { return mod289(((x * 34.0) + 1.0) * x); }
vec4 taylorInvSqrt(vec4 r) { return 1.79284291400159 - 0.85373472095314 * r; }

float snoise(vec3 v) {
    const vec2 C = vec2(1.0/6.0, 1.0/3.0);
    const vec4 D = vec4(0.0, 0.5, 1.0, 2.0);
    vec3 i = floor(v + dot(v, C.yyy));
    vec3 x0 = v - i + dot(i, C.xxx);
    vec3 g = step(x0.yzx, x0.xyz);
    vec3 l = 1.0 - g;
    vec3 i1 = min(g.xyz, l.zxy);
    vec3 i2 = max(g.xyz, l.zxy);
    vec3 x1 = x0 - i1 + C.xxx;
    vec3 x2 = x0 - i2 + C.yyy;
    vec3 x3 = x0 - D.yyy;
    i = mod289(i);
    vec4 p = permute(permute(permute(i.z + vec4(0.0, i1.z, i2.z, 1.0)) + i.y + vec4(0.0, i1.y, i2.y, 1.0)) + i.x + vec4(0.0, i1.x, i2.x, 1.0));
    vec4 j = p - 49.0 * floor(p * (1.0/49.0));
    vec4 x_ = floor(j * (1.0/7.0));
    vec4 y_ = floor(j - 7.0 * x_);
    vec4 x = x_ * (1.0/7.0) + 0.5/7.0 - 0.5;
    vec4 y = y_ * (1.0/7.0) + 0.5/7.0 - 0.5;
    vec4 h = 1.0 - abs(x) - abs(y);
    vec4 b0 = vec4(x.xy, y.xy); vec4 b1 = vec4(x.zw, y.zw);
    vec4 s0 = floor(b0)*2.0 + 1.0; vec4 s1 = floor(b1)*2.0 + 1.0;
    vec4 sh = -step(h, vec4(0.0));
    vec4 a0 = b0.xzyw + s0.xzyw * sh.xxyy; vec4 a1 = b1.xzyw + s1.xzyw * sh.zzww;
    vec3 p0 = vec3(a0.xy, h.x); vec3 p1 = vec3(a0.zw, h.y); vec3 p2 = vec3(a1.xy, h.z); vec3 p3 = vec3(a1.zw, h.w);
    vec4 norm = taylorInvSqrt(vec4(dot(p0,p0), dot(p1,p1), dot(p2, p2), dot(p3,p3)));
    p0 *= norm.x; p1 *= norm.y; p2 *= norm.z; p3 *= norm.w;
    vec4 m = max(0.6 - vec4(dot(x0,x0), dot(x1,x1), dot(x2,x2), dot(x3,x3)), 0.0);
    m = m * m;
    return 42.0 * dot(m*m, vec4(dot(p0,x0), dot(p1,x1), dot(p2,x2), dot(p3,x3)));
}
// --------------------------------------------------------

void main() {
    vec2 totalDistortion = vec2(0.0);
    float maxBlackHoleIntensity = 0.0;
    vec4 totalDiskColor = vec4(0.0); 

    float aspectRatio = OutSize.x / OutSize.y;

    for (int i = 0; i < 10; i++) {
        if (i >= AnomalyCount) break;

        vec2 center = vec2(AnomalyCentersX[i], AnomalyCentersY[i]);
        if (center.x < 0.0 || center.y < 0.0) continue; 

        vec2 dir = texCoord - center;
        vec2 distortedDir = dir;
        distortedDir.x *= aspectRatio;
        float dist = length(distortedDir);
        
        float eventHorizon = AnomalyRadii[i] * 1.5; 
        float lensRadius = AnomalyRadii[i] * 5.0;
        float strength = 2.0 * AnomalyIntensities[i]; 

        // --- DISTORSIÓN GRAVITACIONAL ---
        if (dist < lensRadius && dist > eventHorizon) {
            float distortion = (lensRadius - dist) / lensRadius;
            vec2 normDir = normalize(distortedDir);
            normDir.x /= aspectRatio;
            totalDistortion += normDir * (distortion * distortion) * strength * dist;
        }

        // --- GALAXIA ---
        vec2 diskDir = dir;
        diskDir.x *= aspectRatio; 
        diskDir.y *= 2.5; 
        float diskDist = length(diskDir);

        float diskInnerLimit = eventHorizon * 1.0;
        float diskOuterLimit = eventHorizon * 7.5; 

        if (diskDist > diskInnerLimit && diskDist < diskOuterLimit && AnomalyIntensities[i] > 0.05) {
            
            float normDist = (diskDist - diskInnerLimit) / (diskOuterLimit - diskInnerLimit);
            float diskFringe = smoothstep(1.0, 0.4, normDist); 
            
            float angle = atan(diskDir.y, diskDir.x);
            
            // 1. MÁSCARA DE LOS BRAZOS
            float numArms = 12.0;
            float twist = 24.0;  
            float spinSpeed = AnomalyTime * 1.5; 
            
            float spiralBase = sin(angle * numArms - normDist * twist + spinSpeed);
            spiralBase = spiralBase * 0.5 + 0.5; 
            
            float thicknessCutoff = mix(0.1, 0.8, normDist);
            float arm = smoothstep(thicknessCutoff - 0.2, 1.0, spiralBase); 
            
            // 2. TEXTURA DE NEBULOSA
            vec3 p = vec3(diskDir * 3.5, AnomalyTime * 0.15); 
            
            float n1 = 1.0 - abs(snoise(p));
            float n2 = 1.0 - abs(snoise(p * 2.0 - vec3(AnomalyTime * 0.1)));
            float n3 = 1.0 - abs(snoise(p * 4.0));
            
            float lines = pow(n1, 2.0) * 0.6 + pow(n2, 2.0) * 0.3 + pow(n3, 2.0) * 0.1;
            
            // 3. RECUPERAR LAS ESTRELLAS (SUTILES)
            // Aumentado el pow() para hacerlas más pequeñas, bajado el multiplicador para menos brillo
            float starNoise1 = max(0.0, snoise(p * 12.0));
            float stars = pow(starNoise1, 12.0) * 2.5; 
            
            float starNoise2 = max(0.0, snoise(p * 25.0 + vec3(10.0)));
            float microStars = pow(starNoise2, 10.0) * 1.5; 
            
            float totalStars = stars + microStars;

            // 4. SEPARACIÓN: BRAZOS vs HUECOS
            float intensity = mix(0.3, 1.2, arm);
            float finalStructure = lines * intensity;
            
            // Reducimos también la fuerza con la que las estrellas se muestran en los brazos
            float finalStars = totalStars * mix(0.2, 0.8, arm);

            // 5. COLORES Y MEZCLA
            vec3 color_star = vec3(0.7, 0.25, 0.95); 
            vec3 color_void = vec3(0.05, 0.0, 0.1);  
            vec3 pure_star_color = vec3(0.9, 0.9, 1.0); // Blanco ligeramente rebajado
            
            vec3 skyColor = mix(color_void, color_star, clamp(finalStructure, 0.0, 1.0));
            
            skyColor += pure_star_color * finalStars;
            
            // Bajamos el impacto de las estrellas en el canal Alpha para que no generen bloques opacos
            float alpha = smoothstep(0.0, 0.6, finalStructure + (arm * 0.3) + (finalStars * 0.2)) * diskFringe * AnomalyIntensities[i];
            
            vec4 finalBlockDiskColor = vec4(skyColor, alpha);
            totalDiskColor = mix(totalDiskColor, finalBlockDiskColor, finalBlockDiskColor.a);
        }

        // --- NÚCLEO NEGRO ---
        if (dist <= eventHorizon) {
            maxBlackHoleIntensity = max(maxBlackHoleIntensity, AnomalyIntensities[i]);
        }
    }

    vec2 finalUV = texCoord - totalDistortion;
    vec4 sceneColor = texture(DiffuseSampler, finalUV);

    if (maxBlackHoleIntensity > 0.0) {
        sceneColor = mix(sceneColor, vec4(0.0, 0.0, 0.0, 1.0), maxBlackHoleIntensity);
    } else {
        sceneColor = mix(sceneColor, vec4(totalDiskColor.rgb, 1.0), totalDiskColor.a);
    }

    fragColor = sceneColor;
}