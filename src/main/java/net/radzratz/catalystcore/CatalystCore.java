package net.radzratz.catalystcore;

import net.createmod.ponder.foundation.PonderIndex;
import net.neoforged.fml.ModList;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLEnvironment;
import net.radzratz.catalystcore.client.blocks.CTCEBlocks;
import net.radzratz.catalystcore.client.blocks.entity.CTCEBlockEntities;
import net.radzratz.catalystcore.common.compat.curios.curio.curios.CTCECurioItems;
import net.radzratz.catalystcore.client.visuals.particle.CTCEParticles;
import net.radzratz.catalystcore.client.entities.CTCEEntities;
import net.radzratz.catalystcore.client.items.CTCEItems;
import net.radzratz.catalystcore.common.compat.ponder.CTCEPonderPlugin;
import net.radzratz.catalystcore.common.recipes.CTCERecipeTypes;
import net.radzratz.catalystcore.client.sound.CTCESounds;
import net.radzratz.catalystcore.common.util.CTCECreativeTabs;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.radzratz.catalystcore.common.util.config.CTCEConfig;

@Mod(CatalystCore.id)
public class CatalystCore {
    public static final String id = "catalystcore";

    public CatalystCore(IEventBus bus, ModContainer mCont) {
        mCont.registerConfig(ModConfig.Type.COMMON, CTCEConfig.CONFIG_SPEC, CatalystCore.id + "/settings.toml");

        CTCESounds.rgtr(bus);
        CTCERecipeTypes.rgtr(bus);
        CTCEItems.rgtr(bus);
        CTCEBlocks.rgtr(bus);
        CTCEBlockEntities.register(bus);
        CTCEEntities.rgtr(bus);
        CTCECreativeTabs.rgtr(bus);
        CTCEParticles.rgtr(bus);

        if(ModList.get().isLoaded("curios")) {
            bus.addListener(CTCECurioItems::registerCatalystCurioCapabilities);
        }

        if(FMLEnvironment.dist.isClient()) {
            PonderIndex.addPlugin(new CTCEPonderPlugin());
        }
    }
}
