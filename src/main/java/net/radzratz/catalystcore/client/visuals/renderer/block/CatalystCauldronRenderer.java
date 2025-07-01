package net.radzratz.catalystcore.client.visuals.renderer.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.FluidStack;
import net.radzratz.catalystcore.client.blocks.entity.cauldron.CatalystCauldronBlockEntity;
import org.jetbrains.annotations.NotNull;

public class CatalystCauldronRenderer implements BlockEntityRenderer<CatalystCauldronBlockEntity>
{
    public CatalystCauldronRenderer(BlockEntityRendererProvider.Context context)
    {
    }

    @Override
    public void render(CatalystCauldronBlockEntity entity,
                       float partialTick,
                       @NotNull PoseStack poseStack,
                       @NotNull MultiBufferSource buffer,
                       int packedLight,
                       int packedOverlay)
    {
        FluidStack fluidStack = entity.getFluidTank().getFluid();
        if(fluidStack.isEmpty() || fluidStack.getFluid() == Fluids.EMPTY)
            return;

        Level level = entity.getLevel();
        if(level == null)
            return;

        BlockPos pos = entity.getBlockPos();

        FluidState fluidState = fluidStack.getFluid().defaultFluidState();
        IClientFluidTypeExtensions fluidTypeExtensions = IClientFluidTypeExtensions.of(fluidStack.getFluid());
        ResourceLocation stillTexture = fluidTypeExtensions.getStillTexture(fluidStack);

        TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(stillTexture);
        int color = fluidTypeExtensions.getTintColor(fluidState, level, pos);

        float fillRatio = (float) fluidStack.getAmount() / entity.getFluidTank().getCapacity();
        float height = 0.02f + fillRatio * 0.6f;

        VertexConsumer builder = buffer.getBuffer(ItemBlockRenderTypes.getRenderLayer(fluidState));

        poseStack.pushPose();

        // Superficie de arriba
        drawQuad(builder, poseStack,
                3f/16f,
                height,
                3f/16f,
                13f/16f,
                height,
                13f/16f,
                sprite.getU0(), sprite.getV0(), sprite.getU1(), sprite.getV1(), packedLight, color);

        poseStack.popPose();
    }

    private static void drawVertex(VertexConsumer builder,
                                   PoseStack poseStack,
                                   float x,
                                   float y,
                                   float z,
                                   float u,
                                   float v,
                                   int packedLight,
                                   int color)
    {
        builder.addVertex(poseStack.last().pose(), x, y, z)
                .setColor(color)
                .setUv(u, v)
                .setLight(packedLight)
                .setNormal(0, 1, 0); // arriba
    }

    private static void drawQuad(VertexConsumer builder, PoseStack poseStack,
                                 float x0, float y0, float z0,
                                 float x1, float y1, float z1,
                                 float u0, float v0, float u1, float v1,
                                 int packedLight, int color) {
        drawVertex(builder, poseStack, x0, y0, z0, u0, v0, packedLight, color);
        drawVertex(builder, poseStack, x0, y1, z1, u0, v1, packedLight, color);
        drawVertex(builder, poseStack, x1, y1, z1, u1, v1, packedLight, color);
        drawVertex(builder, poseStack, x1, y0, z0, u1, v0, packedLight, color);
    }
}
