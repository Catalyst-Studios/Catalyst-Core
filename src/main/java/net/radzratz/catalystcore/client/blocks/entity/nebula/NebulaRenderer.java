package net.radzratz.catalystcore.client.blocks.entity.nebula;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.radzratz.catalystcore.common.util.config.CTCEConfig;

@SuppressWarnings("null")
public class NebulaRenderer implements BlockEntityRenderer<NebulaBlockEntity>
{
    public NebulaRenderer(BlockEntityRendererProvider.Context context)
    {
    }

    @Override
    public void render(NebulaBlockEntity entity, float partialTick, PoseStack poseStack, 
                       MultiBufferSource bufferSource, int combinedLight, int combinedOverlay)
    {
        if(!entity.active) return;

        if(CTCEConfig.CONFIG.nebula.enableNebulaShader.get())
        {
            NebulaShaderController.registerNebula(
                entity.getBlockPos(), 
                entity.customRadius, 
                entity.customStrength,
                entity.mode,
                entity.colorA,
                entity.colorB,
                entity.progress,
                entity.starDensity
            );
        }
    }
}
