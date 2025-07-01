package net.radzratz.catalystcore.common.compat.modopedia.entries.mainentries.base;

import com.google.gson.JsonElement;
import net.favouriteless.modopedia.api.datagen.builders.EntryBuilder;
import net.favouriteless.modopedia.api.datagen.builders.page_components.components.TextBuilder;
import net.favouriteless.modopedia.book.text.Justify;
import net.minecraft.world.item.ItemStack;
import net.radzratz.catalystcore.client.items.CTCEItems;

import java.util.function.BiConsumer;

public class MCosmicAbominationEntry
{
    public static void registerAbominationEntry(BiConsumer<String, JsonElement> output)
    {
        EntryBuilder.of("base/cosmic_abomination", "Something is Wrong...")
                .icon(new ItemStack(CTCEItems.COSMIC_ABOMINATION.get()))
                .assignedItems(CTCEItems.COSMIC_ABOMINATION.get())
                .page(
                        TextBuilder.of("It has awakened. Once dormant beyond the edge of all reality, " +
                                        "this being was drawn to you by choices you thought noble—actions meant for a greater good, " +
                                        "yet twisted by consequence. Now it follows. It clings to your presence. " +
                                        "The world feels wrong when it’s near. It doesn’t speak.")
                                .justify(Justify.LEFT)
                                .lineHeight(12)
                )
                .page(
                        TextBuilder.of("It doesn’t remember why it exists. But it knows you. And it is watching.")
                                .justify(Justify.LEFT)
                                .lineHeight(12)
                )
                .build(output);
    }
}
