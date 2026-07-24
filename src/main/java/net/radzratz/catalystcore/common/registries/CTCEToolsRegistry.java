package net.radzratz.catalystcore.common.registries;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.radzratz.catalystcore.CatalystCore;
import net.radzratz.catalystcore.client.items.tools.tools.CataclysticAxe;
import net.radzratz.catalystcore.client.items.tools.tools.CataclysticPaxel;
import net.radzratz.catalystcore.client.items.tools.tools.CataclysticPickaxe;
import net.radzratz.catalystcore.client.items.tools.tools.CataclysticShovel;
import net.radzratz.catalystcore.client.items.tools.weapons.*;
import net.radzratz.catalystcore.common.util.config.CTCEConfig;

import static net.radzratz.catalystcore.client.items.CTCEItemHelpers.*;

public class CTCEToolsRegistry {
    public static final DeferredRegister.Items CTCE_TOOLS = DeferredRegister.createItems(CatalystCore.id);

    public static final DeferredItem<CataclysticGreatSword> CATALYST_GREATSWORD;
    public static final DeferredItem<CataclysticZweihander> CATALYST_ZWEIHANDER;
    public static final DeferredItem<CataclysticUlfberht> CATALYST_ULFBERHT;
    public static final DeferredItem<CataclysticGladius> CATALYST_GLADIUS;
    public static final DeferredItem<CataclysticScythe> CATALYST_SCYTHE;
    public static final DeferredItem<CataclysticKatar> CATALYST_KATAR;
    public static final DeferredItem<CataclysticHammer> CATALYST_BIG_BONK;
    public static final DeferredItem<CataclysticBroadsword> CATALYST_BROADSWORD;
    public static final DeferredItem<CataclysticHalberd> CATALYST_HALBERD;
    public static final DeferredItem<CataclysticShield> CATALYST_SHIELD;
    public static final DeferredItem<CataclysticBattleAxe> CATALYST_BATTLEAXE;
    public static final DeferredItem<CataclysticRapier> CATALYST_RAPIER;
    public static final DeferredItem<UniverseSword> UNIVERSE_SWORD;

    public static final DeferredItem<CataclysticPickaxe> CATALYST_PICKAXE;
    public static final DeferredItem<CataclysticPaxel> CATALYST_PAXEL;
    public static final DeferredItem<CataclysticShovel> CATALYST_SHOVEL;
    public static final DeferredItem<CataclysticAxe> CATALYST_AXE;

    static {
        CATALYST_BIG_BONK = rgtrHammer(CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.hammer::get);
        CATALYST_BROADSWORD = rgtrBroadSword(CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.broadsword::get);
        CATALYST_GLADIUS = rgtrGladius(CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.gladius::get);
        CATALYST_GREATSWORD = rgrtGreatSword(CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.gladius::get);
        CATALYST_HALBERD = rgtrHalberd(CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.halberd::get);
        CATALYST_KATAR = rgtrKatar(CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.katar::get);
        CATALYST_RAPIER = rgtrRapier(CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.rapier::get);
        CATALYST_SCYTHE = rgtrScythe(CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.scythe::get);
        CATALYST_ULFBERHT = rgtrUlfberht(CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.ulfberht::get);
        CATALYST_ZWEIHANDER = rgtrZweihander(CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.zweihander::get);

        UNIVERSE_SWORD = rgtrUniverseSword(CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.universeSword::get);

        CATALYST_SHIELD = rgtrShield(CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.shield::get);

        CATALYST_AXE = rgtrAxe(CTCEConfig.CONFIG.modules.tools::get, CTCEConfig.CONFIG.modules.axe::get);
        CATALYST_BATTLEAXE = rgtrBattleAxe(CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.battleAxe::get);

        CATALYST_PICKAXE = rgtrPickaxe(CTCEConfig.CONFIG.modules.tools::get, CTCEConfig.CONFIG.modules.pickaxe::get);

        CATALYST_PAXEL = rgtrPaxel(CTCEConfig.CONFIG.modules.tools::get, CTCEConfig.CONFIG.modules.paxel::get);

        CATALYST_SHOVEL = rgtrShovel(CTCEConfig.CONFIG.modules.tools::get, CTCEConfig.CONFIG.modules.shovel::get);
    }

    public static void rgtr(IEventBus eventBus) {
        CTCE_TOOLS.register(eventBus);
    }
}
