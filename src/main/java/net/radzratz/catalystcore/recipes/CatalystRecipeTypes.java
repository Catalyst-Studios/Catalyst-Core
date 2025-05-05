package net.radzratz.catalystcore.recipes;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.radzratz.catalystcore.recipes.pentagram.PentagramJsonRecipe;
import net.radzratz.catalystcore.recipes.pentagram.PentagramRecipeSerializer;

public class CatalystRecipeTypes
{
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, "catalystcore");

    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, "catalystcore");

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

    public static void register(IEventBus eventBus)
    {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }
}
