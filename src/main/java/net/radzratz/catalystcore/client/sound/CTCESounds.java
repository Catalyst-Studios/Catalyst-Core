package net.radzratz.catalystcore.client.sound;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.radzratz.catalystcore.CatalystCore;

import static net.radzratz.catalystcore.common.util.CTCEUtils.CTCE;

public class CTCESounds {
    public static final DeferredRegister<SoundEvent> CTCE_SOUND_EVENTS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, CatalystCore.id);

    // todo: work on audio design for these
    public static final DeferredHolder<SoundEvent, SoundEvent> PENTAGRAM_CRAFT =
            CTCE_SOUND_EVENTS.register("pentagram_craft", () -> SoundEvent.createVariableRangeEvent(CTCE("pentagram_craft")));

    public static final DeferredHolder<SoundEvent, SoundEvent> PENTAGRAM_PLACE =
            CTCE_SOUND_EVENTS.register("pentagram_place", () -> SoundEvent.createVariableRangeEvent(CTCE("pentagram_place")));

    public static void rgtr(IEventBus eventBus) {
        CTCE_SOUND_EVENTS.register(eventBus);
    }
}