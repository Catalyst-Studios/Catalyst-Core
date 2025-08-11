package net.radzratz.catalystcore.common.recipes.cauldron.codecs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.radzratz.catalystcore.common.recipes.CTCERecipeTypes;
import net.radzratz.catalystcore.common.recipes.cauldron.types.WaterBrewingRecipeType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WaterBrewingCodec extends WaterBrewingRecipeType
{
    public WaterBrewingCodec(List<Ingredient> ingredients,
                             ItemStack output,
                             int fluidAmount,
                             int cookingTime,
                             boolean requiresHeat)
    {
        super(ingredients, output, fluidAmount, cookingTime, requiresHeat);
    }

    public static final MapCodec<WaterBrewingRecipeType> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Ingredient.CODEC.listOf().fieldOf("ingredients").forGetter(WaterBrewingRecipeType::getIngredients),
            ItemStack.CODEC.fieldOf("output").forGetter(WaterBrewingRecipeType::getOutput),
            Codec.INT.fieldOf("fluidAmount").forGetter(WaterBrewingRecipeType::getFluidAmount),
            Codec.INT.fieldOf("cookingTime").forGetter(WaterBrewingRecipeType::getCookingTime),
            Codec.BOOL.optionalFieldOf("requiresHeat", true).forGetter(WaterBrewingRecipeType::requiresHeat)
    ).apply(instance, WaterBrewingCodec::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, WaterBrewingRecipeType> STREAM_CODEC =
            StreamCodec.composite(
                    Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list(4)), WaterBrewingRecipeType::getIngredients,
                    ItemStack.STREAM_CODEC, WaterBrewingRecipeType::getOutput, ByteBufCodecs.INT, WaterBrewingRecipeType::getFluidAmount,
                    ByteBufCodecs.INT, WaterBrewingRecipeType::getCookingTime, ByteBufCodecs.BOOL, WaterBrewingRecipeType::requiresHeat,
                    WaterBrewingCodec::new);

    @Override
    public @NotNull RecipeSerializer<?> getSerializer()
    {
        return CTCERecipeTypes.WATER_BREWING_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<?> getType()
    {
        return CTCERecipeTypes.WATER_BREWING.get();
    }
}
