package net.radzratz.catalystcore.common.compat.modopedia.entries.base.base;

import net.favouriteless.modopedia.api.datagen.BookContentOutput;
import net.favouriteless.modopedia.api.datagen.builders.EntryBuilder;
import net.favouriteless.modopedia.api.datagen.builders.page_components.components.TextBuilder;
import net.favouriteless.modopedia.api.datagen.builders.templates.LargeFramedShowcaseBuilder;
import net.favouriteless.modopedia.book.text.Justify;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.radzratz.catalystcore.client.items.CTCEItems;

public class MMagicAnomalyEntry
{
    public static void registerAnomalyEntry(HolderLookup.Provider registries, BookContentOutput output)
    {
        EntryBuilder.of("Breaking the Reality")
                .icon(new ItemStack(CTCEItems.MAGIC_ANOMALY.get()))
                .assignedItems(CTCEItems.MAGIC_ANOMALY.get())
                .page(
                        TextBuilder.of("""
                                        Formed by an unprecedented convergence of all magical disciplines —
                                        from Elven rites to Voidbinding — this anomaly defies categorization.
                                        
                                        It is not a spell, nor a creature, but a flaw in the arcane weave; a wound where reality collapses
                                        under magical overload.
                                        """)
                                .justify(Justify.LEFT)
                                .lineHeight(12)
                )
                .page(
                        LargeFramedShowcaseBuilder.of(CTCEItems.MAGIC_ANOMALY.get().getDefaultInstance())
                                .x(0)
                                .y(25)
                )
                .build("base/magic_anomaly", output);
    }
}
