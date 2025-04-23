package net.radzratz.catalystcore.items.curio;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;

public class CatalystCurio implements ICurio
{

    private final ItemStack stack;

    public CatalystCurio(ItemStack stack)
    {
        this.stack = stack;
    }

    private static final List<Holder<MobEffect>> CURIO_EFFECTS = List.of(
            BuiltInRegistries.MOB_EFFECT.wrapAsHolder(MobEffects.NIGHT_VISION.value()),
            BuiltInRegistries.MOB_EFFECT.wrapAsHolder(MobEffects.DAMAGE_BOOST.value()),
            BuiltInRegistries.MOB_EFFECT.wrapAsHolder(MobEffects.MOVEMENT_SPEED.value()),
            BuiltInRegistries.MOB_EFFECT.wrapAsHolder(MobEffects.SATURATION.value()),
            BuiltInRegistries.MOB_EFFECT.wrapAsHolder(MobEffects.WATER_BREATHING.value()),
            BuiltInRegistries.MOB_EFFECT.wrapAsHolder(MobEffects.LUCK.value()),
            BuiltInRegistries.MOB_EFFECT.wrapAsHolder(MobEffects.HEALTH_BOOST.value()),
            BuiltInRegistries.MOB_EFFECT.wrapAsHolder(MobEffects.FIRE_RESISTANCE.value()),
            BuiltInRegistries.MOB_EFFECT.wrapAsHolder(MobEffects.REGENERATION.value())
    );

    @Override
    public ItemStack getStack()
    {
        return stack;
    }

    @Override
    public void curioTick(SlotContext slotContext)
    {
        LivingEntity entity = slotContext.entity();
        if(!entity.level().isClientSide())
        {
            applyPermanentEffect(entity, CURIO_EFFECTS.get(0), 0);
            applyPermanentEffect(entity, CURIO_EFFECTS.get(1), 4);
            applyPermanentEffect(entity, CURIO_EFFECTS.get(2), 2);
            applyPermanentEffect(entity, CURIO_EFFECTS.get(3), 0);
            applyPermanentEffect(entity, CURIO_EFFECTS.get(4), 2);
            applyPermanentEffect(entity, CURIO_EFFECTS.get(5), 4);
            applyPermanentEffect(entity, CURIO_EFFECTS.get(6), 4);
            applyPermanentEffect(entity, CURIO_EFFECTS.get(7), 4);
            applyPermanentEffect(entity, CURIO_EFFECTS.get(8), 4);
        }
    }

    private void applyPermanentEffect(LivingEntity entity, Holder<MobEffect> effect, int amplifier)
    {
        boolean hasEffect = entity.getActiveEffects().stream().anyMatch(instance -> instance.getEffect() == effect.value());

        if (!hasEffect)
        {
            entity.addEffect(new MobEffectInstance(
                    effect,
                    -1,
                    amplifier,
                    false,
                    false,
                    false
            ), entity);
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack)
    {
        LivingEntity entity = slotContext.entity();
        CURIO_EFFECTS.forEach(entity::removeEffect);
    }

    @Override
    public boolean canEquip(SlotContext slotContext)
    {
        return slotContext.identifier().equals("catalyst") ||
                slotContext.identifier().equals("curio");
    }

}
