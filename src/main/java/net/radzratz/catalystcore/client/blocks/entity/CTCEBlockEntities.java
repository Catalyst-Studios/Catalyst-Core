package net.radzratz.catalystcore.client.blocks.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.radzratz.catalystcore.CatalystCore;
import net.radzratz.catalystcore.common.registries.CTCEBlockRegistry;
import net.radzratz.catalystcore.client.blocks.entity.anomaly.GravityAnomalyBlockEntity;
import net.radzratz.catalystcore.client.blocks.entity.cauldron.CatalystCauldronBlockEntity;
import net.radzratz.catalystcore.client.blocks.entity.nebula.NebulaBlockEntity;
import net.radzratz.catalystcore.client.blocks.entity.pedestal.CatalystAltarPedestalEntity;

import java.util.function.Supplier;

@SuppressWarnings("null")
public class CTCEBlockEntities
{
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, CatalystCore.id);

    public static final Supplier<BlockEntityType<CatalystAltarPedestalEntity>> PEDESTAL_ALTAR =
            BLOCK_ENTITIES.register("pedestal_altar_1", ()-> BlockEntityType.Builder.of(
                    CatalystAltarPedestalEntity::new, CTCEBlockRegistry.CATALYST_ALTAR_PEDESTAL.get()).build(null));

    public static final Supplier<BlockEntityType<CatalystCauldronBlockEntity>> CAULDRON =
            BLOCK_ENTITIES.register("cauldron", ()-> BlockEntityType.Builder.of(
                    CatalystCauldronBlockEntity::new, CTCEBlockRegistry.CAULDRON.get()).build(null));

    public static final Supplier<BlockEntityType<GravityAnomalyBlockEntity>> ANOMALY =
            BLOCK_ENTITIES.register("anomaly", ()-> BlockEntityType.Builder.of(
                    GravityAnomalyBlockEntity::new, CTCEBlockRegistry.GRAVITY_ANOMALY.get()).build(null));

    public static final Supplier<BlockEntityType<NebulaBlockEntity>> NEBULA_ETERNA =
            BLOCK_ENTITIES.register("nebula", ()-> BlockEntityType.Builder.of(
                    NebulaBlockEntity::new, CTCEBlockRegistry.NEBULA_ETERNA.get()).build(null));

    public static void register(IEventBus eventBus)
    {
        BLOCK_ENTITIES.register(eventBus);
    }
}
