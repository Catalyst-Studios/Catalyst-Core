package net.radzratz.catalystcore.recipes.pentagram.debug;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.radzratz.catalystcore.util.config.CatalystConfig;

import java.util.ArrayList;
import java.util.List;

///Debug only
public class PentagramRecipeManager
{
    private static final List<PentagramRecipes> RECIPES = new ArrayList<>();

    public static void addRecipe(PentagramRecipes recipe)
    {
        RECIPES.add(recipe);
    }

    @SuppressWarnings("unused")
    public static PentagramRecipes matchRecipe(ServerLevel level, List<ItemEntity> itemsAround)
    {
        for(PentagramRecipes recipe : RECIPES)
        {
            if(matches(recipe, itemsAround))
            {
                return recipe;
            }
        }
        return null;
    }

    private static boolean matches(PentagramRecipes recipe, List<ItemEntity> itemsAround)
    {
        if(!CatalystConfig.CONFIG.pentagram.enableHardcodedRecipes.get())
        {
            return false;
        }

        List<ItemStack> required = new ArrayList<>(recipe.getInputs());
        List<ItemEntity> toCheck = new ArrayList<>(itemsAround);

        for(ItemStack needed : required)
        {
            boolean found = false;

            for(ItemEntity itemEntity : toCheck)
            {
                if(ItemStack.isSameItemSameComponents(itemEntity.getItem(), needed) &&
                        itemEntity.getItem().getCount() >= needed.getCount())
                {
                    found = true;
                    break;
                }
            }

            if(!found)
            {
                return false;
            }
        }

        return true;
    }
}
