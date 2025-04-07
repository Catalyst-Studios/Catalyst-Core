package net.radzratz.catalystcore.util.tag;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.radzratz.catalystcore.CatalystCore;

public class CatalystTags
{

    public static class Blocks
    {
        public static final TagKey<Block> CATALYST_TOOLS = catalystTags("catalyst_tools");

        public static final TagKey<Block> RAW_CATALYRIUM = catalystBlocks("storage_blocks/raw_catalyrium");

        private static TagKey<Block> catalystTags(String name)
        {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(CatalystCore.MOD_ID, name));
        }

        private static TagKey<Block> catalystBlocks(String path)
        {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath("c", path));
        }
    }

    public static class Items
    {
        public static TagKey<Item> RAW_CATALYRIUM = catalystOresGems("raw_materials/catalyrium");

        public static TagKey<Item> CALTARITE = catalystOresGems("gems/caltarite");

        private static TagKey<Item> catalystOresGems(String path)
        {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", path));
        }

        private static TagKey<Item> catalystItems(String name)
        {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(CatalystCore.MOD_ID, name));
        }
    }

}
