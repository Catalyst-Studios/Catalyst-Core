package net.radzratz.catalystcore.common.compat.modopedia.datagen;

import net.favouriteless.modopedia.Modopedia;
import net.favouriteless.modopedia.api.book.Book;
import net.favouriteless.modopedia.api.datagen.builders.BookBuilder;
import net.favouriteless.modopedia.api.datagen.providers.BookProvider;
import net.favouriteless.modopedia.common.book_types.ClassicBookType;
import net.favouriteless.modopedia.common.book_types.LockedViewType;
import net.favouriteless.modopedia.common.init.MSoundEvents;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.radzratz.catalystcore.CatalystCore;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import static net.radzratz.catalystcore.common.util.CTCEUtils.CTCE;

public class MCatalystCodexProvider extends BookProvider
{
    public MCatalystCodexProvider(CompletableFuture<HolderLookup.Provider> registries, PackOutput output)
    {
        super(CatalystCore.id, registries, output);
    }

    @Override
    protected void build(HolderLookup.Provider registries, BiConsumer<String, Book> output)
    {
        BookBuilder.of("Universe Codex")
                .subtitle("Unfathomable Power")
                .type(new ClassicBookType(LockedViewType.TRANSLUCENT, 37, 7, 10))
                .landingText("""
                             Welcome to the Universe Codex.
                             
                             In here, you may find the $(b)Lore$(/b) behind some of our Items, an Explanation to some of our Events
                             and Item transformations, Dimension list and how to access them, and lastly, how
                             our Block entities and Entities work!$()
                             """)
                .texture(CTCE("catalyst_codex"))
                .itemModel(CTCE("item/modopedia_books/universe_codex"))
                .tab(CTCE("catalystcore_tab1"))
                .openSound(MSoundEvents.BOOK_OPEN)
                .flipSound(MSoundEvents.BOOK_FLIP)
                .font(Modopedia.id("default"))
                .textColour(0xd3b792)
                .headerColour(0x660000)
                .lineWidth(100)
                .build("catalyst_codex", output);
    }
}
