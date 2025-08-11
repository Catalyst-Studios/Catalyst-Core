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
import net.radzratz.catalystcore.common.recipes.cauldron.types.LavaBrewingRecipeType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LavaBrewingCodec extends LavaBrewingRecipeType
{
    public LavaBrewingCodec(List<Ingredient> ingredients, ItemStack output, int fluidAmount, int cookingTime)
    {
        super(ingredients, output, fluidAmount, cookingTime);
    }

    public static final MapCodec<LavaBrewingRecipeType> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Ingredient.CODEC.listOf().fieldOf("ingredients").forGetter(LavaBrewingRecipeType::getIngredients),
            ItemStack.CODEC.fieldOf("output").forGetter(LavaBrewingRecipeType::getOutput),
            Codec.INT.fieldOf("fluidAmount").forGetter(LavaBrewingRecipeType::getFluidAmount),
            Codec.INT.fieldOf("cookingTime").forGetter(LavaBrewingRecipeType::getCookingTime)
    ).apply(instance, LavaBrewingCodec::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, LavaBrewingRecipeType> STREAM_CODEC =
            StreamCodec.composite(
                    Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list(4)), LavaBrewingRecipeType::getIngredients,
                    ItemStack.STREAM_CODEC, LavaBrewingRecipeType::getOutput,
                    ByteBufCodecs.INT, LavaBrewingRecipeType::getFluidAmount,
                    ByteBufCodecs.INT, LavaBrewingRecipeType::getCookingTime,
                    LavaBrewingCodec::new
            );

    @Override
    public @NotNull RecipeSerializer<?> getSerializer()
    {
        return CTCERecipeTypes.LAVA_BREWING_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<?> getType()
    {
        return CTCERecipeTypes.LAVA_BREWING.get();
    }
}
