package net.radzratz.catalystcore.blocks.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.radzratz.catalystcore.CatalystCore;
import net.radzratz.catalystcore.blocks.CatalystBlocks;

import java.util.function.Supplier;

public class CatalystBlockEntities
{
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, CatalystCore.MOD_ID);

    public static final Supplier<BlockEntityType<CatalystPedestalEntity>> PEDESTAL_BE =
            BLOCK_ENTITIES.register("pedestal_be", ()-> BlockEntityType.Builder.of(
                    CatalystPedestalEntity::new, CatalystBlocks.CATALYST_PEDESTAL.get()).build(null));

    public static void register(IEventBus eventBus)
    {
        BLOCK_ENTITIES.register(eventBus);
    }
}
