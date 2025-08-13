package net.radzratz.catalystcore.common.compat.modopedia.entries.base.base;

import net.favouriteless.modopedia.api.datagen.BookContentOutput;
import net.favouriteless.modopedia.api.datagen.builders.EntryBuilder;
import net.favouriteless.modopedia.api.datagen.builders.page_components.components.TextBuilder;
import net.favouriteless.modopedia.api.datagen.builders.templates.LargeFramedShowcaseBuilder;
import net.favouriteless.modopedia.book.text.Justify;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.radzratz.catalystcore.client.items.CTCEItems;

public class MDivineRelicEntry
{
    public static void registerDivineEntry(HolderLookup.Provider registries, BookContentOutput output)
    {
        EntryBuilder.of("The Eternal Witness")
                .icon(new ItemStack(CTCEItems.ANCIENT_RELIC.get()))
                .assignedItems(CTCEItems.ANCIENT_RELIC.get())
                .page(
                        TextBuilder.of("""
                                            Few relics of the old world have withstood the erosion of time.
                                        
                                            The Divine Relic is one of these remnants, alongside the Spirit Agglomeratio
                                            and the Fractured Chaos Crystal. Yet unlike them, it remains whole,
                                            as if the ages themselves have spared it for a reason now lost.
                                            """)
                                .justify(Justify.LEFT)
                                .lineHeight(12)
                )
                .page(
                        TextBuilder.of("""
                                            Its purpose has faded into shadow, a noble cause forgotten by history.
                                            
                                            Those who touch it speak of a cold whisper in the mind,
                                            a presence that watches silently, neither hostile nor kind.
                                            """)
                                .justify(Justify.LEFT)
                                .lineHeight(12)
                )
                .page(
                        TextBuilder.of("""
                                            Rumors claim the relic hums with power—vivid and unsettling.
                                            
                                            Some say it draws from light and shadow alike, a force that bends
                                            to no mortal hand. To possess it is to feel both awe and sorrow,
                                            as if the relic mourns the world that has forgotten its own guardians.
                                            """)
                                .justify(Justify.LEFT)
                                .lineHeight(12)
                )
                .page(
                        TextBuilder.of("""
                                            Travelers, scholars, and seekers beware: the Divine Relic is not mere treasure.
                                            
                                            It is a fragment of an age that no longer exists,
                                            carrying a weight that none were meant to bear.
                                            """)
                                .justify(Justify.LEFT)
                                .lineHeight(12)
                )
                .page(
                        LargeFramedShowcaseBuilder.of(CTCEItems.ANCIENT_RELIC.get().getDefaultInstance())
                                .x(65)
                                .y(25)
                )
                .build("base/divine", output);
    }
}
