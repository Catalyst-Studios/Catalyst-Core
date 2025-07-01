package net.radzratz.catalystcore.common.compat.modopedia.datagen;

import com.google.gson.JsonElement;
import net.favouriteless.modopedia.api.datagen.providers.TemplateProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.radzratz.catalystcore.CatalystCore;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class MCodexTemplateProvider extends TemplateProvider
{
    public MCodexTemplateProvider(CompletableFuture<HolderLookup.Provider> registries, PackOutput output)
    {
        super(CatalystCore.MOD_ID, registries, output);
    }

    @Override
    public void build(BiConsumer<String, JsonElement> output)
    {
        /// Gonna leave this empty for the mean time 'till I get everything done correctly
    }
}
