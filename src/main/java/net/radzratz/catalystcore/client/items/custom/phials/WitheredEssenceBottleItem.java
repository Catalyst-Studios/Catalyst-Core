package net.radzratz.catalystcore.client.items.custom.phials;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.radzratz.catalystcore.client.items.CTCEItems;
import net.radzratz.catalystcore.common.util.config.CTCEConfig;

@EventBusSubscriber
@SuppressWarnings("all")
public class WitheredEssenceBottleItem
{
    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event)
    {
        if(!CTCEConfig.CONFIG.phials.phialEvents.isTrue()) return;

        if(!CTCEConfig.CONFIG.phials.witheredPhial.isTrue()) return;

        Entity target = event.getTarget();
        if(!(target instanceof WitherBoss wither)) return;

        Player player = event.getEntity();
        ItemStack mainHand = player.getMainHandItem();
        ItemStack offHand = player.getOffhandItem();

        boolean mainIsBrush = mainHand.is(Items.BRUSH);
        boolean offIsBottle = offHand.is(CTCEItems.REINFORCED_BOTTLE.get());

        if(mainIsBrush && offIsBottle)
        {
            if(!player.level().isClientSide)
            {
                offHand.shrink(1);

                ItemStack witherEssence = new ItemStack(CTCEItems.WITHERED_BOTTLE.get());

                if(!player.getInventory().add(witherEssence))
                {
                    player.drop(witherEssence, false);
                }

                BlockPos pos = player.blockPosition();
                player.level().playSound(null,
                        pos,
                        SoundEvents.BOTTLE_FILL,
                        SoundSource.PLAYERS,
                        1.0F, 1.0F);
            }

            event.setCanceled(true);
            event.setCancellationResult(InteractionResult.SUCCESS);
        }
    }
}
