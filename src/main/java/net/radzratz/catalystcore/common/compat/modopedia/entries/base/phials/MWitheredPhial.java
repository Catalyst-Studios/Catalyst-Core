package net.radzratz.catalystcore.common.compat.modopedia.entries.base.phials;

import com.google.gson.JsonElement;
import net.favouriteless.modopedia.api.datagen.BookContentOutput;
import net.favouriteless.modopedia.api.datagen.builders.EntryBuilder;
import net.favouriteless.modopedia.api.datagen.builders.page_components.components.TextBuilder;
import net.favouriteless.modopedia.api.datagen.builders.templates.LargeFramedShowcaseBuilder;
import net.favouriteless.modopedia.book.text.Justify;
import net.minecraft.core.HolderLookup;
import net.radzratz.catalystcore.client.items.CTCEItems;

import java.util.function.BiConsumer;

public class MWitheredPhial
{
    public static void registerWitheredPhial(HolderLookup.Provider registries, BookContentOutput output)
    {
        EntryBuilder.of("Withered Essence")
                .icon(CTCEItems.WITHERED_BOTTLE.get().getDefaultInstance())
                .assignedItems(CTCEItems.WITHERED_BOTTLE.get())
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
                        LargeFramedShowcaseBuilder.of(CTCEItems.WITHERED_BOTTLE.get().getDefaultInstance()),
                        TextBuilder.of("""
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            A Sword should've done the trick, but these made the blood loose it's powerful
                                            properties...
                                            """)
                                .lineHeight(12)
                )
                .build("phials/withered_essence", output);
    }
}
