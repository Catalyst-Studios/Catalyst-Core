package net.radzratz.catalystcore.recipes.pentagram.debug;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Collections;
import java.util.List;

///Debug only
public class TransmutationRecipe implements PentagramRecipes
{
    @Override
    public List<ItemStack> getInputs()
    {
        return Collections.singletonList(new ItemStack(Items.DIAMOND, 1));
    }

    @Override
    public ItemStack getResult()
    {
        return new ItemStack(Items.EMERALD, 1);
    }
}
