package net.radzratz.catalystcore.common.compat.modopedia.entries.base.base;

import net.favouriteless.modopedia.api.datagen.BookContentOutput;
import net.favouriteless.modopedia.api.datagen.builders.EntryBuilder;
import net.favouriteless.modopedia.api.datagen.builders.page_components.components.TextBuilder;
import net.favouriteless.modopedia.api.datagen.builders.templates.LargeFramedShowcaseBuilder;
import net.favouriteless.modopedia.book.text.Justify;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.radzratz.catalystcore.common.registries.CTCEItemRegistry;

public class MBlackHoleEntry
{
    public static void registerBlackHoleEntry(HolderLookup.Provider registries, BookContentOutput output)
    {
        EntryBuilder.of("The Devourer")
                .icon(new ItemStack(CTCEItemRegistry.BLACK_HOLE.get()))
                .assignedItems(CTCEItemRegistry.BLACK_HOLE.get())
                .page(
                        TextBuilder.of("""
                                            Created through unmatched technological mastery,
                                            the Black Hole is the impossible made real.
                                            
                                            Though reduced to a miniature scale,
                                            its nature is unchanged—it consumes all without mercy.
                                            """)
                                .justify(Justify.LEFT)
                                .lineHeight(12)
                )
                .page(
                        TextBuilder.of("""
                                            The Catalyst Control Authority warns:
                                            
                                            Never place it near the Shaped Shifted Quantum Chocolate Bar.
                                            
                                            Proximity triggers an expanding event horizon,
                                            destabilizing containment and risking total collapse.
                                            """)
                )
                .page(
                        LargeFramedShowcaseBuilder.of(CTCEItemRegistry.BLACK_HOLE.get().getDefaultInstance())
                                .x(65)
                                .y(25)
                )
                .build("base/black_hole", output);
    }
}
