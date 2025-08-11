package net.radzratz.catalystcore.common.recipes.cauldron.codecs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.fluids.FluidStack;
import net.radzratz.catalystcore.common.recipes.CTCERecipeTypes;
import net.radzratz.catalystcore.common.recipes.cauldron.types.ReactionBrewingFluidRecipeType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class ReactionFluidBrewingCodec extends ReactionBrewingFluidRecipeType
{
    public ReactionFluidBrewingCodec(List<Ingredient> orderedIngredients, FluidStack result,
                                     int fluidAmount, int cookingTime, boolean requiresHeat,
                                     @Nullable ResourceLocation fluidInputId)
    {
        super(orderedIngredients, result, fluidAmount, cookingTime, requiresHeat, fluidInputId);
    }

    public static final MapCodec<ReactionBrewingFluidRecipeType> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                    Ingredient.CODEC.listOf().fieldOf("ingredients").forGetter(ReactionBrewingFluidRecipeType::getOrderedIngredients),
                    FluidStack.CODEC.fieldOf("output").forGetter(ReactionBrewingFluidRecipeType::getFluidResult),
                    Codec.INT.fieldOf("fluidAmount").forGetter(ReactionBrewingFluidRecipeType::getFluidAmount),
                    Codec.INT.fieldOf("cookingTime").forGetter(ReactionBrewingFluidRecipeType::getCookingTime),
                    Codec.BOOL.optionalFieldOf("requiresHeat", true).forGetter(ReactionBrewingFluidRecipeType::requiresHeat),
                    ResourceLocation.CODEC.optionalFieldOf("fluid").forGetter(r -> Optional.ofNullable(r.getFluidInputId()))
            ).apply(instance, (ingredients, output, fluidAmount, cookingTime, requiresHeat, fluidOpt) ->
                    new ReactionFluidBrewingCodec(ingredients, output, fluidAmount, cookingTime, requiresHeat, fluidOpt.orElse(null)))
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, ReactionBrewingFluidRecipeType> STREAM_CODEC =
            StreamCodec.composite(
                    Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list(4)), ReactionBrewingFluidRecipeType::getOrderedIngredients,
                    FluidStack.STREAM_CODEC, ReactionBrewingFluidRecipeType::getFluidResult,
                    ByteBufCodecs.INT, ReactionBrewingFluidRecipeType::getFluidAmount,
                    ByteBufCodecs.INT, ReactionBrewingFluidRecipeType::getCookingTime,
                    ByteBufCodecs.BOOL, ReactionBrewingFluidRecipeType::requiresHeat,
                    ByteBufCodecs.optional(ResourceLocation.STREAM_CODEC),
                    r -> Optional.ofNullable(r.getFluidInputId()),
                    (ingredients, output, fluidAmount, cookingTime, requiresHeat, fluidOpt) ->
                            new ReactionFluidBrewingCodec(ingredients, output, fluidAmount, cookingTime, requiresHeat, fluidOpt.orElse(null))
            );

    @Override
    public @NotNull RecipeSerializer<?> getSerializer()
    {
        return CTCERecipeTypes.REACTION_BREWING_FLUID_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<?> getType()
    {
        return CTCERecipeTypes.REACTION_BREWING_FLUID.get();
    }
}
