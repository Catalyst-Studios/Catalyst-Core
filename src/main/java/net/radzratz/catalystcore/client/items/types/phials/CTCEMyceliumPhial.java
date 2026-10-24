package net.radzratz.catalystcore.client.items.types.phials;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.radzratz.catalystcore.client.items.types.CTCEPhialItem;
import net.radzratz.catalystcore.common.registries.CTCEItemRegistry;

import java.util.function.BooleanSupplier;

public class CTCEMyceliumPhial extends CTCEPhialItem {
    public CTCEMyceliumPhial(Properties properties, BooleanSupplier item, BooleanSupplier event) {
        super(properties, item, event);
    }

    @Override
    public boolean matchesBlock(Level level, BlockState state, Player player) {
        if (!isEventEnabled()) return false;
        if (!state.is(Blocks.MYCELIUM)) return false;

        ItemStack mainHand = player.getMainHandItem();
        ItemStack offHand = player.getOffhandItem();

        boolean mainIsBrush = mainHand.is(Items.BRUSH) && offHand.is(CTCEItemRegistry.REINFORCED_BOTTLE.get());
        boolean offIsBrush = offHand.is(Items.BRUSH) && mainHand.is(CTCEItemRegistry.REINFORCED_BOTTLE.get());

        return mainIsBrush || offIsBrush;
    }

    @Override
    public void fillFromBlock(Level level, BlockPos pos, BlockState state, Player player) {
        if (level.isClientSide) return;

        ItemStack mainHand = player.getMainHandItem();
        ItemStack offHand = player.getOffhandItem();

        boolean brushInMain = mainHand.is(Items.BRUSH);
        EquipmentSlot brushSlot = brushInMain ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
        ItemStack bottleStack = brushInMain ? offHand : mainHand;

        bottleStack.shrink(1);
        giveOrDrop(player, new ItemStack(CTCEItemRegistry.MYCELIUM_BOTTLE.get()));

        level.setBlockAndUpdate(pos, Blocks.COARSE_DIRT.defaultBlockState());
        playFillSound(level, pos, SoundEvents.BOTTLE_FILL);

        if (brushInMain) {
            mainHand.hurtAndBreak(1, player, brushSlot);
        } else {
            offHand.hurtAndBreak(1, player, brushSlot);
        }
    }
}