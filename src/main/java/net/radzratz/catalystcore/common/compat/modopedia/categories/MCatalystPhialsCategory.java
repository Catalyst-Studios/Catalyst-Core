package net.radzratz.catalystcore.common.compat.modopedia.categories;

import net.favouriteless.modopedia.api.datagen.BookContentOutput;
import net.favouriteless.modopedia.api.datagen.builders.CategoryBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.radzratz.catalystcore.client.items.CTCEItems;

public class MCatalystPhialsCategory
{
    public static void registerPhialsCategory(HolderLookup.Provider registries, BookContentOutput output)
    {
        CategoryBuilder.of("Reinforced Phials")
                .icon(new ItemStack(CTCEItems.REINFORCED_BOTTLE.get()))
                .entries("phials/reinforced_phial", "phials/torch_flower_essence", "phials/withered_essence",
                         "phials/dragon_blood", "phials/pixie_essence", "phials/mycelium_spores", "phials/souls",
                         "phials/life_essence", "phials/sculk_biomass"
                )
                .displayOnFrontPage(true)
                .landingText("""
                             Phials are essential to extract and store essences.
                             
                             Also capable of storing Biomass of specific living creatures... and their souls.
                             """
                )
                .build("catalyst_phials", output);
    }
}
