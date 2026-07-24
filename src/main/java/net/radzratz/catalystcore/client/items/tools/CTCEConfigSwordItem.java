package net.radzratz.catalystcore.client.items.tools;

import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import org.jetbrains.annotations.NotNull;

import java.util.function.BooleanSupplier;

public class CTCEConfigSwordItem extends SwordItem {
    private final BooleanSupplier module;
    private final BooleanSupplier type;

    public CTCEConfigSwordItem(Tier tier, Properties properties, BooleanSupplier module, BooleanSupplier type) {
        super(tier, properties);
        this.module = module;
        this.type = type;
    }

    @Override
    public boolean isEnabled(@NotNull FeatureFlagSet enabledFeatures) {
        return module.getAsBoolean() && type.getAsBoolean();
    }
}
