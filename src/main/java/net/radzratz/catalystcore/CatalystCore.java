package net.radzratz.catalystcore;

import net.createmod.ponder.foundation.PonderIndex;
import net.neoforged.fml.ModList;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLEnvironment;
import net.radzratz.catalystcore.common.registries.CTCEBlockRegistry;
import net.radzratz.catalystcore.common.registries.CTCEBlockEntityRegistry;
import net.radzratz.catalystcore.common.compat.curios.curio.curios.CTCECurioItems;
import net.radzratz.catalystcore.common.registries.CTCEParticleRegistry;
import net.radzratz.catalystcore.common.registries.CTCEEntityRegistry;
import net.radzratz.catalystcore.common.registries.CTCEItemRegistry;
import net.radzratz.catalystcore.common.compat.ponder.CTCEPonderPlugin;
import net.radzratz.catalystcore.common.registries.CTCERecipeTypeRegistry;
import net.radzratz.catalystcore.common.registries.CTCESoundRegistry;
import net.radzratz.catalystcore.common.registries.CTCEToolsRegistry;
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

        CTCESoundRegistry.rgtr(bus);
        CTCERecipeTypeRegistry.rgtr(bus);
        CTCEItemRegistry.rgtr(bus);
        CTCEToolsRegistry.rgtr(bus);
        CTCEBlockRegistry.rgtr(bus);
        CTCEBlockEntityRegistry.register(bus);
        CTCEEntityRegistry.rgtr(bus);
        CTCEParticleRegistry.rgtr(bus);
        CTCECreativeTabs.rgtr(bus);

        if(ModList.get().isLoaded("curios")) {
            bus.addListener(CTCECurioItems::registerCatalystCurioCapabilities);
        }

        if(FMLEnvironment.dist.isClient()) {
            PonderIndex.addPlugin(new CTCEPonderPlugin());
        }
    }
}
