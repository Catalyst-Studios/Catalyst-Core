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
import net.radzratz.catalystcore.client.blocks.entity.anomaly.GravityAnomalyRenderer;
import net.radzratz.catalystcore.client.entities.CTCEEntities;
import net.radzratz.catalystcore.client.visuals.particle.CTCEParticles;
import net.radzratz.catalystcore.client.visuals.renderer.block.CatalystAltarPedestalItemRenderer;
import net.radzratz.catalystcore.client.visuals.renderer.block.CatalystCauldronRenderer;
import net.radzratz.catalystcore.client.visuals.renderer.particle.AnomalyMatterParticle;
import net.radzratz.catalystcore.client.visuals.renderer.particle.PentagramMysticParticles;
import net.radzratz.catalystcore.client.visuals.renderer.item.pentagram.PentagramRenderEntity;

@SuppressWarnings("null")
public class CTCEClientRegistries {
    @EventBusSubscriber(modid = CatalystCore.id, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
            event.registerSpriteSet(
                    CTCEParticles.PENTAGRAM_PARTICLE.get(),
                    spriteSet -> (params, level, x, y, z, xSpeed, ySpeed, zSpeed) ->
                            new PentagramMysticParticles(level, new Vec3(x, y, z), x, y, z, spriteSet)
            );

            event.registerSpriteSet(
                    CTCEParticles.PENTAGRAM_PARTICLE.get(),
                    spriteSet -> (params, level, x, y, z, xTarget, yTarget, zTarget) ->
                            new AnomalyMatterParticle(level, new Vec3(xTarget, yTarget, zTarget), x, y, z, spriteSet)
            );
        }

        @SubscribeEvent
        public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(CTCEEntities.PENTAGRAM.get(), PentagramRenderEntity::new);
        }

        @SubscribeEvent
        public static void registerCustomRenders(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(CTCEBlockEntities.PEDESTAL_ALTAR.get(), CatalystAltarPedestalItemRenderer::new);
            event.registerBlockEntityRenderer(CTCEBlockEntities.CAULDRON.get(), CatalystCauldronRenderer::new);
            event.registerBlockEntityRenderer(CTCEBlockEntities.ANOMALY.get() ,GravityAnomalyRenderer::new);
            event.registerBlockEntityRenderer(CTCEBlockEntities.NEBULA_ETERNA.get(), net.radzratz.catalystcore.client.blocks.entity.nebula.NebulaRenderer::new);
        }

        // WHY YA COMPLAIN!?
        @SuppressWarnings("deprecation")
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ItemBlockRenderTypes.setRenderLayer(CTCEBlocks.REINFORCED_GLASS.get(), RenderType.translucent());
        }
    }
}
