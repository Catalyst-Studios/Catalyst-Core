package net.radzratz.catalystcore.recipes.pentagram.debug;

import net.minecraft.world.item.ItemStack;

import java.util.List;

///Debug only
public interface PentagramRecipes
{
    List<ItemStack> getInputs();
    ItemStack getResult();
}
