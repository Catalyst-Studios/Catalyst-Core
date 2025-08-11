package net.radzratz.catalystcore.common.recipes.cauldron.types;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidStack;
import net.radzratz.catalystcore.common.recipes.cauldron.util.inputs.CauldronInput;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class ReactionBrewingFluidRecipeType implements Recipe<CauldronInput>
{
    private final List<Ingredient> orderedIngredients;
    private final FluidStack result;
    private final int fluidAmount;
    private final int cookingTime;
    private final boolean requiresHeat;
    @Nullable private final ResourceLocation fluidInputId;

    public ReactionBrewingFluidRecipeType(List<Ingredient> orderedIngredients, FluidStack result,
                                          int fluidAmount, int cookingTime, boolean requiresHeat,
                                          @Nullable ResourceLocation fluidInputId)
    {
        this.orderedIngredients = orderedIngredients;
        this.result = result;
        this.fluidAmount = fluidAmount;
        this.cookingTime = cookingTime;
        this.requiresHeat = requiresHeat;
        this.fluidInputId = fluidInputId;
    }

    public List<Ingredient> getOrderedIngredients()
    {
        return orderedIngredients;
    }

    public FluidStack getFluidResult()
    {
        return result;
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

    @Nullable public ResourceLocation getFluidInputId()
    {
        return fluidInputId;
    }

    @Override public boolean matches(@NotNull CauldronInput input, @NotNull Level level)
    {
        return true;
    }

    @Override public @NotNull ItemStack assemble(@NotNull CauldronInput input, HolderLookup.@NotNull Provider provider)
    {
        return ItemStack.EMPTY;
    }

    @Override public boolean isSpecial()
    {
        return true;
    }

    @Override public boolean canCraftInDimensions(int i, int j)
    {
        return true;
    }

    @Override public @NotNull ItemStack getResultItem(HolderLookup.@NotNull Provider provider)
    {
        return ItemStack.EMPTY;
    }

    @Override public @NotNull NonNullList<Ingredient> getIngredients()
    {
        return NonNullList.copyOf(orderedIngredients);
    }
}
