package net.radzratz.catalystcore.common.recipes.cauldron.recipe_executors;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.radzratz.catalystcore.client.blocks.entity.cauldron.CatalystCauldronBlockEntity;
import net.radzratz.catalystcore.common.recipes.CTCERecipeTypes;
import net.radzratz.catalystcore.common.recipes.cauldron.types.UniversalBrewingRecipeType;
import net.radzratz.catalystcore.common.recipes.cauldron.util.inputs.CauldronInput;

import java.util.List;
import java.util.Optional;

import static net.radzratz.catalystcore.common.recipes.cauldron.util.helper.CauldronExecutorHelpers.performCraftUniversalBrewing;

public class UniversalBrewingExecutor
{
    public static boolean tickUniversalBrewing(CatalystCauldronBlockEntity be, Level level)
    {
        if(be.getFluidTank().isEmpty())
        {
            be.resetProgressExternally();
            return false;
        }

        List<ItemStack> items = be.getItemInputs();
        CauldronInput inputs = CauldronInput.of(items, List.of(be.getFluidTank().getFluidInTank(0)));

        Optional<RecipeHolder<UniversalBrewingRecipeType>> recipeOpt = level.getRecipeManager()
                .getRecipeFor(CTCERecipeTypes.UNIVERSAL_BREWING.get(), inputs, level);

        if(recipeOpt.isEmpty())
        {
            be.resetProgressExternally();
            return false;
        }

        UniversalBrewingRecipeType recipe = recipeOpt.get().value();

        if(recipe.requiresHeat() && !be.hasValidHeatSource())
        {
            be.resetProgressExternally();
            return false;
        }

        if(!recipe.matches(inputs, level))
        {
            be.resetProgressExternally();
            return false;
        }

        be.incrementProgressExternally();

        if(be.getProgress() >= recipe.getCookingTime())
        {
            performCraftUniversalBrewing(recipe, be, level);
        }

        be.setChanged();
        return true;
    }
}
