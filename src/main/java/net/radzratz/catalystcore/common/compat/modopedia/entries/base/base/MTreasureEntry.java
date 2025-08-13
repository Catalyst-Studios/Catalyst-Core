package net.radzratz.catalystcore.common.compat.modopedia.entries.base.base;

import net.favouriteless.modopedia.api.datagen.BookContentOutput;
import net.favouriteless.modopedia.api.datagen.builders.EntryBuilder;
import net.favouriteless.modopedia.api.datagen.builders.page_components.components.TextBuilder;
import net.favouriteless.modopedia.api.datagen.builders.templates.LargeFramedShowcaseBuilder;
import net.favouriteless.modopedia.book.text.Justify;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.radzratz.catalystcore.client.items.CTCEItems;

public class MTreasureEntry
{
    public static void registerTreasureEntry(HolderLookup.Provider registries, BookContentOutput output)
    {
        EntryBuilder.of("Treasure of the Unbound")
                .icon(new ItemStack(CTCEItems.TREASURE.get()))
                .assignedItems(CTCEItems.TREASURE.get())
                .page(
                        TextBuilder.of("""
                                            Its origin is unknown, yet its craftsmanship speaks of rare,
                                            exotic gems set in a frame of pure gold.
                                            
                                            Despite its size, it feels almost weightless in hand.
                                            """)
                                .justify(Justify.LEFT)
                                .lineHeight(12)
                )
                .page(
                        TextBuilder.of("""
                                            Those who have held it describe a sudden rush of adrenaline,
                                            as if the treasure itself whispers for more.
                                            
                                            Whether it is fortune or curse, the call is impossible to ignore.
                                            """)
                )
                .page(
                        LargeFramedShowcaseBuilder.of(CTCEItems.TREASURE.get().getDefaultInstance())
                                .x(65)
                                .y(25)
                )
                .build("base/treasure", output);
    }
}
