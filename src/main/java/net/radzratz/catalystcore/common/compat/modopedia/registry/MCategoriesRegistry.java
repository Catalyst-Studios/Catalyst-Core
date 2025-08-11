package net.radzratz.catalystcore.common.compat.modopedia.registry;

import net.favouriteless.modopedia.api.book.Category;
import net.favouriteless.modopedia.api.datagen.BookContentOutput;
import net.minecraft.core.HolderLookup;
import net.radzratz.catalystcore.common.compat.modopedia.categories.MCatalystBaseCategory;
import net.radzratz.catalystcore.common.compat.modopedia.categories.MCatalystPhialsCategory;

import java.util.function.BiConsumer;

public class MCategoriesRegistry
{
    public static void registerCategories(HolderLookup.Provider registries, BookContentOutput output)
    {
        MCatalystBaseCategory.registerBaseCategory(registries, output);
        MCatalystPhialsCategory.registerPhialsCategory(registries, output);
    }
}
