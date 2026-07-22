package net.radzratz.catalystcore.client.blocks;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.radzratz.catalystcore.CatalystCore;
import net.radzratz.catalystcore.client.blocks.type.CTCEGlass;
import net.radzratz.catalystcore.client.custom.CTCECauldron;
import net.radzratz.catalystcore.client.custom.CTCEPedestal;
import net.radzratz.catalystcore.client.custom.GravityAnomalyBlock;
import net.radzratz.catalystcore.client.custom.NebulaBlock;
import net.radzratz.catalystcore.client.items.CTCEItems;

import java.util.function.Supplier;

@SuppressWarnings("null")
public class CTCEBlocks {
    public static final DeferredRegister.Blocks CTCE_BLOCKS = DeferredRegister.createBlocks(CatalystCore.id);

    public static final DeferredBlock<CTCEPedestal> CATALYST_ALTAR_PEDESTAL = rgtrBlock("center_pedestal",
            ()-> new CTCEPedestal(BlockBehaviour.Properties.of().noOcclusion()));

    public static final DeferredBlock<CTCECauldron> CAULDRON = rgtrBlock("cauldron",
            ()-> new CTCECauldron(BlockBehaviour.Properties.of().noOcclusion()));

    public static final DeferredBlock<CTCEGlass> REINFORCED_GLASS = rgtrBlock("reinforced_glass",
            ()-> new CTCEGlass(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .isViewBlocking(((state, world, pos) -> true))
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<GravityAnomalyBlock> GRAVITY_ANOMALY = rgtrBlock("gravity_anomaly",
            () -> new GravityAnomalyBlock(BlockBehaviour.Properties.of()
                    .strength(-1.0F, 3600000.0F)
                    .noLootTable()
                    .noCollission()
                    .isViewBlocking(((state, world, pos) -> true))
                    .noOcclusion()
            ));

        public static final DeferredBlock<NebulaBlock> NEBULA_ETERNA = rgtrBlock("nebula_eterna",
            () -> new NebulaBlock(BlockBehaviour.Properties.of()
                    .strength(-1.0F, 3600000.0F)
                    .noLootTable()
                    .noCollission()
                    .isViewBlocking(((state, world, pos) -> true))
                    .noOcclusion()
                    .noTerrainParticles()
            ));
    
    private static <T extends Block> DeferredBlock<T> rgtrBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = CTCE_BLOCKS.register(name, block);
        rgtrBlockItem(name, toReturn);
        return toReturn;
    }

    public static <T extends Block> void rgtrBlockItem(String name, DeferredBlock<T> block) {
        CTCEItems.CTCE_ITEMS.register(name, ()-> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void rgtr(IEventBus eventBus) {
        CTCE_BLOCKS.register(eventBus);
    }
}
