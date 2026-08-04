package net.radzratz.catalystcore.client.blocks.entity.warp;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

@SuppressWarnings("null")
public class WarpBlockEntityRenderer implements BlockEntityRenderer<WarpBlockEntity>
{

    public WarpBlockEntityRenderer(BlockEntityRendererProvider.Context context)
    {
    }

    @Override
    public void render(WarpBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay)
    {
        if(!blockEntity.active) return;
        
        float width = blockEntity.getWidth();
        float height = blockEntity.getHeight();
        
        if(width <= 0) width = 1.0f;
        if(height <= 0) height = 1.0f;

        Direction.Axis axis = Direction.Axis.Z;
        
        if(blockEntity.getBlockState().hasProperty(BlockStateProperties.AXIS))
        {
            axis = blockEntity.getBlockState().getValue(BlockStateProperties.AXIS);
        }

        WarpLevelRenderer.enqueueWarp(blockEntity.getBlockPos(), width, height, axis);
    }

    @Override
    public boolean shouldRenderOffScreen(WarpBlockEntity blockEntity)
    {
        return true;
    }
}