package net.radzratz.catalystcore.recipes.pentagram;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.radzratz.catalystcore.recipes.CatalystRecipeTypes;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class PentagramJsonRecipe implements Recipe<PentagramContainer>
{
    public final List<Ingredient> ingredient;
    public final ItemStack result;

    public PentagramJsonRecipe(List<Ingredient> ingredients, ItemStack result)
    {
        this.ingredient = ingredients;
        this.result = result;

        for (Ingredient ing : this.ingredient)
        {
            ing.getItems();
        }
    }


    @SuppressWarnings("unused")
    public Ingredient getInput()
    {
        return this.ingredient.getFirst();
    }

    @SuppressWarnings("unused")
    public List<Ingredient> getInputs()
    {
        return Collections.unmodifiableList(this.ingredient);
    }

    @Override
    public boolean matches(PentagramContainer container, @NotNull Level level)
    {
        List<ItemStack> available = new ArrayList<>();
        for (int i = 0; i < container.size(); i++)
        {
            ItemStack stack = container.getItem(i).copy();
            if (!stack.isEmpty())
            {
                available.add(stack);
            }
        }

        for (Ingredient ingredient : this.ingredient)
        {
            boolean found = false;
            Iterator<ItemStack> it = available.iterator();
            while (it.hasNext())
            {
                ItemStack stack = it.next();
                if (ingredient.test(stack))
                {
                    stack.shrink(1);
                    if (stack.isEmpty())
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

    @Override
    public @NotNull ItemStack assemble(@NotNull PentagramContainer pentagramContainer,
                                       HolderLookup.@NotNull Provider provider)
    {
        return result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height)
    {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem(@NotNull HolderLookup.Provider provider)
    {
        return this.result;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer()
    {
        return CatalystRecipeTypes.PENTAGRAM_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<?> getType()
    {
        return CatalystRecipeTypes.PENTAGRAM_TYPE.get();
    }
}
