package net.radzratz.catalystcore.common.recipes.pentagram;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.radzratz.catalystcore.common.recipes.CTCERecipeTypes;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PentagramJsonRecipe implements Recipe<PentagramContainer>
{
    private final NonNullList<Ingredient> ingredients;
    private final ItemStack result;

    public PentagramJsonRecipe(List<Ingredient> ingredients, ItemStack result)
    {
        this.ingredients = NonNullList.of(Ingredient.EMPTY, ingredients.toArray(new Ingredient[0]));
        this.result = result;
    }

    @Override
    public boolean matches(@NotNull PentagramContainer container, @NotNull Level level)
    {
        return ingredients.stream().allMatch(ing ->
                container.getItems().stream()
                        .filter(stack -> ing.test(stack))
                        .mapToInt(ItemStack::getCount)
                        .sum() >= 1
        );
    }

    @Override
    public boolean canCraftInDimensions(int width, int height)
    {
        return true;
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull PentagramContainer container, HolderLookup.@NotNull Provider registries)
    {
        return this.result.copy();
    }

    @Override
    public boolean isSpecial()
    {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem(HolderLookup.@NotNull Provider registries)
    {
        return this.result.copy();
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients()
    {
        return ingredients;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer()
    {
        return CTCERecipeTypes.PENTAGRAM_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<?> getType()
    {
        return CTCERecipeTypes.PENTAGRAM_TYPE.get();
    }
}
