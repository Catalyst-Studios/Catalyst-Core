package net.radzratz.catalystcore.common.compat.modopedia.datagen;

import com.google.gson.JsonElement;
import net.favouriteless.modopedia.api.book.Category;
import net.favouriteless.modopedia.api.datagen.providers.ContentSetProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.radzratz.catalystcore.CatalystCore;
import net.radzratz.catalystcore.common.compat.modopedia.registry.MCategoriesRegistry;
import net.radzratz.catalystcore.common.compat.modopedia.registry.MEntriesRegistry;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class MCodexEntriesProvider extends ContentSetProvider
{
    public MCodexEntriesProvider(CompletableFuture<HolderLookup.Provider> registries, PackOutput output)
    {
        super(CatalystCore.MOD_ID, "catalyst_codex", "en_us", registries, output);
    }

    @Override
    public void buildCategories(BiConsumer<String, Category> output)
    {
        MCategoriesRegistry.registerCategories(output);
    }

    @Override
    public void buildEntries(BiConsumer<String, JsonElement> output)
    {
        MEntriesRegistry.registerMainEntries(output);
        MEntriesRegistry.registerPhialEntries(output);
    }
}
