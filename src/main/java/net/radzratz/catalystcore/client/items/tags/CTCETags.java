package net.radzratz.catalystcore.client.items.tags;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import static net.radzratz.catalystcore.client.items.tags.CTCETagHelpers.*;

public class CTCETags
{
    public static class Blocks
    {
        public static final TagKey<Block> CATALYST_TOOLS = catalystTags();
        public static final TagKey<Block> PAXEL_MINEABLE = catalystPaxel();
        public static final TagKey<Block> HEAT_SOURCES = catalystHeatSources();
    }

    public static class Items
    {
        public static TagKey<Item> CATALYST_ITEMS = catalystItems();
        public static TagKey<Item> CATALYST_TOOLS = catalystTools();
        public static TagKey<Item> CATALYST_HAMMER = catalystHammer("hammer");

        ///Recipe Activators
        public static TagKey<Item> CAULDRON_ACTIVATOR_WATER = catalystActivator("cauldron/water_activator");
        public static TagKey<Item> CAULDRON_ACTIVATOR_LAVA = catalystActivator("cauldron/lava_activator");
        public static TagKey<Item> CAULDRON_ACTIVATOR_UNIVERSAL = catalystActivator("cauldron/universal_activator");
        public static TagKey<Item> CAULDRON_ACTIVATOR_FLUID = catalystActivator("cauldron/fluid_activator");
        public static TagKey<Item> CAULDRON_ACTIVATOR_REACTION_ITEM = catalystActivator("cauldron/item_reaction");
        public static TagKey<Item> CAULDRON_ACTIVATOR_REACTION_FLUID = catalystActivator("cauldron/fluid_reaction");
    }

}
