package net.radzratz.catalystcore.common.registries;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tiers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.radzratz.catalystcore.CatalystCore;
import net.radzratz.catalystcore.client.items.tools.tiers.CTCEToolTiers;
import net.radzratz.catalystcore.client.items.tools.tools.*;
import net.radzratz.catalystcore.client.items.tools.weapons.*;
import net.radzratz.catalystcore.common.util.config.CTCEConfig;


import static net.radzratz.catalystcore.client.items.CTCEItemHelpers.*;

public class CTCEToolsRegistry {
    public static final DeferredRegister.Items CTCE_TOOLS = DeferredRegister.createItems(CatalystCore.id);

    public static final DeferredItem<CTCEGreatswordItem> CATALYST_GREATSWORD;
    public static final DeferredItem<CTCEGreatswordItem> IRON_GREATSWORD;
    public static final DeferredItem<CTCEGreatswordItem> DIAMOND_GREATSWORD;
    public static final DeferredItem<CTCEGreatswordItem> NETHERITE_GREATSWORD;

    public static final DeferredItem<CTCELongswordItem> IRON_LONGSWORD;
    public static final DeferredItem<CTCELongswordItem> DIAMOND_LONGSWORD;
    public static final DeferredItem<CTCELongswordItem> NETHERITE_LONGSWORD;
    public static final DeferredItem<CTCELongswordItem> CATALYST_LONGSWORD;

    public static final DeferredItem<CTCEZweihanderItem> IRON_ZWEIHANDER;
    public static final DeferredItem<CTCEZweihanderItem> DIAMOND_ZWEIHANDER;
    public static final DeferredItem<CTCEZweihanderItem> NETHERITE_ZWEIHANDER;
    public static final DeferredItem<CTCEZweihanderItem> CATALYST_ZWEIHANDER;

    public static final DeferredItem<CTCEUlfberhtItem> IRON_ULFBERHT;
    public static final DeferredItem<CTCEUlfberhtItem> DIAMOND_ULFBERHT;
    public static final DeferredItem<CTCEUlfberhtItem> NETHERITE_ULFBERHT;
    public static final DeferredItem<CTCEUlfberhtItem> CATALYST_ULFBERHT;

    public static final DeferredItem<CTCEGladiusItem> IRON_GLADIUS;
    public static final DeferredItem<CTCEGladiusItem> DIAMOND_GLADIUS;
    public static final DeferredItem<CTCEGladiusItem> NETHERITE_GLADIUS;
    public static final DeferredItem<CTCEGladiusItem> CATALYST_GLADIUS;

    public static final DeferredItem<CTCEScytheItem> IRON_SCYTHE;
    public static final DeferredItem<CTCEScytheItem> DIAMOND_SCYTHE;
    public static final DeferredItem<CTCEScytheItem> NETHERITE_SCYTHE;
    public static final DeferredItem<CTCEScytheItem> CATALYST_SCYTHE;

    public static final DeferredItem<CTCEKatarItem> IRON_KATAR;
    public static final DeferredItem<CTCEKatarItem> DIAMOND_KATAR;
    public static final DeferredItem<CTCEKatarItem> NETHERITE_KATAR;
    public static final DeferredItem<CTCEKatarItem> CATALYST_KATAR;

    public static final DeferredItem<CTCEBattleAxeItem> IRON_BATTLEAXE;
    public static final DeferredItem<CTCEBattleAxeItem> DIAMOND_BATTLEAXE;
    public static final DeferredItem<CTCEBattleAxeItem> NETHERITE_BATTLEAXE;
    public static final DeferredItem<CTCEBattleAxeItem> CATALYST_BATTLEAXE;

    public static final DeferredItem<CTCEBigBonkItem> IRON_BIG_BONK;
    public static final DeferredItem<CTCEBigBonkItem> DIAMOND_BIG_BONK;
    public static final DeferredItem<CTCEBigBonkItem> NETHERITE_BIG_BONK;
    public static final DeferredItem<CTCEBigBonkItem> CATALYST_BIG_BONK;

    public static final DeferredItem<CTCEShieldItem> CATALYST_SHIELD;

