package net.radzratz.catalystcore.client.visuals.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.radzratz.catalystcore.client.entities.tilmat_table.CatalystTilmatEntity;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

public class CatalystTilmatRender extends EntityRenderer<CatalystTilmatEntity>
{
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath("catalystcore", "textures/entity/circle_alta.png");

    public CatalystTilmatRender(EntityRendererProvider.Context ctx)
    {
        super(ctx);
    }

    @Override
    public void render(CatalystTilmatEntity entity,
                       float entityYaw,
                       float partialTicks,
                       PoseStack poseStack,
                       MultiBufferSource buffer,
                       int light)
    {

        poseStack.pushPose();

        float angle = (entity.tickCount + partialTicks) * 2f;
        poseStack.translate(0, 0.50, 0);
        poseStack.mulPose(Axis.YP.rotationDegrees(angle));
        poseStack.scale(5f, 1.0f, 5f);

        VertexConsumer consumer = buffer.getBuffer(RenderType.entityTranslucent(TEXTURE));
        Matrix4f matrix = poseStack.last().pose();

        float a = 0.75f;
        consumer.addVertex(matrix, -0.5f, 0, -0.5f)
                .setColor(1f, 1f, 1f, a)
                .setUv(0, 0)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setUv2(light, 1)
                .setNormal(0, 1, 0);

        consumer.addVertex(matrix, -0.5f, 0, 0.5f)
                .setColor(1f, 1f, 1f, a)
                .setUv(0, 1)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setUv2(light, 1)
                .setNormal(0, 1, 0);

        consumer.addVertex(matrix, 0.5f, 0, 0.5f)
                .setColor(1f, 1f, 1f, a).setUv(1, 1)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setUv2(light, 1)
                .setNormal(0, 1, 0);

        consumer.addVertex(matrix, 0.5f, 0, -0.5f)
                .setColor(1f, 1f, 1f, a)
                .setUv(1, 0)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setUv2(light, 1)
                .setNormal(0, 1, 0);

        poseStack.popPose();

        super.render(entity, entityYaw, partialTicks, poseStack, buffer, light);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull CatalystTilmatEntity entity)
    {
        return TEXTURE;
    }
}
