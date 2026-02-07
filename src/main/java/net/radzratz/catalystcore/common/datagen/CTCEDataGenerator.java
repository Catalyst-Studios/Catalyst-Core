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
import net.radzratz.catalystcore.CatalystCore;
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

@EventBusSubscriber(modid = CatalystCore.id)
public class CTCEDataGenerator {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent evt) {
        DataGenerator gntr = evt.getGenerator();
        PackOutput pOutput = gntr.getPackOutput();
        ExistingFileHelper eFileHelper = evt.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lProvider = evt.getLookupProvider();

        CTCEDataProvider prv = new CTCEDataProvider(pOutput);

        /// Loot Tables
        prv.addSubProvider(evt.includeServer(), new LootTableProvider(pOutput, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(CTCEBlockLootTable::new, LootContextParamSets.BLOCK)), lProvider));

        /// Tags
        BlockTagsProvider blockTagsProvider = new CTCEBlockTagProvider(pOutput, lProvider, eFileHelper);
        prv.addSubProvider(evt.includeServer(), blockTagsProvider);
        prv.addSubProvider(evt.includeServer(), new CTCEItemTagProvider(pOutput, lProvider, blockTagsProvider.contentsGetter(), eFileHelper));

        /// Models
        prv.addSubProvider(evt.includeClient(), new CTCEModelProvider(pOutput, eFileHelper));
        prv.addSubProvider(evt.includeClient(), new CTCEBlockStateProvider(pOutput, eFileHelper));

        /// Curios
        prv.addSubProvider(evt.includeServer(), new CTCECuriosProvider(pOutput, eFileHelper, lProvider));

        /// Modopedia
        prv.addSubProvider(evt.includeServer(), new MCatalystCodexProvider(lProvider, pOutput));
        prv.addSubProvider(evt.includeServer(), new MCodexEntriesProvider(lProvider, pOutput));
        prv.addSubProvider(evt.includeServer(), new MCodexTemplateProvider(lProvider, pOutput));
        prv.addSubProvider(evt.includeServer(), new MCodexTextureProvider(lProvider, pOutput));

        gntr.addProvider(true, prv);
    }
}
