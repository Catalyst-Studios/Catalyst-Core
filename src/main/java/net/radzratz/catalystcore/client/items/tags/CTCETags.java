package net.radzratz.catalystcore.client.items.tags;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import static net.radzratz.catalystcore.client.items.tags.CTCETagHelpers.*;

public class CTCETags {
    public static class Blocks {
        public static final TagKey<Block> CATALYST_TOOLS = catalystTags();
        public static final TagKey<Block> PAXEL_MINEABLE = catalystPaxel();
        public static final TagKey<Block> HEAT_SOURCES = catalystHeatSources();
    }

    public static class Items {
        public static TagKey<Item> HAMMER = pathByC("big_hammer");
        public static TagKey<Item> TOOL_HAMMER = weapon("big_hammer");
        public static TagKey<Item> SCYTHE = pathByC("scythe");
        public static TagKey<Item> TOOL_SCYTHE = weapon("scythe");
        public static TagKey<Item> ZWEIHANDER = pathByC("zweihander");
        public static TagKey<Item> TOOL_ZWEIHANDER = weapon("zweihander");
        public static TagKey<Item> LONGSWORD = pathByC("longsword");
        public static TagKey<Item> TOOL_LONGSWORD = weapon("longsword");
        public static TagKey<Item> GREATSWORD = pathByC("greatsword");
        public static TagKey<Item> TOOL_GREATSWORD = weapon("greatsword");
        public static TagKey<Item> RAPIER = pathByC("rapier");
        public static TagKey<Item> TOOL_RAPIER = weapon("rapier");
        public static TagKey<Item> KATAR = pathByC("katar");
        public static TagKey<Item> TOOL_KATAR = weapon("katar");
        public static TagKey<Item> HALBERD = pathByC("halberd");
        public static TagKey<Item> TOOL_HALBERD = weapon("halberd");
        public static TagKey<Item> SPEAR = pathByC("spear");
        public static TagKey<Item> TOOL_SPEAR = weapon("spear");
        public static TagKey<Item> BATTLEAXE = weapon("battleaxe");
        public static TagKey<Item> TOOL_BATTLEAXE = weapon("battleaxe");
        public static TagKey<Item> GLADIUS = pathByC("gladius");
        public static TagKey<Item> TOOL_GLADIUS = weapon("gladius");
        public static TagKey<Item> ULFBERHT = pathByC("ulfberht");
        public static TagKey<Item> TOOL_ULFBERHT = weapon("ulfberht");

        public static TagKey<Item> CATALYRIUM = pathByC("ingots/catalyrium");

        public static TagKey<Item> MALUM_SCYTHE = malum("scythe");

        ///Recipe Activators
        public static TagKey<Item> CAULDRON_ACTIVATOR_WATER = catalystActivator("cauldron/water_activator");
        public static TagKey<Item> CAULDRON_ACTIVATOR_LAVA = catalystActivator("cauldron/lava_activator");
        public static TagKey<Item> CAULDRON_ACTIVATOR_UNIVERSAL = catalystActivator("cauldron/universal_activator");
        public static TagKey<Item> CAULDRON_ACTIVATOR_FLUID = catalystActivator("cauldron/fluid_activator");
        public static TagKey<Item> CAULDRON_ACTIVATOR_REACTION_ITEM = catalystActivator("cauldron/item_reaction");
        public static TagKey<Item> CAULDRON_ACTIVATOR_REACTION_FLUID = catalystActivator("cauldron/fluid_reaction");
    }
}
