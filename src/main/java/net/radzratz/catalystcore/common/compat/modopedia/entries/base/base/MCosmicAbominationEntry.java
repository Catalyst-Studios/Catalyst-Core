package net.radzratz.catalystcore.common.compat.modopedia.entries.base.base;

import net.favouriteless.modopedia.api.datagen.BookContentOutput;
import net.favouriteless.modopedia.api.datagen.builders.EntryBuilder;
import net.favouriteless.modopedia.api.datagen.builders.page_components.components.TextBuilder;
import net.favouriteless.modopedia.api.datagen.builders.templates.LargeFramedShowcaseBuilder;
import net.favouriteless.modopedia.book.text.Justify;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.radzratz.catalystcore.client.items.CTCEItems;

public class MCosmicAbominationEntry
{
    public static void registerAbominationEntry(HolderLookup.Provider registries, BookContentOutput output)
    {
        EntryBuilder.of("Something is Wrong...")
                .icon(new ItemStack(CTCEItems.COSMIC_ABOMINATION.get()))
                .assignedItems(CTCEItems.COSMIC_ABOMINATION.get())
                .page(
                        TextBuilder.of("It has awakened. Once dormant beyond the edge of all reality, " +
                                        "this being was drawn to you by choices you thought noble—actions meant for a greater good, " +
                                        "yet twisted by consequence. Now it follows. It clings to your presence. " +
                                        "The world feels wrong when it’s near. It doesn’t speak.")
                                .justify(Justify.LEFT)
                                .lineHeight(12)
                )
                .page(
                        TextBuilder.of("It doesn’t remember why it exists. But it knows you. And it is watching.")
                                .justify(Justify.LEFT)
                                .lineHeight(12)
                )
                .page(
                        LargeFramedShowcaseBuilder.of(CTCEItems.COSMIC_ABOMINATION.get().getDefaultInstance())
                                .x(65)
                                .y(25)
                )
                .build("base/cosmic_abomination", output);
    }
}
