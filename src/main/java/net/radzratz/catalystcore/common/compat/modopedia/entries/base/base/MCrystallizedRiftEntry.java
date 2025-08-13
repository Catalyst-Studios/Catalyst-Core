package net.radzratz.catalystcore.common.compat.modopedia.entries.base.base;

import net.favouriteless.modopedia.api.datagen.BookContentOutput;
import net.favouriteless.modopedia.api.datagen.builders.EntryBuilder;
import net.favouriteless.modopedia.api.datagen.builders.page_components.components.TextBuilder;
import net.favouriteless.modopedia.api.datagen.builders.templates.LargeFramedShowcaseBuilder;
import net.favouriteless.modopedia.book.text.Justify;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.radzratz.catalystcore.client.items.CTCEItems;

public class MCrystallizedRiftEntry
{
    public static void registerRiftEntry(HolderLookup.Provider registries, BookContentOutput output)
    {
        EntryBuilder.of("Where Worlds Bleed")
                .icon(new ItemStack(CTCEItems.RIFT.get()))
                .assignedItems(CTCEItems.RIFT.get())
                .page(
                        TextBuilder.of("""
                                            Born from the convergence of all portals—arcane, magical, and technological—
                                            the Crystallized Rift should not exist.
                                            
                                            Its creation was an accident,
                                            yet the deities watched in silence, taking no action, as if they had foreseen
                                            this moment since the dawn of time.
                                            """)
                                .justify(Justify.LEFT)
                                .lineHeight(12)
                )
                .page(
                        TextBuilder.of("""
                                            The Rift pulses with unstable power, shifting and bending
                                            as though struggling to contain itself.
                                            
                                            To gaze upon it is to feel the pull of something vast,
                                            something not meant for mortal eyes.
                                            """)
                                .justify(Justify.LEFT)
                                .lineHeight(12)
                )
                .page(
                        TextBuilder.of("""
                                            The Cosmic Abomination reacts with violent fury in its presence,
                                            as if recognizing an ancient enemy—or perhaps a key.
                                            
                                            Whispers claim the Rift is not merely a tear in reality,
                                            but a passage to a place that defies the laws of existence.
                                            """)
                                .justify(Justify.LEFT)
                                .lineHeight(12)
                )
                .page(
                        TextBuilder.of("""
                                            No record speaks of what lies beyond.
                                            
                                            Only one question endures...
                                        
                                            What waits on the other side?
                                            """)
                                .justify(Justify.LEFT)
                                .lineHeight(12)
                )
                .page(
                        LargeFramedShowcaseBuilder.of(CTCEItems.RIFT.get().getDefaultInstance())
                                .x(65)
                                .y(25)
                )
                .build("base/rift", output);
    }
}
