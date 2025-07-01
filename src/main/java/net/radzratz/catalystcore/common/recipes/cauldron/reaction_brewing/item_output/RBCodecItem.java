package net.radzratz.catalystcore.common.recipes.cauldron.reaction_brewing.item_output;

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

public class RBCodecItem extends RBRecipeItemType
{
    public RBCodecItem(List<Ingredient> orderedIngredients,
                       ItemStack result,
                       int fluidAmount,
                       int cookingTime,
                       boolean requiresHeat,
                       @Nullable ResourceLocation fluidInputId)
    {
        super(orderedIngredients, result, fluidAmount, cookingTime, requiresHeat, fluidInputId);
    }

    public static final MapCodec<RBRecipeItemType> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                    Ingredient.CODEC.listOf().fieldOf("ingredients").forGetter(RBRecipeItemType::getOrderedIngredients),
                    ItemStack.CODEC.fieldOf("output").forGetter(RBRecipeItemType::getResult),
                    Codec.INT.fieldOf("fluidAmount").forGetter(RBRecipeItemType::getFluidAmount),
                    Codec.INT.fieldOf("cookingTime").forGetter(RBRecipeItemType::getCookingTime),
                    Codec.BOOL.optionalFieldOf("requiresHeat", true).forGetter(RBRecipeItemType::requiresHeat),
                    ResourceLocation.CODEC.optionalFieldOf("fluid").forGetter(r -> Optional.ofNullable(r.getFluidInputId()))
            ).apply(instance, (ingredients, output, fluidAmount, cookingTime, requiresHeat, fluidOpt) ->
                    new RBCodecItem(ingredients, output, fluidAmount, cookingTime, requiresHeat, fluidOpt.orElse(null)))
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, RBRecipeItemType> STREAM_CODEC =
            StreamCodec.composite(
                    Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list(4)),
                    RBRecipeItemType::getOrderedIngredients,
                    ItemStack.STREAM_CODEC,
                    RBRecipeItemType::getResult,
                    ByteBufCodecs.INT,
                    RBRecipeItemType::getFluidAmount,
                    ByteBufCodecs.INT,
                    RBRecipeItemType::getCookingTime,
                    ByteBufCodecs.BOOL,
                    RBRecipeItemType::requiresHeat,
                    ByteBufCodecs.optional(ResourceLocation.STREAM_CODEC),
                    r -> Optional.ofNullable(r.getFluidInputId()),
                    (ingredients, output, fluidAmount, cookingTime, requiresHeat, fluidOpt) ->
                            new RBCodecItem(ingredients, output, fluidAmount, cookingTime, requiresHeat, fluidOpt.orElse(null))
            );

    @Override
    public @NotNull RecipeSerializer<?> getSerializer()
    {
        return CTCERecipeTypes.REACTION_BREWING_ITEM_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<?> getType()
    {
        return CTCERecipeTypes.REACTION_BREWING_ITEM.get();
    }
}
