package net.radzratz.catalystcore.common.compat.modopedia.categories;

import net.favouriteless.modopedia.api.datagen.BookContentOutput;
import net.favouriteless.modopedia.api.datagen.builders.CategoryBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.radzratz.catalystcore.client.items.CTCEItems;

public class MCatalystBaseCategory
{
    public static void registerBaseCategory(HolderLookup.Provider registries, BookContentOutput output)
    {
        CategoryBuilder.of("Catalyst Core Base")
                .icon(new ItemStack(CTCEItems.FULL_CATALYST.get()))
                .entries("base/the_catalyst", "base/a.r.e.s", "base/chocolate_bar", "base/cosmic_abomination",
                         "base/spirit_agglomeratio", "base/cosmic_shatterer", "base/fractured_crystal",
                         "base/magic_anomaly", "base/icaros", "base/vortex")
                .displayOnFrontPage(true)
                .landingText("The Universe's Finest Creations... Some shouldn't even see the light, but allas... here we are.")
                .build("catalyst_base", output);
    }
}
