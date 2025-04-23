package net.radzratz.catalystcore.client;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.radzratz.catalystcore.blocks.CatalystBlocks;

public class ClientSetup
{
    @SuppressWarnings("deprecation")
    public static void init()
    {
        ItemBlockRenderTypes.setRenderLayer(CatalystBlocks.EYE_FLOWER.get(), RenderType.cutout());
    }
}
