package net.radzratz.catalystcore.common.registries;

import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.radzratz.catalystcore.CatalystCore;
import net.radzratz.catalystcore.client.items.types.*;
import net.radzratz.catalystcore.client.items.types.phials.CTCEDragonBloodPhial;
import net.radzratz.catalystcore.client.items.types.phials.CTCEMyceliumPhial;
import net.radzratz.catalystcore.client.items.types.phials.CTCESculkPhial;
import net.radzratz.catalystcore.client.items.types.phials.CTCETorchFlowerPhial;
import net.radzratz.catalystcore.client.items.types.phials.CTCEWitheredPhial;
import net.radzratz.catalystcore.common.util.config.CTCEConfig;

import java.util.ArrayList;
import java.util.List;

import static net.radzratz.catalystcore.client.items.CTCEItemHelpers.*;

public class CTCEItemRegistry {
    public static final DeferredRegister.Items CTCE_ITEMS = DeferredRegister.createItems(CatalystCore.id);

    public static final List<CTCEPhialItem> PHIALS = new ArrayList<>();

    // todo: check why dis not workening
    public static final DeferredItem<Item> CODEX = rgtrUncommonItems("universe_codex");

    public static final DeferredItem<CTCEEndgameItem> FULL_CATALYST = rgtrEndGameItems("complete_catalyst");
    public static final DeferredItem<CTCEEndgameItem> ARES = rgtrEndGameItems("a.r.e.s");
    public static final DeferredItem<CTCEEndgameItem> ETERNAL_VORTEX = rgtrEndGameItems("eternal_vortex");
    public static final DeferredItem<CTCEEndgameItem> MAGIC_ANOMALY = rgtrEndGameItems("magic_anomaly");
    public static final DeferredItem<CTCEEndgameItem> RIFT = rgtrEndGameItems("crystallized_rift");
    public static final DeferredItem<CTCEEndgameItem> CONTAINER = rgtrEndGameItems("universe_containment_cell");
    public static final DeferredItem<CTCEEndgameItem> MALICIOUS_EYE = rgtrEndGameItems("malicious_eye");
    public static final DeferredItem<CTCEEndgameItem> TREASURE = rgtrEndGameItems("explorers_treasure");
    public static final DeferredItem<CTCEEndgameItem> BLACK_HOLE = rgtrEndGameItems("black_hole");
    public static final DeferredItem<CTCEEndgameItem> CHOCOLATE_BAR = rgtrEndGameItems("chocolate_bar");
    public static final DeferredItem<CTCEEndgameItem> COSMIC_SHATTERER = rgtrEndGameItems("cosmic_shatterer");
    public static final DeferredItem<CTCEEndgameItem> FORBIDDEN_ORB = rgtrEndGameItems("forbidden_artifact");
    public static final DeferredItem<CTCEEndgameItem> CHAOS_CRYSTAL = rgtrEndGameItems("fractured_chaos_seed");
    public static final DeferredItem<CTCEEndgameItem> SPIRIT_AGGLOMERATIO = rgtrEndGameItems("spirit_agglomeratio");
    public static final DeferredItem<CTCEEndgameItem> COSMIC_ABOMINATION = rgtrEndGameItems("cosmic_abomination");
    public static final DeferredItem<CTCEEndgameItem> ICAROS = rgtrEndGameItems("icaros");
    public static final DeferredItem<CTCEEndgameItem> ANCIENT_RELIC = rgtrEndGameItems("ancient_relic");

    public static final DeferredItem<CTCEMidGameItem> FRAME_CATALYST = rgtrMidGameItems("empty_catalyst");
    public static final DeferredItem<CTCEMidGameItem> SELF_AWARE_CHIP = rgtrMidGameItems("nano_self_aware");
    public static final DeferredItem<CTCEMidGameItem> COMET_SHARD = rgtrMidGameItems("comet_shard");
    public static final DeferredItem<CTCEMidGameItem> CONTAINER_INACTIVE = rgtrMidGameItems("container_cell");
    public static final DeferredItem<CTCEMidGameItem> WATER_ORB = rgtrMidGameItems("water_orb");
    public static final DeferredItem<CTCEMidGameItem> END_CORE = rgtrMidGameItems("end_core");
    public static final DeferredItem<CTCEMidGameItem> NETHER_CORE = rgtrMidGameItems("nether_core");
    public static final DeferredItem<CTCEMidGameItem> OVERWORLD_CORE = rgtrMidGameItems("overworld_core");
    public static final DeferredItem<CTCEMidGameItem> LAVA_ORB = rgtrMidGameItems("lava_orb");
    public static final DeferredItem<CTCEMidGameItem> WARDEN_CORE = rgtrMidGameItems("warden_core");
    public static final DeferredItem<CTCEMidGameItem> ELDER_CORE = rgtrMidGameItems("elder_core");
    public static final DeferredItem<CTCEMidGameItem> WITHER_CORE = rgtrMidGameItems("wither_core");
    public static final DeferredItem<CTCEMidGameItem> DRAGON_CORE = rgtrMidGameItems("dragon_core");
    public static final DeferredItem<CTCEMidGameItem> EMPTY_CRYSTAL = rgtrMidGameItems("elemental_emptiness_crystal");
    public static final DeferredItem<CTCEMidGameItem> FIRE_CRYSTAL = rgtrMidGameItems("elemental_fire_crystal");
    public static final DeferredItem<CTCEMidGameItem> WATER_CRYSTAL = rgtrMidGameItems("elemental_water_crystal");
    public static final DeferredItem<CTCEMidGameItem> EARTH_CRYSTAL = rgtrMidGameItems("elemental_earth_crystal");
    public static final DeferredItem<CTCEMidGameItem> WIND_CRYSTAL = rgtrMidGameItems("elemental_wind_crystal");
    public static final DeferredItem<CTCEMidGameItem> LIGHT_CRYSTAL = rgtrMidGameItems("elemental_light_crystal");
    public static final DeferredItem<CTCEMidGameItem> DARKNESS_CRYSTAL = rgtrMidGameItems("elemental_darkness_crystal");
    public static final DeferredItem<CTCEMidGameItem> ANTIMATTER = rgtrMidGameItems("antimatter_cell");
    public static final DeferredItem<CTCEMidGameItem> CONCENTRATED_RADIANCE = rgtrMidGameItems("concentrated_radiance");
    public static final DeferredItem<CTCEMidGameItem> BOUND_VOIDSPAWN = rgtrMidGameItems("bound_voidspawn");
    public static final DeferredItem<CTCEMidGameItem> EMPTY_CONTAINMENT_VESSEL = rgtrMidGameItems("empty_containment_vessel");

