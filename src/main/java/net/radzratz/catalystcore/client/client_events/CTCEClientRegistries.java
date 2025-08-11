package net.radzratz.catalystcore.client.client_events;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.radzratz.catalystcore.CatalystCore;
import net.radzratz.catalystcore.client.blocks.CTCEBlocks;
import net.radzratz.catalystcore.client.blocks.entity.CTCEBlockEntities;
import net.radzratz.catalystcore.client.entities.CTCEEntities;
import net.radzratz.catalystcore.client.visuals.particle.CTCEParticles;
import net.radzratz.catalystcore.client.visuals.renderer.block.CatalystAltarPedestalItemRenderer;
import net.radzratz.catalystcore.client.visuals.renderer.block.CatalystCauldronRenderer;
import net.radzratz.catalystcore.client.visuals.renderer.particle.PentagramMysticParticles;
import net.radzratz.catalystcore.client.visuals.renderer.item.pentagram.PentagramRenderEntity;


@SuppressWarnings("deprecation")
public class CTCEClientRegistries
{
    @SuppressWarnings("unused")
    @EventBusSubscriber(modid = CatalystCore.id, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void registerParticleFactories(RegisterParticleProvidersEvent event)
        {
            event.registerSpriteSet(
                    CTCEParticles.PENTAGRAM_PARTICLE.get(),
                    spriteSet -> (params, level, x, y, z, xSpeed, ySpeed, zSpeed) ->
                            new PentagramMysticParticles(level, new Vec3(x, y, z), x, y, z, spriteSet)
            );
        }

        @SubscribeEvent
        public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event)
        {
            event.registerEntityRenderer(CTCEEntities.PENTAGRAM.get(), PentagramRenderEntity::new);
        }

        @SubscribeEvent
        public static void registerCustomRenders(EntityRenderersEvent.RegisterRenderers event)
        {
            event.registerBlockEntityRenderer(CTCEBlockEntities.PEDESTAL_ALTAR.get(), CatalystAltarPedestalItemRenderer::new);
            event.registerBlockEntityRenderer(CTCEBlockEntities.CAULDRON.get(), CatalystCauldronRenderer::new);
        }

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            ItemBlockRenderTypes.setRenderLayer(CTCEBlocks.REINFORCED_GLASS.get(), RenderType.translucent());
        }
    }
}
