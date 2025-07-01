package net.radzratz.catalystcore.common.compat.ponder;

import net.createmod.ponder.api.registration.PonderTagRegistrationHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.radzratz.catalystcore.client.items.CTCEItems;

public class CTCEPonderTags
{
    public static final ResourceLocation PHIALS = ResourceLocation.fromNamespaceAndPath("catalystcore", "phials");
    public static final ResourceLocation SLATES = ResourceLocation.fromNamespaceAndPath("catalystcore", "slates");
    public static final ResourceLocation BOOKS = ResourceLocation.fromNamespaceAndPath("catalystcore", "books");

    public static void register(PonderTagRegistrationHelper<ResourceLocation> helper)
    {
        PonderTagRegistrationHelper<Item> itemHelper = helper.withKeyFunction(BuiltInRegistries.ITEM::getKey);

        helper.registerTag(BOOKS)
                .addToIndex()
                .item(CTCEItems.CODEX.get(), true, false)
                .title("Universe Codex")
                .description("In-Game Guide and Lore Book for CatalystCore")
                .register();

        helper.registerTag(PHIALS)
                .addToIndex()
                .item(CTCEItems.REINFORCED_BOTTLE.get(), true, false)
                .title("Reinforced Phials")
                .description("Phials capable of extracting and storing arcane or biological essence")
                .register();

        helper.registerTag(SLATES)
                .addToIndex()
                .item(CTCEItems.RUNIC_EMPTY.get(), true, false)
                .title("Runic Slates")
                .description("Mystic slates used for rituals or crafting")
                .register();

        itemHelper.addToTag(PHIALS)
                .add(CTCEItems.TORCHFLOWER_BOTTLE.get())
                .add(CTCEItems.WITHERED_BOTTLE.get())
                .add(CTCEItems.DRAGON_BLOOD.get())
                .add(CTCEItems.MYCELIUM_BOTTLE.get())
                .add(CTCEItems.LIFE_ESSENCE_BOTTLE.get())
                .add(CTCEItems.PIXIE_BOTTLE.get())
                .add(CTCEItems.SCULK_BOTTLE.get());

        itemHelper.addToTag(SLATES)
                .add(CTCEItems.INFECTED_SLATE.get())
                .add(CTCEItems.RUNIC_ARCANE.get())
                .add(CTCEItems.RUNIC_BLOOD.get())
                .add(CTCEItems.RUNIC_ESOTHERICAL.get())
                .add(CTCEItems.RUNIC_ENDER.get());

        itemHelper.addToTag(BOOKS).add(CTCEItems.CODEX.get());
    }
}
