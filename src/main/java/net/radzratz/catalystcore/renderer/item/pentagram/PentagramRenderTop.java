package net.radzratz.catalystcore.renderer.item.pentagram;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.radzratz.catalystcore.blocks.entity.pentagram.PentagramEntity;
import net.radzratz.catalystcore.util.config.CatalystConfig;
import org.joml.Matrix4f;

public class PentagramRenderTop
{
    private static final ResourceLocation STATIC_CIRCLE_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("catalystcore", "textures/entity/eye1.png");
    private static final ResourceLocation INNER_CIRCLE_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("catalystcore", "textures/entity/pentagram_smol_circle.png");
    private static final ResourceLocation OUTER_CIRCLE_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("catalystcore", "textures/entity/pentagram_smol_circle.png");

    private static float currentYaw = 0;
    @SuppressWarnings("unused")
    private static float prevYaw = 0;


    public static void render(PentagramEntity entity,
                              float partialTicks,
                              PoseStack poseStack,
                              MultiBufferSource buffer,
                              int packedLight)
    {
        if(!CatalystConfig.CONFIG.pentagram.enablePentagramSpecialRenderers.get())
        {
            return;
        }

        poseStack.pushPose();
        poseStack.translate(0, 1, 0);

        LocalPlayer clientPlayer = Minecraft.getInstance().player;
        if (clientPlayer == null)
        {
            poseStack.popPose();
            return;
        }

        float rotation = entity.getCircleRotation() + partialTicks * 0.5f;
        float innerScale = entity.getInnerCircleScale();
        float outerScale = entity.getOuterCircleScale();
        float staticScale = entity.getStaticCircleScale();
        float innerAngle = entity.getInnerCircleAngle();
        float outerAngle = entity.getOuterCircleAngle();

        renderEyeFollowingPlayer(entity, clientPlayer, partialTicks,
                poseStack, buffer, staticScale, packedLight);

        renderDysonCircle(poseStack, buffer.getBuffer(RenderType.entityTranslucent(INNER_CIRCLE_TEXTURE)),
                innerScale, rotation * 0.8f, innerAngle, packedLight, 0.95f);

        renderDysonCircle(poseStack, buffer.getBuffer(RenderType.entityTranslucent(OUTER_CIRCLE_TEXTURE)),
                outerScale, -rotation, outerAngle, packedLight, 0.85f);

        poseStack.popPose();
    }

    private static void renderEyeFollowingPlayer(PentagramEntity entity,
                                                 LocalPlayer player,
                                                 float partialTicks,
                                                 PoseStack poseStack,
                                                 MultiBufferSource buffer,
                                                 float scale,
                                                 int packedLight)
    {
        poseStack.pushPose();

        poseStack.translate(0, -0.1, 0);

        Vec3 eyePos = player.getEyePosition(partialTicks);
        Vec3 entityPos = entity.position().add(0, 1, 0);
        Vec3 lookVec = eyePos.subtract(entityPos).normalize();

        float yaw = (float)Math.toDegrees(Math.atan2(lookVec.x, lookVec.z));
        float pitch = (float)Math.toDegrees(Math.asin(lookVec.y));

        prevYaw = currentYaw;
        currentYaw += (yaw - currentYaw) * 0.1f;

        poseStack.mulPose(Axis.YP.rotationDegrees(currentYaw));

        poseStack.mulPose(Axis.XP.rotationDegrees(-pitch * 0.15f));

        poseStack.scale(1.0f, 1.2f, 1.0f);

        renderEyeQuad(poseStack,
                buffer.getBuffer(RenderType.entityTranslucent(STATIC_CIRCLE_TEXTURE)),
                scale, packedLight, 1.0f, -0.05f);

        poseStack.popPose();
    }

    private static void renderEyeQuad(PoseStack poseStack,
                                      VertexConsumer vertexConsumer,
                                      float scale,
                                      int packedLight,
                                      float alpha,
                                      float yOffset)
    {
        float halfSize = scale / 2;
        Matrix4f pose = poseStack.last().pose();

        poseStack.mulPose(Axis.XP.rotationDegrees(90));

        vertexConsumer.addVertex(pose, -halfSize, yOffset, -halfSize)
                .setColor(1.0f, 1.0f, 1.0f, alpha)
                .setUv(0, 0)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(packedLight)
                .setNormal(0, 1, 0);

        vertexConsumer.addVertex(pose, halfSize, yOffset, -halfSize)
                .setColor(1.0f, 1.0f, 1.0f, alpha)
                .setUv(1, 0)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(packedLight)
                .setNormal(0, 1, 0);

        vertexConsumer.addVertex(pose, halfSize, yOffset, halfSize)
                .setColor(1.0f, 1.0f, 1.0f, alpha)
                .setUv(1, 1)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(packedLight)
                .setNormal(0, 1, 0);

        vertexConsumer.addVertex(pose, -halfSize, yOffset, halfSize)
                .setColor(1.0f, 1.0f, 1.0f, alpha)
                .setUv(0, 1)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(packedLight)
                .setNormal(0, 1, 0);
    }

    @SuppressWarnings("unused")
    private static void renderVerticalCircle(PoseStack poseStack,
                                             VertexConsumer vertexConsumer,
                                             float scale,
                                             int packedLight,
                                             float alpha)
    {
        poseStack.pushPose();
        poseStack.mulPose(Axis.ZP.rotationDegrees(90));
        renderQuad(poseStack, vertexConsumer, scale, 0, packedLight, alpha, 0.05f);
        poseStack.popPose();
    }

    private static void renderDysonCircle(PoseStack poseStack,
                                          VertexConsumer vertexConsumer,
                                          float scale,
                                          float rotation,
                                          float angle,
                                          int packedLight,
                                          float alpha)
    {
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(rotation));
        poseStack.mulPose(Axis.XP.rotationDegrees(angle));
        renderQuad(poseStack, vertexConsumer, scale, 0, packedLight, alpha, 0.03f);
        poseStack.popPose();
    }

    private static void renderQuad(PoseStack poseStack,
                                   VertexConsumer vertexConsumer,
                                   float scale,
                                   float rotation,
                                   int packedLight,
                                   float alpha,
                                   float yOffset)
    {
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(rotation));

        float halfSize = scale / 2;

        Matrix4f pose = poseStack.last().pose();

        vertexConsumer.addVertex(pose, -halfSize, yOffset, -halfSize)
                .setColor(1.0f, 1.0f, 1.0f, alpha)
                .setUv(0, 0)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(packedLight)
                .setNormal(0, 1, 0);

        vertexConsumer.addVertex(pose, halfSize, yOffset, -halfSize)
                .setColor(1.0f, 1.0f, 1.0f, alpha)
                .setUv(1, 0)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(packedLight)
                .setNormal(0, 1, 0);

        vertexConsumer.addVertex(pose, halfSize, yOffset, halfSize)
                .setColor(1.0f, 1.0f, 1.0f, alpha)
                .setUv(1, 1)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(packedLight)
                .setNormal(0, 1, 0);

        vertexConsumer.addVertex(pose, -halfSize, yOffset, halfSize)
                .setColor(1.0f, 1.0f, 1.0f, alpha)
                .setUv(0, 1)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(packedLight)
                .setNormal(0, 1, 0);

        poseStack.popPose();
    }
}