package net.radzratz.catalystcore.client.items.tools;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.BooleanSupplier;

import static net.radzratz.catalystcore.common.registries.CTCEToolsRegistry.*;

public class CTCESwordItem extends SwordItem {
    private final BooleanSupplier module;
    private final BooleanSupplier type;
    private final boolean noBarDamage;

    public CTCESwordItem(Tier tier, boolean noBarDamage, Properties properties, Tool toolData, ItemAttributeModifiers attributes, BooleanSupplier module, BooleanSupplier type) {
        super(tier, properties.component(DataComponents.TOOL, toolData).component(DataComponents.ATTRIBUTE_MODIFIERS, attributes));
        this.module = module;
        this.type = type;
        this.noBarDamage = noBarDamage;
    }

    @Override
    public boolean isEnabled(@NotNull FeatureFlagSet enabledFeatures) {
        return module.getAsBoolean() && type.getAsBoolean();
    }

    @Override
    public void appendHoverText(ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltip, @NotNull TooltipFlag tooltipFlag) {
        Item item = stack.getItem();

        if (item == CATALYST_GREATSWORD.get()) {
            tooltip.add(Component.translatable("item.catalystcore.cataclystic_greatsword.desc").withStyle(ChatFormatting.DARK_BLUE));
        }

        if (item == CATALYST_ZWEIHANDER.get()) {
            tooltip.add(Component.translatable("item.catalystcore.cataclystic_zweihander.desc").withStyle(ChatFormatting.DARK_BLUE));
        }

        if (item == CATALYST_ULFBERHT.get()) {
            tooltip.add(Component.translatable("item.catalystcore.cataclystic_ulfberht.desc").withStyle(ChatFormatting.DARK_BLUE));
        }

        if (item == UNIVERSE_SWORD.get()) {
            tooltip.add(Component.translatable("item.catalystcore.universe_sword.desc").withStyle(ChatFormatting.WHITE));
        }

        if (item == CATALYST_SCYTHE.get()) {
            tooltip.add(Component.translatable("item.catalystcore.cataclystic_scythe.desc").withStyle(ChatFormatting.DARK_BLUE));
        }

        if (item == CATALYST_RAPIER.get()) {
            tooltip.add(Component.translatable("item.catalystcore.cataclystic_rapier.desc").withStyle(ChatFormatting.DARK_BLUE));
        }

        if (item == CATALYST_HALBERD.get()) {
            tooltip.add(Component.translatable("item.catalystcore.cataclystic_halberd.desc").withStyle(ChatFormatting.DARK_BLUE));
        }

        if (item == CATALYST_BROADSWORD.get()) {
            tooltip.add(Component.translatable("item.catalystcore.cataclystic_broadsword.desc").withStyle(ChatFormatting.DARK_BLUE));
        }

        if (item == CATALYST_BIG_BONK.get()) {
            tooltip.add(Component.translatable("item.catalystcore.cataclystic_big_bonk.desc").withStyle(ChatFormatting.DARK_BLUE));
        }

        if (item == CATALYST_KATAR.get()) {
            tooltip.add(Component.translatable("item.catalystcore.cataclystic_katar.desc").withStyle(ChatFormatting.DARK_BLUE));
        }

        if (item == CATALYST_GLADIUS.get()) {
            tooltip.add(Component.translatable("item.catalystcore.cataclystic_gladius.desc").withStyle(ChatFormatting.DARK_BLUE));
        }
    }

    @Override
    public boolean isEnchantable(@NotNull ItemStack stack) {
        return true;
    }

    @Override
    public boolean isBarVisible(@NotNull ItemStack stack) {
        if (stack.toString().contains("cataclystic")) {
            return false;
        }

        return stack.isDamageableItem() && stack.isDamaged();
    }

    @Override
    public boolean isDamageable(@NotNull ItemStack stack) {
        return !noBarDamage;
    }
}
