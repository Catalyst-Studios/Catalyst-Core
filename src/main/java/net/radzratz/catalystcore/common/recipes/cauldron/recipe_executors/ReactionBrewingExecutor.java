package net.radzratz.catalystcore.common.recipes.cauldron.recipe_executors;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidStack;
import net.radzratz.catalystcore.client.blocks.entity.cauldron.CatalystCauldronBlockEntity;
import net.radzratz.catalystcore.common.recipes.CTCERecipeTypes;
import net.radzratz.catalystcore.common.recipes.cauldron.types.ReactionBrewingFluidRecipeType;
import net.radzratz.catalystcore.common.recipes.cauldron.types.ReactionBrewingItemRecipeType;
import net.radzratz.catalystcore.common.recipes.cauldron.util.inputs.CauldronInput;

import java.util.List;
import java.util.Optional;

import static net.radzratz.catalystcore.common.recipes.cauldron.util.helper.CauldronExecutorHelpers.performCraftReactionBrewingFluid;
import static net.radzratz.catalystcore.common.recipes.cauldron.util.helper.CauldronExecutorHelpers.performCraftReactionBrewingItem;

@SuppressWarnings("all")
public class ReactionBrewingExecutor
{
    /**
     * Executes one tick of the Reaction Brewing Item recipe process.
     * Validates inputs, checks heat requirements, and triggers explosion if inputs are incorrect.
     *
     * @param be    The Catalyst Cauldron Block Entity instance.
     * @param level The world where the cauldron exists.
     * @return true if processing occurred or completed; false if invalid or halted.
     */
    public static boolean tickReactionBrewingItem(CatalystCauldronBlockEntity be, Level level)
    {
        if(be.getFluidTank().isEmpty())
        {
            be.resetProgressExternally();
            return false;
        }

        List<ItemStack> items = be.getItemInputs();
        FluidStack fluid = be.getFluidTank().getFluidInTank(0);
        CauldronInput input = CauldronInput.of(items, List.of(fluid));

        Optional<RecipeHolder<ReactionBrewingItemRecipeType>> recipeOpt = level.getRecipeManager()
                .getRecipeFor(CTCERecipeTypes.REACTION_BREWING_ITEM.get(), input, level);

        if(recipeOpt.isEmpty())
        {
            be.resetProgressExternally();
            return false;
        }

        ReactionBrewingItemRecipeType recipe = recipeOpt.get().value();

        if(recipe.requiresHeat() && !be.hasValidHeatSource())
        {
            be.resetProgressExternally();
            return false;
        }

        if(!matchesInExactOrderItem(recipe, input))
        {
            if(input.items().size() == recipe.getOrderedIngredients().size())
            {
                triggerExplosion(be, level);
            }
            be.resetProgressExternally();
            return false;
        }

        be.incrementProgressExternally();

        if(be.getProgress() >= recipe.getCookingTime())
        {
            performCraftReactionBrewingItem(recipe, be, level);
        }

        be.setChanged();
        return true;
    }

    /**
     * Checks whether the input matches the expected ingredients in the exact order for item output recipes.
     *
     * @param recipe The ReactionBrewingItemRecipeType recipe to check against.
     * @param input  The cauldron input (items + fluids).
     * @return true if input matches expected ingredient sequence and fluid; false otherwise.
     */
    private static boolean matchesInExactOrderItem(ReactionBrewingItemRecipeType recipe, CauldronInput input)
    {
        ResourceLocation expectedFluidId = recipe.getFluidInputId();
        List<FluidStack> fluids = input.fluids();
        List<Ingredient> expected = recipe.getOrderedIngredients();
        List<ItemStack> items = input.items();

        if(expectedFluidId != null)
        {
            if(fluids.isEmpty()) return false;

            FluidStack fluid = fluids.get(0);
            if(!BuiltInRegistries.FLUID.getKey(fluid.getFluid()).equals(expectedFluidId)) return false;
            if(fluid.getAmount() < recipe.getFluidAmount()) return false;
        }

        if(items.size() != expected.size()) return false;

        for(int i = 0; i < items.size(); i++)
        {
            if(!expected.get(i).test(items.get(i))) return false;
        }

        return true;
    }

