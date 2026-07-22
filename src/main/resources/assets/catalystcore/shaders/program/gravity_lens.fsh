#version 150

uniform sampler2D DiffuseSampler; //base scene texture
uniform vec2 OutSize; //screen or render target size
uniform float AnomalyTime; //time variable for animations

uniform int AnomalyCount; //number of active anomalies
uniform float AnomalyCentersX[10]; //x coordinates of the anomalies
uniform float AnomalyCentersY[10]; //y coordinates of the anomalies
uniform float AnomalyRadii[10]; //radii of the anomalies
uniform float AnomalyIntensities[10]; //visual intensities of the anomalies

in vec2 texCoord; //current texture coordinates
out vec4 fragColor; //final output color for the fragment

const float EVENT_HORIZON_MULT = 1.5; //size multiplier for the event horizon
const float LENS_RADIUS_MULT = 5.0; //size multiplier for the gravitational lens effect
const float GRAVITY_STRENGTH_MULT = 2.0; //strength multiplier for the distortion

const float DISK_Y_SCALE = 2.0; //vertical squash factor for the accretion disk
const float DISK_INNER_LIMIT_MULT = 1.0; //inner boundary multiplier for the disk
const float DISK_OUTER_LIMIT_MULT = 7.5; //outer boundary multiplier for the disk

const float SPIRAL_NUM_ARMS = 12.0; //number of spiral arms in the galaxy
const float SPIRAL_TWIST = 24.0; //how tightly the spiral arms are wound
const float SPIN_SPEED_MULT = 1.5; //rotation speed multiplier for the galaxy
const float SPIRAL_ARM_WIDTH_MIN = 0.25; //inner spiral arm thickness
const float SPIRAL_ARM_WIDTH_MAX = 0.95; //outer spiral arm thickness
const float SPIRAL_ARM_FALLOFF = 0.65; //sharpness of the spiral arm edges (higher = softer/cloudier edges)

const float NOISE_SCALE = 3.5; //base scale for the noise texture
const float NOISE_TIME_MULT_1 = 0.15; //animation speed for the primary noise
const float NOISE_TIME_MULT_2 = 0.1; //animation speed for the secondary noise

const float STAR1_DENSITY = 12.0; //density of the primary star layer
const float STAR1_POW = 12.0; //sharpness/size of the primary stars
const float STAR1_BRIGHTNESS = 2.5; //brightness of the primary stars

const float STAR2_DENSITY = 25.0; //density of the secondary micro-stars layer
const float STAR2_OFFSET = 10.0; //coordinate offset for the secondary stars
const float STAR2_POW = 10.0; //sharpness/size of the secondary stars
const float STAR2_BRIGHTNESS = 1.5; //brightness of the secondary stars

const float STAR_ARM_MIX_MIN = 0.2; //minimum star visibility outside spiral arms
const float STAR_ARM_MIX_MAX = 0.8; //maximum star visibility inside spiral arms

const float ALPHA_SMOOTHSTEP_MAX = 0.6; //upper limit for alpha smoothstep transition
const float ALPHA_ARM_MULT = 0.3; //alpha contribution from the spiral arms
const float ALPHA_STAR_MULT = 0.2; //alpha contribution from the stars

const vec3 COLOR_STAR = vec3(0.7, 0.25, 0.95); //primary color for the galaxy dust/stars (purple)
const vec3 COLOR_VOID = vec3(0.05, 0.0, 0.1); //background color for the galaxy void (dark purple/black)
const vec3 COLOR_PURE_STAR = vec3(0.9, 0.9, 1.0); //color for the bright star cores (slightly blue-white)

//helper function: modulo 289 for a 3d vector
vec3 mod289(vec3 x)
{
    return x - floor(x * (1.0 / 289.0)) * 289.0;
}

//helper function: modulo 289 for a 4d vector
vec4 mod289(vec4 x)
{
    return x - floor(x * (1.0 / 289.0)) * 289.0;
}

