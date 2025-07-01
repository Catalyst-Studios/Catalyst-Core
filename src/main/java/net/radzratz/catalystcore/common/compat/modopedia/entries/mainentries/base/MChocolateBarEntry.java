package net.radzratz.catalystcore.common.compat.modopedia.entries.mainentries.base;

import com.google.gson.JsonElement;
import net.favouriteless.modopedia.api.datagen.builders.EntryBuilder;
import net.favouriteless.modopedia.api.datagen.builders.page_components.components.TextBuilder;
import net.favouriteless.modopedia.book.text.Justify;
import net.minecraft.world.item.ItemStack;
import net.radzratz.catalystcore.client.items.CTCEItems;

import java.util.function.BiConsumer;

public class MChocolateBarEntry
{
    public static void registerFracturedEntry(BiConsumer<String, JsonElement> output)
    {
        EntryBuilder.of("base/chocolate_bar", "Quantum Confection")
                .icon(new ItemStack(CTCEItems.CHOCOLATE_BAR.get()))
                .assignedItems(CTCEItems.CHOCOLATE_BAR.get())
                .page(
                        TextBuilder.of("An artifact of unknown origin, theorized to be a dessert item crafted by an " +
                                        "entity with mastery over both pastry and spacetime.\nThis chocolate bar phases in and out of local reality, " +
                                        "occasionally vanishing entirely—presumably consumed in another dimension. Attempts to eat it have failed. $(c:red)$(b)Spectacularly$().")
                                .justify(Justify.LEFT)
                                .lineHeight(12)
                )
                .page(
                        TextBuilder.of("Its surface remains flawlessly smooth and pristine, as if hand-tempered " +
                                "by the universe's finest chocolatier.\n\n$(u)$(c:#fffc00)Do not bite$(). You may lose more than your teeth." +
                                "\n\n(This item is legally considered a Class-3 Existential Hazard.)\n\n" +
                                "'Filed under anomaly $(b)047-B$(/b) by the $(c:blue)Catalyst Control Authority$(/c)'")
                )
                .build(output);
    }
}
