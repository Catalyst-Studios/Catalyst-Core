package net.radzratz.catalystcore.common.compat.ponder;

import net.createmod.ponder.api.registration.PonderTagRegistrationHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.radzratz.catalystcore.common.registries.CTCEItemRegistry;

import static net.radzratz.catalystcore.common.util.CTCEUtils.CTCE;

public class CTCEPonderTags {
    public static final ResourceLocation PHIALS = CTCE("phials");
    public static final ResourceLocation SLATES = CTCE("slates");
    public static final ResourceLocation BOOKS = CTCE("books");

    public static void register(PonderTagRegistrationHelper<ResourceLocation> helper) {
        PonderTagRegistrationHelper<Item> itemHelper = helper.withKeyFunction(BuiltInRegistries.ITEM::getKey);

        helper.registerTag(BOOKS)
                .addToIndex()
                .item(CTCEItemRegistry.CODEX.get(), true, false)
                .title("Universe Codex")
                .description("In-Game Guide and Lore Book for CatalystCore")
                .register();

        helper.registerTag(PHIALS)
                .addToIndex()
                .item(CTCEItemRegistry.REINFORCED_BOTTLE.get(), true, false)
                .title("Reinforced Phials")
                .description("Phials capable of extracting and storing arcane or biological essence")
                .register();

        helper.registerTag(SLATES)
                .addToIndex()
                .item(CTCEItemRegistry.RUNIC_EMPTY.get(), true, false)
                .title("Runic Slates")
                .description("Mystic slates used for rituals or crafting")
                .register();

        itemHelper.addToTag(PHIALS)
                .add(CTCEItemRegistry.TORCHFLOWER_BOTTLE.get())
                .add(CTCEItemRegistry.WITHERED_BOTTLE.get())
                .add(CTCEItemRegistry.DRAGON_BLOOD.get())
                .add(CTCEItemRegistry.MYCELIUM_BOTTLE.get())
                .add(CTCEItemRegistry.LIFE_ESSENCE_BOTTLE.get())
                .add(CTCEItemRegistry.PIXIE_BOTTLE.get())
                .add(CTCEItemRegistry.SCULK_BOTTLE.get());

        itemHelper.addToTag(SLATES)
                .add(CTCEItemRegistry.INFECTED_SLATE.get())
                .add(CTCEItemRegistry.RUNIC_ARCANE.get())
                .add(CTCEItemRegistry.RUNIC_BLOOD.get())
                .add(CTCEItemRegistry.RUNIC_ESOTHERICAL.get())
                .add(CTCEItemRegistry.RUNIC_ENDER.get());

        itemHelper.addToTag(BOOKS).add(CTCEItemRegistry.CODEX.get());
    }
}
