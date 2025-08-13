package net.radzratz.catalystcore.common.compat.modopedia.entries.base.base;

import net.favouriteless.modopedia.api.datagen.BookContentOutput;
import net.favouriteless.modopedia.api.datagen.builders.EntryBuilder;
import net.favouriteless.modopedia.api.datagen.builders.page_components.components.TextBuilder;
import net.favouriteless.modopedia.api.datagen.builders.templates.LargeFramedShowcaseBuilder;
import net.favouriteless.modopedia.book.text.Justify;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.radzratz.catalystcore.client.items.CTCEItems;

public class MCatalystEntry
{
    public static void registerCatalystEntry(HolderLookup.Provider registries, BookContentOutput output)
    {
        EntryBuilder.of("The Final Destination")
                .icon(new ItemStack(CTCEItems.FULL_CATALYST.get()))
                .assignedItems(CTCEItems.FULL_CATALYST.get())
                .page(
                        TextBuilder.of("""
                                            A strange man once said:
                                            
                                            'To whoever seeks true power, you must endure a task—one that might
                                            overwhelm the weak and awaken the deities of our world...
                                            
                                            Angering them, challenging them, and overcoming their might.
                                            """)
                                .justify(Justify.LEFT)
                                .lineHeight(12)
                )
                .page(
                        TextBuilder.of("""
                                            Some of these deities don't like to fight directly—or even fight at all.
                                            
                                            Others may enlighten the challenger with incredible, yet complex machinery and arcane technology
                                            that may or may not wreak havoc upon the lands.
                                            
                                            Will you be the one to set the difference?'
                                            """)
                                .justify(Justify.LEFT)
                                .lineHeight(12)
                )
                .page(
                        LargeFramedShowcaseBuilder.of(CTCEItems.FULL_CATALYST.get().getDefaultInstance())
                                .x(65)
                                .y(25)
                )
                .build("base/the_catalyst", output);
    }
}
