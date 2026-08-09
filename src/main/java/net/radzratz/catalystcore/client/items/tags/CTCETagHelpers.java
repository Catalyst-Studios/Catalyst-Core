package net.radzratz.catalystcore.client.items.tags;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.radzratz.catalystcore.CatalystCore;

public class CTCETagHelpers
{
    /// Blocks
    public static TagKey<Block> catalystHeatSources() {
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath(CatalystCore.id, "heat_sources"));
    }

    public static TagKey<Block> catalystTags() {
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath(CatalystCore.id, "catalyst_tools"));
    }

    public static TagKey<Block> catalystPaxel() {
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath(CatalystCore.id, "mineable/paxel"));
    }

    public static TagKey<Block> catalystBlocks(String path) {
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath("c", path));
    }

    ///Items
    public static TagKey<Item> malum(String path) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("malum", path));
    }

    public static TagKey<Item> weapon(String path) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "tools/" + path));
    }

    public static TagKey<Item> pathByC(String path) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", path));
    }

    public static TagKey<Item> catalystActivator(String path) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath(CatalystCore.id, path));
    }
}
