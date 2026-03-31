package net.radzratz.catalystcore.client.items;

import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.radzratz.catalystcore.CatalystCore;
import net.radzratz.catalystcore.client.items.custom.PentagramItem;
import net.radzratz.catalystcore.client.items.tools.tools.CTCEAxe;
import net.radzratz.catalystcore.client.items.tools.tools.CTCEPaxel;
import net.radzratz.catalystcore.client.items.tools.tools.CTCEPickaxe;
import net.radzratz.catalystcore.client.items.tools.tools.CTCEShovel;
import net.radzratz.catalystcore.client.items.tools.weapons.*;

import static net.radzratz.catalystcore.client.items.CTCEItemHelpers.*;

@SuppressWarnings("all")
public class CTCEItems {
    public static final DeferredRegister.Items CTCE_ITEMS = DeferredRegister.createItems(CatalystCore.id);

    //Items
    public static final DeferredItem<Item> FULL_CATALYST = rgtrEndGameItems("complete_catalyst");
    public static final DeferredItem<Item> ARES = rgtrEndGameItems("a.r.e.s");
    public static final DeferredItem<Item> ETERNAL_VORTEX = rgtrEndGameItems("eternal_vortex");
    public static final DeferredItem<Item> MAGIC_ANOMALY = rgtrEndGameItems("magic_anomaly");
    public static final DeferredItem<Item> RIFT = rgtrEndGameItems("crystallized_rift");
    public static final DeferredItem<Item> CONTAINER = rgtrEndGameItems("universe_containment_cell");
    public static final DeferredItem<Item> MALICIOUS_EYE = rgtrEndGameItems("malicious_eye");
    public static final DeferredItem<Item> TREASURE = rgtrEndGameItems("explorers_treasure");
    public static final DeferredItem<Item> BLACK_HOLE = rgtrEndGameItems("black_hole");
    public static final DeferredItem<Item> CHOCOLATE_BAR = rgtrEndGameItems("chocolate_bar");
    public static final DeferredItem<Item> COSMIC_SHATTERER = rgtrEndGameItems("cosmic_shatterer");
    public static final DeferredItem<Item> FORBIDDEN_ORB = rgtrEndGameItems("forbidden_artifact");
    public static final DeferredItem<Item> CHAOS_CRYSTAL = rgtrEndGameItems("fractured_chaos_seed");
    public static final DeferredItem<Item> SPIRIT_AGGLOMERATIO = rgtrEndGameItems("spirit_agglomeratio");
    public static final DeferredItem<Item> COSMIC_ABOMINATION = rgtrEndGameItems("cosmic_abomination");
    public static final DeferredItem<Item> ICAROS = rgtrEndGameItems("icaros");
    public static final DeferredItem<Item> ANCIENT_RELIC = rgtrEndGameItems("ancient_relic");

    public static final DeferredItem<Item> FRAME_CATALYST = rgtrMidGameItems("empty_catalyst");
    public static final DeferredItem<Item> SELF_AWARE_CHIP = rgtrMidGameItems("nano_self_aware");
    public static final DeferredItem<Item> COMET_SHARD = rgtrMidGameItems("comet_shard");
    public static final DeferredItem<Item> CONTAINER_INACTIVE = rgtrMidGameItems("container_cell");
    public static final DeferredItem<Item> WATER_ORB = rgtrMidGameItems("water_orb");
    public static final DeferredItem<Item> END_CORE = rgtrMidGameItems("end_core");
    public static final DeferredItem<Item> NETHER_CORE = rgtrMidGameItems("nether_core");
    public static final DeferredItem<Item> OVERWORLD_CORE = rgtrMidGameItems("overworld_core");
    public static final DeferredItem<Item> LAVA_ORB = rgtrMidGameItems("lava_orb");
    public static final DeferredItem<Item> WARDEN_CORE = rgtrMidGameItems("warden_core");
    public static final DeferredItem<Item> ELDER_CORE = rgtrMidGameItems("elder_core");
    public static final DeferredItem<Item> WITHER_CORE = rgtrMidGameItems("wither_core");
    public static final DeferredItem<Item> DRAGON_CORE = rgtrMidGameItems("dragon_core");
    public static final DeferredItem<Item> EMPTY_CRYSTAL = rgtrMidGameItems("elemental_emptiness_crystal");
    public static final DeferredItem<Item> FIRE_CRYSTAL = rgtrMidGameItems("elemental_fire_crystal");
    public static final DeferredItem<Item> WATER_CRYSTAL = rgtrMidGameItems("elemental_water_crystal");
    public static final DeferredItem<Item> EARTH_CRYSTAL = rgtrMidGameItems("elemental_earth_crystal");
    public static final DeferredItem<Item> WIND_CRYSTAL = rgtrMidGameItems("elemental_wind_crystal");
    public static final DeferredItem<Item> LIGHT_CRYSTAL = rgtrMidGameItems("elemental_light_crystal");
    public static final DeferredItem<Item> DARKNESS_CRYSTAL = rgtrMidGameItems("elemental_darkness_crystal");
    public static final DeferredItem<Item> ANTIMATTER = rgtrMidGameItems("antimatter_cell");
    public static final DeferredItem<Item> CONCENTRATED_RADIANCE = rgtrMidGameItems("concentrated_radiance");
    public static final DeferredItem<Item> BOUND_VOIDSPAWN = rgtrMidGameItems("bound_voidspawn");

