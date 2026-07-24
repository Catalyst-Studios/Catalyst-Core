package net.radzratz.catalystcore.client.items;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Tool;
import net.neoforged.neoforge.registries.DeferredItem;
import net.radzratz.catalystcore.client.items.foods.CTCEFoodProperties;
import net.radzratz.catalystcore.client.items.tools.CTCEToolTiers;
import net.radzratz.catalystcore.client.items.tools.tools.CataclysticAxe;
import net.radzratz.catalystcore.client.items.tools.tools.CataclysticPaxel;
import net.radzratz.catalystcore.client.items.tools.tools.CataclysticPickaxe;
import net.radzratz.catalystcore.client.items.tools.tools.CataclysticShovel;
import net.radzratz.catalystcore.client.items.tools.weapons.*;
import net.radzratz.catalystcore.client.items.types.*;

import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import static net.radzratz.catalystcore.common.registries.CTCEItemRegistry.CTCE_ITEMS;
import static net.radzratz.catalystcore.common.registries.CTCEItemRegistry.PHIALS;

public class CTCEItemHelpers {

    public static DeferredItem<CTCEEndgameItem> rgtrEndGameItems(String name) {
        return CTCE_ITEMS.register(name, () -> new CTCEEndgameItem(new Item.Properties().fireResistant().rarity(Rarity.EPIC)));
    }

    public static DeferredItem<CTCEMidGameItem> rgtrMidGameItems(String name) {
        return CTCE_ITEMS.register(name, () -> new CTCEMidGameItem(new Item.Properties().fireResistant().rarity(Rarity.RARE)));
    }

