package net.radzratz.catalystcore.common.compat.modopedia.entries.base.base;

import net.favouriteless.modopedia.api.datagen.BookContentOutput;
import net.favouriteless.modopedia.api.datagen.builders.EntryBuilder;
import net.favouriteless.modopedia.api.datagen.builders.page_components.components.TextBuilder;
import net.favouriteless.modopedia.api.datagen.builders.templates.LargeFramedShowcaseBuilder;
import net.favouriteless.modopedia.book.text.Justify;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.radzratz.catalystcore.client.items.CTCEItems;

public class MForbiddenArtifactEntry
{
    public static void registerForbiddenEntry(HolderLookup.Provider registries, BookContentOutput output)
    {
        EntryBuilder.of("The Failed Ascendant")
                .icon(new ItemStack(CTCEItems.FORBIDDEN_ORB.get()))
                .assignedItems(CTCEItems.FORBIDDEN_ORB.get())
                .page(
                        TextBuilder.of("""
                                            Born from the merging of lesser relics,
                                            its conflicting properties doomed it from the start.
                                            
                                            Unlike the Cosmic Shatterer,
                                            it drew the attention of entities and deities alike,
                                            who saw in it the echo of a noble purpose.
                                            """)
                                .justify(Justify.LEFT)
                                .lineHeight(12)
                )
                .page(
                        TextBuilder.of("""
                                            Even with cosmic aid, the attempt failed.
                                            
                                            What remains is a flawed creation—neither relic nor ruin,
                                            yet carrying the weight of what it could have been.
                                            """)
                )
                .page(
                        LargeFramedShowcaseBuilder.of(CTCEItems.FORBIDDEN_ORB.get().getDefaultInstance())
                                .x(65)
                                .y(25)
                )
                .build("base/forbidden", output);
    }
}
