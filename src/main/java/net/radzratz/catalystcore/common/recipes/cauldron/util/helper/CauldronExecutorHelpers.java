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
import net.radzratz.catalystcore.common.recipes.cauldron.reaction_brewing.item_output.RBRecipeItemType;
import net.radzratz.catalystcore.common.recipes.cauldron.types.fluid_brewing.FBRecipeType;
import net.radzratz.catalystcore.common.recipes.cauldron.types.lava_brewing.LBRecipeType;
import net.radzratz.catalystcore.common.recipes.cauldron.types.universal_brewing.UBRecipeType;
import net.radzratz.catalystcore.common.recipes.cauldron.types.water_brewing.WBRecipeType;

public class CauldronExecutorHelpers
{
    public static void performCraftWaterBrewing(WBRecipeType recipe,
                                                CatalystCauldronBlockEntity be,
                                                Level level)
    {
        be.consumeIngredients(recipe.getIngredients());
        be.getFluidTank().drain(recipe.getFluidAmount(), IFluidHandler.FluidAction.EXECUTE);

        ItemStack result = recipe.getResultItem(level.registryAccess());

        BlockPos pos = be.getBlockPos();
        Containers.dropItemStack(level, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, result);

        be.resetProgressExternally();

        level.playSound(null, pos, SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS, 1.0f, 1.0f);
    }

    public static void performCraftLavaBrewing(LBRecipeType recipe, CatalystCauldronBlockEntity be, Level level)
    {
        be.consumeIngredients(recipe.getIngredients());
        be.getFluidTank().drain(recipe.getFluidAmount(), IFluidHandler.FluidAction.EXECUTE);

        ItemStack result = recipe.getResultItem(level.registryAccess());

        BlockPos pos = be.getBlockPos();
        Containers.dropItemStack(level, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, result);

        be.resetProgressExternally();
        level.playSound(null, pos, SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS, 1.0f, 1.0f);
    }

    public static void performCraftUniversalBrewing(UBRecipeType recipe,
                                                    CatalystCauldronBlockEntity be,
                                                    Level level)
    {
        be.consumeIngredients(recipe.getIngredients());
        be.getFluidTank().drain(recipe.getFluidAmount(), IFluidHandler.FluidAction.EXECUTE);

        ItemStack result = recipe.getResultItem(level.registryAccess());

        BlockPos pos = be.getBlockPos();
        Containers.dropItemStack(level, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, result);

        be.resetProgressExternally();
        level.playSound(null, pos, SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS, 1.0f, 1.0f);
    }

    public static void performCraftFluidBrewing(FBRecipeType recipe,
                                                CatalystCauldronBlockEntity be,
                                                Level level)
    {
        be.consumeIngredients(recipe.getIngredients());

        be.getFluidTank().drain(recipe.getFluidAmount(), IFluidHandler.FluidAction.EXECUTE);

        FluidStack result = recipe.getResultFluid();
        be.getFluidTank().fill(result.copy(), IFluidHandler.FluidAction.EXECUTE);

        be.resetProgressExternally();

        BlockPos pos = be.getBlockPos();
        level.playSound(null, pos, SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS, 1.0f, 1.0f);
    }

    public static void performCraftReactionBrewing(RBRecipeItemType recipe,
                                                   CatalystCauldronBlockEntity be,
                                                   Level level)
    {
        be.consumeIngredients(recipe.getOrderedIngredients());

        if (recipe.getFluidInputId() != null)
        {
            be.getFluidTank().drain(recipe.getFluidAmount(), IFluidHandler.FluidAction.EXECUTE);
        }

        ItemStack result = recipe.getResultItem(level.registryAccess());
        BlockPos pos = be.getBlockPos();

        Containers.dropItemStack(level, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, result);

        be.resetProgressExternally();
        level.playSound(null, pos, SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS, 1.0f, 1.0f);
    }
}
