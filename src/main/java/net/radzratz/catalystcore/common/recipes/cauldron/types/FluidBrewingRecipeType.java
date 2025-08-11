package net.radzratz.catalystcore.common.recipes.cauldron.types;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidStack;
import net.radzratz.catalystcore.common.recipes.cauldron.util.helper.CauldronIngredientHelper;
import net.radzratz.catalystcore.common.recipes.cauldron.util.inputs.CauldronInput;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class FluidBrewingRecipeType implements Recipe<CauldronInput>
{
    protected final List<Ingredient> ingredients;
    protected final FluidStack result;
    protected final int fluidAmount;
    protected final int cookingTime;
    protected final boolean requiresHeat;
    @Nullable
    protected final ResourceLocation fluidId;

    public FluidBrewingRecipeType(List<Ingredient> ingredients,
                                  FluidStack result,
                                  int fluidAmount,
                                  int cookingTime,
                                  boolean requiresHeat,
                                  @Nullable ResourceLocation fluidId)
    {
        this.ingredients = ingredients;
        this.result = result;
        this.fluidAmount = fluidAmount;
        this.cookingTime = cookingTime;
        this.requiresHeat = requiresHeat;
        this.fluidId = fluidId;
    }

    @Override
    public boolean matches(CauldronInput input, @NotNull Level level)
    {
        boolean hasValidFluid = input.fluids().stream().anyMatch(fluidStack ->
        {
            boolean correctAmount = fluidStack.getAmount() >= fluidAmount;
            if(fluidId == null) return correctAmount;
            return correctAmount && BuiltInRegistries.FLUID.getKey(fluidStack.getFluid()).equals(fluidId);
        });

        boolean hasItems = CauldronIngredientHelper.matchIngredients(this.ingredients, input.items());
        return hasValidFluid && hasItems;
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull CauldronInput cauldronInput, HolderLookup.@NotNull Provider provider)
    {
        return ItemStack.EMPTY;
    }

    public FluidStack getResultFluid()
    {
        return result.copy();
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

    @Nullable
    public ResourceLocation getFluidId()
    {
        return fluidId;
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

    @Override
    public @NotNull ItemStack getResultItem(HolderLookup.@NotNull Provider provider)
    {
        return ItemStack.EMPTY;
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients()
    {
        return NonNullList.copyOf(ingredients);
    }
}
