package net.radzratz.catalystcore.blocks;

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
import net.radzratz.catalystcore.blocks.custom.EyeFlower;
import net.radzratz.catalystcore.custom.CatalystAltarPedestal;
import net.radzratz.catalystcore.custom.CatalystPedestal;
import net.radzratz.catalystcore.items.CatalystItems;

import java.util.function.Supplier;

public class CatalystBlocks
{
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(CatalystCore.MOD_ID);

    public static final DeferredBlock<CatalystPedestal> CATALYST_PEDESTAL = registerBlock("catalyst_pedestal",
            ()-> new CatalystPedestal(BlockBehaviour.Properties.of().noOcclusion()));

    public static final DeferredBlock<CatalystAltarPedestal> CATALYST_ALTAR_PEDESTAL = registerBlock("center_pedestal",
            ()-> new CatalystAltarPedestal(BlockBehaviour.Properties.of().noOcclusion()));

    public static final DeferredBlock<Block> EYE_FLOWER = registerBlock("eye_flower",
            () -> new EyeFlower(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .strength(0.2F)
                    .noOcclusion()
                    .sound(SoundType.AZALEA)));

    public static final DeferredBlock<TintedGlassBlock> REINFORCED_GLASS = registerBlock("reinforced_glass",
            ()-> new TintedGlassBlock(BlockBehaviour.Properties.of()
                    .destroyTime(1f)
                    .explosionResistance(1000)
                    .noOcclusion()
                    .isViewBlocking(((state, world, pos) -> true))
                    .sound(SoundType.GLASS)
                    .requiresCorrectToolForDrops()));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block)
    {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    public static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block)
    {
        CatalystItems.ITEMS.register(name, ()-> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}
