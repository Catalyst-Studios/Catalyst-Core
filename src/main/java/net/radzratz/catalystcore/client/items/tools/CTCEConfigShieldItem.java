package net.radzratz.catalystcore.client.items.tools;

import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.ShieldItem;
import org.jetbrains.annotations.NotNull;

import java.util.function.BooleanSupplier;

public class CTCEConfigShieldItem extends ShieldItem {
    private final BooleanSupplier module;
    private final BooleanSupplier type;

    public CTCEConfigShieldItem(Properties properties, BooleanSupplier module, BooleanSupplier type) {
        super(properties);
        this.module = module;
        this.type = type;
    }

    @Override
    public boolean isEnabled(@NotNull FeatureFlagSet enabledFeatures) {
        return module.getAsBoolean() && type.getAsBoolean();
    }
}
