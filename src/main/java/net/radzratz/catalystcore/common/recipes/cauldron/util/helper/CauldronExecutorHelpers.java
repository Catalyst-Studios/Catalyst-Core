package net.radzratz.catalystcore.common.recipes.cauldron.util.helper;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.radzratz.catalystcore.client.blocks.entity.cauldron.CatalystCauldronBlockEntity;
import net.radzratz.catalystcore.common.recipes.cauldron.types.*;

public class CauldronExecutorHelpers
{
    public static void performCraftWaterBrewing(WaterBrewingRecipeType recipe, CatalystCauldronBlockEntity be, Level level)
    {
        be.consumeIngredients(recipe.getIngredients());
        be.getFluidTank().drain(recipe.getFluidAmount(), IFluidHandler.FluidAction.EXECUTE);

        ItemStack result = recipe.getResultItem(level.registryAccess());

        BlockPos pos = be.getBlockPos();
        Containers.dropItemStack(level, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, result);

        be.resetProgressExternally();

        level.playSound(null, pos, SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS, 1.0f, 1.0f);
    }

    public static void performCraftLavaBrewing(LavaBrewingRecipeType recipe, CatalystCauldronBlockEntity be, Level level)
    {
        be.consumeIngredients(recipe.getIngredients());
        be.getFluidTank().drain(recipe.getFluidAmount(), IFluidHandler.FluidAction.EXECUTE);

        ItemStack result = recipe.getResultItem(level.registryAccess());

        BlockPos pos = be.getBlockPos();
        Containers.dropItemStack(level, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, result);

        be.resetProgressExternally();
        level.playSound(null, pos, SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS, 1.0f, 1.0f);
    }

    public static void performCraftUniversalBrewing(UniversalBrewingRecipeType recipe, CatalystCauldronBlockEntity be, Level level)
    {
        be.consumeIngredients(recipe.getIngredients());
        be.getFluidTank().drain(recipe.getFluidAmount(), IFluidHandler.FluidAction.EXECUTE);

        ItemStack result = recipe.getResultItem(level.registryAccess());

        BlockPos pos = be.getBlockPos();
        Containers.dropItemStack(level, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, result);

        be.resetProgressExternally();
        level.playSound(null, pos, SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS, 1.0f, 1.0f);
    }

    public static void performCraftFluidBrewing(FluidBrewingRecipeType recipe, CatalystCauldronBlockEntity be, Level level)
    {
        be.consumeIngredients(recipe.getIngredients());

        be.getFluidTank().drain(recipe.getFluidAmount(), IFluidHandler.FluidAction.EXECUTE);

        FluidStack result = recipe.getResultFluid();
        be.getFluidTank().fill(result.copy(), IFluidHandler.FluidAction.EXECUTE);

        be.resetProgressExternally();

        BlockPos pos = be.getBlockPos();
        level.playSound(null, pos, SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS, 1.0f, 1.0f);
    }

    public static void performCraftReactionBrewingItem(ReactionBrewingItemRecipeType recipe, CatalystCauldronBlockEntity be, Level level)
    {
        be.consumeIngredients(recipe.getOrderedIngredients());
        be.getFluidTank().drain(recipe.getFluidAmount(), IFluidHandler.FluidAction.EXECUTE);

        ItemStack result = recipe.getResultItem(level.registryAccess());
        BlockPos pos = be.getBlockPos();
        Containers.dropItemStack(level,
                pos.getX() + 0.5,
                pos.getY() + 1,
                pos.getZ() + 0.5,
                result);

        be.resetProgressExternally();
        level.playSound(null, pos, SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS, 1.0f, 1.0f);
    }


    public static void performCraftReactionBrewingFluid(ReactionBrewingFluidRecipeType recipe, CatalystCauldronBlockEntity be, Level level)
    {
        be.consumeIngredients(recipe.getOrderedIngredients());
        be.getFluidTank().drain(recipe.getFluidAmount(), IFluidHandler.FluidAction.EXECUTE);

        FluidStack result = recipe.getFluidResult().copy();
        be.getFluidTank().fill(result, IFluidHandler.FluidAction.EXECUTE);

        BlockPos pos = be.getBlockPos();
        be.resetProgressExternally();
        level.playSound(null, pos, SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS, 1.0F, 1.0F);
    }
}
