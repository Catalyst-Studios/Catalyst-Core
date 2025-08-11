package net.radzratz.catalystcore.common.recipes.cauldron.types;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluids;
import net.radzratz.catalystcore.common.recipes.cauldron.util.helper.CauldronIngredientHelper;
import net.radzratz.catalystcore.common.recipes.cauldron.util.inputs.CauldronInput;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class WaterBrewingRecipeType implements Recipe<CauldronInput>
{
    private final List<Ingredient> ingredients;
    private final String id;
    private final ItemStack result;
    private final int fluidAmount;
    private final int cookingTime;
    private final boolean requiresHeat;

    public WaterBrewingRecipeType(List<Ingredient> ingredients,
                                  ItemStack result,
                                  int fluidAmount,
                                  int cookingTime,
                                  boolean requiresHeat)
    {
        this.ingredients = ingredients;
        this.result = result;
        this.fluidAmount = fluidAmount;
        this.cookingTime = cookingTime;
        this.requiresHeat = requiresHeat;
        this.id = getId();
    }

    @Override
    public boolean matches(CauldronInput input, @NotNull Level level)
    {
        boolean hasFluid = input.fluids().stream()
                .anyMatch(f -> f.getFluid() == Fluids.WATER && f.getAmount() >= this.fluidAmount);

        boolean hasItems = CauldronIngredientHelper.matchIngredients(this.ingredients, input.items());

        return hasFluid && hasItems;
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull CauldronInput cauldronInput, HolderLookup.@NotNull Provider provider)
    {
        return result.copy();
    }

    @Override
    public boolean isSpecial()
    {
        return true;
    }

    @Override
    public boolean canCraftInDimensions(int i, int j)
    {
        return true;
    }

    public int getFluidAmount()
    {
        return fluidAmount;
    }

    public int getCookingTime()
    {
        return cookingTime;
    }

    public boolean requiresHeat()
    {
        return requiresHeat;
    }

    @Override
    public @NotNull ItemStack getResultItem(HolderLookup.@NotNull Provider provider)
    {
        return result.copy();
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients()
    {
        return NonNullList.copyOf(ingredients);
    }

    public ItemStack getOutput()
    {
        return result;
    }

    public String getId()
    {
        return "water_brewing";
    }
}
