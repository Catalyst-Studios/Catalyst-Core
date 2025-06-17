package net.radzratz.catalystcore.items.tools;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;
import net.radzratz.catalystcore.items.CatalystItems;
import net.radzratz.catalystcore.util.tag.CatalystTags;

public class CatalystToolTiers
{

    public static final Tier CATALYST = new SimpleTier(CatalystTags.Blocks.CATALYST_TOOLS,
            Integer.MAX_VALUE, 10f, 0f, 70,
            ()-> Ingredient.of(CatalystItems.CATALYST_INGOT));

}
