package net.radzratz.catalystcore.common.compat.modopedia.datagen;

import net.favouriteless.modopedia.api.datagen.BookContentOutput;
import net.favouriteless.modopedia.api.datagen.providers.ContentSetProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.radzratz.catalystcore.CatalystCore;
import net.radzratz.catalystcore.common.compat.modopedia.registry.MCategoriesRegistry;
import net.radzratz.catalystcore.common.compat.modopedia.registry.MEntriesRegistry;

import java.util.concurrent.CompletableFuture;

public class MCodexEntriesProvider extends ContentSetProvider
{
    public MCodexEntriesProvider(CompletableFuture<HolderLookup.Provider> registries, PackOutput output)
    {
        super(CatalystCore.id, "catalyst_codex", "en_us", registries, output);
    }

    @Override
    public void buildCategories(HolderLookup.Provider registries, BookContentOutput output)
    {
        MCategoriesRegistry.registerCategories(registries, output);
    }

    @Override
    public void buildEntries(HolderLookup.Provider registries, BookContentOutput output)
    {
        MEntriesRegistry.registerMainEntries(registries, output);
        MEntriesRegistry.registerPhialEntries(registries, output);
    }
}
