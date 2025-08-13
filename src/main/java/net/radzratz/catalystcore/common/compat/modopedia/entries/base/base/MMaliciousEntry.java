package net.radzratz.catalystcore.common.compat.modopedia.entries.base.base;

import net.favouriteless.modopedia.api.datagen.BookContentOutput;
import net.favouriteless.modopedia.api.datagen.builders.EntryBuilder;
import net.favouriteless.modopedia.api.datagen.builders.page_components.components.TextBuilder;
import net.favouriteless.modopedia.api.datagen.builders.templates.LargeFramedShowcaseBuilder;
import net.favouriteless.modopedia.book.text.Justify;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.radzratz.catalystcore.client.items.CTCEItems;

public class MMaliciousEntry
{
    public static void registerMaliciousEntry(HolderLookup.Provider registries, BookContentOutput output)
    {
        EntryBuilder.of("Chains of the Forgotten")
                .icon(new ItemStack(CTCEItems.MALICIOUS_EYE.get()))
                .assignedItems(CTCEItems.MALICIOUS_EYE.get())
                .page(
                        TextBuilder.of("""
                                            Trapped within an ancient snare, the entity watches—silent, yet unyielding.
                                            
                                            It cannot speak, but its gaze carries the weight of countless unspoken curses.
                                            """)
                                .justify(Justify.LEFT)
                                .lineHeight(12)
                )
                .page(
                        TextBuilder.of("""
                                            Eons ago, after the event that nearly unmade the world—
                                            corrupting relics, artifacts, and treasures beyond count—
                                            this being stood unmatched. With but a smile, it could erase all that existed.
                                            """)
                                .justify(Justify.LEFT)
                                .lineHeight(12)
                )
                .page(
                        TextBuilder.of("""
                                            Its name has faded from memory, but its will remains.
                                            
                                            Bound in chains forged from forgotten magics, it watches without rest,
                                            waiting for the moment its prison fails.
                                            """)
                )
                .page(
                        TextBuilder.of("""
                                            Should the bindings break, no wall, no god, and no star will stand against it.
                                            
                                            For now, all we can do is pray—and hope it never smiles again.
                                            """)
                )
                .page(
                        LargeFramedShowcaseBuilder.of(CTCEItems.MALICIOUS_EYE.get().getDefaultInstance())
                                .x(65)
                                .y(25)
                )
                .build("base/mal", output);
    }
}
