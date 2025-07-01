package net.radzratz.catalystcore.common.compat.modopedia.entries.mainentries.base;

import com.google.gson.JsonElement;
import net.favouriteless.modopedia.api.datagen.builders.EntryBuilder;
import net.favouriteless.modopedia.api.datagen.builders.page_components.components.TextBuilder;
import net.favouriteless.modopedia.book.text.Justify;
import net.minecraft.world.item.ItemStack;
import net.radzratz.catalystcore.client.items.CTCEItems;

import java.util.function.BiConsumer;

public class MMagicAnomalyEntry
{
    public static void registerAnomalyEntry(BiConsumer<String, JsonElement> output)
    {
        EntryBuilder.of("base/magic_anomaly", "Breaking the Reality")
                .icon(new ItemStack(CTCEItems.MAGIC_ANOMALY.get()))
                .assignedItems(CTCEItems.MAGIC_ANOMALY.get())
                .page(
                        TextBuilder.of("Formed by an unprecedented convergence of all magical disciplines — " +
                                        "from Elven rites to Voidbinding — this anomaly defies categorization. " +
                                        "It is not a spell, nor a creature, but a flaw in the arcane weave; a wound where reality collapses " +
                                        "under magical overload.")
                                .justify(Justify.LEFT)
                                .lineHeight(12)
                )
                .build(output);
    }
}
