package net.radzratz.catalystcore.client.items.tools.tools;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Unbreakable;
import org.jetbrains.annotations.NotNull;

import java.util.function.BooleanSupplier;

public class CTCEHoeItem extends HoeItem {
    private final BooleanSupplier module;
    private final BooleanSupplier type;

    public CTCEHoeItem(Tier tier, ItemAttributeModifiers attributes, Properties properties, BooleanSupplier module, BooleanSupplier type) {
        super(tier, properties.stacksTo(1).setNoRepair().rarity(Rarity.EPIC)
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
    public boolean isDamageable(@NotNull ItemStack stack) {
        return false;
    }
}
