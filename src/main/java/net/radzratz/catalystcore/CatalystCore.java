package net.radzratz.catalystcore;

import net.createmod.ponder.foundation.PonderIndex;
import net.neoforged.fml.ModList;
import net.neoforged.fml.config.ModConfig;
import net.radzratz.catalystcore.blocks.CatalystBlocks;
import net.radzratz.catalystcore.blocks.entity.CatalystBlockEntities;
import net.radzratz.catalystcore.items.curio.CatalystCurioCompatibility;
import net.radzratz.catalystcore.particle.CatalystParticles;
import net.radzratz.catalystcore.ponder.CatalystPonderPlugin;
import net.radzratz.catalystcore.recipes.pentagram.debug.PentagramRecipeManager;
import net.radzratz.catalystcore.recipes.pentagram.debug.TransmutationRecipe;
import net.radzratz.catalystcore.entities.CatalystEntities;
import net.radzratz.catalystcore.items.CatalystItems;
import net.radzratz.catalystcore.recipes.CatalystRecipeTypes;
import net.radzratz.catalystcore.sound.CatalystSounds;
import net.radzratz.catalystcore.util.CatalystCreativeTab;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.radzratz.catalystcore.util.config.CatalystConfig;

@Mod(CatalystCore.MOD_ID)
public class CatalystCore
{
    public static final String MOD_ID = "catalystcore";

    public CatalystCore(IEventBus modEventBus, ModContainer modContainer)
    {
        modEventBus.addListener(this::commonSetup);

        modContainer.registerConfig(ModConfig.Type.COMMON, CatalystConfig.CONFIG_SPEC);

        CatalystSounds.init(modEventBus);
        CatalystRecipeTypes.register(modEventBus);
        CatalystItems.register(modEventBus);
        CatalystBlocks.register(modEventBus);
        CatalystBlockEntities.register(modEventBus);
        CatalystEntities.register(modEventBus);
        CatalystCreativeTab.register(modEventBus);
        CatalystParticles.register(modEventBus);

        if(ModList.get().isLoaded("curios"))
        {
            modEventBus.addListener(CatalystCurioCompatibility::registerCatalystCurioCapabilities);
        }

        PonderIndex.addPlugin(new CatalystPonderPlugin());
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() ->
        {
            ///Debug only
            PentagramRecipeManager.addRecipe(new TransmutationRecipe());
        });
    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }
}
