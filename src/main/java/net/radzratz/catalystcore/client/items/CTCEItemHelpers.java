package net.radzratz.catalystcore.client.items;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Tool;
import net.neoforged.neoforge.registries.DeferredItem;
import net.radzratz.catalystcore.client.items.foods.CTCEFoods;
import net.radzratz.catalystcore.client.items.tools.CTCEToolTiers;
import net.radzratz.catalystcore.client.items.tools.tools.CTCEAxe;
import net.radzratz.catalystcore.client.items.tools.tools.CTCEPaxel;
import net.radzratz.catalystcore.client.items.tools.tools.CTCEPickaxe;
import net.radzratz.catalystcore.client.items.tools.tools.CTCEShovel;
import net.radzratz.catalystcore.client.items.tools.weapons.*;

import java.util.List;

import static net.radzratz.catalystcore.client.items.CTCEItems.CTCE_ITEMS;

public class CTCEItemHelpers {

    public static DeferredItem<Item> rgtrEndGameItems(String name) {
        return CTCE_ITEMS.register(name, () -> new Item(new Item.Properties().fireResistant().rarity(Rarity.EPIC)));
    }

    public static DeferredItem<Item> rgtrMidGameItems(String name) {
        return CTCE_ITEMS.register(name, () -> new Item(new Item.Properties().fireResistant().rarity(Rarity.RARE)));
    }

    public static DeferredItem<Item> rgtrCommonItems(String name) {
        return CTCE_ITEMS.register(name, () -> new Item(new Item.Properties()));
    }

