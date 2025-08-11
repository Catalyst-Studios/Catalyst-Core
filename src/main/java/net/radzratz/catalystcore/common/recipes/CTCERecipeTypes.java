package net.radzratz.catalystcore.common.recipes;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.radzratz.catalystcore.common.recipes.cauldron.codecs.*;
import net.radzratz.catalystcore.common.recipes.cauldron.types.*;
import net.radzratz.catalystcore.common.recipes.pentagram.PentagramRecipeType;
import net.radzratz.catalystcore.common.recipes.pentagram.PentagramCodec;
import net.radzratz.catalystcore.common.recipes.serializer.CTCERecipeSerializer;

public class CTCERecipeTypes
{
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, "catalystcore");

    public static final DeferredRegister<RecipeType<?>> TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, "catalystcore");

    /// Pentagram
    public static final DeferredHolder<RecipeSerializer<?>, PentagramCodec> PENTAGRAM_SERIALIZER =
            SERIALIZERS.register("pentagram", PentagramCodec::new);

    public static final DeferredHolder<RecipeType<?>, RecipeType<PentagramRecipeType>> PENTAGRAM_TYPE =
            TYPES.register("pentagram", () -> new RecipeType<PentagramRecipeType>()
            {
                @Override
                public String toString()
                {
                    return "pentagram";
                }
            });

    /// WaterBrewing
    public static final DeferredHolder<RecipeType<?>, RecipeType<WaterBrewingRecipeType>> WATER_BREWING =
            TYPES.register("water_brewing", () -> new RecipeType<WaterBrewingRecipeType>()
            {
                @Override
                public String toString()
                {
                    return "water_brewing";
                }
            });

    public static final DeferredHolder<RecipeSerializer<?>, CTCERecipeSerializer<WaterBrewingRecipeType>> WATER_BREWING_SERIALIZER =
            SERIALIZERS.register("water_brewing", () -> CTCERecipeSerializer.of(WaterBrewingCodec.CODEC, WaterBrewingCodec.STREAM_CODEC));

    /// LavaBrewing
    public static final DeferredHolder<RecipeType<?>, RecipeType<LavaBrewingRecipeType>> LAVA_BREWING =
            TYPES.register("lava_brewing", () -> new RecipeType<LavaBrewingRecipeType>()
            {
                @Override
                public String toString()
                {
                    return "lava_brewing";
                }
            });

    public static final DeferredHolder<RecipeSerializer<?>, CTCERecipeSerializer<LavaBrewingRecipeType>> LAVA_BREWING_SERIALIZER =
            SERIALIZERS.register("lava_brewing", () -> CTCERecipeSerializer.of(LavaBrewingCodec.CODEC, LavaBrewingCodec.STREAM_CODEC));

    /// UniversalBrewing
    public static final DeferredHolder<RecipeType<?>, RecipeType<UniversalBrewingRecipeType>> UNIVERSAL_BREWING =
            TYPES.register("universal_brewing", () -> new RecipeType<UniversalBrewingRecipeType>()
            {
                @Override
                public String toString()
                {
                    return "universal_brewing";
                }
            });

    public static final DeferredHolder<RecipeSerializer<?>, CTCERecipeSerializer<UniversalBrewingRecipeType>> UNIVERSAL_BREWING_SERIALIZER =
            SERIALIZERS.register("universal_brewing", () -> CTCERecipeSerializer.of(UniversalBrewingCodec.CODEC, UniversalBrewingCodec.STREAM_CODEC));

    /// FluidBrewing
    public static final DeferredHolder<RecipeType<?>, RecipeType<FluidBrewingRecipeType>> FLUID_BREWING =
            TYPES.register("fluid_brewing", () -> new RecipeType<FluidBrewingRecipeType>()
            {
                @Override
                public String toString()
                {
                    return "fluid_brewing";
                }
            });

    public static final DeferredHolder<RecipeSerializer<?>, CTCERecipeSerializer<FluidBrewingRecipeType>> FLUID_BREWING_SERIALIZER =
            SERIALIZERS.register("fluid_brewing", () -> CTCERecipeSerializer.of(FluidBrewingCodec.CODEC, FluidBrewingCodec.STREAM_CODEC));

    /// Reaction Brewing Item/Fluid
    public static final DeferredHolder<RecipeType<?>, RecipeType<ReactionBrewingItemRecipeType>> REACTION_BREWING_ITEM =
            TYPES.register("reaction_item_brewing", () -> new RecipeType<ReactionBrewingItemRecipeType>()
            {
                @Override
                public String toString()
                {
                    return "reaction_item_brewing";
                }
            });

    public static final DeferredHolder<RecipeSerializer<?>, CTCERecipeSerializer<ReactionBrewingItemRecipeType>> REACTION_BREWING_ITEM_SERIALIZER =
            SERIALIZERS.register("reaction_item_brewing", () -> CTCERecipeSerializer.of(ReactionItemBrewingCodec.CODEC, ReactionItemBrewingCodec.STREAM_CODEC));

    public static final DeferredHolder<RecipeType<?>, RecipeType<ReactionBrewingFluidRecipeType>> REACTION_BREWING_FLUID =
            TYPES.register("reaction_fluid_brewing", () -> new RecipeType<ReactionBrewingFluidRecipeType>()
            {
                @Override
                public String toString()
                {
                    return "reaction_fluid_brewing";
                }
            });

    public static final DeferredHolder<RecipeSerializer<?>, CTCERecipeSerializer<ReactionBrewingFluidRecipeType>> REACTION_BREWING_FLUID_SERIALIZER =
            SERIALIZERS.register("reaction_fluid_brewing", () -> CTCERecipeSerializer.of(ReactionFluidBrewingCodec.CODEC, ReactionFluidBrewingCodec.STREAM_CODEC));

    public static void register(IEventBus eventBus)
    {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }
}
