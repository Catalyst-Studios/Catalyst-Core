package net.radzratz.catalystcore.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.radzratz.catalystcore.CatalystCore;
import net.radzratz.catalystcore.blocks.CatalystBlocks;

public class CatalystBlockStateProvider extends BlockStateProvider
{
    public CatalystBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper)
    {
        super(output, CatalystCore.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels()
    {
        /*blockWithItem(CatalystBlocks.MULTIBLOCK_CONTROLLER);*/
        blockWithItem(CatalystBlocks.REINFORCED_GLASS);
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock)
    {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
