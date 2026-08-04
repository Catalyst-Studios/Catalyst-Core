package net.radzratz.catalystcore.client.blocks.entity.warp;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import net.radzratz.catalystcore.common.util.config.CTCEConfig;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("null")
@EventBusSubscriber(modid = "catalystcore", value = Dist.CLIENT)
public class WarpLevelRenderer
{
    public record QueuedWarp(BlockPos pos, float width, float height, Direction.Axis axis){}

    private static final List<QueuedWarp> WARPS_TO_RENDER = new ArrayList<>();

    public static void enqueueWarp(BlockPos pos, float width, float height, Direction.Axis axis)
    {
        WARPS_TO_RENDER.add(new QueuedWarp(pos, width, height, axis));
    }

    @SubscribeEvent
    public static void onRenderStage(RenderLevelStageEvent event)
    {
        if(event.getStage() != RenderLevelStageEvent.Stage.AFTER_LEVEL) return;
        if(WARPS_TO_RENDER.isEmpty()) return;
        if(!CTCEConfig.CONFIG.warp.enableWarpShader.get()) return;

        PoseStack poseStack = event.getPoseStack();
        Camera camera = event.getCamera();
        Vec3 camPos = camera.getPosition();

        RenderSystem.enableDepthTest();
        RenderSystem.depthFunc(GL11.GL_LEQUAL);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableCull();
        RenderSystem.depthMask(false); 

        if(WarpRenderState.warpPortalShader != null)
        {
            if(WarpRenderState.warpPortalShader.getUniform("WarpTime") != null)
            {
                float time = (System.currentTimeMillis() - WarpRenderState.START_TIME) / 1000.0f;
                WarpRenderState.warpPortalShader.safeGetUniform("WarpTime").set(time);
            }

            RenderSystem.setShader(() -> WarpRenderState.warpPortalShader);
        }
        else
        {
            RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        }

        RenderSystem.getModelViewStack().pushMatrix();
        RenderSystem.getModelViewStack().identity();
        RenderSystem.applyModelViewMatrix();

        Tesselator tessellator = Tesselator.getInstance();
        BufferBuilder bufferBuilder = tessellator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);

        for(QueuedWarp warp : WARPS_TO_RENDER)
        {
            poseStack.pushPose();
            poseStack.mulPose(Axis.XP.rotationDegrees(camera.getXRot()));
            poseStack.mulPose(Axis.YP.rotationDegrees(camera.getYRot() + 180.0F));

            double x = warp.pos().getX() - camPos.x + 0.5;
            double y = warp.pos().getY() - camPos.y + 0.5;
            double z = warp.pos().getZ() - camPos.z + 0.5;
            poseStack.translate(x, y, z);

            if(warp.axis() == Direction.Axis.X)
            {
                poseStack.mulPose(Axis.YP.rotationDegrees(90));
            }
            else if(warp.axis() == Direction.Axis.Y)
            {
                poseStack.mulPose(Axis.XP.rotationDegrees(90));
            }

            float halfW = warp.width() / 2.0f;
            float halfH = warp.height() / 2.0f;

            Matrix4f poseFront = poseStack.last().pose();
            drawQuad(bufferBuilder, poseFront, halfW, halfH, 0.01f);

            poseStack.mulPose(Axis.YP.rotationDegrees(180));
            Matrix4f poseBack = poseStack.last().pose();
            drawQuad(bufferBuilder, poseBack, halfW, halfH, 0.01f);

            poseStack.popPose();
        }

        BufferUploader.drawWithShader(bufferBuilder.buildOrThrow());

        RenderSystem.getModelViewStack().popMatrix();
        RenderSystem.applyModelViewMatrix();

        RenderSystem.depthMask(true);
        RenderSystem.enableCull();
        WARPS_TO_RENDER.clear();
    }

    private static void drawQuad(VertexConsumer vertexConsumer, Matrix4f pose, float w, float h, float z)
    {
        vertexConsumer.addVertex(pose, -w, -h, z).setColor(255, 255, 255, 255).setUv(0, 0);
        vertexConsumer.addVertex(pose, w, -h, z).setColor(255, 255, 255, 255).setUv(1, 0);
        vertexConsumer.addVertex(pose, w, h, z).setColor(255, 255, 255, 255).setUv(1, 1);
        vertexConsumer.addVertex(pose, -w, h, z).setColor(255, 255, 255, 255).setUv(0, 1);
    }
}