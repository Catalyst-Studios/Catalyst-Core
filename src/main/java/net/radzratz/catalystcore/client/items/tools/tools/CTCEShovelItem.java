package net.radzratz.catalystcore.client.items.tools.tools;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.component.Unbreakable;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.BooleanSupplier;

public class CTCEShovelItem extends ShovelItem {
    private final BooleanSupplier module;
    private final BooleanSupplier type;

    public CTCEShovelItem(Tier tier, Properties properties, Tool toolComponentData, ItemAttributeModifiers attributes, BooleanSupplier module, BooleanSupplier type) {
        super(tier, properties.component(DataComponents.TOOL, toolComponentData)
                .component(DataComponents.ATTRIBUTE_MODIFIERS, attributes)
                .component(DataComponents.UNBREAKABLE, new Unbreakable(true)));
        this.module = module;
        this.type = type;
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
        return false;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, List<Component> tooltip, @NotNull TooltipFlag tooltipFlag) {
        tooltip.add(Component.translatable("item.catalystcore.cataclystic_shovel.desc").withStyle(ChatFormatting.DARK_BLUE));
    }

    @Override
    public boolean isDamageable(@NotNull ItemStack stack) {
        return false;
    }
}
