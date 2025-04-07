package net.radzratz.catalystcore;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.radzratz.catalystcore.items.CatalystItems;
import net.radzratz.catalystcore.util.CatalystCreativeTab;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;

@Mod(CatalystCore.MOD_ID)
public class CatalystCore
{

    public static final String MOD_ID = "catalystcore";

    public CatalystCore(IEventBus modEventBus, ModContainer modContainer)
    {

        modEventBus.addListener(this::registerCapabilities);

        modEventBus.addListener(this::commonSetup);

        CatalystItems.register(modEventBus);

        CatalystCreativeTab.register(modEventBus);

    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }

    private void registerCapabilities(final RegisterCapabilitiesEvent event)
    {
        event.registerItem(
                CuriosCapability.ITEM,
                (stack, context) -> new ICurio()
                {
                    private final List<Holder<MobEffect>> CURIO_EFFECTS = List.of(
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
                        if (!entity.level().isClientSide())
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
                        boolean hasEffect = entity.getActiveEffects().stream()
                                .anyMatch(instance -> instance.getEffect() == effect);

                        if(!hasEffect)
                        {
                            entity.addEffect(new MobEffectInstance(
                                    effect,
                                    -1,
                                    amplifier,
                                    false,
                                    false,
                                    false
                            )
                            {
                                public Component getDescription()
                                {
                                    return Component.translatable(this.getDescriptionId());
                                }
                            }, entity);
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
                },
                CatalystItems.FULL_CATALYST.get()
        );
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }

    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
        }
    }
}
