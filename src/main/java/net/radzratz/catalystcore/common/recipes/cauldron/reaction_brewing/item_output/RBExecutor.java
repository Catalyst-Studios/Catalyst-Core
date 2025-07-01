package net.radzratz.catalystcore.common.recipes.cauldron.reaction_brewing.item_output;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.radzratz.catalystcore.client.blocks.entity.cauldron.CatalystCauldronBlockEntity;
import net.radzratz.catalystcore.common.recipes.CTCERecipeTypes;
import net.radzratz.catalystcore.common.recipes.cauldron.util.inputs.CauldronInput;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("all")
public class RBExecutor
{
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

        Optional<RecipeHolder<RBRecipeItemType>> recipeOpt = level.getRecipeManager()
                .getRecipeFor(CTCERecipeTypes.REACTION_BREWING_ITEM.get(), input, level);

        if(recipeOpt.isEmpty())
        {
            be.resetProgressExternally();
            return false;
        }

        RBRecipeItemType recipe = recipeOpt.get().value();

        if(recipe.requiresHeat() && !be.hasValidHeatSource())
        {
            be.resetProgressExternally();
            return false;
        }

        if(!matchesInExactOrder(recipe, input))
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
            performCraftReactionBrewing(recipe, be, level);
        }

        be.setChanged();
        return true;
    }

    public static void performCraftReactionBrewing(RBRecipeItemType recipe,
                                                   CatalystCauldronBlockEntity be,
                                                   Level level)
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

    private static boolean matchesInExactOrder(RBRecipeItemType recipe, CauldronInput input)
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

        if (items.size() != expected.size()) return false;

        for(int i = 0; i < items.size(); i++)
        {
            if(!expected.get(i).test(items.get(i))) return false;
        }

        return true;
    }

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
