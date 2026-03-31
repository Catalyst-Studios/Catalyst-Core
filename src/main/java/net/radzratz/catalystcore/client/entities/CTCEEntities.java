package net.radzratz.catalystcore.client.entities;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.radzratz.catalystcore.CatalystCore;
import net.radzratz.catalystcore.client.entities.pentagram.PentagramEntity;

public class CTCEEntities {
    public static final DeferredRegister<EntityType<?>> CTCE_ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, CatalystCore.id);

    // todo: fix this unlawful thing
    public static final DeferredHolder<EntityType<?>, EntityType<PentagramEntity>> PENTAGRAM =
            CTCE_ENTITY_TYPES.register("pentagram", () -> EntityType.Builder.<PentagramEntity>of(PentagramEntity::new, MobCategory.MISC).sized(3.0F, 0.1F)
                    .build("pentagram"));

    public static void rgtr(IEventBus bus) {
        CTCE_ENTITY_TYPES.register(bus);
    }
}