    /**
     * Executes one tick of the Reaction Brewing Fluid recipe process.
     * Similar to item variant, but outputs a FluidStack instead of ItemStack.
     *
     * @param be    The Catalyst Cauldron Block Entity instance.
     * @param level The world where the cauldron exists.
     * @return true if processing occurred or completed; false if invalid or halted.
     */
    public static boolean tickReactionBrewingFluid(CatalystCauldronBlockEntity be, Level level)
    {
        if(be.getFluidTank().isEmpty())
        {
            be.resetProgressExternally();
            return false;
        }

        List<ItemStack> items = be.getItemInputs();

        if(items.isEmpty())
        {
            be.resetProgressExternally();
            return false;
        }

        FluidStack fluid = be.getFluidTank().getFluidInTank(0);
        CauldronInput input = CauldronInput.of(items, List.of(fluid));

        Optional<RecipeHolder<ReactionBrewingFluidRecipeType>> recipeOpt = level.getRecipeManager()
                .getRecipeFor(CTCERecipeTypes.REACTION_BREWING_FLUID.get(), input, level);

        if(recipeOpt.isEmpty())
        {
            be.resetProgressExternally();
            return false;
        }

        ReactionBrewingFluidRecipeType recipe = recipeOpt.get().value();

        if(recipe.requiresHeat() && !be.hasValidHeatSource())
        {
            be.resetProgressExternally();
            return false;
        }

        if(!matchesInExactOrderFluid(recipe, input))
        {
            if(input.items().size() == recipe.getOrderedIngredients().size())
            {
                triggerExplosion(be, level);
            }
            be.resetProgressExternally();
            return false;
        }

        be.incrementProgressExternally();

        if(be.getProgress() >= recipe.getCookingTime())
        {
            performCraftReactionBrewingFluid(recipe, be, level);
        }

        be.setChanged();
        return true;
    }

    /**
     * Checks whether the input matches the expected ingredients in the exact order for fluid output recipes.
     *
     * @param recipe The ReactionBrewingFluidRecipeType recipe to check against.
     * @param input  The cauldron input (items + fluids).
     * @return true if input matches expected ingredient sequence and fluid; false otherwise.
     */
    private static boolean matchesInExactOrderFluid(ReactionBrewingFluidRecipeType recipe, CauldronInput input)
    {
        ResourceLocation fluidId = recipe.getFluidInputId();
        List<Ingredient> expected = recipe.getOrderedIngredients();

        List<ItemStack> items = input.items();
        List<FluidStack> fluids = input.fluids();

        if(fluidId != null)
        {
            if(fluids.isEmpty()) return false;
            FluidStack fs = fluids.get(0);
            if(!BuiltInRegistries.FLUID.getKey(fs.getFluid()).equals(fluidId) || fs.getAmount() < recipe.getFluidAmount())
            {
                return false;
            }
        }

        if(items.size() != expected.size()) return false;

        for(int i = 0; i < items.size(); i++)
        {
            if(!expected.get(i).test(items.get(i)))
            {
                return false;
            }
        }

        return true;
    }

    /**
     * Triggers a lovely explosion centered on the cauldron.
     * Damages nearby players and destroys blocks in the surrounding area.
     * Shared between both Item and Fluid variants.
     *
     * @param be    The Catalyst Cauldron Block Entity.
     * @param level The world context.
     */
    private static void triggerExplosion(CatalystCauldronBlockEntity be, Level level)
    {
        BlockPos pos = be.getBlockPos();

        Player nearest = level.getNearestPlayer(pos.getX(), pos.getY(), pos.getZ(), 15.0, false);
        if(nearest != null)
        {
            nearest.hurt(level.damageSources().generic(), 12.0F);
        }

        level.explode(
                null,
                pos.getX() + 0.5,
                pos.getY() + 0.5,
                pos.getZ() + 0.5,
                10F,
                Level.ExplosionInteraction.BLOCK);
    }
}
