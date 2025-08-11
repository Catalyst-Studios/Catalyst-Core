package net.radzratz.catalystcore.common.datagen.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.radzratz.catalystcore.CatalystCore;
import net.radzratz.catalystcore.client.blocks.CTCEBlocks;
import net.radzratz.catalystcore.client.items.tags.CTCETags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class CTCEBlockTagProvider extends BlockTagsProvider
{
    public CTCEBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                                @Nullable ExistingFileHelper existingFileHelper)
    {
        super(output, lookupProvider, CatalystCore.id, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider)
    {
        tag(BlockTags.WITHER_IMMUNE).add(CTCEBlocks.REINFORCED_GLASS.get());
        tag(BlockTags.NEEDS_IRON_TOOL).add(CTCEBlocks.REINFORCED_GLASS.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(CTCEBlocks.REINFORCED_GLASS.get());
        tag(BlockTags.INCORRECT_FOR_STONE_TOOL).add(CTCEBlocks.REINFORCED_GLASS.get());
        tag(BlockTags.DRAGON_IMMUNE).add(CTCEBlocks.REINFORCED_GLASS.get());

        tag(BlockTags.INCORRECT_FOR_STONE_TOOL).add(CTCEBlocks.CAULDRON.get());
        tag(BlockTags.NEEDS_IRON_TOOL).add(CTCEBlocks.CAULDRON.get());
        tag(BlockTags.CAULDRONS).add(CTCEBlocks.CAULDRON.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(CTCEBlocks.CAULDRON.get());

        tag(CTCETags.Blocks.HEAT_SOURCES).add(Blocks.LAVA);
        tag(CTCETags.Blocks.HEAT_SOURCES).add(Blocks.CAMPFIRE);
        tag(CTCETags.Blocks.HEAT_SOURCES).add(Blocks.MAGMA_BLOCK);
    }
}
