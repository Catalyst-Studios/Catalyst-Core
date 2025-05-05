package net.radzratz.catalystcore.recipes.pentagram;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PentagramRecipeSerializer implements RecipeSerializer<PentagramJsonRecipe>
{
    public static final MapCodec<PentagramJsonRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Ingredient.CODEC.listOf().fieldOf("inputs").forGetter(r -> r.ingredient),
                    ItemStack.CODEC.fieldOf("output").forGetter(r -> r.result)
            ).apply(instance, PentagramJsonRecipe::new)
    );

    @Override
    public @NotNull StreamCodec<RegistryFriendlyByteBuf, PentagramJsonRecipe> streamCodec()
    {
        return StreamCodec.of(
                (buf, recipe) ->
                {
                    Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.ingredient.getFirst());
                    ItemStack.STREAM_CODEC.encode(buf, recipe.result);
                },
                buf -> new PentagramJsonRecipe(
                        List.of(Ingredient.CONTENTS_STREAM_CODEC.decode(buf)),
                        ItemStack.STREAM_CODEC.decode(buf)
                )
        );
    }

    @Override
    public @NotNull MapCodec<PentagramJsonRecipe> codec()
    {
        return CODEC;
    }
}