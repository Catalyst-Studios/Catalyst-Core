package net.radzratz.catalystcore.client.items.types.phials;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.radzratz.catalystcore.client.items.types.CTCEPhialItem;
import net.radzratz.catalystcore.common.registries.CTCEItemRegistry;

import java.util.function.BooleanSupplier;

public class CTCESculkPhial extends CTCEPhialItem {
    public CTCESculkPhial(Properties properties, BooleanSupplier item, BooleanSupplier event) {
        super(properties, item, event);
    }

    @Override
    public boolean matchesBlock(Level level, BlockState state, Player player) {
        boolean validBlock = state.is(Blocks.SCULK) || state.is(Blocks.SCULK_SENSOR)
                || state.is(Blocks.SCULK_CATALYST) || state.is(Blocks.SCULK_SHRIEKER);
        if (!validBlock) return false;

        ItemStack mainHand = player.getMainHandItem();
        ItemStack offHand = player.getOffhandItem();

        return mainHand.is(Items.GOLDEN_SHOVEL) && offHand.is(CTCEItemRegistry.REINFORCED_BOTTLE.get());
    }

    @Override
    public void fillFromBlock(Level level, BlockPos pos, BlockState state, Player player) {
        ItemStack offHand = player.getOffhandItem();

        offHand.shrink(1);
        giveOrDrop(player, new ItemStack(CTCEItemRegistry.SCULK_BOTTLE.get()));

        playFillSound(level, pos, SoundEvents.BOTTLE_FILL);
    }
}