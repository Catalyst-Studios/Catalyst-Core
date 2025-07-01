package net.radzratz.catalystcore.common.recipes.cauldron.types.universal_brewing;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.radzratz.catalystcore.common.recipes.CTCERecipeTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class UBCodec extends UBRecipeType
{
    public UBCodec(List<Ingredient> ingredients,
                   ItemStack output,
                   int fluidAmount,
                   int cookingTime,
                   boolean requiresHeat,
                   @Nullable ResourceLocation fluidId)
    {
        super(ingredients, output, fluidAmount, cookingTime, requiresHeat, fluidId);
    }

    public static final MapCodec<UBRecipeType> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                    Ingredient.CODEC.listOf().fieldOf("ingredients").forGetter(UBRecipeType::getIngredients),
                    ItemStack.CODEC.fieldOf("output").forGetter(UBRecipeType::getOutput),
                    Codec.INT.fieldOf("fluidAmount").forGetter(UBRecipeType::getFluidAmount),
                    Codec.INT.fieldOf("cookingTime").forGetter(UBRecipeType::getCookingTime),
                    Codec.BOOL.optionalFieldOf("requiresHeat", true).forGetter(UBRecipeType::requiresHeat),
                    ResourceLocation.CODEC.optionalFieldOf("fluid").forGetter(recipe -> Optional.ofNullable(recipe.getFluidId()))
            ).apply(instance, (ingredients, output, amount, time, heat, fluidOpt) ->
                    new UBCodec(ingredients, output, amount, time, heat, fluidOpt.orElse(null)))
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, UBRecipeType> STREAM_CODEC =
            StreamCodec.composite(
                    Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list(4)),
                    UBRecipeType::getIngredients,
                    ItemStack.STREAM_CODEC, UBRecipeType::getOutput,
                    ByteBufCodecs.INT, UBRecipeType::getFluidAmount,
                    ByteBufCodecs.INT, UBRecipeType::getCookingTime,
                    ByteBufCodecs.BOOL, UBRecipeType::requiresHeat,
                    ByteBufCodecs.optional(ResourceLocation.STREAM_CODEC),
                    recipe -> Optional.ofNullable(recipe.getFluidId()),
                    (ingredients, output, fluidAmount, cookingTime, requiresHeat, fluidOpt) ->
                    new UBCodec(ingredients, output, fluidAmount, cookingTime, requiresHeat, fluidOpt.orElse(null))
            );

    @Override
    public @NotNull RecipeSerializer<?> getSerializer()
    {
        return CTCERecipeTypes.UNIVERSAL_BREWING_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<?> getType()
    {
        return CTCERecipeTypes.UNIVERSAL_BREWING.get();
    }
}
