package net.radzratz.catalystcore.datagen;

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
import net.radzratz.catalystcore.datagen.curio.CatalystCuriosProvider;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static net.radzratz.catalystcore.CatalystCore.MOD_ID;

@EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD)
@SuppressWarnings("unused")
public class CatalystDataProvider
{
    @SuppressWarnings("unused")
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event)
    {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new LootTableProvider(packOutput, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(CatalystBlockLootTable::new, LootContextParamSets.BLOCK)), lookupProvider));

        event.getGenerator().addProvider(event.includeServer(), new CatalystCuriosProvider(MOD_ID,packOutput, existingFileHelper, lookupProvider));

        BlockTagsProvider blockTagsProvider = new CatalystBlockTagProvider(packOutput, lookupProvider, existingFileHelper);
        generator.addProvider(event.includeServer(), blockTagsProvider);
        event.getGenerator().addProvider(event.includeServer(), new CatalystItemTagProvider(packOutput, lookupProvider, blockTagsProvider.contentsGetter(), existingFileHelper));

        generator.addProvider(event.includeClient(), new CatalystModelProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new CatalystBlockStateProvider(packOutput, existingFileHelper));
    }
}
