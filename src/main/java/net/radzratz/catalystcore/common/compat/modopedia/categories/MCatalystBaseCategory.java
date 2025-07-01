package net.radzratz.catalystcore.common.compat.modopedia.categories;

import net.favouriteless.modopedia.api.book.Category;
import net.favouriteless.modopedia.api.datagen.builders.CategoryBuilder;
import net.minecraft.world.item.ItemStack;
import net.radzratz.catalystcore.client.items.CTCEItems;

import java.util.function.BiConsumer;

public class MCatalystBaseCategory
{
    public static void registerBaseCategory(BiConsumer<String, Category> output)
    {
        CategoryBuilder.of("catalyst_base", "Catalyst Core Base")
                .icon(new ItemStack(CTCEItems.FULL_CATALYST.get()))
                .entries("base/the_catalyst", "base/a.r.e.s", "base/chocolate_bar", "base/cosmic_abomination",
                         "base/spirit_agglomeratio", "base/cosmic_shatterer", "base/fractured_crystal",
                         "base/magic_anomaly", "base/icaros")
                .displayOnFrontPage(true)
                .landingText("The Universe's Finest Creations... Some shouldn't even see the light, but allas... here we are.")
                .build(output);
    }
}
