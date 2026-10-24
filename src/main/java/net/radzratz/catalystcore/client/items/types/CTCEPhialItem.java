package net.radzratz.catalystcore.client.items.types;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.radzratz.catalystcore.client.items.CTCEConfigItem;
import net.radzratz.catalystcore.common.util.config.CTCEConfig;

import java.util.function.BooleanSupplier;

@SuppressWarnings("null")
public class CTCEPhialItem extends CTCEConfigItem {
    private final BooleanSupplier phialEvent;

    public CTCEPhialItem(Properties properties, BooleanSupplier phial, BooleanSupplier phialEvent) {
        super(properties.stacksTo(16).rarity(Rarity.UNCOMMON), phial);
        this.phialEvent = phialEvent;
    }

    public boolean isEventEnabled() {
        //return phialEvent == null || !phialEvent.getAsBoolean();
        boolean globalEnabled = CTCEConfig.CONFIG.phials.phialEvents.get();
        return globalEnabled && (phialEvent == null || phialEvent.getAsBoolean());
    }

    public boolean matchesBlock(Level level, BlockState state, Player player) {
        return false;
    }

    public boolean matchesEntity(Entity target, Player player) {
        return false;
    }

    public void fillFromBlock(Level level, BlockPos pos, BlockState state, Player player) {}

    public void fillFromEntity(Entity target, Player player) {}

    protected static void giveOrDrop(Player player, ItemStack stack) {
        if (!player.getInventory().add(stack)) {
            player.drop(stack, false);
        }
    }

    protected static void playFillSound(Level level, BlockPos pos, SoundEvent sound) {
        level.playSound(null, pos, sound, SoundSource.PLAYERS, 1.0F, 1.0F);
    }
}
