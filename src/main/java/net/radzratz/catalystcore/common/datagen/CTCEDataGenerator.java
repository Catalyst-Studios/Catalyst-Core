package net.radzratz.catalystcore.common.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.radzratz.catalystcore.common.compat.curios.curio.datagen.CTCECuriosProvider;
import net.radzratz.catalystcore.common.compat.modopedia.datagen.MCatalystCodexProvider;
import net.radzratz.catalystcore.common.compat.modopedia.datagen.MCodexEntriesProvider;
import net.radzratz.catalystcore.common.compat.modopedia.datagen.MCodexTemplateProvider;
import net.radzratz.catalystcore.common.compat.modopedia.datagen.MCodexTextureProvider;
import net.radzratz.catalystcore.common.datagen.loot_tables.CTCEBlockLootTable;
import net.radzratz.catalystcore.common.datagen.models.CTCEBlockStateProvider;
import net.radzratz.catalystcore.common.datagen.models.CTCEModelProvider;
import net.radzratz.catalystcore.common.datagen.tags.CTCEBlockTagProvider;
import net.radzratz.catalystcore.common.datagen.tags.CTCEItemTagProvider;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static net.radzratz.catalystcore.CatalystCore.MOD_ID;

@EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD)
@SuppressWarnings("unused")
public class CTCEDataGenerator
{
    @SuppressWarnings("unused")
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event)
    {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        CTCEDataProvider provider = new CTCEDataProvider(packOutput);

        /// Loot Tables
        provider.addSubProvider(event.includeServer(), new LootTableProvider(packOutput, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(CTCEBlockLootTable::new, LootContextParamSets.BLOCK)), lookupProvider));

        /// Tags
        BlockTagsProvider blockTagsProvider = new CTCEBlockTagProvider(packOutput, lookupProvider, existingFileHelper);
        provider.addSubProvider(event.includeServer(), blockTagsProvider);
        provider.addSubProvider(event.includeServer(), new CTCEItemTagProvider(packOutput, lookupProvider, blockTagsProvider.contentsGetter(), existingFileHelper));

        /// Models
        provider.addSubProvider(event.includeClient(), new CTCEModelProvider(packOutput, existingFileHelper));
        provider.addSubProvider(event.includeClient(), new CTCEBlockStateProvider(packOutput, existingFileHelper));

        /// Curios
        provider.addSubProvider(event.includeServer(), new CTCECuriosProvider(packOutput, existingFileHelper, lookupProvider));

        /// Modopedia
        provider.addSubProvider(event.includeServer(), new MCatalystCodexProvider(lookupProvider, packOutput));
        provider.addSubProvider(event.includeServer(), new MCodexEntriesProvider(lookupProvider, packOutput));
        provider.addSubProvider(event.includeServer(), new MCodexTemplateProvider(lookupProvider, packOutput));
        provider.addSubProvider(event.includeServer(), new MCodexTextureProvider(lookupProvider, packOutput));

        generator.addProvider(true, provider);
    }
}
