package net.radzratz.catalystcore.items;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Tool;
import net.neoforged.neoforge.registries.DeferredItem;
import net.radzratz.catalystcore.items.foods.CatalystFoods;
import net.radzratz.catalystcore.items.tools.CatalystToolTiers;
import net.radzratz.catalystcore.items.tools.tools.CatalystAxe;
import net.radzratz.catalystcore.items.tools.tools.CatalystPaxel;
import net.radzratz.catalystcore.items.tools.tools.CatalystPickaxe;
import net.radzratz.catalystcore.items.tools.tools.CatalystShovel;
import net.radzratz.catalystcore.items.tools.weapons.*;

import java.util.List;

import static net.radzratz.catalystcore.items.CatalystItems.ITEMS;

@SuppressWarnings("all")
public class CatalystItemHelpers
{

    public static DeferredItem<Item> registerCatalystCoreEndGameItems(String name)
    {
        return ITEMS.register(name, () -> new Item(new Item.Properties()
                .fireResistant()
                .rarity(Rarity.EPIC)));
    }
    public static DeferredItem<Item> registerCatalystCoreMidGameItems(String name)
    {
        return ITEMS.register(name, () -> new Item(new Item.Properties()
                .fireResistant()
                .rarity(Rarity.RARE)));
    }
    public static DeferredItem<Item> registerCatalystCoreNormalItems(String name)
    {
        return ITEMS.register(name, () -> new Item(new Item.Properties()));
    }
    public static DeferredItem<Item> registerCatalystCoreBasicItems(String name)
    {
        return ITEMS.register(name, () -> new Item(new Item.Properties()
                .rarity(Rarity.UNCOMMON)));
    }
    public static DeferredItem<Item> registerCatalystCoreBottleItems(String name)
    {
        return ITEMS.register(name, () -> new Item(new Item.Properties()
                .stacksTo(16)
                .rarity(Rarity.UNCOMMON)));
    }
    public static DeferredItem<Item> registerCatalystCoreFoods(String name)
    {
        return ITEMS.register(name, () -> new Item(new Item.Properties()
                .food(CatalystFoods.BURRITO)
                .rarity(Rarity.UNCOMMON)));
    }

