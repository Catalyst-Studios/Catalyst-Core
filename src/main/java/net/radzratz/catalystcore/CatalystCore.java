package net.radzratz.catalystcore;

import net.neoforged.fml.ModList;
import net.neoforged.fml.config.ModConfig;
import net.radzratz.catalystcore.client.blocks.CTCEBlocks;
import net.radzratz.catalystcore.client.blocks.entity.CTCEBlockEntities;
import net.radzratz.catalystcore.common.compat.curios.curio.curios.CTCECurioItems;
import net.radzratz.catalystcore.client.visuals.particle.CTCEParticles;
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

    public CatalystCore(IEventBus bus, ModContainer modContainer)
    {
        modContainer.registerConfig(ModConfig.Type.COMMON, CTCEConfig.CONFIG_SPEC);

        CTCESounds.init(bus);
        CTCERecipeTypes.register(bus);
        CTCEItems.register(bus);
        CTCEBlocks.register(bus);
        CTCEBlockEntities.register(bus);
        CTCEEntities.register(bus);
        CTCECreativeTabs.register(bus);
        CTCEParticles.register(bus);

        if(ModList.get().isLoaded("curios")) { bus.addListener(CTCECurioItems::registerCatalystCurioCapabilities); }

        // PonderIndex.addPlugin(new CTCEPonderPlugin());
    }
}
