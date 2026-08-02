package net.radzratz.catalystcore.client.items.tools.weapons;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.radzratz.catalystcore.client.items.tools.CTCESwordItem;
import org.jetbrains.annotations.NotNull;

import java.util.function.BooleanSupplier;

public class CTCEUniverseSwordItem extends CTCESwordItem {
    public CTCEUniverseSwordItem(Tier tier, boolean noBarDamage, Properties properties, Tool toolData, ItemAttributeModifiers attributes, BooleanSupplier module, BooleanSupplier type) {
        super(tier, noBarDamage, properties, toolData, attributes, module, type);
    }

    @Override
    public boolean isEnderMask(@NotNull ItemStack stack, @NotNull Player player, @NotNull EnderMan endermanEntity) {
        return true;
    }

    @Override
    public boolean makesPiglinsNeutral(@NotNull ItemStack stack, @NotNull LivingEntity wearer) {
        return true;
    }
}
