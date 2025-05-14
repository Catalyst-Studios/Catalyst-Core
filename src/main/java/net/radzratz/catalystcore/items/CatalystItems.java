package net.radzratz.catalystcore.items;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Tool;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.radzratz.catalystcore.CatalystCore;
import net.radzratz.catalystcore.custom.PentagramItem;
import net.radzratz.catalystcore.items.tools.tools.CatalystPickaxe;
import net.radzratz.catalystcore.items.tools.tools.CatalystShovel;
import net.radzratz.catalystcore.items.tools.weapons.*;

import java.util.List;

public class CatalystItems
{
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CatalystCore.MOD_ID);

    private static DeferredItem<Item> registerCatalystCoreEndGameItems(String name)
    {
        return ITEMS.register(name, () -> new Item(new Item.Properties()
                .fireResistant()
                .rarity(Rarity.EPIC)));
    }
    private static DeferredItem<Item> registerCatalystCoreMidGameItems(String name)
    {
        return ITEMS.register(name, () -> new Item(new Item.Properties()
                .fireResistant()
                .rarity(Rarity.RARE)));
    }
    private static DeferredItem<Item> registerCatalystCoreNormalItems(String name)
    {
        return ITEMS.register(name, () -> new Item(new Item.Properties()));
    }

    //Items
    public static final DeferredItem<Item> FULL_CATALYST = registerCatalystCoreEndGameItems("complete_catalyst");
    public static final DeferredItem<Item> BLOOD_VORTEX = registerCatalystCoreEndGameItems("blood_vortex");
    public static final DeferredItem<Item> MAGIC_ANOMALY = registerCatalystCoreEndGameItems("magic_anomaly");
    public static final DeferredItem<Item> ANTIMATTER = registerCatalystCoreEndGameItems("pure_antimatter");
    public static final DeferredItem<Item> RIFT = registerCatalystCoreEndGameItems("crystallized_rift");
    public static final DeferredItem<Item> CONTAINER = registerCatalystCoreEndGameItems("universe_containment_cell");
    public static final DeferredItem<Item> MALICIOUS_EYE = registerCatalystCoreEndGameItems("malicious_eye");
    public static final DeferredItem<Item> TREASURE = registerCatalystCoreEndGameItems("explorers_treasure");
    public static final DeferredItem<Item> BLACK_HOLE = registerCatalystCoreEndGameItems("black_hole");
    public static final DeferredItem<Item> CHOCOLATE_BAR = registerCatalystCoreEndGameItems("chocolate_bar");
    public static final DeferredItem<Item> COSMIC_SHATTERER = registerCatalystCoreEndGameItems("cosmic_shatterer");

    public static final DeferredItem<Item> FRAME_CATALYST = registerCatalystCoreMidGameItems("empty_catalyst");
    public static final DeferredItem<Item> SELF_AWARE_CHIP = registerCatalystCoreMidGameItems("nano_self_aware");
    /*public static final DeferredItem<Item> PENTAGRAM = registerCatalystCoreMidGameItems("pentagram");*/
    public static final DeferredItem<Item> COMET_SHARD = registerCatalystCoreMidGameItems("comet_shard");
    public static final DeferredItem<Item> CONTAINER_INACTIVE = registerCatalystCoreMidGameItems("container_cell");
    public static final DeferredItem<Item> WATER_ORB = registerCatalystCoreMidGameItems("water_orb");
    public static final DeferredItem<Item> END_CORE = registerCatalystCoreMidGameItems("end_core");
    public static final DeferredItem<Item> NETHER_CORE = registerCatalystCoreMidGameItems("nether_core");
    public static final DeferredItem<Item> OVERWORLD_CORE = registerCatalystCoreMidGameItems("overworld_core");

    public static final DeferredItem<PentagramItem> PENTAGRAM = ITEMS.register("pentagram",
            () -> new PentagramItem(new Item.Properties()
                    .stacksTo(1)
                    .rarity(Rarity.RARE)
                    .fireResistant()));

    public static final DeferredItem<Item> CATALYST_INGOT = registerCatalystCoreNormalItems("cataclystic_ingot");
    public static final DeferredItem<Item> RAW_CATALYRIUM = registerCatalystCoreNormalItems("raw_catalyrium");
    public static final DeferredItem<Item> CALTARITE = registerCatalystCoreNormalItems("caltarite_gem");

    //Tools and Weapons
    public static final DeferredItem<SwordItem> CATALYST_SWORD = registerCatalystLongSword();
    public static final DeferredItem<SwordItem> CATALYST_GLADIUS = registerCatalystGladius();
    public static final DeferredItem<SwordItem> CATALYST_SCYTHE = registerCatalystScythe();
    public static final DeferredItem<SwordItem> CATALYST_KATAR = registerCatalystKatar();
    public static final DeferredItem<SwordItem> CATALYST_BIG_BONK = registerCatalystBigBonk();
    public static final DeferredItem<SwordItem> CATALYST_BROADSWORD = registerCatalystBroadSword();
    public static final DeferredItem<SwordItem> CATALYST_HALBERD = registerCatalystHalberd();
    public static final DeferredItem<PickaxeItem> CATALYST_PICKAXE = registerCatalystPickaxe();
    public static final DeferredItem<ShovelItem> CATALYST_SHOVEL = registerCatalystShovel();
    public static final DeferredItem<ShieldItem> CATALYST_SHIELD = registerCatalystShield();

    private static DeferredItem<SwordItem> registerCatalystBigBonk()
    {
        return ITEMS.register("cataclystic_big_bonk", () -> new CatalystBigBonk(
                CatalystToolTiers.CATALYST,
                new Item.Properties()
                        .fireResistant()
                        .setNoRepair()
                        .rarity(Rarity.EPIC)
                        .stacksTo(1),
                SwordItem.createToolProperties(),
                SwordItem.createAttributes(CatalystToolTiers.CATALYST, 80, 1f)
        ));
    }
    private static DeferredItem<SwordItem> registerCatalystLongSword()
    {
        return ITEMS.register("cataclystic_sword", () -> new CatalystLongSword(
                CatalystToolTiers.CATALYST,
                new Item.Properties()
                        .fireResistant()
                        .setNoRepair()
                        .rarity(Rarity.EPIC)
                        .stacksTo(1),
                SwordItem.createToolProperties(),
                SwordItem.createAttributes(CatalystToolTiers.CATALYST, 50, 3f)
        ));
    }
    private static DeferredItem<SwordItem> registerCatalystGladius()
    {
        return ITEMS.register("cataclystic_gladius", () -> new CatalystGladius(
                CatalystToolTiers.CATALYST,
                new Item.Properties()
                        .fireResistant()
                        .setNoRepair()
                        .rarity(Rarity.EPIC)
                        .stacksTo(1),
                SwordItem.createToolProperties(),
                SwordItem.createAttributes(CatalystToolTiers.CATALYST, 50, 3f)
        ));
    }
    private static DeferredItem<SwordItem> registerCatalystScythe()
    {
        return ITEMS.register("cataclystic_scythe", () -> new CatalystScythe(
                CatalystToolTiers.CATALYST,
                new Item.Properties()
                        .fireResistant()
                        .setNoRepair()
                        .rarity(Rarity.EPIC)
                        .stacksTo(1),
                SwordItem.createToolProperties(),
                SwordItem.createAttributes(CatalystToolTiers.CATALYST, 50, 4f)
        ));
    }
    private static DeferredItem<SwordItem> registerCatalystBroadSword()
    {
        return ITEMS.register("cataclystic_broadsword", () -> new CatalystBroadSword(
                CatalystToolTiers.CATALYST,
                new Item.Properties()
                        .fireResistant()
                        .setNoRepair()
                        .rarity(Rarity.EPIC)
                        .stacksTo(1),
                SwordItem.createToolProperties(),
                SwordItem.createAttributes(CatalystToolTiers.CATALYST, 30, 5f)
        ));
    }
    private static DeferredItem<SwordItem> registerCatalystKatar()
    {
        return ITEMS.register("cataclystic_katar", () -> new CatalystKatar(
                CatalystToolTiers.CATALYST,
                new Item.Properties()
                        .fireResistant()
                        .setNoRepair()
                        .rarity(Rarity.EPIC)
                        .stacksTo(1),
                SwordItem.createToolProperties(),
                SwordItem.createAttributes(CatalystToolTiers.CATALYST, 15, 9f)
        ));
    }
    private static DeferredItem<SwordItem> registerCatalystHalberd()
    {
        return ITEMS.register("cataclystic_halberd", () -> new CatalystHalberd(
                CatalystToolTiers.CATALYST,
                new Item.Properties()
                        .fireResistant()
                        .setNoRepair()
                        .rarity(Rarity.EPIC)
                        .stacksTo(1),
                SwordItem.createToolProperties(),
                SwordItem.createAttributes(CatalystToolTiers.CATALYST, 45, 2f)
        ));
    }
    private static DeferredItem<PickaxeItem> registerCatalystPickaxe()
    {
        return ITEMS.register("cataclystic_pickaxe", () -> new CatalystPickaxe(
                CatalystToolTiers.CATALYST,
                new Item.Properties()
                        .fireResistant()
                        .setNoRepair()
                        .rarity(Rarity.EPIC)
                        .stacksTo(1),
                new Tool(
                        List.of(Tool.Rule.overrideSpeed(BlockTags.MINEABLE_WITH_PICKAXE, 1.5F)),
                        4.0F,
                        4
                ),
                PickaxeItem.createAttributes(CatalystToolTiers.CATALYST, 25, 5f)
        ));
    }
    private static DeferredItem<ShovelItem> registerCatalystShovel()
    {
        return ITEMS.register("cataclystic_shovel", () -> new CatalystShovel(
                CatalystToolTiers.CATALYST,
                new Item.Properties()
                        .fireResistant()
                        .setNoRepair()
                        .rarity(Rarity.EPIC)
                        .stacksTo(1),
                new Tool(
                        List.of(Tool.Rule.overrideSpeed(BlockTags.MINEABLE_WITH_SHOVEL, 1.5F)),
                        4.0F,
                        4
                ),
                ShovelItem.createAttributes(CatalystToolTiers.CATALYST, 25, 5f)
        ));
    }
    private static DeferredItem<ShieldItem> registerCatalystShield() {
        return ITEMS.register("cataclystic_shield", () -> new CatalystShield(
                new Item.Properties()
                        .fireResistant()
                        .setNoRepair()
                        .rarity(Rarity.EPIC)
                        .stacksTo(1)
        ));
    }

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
