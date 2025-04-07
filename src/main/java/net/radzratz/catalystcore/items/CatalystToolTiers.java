package net.radzratz.catalystcore.items;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;
import net.radzratz.catalystcore.util.tag.CatalystTags;

public class CatalystToolTiers
{

    public static final Tier CATALYST = new SimpleTier(CatalystTags.Blocks.CATALYST_TOOLS,
            Integer.MAX_VALUE, 0.5f, 10f, 70,
            ()-> Ingredient.of(CatalystItems.CATALYST_INGOT));

}
