package net.radzratz.catalystcore.client.items.types.phials;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.sounds.SoundEvents;
import net.radzratz.catalystcore.client.items.types.CTCEPhialItem;
import net.radzratz.catalystcore.common.registries.CTCEItemRegistry;

import java.util.function.BooleanSupplier;

public class CTCEWitheredPhial extends CTCEPhialItem {
    public CTCEWitheredPhial(Properties properties, BooleanSupplier item, BooleanSupplier event) {
        super(properties, item, event);
    }

    @Override
    public boolean matchesEntity(Entity target, Player player) {
        if (!(target instanceof WitherBoss)) return false;

        ItemStack mainHand = player.getMainHandItem();
        ItemStack offHand = player.getOffhandItem();

        return mainHand.is(Items.BRUSH) && offHand.is(CTCEItemRegistry.REINFORCED_BOTTLE.get());
    }

    @Override
    public void fillFromEntity(Entity target, Player player) {
        ItemStack offHand = player.getOffhandItem();

        offHand.shrink(1);
        giveOrDrop(player, new ItemStack(CTCEItemRegistry.WITHERED_BOTTLE.get()));

        playFillSound(player.level(), player.blockPosition(), SoundEvents.BOTTLE_FILL);
    }
}