    public static DeferredItem<Item> rgtrCommonItems(String name) {
        return CTCE_ITEMS.register(name, () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
    }

    public static DeferredItem<Item> rgtrUncommonItems(String name) {
        return CTCE_ITEMS.register(name, () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    }

    public static DeferredItem<CTCESlateItem> rgtrSlateItems(String name) {
        return CTCE_ITEMS.register(name, () -> new CTCESlateItem(new Item.Properties().rarity(Rarity.UNCOMMON)));
    }

    public static <T extends CTCEPhialItem> DeferredItem<T> rgtrPhials(String name, Supplier<T> factory) {
        return CTCE_ITEMS.register(name, () -> {
            T phial = factory.get();
            PHIALS.add(phial);
            return phial;
        });
    }

    public static DeferredItem<CTCEFoodItem> rgtrFoods(String name) {
        return CTCE_ITEMS.register(name, () -> new CTCEFoodItem(new Item.Properties().food(CTCEFoodProperties.BURRITO).rarity(Rarity.UNCOMMON)));
    }

    public static DeferredItem<CataclysticHammer> rgtrHammer(BooleanSupplier module, BooleanSupplier type) {
        return CTCE_ITEMS.register("cataclystic_big_bonk", () -> new CataclysticHammer(
                CTCEToolTiers.CATALYST, new CataclysticHammer.Properties().fireResistant().setNoRepair().rarity(Rarity.EPIC).stacksTo(1),
                CataclysticHammer.createToolProperties(), CataclysticHammer.createAttributes(CTCEToolTiers.CATALYST, 69, -3.5f),
                module, type
        ));
    }

    public static DeferredItem<CataclysticGreatSword> rgrtGreatSword(BooleanSupplier module, BooleanSupplier type) {
        return CTCE_ITEMS.register("cataclystic_greatsword", () -> new CataclysticGreatSword(
                CTCEToolTiers.CATALYST, new CataclysticGreatSword.Properties().fireResistant().setNoRepair().rarity(Rarity.EPIC).stacksTo(1),
                CataclysticGreatSword.createToolProperties(), CataclysticGreatSword.createAttributes(CTCEToolTiers.CATALYST, 49, -2f),
                module, type
        ));
    }

    public static DeferredItem<CataclysticZweihander> rgtrZweihander(BooleanSupplier module, BooleanSupplier type) {
        return CTCE_ITEMS.register("cataclystic_zweihander", () -> new CataclysticZweihander(
                CTCEToolTiers.CATALYST, new CataclysticZweihander.Properties().fireResistant().setNoRepair().rarity(Rarity.EPIC).stacksTo(1),
                CataclysticZweihander.createToolProperties(), CataclysticZweihander.createAttributes(CTCEToolTiers.CATALYST, 14, 1f),
                module, type
        ));
    }

    public static DeferredItem<CataclysticHalberd> rgtrHalberd(BooleanSupplier module, BooleanSupplier type) {
        return CTCE_ITEMS.register("cataclystic_halberd", () -> new CataclysticHalberd(
                CTCEToolTiers.CATALYST, new CataclysticHalberd.Properties().fireResistant().setNoRepair().rarity(Rarity.EPIC).stacksTo(1),
                CataclysticHalberd.createToolProperties(), CataclysticHalberd.createAttributes(CTCEToolTiers.CATALYST, 24, 1.5f),
                module, type
        ));
    }

    public static DeferredItem<UniverseSword> rgtrUniverseSword(BooleanSupplier module, BooleanSupplier type) {
        return CTCE_ITEMS.register("universe_sword", () -> new UniverseSword(
                CTCEToolTiers.CATALYST, new UniverseSword.Properties().fireResistant().setNoRepair().rarity(Rarity.EPIC).stacksTo(1),
                UniverseSword.createToolProperties(), UniverseSword.createAttributes(CTCEToolTiers.CATALYST, 499, 8f),
                module, type
        ));
    }

    public static DeferredItem<CataclysticBroadsword> rgtrBroadSword(BooleanSupplier module, BooleanSupplier type) {
        return CTCE_ITEMS.register("cataclystic_broadsword", () -> new CataclysticBroadsword(
                CTCEToolTiers.CATALYST, new CataclysticBroadsword.Properties().fireResistant().setNoRepair().rarity(Rarity.EPIC).stacksTo(1),
                CataclysticBroadsword.createToolProperties(), CataclysticBroadsword.createAttributes(CTCEToolTiers.CATALYST, 14, 3.5f),
                module, type
        ));
    }

    public static DeferredItem<CataclysticUlfberht> rgtrUlfberht(BooleanSupplier module, BooleanSupplier type) {
        return CTCE_ITEMS.register("cataclystic_ulfberht", () -> new CataclysticUlfberht(
                CTCEToolTiers.CATALYST, new CataclysticUlfberht.Properties().fireResistant().setNoRepair().rarity(Rarity.EPIC).stacksTo(1),
                CataclysticUlfberht.createToolProperties(), CataclysticUlfberht.createAttributes(CTCEToolTiers.CATALYST, 14, 6f),
                module, type
        ));
    }

    public static DeferredItem<CataclysticGladius> rgtrGladius(BooleanSupplier module, BooleanSupplier type) {
        return CTCE_ITEMS.register("cataclystic_gladius", () -> new CataclysticGladius(
                CTCEToolTiers.CATALYST, new CataclysticGladius.Properties().fireResistant().setNoRepair().rarity(Rarity.EPIC).stacksTo(1),
                CataclysticGladius.createToolProperties(), CataclysticGladius.createAttributes(CTCEToolTiers.CATALYST, 14, 4.5f),
                module, type
        ));
    }

    public static DeferredItem<CataclysticScythe> rgtrScythe(BooleanSupplier module, BooleanSupplier type) {
        return CTCE_ITEMS.register("cataclystic_scythe", () -> new CataclysticScythe(
                CTCEToolTiers.CATALYST, new CataclysticScythe.Properties().fireResistant().setNoRepair().rarity(Rarity.EPIC).stacksTo(1),
                CataclysticScythe.createToolProperties(), CataclysticScythe.createAttributes(CTCEToolTiers.CATALYST, 14, 4f),
                module, type
        ));
    }

    public static DeferredItem<CataclysticRapier> rgtrRapier(BooleanSupplier module, BooleanSupplier type) {
        return CTCE_ITEMS.register("cataclystic_rapier", () -> new CataclysticRapier(
                CTCEToolTiers.CATALYST, new CataclysticRapier.Properties().fireResistant().setNoRepair().rarity(Rarity.EPIC).stacksTo(1),
                CataclysticRapier.createToolProperties(), CataclysticRapier.createAttributes(CTCEToolTiers.CATALYST, 11, 6f),
                module, type
        ));
    }

    public static DeferredItem<CataclysticBattleAxe> rgtrBattleAxe(BooleanSupplier module, BooleanSupplier type) {
        return CTCE_ITEMS.register("cataclystic_battleaxe", () -> new CataclysticBattleAxe(
                CTCEToolTiers.CATALYST, new CataclysticBattleAxe.Properties().fireResistant().setNoRepair().rarity(Rarity.EPIC).stacksTo(1),
                SwordItem.createToolProperties(), CataclysticBattleAxe.createAttributes(CTCEToolTiers.CATALYST, 34, -3f),
                module, type
        ));
    }

    public static DeferredItem<CataclysticKatar> rgtrKatar(BooleanSupplier module, BooleanSupplier type) {
        return CTCE_ITEMS.register("cataclystic_katar", () -> new CataclysticKatar(
                CTCEToolTiers.CATALYST, new CataclysticKatar.Properties().fireResistant().setNoRepair().rarity(Rarity.EPIC).stacksTo(1),
                CataclysticKatar.createToolProperties(), CataclysticKatar.createAttributes(CTCEToolTiers.CATALYST, 8, 14f),
                module, type
        ));
    }

    public static DeferredItem<CataclysticPaxel> rgtrPaxel(BooleanSupplier module, BooleanSupplier type) {
        return CTCE_ITEMS.register("cataclystic_paxel", () -> new CataclysticPaxel(
                CTCEToolTiers.CATALYST, new CataclysticPaxel.Properties().fireResistant().setNoRepair().rarity(Rarity.EPIC).stacksTo(1),
                new Tool(List.of(Tool.Rule.overrideSpeed(BlockTags.MINEABLE_WITH_PICKAXE, 27F)), 10.0F, 15),
                CataclysticPaxel.createAttributes(CTCEToolTiers.CATALYST, 39, 9f),
                module, type
        ));
    }

    public static DeferredItem<CataclysticPickaxe> rgtrPickaxe(BooleanSupplier module, BooleanSupplier type) {
        return CTCE_ITEMS.register("cataclystic_pickaxe", () -> new CataclysticPickaxe(
                CTCEToolTiers.CATALYST, new CataclysticPickaxe.Properties().fireResistant().setNoRepair().rarity(Rarity.EPIC).stacksTo(1),
                new Tool(List.of(Tool.Rule.overrideSpeed(BlockTags.MINEABLE_WITH_PICKAXE, 9f)), 10.0F, 15),
                CataclysticPickaxe.createAttributes(CTCEToolTiers.CATALYST, 9, 4f),
                module, type
        ));
    }

    public static DeferredItem<CataclysticShovel> rgtrShovel(BooleanSupplier module, BooleanSupplier type) {
        return CTCE_ITEMS.register("cataclystic_shovel", () -> new CataclysticShovel(
                CTCEToolTiers.CATALYST, new Item.Properties().fireResistant().setNoRepair().rarity(Rarity.EPIC).stacksTo(1),
                new Tool(List.of(Tool.Rule.overrideSpeed(BlockTags.MINEABLE_WITH_SHOVEL, 9F)), 10.0F, 15),
                CataclysticShovel.createAttributes(CTCEToolTiers.CATALYST, 9, 4f),
                module, type
        ));
    }

    public static DeferredItem<CataclysticAxe> rgtrAxe(BooleanSupplier module, BooleanSupplier type) {
        return CTCE_ITEMS.register("cataclystic_axe", () -> new CataclysticAxe(
                CTCEToolTiers.CATALYST, new CataclysticAxe.Properties().fireResistant().setNoRepair().rarity(Rarity.EPIC).stacksTo(1),
                new Tool(List.of(Tool.Rule.overrideSpeed(BlockTags.MINEABLE_WITH_AXE, 7F)), 10.0F, 15),
                CataclysticAxe.createAttributes(CTCEToolTiers.CATALYST, 14, -3f),
                module, type
        ));
    }

    public static DeferredItem<CataclysticShield> rgtrShield(BooleanSupplier module, BooleanSupplier type) {
        return CTCE_ITEMS.register("cataclystic_shield", () -> new CataclysticShield(
                new CataclysticShield.Properties()
                        .fireResistant()
                        .setNoRepair()
                        .rarity(Rarity.EPIC)
                        .stacksTo(1),
                module, type
        ));
    }
}
