package net.radzratz.catalystcore.client.items;

import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.radzratz.catalystcore.CatalystCore;
import net.radzratz.catalystcore.client.items.custom.PentagramItem;
import net.radzratz.catalystcore.client.items.tools.tools.CTCEPaxel;

import static net.radzratz.catalystcore.client.items.CTCEItemHelpers.*;

@SuppressWarnings("all")
public class CTCEItems
{
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CatalystCore.id);

    //Items
    public static final DeferredItem<Item> FULL_CATALYST = registerCatalystCoreEndGameItems("complete_catalyst");
    public static final DeferredItem<Item> ARES = registerCatalystCoreEndGameItems("a.r.e.s");
    public static final DeferredItem<Item> ETERNAL_VORTEX = registerCatalystCoreEndGameItems("eternal_vortex");
    public static final DeferredItem<Item> MAGIC_ANOMALY = registerCatalystCoreEndGameItems("magic_anomaly");
    public static final DeferredItem<Item> RIFT = registerCatalystCoreEndGameItems("crystallized_rift");
    public static final DeferredItem<Item> CONTAINER = registerCatalystCoreEndGameItems("universe_containment_cell");
    public static final DeferredItem<Item> MALICIOUS_EYE = registerCatalystCoreEndGameItems("malicious_eye");
    public static final DeferredItem<Item> TREASURE = registerCatalystCoreEndGameItems("explorers_treasure");
    public static final DeferredItem<Item> BLACK_HOLE = registerCatalystCoreEndGameItems("black_hole");
    public static final DeferredItem<Item> CHOCOLATE_BAR = registerCatalystCoreEndGameItems("chocolate_bar");
    public static final DeferredItem<Item> COSMIC_SHATTERER = registerCatalystCoreEndGameItems("cosmic_shatterer");
    public static final DeferredItem<Item> FORBIDDEN_ORB = registerCatalystCoreEndGameItems("forbidden_artifact");
    public static final DeferredItem<Item> CHAOS_CRYSTAL = registerCatalystCoreEndGameItems("fractured_chaos_seed");
    public static final DeferredItem<Item> SPIRIT_AGGLOMERATIO = registerCatalystCoreEndGameItems("spirit_agglomeratio");
    public static final DeferredItem<Item> COSMIC_ABOMINATION = registerCatalystCoreEndGameItems("cosmic_abomination");
    public static final DeferredItem<Item> ICAROS = registerCatalystCoreEndGameItems("icaros");
    public static final DeferredItem<Item> ANCIENT_RELIC = registerCatalystCoreEndGameItems("ancient_relic");

    public static final DeferredItem<Item> FRAME_CATALYST = registerCatalystCoreMidGameItems("empty_catalyst");
    public static final DeferredItem<Item> SELF_AWARE_CHIP = registerCatalystCoreMidGameItems("nano_self_aware");
    public static final DeferredItem<Item> COMET_SHARD = registerCatalystCoreMidGameItems("comet_shard");
    public static final DeferredItem<Item> CONTAINER_INACTIVE = registerCatalystCoreMidGameItems("container_cell");
    public static final DeferredItem<Item> WATER_ORB = registerCatalystCoreMidGameItems("water_orb");
    public static final DeferredItem<Item> END_CORE = registerCatalystCoreMidGameItems("end_core");
    public static final DeferredItem<Item> NETHER_CORE = registerCatalystCoreMidGameItems("nether_core");
    public static final DeferredItem<Item> OVERWORLD_CORE = registerCatalystCoreMidGameItems("overworld_core");
    public static final DeferredItem<Item> LAVA_ORB = registerCatalystCoreMidGameItems("lava_orb");
    public static final DeferredItem<Item> WARDEN_CORE = registerCatalystCoreMidGameItems("warden_core");
    public static final DeferredItem<Item> ELDER_CORE = registerCatalystCoreMidGameItems("elder_core");
    public static final DeferredItem<Item> WITHER_CORE = registerCatalystCoreMidGameItems("wither_core");
    public static final DeferredItem<Item> DRAGON_CORE = registerCatalystCoreMidGameItems("dragon_core");
    public static final DeferredItem<Item> EMPTY_CRYSTAL = registerCatalystCoreMidGameItems("elemental_emptiness_crystal");
    public static final DeferredItem<Item> FIRE_CRYSTAL = registerCatalystCoreMidGameItems("elemental_fire_crystal");
    public static final DeferredItem<Item> WATER_CRYSTAL = registerCatalystCoreMidGameItems("elemental_water_crystal");
    public static final DeferredItem<Item> EARTH_CRYSTAL = registerCatalystCoreMidGameItems("elemental_earth_crystal");
    public static final DeferredItem<Item> WIND_CRYSTAL = registerCatalystCoreMidGameItems("elemental_wind_crystal");
    public static final DeferredItem<Item> LIGHT_CRYSTAL = registerCatalystCoreMidGameItems("elemental_light_crystal");
    public static final DeferredItem<Item> DARKNESS_CRYSTAL = registerCatalystCoreMidGameItems("elemental_darkness_crystal");
    public static final DeferredItem<Item> ANTIMATTER = registerCatalystCoreMidGameItems("antimatter_cell");

    public static final DeferredItem<Item> DRAGON_BLOOD = registerCatalystCoreBottleItems("dragon_blood_bottle");
    public static final DeferredItem<Item> WITHERED_BOTTLE = registerCatalystCoreBottleItems("withered_essence_bottle");
    public static final DeferredItem<Item> SCULK_BOTTLE = registerCatalystCoreBottleItems("sculk_bottle");
    public static final DeferredItem<Item> TORCHFLOWER_BOTTLE = registerCatalystCoreBottleItems("torchflower_bottle");
    public static final DeferredItem<Item> MYCELIUM_BOTTLE = registerCatalystCoreBottleItems("mycelium_bottle");
    public static final DeferredItem<Item> LIFE_ESSENCE_BOTTLE = registerCatalystCoreBottleItems("life_essence_bottle");
    public static final DeferredItem<Item> PIXIE_BOTTLE = registerCatalystCoreBottleItems("pixie_essence");
    public static final DeferredItem<Item> REINFORCED_BOTTLE = registerCatalystCoreBottleItems("reinforced_bottle");

    public static final DeferredItem<Item> CODEX = registerCatalystCoreBasicItems("universe_codex");

    public static final DeferredItem<Item> RUNIC_EMPTY = registerCatalystCoreBasicItems("runic_slate");
    public static final DeferredItem<Item> RUNIC_BLOOD = registerCatalystCoreBasicItems("runic_blood_slate");
    public static final DeferredItem<Item> RUNIC_ARCANE = registerCatalystCoreBasicItems("runic_arcane_slate");
    public static final DeferredItem<Item> INFECTED_SLATE = registerCatalystCoreBasicItems("infected_slate");
    public static final DeferredItem<Item> RUNIC_ENDER = registerCatalystCoreBasicItems("runic_ender_slate");
    public static final DeferredItem<Item> RUNIC_ESOTHERICAL = registerCatalystCoreBasicItems("esotherical_runic_slate");

    public static final DeferredItem<Item> CATALYST_INGOT = registerCatalystCoreNormalItems("cataclystic_ingot");

    public static final DeferredItem<Item> BURRITO = registerCatalystCoreFoods("burrito");

    public static final DeferredItem<PentagramItem> PENTAGRAM = ITEMS.register("pentagram",
            () -> new PentagramItem(new Item.Properties()
                    .stacksTo(1)
                    .rarity(Rarity.RARE)
                    .fireResistant()));

    //Tools and Weapons
    public static final DeferredItem<SwordItem> CATALYST_GREATSWORD = registerCatalystGreatSword();
    public static final DeferredItem<SwordItem> CATALYST_ZWEIHANDER = registerCatalystZweihander();
    public static final DeferredItem<SwordItem> CATALYST_ULFBERHT = registerCatalystUlfberht();
    public static final DeferredItem<SwordItem> CATALYST_GLADIUS = registerCatalystGladius();
    public static final DeferredItem<SwordItem> CATALYST_SCYTHE = registerCatalystScythe();
    public static final DeferredItem<SwordItem> CATALYST_KATAR = registerCatalystKatar();
    public static final DeferredItem<SwordItem> CATALYST_BIG_BONK = registerCatalystBigBonk();
    public static final DeferredItem<SwordItem> CATALYST_BROADSWORD = registerCatalystBroadSword();
    public static final DeferredItem<SwordItem> CATALYST_HALBERD = registerCatalystHalberd();
    public static final DeferredItem<ShieldItem> CATALYST_SHIELD = registerCatalystShield();
    public static final DeferredItem<AxeItem> CATALYST_BATTLEAXE = registerCatalystBattleAxe();
    public static final DeferredItem<SwordItem> CATALYST_RAPIER = registerCatalystRapier();
    public static final DeferredItem<SwordItem> UNIVERSE_SWORD = registerUnivereSword();

    public static final DeferredItem<PickaxeItem> CATALYST_PICKAXE = registerCatalystPickaxe();
    public static final DeferredItem<CTCEPaxel> CATALYST_PAXEL = registerCatalystPaxel();
    public static final DeferredItem<ShovelItem> CATALYST_SHOVEL = registerCatalystShovel();
    public static final DeferredItem<AxeItem> CATALYST_AXE = registerCatalystAxe();

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
