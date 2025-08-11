package net.radzratz.catalystcore;

import net.createmod.ponder.foundation.PonderIndex;
import net.neoforged.fml.ModList;
import net.neoforged.fml.config.ModConfig;
import net.radzratz.catalystcore.client.blocks.CTCEBlocks;
import net.radzratz.catalystcore.client.blocks.entity.CTCEBlockEntities;
import net.radzratz.catalystcore.common.compat.curios.curio.curios.CTCECurioItems;
import net.radzratz.catalystcore.client.visuals.particle.CTCEParticles;
import net.radzratz.catalystcore.common.compat.ponder.CTCEPonderPlugin;
import net.radzratz.catalystcore.client.entities.CTCEEntities;
import net.radzratz.catalystcore.client.items.CTCEItems;
import net.radzratz.catalystcore.common.recipes.CTCERecipeTypes;
import net.radzratz.catalystcore.client.sound.CTCESounds;
import net.radzratz.catalystcore.common.util.CTCECreativeTabs;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.radzratz.catalystcore.common.util.config.CTCEConfig;

@Mod(CatalystCore.id)
public class CatalystCore
{
    public static final String id = "catalystcore";

    public CatalystCore(IEventBus modEventBus, ModContainer modContainer)
    {
        modEventBus.addListener(this::commonSetup);

        modContainer.registerConfig(ModConfig.Type.COMMON, CTCEConfig.CONFIG_SPEC);

        CTCESounds.init(modEventBus);
        CTCERecipeTypes.register(modEventBus);
        CTCEItems.register(modEventBus);
        CTCEBlocks.register(modEventBus);
        CTCEBlockEntities.register(modEventBus);
        CTCEEntities.register(modEventBus);
        CTCECreativeTabs.register(modEventBus);
        CTCEParticles.register(modEventBus);

        if(ModList.get().isLoaded("curios"))
        {
            modEventBus.addListener(CTCECurioItems::registerCatalystCurioCapabilities);
        }

        PonderIndex.addPlugin(new CTCEPonderPlugin());
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() ->
        {
        });
    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }
}
