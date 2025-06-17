package net.radzratz.catalystcore.items.custom.phials;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.EnderDragonPart;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.radzratz.catalystcore.items.CatalystItems;
import net.radzratz.catalystcore.util.config.CatalystConfig;

@EventBusSubscriber
@SuppressWarnings("all")
public class DragonBloodBottleitem
{
    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event)
    {
        if(!CatalystConfig.CONFIG.phials.phialEvents.isTrue()) return;

        if(!CatalystConfig.CONFIG.phials.dragonPhial.isTrue()) return;

        Entity target = event.getTarget();
        EnderDragon dragon = null;

        if(target instanceof EnderDragon ed)
        {
            dragon = ed;
        }
            else
                if(target instanceof EnderDragonPart part)
        {
            dragon = part.parentMob;
        }

        if(dragon == null) return;

        Player player = event.getEntity();
        InteractionHand hand = event.getHand();
        ItemStack heldItem = player.getItemInHand(hand);
        ItemStack otherHand = player.getItemInHand(hand == InteractionHand.MAIN_HAND ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND);

        boolean isHoldingBottle = heldItem.is(CatalystItems.REINFORCED_BOTTLE);
        boolean isHoldingSword = otherHand.getItem() instanceof SwordItem;

        if(isHoldingBottle && isHoldingSword)
        {
            if(!player.level().isClientSide)
            {
                heldItem.shrink(1);

                ItemStack blood = new ItemStack(CatalystItems.DRAGON_BLOOD.get());

                if(!player.getInventory().add(blood))
                {
                    player.drop(blood, false);
                }

                BlockPos pos = player.blockPosition();
                player.level().playSound(null,
                        pos,
                        SoundEvents.BOTTLE_FILL_DRAGONBREATH,
                        SoundSource.PLAYERS,
                        1.0F, 1.0F);
            }

            event.setCanceled(true);
            event.setCancellationResult(InteractionResult.SUCCESS);
        }
    }
}
