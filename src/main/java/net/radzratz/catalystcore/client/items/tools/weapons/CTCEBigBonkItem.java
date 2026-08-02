package net.radzratz.catalystcore.client.items.tools.weapons;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.Level;
import net.radzratz.catalystcore.client.items.tools.CTCESwordItem;
import org.jetbrains.annotations.NotNull;

import java.util.function.BooleanSupplier;

public class CTCEBigBonkItem extends CTCESwordItem {
    private static final int SLOWNESS_AMPLIFIER = 1;
    private static final int EFFECT_DURATION = 25;

    public CTCEBigBonkItem(Tier tier, boolean noBarDamage, Properties properties, Tool toolData, ItemAttributeModifiers attributes, BooleanSupplier module, BooleanSupplier type) {
        super(tier, noBarDamage, properties, toolData, attributes, module, type);
    }

    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull Level level, @NotNull Entity entity, int slotId, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slotId, isSelected);

        if (level.isClientSide) return;
        if (!(entity instanceof LivingEntity living)) return;

        boolean holding = living.getMainHandItem() == stack || living.getOffhandItem() == stack;
        if (!holding) return;

        living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, EFFECT_DURATION, SLOWNESS_AMPLIFIER, false, false, true));
    }
}