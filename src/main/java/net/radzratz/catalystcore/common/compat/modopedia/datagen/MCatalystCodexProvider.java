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
import net.minecraft.resources.ResourceLocation;
import net.radzratz.catalystcore.CatalystCore;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class MCatalystCodexProvider extends BookProvider
{
    /// **
    /// This goes to whoever does maintenance on CT Core in case I la RataRadiactiva AKA
    /// RadRatz leaves the project or delegates the same project to other person
    ///
    /// You who are the new maintaineer DO NOT TOUCH .itemModel
    ///
    /// but why?
    ///
    /// WELL... I spent 5hrs to make that thing work, AGAIN, which it did before and as im writing this did not
    /// work correctly, pretty much it workn't and said you are now called Bubu the Fooboo ma friend.
    ///
    /// But now it does work and I highly advice ye tweak constructor and build method and don't touch
    /// .itemModel for your own mental health...
    /// **

    public MCatalystCodexProvider(CompletableFuture<HolderLookup.Provider> registries, PackOutput output)
    {
        super(CatalystCore.MOD_ID, registries, output);
    }

    @Override
    protected void build(BiConsumer<String, Book> output)
    {
        BookBuilder.of("catalyst_codex", "Universe Codex")
                .subtitle("Unfathomable Power")
                .type(new ClassicBookType(LockedViewType.TRANSLUCENT))
                .landingText("""
                             Welcome to the Universe Codex.
                             
                             In here, you may find the $(b)Lore$() behind some of our Items, and Explanation to some of our Events
                             and Item transformations, Dimension list and how to access them, and lastly, how
                             our Block entities and Entities work!
                             """)
                .texture(Modopedia.id("brown_brass"))
                .itemModel(ResourceLocation.fromNamespaceAndPath("catalystcore","item/universe_codex"))
                .tab(ResourceLocation.fromNamespaceAndPath("catalystcore","catalystcore_tab1"))
                .openSound(MSoundEvents.BOOK_OPEN)
                .flipSound(MSoundEvents.BOOK_FLIP)
                .font(Modopedia.id("default"))
                .textColour(0x222222)
                .headerColour(0x660000)
                .lineWidth(100)
                .build(output);
    }
}
