package net.radzratz.catalystcore.common.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.radzratz.catalystcore.CatalystCore;

import java.util.Collection;
import java.util.stream.Stream;

import static net.radzratz.catalystcore.common.registries.CTCEBlockRegistry.CTCE_BLOCKS;
import static net.radzratz.catalystcore.common.registries.CTCEItemRegistry.CTCE_ITEMS;
import static net.radzratz.catalystcore.common.registries.CTCEToolsRegistry.CTCE_TOOLS;

public class CTCEUtils {
    public static Stream<DeferredHolder<Item, ? extends Item>> allItemEntries() {
        return Stream.of(CTCE_ITEMS.getEntries()).flatMap(Collection::stream);
    }

    public static Stream<DeferredHolder<Item, ? extends Item>> allToolEntries() {
        return Stream.of(CTCE_TOOLS.getEntries()).flatMap(Collection::stream);
    }

    public static Stream<DeferredHolder<Block, ? extends Block>> allBlockEntries() {
        return Stream.of(CTCE_BLOCKS.getEntries()).flatMap(Collection::stream);
    }

    public static ResourceLocation CTCE(String path) {
        return ResourceLocation.fromNamespaceAndPath(CatalystCore.id, path);
    }
}
