package net.radzratz.catalystcore.client.blocks.entity.nebula;

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

@SuppressWarnings("null")
@EventBusSubscriber(modid = "catalystcore", value = Dist.CLIENT)
public class NebulaShaderController
{

    private static final ResourceLocation NEBULA_SHADER = ResourceLocation.fromNamespaceAndPath("catalystcore", "shaders/post/nebula.json");
    private static final long START_TIME = System.currentTimeMillis();
    private static Field passesField;

    private static final List<NebulaData> currentFrameNebulas = new ArrayList<>();
    private static final Map<BlockPos, Float> fadeMap = new HashMap<>();

    private static final boolean IS_IRIS_LOADED = 
        ModList.get().isLoaded("iris") || 
        ModList.get().isLoaded("oculus");

    public static class NebulaData
    {
        BlockPos pos;
        float radius;
        float strength;
        int mode;
        float[] colorA;
        float[] colorB;
        float progress;
        float starDensity;
        float currentFade;

        public NebulaData(BlockPos p, float r, float s, int m, float[] cA, float[] cB, float prog, float sd) {
            pos = p;
            radius = r;
            strength = s;
            mode = m;
            colorA = cA;
            colorB = cB;
            progress = prog;
            starDensity = sd;
            currentFade = 1.0f;
        }
    }

    public static void registerNebula(BlockPos pos, float radius, float strength, int mode, float[] colorA, float[] colorB, float progress, float starDensity)
    {
        if(currentFrameNebulas.size() < 100)
        {
            currentFrameNebulas.add(new NebulaData(pos, radius, strength, mode, colorA, colorB, progress, starDensity));
        }
    }

    static
    {
        try
        {
            passesField = PostChain.class.getDeclaredField("passes");
            passesField.setAccessible(true);
        }
        catch(NoSuchFieldException e)
        {
            System.err.println("[CatalystCore] Error: Can't access Nebula passes.");
        }
    }

    @SubscribeEvent
    public static void onRenderStage(RenderLevelStageEvent event)
    {
        if(IS_IRIS_LOADED)
        {
            if(currentFrameNebulas.size() != 0) currentFrameNebulas.clear();
            return;
        }

        if(event.getStage() != RenderLevelStageEvent.Stage.AFTER_LEVEL) return;

        Minecraft mc = Minecraft.getInstance();
        if(mc.player == null || mc.level == null)
        {
            currentFrameNebulas.clear();
            return;
        }

        Camera camera = event.getCamera();
        Vec3 camPos = camera.getPosition();

        List<NebulaData> validNebulas = new ArrayList<>();
        Set<BlockPos> processedPositions = new HashSet<>();

        for(NebulaData data : currentFrameNebulas)
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
                    validNebulas.add(data);
                }
                else
                {
                    fadeMap.remove(data.pos);
                }
            }
        }

        fadeMap.keySet().retainAll(processedPositions);

        if(validNebulas.isEmpty())
        {
            if(mc.gameRenderer.currentEffect() != null && NEBULA_SHADER.toString().equals(mc.gameRenderer.currentEffect().getName()))
            {
                mc.gameRenderer.shutdownEffect();
            }
            currentFrameNebulas.clear();
            return;
        }
        else
        {
            if(mc.gameRenderer.currentEffect() == null || !NEBULA_SHADER.toString().equals(mc.gameRenderer.currentEffect().getName()))
            {
                mc.gameRenderer.loadEffect(NEBULA_SHADER);
            }
        }

        validNebulas.sort(Comparator.comparingDouble(data ->
                camPos.distanceToSqr(Vec3.atCenterOf(data.pos))
        ));

        int count = 0;
        float[] centersX = new float[]{-1f, -1f, -1f, -1f, -1f, -1f, -1f, -1f, -1f, -1f};
        float[] centersY = new float[]{-1f, -1f, -1f, -1f, -1f, -1f, -1f, -1f, -1f, -1f};
        float[] radii = new float[10];
        float[] intensities = new float[10];
        float[] modes = new float[10];
        float[] colorsA = new float[30];
        float[] colorsB = new float[30];
        float[] progresses = new float[10];
        float[] starDensities = new float[10];

        for(NebulaData data : validNebulas)
        {
            if(count >= 10) break;

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
                modes[count] = (float) data.mode;
                progresses[count] = data.progress;
                starDensities[count] = data.starDensity;

                colorsA[count * 3] = data.colorA[0];
                colorsA[count * 3 + 1] = data.colorA[1];
                colorsA[count * 3 + 2] = data.colorA[2];

                colorsB[count * 3] = data.colorB[0];
                colorsB[count * 3 + 1] = data.colorB[1];
                colorsB[count * 3 + 2] = data.colorB[2];

                count++;
            }
        }

        PostChain effect = mc.gameRenderer.currentEffect();
        if(effect != null && NEBULA_SHADER.toString().equals(effect.getName()) && passesField != null && count > 0)
        {
            float timeInSeconds = (System.currentTimeMillis() - START_TIME) / 1000.0f;
            try
            {
                @SuppressWarnings("unchecked")
                List<PostPass> passes = (List<PostPass>) passesField.get(effect);
                for(PostPass pass : passes)
                {
                    pass.getEffect().safeGetUniform("NebulaCount").set(count);
                    pass.getEffect().safeGetUniform("NebulaCentersX").set(centersX);
                    pass.getEffect().safeGetUniform("NebulaCentersY").set(centersY);
                    pass.getEffect().safeGetUniform("NebulaRadii").set(radii);
                    pass.getEffect().safeGetUniform("NebulaIntensities").set(intensities);
                    pass.getEffect().safeGetUniform("NebulaModes").set(modes);
                    pass.getEffect().safeGetUniform("NebulaColorsA").set(colorsA);
                    pass.getEffect().safeGetUniform("NebulaColorsB").set(colorsB);
                    pass.getEffect().safeGetUniform("NebulaProgress").set(progresses);
                    pass.getEffect().safeGetUniform("NebulaStarDensity").set(starDensities);

                    if(pass.getEffect().getUniform("NebulaTime") != null)
                    {
                        pass.getEffect().safeGetUniform("NebulaTime").set(timeInSeconds);
                    }
                }
            }
            catch(IllegalAccessException e)
            {
            }
        }

        currentFrameNebulas.clear();
    }
}