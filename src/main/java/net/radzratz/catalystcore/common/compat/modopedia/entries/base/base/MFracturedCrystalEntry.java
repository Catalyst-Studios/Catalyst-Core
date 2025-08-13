package net.radzratz.catalystcore.common.compat.modopedia.entries.base.base;

import net.favouriteless.modopedia.api.datagen.BookContentOutput;
import net.favouriteless.modopedia.api.datagen.builders.EntryBuilder;
import net.favouriteless.modopedia.api.datagen.builders.page_components.components.TextBuilder;
import net.favouriteless.modopedia.api.datagen.builders.templates.LargeFramedShowcaseBuilder;
import net.favouriteless.modopedia.book.text.Justify;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.radzratz.catalystcore.client.items.CTCEItems;

public class MFracturedCrystalEntry
{
    public static void registerFracturedEntry(HolderLookup.Provider registries, BookContentOutput output)
    {
        EntryBuilder.of("Elemental Havoc")
                .icon(new ItemStack(CTCEItems.CHAOS_CRYSTAL.get()))
                .assignedItems(CTCEItems.CHAOS_CRYSTAL.get())
                .page(
                        TextBuilder.of("""
                                            Born from a failed pursuit of elemental unity.
                                            
                                            Scholars and arcanists once sought to bind fire, water, earth, air, and
                                            other primordial forces into a single, perfect core.
                                            
                                            They did not achieve balance—they summoned catastrophe.
                                            """)
                                .justify(Justify.LEFT)
                                .lineHeight(12)
                )
                .page(
                        TextBuilder.of("""
                                            The result was a fracture in the elemental order itself.
                                            
                                            What remains is this crystal—unstable, volatile, and eternally defiant.
                                            
                                            It resonates with power, but not with purpose.
                                            """)
                                .justify(Justify.LEFT)
                                .lineHeight(12)
                )
                .page(
                        LargeFramedShowcaseBuilder.of(CTCEItems.CHAOS_CRYSTAL.get().getDefaultInstance())
                                .x(65)
                                .y(25)
                )
                .build("base/fractured_crystal", output);
    }
}
