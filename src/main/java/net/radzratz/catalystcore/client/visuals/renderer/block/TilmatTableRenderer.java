package net.radzratz.catalystcore.client.visuals.renderer.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.radzratz.catalystcore.client.blocks.entity.tilmat_table.TilmatTableBlockEntity;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

public class TilmatTableRenderer implements BlockEntityRenderer<TilmatTableBlockEntity>
{
    private static final ResourceLocation MAGIC_CIRCLE_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("catalystcore", "textures/entity/circle_alta.png");

    public TilmatTableRenderer(BlockEntityRendererProvider.Context context) {}

    @Override
    public void render(@NotNull TilmatTableBlockEntity be,
                       float partialTicks,
                       @NotNull PoseStack poseStack,
                       @NotNull MultiBufferSource buffer,
                       int packedLight,
                       int packedOverlay)
    {

        float age = (be.getRitualTimer() + partialTicks);

        renderCircle(poseStack, buffer, packedLight, be.getLevel(), be.getBlockPos(), MAGIC_CIRCLE_TEXTURE, 1.5f, 0.02f);

        renderCircle(poseStack, buffer, packedLight, be.getLevel(), be.getBlockPos(), MAGIC_CIRCLE_TEXTURE, 1.2f, 0.5f);

        renderCircle(poseStack, buffer, packedLight, be.getLevel(), be.getBlockPos(), MAGIC_CIRCLE_TEXTURE, 1.0f, 1.01f);
    }

    private void renderCircle(PoseStack poseStack,
                              MultiBufferSource buffer,
                              int light,
                              Level level,
                              BlockPos pos,
                              ResourceLocation texture,
                              float scale,
                              float heightOffset)
    {

        poseStack.pushPose();

        poseStack.translate(0.5, heightOffset, 0.5);
        poseStack.scale(scale, 1.0f, scale);
        poseStack.mulPose(Axis.YP.rotationDegrees((level.getGameTime() % 360) * 2));

        VertexConsumer consumer = buffer.getBuffer(RenderType.entityTranslucent(texture));

        Matrix4f matrix = poseStack.last().pose();

        float r = 1f, g = 1f, b = 1f, a = 0.75f;

        consumer.addVertex(matrix, -0.5f, 0, -0.5f)
                .setColor(r, g, b, a)
                .setUv(0, 0)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setUv2(light, 1)
                .setNormal(0, 1, 0);

        consumer.addVertex(matrix, -0.5f, 0, 0.5f)
                .setColor(r, g, b, a).setUv(0, 1)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setUv2(light, 1)
                .setNormal(0, 1, 0);

        consumer.addVertex(matrix, 0.5f, 0, 0.5f)
                .setColor(r, g, b, a).setUv(1, 1)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setUv2(light, 1)
                .setNormal(0, 1, 0);

        consumer.addVertex(matrix, 0.5f, 0, -0.5f)
                .setColor(r, g, b, a)
                .setUv(1, 0)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setUv2(light, 1)
                .setNormal(0, 1, 0);

        poseStack.popPose();
    }

    @Override
    public boolean shouldRenderOffScreen(@NotNull TilmatTableBlockEntity be)
    {
        return true;
    }
}
