package net.radzratz.catalystcore.common.compat.modopedia.categories;

import net.favouriteless.modopedia.api.book.Category;
import net.favouriteless.modopedia.api.datagen.builders.CategoryBuilder;
import net.minecraft.world.item.ItemStack;
import net.radzratz.catalystcore.client.items.CTCEItems;

import java.util.function.BiConsumer;

public class MCatalystPhialsCategory
{
    public static void registerPhialsCategory(BiConsumer<String, Category> output)
    {
        CategoryBuilder.of("catalyst_phials", "Reinforced Phials")
                .icon(new ItemStack(CTCEItems.REINFORCED_BOTTLE.get()))
                .entries("phials/reinforced_phial", "phials/torch_flower_essence", "phials/withered_essence",
                         "phials/dragon_blood", "phials/pixie_essence", "phials/mycelium_spores", "phials/souls",
                         "phials/life_essence", "phials/sculk_biomass")
                .displayOnFrontPage(true)
                .landingText("""
                             Phials are essential to extract and store essences.
                             
                             Also capable of storing Biomass of specific living creatures... and their souls.
                             """)
                .build(output);
    }
}
