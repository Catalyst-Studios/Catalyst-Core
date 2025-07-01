package net.radzratz.catalystcore.common.recipes;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.radzratz.catalystcore.common.recipes.cauldron.reaction_brewing.item_output.RBCodecItem;
import net.radzratz.catalystcore.common.recipes.cauldron.reaction_brewing.item_output.RBRecipeItemType;
import net.radzratz.catalystcore.common.recipes.cauldron.types.fluid_brewing.FBCodec;
import net.radzratz.catalystcore.common.recipes.cauldron.types.lava_brewing.LBCodec;
import net.radzratz.catalystcore.common.recipes.cauldron.types.universal_brewing.UBCodec;
import net.radzratz.catalystcore.common.recipes.cauldron.types.water_brewing.WBCodec;
import net.radzratz.catalystcore.common.recipes.cauldron.types.fluid_brewing.FBRecipeType;
import net.radzratz.catalystcore.common.recipes.cauldron.types.lava_brewing.LBRecipeType;
import net.radzratz.catalystcore.common.recipes.cauldron.types.universal_brewing.UBRecipeType;
import net.radzratz.catalystcore.common.recipes.cauldron.types.water_brewing.WBRecipeType;
import net.radzratz.catalystcore.common.recipes.pentagram.PentagramJsonRecipe;
import net.radzratz.catalystcore.common.recipes.pentagram.PentagramRecipeSerializer;
import net.radzratz.catalystcore.common.recipes.serializer.CTCERecipeSerializer;

public class CTCERecipeTypes
{
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, "catalystcore");

    public static final DeferredRegister<RecipeType<?>> TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, "catalystcore");

    /// Pentagram
    public static final DeferredHolder<RecipeSerializer<?>, PentagramRecipeSerializer> PENTAGRAM_SERIALIZER =
            SERIALIZERS.register("pentagram", PentagramRecipeSerializer::new);

    public static final DeferredHolder<RecipeType<?>, RecipeType<PentagramJsonRecipe>> PENTAGRAM_TYPE =
            TYPES.register("pentagram", () -> new RecipeType<PentagramJsonRecipe>()
            {
                @Override
                public String toString()
                {
                    return "pentagram";
                }
            });

    /// WaterBrewing
    public static final DeferredHolder<RecipeType<?>, RecipeType<WBRecipeType>> WATER_BREWING =
            TYPES.register("water_brewing", () -> new RecipeType<WBRecipeType>()
            {
                @Override
                public String toString()
                {
                    return "water_brewing";
                }
            });

    public static final DeferredHolder<RecipeSerializer<?>, CTCERecipeSerializer<WBRecipeType>> WATER_BREWING_SERIALIZER =
            SERIALIZERS.register("water_brewing", () -> CTCERecipeSerializer.of(WBCodec.CODEC, WBCodec.STREAM_CODEC));

    /// LavaBrewing
    public static final DeferredHolder<RecipeType<?>, RecipeType<LBRecipeType>> LAVA_BREWING =
            TYPES.register("lava_brewing", () -> new RecipeType<LBRecipeType>()
            {
                @Override
                public String toString()
                {
                    return "lava_brewing";
                }
            });

    public static final DeferredHolder<RecipeSerializer<?>, CTCERecipeSerializer<LBRecipeType>> LAVA_BREWING_SERIALIZER =
            SERIALIZERS.register("lava_brewing", () -> CTCERecipeSerializer.of(LBCodec.CODEC, LBCodec.STREAM_CODEC));

    /// UniversalBrewing
    public static final DeferredHolder<RecipeType<?>, RecipeType<UBRecipeType>> UNIVERSAL_BREWING =
            TYPES.register("universal_brewing", () -> new RecipeType<UBRecipeType>()
            {
                @Override
                public String toString()
                {
                    return "universal_brewing";
                }
            });

    public static final DeferredHolder<RecipeSerializer<?>, CTCERecipeSerializer<UBRecipeType>> UNIVERSAL_BREWING_SERIALIZER =
            SERIALIZERS.register("universal_brewing", () -> CTCERecipeSerializer.of(UBCodec.CODEC, UBCodec.STREAM_CODEC));

    /// FluidBrewing
    public static final DeferredHolder<RecipeType<?>, RecipeType<FBRecipeType>> FLUID_BREWING =
            TYPES.register("fluid_brewing", () -> new RecipeType<FBRecipeType>()
            {
                @Override
                public String toString()
                {
                    return "fluid_brewing";
                }
            });

    public static final DeferredHolder<RecipeSerializer<?>, CTCERecipeSerializer<FBRecipeType>> FLUID_BREWING_SERIALIZER =
            SERIALIZERS.register("fluid_brewing", () -> CTCERecipeSerializer.of(FBCodec.CODEC, FBCodec.STREAM_CODEC));

    /// Reaction Brewing Item/Fluid
    public static final DeferredHolder<RecipeType<?>, RecipeType<RBRecipeItemType>> REACTION_BREWING_ITEM =
            TYPES.register("reaction_item_brewing", () -> new RecipeType<RBRecipeItemType>()
            {
                @Override
                public String toString()
                {
                    return "reaction_item_brewing";
                }
            });

    public static final DeferredHolder<RecipeSerializer<?>, CTCERecipeSerializer<RBRecipeItemType>> REACTION_BREWING_ITEM_SERIALIZER =
            SERIALIZERS.register("reaction_item_brewing", () -> CTCERecipeSerializer.of(RBCodecItem.CODEC, RBCodecItem.STREAM_CODEC));

    public static void register(IEventBus eventBus)
    {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }
}
