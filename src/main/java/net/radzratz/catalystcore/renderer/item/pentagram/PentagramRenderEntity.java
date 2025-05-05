package net.radzratz.catalystcore.renderer.item.pentagram;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.radzratz.catalystcore.blocks.entity.pentagram.PentagramEntity;
import org.jetbrains.annotations.NotNull;

public class PentagramRenderEntity extends EntityRenderer<PentagramEntity>
{
    private static final ResourceLocation PENTAGRAM_TEXTURE = ResourceLocation.fromNamespaceAndPath("catalystcore",
            "textures/entity/pentagram.png");

    public PentagramRenderEntity(EntityRendererProvider.Context context)
    {
        super(context);
    }

    @Override
    public void render(PentagramEntity entity,
                       float entityYaw,
                       float partialTicks,
                       PoseStack poseStack,
                       MultiBufferSource buffer,
                       int packedLight)
    {
        poseStack.pushPose();

        float scale = entity.getVisualScale(partialTicks);
        float alpha = entity.getAlpha(partialTicks);
        float size = 3.0F * scale;
        float rotation = (entity.tickCount + partialTicks) * 2.0F;

        poseStack.translate(0, 0.01D, 0);
        poseStack.mulPose(Axis.YP.rotationDegrees(rotation));

        var pose = poseStack.last().pose();
        @SuppressWarnings("unused")
        var normal = poseStack.last().normal();

        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityCutoutNoCull(PENTAGRAM_TEXTURE));

        float halfSize = size / 2;

        vertexConsumer.addVertex(pose, -halfSize, 0, -halfSize)
                .setColor(255, 255, 255, (int)(alpha * 255))
                .setUv(0, 0)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(packedLight)
                .setNormal(0, 1, 0);

        vertexConsumer.addVertex(pose, halfSize, 0, -halfSize)
                .setColor(255, 255, 255, (int)(alpha * 255))
                .setUv(1, 0)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(packedLight)
                .setNormal(0, 1, 0);

        vertexConsumer.addVertex(pose, halfSize, 0, halfSize)
                .setColor(255, 255, 255, (int)(alpha * 255))
                .setUv(1, 1)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(packedLight)
                .setNormal(0, 1, 0);

        vertexConsumer.addVertex(pose, -halfSize, 0, halfSize)
                .setColor(255, 255, 255, (int)(alpha * 255))
                .setUv(0, 1)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(packedLight)
                .setNormal(0, 1, 0);

        poseStack.popPose();

        PentagramRenderTop.render(entity, partialTicks, poseStack, buffer, packedLight);

        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull PentagramEntity entity)
    {
        return PENTAGRAM_TEXTURE;
    }
}
