package net.radzratz.catalystcore.client.blocks;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TintedGlassBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.radzratz.catalystcore.CatalystCore;
import net.radzratz.catalystcore.client.custom.CTCECauldron;
import net.radzratz.catalystcore.client.custom.CTCEPedestal;
import net.radzratz.catalystcore.client.custom.GravityAnomalyBlock;
import net.radzratz.catalystcore.client.items.CTCEItems;

import java.util.function.Supplier;

public class CTCEBlocks {
    public static final DeferredRegister.Blocks CTCE_BLOCKS = DeferredRegister.createBlocks(CatalystCore.id);

    public static final DeferredBlock<CTCEPedestal> CATALYST_ALTAR_PEDESTAL = registerBlock("center_pedestal",
            ()-> new CTCEPedestal(BlockBehaviour.Properties.of().noOcclusion()));

    public static final DeferredBlock<CTCECauldron> CAULDRON = registerBlock("cauldron",
            ()-> new CTCECauldron(BlockBehaviour.Properties.of().noOcclusion()));

    public static final DeferredBlock<TintedGlassBlock> REINFORCED_GLASS = registerBlock("reinforced_glass",
            ()-> new TintedGlassBlock(BlockBehaviour.Properties.of()
                    .destroyTime(1f)
                    .explosionResistance(1000)
                    .noOcclusion()
                    .isViewBlocking(((state, world, pos) -> true))
                    .sound(SoundType.GLASS)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> GRAVITY_ANOMALY = registerBlock("gravity_anomaly",
            () -> new GravityAnomalyBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLACK)
                    .strength(-1.0F, 3600000.0F)
                    .explosionResistance(1000)
                    .noLootTable()
                    .noCollission()
                    .isViewBlocking(((state, world, pos) -> true))
                    .sound(SoundType.GLASS)
                    .noOcclusion()
            ));
    
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = CTCE_BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    public static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        CTCEItems.CTCE_ITEMS.register(name, ()-> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void rgtr(IEventBus eventBus) {
        CTCE_BLOCKS.register(eventBus);
    }
}
