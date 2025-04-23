package net.radzratz.catalystcore;

import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.radzratz.catalystcore.blocks.CatalystBlocks;
import net.radzratz.catalystcore.blocks.entity.CatalystBlockEntities;
import net.radzratz.catalystcore.blocks.entity.renderer.CatalystPedestalItemRenderer;
import net.radzratz.catalystcore.client.ClientSetup;
import net.radzratz.catalystcore.items.CatalystItems;
import net.radzratz.catalystcore.items.curio.CatalystCurio;
import net.radzratz.catalystcore.util.CatalystCreativeTab;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import top.theillusivec4.curios.api.CuriosCapability;

@Mod(CatalystCore.MOD_ID)
public class CatalystCore
{

    public static final String MOD_ID = "catalystcore";

    public CatalystCore(IEventBus modEventBus, ModContainer modContainer)
    {
        modEventBus.addListener(this::registerCapabilities);
        modEventBus.addListener(this::commonSetup);

        CatalystItems.register(modEventBus);
        CatalystBlocks.register(modEventBus);
        CatalystBlockEntities.register(modEventBus);

        CatalystCreativeTab.register(modEventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }

    private void registerCapabilities(final RegisterCapabilitiesEvent event)
    {
            event.registerItem(
                    CuriosCapability.ITEM,
                    (stack, context) -> new CatalystCurio(stack),
                    CatalystItems.FULL_CATALYST.get()
            );
        }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }

    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void registerBER(EntityRenderersEvent.RegisterRenderers event)
        {
            event.registerBlockEntityRenderer(CatalystBlockEntities.PEDESTAL_BE.get(), CatalystPedestalItemRenderer::new);
        }

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            event.enqueueWork(ClientSetup::init);
        }
    }
}
