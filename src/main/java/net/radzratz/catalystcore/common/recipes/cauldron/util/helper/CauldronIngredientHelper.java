package net.radzratz.catalystcore.common.recipes.cauldron.util.helper;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.*;

public class CauldronIngredientHelper
{
    /**
     * Checks Any amount of Items
     */
    public static boolean matchIngredients(List<Ingredient> ingredients, List<ItemStack> inputs)
    {
        if (ingredients.isEmpty()) return true;

        List<ItemStack> availableItems = new ArrayList<>();
        for(ItemStack stack : inputs)
        {
            if(!stack.isEmpty())
            {
                availableItems.add(stack.copy());
            }
        }

        for(Ingredient ingredient : ingredients)
        {
            boolean found = false;

            for(Iterator<ItemStack> it = availableItems.iterator(); it.hasNext();)
            {
                ItemStack stack = it.next();
                if(ingredient.test(stack))
                {
                    stack.shrink(1);
                    if(stack.isEmpty())
                    {
                        it.remove();
                    }
                    found = true;
                    break;
                }
            }

            if(!found) return false;
        }

        return true;
    }

    /**
     * Checks Exact amount of Items
     */
    public static boolean matchExactIngredients(List<Ingredient> ingredients, List<ItemStack> inputs)
    {
        Map<Ingredient, Integer> required = new HashMap<>();
        ingredients.forEach(ing -> required.merge(ing, 1, Integer::sum));

        Map<Item, Integer> available = new HashMap<>();
        inputs.forEach(stack ->
        {
            if(!stack.isEmpty())
            {
                available.merge(stack.getItem(), stack.getCount(), Integer::sum);
            }
        });

        for(Map.Entry<Ingredient, Integer> entry : required.entrySet())
        {
            int totalFound = 0;
            for(ItemStack matchingStack : entry.getKey().getItems())
            {
                totalFound += available.getOrDefault(matchingStack.getItem(), 0);
            }
            if(totalFound < entry.getValue()) return false;
        }

        return true;
    }
}