    ///Heavy Weapons
    public static DeferredItem<SwordItem> registerCatalystBigBonk()
    {
        return ITEMS.register("cataclystic_big_bonk", () -> new CatalystBigBonk(
                CatalystToolTiers.CATALYST,
                new Item.Properties()
                        .fireResistant()
                        .setNoRepair()
                        .rarity(Rarity.EPIC)
                        .stacksTo(1),
                SwordItem.createToolProperties(),
                SwordItem.createAttributes(CatalystToolTiers.CATALYST, 69, -3.5f)
        ));
    }
    public static DeferredItem<SwordItem> registerCatalystGreatSword()
    {
        return ITEMS.register("cataclystic_greatsword", () -> new CatalystGreatSword(
                CatalystToolTiers.CATALYST,
                new Item.Properties()
                        .fireResistant()
                        .setNoRepair()
                        .rarity(Rarity.EPIC)
                        .stacksTo(1),
                SwordItem.createToolProperties(),
                SwordItem.createAttributes(CatalystToolTiers.CATALYST, 49, -2f)
        ));
    }
    public static DeferredItem<SwordItem> registerCatalystZweihander()
    {
        return ITEMS.register("cataclystic_zweihander", () -> new CatalystZweihander(
                CatalystToolTiers.CATALYST,
                new Item.Properties()
                        .fireResistant()
                        .setNoRepair()
                        .rarity(Rarity.EPIC)
                        .stacksTo(1),
                SwordItem.createToolProperties(),
                SwordItem.createAttributes(CatalystToolTiers.CATALYST, 14, 1f)
        ));
    }
    public static DeferredItem<SwordItem> registerCatalystHalberd()
    {
        return ITEMS.register("cataclystic_halberd", () -> new CatalystHalberd(
                CatalystToolTiers.CATALYST,
                new Item.Properties()
                        .fireResistant()
                        .setNoRepair()
                        .rarity(Rarity.EPIC)
                        .stacksTo(1),
                SwordItem.createToolProperties(),
                SwordItem.createAttributes(CatalystToolTiers.CATALYST, 24, 1.5f)
        ));
    }
    public static DeferredItem<SwordItem> registerUnivereSword()
    {
        return ITEMS.register("universe_sword", () -> new CatalystUniverseSword(
                CatalystToolTiers.CATALYST,
                new Item.Properties()
                        .fireResistant()
                        .setNoRepair()
                        .rarity(Rarity.EPIC)
                        .stacksTo(1),
                SwordItem.createToolProperties(),
                SwordItem.createAttributes(CatalystToolTiers.CATALYST, 499, 8f)
        ));
    }
    ///Medium Weapons
    public static DeferredItem<SwordItem> registerCatalystBroadSword()
    {
        return ITEMS.register("cataclystic_broadsword", () -> new CatalystBroadSword(
                CatalystToolTiers.CATALYST,
                new Item.Properties()
                        .fireResistant()
                        .setNoRepair()
                        .rarity(Rarity.EPIC)
                        .stacksTo(1),
                SwordItem.createToolProperties(),
                SwordItem.createAttributes(CatalystToolTiers.CATALYST, 14, 3.5f)
        ));
    }
    public static DeferredItem<SwordItem> registerCatalystUlfberht()
    {
        return ITEMS.register("cataclystic_ulfberht", () -> new CatalystUlfberht(
                CatalystToolTiers.CATALYST,
                new Item.Properties()
                        .fireResistant()
                        .setNoRepair()
                        .rarity(Rarity.EPIC)
                        .stacksTo(1),
                SwordItem.createToolProperties(),
                SwordItem.createAttributes(CatalystToolTiers.CATALYST, 14, 6f)
        ));
    }
    public static DeferredItem<SwordItem> registerCatalystGladius()
    {
        return ITEMS.register("cataclystic_gladius", () -> new CatalystGladius(
                CatalystToolTiers.CATALYST,
                new Item.Properties()
                        .fireResistant()
                        .setNoRepair()
                        .rarity(Rarity.EPIC)
                        .stacksTo(1),
                SwordItem.createToolProperties(),
                SwordItem.createAttributes(CatalystToolTiers.CATALYST, 14, 4.5f)
        ));
    }
    public static DeferredItem<SwordItem> registerCatalystScythe()
    {
        return ITEMS.register("cataclystic_scythe", () -> new CatalystScythe(
                CatalystToolTiers.CATALYST,
                new Item.Properties()
                        .fireResistant()
                        .setNoRepair()
                        .rarity(Rarity.EPIC)
                        .stacksTo(1),
                SwordItem.createToolProperties(),
                SwordItem.createAttributes(CatalystToolTiers.CATALYST, 14, 4f)
        ));
    }
    public static DeferredItem<SwordItem> registerCatalystRapier()
    {
        return ITEMS.register("cataclystic_rapier", () -> new CatalystRapier(
                CatalystToolTiers.CATALYST,
                new Item.Properties()
                        .fireResistant()
                        .setNoRepair()
                        .rarity(Rarity.EPIC)
                        .stacksTo(1),
                SwordItem.createToolProperties(),
                SwordItem.createAttributes(CatalystToolTiers.CATALYST, 11, 6f)
        ));
    }
    public static DeferredItem<AxeItem> registerCatalystBattleAxe()
    {
        return ITEMS.register("cataclystic_battleaxe", () -> new CatalystBattleAxe(
                CatalystToolTiers.CATALYST,
                new Item.Properties()
                        .fireResistant()
                        .setNoRepair()
                        .rarity(Rarity.EPIC)
                        .stacksTo(1),
                SwordItem.createToolProperties(),
                AxeItem.createAttributes(CatalystToolTiers.CATALYST, 34, -3f)
        ));
    }
    ///Light Weapons
    public static DeferredItem<SwordItem> registerCatalystKatar()
    {
        return ITEMS.register("cataclystic_katar", () -> new CatalystKatar(
                CatalystToolTiers.CATALYST,
                new Item.Properties()
                        .fireResistant()
                        .setNoRepair()
                        .rarity(Rarity.EPIC)
                        .stacksTo(1),
                SwordItem.createToolProperties(),
                SwordItem.createAttributes(CatalystToolTiers.CATALYST, 8, 14f)
        ));
    }
    ///Armor set
    ///Tools and Shield
    public static DeferredItem<CatalystPaxel> registerCatalystPaxel()
    {
        return ITEMS.register("cataclystic_paxel", () -> new CatalystPaxel(
                CatalystToolTiers.CATALYST,
                new Item.Properties()
                        .fireResistant()
                        .setNoRepair()
                        .rarity(Rarity.EPIC)
                        .stacksTo(1),
                new Tool(
                        List.of(Tool.Rule.overrideSpeed(BlockTags.MINEABLE_WITH_PICKAXE, 27F)),
                        10.0F,
                        15
                ),
                CatalystPaxel.createAttributes(CatalystToolTiers.CATALYST, 39, 9f)
        ));
    }
    public static DeferredItem<PickaxeItem> registerCatalystPickaxe()
    {
        return ITEMS.register("cataclystic_pickaxe", () -> new CatalystPickaxe(
                CatalystToolTiers.CATALYST,
                new Item.Properties()
                        .fireResistant()
                        .setNoRepair()
                        .rarity(Rarity.EPIC)
                        .stacksTo(1),
                new Tool(
                        List.of(Tool.Rule.overrideSpeed(BlockTags.MINEABLE_WITH_PICKAXE, 9F)),
                        10.0F,
                        15
                ),
                PickaxeItem.createAttributes(CatalystToolTiers.CATALYST, 9, 4f)
        ));
    }
    public static DeferredItem<ShovelItem> registerCatalystShovel()
    {
        return ITEMS.register("cataclystic_shovel", () -> new CatalystShovel(
                CatalystToolTiers.CATALYST,
                new Item.Properties()
                        .fireResistant()
                        .setNoRepair()
                        .rarity(Rarity.EPIC)
                        .stacksTo(1),
                new Tool(
                        List.of(Tool.Rule.overrideSpeed(BlockTags.MINEABLE_WITH_SHOVEL, 9F)),
                        10.0F,
                        15
                ),
                ShovelItem.createAttributes(CatalystToolTiers.CATALYST, 9, 4f)
        ));
    }
    public static DeferredItem<AxeItem> registerCatalystAxe()
    {
        return ITEMS.register("cataclystic_axe", () -> new CatalystAxe(
                CatalystToolTiers.CATALYST,
                new Item.Properties()
                        .fireResistant()
                        .setNoRepair()
                        .rarity(Rarity.EPIC)
                        .stacksTo(1),
                new Tool(
                        List.of(Tool.Rule.overrideSpeed(BlockTags.MINEABLE_WITH_AXE, 7F)),
                        10.0F,
                        15
                ),
                AxeItem.createAttributes(CatalystToolTiers.CATALYST, 14, -3f)
        ));
    }
    public static DeferredItem<ShieldItem> registerCatalystShield()
    {
        return ITEMS.register("cataclystic_shield", () -> new CatalystShield(
                new Item.Properties()
                        .fireResistant()
                        .setNoRepair()
                        .rarity(Rarity.EPIC)
                        .stacksTo(1)
        ));
    }
}
