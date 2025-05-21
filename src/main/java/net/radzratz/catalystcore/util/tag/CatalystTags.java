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
        public static final TagKey<Block> CATALYST_TOOLS = catalystTags();
        public static final TagKey<Block> RAW_CATALYRIUM = catalystBlocks("storage_blocks/raw_catalyrium");
        public static final TagKey<Block> PAXEL_MINEABLE = catalystPaxel();

        private static TagKey<Block> catalystTags()
        {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(CatalystCore.MOD_ID, "catalyst_tools"));
        }
        private static TagKey<Block> catalystPaxel()
        {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(CatalystCore.MOD_ID, "mineable/paxel"));
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
        public static TagKey<Item> CATALYST_ITEMS = catalystItems();
        public static TagKey<Item> CATALYST_TOOLS = catalystTools();
        public static TagKey<Item> CATALYST_HAMMER = catalystHammer("hammer");

        private static TagKey<Item> catalystOresGems(String path)
        {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", path));
        }
        private static TagKey<Item> catalystItems()
        {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(CatalystCore.MOD_ID, "catalyst_items"));
        }
        private static TagKey<Item> catalystTools()
        {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(CatalystCore.MOD_ID, "catalyst_tools"));
        }
        private static TagKey<Item> catalystHammer(String path)
        {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", path));
        }
    }

}
