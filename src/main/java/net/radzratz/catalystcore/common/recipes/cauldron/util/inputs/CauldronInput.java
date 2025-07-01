package net.radzratz.catalystcore.common.recipes.cauldron.util.inputs;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@SuppressWarnings("all")
public record CauldronInput(List<ItemStack> items, List<FluidStack> fluids) implements RecipeInput
{
    public static CauldronInput of(List<ItemStack> items, List<FluidStack> fluids)
    {
        return new CauldronInput(
                items != null ? items : List.of(),
                fluids != null ? fluids : List.of()
        );
    }

    @Override
    public int size()
    {
        return items.size();
    }

    @Override
    public boolean isEmpty()
    {
        return items.stream().allMatch(ItemStack::isEmpty) &&
                fluids.stream().allMatch(f -> f.isEmpty() || f.getFluid() == Fluids.EMPTY);
    }

    @Override
    public @NotNull ItemStack getItem(int index)
    {
        return index < items.size() ? items.get(index) : ItemStack.EMPTY;
    }

    public FluidStack getFluid(int index)
    {
        return index < fluids.size() ? fluids.get(index) : FluidStack.EMPTY;
    }

    public boolean hasFluid(Fluid fluid, int minAmount)
    {
        return fluids.stream().anyMatch(f -> f.getFluid() == fluid && f.getAmount() >= minAmount);
    }

    public int getTotalFluidAmount(Fluid fluid)
    {
        return fluids.stream()
                .filter(f -> f.getFluid() == fluid)
                .mapToInt(FluidStack::getAmount)
                .sum();
    }
}
