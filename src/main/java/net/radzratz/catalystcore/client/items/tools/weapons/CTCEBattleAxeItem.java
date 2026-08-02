package net.radzratz.catalystcore.client.items.tools.weapons;

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

public class CTCEBattleAxeItem extends AxeItem {
    private final boolean noBarDamage;
    private final BooleanSupplier module;
    private final BooleanSupplier type;

    public CTCEBattleAxeItem(Tier tier, boolean noBarDamage, Item.Properties properties, Tool toolComponentData, ItemAttributeModifiers attributes, BooleanSupplier module, BooleanSupplier type) {
        super(tier, properties.component(DataComponents.TOOL,
                toolComponentData).component(DataComponents.ATTRIBUTE_MODIFIERS, attributes));
        this.noBarDamage = noBarDamage;
        this.module = module;
        this.type = type;
    }

    @Override
    public void appendHoverText(ItemStack stack, @NotNull Item.TooltipContext context, @NotNull List<Component> tooltip, @NotNull TooltipFlag tooltipFlag) {
        Item item = stack.getItem();

        if (item == CATALYST_BATTLEAXE.get()) {
            tooltip.add(Component.translatable("item.catalystcore.cataclystic_battleaxe.desc").withStyle(ChatFormatting.DARK_BLUE));
        }
    }

    @Override
    public boolean isEnabled(@NotNull FeatureFlagSet enabledFeatures) {
        return module.getAsBoolean() && type.getAsBoolean();
    }

    @Override
    public boolean isEnchantable(@NotNull ItemStack stack) {
        return true;
    }

    @Override
    public boolean isBarVisible(@NotNull ItemStack stack) {
        return !noBarDamage;
    }

    @Override
    public boolean isDamageable(@NotNull ItemStack stack) {
        return !noBarDamage;
    }
}
