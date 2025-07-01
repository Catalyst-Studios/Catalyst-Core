package net.radzratz.catalystcore.common.recipes.serializer;

import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("all")
public class CTCERecipeSerializer<T extends Recipe<?>> implements RecipeSerializer<T>
{
    private final MapCodec<T> codec;
    private final StreamCodec<RegistryFriendlyByteBuf, T> streamCodec;

    public CTCERecipeSerializer(MapCodec<T> codec, StreamCodec<RegistryFriendlyByteBuf, T> streamCodec)
    {
        this.codec = codec;
        this.streamCodec = streamCodec;
    }

    public static <T extends Recipe<?>> CTCERecipeSerializer<T> of(MapCodec<T> codec, StreamCodec<RegistryFriendlyByteBuf, T> streamCodec)
    {
        return new CTCERecipeSerializer<>(codec, streamCodec);
    }

    @Override
    public @NotNull MapCodec<T> codec()
    {
        return codec;
    }

    @Override
    public @NotNull StreamCodec<RegistryFriendlyByteBuf, T> streamCodec()
    {
        return streamCodec;
    }
}