    public static final DeferredItem<CTCEHalberdItem> IRON_HALBERD;
    public static final DeferredItem<CTCEHalberdItem> DIAMOND_HALBERD;
    public static final DeferredItem<CTCEHalberdItem> NETHERITE_HALBERD;
    public static final DeferredItem<CTCEHalberdItem> CATALYST_HALBERD;

    public static final DeferredItem<CTCERapierItem> IRON_RAPIER;
    public static final DeferredItem<CTCERapierItem> DIAMOND_RAPIER;
    public static final DeferredItem<CTCERapierItem> NETHERITE_RAPIER;
    public static final DeferredItem<CTCERapierItem> CATALYST_RAPIER;

    public static final DeferredItem<CTCESpearItem> CATALYST_SPEAR;

    public static final DeferredItem<CTCEUniverseSwordItem> UNIVERSE_SWORD;

    public static final DeferredItem<CTCEBroadswordItem> CATALYST_BROADSWORD;
    public static final DeferredItem<CTCEHoeItem> CATALYST_HOE;
    public static final DeferredItem<CTCEPickaxeItem> CATALYST_PICKAXE;
    public static final DeferredItem<CataclysticPaxel> CATALYST_PAXEL;
    public static final DeferredItem<CTCEShovelItem> CATALYST_SHOVEL;
    public static final DeferredItem<CTCEAxeItem> CATALYST_AXE;

