package net.radzratz.catalystcore.common.recipes.cauldron.types.lava_brewing;

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
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LBCodec extends LBRecipeType
{
    public LBCodec(List<Ingredient> ingredients, ItemStack output, int fluidAmount, int cookingTime)
    {
        super(ingredients, output, fluidAmount, cookingTime);
    }

    public static final MapCodec<LBRecipeType> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Ingredient.CODEC.listOf().fieldOf("ingredients").forGetter(LBRecipeType::getIngredients),
            ItemStack.CODEC.fieldOf("output").forGetter(LBRecipeType::getOutput),
            Codec.INT.fieldOf("fluidAmount").forGetter(LBRecipeType::getFluidAmount),
            Codec.INT.fieldOf("cookingTime").forGetter(LBRecipeType::getCookingTime)
    ).apply(instance, LBCodec::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, LBRecipeType> STREAM_CODEC =
            StreamCodec.composite(
                    Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list(4)), LBRecipeType::getIngredients,
                    ItemStack.STREAM_CODEC, LBRecipeType::getOutput,
                    ByteBufCodecs.INT, LBRecipeType::getFluidAmount,
                    ByteBufCodecs.INT, LBRecipeType::getCookingTime,
                    LBCodec::new
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
