package net.radzratz.catalystcore.client;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.radzratz.catalystcore.blocks.CatalystBlocks;
import net.radzratz.catalystcore.blocks.entity.CatalystBlockEntities;
import net.radzratz.catalystcore.entities.CatalystEntities;
import net.radzratz.catalystcore.particle.CatalystParticles;
import net.radzratz.catalystcore.renderer.block.CatalystAltarPedestalItemRenderer;
import net.radzratz.catalystcore.renderer.block.CatalystPedestalItemRenderer;
import net.radzratz.catalystcore.renderer.item.pentagram.PentagramMysticParticles;
import net.radzratz.catalystcore.renderer.item.pentagram.PentagramRenderEntity;

import static net.radzratz.catalystcore.CatalystCore.MOD_ID;

@SuppressWarnings("deprecation")
public class ClientRegistries
{
    @SuppressWarnings("unused")
    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void registerParticleFactories(RegisterParticleProvidersEvent event)
        {
            event.registerSpriteSet(
                    CatalystParticles.PENTAGRAM_PARTICLE.get(),
                    spriteSet -> (params, level, x, y, z, xSpeed, ySpeed, zSpeed) ->
                            new PentagramMysticParticles(level, new Vec3(x, y, z), x, y, z, spriteSet)
            );
        }

        @SubscribeEvent
        public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event)
        {
            event.registerEntityRenderer(CatalystEntities.PENTAGRAM.get(), PentagramRenderEntity::new);
        }

        @SubscribeEvent
        public static void registerBER(EntityRenderersEvent.RegisterRenderers event)
        {
            event.registerBlockEntityRenderer(CatalystBlockEntities.PEDESTAL_BE.get(), CatalystPedestalItemRenderer::new);
            event.registerBlockEntityRenderer(CatalystBlockEntities.PEDESTAL_ALTAR.get(), CatalystAltarPedestalItemRenderer::new);
        }

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            ItemBlockRenderTypes.setRenderLayer(CatalystBlocks.REINFORCED_GLASS.get(), RenderType.translucent());
        }
    }
}