    static {
        // todo: add crossbow and bow, adjust damage, speed and uses, and lang entries
        CATALYST_SHIELD = rgtrShield(CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.shield::get);

        IRON_BIG_BONK = rgtrHammer("iron", 325, false, false, false, Tiers.IRON,
                24, -3f, Rarity.COMMON, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.hammerIron::get);
        DIAMOND_BIG_BONK = rgtrHammer("diamond", 1750, false, false, false, Tiers.DIAMOND,
                35, -3f, Rarity.UNCOMMON, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.hammerDiamond::get);
        NETHERITE_BIG_BONK = rgtrHammer("netherite", 2500, false, true, false, Tiers.NETHERITE,
                47, -3f, Rarity.RARE, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.hammerNetherite::get);
        CATALYST_BIG_BONK = rgtrHammer("cataclystic", CTCEToolTiers.CATALYST.getUses(), true, true, true, CTCEToolTiers.CATALYST,
                69, -3f, Rarity.EPIC, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.hammerCataclystic::get);

        IRON_GLADIUS = rgtrGladius("iron", 325, false, false, false, Tiers.IRON,
                9, -2f, Rarity.COMMON, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.gladiusIron::get);
        DIAMOND_GLADIUS = rgtrGladius("diamond", 1750, false, false, false, Tiers.DIAMOND,
                13, 0f, Rarity.UNCOMMON, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.gladiusDiamond::get);
        NETHERITE_GLADIUS = rgtrGladius("netherite", 2500, false, true, false, Tiers.NETHERITE,
                18, 2f, Rarity.RARE, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.gladiusNetherite::get);
        CATALYST_GLADIUS = rgtrGladius("cataclystic", CTCEToolTiers.CATALYST.getUses(), true, true, true, CTCEToolTiers.CATALYST,
                24, 4f, Rarity.EPIC, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.gladiusCataclystic::get);

        IRON_HALBERD = rgtrHalberd("iron", 325, false, false, false, Tiers.IRON,
                15, 3f, Rarity.COMMON, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.halberdIron::get);
        DIAMOND_HALBERD = rgtrHalberd("diamond", 1750, false, false, false, Tiers.DIAMOND,
                25, 3f, Rarity.UNCOMMON, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.halberdDiamond::get);
        NETHERITE_HALBERD = rgtrHalberd("netherite", 2500, false, true, false, Tiers.NETHERITE,
                35, 3f, Rarity.RARE, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.halberdNetherite::get);
        CATALYST_HALBERD = rgtrHalberd("cataclystic", CTCEToolTiers.CATALYST.getUses(), true, true, true, CTCEToolTiers.CATALYST,
                45, 3f, Rarity.EPIC, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.halberdCataclystic::get);

        IRON_KATAR = rgtrKatar("iron", 325, false, false, false, Tiers.IRON,
                5, 2f, Rarity.COMMON, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.katarIron::get);
        DIAMOND_KATAR = rgtrKatar("diamond", 1750, false, false, false, Tiers.DIAMOND,
                9, 4f, Rarity.UNCOMMON, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.katarDiamond::get);
        NETHERITE_KATAR = rgtrKatar("netherite", 2500, false, true, false, Tiers.NETHERITE,
                11, 6f, Rarity.RARE, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.katarNetherite::get);
        CATALYST_KATAR = rgtrKatar("cataclystic", CTCEToolTiers.CATALYST.getUses(), true, true, true, CTCEToolTiers.CATALYST,
                15, 8f, Rarity.EPIC, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.katarCataclystic::get);

        IRON_RAPIER = rgtrRapier("iron", 325, false, false, false, Tiers.IRON,
                6, 4f, Rarity.COMMON, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.rapierIron::get);
        DIAMOND_RAPIER = rgtrRapier("diamond", 1750, false, false, false, Tiers.DIAMOND,
                9, 6f, Rarity.UNCOMMON, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.rapierDiamond::get);
        NETHERITE_RAPIER = rgtrRapier("netherite", 2500, false, true, false, Tiers.NETHERITE,
                12, 8f, Rarity.RARE, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.rapierNetherite::get);
        CATALYST_RAPIER = rgtrRapier("cataclystic", CTCEToolTiers.CATALYST.getUses(), true, true, true, CTCEToolTiers.CATALYST,
                17, 10f, Rarity.EPIC, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.rapierCataclystic::get);

        IRON_SCYTHE = rgtrScythe("iron", 325, false, false, false, Tiers.IRON,
                13, -2f, Rarity.COMMON, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.scytheIron::get);
        DIAMOND_SCYTHE = rgtrScythe("diamond", 1760, false, false, false, Tiers.DIAMOND,
                19, 0f, Rarity.UNCOMMON, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.scytheCataclystic::get);
        NETHERITE_SCYTHE = rgtrScythe("netherite", 2500, false, true, false, Tiers.NETHERITE,
                27, 2f, Rarity.RARE, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.scytheCataclystic::get);
        CATALYST_SCYTHE = rgtrScythe("cataclystic", CTCEToolTiers.CATALYST.getUses(), true, true, true, CTCEToolTiers.CATALYST,
                35, 4f, Rarity.EPIC, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.scytheCataclystic::get);

        IRON_ULFBERHT = rgtrUlfberht("iron", 325, false, false, false, Tiers.IRON,
                9, -2f, Rarity.COMMON, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.ulfberhtIron::get);
        DIAMOND_ULFBERHT = rgtrUlfberht("diamond", 1750, false, false, false, Tiers.DIAMOND,
                13, 0f, Rarity.UNCOMMON, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.ulfberhtDiamond::get);
        NETHERITE_ULFBERHT = rgtrUlfberht("netherite", 2500, false, true, false, Tiers.NETHERITE,
                18, 2f, Rarity.RARE, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.ulfberhtNetherite::get);
        CATALYST_ULFBERHT = rgtrUlfberht("cataclystic", CTCEToolTiers.CATALYST.getUses(), true, true, true, CTCEToolTiers.CATALYST,
                24, 4f, Rarity.EPIC, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.ulfberhtCataclystic::get);

        IRON_ZWEIHANDER = rgtrZweihander("iron", 325, false, false, false, Tiers.IRON,
                12, -2f, Rarity.COMMON, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.zweihanderIron::get);
        DIAMOND_ZWEIHANDER = rgtrZweihander("diamond", 1750, false, false, false, Tiers.DIAMOND,
                18, 0f, Rarity.UNCOMMON, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.zweihanderDiamond::get);
        NETHERITE_ZWEIHANDER = rgtrZweihander("netherite", 2500, false, true, false, Tiers.NETHERITE,
                24, 2f, Rarity.RARE, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.zweihanderNetherite::get);
        CATALYST_ZWEIHANDER = rgtrZweihander("cataclystic", CTCEToolTiers.CATALYST.getUses(), true, true, true, CTCEToolTiers.CATALYST,
                30, 4f, Rarity.EPIC, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.zweihanderCataclystic::get);

        IRON_GREATSWORD = rgrtGreatSword("iron", 325, false, false, false, Tiers.IRON,
                15, -4f, Rarity.COMMON, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.greatSwordIron::get);
        DIAMOND_GREATSWORD = rgrtGreatSword("diamond", 1750, false, false, false, Tiers.DIAMOND,
                21, -2f, Rarity.UNCOMMON, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.greatSwordDiamond::get);
        NETHERITE_GREATSWORD = rgrtGreatSword("netherite", 2500, false, true, false, Tiers.NETHERITE,
                30, 0f, Rarity.RARE, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.greatSwordNetherite::get);
        CATALYST_GREATSWORD = rgrtGreatSword("cataclystic", CTCEToolTiers.CATALYST.getUses(), true, true, true, CTCEToolTiers.CATALYST,
                49, 2f, Rarity.EPIC, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.greatSwordCataclystic::get);

        IRON_LONGSWORD = rgtrLongsword("iron", 325, false, false, false, Tiers.IRON,
                15, -2f, Rarity.COMMON, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.longswordIron::get);
        DIAMOND_LONGSWORD = rgtrLongsword("diamond", 1750, false, false, false, Tiers.DIAMOND,
                21, -2f, Rarity.UNCOMMON, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.longswordDiamond::get);
        NETHERITE_LONGSWORD = rgtrLongsword("netherite", 2500, false, true, false, Tiers.NETHERITE,
                30, -2f, Rarity.RARE, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.longswordNetherite::get);
        CATALYST_LONGSWORD = rgtrLongsword("cataclystic", CTCEToolTiers.CATALYST.getUses(), true, true, true, CTCEToolTiers.CATALYST,
                49, -2f, Rarity.EPIC, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.longswordCataclystic::get);

        IRON_BATTLEAXE = rgtrBattleAxe("iron", 325, false, false, false, Tiers.IRON,
                19, -3f, Rarity.COMMON, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.battleAxeIron::get);
        DIAMOND_BATTLEAXE = rgtrBattleAxe("diamond", 1750, false, false, false, Tiers.DIAMOND,
                25, -3f, Rarity.UNCOMMON, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.battleAxeDiamond::get);
        NETHERITE_BATTLEAXE = rgtrBattleAxe("netherite", 2500, false, true, false, Tiers.NETHERITE,
                30, -3f, Rarity.RARE, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.battleAxeNetherite::get);
        CATALYST_BATTLEAXE = rgtrBattleAxe("cataclystic", CTCEToolTiers.CATALYST.getUses(), true, true, true, CTCEToolTiers.CATALYST,
                40, -3f, Rarity.EPIC, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.battleAxeCataclystic::get);

        CATALYST_SPEAR = rgtrSpear("cataclystic", CTCEToolTiers.CATALYST.getUses(), true, true, true, CTCEToolTiers.CATALYST,
                25, 8f, Rarity.EPIC, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.spearCataclystic::get);

        UNIVERSE_SWORD = rgtrUniverseSword(true, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.universeSword::get);

        CATALYST_BROADSWORD = rgtrBroadSword("cataclystic", CTCEToolTiers.CATALYST.getUses(), true, true, true, CTCEToolTiers.CATALYST,
                17, 4f, Rarity.EPIC, CTCEConfig.CONFIG.modules.weaponry::get, CTCEConfig.CONFIG.modules.broadswordCataclystic::get);

        CATALYST_HOE = rgtrHoe(CTCEConfig.CONFIG.modules.tools::get, CTCEConfig.CONFIG.modules.hoe::get);
        CATALYST_AXE = rgtrAxe(CTCEConfig.CONFIG.modules.tools::get, CTCEConfig.CONFIG.modules.axe::get);
        CATALYST_PICKAXE = rgtrPickaxe(CTCEConfig.CONFIG.modules.tools::get, CTCEConfig.CONFIG.modules.pickaxe::get);
        CATALYST_SHOVEL = rgtrShovel(CTCEConfig.CONFIG.modules.tools::get, CTCEConfig.CONFIG.modules.shovel::get);
        CATALYST_PAXEL = rgtrPaxel(CTCEConfig.CONFIG.modules.tools::get, CTCEConfig.CONFIG.modules.paxel::get);
    }

    public static void rgtr(IEventBus eventBus) {
        CTCE_TOOLS.register(eventBus);
    }
}
