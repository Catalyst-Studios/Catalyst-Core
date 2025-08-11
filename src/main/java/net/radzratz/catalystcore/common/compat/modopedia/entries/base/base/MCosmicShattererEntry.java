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

public class MCosmicShattererEntry
{
    public static void registerShattererEntry(HolderLookup.Provider registries, BookContentOutput output)
    {
        EntryBuilder.of("A Failed Relic")
                .icon(new ItemStack(CTCEItems.COSMIC_SHATTERER.get()))
                .assignedItems(CTCEItems.COSMIC_SHATTERER.get())
                .page(
                        TextBuilder.of("The result of an experiment that should have never been. " +
                                        "In a reckless attempt to unify advanced technological components and volatile arcane matrices, " +
                                        "a tear was forced open in the veil of reality.")
                                .justify(Justify.LEFT)
                                .lineHeight(12)
                )
                .page(
                        TextBuilder.of("Something ancient noticed. A deity—unknown, silent, and furious—intervened. " +
                                        "What remains is the Cosmic Shatter, a fragment of the process itself, crystallized at the moment of divine interruption.")
                                .justify(Justify.LEFT)
                                .lineHeight(12)
                )
                .page(
                        LargeFramedShowcaseBuilder.of(CTCEItems.COSMIC_SHATTERER.get().getDefaultInstance())
                                .x(65)
                                .y(25)
                )
                .build("base/cosmic_shatterer", output);
    }
}
