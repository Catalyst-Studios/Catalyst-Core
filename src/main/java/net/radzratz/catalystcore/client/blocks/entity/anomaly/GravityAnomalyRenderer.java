package net.radzratz.catalystcore.client.blocks.entity.anomaly;


import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.radzratz.catalystcore.common.util.config.CTCEConfig;

public class GravityAnomalyRenderer implements BlockEntityRenderer<GravityAnomalyBlockEntity>
{
    public GravityAnomalyRenderer(BlockEntityRendererProvider.Context context)
    {
    }

    @Override
    public void render(GravityAnomalyBlockEntity entity, float partialTick, PoseStack poseStack, 
                       MultiBufferSource bufferSource, int combinedLight, int combinedOverlay)
    {
        if(!entity.active) return;

        if(CTCEConfig.CONFIG.anomaly.enableAnomalyShader.get())
        {
            AnomalyShaderController.registerAnomaly(
                entity.getBlockPos(), 
                entity.customRadius, 
                entity.customStrength
            );
        }
    }
}
