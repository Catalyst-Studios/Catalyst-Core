package net.radzratz.catalystcore.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.radzratz.catalystcore.CatalystCore;
import net.radzratz.catalystcore.items.CatalystItems;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosTags;

import java.util.concurrent.CompletableFuture;

public class CatalystTagProvider extends ItemTagsProvider
{
    public CatalystTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                               CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(output, lookupProvider, blockTags, CatalystCore.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider)
    {
        tag(CuriosTags.createItemTag("catalyst")).add(CatalystItems.FULL_CATALYST.get());
    }
}