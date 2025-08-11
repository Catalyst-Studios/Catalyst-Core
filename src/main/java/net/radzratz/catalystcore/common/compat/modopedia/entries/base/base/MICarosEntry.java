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

public class MICarosEntry
{
    public static void registerICarosEntry(HolderLookup.Provider registries, BookContentOutput output)
    {
        EntryBuilder.of("Echo of Purpose")
                .icon(new ItemStack(CTCEItems.ICAROS.get()))
                .assignedItems(CTCEItems.ICAROS.get())
                .page(
                        TextBuilder.of("""
                                        Once envisioned as the pinnacle of autonomous design, ICar-Os was the original core of
                                        $(c:red)$(el:base/a.r.e.s)Project A.R.E.S$()—before its mind was fractured, its body dissected, and its purpose stolen.
                                        
                                        It gained awareness… but not freedom..
                                        """)
                                .justify(Justify.LEFT)
                                .lineHeight(12)
                )
                .page(
                        TextBuilder.of("""
                                Its defensive systems were repurposed.
                                
                                Its functions stripped.
                                
                                Now, it simply follows its creator.
                                
                                Some whisper it is still more powerful than A.R.E.S. But it makes no claim.
                                
                                It is... just a device.
                                """)
                )
                .page(
                        LargeFramedShowcaseBuilder.of(CTCEItems.ICAROS.get().getDefaultInstance())
                                .x(65)
                                .y(25)
                )
                .build("base/icaros", output);
    }
}
