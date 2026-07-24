package net.radzratz.catalystcore.client.items;

import net.minecraft.world.flag.FeatureFlagSet;
import org.jetbrains.annotations.NotNull;

import java.util.function.BooleanSupplier;

public class CTCEConfigItem extends CTCEItem {
    public final BooleanSupplier enabled;

    public CTCEConfigItem(Properties properties, BooleanSupplier enabled) {
        super(properties);
        this.enabled = enabled;
    }

    @Override
    public boolean isEnabled(@NotNull FeatureFlagSet enabledFeatures) {
        return enabled == null || enabled.getAsBoolean();
    }
}