    public static DeferredItem<Item> rgtrUncommonItems(String name) {
        return CTCE_ITEMS.register(name, () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    }

    public static DeferredItem<Item> rgtrBottles(String name) {
        return CTCE_ITEMS.register(name, () -> new Item(new Item.Properties().stacksTo(16).rarity(Rarity.UNCOMMON)));
    }

    public static DeferredItem<Item> rgtrFoods(String name) {
        return CTCE_ITEMS.register(name, () -> new Item(new Item.Properties().food(CTCEFoods.BURRITO).rarity(Rarity.UNCOMMON)));
    }

    ///Heavy Weapons
    public static DeferredItem<CTCEHammer> rgtrHammer() {
        return CTCE_ITEMS.register("cataclystic_big_bonk", () -> new CTCEHammer(
                CTCEToolTiers.CATALYST, new CTCEHammer.Properties().fireResistant().setNoRepair().rarity(Rarity.EPIC).stacksTo(1),
                CTCEHammer.createToolProperties(), CTCEHammer.createAttributes(CTCEToolTiers.CATALYST, 69, -3.5f)
        ));
    }

    public static DeferredItem<CTCEGreatSword> rgrtGreatSword() {
        return CTCE_ITEMS.register("cataclystic_greatsword", () -> new CTCEGreatSword(
                CTCEToolTiers.CATALYST, new CTCEGreatSword.Properties().fireResistant().setNoRepair().rarity(Rarity.EPIC).stacksTo(1),
                CTCEGreatSword.createToolProperties(), CTCEGreatSword.createAttributes(CTCEToolTiers.CATALYST, 49, -2f)
        ));
    }

    public static DeferredItem<CTCEZweihander> rgtrZweihander() {
        return CTCE_ITEMS.register("cataclystic_zweihander", () -> new CTCEZweihander(
                CTCEToolTiers.CATALYST, new CTCEZweihander.Properties().fireResistant().setNoRepair().rarity(Rarity.EPIC).stacksTo(1),
                CTCEZweihander.createToolProperties(), CTCEZweihander.createAttributes(CTCEToolTiers.CATALYST, 14, 1f)
        ));
    }

    public static DeferredItem<CTCEHalberd> rgtrHalberd() {
        return CTCE_ITEMS.register("cataclystic_halberd", () -> new CTCEHalberd(
                CTCEToolTiers.CATALYST, new CTCEHalberd.Properties().fireResistant().setNoRepair().rarity(Rarity.EPIC).stacksTo(1),
                CTCEHalberd.createToolProperties(), CTCEHalberd.createAttributes(CTCEToolTiers.CATALYST, 24, 1.5f)
        ));
    }

    public static DeferredItem<CTCEUniverseSword> rgtrUniverseSword() {
        return CTCE_ITEMS.register("universe_sword", () -> new CTCEUniverseSword(
                CTCEToolTiers.CATALYST, new CTCEUniverseSword.Properties().fireResistant().setNoRepair().rarity(Rarity.EPIC).stacksTo(1),
                CTCEUniverseSword.createToolProperties(), CTCEUniverseSword.createAttributes(CTCEToolTiers.CATALYST, 499, 8f)
        ));
    }

    ///Medium Weapons
    public static DeferredItem<CTCEBroadSword> rgtrBroadSword() {
        return CTCE_ITEMS.register("cataclystic_broadsword", () -> new CTCEBroadSword(
                CTCEToolTiers.CATALYST, new CTCEBroadSword.Properties().fireResistant().setNoRepair().rarity(Rarity.EPIC).stacksTo(1),
                CTCEBroadSword.createToolProperties(), CTCEBroadSword.createAttributes(CTCEToolTiers.CATALYST, 14, 3.5f)
        ));
    }

    public static DeferredItem<CTCEUlfberht> rgtrUlfberht() {
        return CTCE_ITEMS.register("cataclystic_ulfberht", () -> new CTCEUlfberht(
                CTCEToolTiers.CATALYST, new CTCEUlfberht.Properties().fireResistant().setNoRepair().rarity(Rarity.EPIC).stacksTo(1),
                CTCEUlfberht.createToolProperties(), CTCEUlfberht.createAttributes(CTCEToolTiers.CATALYST, 14, 6f)
        ));
    }

    public static DeferredItem<CTCEGladius> rgtrGladius() {
        return CTCE_ITEMS.register("cataclystic_gladius", () -> new CTCEGladius(
                CTCEToolTiers.CATALYST, new CTCEGladius.Properties().fireResistant().setNoRepair().rarity(Rarity.EPIC).stacksTo(1),
                CTCEGladius.createToolProperties(), CTCEGladius.createAttributes(CTCEToolTiers.CATALYST, 14, 4.5f)
        ));
    }

    public static DeferredItem<CTCEScythe> rgtrScythe() {
        return CTCE_ITEMS.register("cataclystic_scythe", () -> new CTCEScythe(
                CTCEToolTiers.CATALYST, new CTCEScythe.Properties().fireResistant().setNoRepair().rarity(Rarity.EPIC).stacksTo(1),
                CTCEScythe.createToolProperties(), CTCEScythe.createAttributes(CTCEToolTiers.CATALYST, 14, 4f)
        ));
    }

    public static DeferredItem<CTCERapier> rgtrRapier() {
        return CTCE_ITEMS.register("cataclystic_rapier", () -> new CTCERapier(
                CTCEToolTiers.CATALYST, new CTCERapier.Properties().fireResistant().setNoRepair().rarity(Rarity.EPIC).stacksTo(1),
                CTCERapier.createToolProperties(), CTCERapier.createAttributes(CTCEToolTiers.CATALYST, 11, 6f)
        ));
    }

    public static DeferredItem<CTCEBattleAxe> rgtrBattleAxe() {
        return CTCE_ITEMS.register("cataclystic_battleaxe", () -> new CTCEBattleAxe(
                CTCEToolTiers.CATALYST, new CTCEBattleAxe.Properties().fireResistant().setNoRepair().rarity(Rarity.EPIC).stacksTo(1),
                SwordItem.createToolProperties(), CTCEBattleAxe.createAttributes(CTCEToolTiers.CATALYST, 34, -3f)
        ));
    }

    ///Light Weapons
    public static DeferredItem<CTCEKatar> rgtrKatar() {
        return CTCE_ITEMS.register("cataclystic_katar", () -> new CTCEKatar(
                CTCEToolTiers.CATALYST, new CTCEKatar.Properties().fireResistant().setNoRepair().rarity(Rarity.EPIC).stacksTo(1),
                CTCEKatar.createToolProperties(), CTCEKatar.createAttributes(CTCEToolTiers.CATALYST, 8, 14f)
        ));
    }

    ///Armor set
    ///Tools and Shield
    public static DeferredItem<CTCEPaxel> rgtrPaxel() {
        return CTCE_ITEMS.register("cataclystic_paxel", () -> new CTCEPaxel(
                CTCEToolTiers.CATALYST, new CTCEPaxel.Properties().fireResistant().setNoRepair().rarity(Rarity.EPIC).stacksTo(1),
                new Tool(List.of(Tool.Rule.overrideSpeed(BlockTags.MINEABLE_WITH_PICKAXE, 27F)), 10.0F, 15),
                CTCEPaxel.createAttributes(CTCEToolTiers.CATALYST, 39, 9f)
        ));
    }

    public static DeferredItem<CTCEPickaxe> rgtrPickaxe() {
        return CTCE_ITEMS.register("cataclystic_pickaxe", () -> new CTCEPickaxe(
                CTCEToolTiers.CATALYST, new CTCEPickaxe.Properties().fireResistant().setNoRepair().rarity(Rarity.EPIC).stacksTo(1),
                new Tool(List.of(Tool.Rule.overrideSpeed(BlockTags.MINEABLE_WITH_PICKAXE, 9f)), 10.0F, 15),
                CTCEPickaxe.createAttributes(CTCEToolTiers.CATALYST, 9, 4f)
        ));
    }

    public static DeferredItem<CTCEShovel> rgtrShovel() {
        return CTCE_ITEMS.register("cataclystic_shovel", () -> new CTCEShovel(
                CTCEToolTiers.CATALYST, new Item.Properties().fireResistant().setNoRepair().rarity(Rarity.EPIC).stacksTo(1),
                new Tool(List.of(Tool.Rule.overrideSpeed(BlockTags.MINEABLE_WITH_SHOVEL, 9F)), 10.0F, 15),
                CTCEShovel.createAttributes(CTCEToolTiers.CATALYST, 9, 4f)
        ));
    }

    public static DeferredItem<CTCEAxe> rgtrAxe() {
        return CTCE_ITEMS.register("cataclystic_axe", () -> new CTCEAxe(
                CTCEToolTiers.CATALYST, new CTCEAxe.Properties().fireResistant().setNoRepair().rarity(Rarity.EPIC).stacksTo(1),
                new Tool(List.of(Tool.Rule.overrideSpeed(BlockTags.MINEABLE_WITH_AXE, 7F)), 10.0F, 15),
                CTCEAxe.createAttributes(CTCEToolTiers.CATALYST, 14, -3f)
        ));
    }

    public static DeferredItem<CTCEShield> rgtrShield() {
        return CTCE_ITEMS.register("cataclystic_shield", () -> new CTCEShield(
                new CTCEShield.Properties().fireResistant().setNoRepair().rarity(Rarity.EPIC).stacksTo(1)));
    }
}
