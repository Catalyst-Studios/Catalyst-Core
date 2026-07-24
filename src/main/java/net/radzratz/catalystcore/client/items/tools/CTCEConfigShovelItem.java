package net.radzratz.catalystcore.client.items.tools;

import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;
import org.jetbrains.annotations.NotNull;

import java.util.function.BooleanSupplier;

public class CTCEConfigShovelItem extends ShovelItem {
    private final BooleanSupplier module;
    private final BooleanSupplier type;

    public CTCEConfigShovelItem(Tier p_43114_, Properties p_43117_, BooleanSupplier module, BooleanSupplier type) {
        super(p_43114_, p_43117_);
        this.module = module;
        this.type = type;
    }

    @Override
    public boolean isEnabled(@NotNull FeatureFlagSet enabledFeatures) {
        return module.getAsBoolean() && type.getAsBoolean();
    }
}
