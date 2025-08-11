package net.radzratz.catalystcore.common.compat.modopedia.entries.base.base;

import net.favouriteless.modopedia.api.datagen.BookContentOutput;
import net.favouriteless.modopedia.api.datagen.builders.EntryBuilder;
import net.favouriteless.modopedia.api.datagen.builders.page_components.components.TextBuilder;
import net.favouriteless.modopedia.api.datagen.builders.templates.LargeFramedShowcaseBuilder;
import net.favouriteless.modopedia.book.text.Justify;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.radzratz.catalystcore.client.items.CTCEItems;

public class MSpiritAgglomeratioEntry
{
    public static void registerFracturedEntry(HolderLookup.Provider registries, BookContentOutput output)
    {
        EntryBuilder.of("A Little Piece of Heaven")
                .icon(new ItemStack(CTCEItems.SPIRIT_AGGLOMERATIO.get()))
                .assignedItems(CTCEItems.SPIRIT_AGGLOMERATIO.get())
                .page(
                        TextBuilder.of("""
                                        Forged in the forgotten twilight of the cosmos, this orb contains the converged "
                                        "essence of all spirits across the universe.
                                        
                                        What was once meant to preserve the cycle of souls has since been twisted.
                                        """
                                )
                                .justify(Justify.LEFT)
                                .lineHeight(12)
                )
                .page(
                        TextBuilder.of("""
                                Its purpose, long corrupted by ancient hands, now drags the restless into a prison of silent torment.
                                
                                The Agglomeratio is not merely a vessel—it is a scar upon existence itself.
                                """
                        )
                )
                .page(
                        LargeFramedShowcaseBuilder.of(CTCEItems.SPIRIT_AGGLOMERATIO.get().getDefaultInstance())
                                .x(65)
                                .y(25)
                )
                .build("base/spirit_agglomeratio", output);
    }
}
