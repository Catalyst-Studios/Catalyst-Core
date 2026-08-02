package net.radzratz.catalystcore.client.items.tools.weapons;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.DispenserBlock;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.BooleanSupplier;

import static net.radzratz.catalystcore.common.registries.CTCEToolsRegistry.CATALYST_SHIELD;

public class CTCEShieldItem extends ShieldItem {
    private final BooleanSupplier module;
    private final BooleanSupplier type;

    public CTCEShieldItem(Properties properties, BooleanSupplier module, BooleanSupplier type) {
        super(properties);
        this.module = module;
        this.type = type;
        DispenserBlock.registerBehavior(this, ArmorItem.DISPENSE_ITEM_BEHAVIOR);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, List<Component> tooltip, @NotNull TooltipFlag tooltipFlag) {
        tooltip.add(Component.translatable("item.catalystcore.cataclystic_shield.desc").withStyle(ChatFormatting.DARK_BLUE));
    }

    @Override
    public boolean isEnabled(@NotNull FeatureFlagSet enabledFeatures) {
        return module.getAsBoolean() && type.getAsBoolean();
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack) {
        return UseAnim.BLOCK;
    }

    @Override
    public @NotNull EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.OFFHAND;
    }

    @Override
    public int getUseDuration(@NotNull ItemStack stack, @NotNull LivingEntity entity) {
        return 72000;
    }

    @Override
    public boolean canPerformAction(@NotNull ItemStack stack, @NotNull ItemAbility itemAbility) {
        return ItemAbilities.DEFAULT_SHIELD_ACTIONS.contains(itemAbility);
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return stack.getItem() != CATALYST_SHIELD.get();
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return stack.getItem() != CATALYST_SHIELD.get();
    }
}
