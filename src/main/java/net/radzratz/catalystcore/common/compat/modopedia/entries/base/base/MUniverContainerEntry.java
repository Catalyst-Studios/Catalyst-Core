package net.radzratz.catalystcore.common.compat.modopedia.entries.base.base;

import net.favouriteless.modopedia.api.datagen.BookContentOutput;
import net.favouriteless.modopedia.api.datagen.builders.EntryBuilder;
import net.favouriteless.modopedia.api.datagen.builders.page_components.components.TextBuilder;
import net.favouriteless.modopedia.api.datagen.builders.templates.LargeFramedShowcaseBuilder;
import net.favouriteless.modopedia.book.text.Justify;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.radzratz.catalystcore.client.items.CTCEItems;

public class MUniverContainerEntry
{
    public static void registerContainerEntry(HolderLookup.Provider registries, BookContentOutput output)
    {
        EntryBuilder.of("The Pocket Cosmos")
                .icon(new ItemStack(CTCEItems.CONTAINER.get()))
                .assignedItems(CTCEItems.CONTAINER.get())
                .page(
                        TextBuilder.of("""
                                        Forged with the most advanced technology ever conceived.
                                        Its construction combined countless exotic materials, impossible elements, and concepts beyond mortal comprehension.
                                        But in the final stages of creation, something unprecedented happened: a universe was born within.
                                        """)
                                .justify(Justify.LEFT)
                                .lineHeight(12)
                )
                .page(
                        TextBuilder.of("""
                                        At first, it was silent. Stable. Perfect.
                                        
                                        The deities whispered among themselves, yet none moved to intervene.
                                        
                                        Over time, observers began to notice... something within was watching back.
                                        """)
                                .justify(Justify.LEFT)
                                .lineHeight(12)
                )
                .page(
                        LargeFramedShowcaseBuilder.of(CTCEItems.CONTAINER.get().getDefaultInstance())
                                .x(65)
                                .y(25)
                )
                .build("base/container", output);
    }
}
