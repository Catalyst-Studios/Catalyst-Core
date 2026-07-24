package net.radzratz.catalystcore.common.events;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.radzratz.catalystcore.client.items.types.CTCEPhialItem;
import net.radzratz.catalystcore.common.registries.CTCEItemRegistry;
import net.radzratz.catalystcore.common.util.config.CTCEConfig;

@EventBusSubscriber
public class CTCECommonEvents {
    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (!CTCEConfig.CONFIG.phials.phialEvents.isTrue()) return;

        Level level = event.getLevel();
        Player player = event.getEntity();
        BlockState state = level.getBlockState(event.getPos());

        for (CTCEPhialItem phial : CTCEItemRegistry.PHIALS) {
            if (phial.isEventEnabled()) continue;
            if (!phial.matchesBlock(level, state, player)) continue;

            if (!level.isClientSide) {
                phial.fillFromBlock(level, event.getPos(), state, player);
            }

            event.setCanceled(true);
            event.setCancellationResult(InteractionResult.SUCCESS);
            return;
        }
    }

    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        if (!CTCEConfig.CONFIG.phials.phialEvents.isTrue()) return;

        Entity target = event.getTarget();
        Player player = event.getEntity();

        for (CTCEPhialItem phial : CTCEItemRegistry.PHIALS) {
            if (phial.isEventEnabled()) continue;
            if (!phial.matchesEntity(target, player)) continue;

            if (!player.level().isClientSide) {
                phial.fillFromEntity(target, player);
            }

            event.setCanceled(true);
            event.setCancellationResult(InteractionResult.SUCCESS);
            return;
        }
    }
}
