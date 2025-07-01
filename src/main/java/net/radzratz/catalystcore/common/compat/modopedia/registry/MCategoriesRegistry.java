package net.radzratz.catalystcore.common.compat.modopedia.registry;

import net.favouriteless.modopedia.api.book.Category;
import net.radzratz.catalystcore.common.compat.modopedia.categories.MCatalystBaseCategory;
import net.radzratz.catalystcore.common.compat.modopedia.categories.MCatalystPhialsCategory;

import java.util.function.BiConsumer;

public class MCategoriesRegistry
{
    public static void registerCategories(BiConsumer<String, Category> output)
    {
        MCatalystBaseCategory.registerBaseCategory(output);
        MCatalystPhialsCategory.registerPhialsCategory(output);
    }
}