    public static final DeferredItem<CTCESlateItem> RUNIC_EMPTY = rgtrSlateItems("runic_slate");
    public static final DeferredItem<CTCESlateItem> RUNIC_ARCANE = rgtrSlateItems("runic_arcane_slate");
    public static final DeferredItem<CTCESlateItem> RUNIC_BLOOD = rgtrSlateItems("runic_blood_slate");
    public static final DeferredItem<CTCESlateItem> INFECTED_SLATE = rgtrSlateItems("infected_slate");
    public static final DeferredItem<CTCESlateItem> RUNIC_ENDER = rgtrSlateItems("runic_ender_slate");
    public static final DeferredItem<CTCESlateItem> RUNIC_ESOTHERICAL = rgtrSlateItems("esotherical_runic_slate");

    public static final DeferredItem<CTCEPentagramItem> PENTAGRAM;

    public static final DeferredItem<CTCEPhialItem> REINFORCED_BOTTLE;
    public static final DeferredItem<CTCETorchFlowerPhial> TORCHFLOWER_BOTTLE;
    public static final DeferredItem<CTCEDragonBloodPhial> DRAGON_BLOOD;
    public static final DeferredItem<CTCEWitheredPhial> WITHERED_BOTTLE;
    public static final DeferredItem<CTCESculkPhial> SCULK_BOTTLE;
    public static final DeferredItem<CTCEMyceliumPhial> MYCELIUM_BOTTLE;
    public static final DeferredItem<CTCEPhialItem> LIFE_ESSENCE_BOTTLE;
    public static final DeferredItem<CTCEPhialItem> PIXIE_BOTTLE;

    public static final DeferredItem<CTCEFoodItem> BURRITO = rgtrFoods("burrito");

    static {
        REINFORCED_BOTTLE = rgtrPhials("reinforced_bottle", () -> new CTCEPhialItem(new Item.Properties(), null, null));
        TORCHFLOWER_BOTTLE = rgtrPhials("torchflower_bottle", () -> new CTCETorchFlowerPhial(new Item.Properties(), CTCEConfig.CONFIG.phials.torchFlowerPhial::get, CTCEConfig.CONFIG.phials.torchFlowerPhialEvent::get));
        DRAGON_BLOOD = rgtrPhials("dragon_blood_bottle", () -> new CTCEDragonBloodPhial(new Item.Properties(), CTCEConfig.CONFIG.phials.dragonPhial::get, CTCEConfig.CONFIG.phials.dragonPhialEvent::get));
        WITHERED_BOTTLE = rgtrPhials("withered_essence_bottle", () -> new CTCEWitheredPhial(new Item.Properties(), CTCEConfig.CONFIG.phials.witheredPhial::get, CTCEConfig.CONFIG.phials.witheredPhialEvent::get));
        SCULK_BOTTLE = rgtrPhials("sculk_bottle", () -> new CTCESculkPhial(new Item.Properties(), CTCEConfig.CONFIG.phials.sculkPhial::get, CTCEConfig.CONFIG.phials.sculkPhialEvent::get));
        MYCELIUM_BOTTLE = rgtrPhials("mycelium_bottle", () -> new CTCEMyceliumPhial(new Item.Properties(), CTCEConfig.CONFIG.phials.myceliumPhial::get, CTCEConfig.CONFIG.phials.myceliumPhialEvent::get));
        LIFE_ESSENCE_BOTTLE = rgtrPhials("life_essence_bottle", () -> new CTCEPhialItem(new Item.Properties(), CTCEConfig.CONFIG.phials.lifeEssencePhial::get, null));
        PIXIE_BOTTLE = rgtrPhials("pixie_essence", () -> new CTCEPhialItem(new Item.Properties(), CTCEConfig.CONFIG.phials.pixiePhial::get, null));

        PENTAGRAM = CTCE_ITEMS.register("pentagram", () -> new CTCEPentagramItem(new CTCEPentagramItem.Properties().stacksTo(1).rarity(Rarity.RARE).fireResistant()));
    }

    public static void rgtr(IEventBus eventBus) {
        CTCE_ITEMS.register(eventBus);
    }
}