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
import net.radzratz.catalystcore.common.recipes.cauldron.types.FluidBrewingRecipeType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class FluidBrewingCodec extends FluidBrewingRecipeType
{
    public FluidBrewingCodec(List<Ingredient> ingredients,
                             FluidStack result,
                             int fluidAmount,
                             int cookingTime,
                             boolean requiresHeat,
                             @Nullable ResourceLocation fluidId)
    {
        super(ingredients, result, fluidAmount, cookingTime, requiresHeat, fluidId);
    }

    public static final MapCodec<FluidBrewingRecipeType> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                    Ingredient.CODEC.listOf().fieldOf("ingredients").forGetter(FluidBrewingRecipeType::getIngredients),
                    FluidStack.CODEC.fieldOf("output").forGetter(FluidBrewingRecipeType::getResultFluid),
                    Codec.INT.fieldOf("fluidAmount").forGetter(FluidBrewingRecipeType::getFluidAmount),
                    Codec.INT.fieldOf("cookingTime").forGetter(FluidBrewingRecipeType::getCookingTime),
                    Codec.BOOL.optionalFieldOf("requiresHeat", true).forGetter(FluidBrewingRecipeType::requiresHeat),
                    ResourceLocation.CODEC.optionalFieldOf("fluid").forGetter(recipe -> Optional.ofNullable(recipe.getFluidId()))
            ).apply(instance, (ingredients, result, fluidAmount, cookingTime, requiresHeat, fluidOpt) ->
                    new FluidBrewingCodec(ingredients, result, fluidAmount, cookingTime, requiresHeat, fluidOpt.orElse(null)))
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, FluidBrewingRecipeType> STREAM_CODEC =
            StreamCodec.composite(
                    Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list(4)), FluidBrewingRecipeType::getIngredients,
                    FluidStack.STREAM_CODEC, FluidBrewingRecipeType::getResultFluid,
                    ByteBufCodecs.INT, FluidBrewingRecipeType::getFluidAmount,
                    ByteBufCodecs.INT, FluidBrewingRecipeType::getCookingTime,
                    ByteBufCodecs.BOOL, FluidBrewingRecipeType::requiresHeat,
                    ByteBufCodecs.optional(ResourceLocation.STREAM_CODEC),
                    recipe -> Optional.ofNullable(recipe.getFluidId()),
                    (ingredients, result, amount, time, heat, fluidOpt) ->
                            new FluidBrewingCodec(ingredients, result, amount, time, heat, fluidOpt.orElse(null))
            );

    @Override
    public @NotNull RecipeSerializer<?> getSerializer()
    {
        return CTCERecipeTypes.FLUID_BREWING_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<?> getType()
    {
        return CTCERecipeTypes.FLUID_BREWING.get();
    }
}
