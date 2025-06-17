package net.radzratz.catalystcore.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.radzratz.catalystcore.CatalystCore;
import net.radzratz.catalystcore.blocks.CatalystBlocks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class CatalystBlockTagProvider extends BlockTagsProvider
{
    public CatalystBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                                    @Nullable ExistingFileHelper existingFileHelper)
    {
        super(output, lookupProvider, CatalystCore.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider)
    {
        tag(BlockTags.WITHER_IMMUNE).add(CatalystBlocks.REINFORCED_GLASS.get());
        tag(BlockTags.NEEDS_IRON_TOOL).add(CatalystBlocks.REINFORCED_GLASS.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(CatalystBlocks.REINFORCED_GLASS.get());
        tag(BlockTags.INCORRECT_FOR_STONE_TOOL).add(CatalystBlocks.REINFORCED_GLASS.get());
        tag(BlockTags.DRAGON_IMMUNE).add(CatalystBlocks.REINFORCED_GLASS.get());
    }
}
