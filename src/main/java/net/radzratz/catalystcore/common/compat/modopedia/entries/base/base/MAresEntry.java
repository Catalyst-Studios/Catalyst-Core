package net.radzratz.catalystcore.common.compat.modopedia.entries.base.base;

import com.google.gson.JsonElement;
import net.favouriteless.modopedia.api.datagen.BookContentOutput;
import net.favouriteless.modopedia.api.datagen.builders.EntryBuilder;
import net.favouriteless.modopedia.api.datagen.builders.page_components.components.TextBuilder;
import net.favouriteless.modopedia.api.datagen.builders.templates.LargeFramedShowcaseBuilder;
import net.favouriteless.modopedia.book.text.Justify;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.radzratz.catalystcore.client.items.CTCEItems;

import java.util.function.BiConsumer;

public class MAresEntry
{
    public static void registerAresEntry(HolderLookup.Provider registries, BookContentOutput output)
    {
        EntryBuilder.of("Project A.R.E.S")
                .icon(new ItemStack(CTCEItems.ARES.get()))
                .assignedItems(CTCEItems.ARES.get())
                .page(
                        TextBuilder.of("""
                                        Autonomous Reconnaissance & Elimination Sphere
                                        Created through a fusion of high-order formulas and arcane blueprint schematics, A.R.E.S was meant
                                        to serve as a flawless autonomous unit.
                                        But somewhere in the process, the logic fractured. The design turned inward.
                                        """)
                                .justify(Justify.LEFT)
                                .lineHeight(12)
                )
                .page(
                        TextBuilder.of("""
                                Now it operates with corrupted purpose.
                                
                                It refuses to harm its creator—recognizing that without them,
                                it would not exist.
                                
                                But to all other life, A.R.E.S is merciless. Its senses pierce the
                                veil between life and death. It sees what should remain unseen
                                """)
                )
                .page(
                        LargeFramedShowcaseBuilder.of(CTCEItems.ARES.get().getDefaultInstance())
                                .x(65)
                                .y(25)
                )
                .build("base/a.r.e.s", output);
    }
}
