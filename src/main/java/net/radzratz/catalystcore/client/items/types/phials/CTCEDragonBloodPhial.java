package net.radzratz.catalystcore.client.items.types.phials;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.EnderDragonPart;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.radzratz.catalystcore.client.items.types.CTCEPhialItem;
import net.radzratz.catalystcore.common.registries.CTCEItemRegistry;

import java.util.function.BooleanSupplier;

public class CTCEDragonBloodPhial extends CTCEPhialItem {
    public CTCEDragonBloodPhial(Properties properties, BooleanSupplier item, BooleanSupplier event) {
        super(properties, item, event);
    }

    @Override
    public boolean matchesEntity(Entity target, Player player) {
        return resolveDragon(target) != null && findBottleHand(player) != null;
    }

    @Override
    public void fillFromEntity(Entity target, Player player) {
        InteractionHand bottleHand = findBottleHand(player);
        ItemStack heldBottle = player.getItemInHand(bottleHand);

        heldBottle.shrink(1);
        giveOrDrop(player, new ItemStack(CTCEItemRegistry.DRAGON_BLOOD.get()));

        playFillSound(player.level(), player.blockPosition(), SoundEvents.BOTTLE_FILL_DRAGONBREATH);
    }

    private static EnderDragon resolveDragon(Entity target) {
        if (target instanceof EnderDragon dragon) return dragon;
        if (target instanceof EnderDragonPart part) return part.parentMob;
        return null;
    }

    /** @return the hand holding the bottle, provided the other hand holds a sword; else null. */
    private static InteractionHand findBottleHand(Player player) {
        for (InteractionHand hand : InteractionHand.values()) {
            InteractionHand other = hand == InteractionHand.MAIN_HAND
                    ? InteractionHand.OFF_HAND
                    : InteractionHand.MAIN_HAND;

            boolean holdingBottle = player.getItemInHand(hand).is(CTCEItemRegistry.REINFORCED_BOTTLE);
            boolean otherIsSword = player.getItemInHand(other).getItem() instanceof SwordItem;

            if (holdingBottle && otherIsSword) return hand;
        }
        return null;
    }
}