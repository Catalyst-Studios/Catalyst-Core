package net.radzratz.catalystcore.common.compat.curios.curio.curios;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.radzratz.catalystcore.client.items.CTCEItems;
import net.radzratz.catalystcore.common.util.config.CTCEConfig;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;
import java.util.function.BooleanSupplier;

public class CTCECurioItems
{
    private record CatalystCurioEffect(BooleanSupplier isActive, int amplifier)
    {
    }

    ///Catalyst Item Curio
    public static void registerCatalystCurioCapabilities(RegisterCapabilitiesEvent event)
    {
        event.registerItem(
                CuriosCapability.ITEM,
                (stack, context) -> new ICurio()
                {
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

                    private static final List<CatalystCurioEffect> EFFECT_CONFIGS = List.of(
                            new CatalystCurioEffect(CTCEConfig.CONFIG.curioCompatibility.catalystNightVision::isTrue, 0),
                            new CatalystCurioEffect(CTCEConfig.CONFIG.curioCompatibility.catalystDamageBoost::isTrue, 4),
                            new CatalystCurioEffect(CTCEConfig.CONFIG.curioCompatibility.catalystMovementSpeed::isTrue, 2),
                            new CatalystCurioEffect(CTCEConfig.CONFIG.curioCompatibility.catalystSaturation::isTrue, 0),
                            new CatalystCurioEffect(CTCEConfig.CONFIG.curioCompatibility.catalystWaterBreathing::isTrue, 0),
                            new CatalystCurioEffect(CTCEConfig.CONFIG.curioCompatibility.catalystLuck::isTrue, 4),
                            new CatalystCurioEffect(CTCEConfig.CONFIG.curioCompatibility.catalystHealthBoost::isTrue, 4),
                            new CatalystCurioEffect(CTCEConfig.CONFIG.curioCompatibility.catalystFireResistance::isTrue, 0),
                            new CatalystCurioEffect(CTCEConfig.CONFIG.curioCompatibility.catalystRegeneration::isTrue, 4)
                    );

                    @Override
                    public void curioTick(SlotContext slotContext)
                    {
                        LivingEntity entity = slotContext.entity();

                        if(!entity.level().isClientSide() && CTCEConfig.CONFIG.curioCompatibility.catalystCurioEffects.get())
                        {
                            for(int i = 0; i < EFFECT_CONFIGS.size(); i++)
                            {
                                CatalystCurioEffect config = EFFECT_CONFIGS.get(i);
                                if(config.isActive().getAsBoolean())
                                {
                                    applyPermanentEffect(entity, CURIO_EFFECTS.get(i), config.amplifier());
                                }
                            }
                        }
                    }

                    @Override
                    public ItemStack getStack()
                    {
                        return stack;
                    }

                    private void applyPermanentEffect(LivingEntity entity,
                                                      Holder<MobEffect> effect,
                                                      int amplifier)
                    {
                        boolean hasEffect = entity.getActiveEffects()
                                .stream()
                                .anyMatch(instance -> instance.getEffect() == effect.value());

                        if(!hasEffect)
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

                    public boolean makesPiglinsNeutral(SlotContext slotContext)
                    {
                        return true;
                    }

                    public boolean canWalkOnPowderedSnow(SlotContext slotContext)
                    {
                        return true;
                    }

                    public boolean isEnderMask(SlotContext slotContext, EnderMan enderMan)
                    {
                        return true;
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
                        return slotContext.identifier().equals("catalyst") || slotContext.identifier().equals("curio");
                    }

                },
                CTCEItems.FULL_CATALYST.get()
        );
    }
    ///Possibly others...
}