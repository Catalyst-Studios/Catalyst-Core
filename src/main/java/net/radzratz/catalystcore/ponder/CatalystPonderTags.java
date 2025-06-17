package net.radzratz.catalystcore.ponder;

import net.createmod.ponder.api.registration.PonderTagRegistrationHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.radzratz.catalystcore.items.CatalystItems;

public class CatalystPonderTags
{
    public static final ResourceLocation PHIALS = ResourceLocation.fromNamespaceAndPath("catalystcore", "phials");
    public static final ResourceLocation SLATES = ResourceLocation.fromNamespaceAndPath("catalystcore", "slates");

    public static void register(PonderTagRegistrationHelper<ResourceLocation> helper)
    {

        PonderTagRegistrationHelper<Item> itemHelper = helper.withKeyFunction(BuiltInRegistries.ITEM::getKey);

        helper.registerTag(PHIALS)
                .addToIndex()
                .item(CatalystItems.REINFORCED_BOTTLE.get(), true, false)
                .title("Reinforced Phials")
                .description("Phials capable of extracting and storing arcane or biological essence")
                .register();

        helper.registerTag(SLATES)
                .addToIndex()
                .item(CatalystItems.RUNIC_EMPTY.get(), true, false)
                .title("Runic Slates")
                .description("Mystic slates used for rituals or crafting")
                .register();

        itemHelper.addToTag(PHIALS)
                .add(CatalystItems.TORCHFLOWER_BOTTLE.get())
                .add(CatalystItems.WITHERED_BOTTLE.get())
                .add(CatalystItems.MYCELIUM_BOTTLE.get())
                .add(CatalystItems.SCULK_BOTTLE.get());

        itemHelper.addToTag(SLATES)
                .add(CatalystItems.INFECTED_SLATE.get())
                .add(CatalystItems.RUNIC_ARCANE.get())
                .add(CatalystItems.RUNIC_BLOOD.get())
                .add(CatalystItems.RUNIC_ENDER.get());
    }
}
