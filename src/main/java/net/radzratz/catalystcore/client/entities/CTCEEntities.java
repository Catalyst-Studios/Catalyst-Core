package net.radzratz.catalystcore.client.entities;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.radzratz.catalystcore.CatalystCore;
import net.radzratz.catalystcore.client.entities.pentagram.PentagramEntity;
import net.radzratz.catalystcore.client.entities.tilmat_table.CatalystTilmatEntity;

public class CTCEEntities
{
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, CatalystCore.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<PentagramEntity>> PENTAGRAM =
            ENTITY_TYPES.register("pentagram",
                    () -> EntityType.Builder.<PentagramEntity>of(PentagramEntity::new, MobCategory.MISC)
                            .sized(3.0F, 0.1F)
                            .build("pentagram"));

    public static final DeferredHolder<EntityType<?>, EntityType<CatalystTilmatEntity>> TILMAT_TABLE =
            ENTITY_TYPES.register("tilmat_table",
                    () -> EntityType.Builder.<CatalystTilmatEntity>of(CatalystTilmatEntity::new, MobCategory.MISC)
                            .sized(3.0F, 0.1F)
                            .build("tilmat_table"));

    public static void register(IEventBus bus)
    {
        ENTITY_TYPES.register(bus);
    }
}
