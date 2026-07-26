package net.radzratz.catalystcore.client.blocks.entity.nebula;

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
import java.util.Arrays;
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

    private static PostChain nebulaPostChain;
    private static int lastWidth = 0;
    private static int lastHeight = 0;

    private static int calculatedCount = 0;
    private static final float[] centersX = new float[10];
    private static final float[] centersY = new float[10];
    private static final float[] radii = new float[10];
    private static final float[] intensities = new float[10];
    private static final float[] modes = new float[10];
    private static final float[] colorsA = new float[30];
    private static final float[] colorsB = new float[30];
    private static final float[] progresses = new float[10];
    private static final float[] starDensities = new float[10];

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
    public static void onRenderLevelStage(RenderLevelStageEvent event)
    {
        if(event.getStage() != RenderLevelStageEvent.Stage.AFTER_LEVEL) return;

        Minecraft mc = Minecraft.getInstance();
        if(mc.player == null || mc.level == null)
        {
            currentFrameNebulas.clear();
            calculatedCount = 0;
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
        validNebulas.sort(Comparator.comparingDouble(data -> camPos.distanceToSqr(Vec3.atCenterOf(data.pos))));

        Arrays.fill(centersX, -1f);
        Arrays.fill(centersY, -1f);
        calculatedCount = 0;

        for(NebulaData data : validNebulas)
        {
            if(calculatedCount >= 10) break;

            BlockPos target = data.pos;
            Vec3 targetCenter = Vec3.atCenterOf(target);
            float distance = (float) Math.sqrt(camPos.distanceToSqr(targetCenter));

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
                centersX[calculatedCount] = (ndcX + 1.0f) * 0.5f;
                centersY[calculatedCount] = (ndcY + 1.0f) * 0.5f;

                float edgeNdcY = edgePos.y / edgePos.w;
                float edgeScreenY = (edgeNdcY + 1.0f) * 0.5f;

                radii[calculatedCount] = Math.min(Math.abs(edgeScreenY - centersY[calculatedCount]), 1.5f);

                if(centerPos.w < 1.0f)
                {
                    float proximityFade = (centerPos.w - 0.2f) / 0.8f;
                    finalIntensity *= Math.max(0.0f, proximityFade);
                }

                intensities[calculatedCount] = finalIntensity;
                modes[calculatedCount] = (float) data.mode;
                progresses[calculatedCount] = data.progress;
                starDensities[calculatedCount] = data.starDensity;

                colorsA[calculatedCount * 3] = data.colorA[0];
                colorsA[calculatedCount * 3 + 1] = data.colorA[1];
                colorsA[calculatedCount * 3 + 2] = data.colorA[2];

                colorsB[calculatedCount * 3] = data.colorB[0];
                colorsB[calculatedCount * 3 + 1] = data.colorB[1];
                colorsB[calculatedCount * 3 + 2] = data.colorB[2];

                calculatedCount++;
            }
        }

        currentFrameNebulas.clear();

        if(calculatedCount > 0)
        {
            renderPostChain(mc, event.getPartialTick().getGameTimeDeltaTicks());
        }
    }

    private static void renderPostChain(Minecraft mc, float deltaTicks)
    {
        int currentWidth = mc.getWindow().getWidth();
        int currentHeight = mc.getWindow().getHeight();

        if(nebulaPostChain == null)
        {
            try
            {
                nebulaPostChain = new PostChain(mc.getTextureManager(), mc.getResourceManager(), mc.getMainRenderTarget(), NEBULA_SHADER);
                nebulaPostChain.resize(currentWidth, currentHeight);
                lastWidth = currentWidth;
                lastHeight = currentHeight;
            }
            catch(Exception e)
            {
                System.err.println("[CatalystCore] Error loading PostChain: " + e.getMessage());
                calculatedCount = 0;
                return;
            }
        }
        else if(lastWidth != currentWidth || lastHeight != currentHeight)
        {
            nebulaPostChain.resize(currentWidth, currentHeight);
            lastWidth = currentWidth;
            lastHeight = currentHeight;
        }

        if(passesField != null)
        {
            float timeInSeconds = (System.currentTimeMillis() - START_TIME) / 1000.0f;
            try
            {
                @SuppressWarnings("unchecked")
                List<PostPass> passes = (List<PostPass>) passesField.get(nebulaPostChain);
                for(PostPass pass : passes)
                {
                    pass.getEffect().safeGetUniform("NebulaCount").set(calculatedCount);
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

                nebulaPostChain.process(deltaTicks);

            }
            catch(IllegalAccessException e)
            {
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

        calculatedCount = 0;
    }
}