package net.radzratz.catalystcore.client.blocks.entity.anomaly;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.client.renderer.PostPass;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import org.joml.Vector4f;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("null")
@EventBusSubscriber(modid = "catalystcore", value = Dist.CLIENT)
public class GravityAnomalyShaderController
{

    private static final ResourceLocation LENS_SHADER = ResourceLocation.fromNamespaceAndPath("catalystcore", "shaders/post/gravity_lens.json");
    private static final long START_TIME = System.currentTimeMillis();
    private static Field passesField;

    private static final List<AnomalyData> currentFrameAnomalies = new ArrayList<>();
    private static final Map<BlockPos, Float> fadeMap = new HashMap<>();

    private static PostChain lensPostChain;
    private static int lastWidth = 0;
    private static int lastHeight = 0;

    public static class AnomalyData
    {
        BlockPos pos; float radius; float strength; float currentFade;
        public AnomalyData(BlockPos p, float r, float s) { pos = p; radius = r; strength = s; currentFade = 1.0f; }
    }

    public static void registerAnomaly(BlockPos pos, float radius, float strength)
    {
        if(currentFrameAnomalies.size() < 100) //at max, 100 anomalies, but will render only ten :p
        { 
            currentFrameAnomalies.add(new AnomalyData(pos, radius, strength));
        }
    }

    static {
        try {
            passesField = PostChain.class.getDeclaredField("passes");
            passesField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            System.err.println("[CatalystCore] Error, can't access passes.");
        }
    }

    @SubscribeEvent
    public static void onRenderStage(RenderLevelStageEvent event)
    {
        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_LEVEL) return;
        
        Minecraft mc = Minecraft.getInstance();
        if(mc.player == null || mc.level == null)
        {
            currentFrameAnomalies.clear();
            return;
        }

        Camera camera = event.getCamera();
        Vec3 camPos = camera.getPosition();

        List<AnomalyData> validAnomalies = new ArrayList<>();
        Set<BlockPos> processedPositions = new HashSet<>();

        for (AnomalyData data : currentFrameAnomalies)
        {
            Vec3 targetCenter = Vec3.atCenterOf(data.pos);
            double distanceSq = camPos.distanceToSqr(targetCenter);

            if(distanceSq <= 400.0)
            {
                ClipContext context = new ClipContext(
                        camPos, 
                        targetCenter, 
                        ClipContext.Block.VISUAL, 
                        ClipContext.Fluid.NONE,   
                        mc.player
                );
                
                BlockHitResult hit = mc.level.clip(context);
                boolean isVisible = hit.getType() == HitResult.Type.MISS || hit.getBlockPos().equals(data.pos);

                float targetFade = isVisible ? 1.0f : 0.0f;
                float currentFade = fadeMap.getOrDefault(data.pos, 0.0f);
                currentFade += (targetFade - currentFade) * 0.15f; 

                if(currentFade > 0.01f)
                {
                    fadeMap.put(data.pos, currentFade);
                    processedPositions.add(data.pos);
                    
                    data.currentFade = currentFade;
                    validAnomalies.add(data);
                }
                else
                {
                    fadeMap.remove(data.pos);
                }
            }
        }

        fadeMap.keySet().retainAll(processedPositions);

