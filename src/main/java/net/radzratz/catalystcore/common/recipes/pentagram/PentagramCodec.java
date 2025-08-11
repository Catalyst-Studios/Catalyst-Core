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

public class PentagramCodec implements RecipeSerializer<PentagramRecipeType>
{
    public static final MapCodec<PentagramRecipeType> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Ingredient.CODEC.listOf().fieldOf("ingredients").forGetter(PentagramRecipeType::getIngredients),
                    ItemStack.CODEC.fieldOf("result").forGetter(r -> r.getResultItem(null))
            ).apply(instance, PentagramRecipeType::new)
    );

    @Override
    public @NotNull StreamCodec<RegistryFriendlyByteBuf, PentagramRecipeType> streamCodec()
    {
        return StreamCodec.composite(
                Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()),
                PentagramRecipeType::getIngredients,
                ItemStack.STREAM_CODEC,
                r -> r.getResultItem(null),
                PentagramRecipeType::new
        );
    }

    @Override
    public @NotNull MapCodec<PentagramRecipeType> codec()
    {
        return CODEC;
    }
}