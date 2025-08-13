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
                             
                             This guide-lore book is under heavy W.I.P
                             
                             The Lore of Our Items are finished, but we are missing plenty of stuff that will come eventually.
                             
                             Soon™
                             """)
                .texture(CTCE("catalyst_codex"))
                .itemModel(CTCE("item/modopedia_books/universe_codex"))
                .tab(CTCE("catalystcore_tab1"))
                .openSound(MSoundEvents.BOOK_OPEN)
                .flipSound(MSoundEvents.BOOK_FLIP)
                .font(Modopedia.id("default"))
                .textColour(0xff6500)
                .headerColour(0x660000)
                .lineWidth(100)
                .build("catalyst_codex", output);
    }
}