        if(!validAnomalies.isEmpty())
        {
            validAnomalies.sort(Comparator.comparingDouble(data -> 
                camPos.distanceToSqr(Vec3.atCenterOf(data.pos))
            ));

            int count = 0;
            float[] centersX = new float[10];
            float[] centersY = new float[10];
            float[] radii = new float[10];
            float[] intensities = new float[10];

            for(AnomalyData data : validAnomalies)
            {
                if(count >= 10) break; //no more than 10 pls

                BlockPos target = data.pos;
                Vec3 targetCenter = Vec3.atCenterOf(target);
                
                double distanceSq = camPos.distanceToSqr(targetCenter);
                float distance = (float) Math.sqrt(distanceSq);
                
                float baseIntensity = Math.max(0.0f, 1.0f - (distance / 20.0f));
                float finalIntensity = baseIntensity * data.strength * data.currentFade;

                Vector4f centerPos = new Vector4f(
                        (float) (target.getX() + 0.5D - camPos.x),
                        (float) (target.getY() + 0.5D - camPos.y),
                        (float) (target.getZ() + 0.5D - camPos.z),
                        1.0f
                );

                event.getModelViewMatrix().transform(centerPos);
                
                Vector4f edgePos = new Vector4f(centerPos);
                edgePos.y += data.radius; 

                event.getProjectionMatrix().transform(centerPos);
                event.getProjectionMatrix().transform(edgePos);

                if(centerPos.w > 0.4f)
                {
                    float ndcX = centerPos.x / centerPos.w;
                    float ndcY = centerPos.y / centerPos.w;
                    centersX[count] = (ndcX + 1.0f) * 0.5f;
                    centersY[count] = (ndcY + 1.0f) * 0.5f;

                    float edgeNdcY = edgePos.y / edgePos.w;
                    float edgeScreenY = (edgeNdcY + 1.0f) * 0.5f;

                    float calculatedRadius = Math.abs(edgeScreenY - centersY[count]);
                    radii[count] = Math.min(calculatedRadius, 1.5f);
                    
                    if(centerPos.w < 1.0f)
                    {
                        float proximityFade = (centerPos.w - 0.2f) / 0.8f;
                        finalIntensity *= Math.max(0.0f, proximityFade);
                    }
                    
                    intensities[count] = finalIntensity;
                    count++;
                }
            }

            if(count > 0)
            {
                renderPostChain(mc, event.getPartialTick().getGameTimeDeltaTicks(), count, centersX, centersY, radii, intensities);
            }
        }

        currentFrameAnomalies.clear();
    }

    private static void renderPostChain(Minecraft mc, float deltaTicks, int count, float[] centersX, float[] centersY, float[] radii, float[] intensities)
    {
        int currentWidth = mc.getWindow().getWidth();
        int currentHeight = mc.getWindow().getHeight();

        if(lensPostChain == null)
        {
            try
            {
                lensPostChain = new PostChain(mc.getTextureManager(), mc.getResourceManager(), mc.getMainRenderTarget(), LENS_SHADER);
                lensPostChain.resize(currentWidth, currentHeight);
                lastWidth = currentWidth;
                lastHeight = currentHeight;
            }
            catch(Exception e)
            {
                System.err.println("[CatalystCore] Error loading PostChain: " + e.getMessage());
                return;
            }
        }
        else if(lastWidth != currentWidth || lastHeight != currentHeight)
        {
            lensPostChain.resize(currentWidth, currentHeight);
            lastWidth = currentWidth;
            lastHeight = currentHeight;
        }

        if(passesField != null)
        {
            float timeInSeconds = (System.currentTimeMillis() - START_TIME) / 1000.0f;
            try
            {
                @SuppressWarnings("unchecked")
                List<PostPass> passes = (List<PostPass>) passesField.get(lensPostChain);
                for(PostPass pass : passes)
                {
                    pass.getEffect().safeGetUniform("AnomalyCount").set(count);
                    pass.getEffect().safeGetUniform("AnomalyCentersX").set(centersX);
                    pass.getEffect().safeGetUniform("AnomalyCentersY").set(centersY);
                    pass.getEffect().safeGetUniform("AnomalyRadii").set(radii);
                    pass.getEffect().safeGetUniform("AnomalyIntensities").set(intensities);

                    if(pass.getEffect().getUniform("AnomalyTime") != null)
                    {
                        pass.getEffect().safeGetUniform("AnomalyTime").set(timeInSeconds);
                    }
                }

                lensPostChain.process(deltaTicks);

            }
            catch(IllegalAccessException e)
            {
                e.printStackTrace();
            }
            finally
            {
                mc.getMainRenderTarget().bindWrite(true);
                RenderSystem.enableBlend();
                RenderSystem.defaultBlendFunc();
                RenderSystem.enableDepthTest();
                RenderSystem.depthMask(true);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            }
        }
    }
}