//helper function: polynomial permutation
vec4 permute(vec4 x)
{
    return mod289(((x * 34.0) + 1.0) * x);
}

//helper function: inverse square root approximation
vec4 taylorInvSqrt(vec4 r)
{
    return 1.79284291400159 - 0.85373472095314 * r;
}

//simplex 3d noise function
float snoise(vec3 v)
{
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

//main shader entry point
void main()
{
    vec2 totalDistortion = vec2(0.0); //accumulator for gravitational distortion
    float maxBlackHoleIntensity = 0.0; //highest intensity of the black hole core
    vec4 totalDiskColor = vec4(0.0); //accumulated color for the accretion disk

    float aspectRatio = OutSize.x / OutSize.y;

    //max 10 blackhole at the same time
    for(int i = 0; i < 10; i++)
    {
        if(i >= AnomalyCount)
        {
            break; //stop if we processed all active anomalies
        }

        vec2 center = vec2(AnomalyCentersX[i], AnomalyCentersY[i]);
        if(center.x < 0.0 || center.y < 0.0)
        {
            continue;
        }

        vec2 dir = texCoord - center; //direction vector from the center
        vec2 distortedDir = dir; //direction vector with aspect ratio correction
        distortedDir.x *= aspectRatio; //apply aspect ratio
        float dist = length(distortedDir); //distance from the center
        
        float eventHorizon = AnomalyRadii[i] * EVENT_HORIZON_MULT; //radius of absolute blackness
        float lensRadius = AnomalyRadii[i] * LENS_RADIUS_MULT; //radius of gravitational lensing
        float strength = GRAVITY_STRENGTH_MULT * AnomalyIntensities[i]; //distortion power

        //process gravitational lens effect
        if(dist < lensRadius && dist > eventHorizon)
        {
            float distortion = (lensRadius - dist) / lensRadius; //distortion falloff
            vec2 normDir = normalize(distortedDir); //normalized direction
            normDir.x /= aspectRatio; //revert aspect ratio for screen space
            totalDistortion += normDir * (distortion * distortion) * strength * dist; //add to total distortion
        }

        vec2 diskDir = dir; //direction vector for the disk
        diskDir.x *= aspectRatio; //aspect ratio correction
        diskDir.y *= DISK_Y_SCALE; //flatten the y axis to simulate a 3d angled disk
        float diskDist = length(diskDir); //distance for disk calculations

        float diskInnerLimit = eventHorizon * DISK_INNER_LIMIT_MULT; //start of the accretion disk
        float diskOuterLimit = eventHorizon * DISK_OUTER_LIMIT_MULT; //end of the accretion disk

        //process the galaxy/accretion disk if within bounds and visible
        if(diskDist > diskInnerLimit && diskDist < diskOuterLimit && AnomalyIntensities[i] > 0.05)
        {
            float normDist = (diskDist - diskInnerLimit) / (diskOuterLimit - diskInnerLimit); //normalized distance (0.0 to 1.0) inside the disk
            float diskFringe = smoothstep(1.0, 0.4, normDist); //soft fade at the edges of the disk
            
            float angle = atan(diskDir.y, diskDir.x); //current angle around the center
            
            float spinSpeed = AnomalyTime * SPIN_SPEED_MULT; //rotation animation offset
            
            // Warp the angle with low-amplitude noise to give plasma/gas cloud texture
            // without destroying the spiral arm structure ("""""""""accretion disk look""""""""")
            vec3 warpP = vec3(diskDir * 2.5, AnomalyTime * 0.07);
            float warp1 = snoise(warpP) * 0.15;
            float warp2 = snoise(warpP * 2.1 + vec3(5.3, 1.7, 0.0)) * 0.08;
            float warp3 = snoise(warpP * 4.3 - vec3(2.1, 3.4, 0.0)) * 0.04;
            float angleWarp = warp1 + warp2 + warp3; //subtle turbulence keeps arms visible but with plasma edges
            float warpedAngle = angle + angleWarp;
            
            float spiralBase = sin(warpedAngle * SPIRAL_NUM_ARMS - normDist * SPIRAL_TWIST + spinSpeed); //base wave for spiral arms with gas turbulence
            spiralBase = spiralBase * 0.5 + 0.5; //map from [-1, 1] to [0, 1]
            
            float thicknessCutoff = mix(SPIRAL_ARM_WIDTH_MIN, SPIRAL_ARM_WIDTH_MAX, normDist); //variable arm thickness depending on distance
            float arm = smoothstep(thicknessCutoff - SPIRAL_ARM_FALLOFF, 1.0, spiralBase); //crispness of the spiral arm edges (soft = more cloud-like)
            
            vec3 p = vec3(diskDir * NOISE_SCALE, AnomalyTime * NOISE_TIME_MULT_1); //3d point for noise sampling
            
            float n1 = 1.0 - abs(snoise(p)); //first layer of billowy noise
            float n2 = 1.0 - abs(snoise(p * 2.0 - vec3(AnomalyTime * NOISE_TIME_MULT_2))); //second layer of noise
            float n3 = 1.0 - abs(snoise(p * 4.0)); //third, detailed layer of noise
            
            float lines = pow(n1, 2.0) * 0.6 + pow(n2, 2.0) * 0.3 + pow(n3, 2.0) * 0.1; //combined nebula texture
            
            float starNoise1 = max(0.0, snoise(p * STAR1_DENSITY)); //base noise for primary stars
            float stars = pow(starNoise1, STAR1_POW) * STAR1_BRIGHTNESS; //emphasize peaks for primary stars
            
            float starNoise2 = max(0.0, snoise(p * STAR2_DENSITY + vec3(STAR2_OFFSET))); //base noise for secondary stars
            float microStars = pow(starNoise2, STAR2_POW) * STAR2_BRIGHTNESS; //emphasize peaks for secondary stars
            
            float totalStars = stars + microStars; //combined star layers

            float intensity = mix(0.3, 1.2, arm); //nebula intensity varies with spiral arm presence
            float finalStructure = lines * intensity; //final nebula structure
            
            float finalStars = totalStars * mix(STAR_ARM_MIX_MIN, STAR_ARM_MIX_MAX, arm); //stars are denser inside the arms

            vec3 skyColor = mix(COLOR_VOID, COLOR_STAR, clamp(finalStructure, 0.0, 1.0)); //blend void and star dust colors
            
            skyColor += COLOR_PURE_STAR * finalStars; //add the bright star dots
            
            float alpha = smoothstep(0.0, ALPHA_SMOOTHSTEP_MAX, finalStructure + (arm * ALPHA_ARM_MULT) + (finalStars * ALPHA_STAR_MULT)) * diskFringe * AnomalyIntensities[i]; //calculate final transparency
            
            vec4 finalBlockDiskColor = vec4(skyColor, alpha); //combine color and alpha
            totalDiskColor = mix(totalDiskColor, finalBlockDiskColor, finalBlockDiskColor.a); //blend this disk over previous ones
        }

        //process the black hole core (event horizon)
        if(dist <= eventHorizon)
        {
            maxBlackHoleIntensity = max(maxBlackHoleIntensity, AnomalyIntensities[i]); //keep the highest intensity core
        }
    }

    vec2 finalUV = texCoord - totalDistortion; //apply gravitational lensing distortion to uvs
    vec4 sceneColor = texture(DiffuseSampler, finalUV);

    //apply the black hole or the accretion disk over the background
    if(maxBlackHoleIntensity > 0.0)
    {
        sceneColor = mix(sceneColor, vec4(0.0, 0.0, 0.0, 1.0), maxBlackHoleIntensity); //draw black hole core
    }
    else
    {
        sceneColor = mix(sceneColor, vec4(totalDiskColor.rgb, 1.0), totalDiskColor.a); //draw accretion disk
    }

    fragColor = vec4(sceneColor.rgb, 1.0); //output the final pixel color with forced alpha=1.0 to avoid black bands
}