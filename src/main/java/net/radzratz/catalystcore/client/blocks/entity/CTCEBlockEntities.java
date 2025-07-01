package net.radzratz.catalystcore.client.blocks.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.radzratz.catalystcore.CatalystCore;
import net.radzratz.catalystcore.client.blocks.CTCEBlocks;
import net.radzratz.catalystcore.client.blocks.entity.cauldron.CatalystCauldronBlockEntity;
import net.radzratz.catalystcore.client.blocks.entity.pedestal.CatalystAltarPedestalEntity;
import net.radzratz.catalystcore.client.blocks.entity.tilmat_table.TilmatTableBlockEntity;

import java.util.function.Supplier;

public class CTCEBlockEntities
{
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, CatalystCore.MOD_ID);

    public static final Supplier<BlockEntityType<CatalystAltarPedestalEntity>> PEDESTAL_ALTAR =
            BLOCK_ENTITIES.register("pedestal_altar_1", ()-> BlockEntityType.Builder.of(
                    CatalystAltarPedestalEntity::new, CTCEBlocks.CATALYST_ALTAR_PEDESTAL.get()).build(null));

    public static final Supplier<BlockEntityType<CatalystCauldronBlockEntity>> CAULDRON =
            BLOCK_ENTITIES.register("cauldron", ()-> BlockEntityType.Builder.of(
                    CatalystCauldronBlockEntity::new, CTCEBlocks.CAULDRON.get()).build(null));

    public static final Supplier<BlockEntityType<TilmatTableBlockEntity>> TILMAT_TABLE =
            BLOCK_ENTITIES.register("tilmat_table", ()-> BlockEntityType.Builder.of(
                    TilmatTableBlockEntity::new, CTCEBlocks.TILMAT_TABLE.get()).build(null));

    public static void register(IEventBus eventBus)
    {
        BLOCK_ENTITIES.register(eventBus);
    }
}
