package net.radzratz.catalystcore.common.compat.modopedia.entries.mainentries.phials;

import com.google.gson.JsonElement;
import net.favouriteless.modopedia.api.datagen.builders.EntryBuilder;
import net.favouriteless.modopedia.api.datagen.builders.page_components.components.TextBuilder;
import net.favouriteless.modopedia.api.datagen.builders.templates.LargeFramedShowcaseBuilder;
import net.favouriteless.modopedia.book.text.Justify;
import net.radzratz.catalystcore.client.items.CTCEItems;

import java.util.function.BiConsumer;

public class MReinforcedPhial
{
    public static void registerReinforcedPhial(BiConsumer<String, JsonElement> output)
    {
        EntryBuilder.of("phials/reinforced_phial", "Reinforced Phial")
                .icon(CTCEItems.REINFORCED_BOTTLE.get().getDefaultInstance())
                .assignedItems(CTCEItems.REINFORCED_BOTTLE.get())
                .page(
                        TextBuilder.of("""
                                        This is an $(b)Empty Reinforced Phial$().
                                        
                                        These can be crafted with the Reinforced Glass, a glass so strong that is capable of withstanding the
                                        might of the Dragon and the Explosive Nature from the Wither.
                                        """)
                                .lineHeight(12)
                                .justify(Justify.LEFT)
                )
                .page(
                        LargeFramedShowcaseBuilder.of(CTCEItems.REINFORCED_BOTTLE.get().getDefaultInstance()),
                        TextBuilder.of("""
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            These type of bottles are the only ones 'capable' of containing these essences without Exploding.
                                            """)
                                .lineHeight(12)
                )
                .build(output);
    }
}
