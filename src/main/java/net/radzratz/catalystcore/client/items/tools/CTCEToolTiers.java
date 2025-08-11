package net.radzratz.catalystcore.client.items.tools;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;
import net.radzratz.catalystcore.client.items.CTCEItems;
import net.radzratz.catalystcore.client.items.tags.CTCETags;

public class CTCEToolTiers
{

    public static final Tier CATALYST = new SimpleTier(CTCETags.Blocks.CATALYST_TOOLS,
            Integer.MAX_VALUE, 10f, 0f, 70,
            ()-> Ingredient.of(CTCEItems.CATALYST_INGOT));

}
