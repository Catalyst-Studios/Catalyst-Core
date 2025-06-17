package net.radzratz.catalystcore.items.foods;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class CatalystFoods
{
    public static final FoodProperties BURRITO = new FoodProperties.Builder()
            .nutrition(7)
            .saturationModifier(4.5f)
            .effect(()-> new MobEffectInstance(MobEffects.ABSORPTION, 200), 0.15f)
            .effect(()-> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100), 0.10f)
            .effect(()-> new MobEffectInstance(MobEffects.HEALTH_BOOST, 400), 0.5f)
            .effect(()-> new MobEffectInstance(MobEffects.DIG_SPEED, 400), 0.1f)
            .effect(()-> new MobEffectInstance(MobEffects.HEAL, 100), 0.01f)
            .build();
}
