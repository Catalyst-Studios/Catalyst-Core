package net.radzratz.catalystcore.items.tools.weapons;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.common.ItemAbility;
import net.radzratz.catalystcore.util.config.CatalystConfig;
import net.radzratz.catalystcore.util.config.CatalystItemInterface;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CatalystShield extends ShieldItem implements CatalystItemInterface
{

    public CatalystShield(Item.Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean shouldAppear()
    {
        return CatalystConfig.CONFIG.modules.weaponsModule.get();
    }

    @Override
    public boolean isEnabled(@NotNull FeatureFlagSet enabledFeatures)
    {
        return this.shouldAppear();
    }

    @Override
    public boolean isBarVisible(@NotNull ItemStack stack) {
        return false;
    }

    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack)
    {
        return UseAnim.BLOCK;
    }

    @Override
    public boolean canPerformAction(@NotNull ItemStack stack, @NotNull ItemAbility itemAbility)
    {
        return true;
    }

    @Override
    public boolean isDamageable(@NotNull ItemStack stack) {
        return false;
    }

    @Override
    public @NotNull EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.OFFHAND;
    }

    @Override
    public boolean isEnchantable(@NotNull ItemStack stack)
    {
        return true;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, List<Component> tooltip, @NotNull TooltipFlag flag)
    {
        tooltip.add(Component.translatable("item.catalystcore.cataclystic_shield.desc").withStyle(ChatFormatting.DARK_BLUE));
        super.appendHoverText(stack, context, tooltip, flag);
    }
}
