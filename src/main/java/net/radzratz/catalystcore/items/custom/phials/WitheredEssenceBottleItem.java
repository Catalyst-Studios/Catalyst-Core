package net.radzratz.catalystcore.items.custom.phials;

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
import net.radzratz.catalystcore.items.CatalystItems;
import net.radzratz.catalystcore.util.config.CatalystConfig;

@EventBusSubscriber
@SuppressWarnings("all")
public class WitheredEssenceBottleItem
{
    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event)
    {
        if(!CatalystConfig.CONFIG.phials.phialEvents.isTrue()) return;

        if(!CatalystConfig.CONFIG.phials.witheredPhial.isTrue()) return;

        Entity target = event.getTarget();
        if(!(target instanceof WitherBoss wither)) return;

        Player player = event.getEntity();
        ItemStack mainHand = player.getMainHandItem();
        ItemStack offHand = player.getOffhandItem();

        boolean mainIsBrush = mainHand.is(Items.BRUSH);
        boolean offIsBottle = offHand.is(CatalystItems.REINFORCED_BOTTLE.get());

        if(mainIsBrush && offIsBottle)
        {
            if(!player.level().isClientSide)
            {
                offHand.shrink(1);

                ItemStack witherEssence = new ItemStack(CatalystItems.WITHERED_BOTTLE.get());

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
