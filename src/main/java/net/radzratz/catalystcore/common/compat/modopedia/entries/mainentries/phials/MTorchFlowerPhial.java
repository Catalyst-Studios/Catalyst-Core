package net.radzratz.catalystcore.common.compat.modopedia.entries.mainentries.phials;

import com.google.gson.JsonElement;
import net.favouriteless.modopedia.api.datagen.builders.EntryBuilder;
import net.favouriteless.modopedia.api.datagen.builders.page_components.components.TextBuilder;
import net.favouriteless.modopedia.api.datagen.builders.templates.LargeFramedShowcaseBuilder;
import net.favouriteless.modopedia.book.text.Justify;
import net.radzratz.catalystcore.client.items.CTCEItems;

import java.util.function.BiConsumer;

public class MTorchFlowerPhial
{
    public static void registerTorchFlowerPhial(BiConsumer<String, JsonElement> output)
    {
        EntryBuilder.of("phials/torch_flower_essence", "TorchFlower Essence")
                .icon(CTCEItems.TORCHFLOWER_BOTTLE.get().getDefaultInstance())
                .assignedItems(CTCEItems.TORCHFLOWER_BOTTLE.get())
                .page(
                        TextBuilder.of("""
                                            TorchFlower Essence is extremely easy to collect!
                                            
                                            As you only need an $(el:phials/reinforced_phial)$(u)$(c:#ffffff)Empty Phial$() and a special type
                                            of shears to cut this plant's flower! Hold the Phial on your Off-Hand and the Shears
                                            on your Main-Hand to collect this essence. And lastly, Right Click the Flower.
                                            """)
                                .lineHeight(12)
                                .justify(Justify.LEFT)
                )
                .page(
                        LargeFramedShowcaseBuilder.of(CTCEItems.TORCHFLOWER_BOTTLE.get().getDefaultInstance()),
                        TextBuilder.of("""
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            By doing this, the Torchflower will loose it's properties and decay.
                                            """)
                                .lineHeight(12)
                )
                .build(output);
    }
}
