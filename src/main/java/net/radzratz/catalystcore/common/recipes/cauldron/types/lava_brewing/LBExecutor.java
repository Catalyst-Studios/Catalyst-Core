package net.radzratz.catalystcore.common.recipes.cauldron.types.lava_brewing;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.radzratz.catalystcore.client.blocks.entity.cauldron.CatalystCauldronBlockEntity;
import net.radzratz.catalystcore.common.recipes.CTCERecipeTypes;
import net.radzratz.catalystcore.common.recipes.cauldron.util.inputs.CauldronInput;

import java.util.List;
import java.util.Optional;

import static net.radzratz.catalystcore.common.recipes.cauldron.util.helper.CauldronExecutorHelpers.performCraftLavaBrewing;

public class LBExecutor
{
    public static boolean tickLavaBrewing(CatalystCauldronBlockEntity be, Level level)
    {
        if(be.getFluidTank().isEmpty())
        {
            be.resetProgressExternally();
            return false;
        }

        List<ItemStack> items = be.getItemInputs();
        CauldronInput inputs = CauldronInput.of(items, List.of(be.getFluidTank().getFluidInTank(0)));

        Optional<RecipeHolder<LBRecipeType>> recipeOpt = level.getRecipeManager()
                .getRecipeFor(CTCERecipeTypes.LAVA_BREWING.get(), inputs, level);

        if(recipeOpt.isEmpty())
        {
            be.resetProgressExternally();
            return false;
        }

        LBRecipeType recipe = recipeOpt.get().value();

        if(!recipe.matches(inputs, level))
        {
            be.resetProgressExternally();
            return false;
        }

        be.incrementProgressExternally();

        if(be.getProgress() >= recipe.getCookingTime())
        {
            performCraftLavaBrewing(recipe, be, level);
        }
        be.setChanged();
        return true;
    }
}
