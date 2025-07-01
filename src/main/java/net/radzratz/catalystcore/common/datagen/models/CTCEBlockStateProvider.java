package net.radzratz.catalystcore.common.datagen.models;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.radzratz.catalystcore.CatalystCore;
import net.radzratz.catalystcore.client.blocks.CTCEBlocks;

public class CTCEBlockStateProvider extends BlockStateProvider
{
    public CTCEBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper)
    {
        super(output, CatalystCore.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels()
    {
        blockWithItem(CTCEBlocks.REINFORCED_GLASS);
        blockWithItem(CTCEBlocks.TILMAT_TABLE);
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock)
    {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
