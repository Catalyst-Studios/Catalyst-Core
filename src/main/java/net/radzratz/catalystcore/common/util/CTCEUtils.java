package net.radzratz.catalystcore.common.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.radzratz.catalystcore.CatalystCore;

import java.util.Collection;
import java.util.stream.Stream;

import static net.radzratz.catalystcore.client.items.CTCEItems.CTCE_ITEMS;

public class CTCEUtils
{
    /**
     * Returns a unified stream of all {@link DeferredHolder} item entries across
     * Catalyst Core registries.
     *
     * <p>This is useful in contexts where all mod items should be treated as a single pool,
     * such as for automated tag generation, item processing, or dynamic lookup.
     *
     * @return a stream of {@code DeferredHolder<Item, ? extends Item>} representing all known mod items
     */
    // gonna use this later on for models // excluding tools, weapons, and special blocks ofc
    public static Stream<DeferredHolder<Item, ? extends Item>> allItemEntries()
    {
        return Stream.of(CTCE_ITEMS.getEntries()).flatMap(Collection::stream);
    }

    public static ResourceLocation CTCE(String path) {
        return ResourceLocation.fromNamespaceAndPath(CatalystCore.id, path);
    }
}
