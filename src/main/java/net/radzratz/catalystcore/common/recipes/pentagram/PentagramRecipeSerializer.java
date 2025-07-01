package net.radzratz.catalystcore.common.recipes.pentagram;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.NotNull;

public class PentagramRecipeSerializer implements RecipeSerializer<PentagramJsonRecipe>
{
    public static final MapCodec<PentagramJsonRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Ingredient.CODEC.listOf().fieldOf("ingredients").forGetter(PentagramJsonRecipe::getIngredients),
                    ItemStack.CODEC.fieldOf("result").forGetter(r -> r.getResultItem(null))
            ).apply(instance, PentagramJsonRecipe::new)
    );

    @Override
    public @NotNull StreamCodec<RegistryFriendlyByteBuf, PentagramJsonRecipe> streamCodec()
    {
        return StreamCodec.composite(
                Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()),
                PentagramJsonRecipe::getIngredients,
                ItemStack.STREAM_CODEC,
                r -> r.getResultItem(null),
                PentagramJsonRecipe::new
        );
    }

    @Override
    public @NotNull MapCodec<PentagramJsonRecipe> codec()
    {
        return CODEC;
    }
}