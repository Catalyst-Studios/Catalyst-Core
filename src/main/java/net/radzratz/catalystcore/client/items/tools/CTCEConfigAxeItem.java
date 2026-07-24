package net.radzratz.catalystcore.client.items.tools;

import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Tier;
import org.jetbrains.annotations.NotNull;

import java.util.function.BooleanSupplier;

public class CTCEConfigAxeItem extends AxeItem {
    private final BooleanSupplier module;
    private final BooleanSupplier type;

    public CTCEConfigAxeItem(Tier p_40521_, Properties p_40524_, BooleanSupplier module, BooleanSupplier type) {
        super(p_40521_, p_40524_);
        this.module = module;
        this.type = type;
    }

    @Override
    public boolean isEnabled(@NotNull FeatureFlagSet enabledFeatures) {
        return module.getAsBoolean() && type.getAsBoolean();
    }
}
