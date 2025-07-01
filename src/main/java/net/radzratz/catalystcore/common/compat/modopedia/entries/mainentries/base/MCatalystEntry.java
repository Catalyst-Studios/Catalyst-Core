package net.radzratz.catalystcore.common.compat.modopedia.entries.mainentries.base;

import com.google.gson.JsonElement;
import net.favouriteless.modopedia.api.datagen.builders.EntryBuilder;
import net.favouriteless.modopedia.api.datagen.builders.page_components.components.TextBuilder;
import net.favouriteless.modopedia.book.text.Justify;
import net.minecraft.world.item.ItemStack;
import net.radzratz.catalystcore.client.items.CTCEItems;

import java.util.function.BiConsumer;

public class MCatalystEntry
{
    public static void registerCatalystEntry(BiConsumer<String, JsonElement> output)
    {
        EntryBuilder.of("base/the_catalyst", "The Final Destination")
                .icon(new ItemStack(CTCEItems.FULL_CATALYST.get()))
                .assignedItems(CTCEItems.FULL_CATALYST.get())
                .page(
                        TextBuilder.of("A strange man once said: 'To whoever seeks true power, " +
                                        "you must endure a task—one that might overwhelm the weak and awaken the deities of our world... " +
                                        "Angering them, challenging them, and overcoming their might.")
                                .justify(Justify.LEFT)
                                .lineHeight(12)
                )
                .page(
                        TextBuilder.of("Some of these deities don't like to fight directly—or even fight at all. " +
                                        "Others may enlighten the challenger with incredible, yet complex machinery and arcane technology " +
                                        "that may or may not wreak havoc upon the lands. Will you be the one to set the difference?'")
                                .justify(Justify.LEFT)
                                .lineHeight(12)
                )
                .build(output);
    }
}
