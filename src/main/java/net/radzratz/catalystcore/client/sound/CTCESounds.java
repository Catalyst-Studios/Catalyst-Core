package net.radzratz.catalystcore.client.sound;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.radzratz.catalystcore.CatalystCore;

public class CTCESounds
{
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, CatalystCore.id);

    public static final DeferredHolder<SoundEvent, SoundEvent> PENTAGRAM_CRAFT =
            SOUND_EVENTS.register("pentagram_craft",
                    () -> SoundEvent.createVariableRangeEvent(
                            ResourceLocation.fromNamespaceAndPath("catalystcore", "pentagram_craft")));

    public static final DeferredHolder<SoundEvent, SoundEvent> PENTAGRAM_PLACE =
            SOUND_EVENTS.register("pentagram_place",
                    () -> SoundEvent.createVariableRangeEvent(
                            ResourceLocation.fromNamespaceAndPath("catalystcore", "pentagram_place")));

    public static void init(IEventBus eventBus)
    {
        SOUND_EVENTS.register(eventBus);
    }
}