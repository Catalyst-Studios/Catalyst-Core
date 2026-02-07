package net.radzratz.catalystcore.client.visuals.particle;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.radzratz.catalystcore.CatalystCore;

import java.util.function.Supplier;

public class CTCEParticles {
    public static final DeferredRegister<ParticleType<?>> CTCE_PARTICLES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE.key(), CatalystCore.id);

    public static final Supplier<SimpleParticleType> PENTAGRAM_PARTICLE =
            CTCE_PARTICLES.register("pentagram", () -> new SimpleParticleType(true));

    public static void rgtr(IEventBus eventBus) {
        CTCE_PARTICLES.register(eventBus);
    }
}