    public static final DeferredItem<Item> DRAGON_BLOOD = rgtrBottles("dragon_blood_bottle");
    public static final DeferredItem<Item> WITHERED_BOTTLE = rgtrBottles("withered_essence_bottle");
    public static final DeferredItem<Item> SCULK_BOTTLE = rgtrBottles("sculk_bottle");
    public static final DeferredItem<Item> TORCHFLOWER_BOTTLE = rgtrBottles("torchflower_bottle");
    public static final DeferredItem<Item> MYCELIUM_BOTTLE = rgtrBottles("mycelium_bottle");
    public static final DeferredItem<Item> LIFE_ESSENCE_BOTTLE = rgtrBottles("life_essence_bottle");
    public static final DeferredItem<Item> PIXIE_BOTTLE = rgtrBottles("pixie_essence");
    public static final DeferredItem<Item> REINFORCED_BOTTLE = rgtrBottles("reinforced_bottle");

    public static final DeferredItem<Item> CODEX = rgtrUncommonItems("universe_codex");
    public static final DeferredItem<Item> RUNIC_EMPTY = rgtrUncommonItems("runic_slate");
    public static final DeferredItem<Item> RUNIC_BLOOD = rgtrUncommonItems("runic_blood_slate");
    public static final DeferredItem<Item> RUNIC_ARCANE = rgtrUncommonItems("runic_arcane_slate");
    public static final DeferredItem<Item> INFECTED_SLATE = rgtrUncommonItems("infected_slate");
    public static final DeferredItem<Item> RUNIC_ENDER = rgtrUncommonItems("runic_ender_slate");
    public static final DeferredItem<Item> RUNIC_ESOTHERICAL = rgtrUncommonItems("esotherical_runic_slate");

    public static final DeferredItem<Item> EMPTY_CONTAINMENT_VESSEL = rgtrUncommonItems("empty_containment_vessel");

    public static final DeferredItem<Item> BURRITO = rgtrFoods("burrito");

    public static final DeferredItem<PentagramItem> PENTAGRAM = CTCE_ITEMS.register("pentagram",
            () -> new PentagramItem(new PentagramItem.Properties().stacksTo(1).rarity(Rarity.RARE).fireResistant()));

    //Tools and Weapons
    // Todo: Move these to a different class
    public static final DeferredItem<CTCEGreatSword> CATALYST_GREATSWORD = rgrtGreatSword();
    public static final DeferredItem<CTCEZweihander> CATALYST_ZWEIHANDER = rgtrZweihander();
    public static final DeferredItem<CTCEUlfberht> CATALYST_ULFBERHT = rgtrUlfberht();
    public static final DeferredItem<CTCEGladius> CATALYST_GLADIUS = rgtrGladius();
    public static final DeferredItem<CTCEScythe> CATALYST_SCYTHE = rgtrScythe();
    public static final DeferredItem<CTCEKatar> CATALYST_KATAR = rgtrKatar();
    public static final DeferredItem<CTCEHammer> CATALYST_BIG_BONK = rgtrHammer();
    public static final DeferredItem<CTCEBroadSword> CATALYST_BROADSWORD = rgtrBroadSword();
    public static final DeferredItem<CTCEHalberd> CATALYST_HALBERD = rgtrHalberd();
    public static final DeferredItem<CTCEShield> CATALYST_SHIELD = rgtrShield();
    public static final DeferredItem<CTCEBattleAxe> CATALYST_BATTLEAXE = rgtrBattleAxe();
    public static final DeferredItem<CTCERapier> CATALYST_RAPIER = rgtrRapier();
    public static final DeferredItem<CTCEUniverseSword> UNIVERSE_SWORD = rgtrUniverseSword();

    public static final DeferredItem<CTCEPickaxe> CATALYST_PICKAXE = rgtrPickaxe();
    public static final DeferredItem<CTCEPaxel> CATALYST_PAXEL = rgtrPaxel();
    public static final DeferredItem<CTCEShovel> CATALYST_SHOVEL = rgtrShovel();
    public static final DeferredItem<CTCEAxe> CATALYST_AXE = rgtrAxe();

    public static void rgtr(IEventBus eventBus) {
        CTCE_ITEMS.register(eventBus);
    }
}
