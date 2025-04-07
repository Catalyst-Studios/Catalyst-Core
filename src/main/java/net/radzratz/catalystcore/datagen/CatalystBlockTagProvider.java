package net.radzratz.catalystcore.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.radzratz.catalystcore.CatalystCore;
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

    }
}
