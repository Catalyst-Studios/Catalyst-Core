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

import static net.radzratz.catalystcore.client.items.CTCEItems.ITEMS;

@SuppressWarnings("all")
public class CTCEItemHelpers
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
                .food(CTCEFoods.BURRITO)
                .rarity(Rarity.UNCOMMON)));
    }

    ///Heavy Weapons
    public static DeferredItem<SwordItem> registerCatalystBigBonk()
    {
        return ITEMS.register("cataclystic_big_bonk", () -> new CTCEHammer(
                CTCEToolTiers.CATALYST,
                new Item.Properties()
                        .fireResistant()
                        .setNoRepair()
                        .rarity(Rarity.EPIC)
                        .stacksTo(1),
                SwordItem.createToolProperties(),
                SwordItem.createAttributes(CTCEToolTiers.CATALYST, 69, -3.5f)
        ));
    }
    public static DeferredItem<SwordItem> registerCatalystGreatSword()
    {
        return ITEMS.register("cataclystic_greatsword", () -> new CTCEGreatSword(
                CTCEToolTiers.CATALYST,
                new Item.Properties()
                        .fireResistant()
                        .setNoRepair()
                        .rarity(Rarity.EPIC)
                        .stacksTo(1),
                SwordItem.createToolProperties(),
                SwordItem.createAttributes(CTCEToolTiers.CATALYST, 49, -2f)
        ));
    }
    public static DeferredItem<SwordItem> registerCatalystZweihander()
    {
        return ITEMS.register("cataclystic_zweihander", () -> new CTCEZweihander(
                CTCEToolTiers.CATALYST,
                new Item.Properties()
                        .fireResistant()
                        .setNoRepair()
                        .rarity(Rarity.EPIC)
                        .stacksTo(1),
                SwordItem.createToolProperties(),
                SwordItem.createAttributes(CTCEToolTiers.CATALYST, 14, 1f)
        ));
    }
    public static DeferredItem<SwordItem> registerCatalystHalberd()
    {
        return ITEMS.register("cataclystic_halberd", () -> new CTCEHalberd(
                CTCEToolTiers.CATALYST,
                new Item.Properties()
                        .fireResistant()
                        .setNoRepair()
                        .rarity(Rarity.EPIC)
                        .stacksTo(1),
                SwordItem.createToolProperties(),
                SwordItem.createAttributes(CTCEToolTiers.CATALYST, 24, 1.5f)
        ));
    }
    public static DeferredItem<SwordItem> registerUnivereSword()
    {
        return ITEMS.register("universe_sword", () -> new CTCEUniverseSword(
                CTCEToolTiers.CATALYST,
                new Item.Properties()
                        .fireResistant()
                        .setNoRepair()
                        .rarity(Rarity.EPIC)
                        .stacksTo(1),
                SwordItem.createToolProperties(),
                SwordItem.createAttributes(CTCEToolTiers.CATALYST, 499, 8f)
        ));
    }
    ///Medium Weapons
    public static DeferredItem<SwordItem> registerCatalystBroadSword()
    {
        return ITEMS.register("cataclystic_broadsword", () -> new CTCEBroadSword(
                CTCEToolTiers.CATALYST,
                new Item.Properties()
                        .fireResistant()
                        .setNoRepair()
                        .rarity(Rarity.EPIC)
                        .stacksTo(1),
                SwordItem.createToolProperties(),
                SwordItem.createAttributes(CTCEToolTiers.CATALYST, 14, 3.5f)
        ));
    }
    public static DeferredItem<SwordItem> registerCatalystUlfberht()
    {
        return ITEMS.register("cataclystic_ulfberht", () -> new CTCEUlfberht(
                CTCEToolTiers.CATALYST,
                new Item.Properties()
                        .fireResistant()
                        .setNoRepair()
                        .rarity(Rarity.EPIC)
                        .stacksTo(1),
                SwordItem.createToolProperties(),
                SwordItem.createAttributes(CTCEToolTiers.CATALYST, 14, 6f)
        ));
    }
    public static DeferredItem<SwordItem> registerCatalystGladius()
    {
        return ITEMS.register("cataclystic_gladius", () -> new CTCEGladius(
                CTCEToolTiers.CATALYST,
                new Item.Properties()
                        .fireResistant()
                        .setNoRepair()
                        .rarity(Rarity.EPIC)
                        .stacksTo(1),
                SwordItem.createToolProperties(),
                SwordItem.createAttributes(CTCEToolTiers.CATALYST, 14, 4.5f)
        ));
    }
    public static DeferredItem<SwordItem> registerCatalystScythe()
    {
        return ITEMS.register("cataclystic_scythe", () -> new CTCEScythe(
                CTCEToolTiers.CATALYST,
                new Item.Properties()
                        .fireResistant()
                        .setNoRepair()
                        .rarity(Rarity.EPIC)
                        .stacksTo(1),
                SwordItem.createToolProperties(),
                SwordItem.createAttributes(CTCEToolTiers.CATALYST, 14, 4f)
        ));
    }
    public static DeferredItem<SwordItem> registerCatalystRapier()
    {
        return ITEMS.register("cataclystic_rapier", () -> new CTCERapier(
                CTCEToolTiers.CATALYST,
                new Item.Properties()
                        .fireResistant()
                        .setNoRepair()
                        .rarity(Rarity.EPIC)
                        .stacksTo(1),
                SwordItem.createToolProperties(),
                SwordItem.createAttributes(CTCEToolTiers.CATALYST, 11, 6f)
        ));
    }
    public static DeferredItem<AxeItem> registerCatalystBattleAxe()
    {
        return ITEMS.register("cataclystic_battleaxe", () -> new CTCEBattleAxe(
                CTCEToolTiers.CATALYST,
                new Item.Properties()
                        .fireResistant()
                        .setNoRepair()
                        .rarity(Rarity.EPIC)
                        .stacksTo(1),
                SwordItem.createToolProperties(),
                AxeItem.createAttributes(CTCEToolTiers.CATALYST, 34, -3f)
        ));
    }
    ///Light Weapons
    public static DeferredItem<SwordItem> registerCatalystKatar()
    {
        return ITEMS.register("cataclystic_katar", () -> new CTCEKatar(
                CTCEToolTiers.CATALYST,
                new Item.Properties()
                        .fireResistant()
                        .setNoRepair()
                        .rarity(Rarity.EPIC)
                        .stacksTo(1),
                SwordItem.createToolProperties(),
                SwordItem.createAttributes(CTCEToolTiers.CATALYST, 8, 14f)
        ));
    }
    ///Armor set
    ///Tools and Shield
    public static DeferredItem<CTCEPaxel> registerCatalystPaxel()
    {
        return ITEMS.register("cataclystic_paxel", () -> new CTCEPaxel(
                CTCEToolTiers.CATALYST,
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
                CTCEPaxel.createAttributes(CTCEToolTiers.CATALYST, 39, 9f)
        ));
    }
    public static DeferredItem<PickaxeItem> registerCatalystPickaxe()
    {
        return ITEMS.register("cataclystic_pickaxe", () -> new CTCEPickaxe(
                CTCEToolTiers.CATALYST,
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
                PickaxeItem.createAttributes(CTCEToolTiers.CATALYST, 9, 4f)
        ));
    }
    public static DeferredItem<ShovelItem> registerCatalystShovel()
    {
        return ITEMS.register("cataclystic_shovel", () -> new CTCEShovel(
                CTCEToolTiers.CATALYST,
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
                ShovelItem.createAttributes(CTCEToolTiers.CATALYST, 9, 4f)
        ));
    }
    public static DeferredItem<AxeItem> registerCatalystAxe()
    {
        return ITEMS.register("cataclystic_axe", () -> new CTCEAxe(
                CTCEToolTiers.CATALYST,
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
                AxeItem.createAttributes(CTCEToolTiers.CATALYST, 14, -3f)
        ));
    }
    public static DeferredItem<ShieldItem> registerCatalystShield()
    {
        return ITEMS.register("cataclystic_shield", () -> new CTCEShield(
                new Item.Properties()
                        .fireResistant()
                        .setNoRepair()
                        .rarity(Rarity.EPIC)
                        .stacksTo(1)
        ));
    }
}
