package net.radzratz.catalystcore.common.compat.modopedia.entries.mainentries.phials;

import com.google.gson.JsonElement;
import net.favouriteless.modopedia.api.datagen.builders.EntryBuilder;
import net.favouriteless.modopedia.api.datagen.builders.page_components.components.TextBuilder;
import net.favouriteless.modopedia.api.datagen.builders.templates.LargeFramedShowcaseBuilder;
import net.favouriteless.modopedia.book.text.Justify;
import net.radzratz.catalystcore.client.items.CTCEItems;

import java.util.function.BiConsumer;

public class MDragonPhial
{
    public static void registerDragonBloodPhial(BiConsumer<String, JsonElement> output)
    {
        EntryBuilder.of("phials/dragon_blood", "Dragon's Blood")
                .icon(CTCEItems.DRAGON_BLOOD.get().getDefaultInstance())
                .assignedItems(CTCEItems.DRAGON_BLOOD.get())
                .page(
                        TextBuilder.of("""
                                            We must venture into the End for this one.
                                            
                                            You will need an $(el:phials/reinforced_phial)$(u)$(c:#ffffff)Empty Phial$() and a Special
                                            Dagger to extract this creature's blood. Hold the Phial in your Off-Hand and the Dagger on your Main-Hand to extract it's
                                            Blood. Then, Right Click the Dragon to extract it's blood.
                                            """)
                                .lineHeight(12)
                                .justify(Justify.LEFT)
                )
                .page(
                        LargeFramedShowcaseBuilder.of(CTCEItems.DRAGON_BLOOD.get().getDefaultInstance()),
                        TextBuilder.of("""
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            A Sword should've done the job, but these made the blood loose it's powerful
                                            properties...
                                            """)
                                .lineHeight(12)
                )
                .build(output);
    }
}
