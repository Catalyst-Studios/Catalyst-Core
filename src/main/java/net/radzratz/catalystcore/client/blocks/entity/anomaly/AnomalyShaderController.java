package net.radzratz.catalystcore.client.blocks.entity.anomaly;

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
import net.neoforged.fml.ModList;
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


@EventBusSubscriber(modid = "catalystcore", value = Dist.CLIENT)
public class AnomalyShaderController
{

    private static final ResourceLocation LENS_SHADER = ResourceLocation.fromNamespaceAndPath("catalystcore", "shaders/post/gravity_lens.json");
    private static final long START_TIME = System.currentTimeMillis();
    private static Field passesField;

    private static final List<AnomalyData> currentFrameAnomalies = new ArrayList<>();
    private static final Map<BlockPos, Float> fadeMap = new HashMap<>();

    private static final boolean IS_IRIS_LOADED = 
        ModList.get().isLoaded("iris") || 
        ModList.get().isLoaded("oculus");

    public static class AnomalyData
    {
        BlockPos pos; float radius; float strength; float currentFade;
        public AnomalyData(BlockPos p, float r, float s) { pos = p; radius = r; strength = s; currentFade = 1.0f; }
    }

    public static void registerAnomaly(BlockPos pos, float radius, float strength)
    {
        if(currentFrameAnomalies.size() < 100) //at max, 100 anomalies
        { 
            currentFrameAnomalies.add(new AnomalyData(pos, radius, strength));
        }
    }

    static {
        try {
            passesField = PostChain.class.getDeclaredField("passes");
            passesField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            System.err.println("[CatalystCore] Error: No se pudo acceder a los pases del shader.");
        }
    }

    
    @SubscribeEvent
    public static void onRenderStage(RenderLevelStageEvent event)
    {
        if(IS_IRIS_LOADED)
        {
            currentFrameAnomalies.clear();
            return;
        }

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

        if(validAnomalies.isEmpty())
        {
            if(mc.gameRenderer.currentEffect() != null && LENS_SHADER.toString().equals(mc.gameRenderer.currentEffect().getName()))
            {
                mc.gameRenderer.shutdownEffect();
            }
            currentFrameAnomalies.clear();
            return; 
        }
        else
        {
            if(mc.gameRenderer.currentEffect() == null)
            {
                mc.gameRenderer.loadEffect(LENS_SHADER);
            }
        }

        validAnomalies.sort(Comparator.comparingDouble(data -> 
            camPos.distanceToSqr(Vec3.atCenterOf(data.pos))
        ));
        //Now we have all blocks ready to render 
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

            if(centerPos.w > 0.0f)
            {
                float ndcX = centerPos.x / centerPos.w;
                float ndcY = centerPos.y / centerPos.w;
                centersX[count] = (ndcX + 1.0f) * 0.5f;
                centersY[count] = (ndcY + 1.0f) * 0.5f;

                float edgeNdcY = edgePos.y / edgePos.w;
                float edgeScreenY = (edgeNdcY + 1.0f) * 0.5f;
                radii[count] = Math.abs(edgeScreenY - centersY[count]);
                
                intensities[count] = finalIntensity;
                count++;
            }
        }

        PostChain effect = mc.gameRenderer.currentEffect();
        if(effect != null && LENS_SHADER.toString().equals(effect.getName()) && passesField != null && count > 0)
        {
            float timeInSeconds = (System.currentTimeMillis() - START_TIME) / 1000.0f;
            try
            {
                @SuppressWarnings("unchecked")
                List<PostPass> passes = (List<PostPass>) passesField.get(effect);
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
            } catch (IllegalAccessException e) {}
        }

        currentFrameAnomalies.clear();
    }
}