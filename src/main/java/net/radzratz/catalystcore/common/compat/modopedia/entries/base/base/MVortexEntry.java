package net.radzratz.catalystcore.common.compat.modopedia.entries.base.base;

import net.favouriteless.modopedia.api.datagen.BookContentOutput;
import net.favouriteless.modopedia.api.datagen.builders.EntryBuilder;
import net.favouriteless.modopedia.api.datagen.builders.page_components.components.TextBuilder;
import net.favouriteless.modopedia.api.datagen.builders.templates.LargeFramedShowcaseBuilder;
import net.favouriteless.modopedia.book.text.Justify;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.radzratz.catalystcore.client.items.CTCEItems;

public class MVortexEntry
{
    public static void registerVortexEntry(HolderLookup.Provider registries, BookContentOutput output)
    {
        EntryBuilder.of("The Divine Scar")
                .icon(new ItemStack(CTCEItems.ETERNAL_VORTEX.get()))
                .assignedItems(CTCEItems.ETERNAL_VORTEX.get())
                .page(
                        TextBuilder.of("""
                                        A spiraling wound in the fabric of existence.
                                        
                                        This vortex is the result of a cosmic event—one that nearly shattered every known layer of reality.
                                        
                                        But it was halted.
                                        
                                        A benevolent deity intervened.
                                        """)
                                .justify(Justify.LEFT)
                                .lineHeight(12)
                )
                .page(
                        TextBuilder.of("""
                                What remains is a scar—a swirling singularity, frozen in time.
                                
                                A reminder of what almost was.
                                
                                No one knows what triggered it.
                                
                                Only that without divine action, we would not be here to ask."
                                """)
                )
                .page(
                        LargeFramedShowcaseBuilder.of(CTCEItems.ETERNAL_VORTEX.get().getDefaultInstance())
                                .x(65)
                                .y(25)
                )
                .build("base/vortex", output);
    }
}
