package net.radzratz.catalystcore.client.items.tools;

import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import org.jetbrains.annotations.NotNull;

import java.util.function.BooleanSupplier;

public class CTCEConfigPickaxeItem extends PickaxeItem {
    private final BooleanSupplier module;
    private final BooleanSupplier type;

    public CTCEConfigPickaxeItem(Tier p_42961_, Properties p_42964_, BooleanSupplier module, BooleanSupplier type) {
        super(p_42961_, p_42964_);
        this.module = module;
        this.type = type;
    }

    @Override
    public boolean isEnabled(@NotNull FeatureFlagSet enabledFeatures) {
        return module.getAsBoolean() && type.getAsBoolean();
    }
}
