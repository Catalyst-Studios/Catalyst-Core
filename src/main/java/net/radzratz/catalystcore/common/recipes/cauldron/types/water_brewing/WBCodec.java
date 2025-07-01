package net.radzratz.catalystcore.common.recipes.cauldron.types.water_brewing;

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

public class WBCodec extends WBRecipeType
{
    public WBCodec(List<Ingredient> ingredients,
                   ItemStack output,
                   int fluidAmount,
                   int cookingTime,
                   boolean requiresHeat)
    {
        super(ingredients, output, fluidAmount, cookingTime, requiresHeat);
    }

    public static final MapCodec<WBRecipeType> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Ingredient.CODEC.listOf().fieldOf("ingredients").forGetter(WBRecipeType::getIngredients),
            ItemStack.CODEC.fieldOf("output").forGetter(WBRecipeType::getOutput),
            Codec.INT.fieldOf("fluidAmount").forGetter(WBRecipeType::getFluidAmount),
            Codec.INT.fieldOf("cookingTime").forGetter(WBRecipeType::getCookingTime),
            Codec.BOOL.optionalFieldOf("requiresHeat", true).forGetter(WBRecipeType::requiresHeat)
    ).apply(instance, WBCodec::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, WBRecipeType> STREAM_CODEC =
            StreamCodec.composite(
                    Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list(4)),
                    WBRecipeType::getIngredients,
                    ItemStack.STREAM_CODEC, WBRecipeType::getOutput,
                    ByteBufCodecs.INT, WBRecipeType::getFluidAmount,
                    ByteBufCodecs.INT, WBRecipeType::getCookingTime,
                    ByteBufCodecs.BOOL, WBRecipeType::requiresHeat,
                    WBCodec::new);

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
