package net.radzratz.catalystcore.client.items;

import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.component.Unbreakable;
import net.neoforged.neoforge.registries.DeferredItem;
import net.radzratz.catalystcore.client.items.tools.tiers.CTCEToolTiers;
import net.radzratz.catalystcore.client.items.tools.tools.*;
import net.radzratz.catalystcore.client.items.tools.weapons.*;
import net.radzratz.catalystcore.client.items.types.*;

import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import static net.radzratz.catalystcore.common.registries.CTCEItemRegistry.CTCE_ITEMS;
import static net.radzratz.catalystcore.common.registries.CTCEItemRegistry.PHIALS;
import static net.radzratz.catalystcore.common.registries.CTCEToolsRegistry.CTCE_TOOLS;

public class CTCEItemHelpers {
    public static Item.Properties weaponProperties(Rarity rarity, int durability, boolean unbreakable, boolean fireResistant, boolean noRepair) {
        Item.Properties props = new Item.Properties()
                .rarity(rarity)
                .stacksTo(1);

        if (durability > 0) {
            props = props.durability(durability);
        }

        if (unbreakable) {
            props = props.component(DataComponents.UNBREAKABLE, new Unbreakable(true));
        }

        if (fireResistant) {
            props = props.fireResistant();
        }

        if (noRepair) {
            props = props.setNoRepair();
        }

        return props;
    }

    public static DeferredItem<CTCEEndgameItem> rgtrEndGameItems(String name) {
        return CTCE_ITEMS.register(name, () -> new CTCEEndgameItem(new Item.Properties().fireResistant().rarity(Rarity.EPIC)));
    }

    public static DeferredItem<CTCEMidGameItem> rgtrMidGameItems(String name) {
        return CTCE_ITEMS.register(name, () -> new CTCEMidGameItem(new Item.Properties().fireResistant().rarity(Rarity.RARE)));
    }

    public static DeferredItem<Item> rgtrUncommonItems(String name) {
        return CTCE_ITEMS.register(name, () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    }

    public static DeferredItem<Item> rgtrCommonItems(String name) {
        return CTCE_ITEMS.register(name, () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
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

    public static DeferredItem<CTCEFoodItem> rgtrFoods(String name, FoodProperties foodProps, Rarity rarity) {
        return CTCE_ITEMS.register(name, () ->
                new CTCEFoodItem(new Item.Properties()
                        .food(foodProps)
                        .rarity(rarity)
                )
        );
    }

    public static DeferredItem<CTCEBigBonkItem> rgtrHammer(String name, int durability, boolean unbreakable, boolean fireResistant, boolean noRepair, Tier tier, int dmg, float speed, Rarity rarity, BooleanSupplier module, BooleanSupplier type) {
        return CTCE_TOOLS.register(name + "_big_hammer", () ->
                new CTCEBigBonkItem(tier, unbreakable,
                        weaponProperties(rarity, durability, unbreakable, fireResistant, noRepair),
                        CTCEBigBonkItem.createToolProperties(),
                        CTCEBigBonkItem.createAttributes(tier, dmg, speed),
                        module,
                        type
                )
        );
    }

    public static DeferredItem<CTCEGreatswordItem> rgrtGreatSword(String name, int durability, boolean unbreakable, boolean fireResistant, boolean noRepair, Tier tier, int dmg, float speed, Rarity rarity, BooleanSupplier module, BooleanSupplier type) {
        return CTCE_TOOLS.register(name + "_greatsword", () ->
                new CTCEGreatswordItem(tier, unbreakable,
                        weaponProperties(rarity, durability, unbreakable, fireResistant, noRepair),
                        CTCEGreatswordItem.createToolProperties(),
                        CTCEGreatswordItem.createAttributes(tier, dmg, speed),
                        module,
                        type
                )
        );
    }

    public static DeferredItem<CTCELongswordItem> rgtrLongsword(String name, int durability, boolean unbreakable, boolean fireResistant, boolean noRepair, Tier tier, int dmg, float speed, Rarity rarity, BooleanSupplier module, BooleanSupplier type) {
        return CTCE_TOOLS.register(name + "_longsword", () ->
                new CTCELongswordItem(tier, unbreakable,
                        weaponProperties(rarity, durability, unbreakable, fireResistant, noRepair),
                        CTCELongswordItem.createToolProperties(),
                        CTCELongswordItem.createAttributes(tier, dmg, speed),
                        module,
                        type
                )
        );
    }

    public static DeferredItem<CTCEZweihanderItem> rgtrZweihander(String name, int durability, boolean unbreakable, boolean fireResistant, boolean noRepair, Tier tier, int dmg, float speed, Rarity rarity, BooleanSupplier module, BooleanSupplier type) {
        return CTCE_TOOLS.register(name + "_zweihander", () ->
                new CTCEZweihanderItem(tier, unbreakable,
                        weaponProperties(rarity, durability, unbreakable, fireResistant, noRepair),
                        CTCEZweihanderItem.createToolProperties(),
                        CTCEZweihanderItem.createAttributes(tier, dmg, speed),
                        module,
                        type
                )
        );
    }

    public static DeferredItem<CTCEHalberdItem> rgtrHalberd(String name, int durability, boolean unbreakable, boolean fireResistant, boolean noRepair, Tier tier, int dmg, float speed, Rarity rarity, BooleanSupplier module, BooleanSupplier type) {
        return CTCE_TOOLS.register(name + "_halberd", () ->
                new CTCEHalberdItem(tier, unbreakable,
                        weaponProperties(rarity, durability, unbreakable, fireResistant, noRepair),
                        CTCEHalberdItem.createToolProperties(),
                        CTCEHalberdItem.createAttributes(tier, dmg, speed),
                        module,
                        type
                )
        );
    }

    public static DeferredItem<CTCEBroadswordItem> rgtrBroadSword(String name, int durability, boolean unbreakable, boolean fireResistant, boolean noRepair, Tier tier, int dmg, float speed, Rarity rarity, BooleanSupplier module, BooleanSupplier type) {
        return CTCE_TOOLS.register(name + "_sword", () ->
                new CTCEBroadswordItem(tier, unbreakable,
                        weaponProperties(rarity, durability, unbreakable, fireResistant, noRepair),
                        CTCEBroadswordItem.createToolProperties(),
                        CTCEBroadswordItem.createAttributes(tier, dmg, speed),
                        module,
                        type
                )
        );
    }

    public static DeferredItem<CTCEUlfberhtItem> rgtrUlfberht(String name, int durability, boolean unbreakable, boolean fireResistant, boolean noRepair, Tier tier, int dmg, float speed, Rarity rarity, BooleanSupplier module, BooleanSupplier type) {
        return CTCE_TOOLS.register(name + "_ulfberht", () ->
                new CTCEUlfberhtItem(tier, unbreakable,
                        weaponProperties(rarity, durability, unbreakable, fireResistant, noRepair),
                        CTCEUlfberhtItem.createToolProperties(),
                        CTCEUlfberhtItem.createAttributes(tier, dmg, speed),
                        module,
                        type
                )
        );
    }

    public static DeferredItem<CTCEGladiusItem> rgtrGladius(String name, int durability, boolean unbreakable, boolean fireResistant, boolean noRepair, Tier tier, int dmg, float speed, Rarity rarity, BooleanSupplier module, BooleanSupplier type) {
        return CTCE_TOOLS.register(name + "_gladius", () ->
                new CTCEGladiusItem(tier, unbreakable,
                        weaponProperties(rarity, durability, unbreakable, fireResistant, noRepair),
                        CTCEGladiusItem.createToolProperties(),
                        CTCEGladiusItem.createAttributes(tier, dmg, speed),
                        module,
                        type
                )
        );
    }

    public static DeferredItem<CTCEScytheItem> rgtrScythe(String name, int durability, boolean unbreakable, boolean fireResistant, boolean noRepair, Tier tier, int dmg, float speed, Rarity rarity, BooleanSupplier module, BooleanSupplier type) {
        return CTCE_TOOLS.register(name + "_scythe", () ->
                new CTCEScytheItem(tier, unbreakable,
                        weaponProperties(rarity, durability, unbreakable, fireResistant, noRepair),
                        CTCEScytheItem.createToolProperties(),
                        CTCEScytheItem.createAttributes(tier, dmg, speed),
                        module,
                        type
                )
        );
    }

    public static DeferredItem<CTCERapierItem> rgtrRapier(String name, int durability, boolean unbreakable, boolean fireResistant, boolean noRepair, Tier tier, int dmg, float speed, Rarity rarity, BooleanSupplier module, BooleanSupplier type) {
        return CTCE_TOOLS.register(name + "_rapier", () ->
                new CTCERapierItem(tier, unbreakable,
                        weaponProperties(rarity, durability, unbreakable, fireResistant, noRepair),
                        CTCERapierItem.createToolProperties(),
                        CTCERapierItem.createAttributes(tier, dmg, speed),
                        module,
                        type
                )
        );
    }

    public static DeferredItem<CTCEBattleAxeItem> rgtrBattleAxe(String name, int durability, boolean unbreakable, boolean fireResistant, boolean noRepair, Tier tier, int dmg, float speed, Rarity rarity, BooleanSupplier module, BooleanSupplier type) {
        return CTCE_TOOLS.register(name + "_battleaxe", () ->
                new CTCEBattleAxeItem(tier, unbreakable,
                        weaponProperties(rarity, durability, unbreakable, fireResistant, noRepair),
                        new Tool(List.of(Tool.Rule.overrideSpeed(BlockTags.MINEABLE_WITH_AXE, 7F)), 10.0F, 15),
                        CTCEBattleAxeItem.createAttributes(tier, dmg, speed),
                        module,
                        type
                )
        );
    }

    public static DeferredItem<CTCEKatarItem> rgtrKatar(String name, int durability, boolean unbreakable, boolean fireResistant, boolean noRepair, Tier tier, int dmg, float speed, Rarity rarity, BooleanSupplier module, BooleanSupplier type) {
        return CTCE_TOOLS.register(name + "_katar", () ->
                new CTCEKatarItem(tier, unbreakable,
                        weaponProperties(rarity, durability, unbreakable, fireResistant, noRepair),
                        CTCEKatarItem.createToolProperties(),
                        CTCEKatarItem.createAttributes(tier, dmg, speed),
                        module,
                        type
                )
        );
    }

    public static DeferredItem<CTCESpearItem> rgtrSpear(String name, int durability, boolean unbreakable, boolean fireResistant, boolean noRepair, Tier tier, int dmg, float speed, Rarity rarity, BooleanSupplier module, BooleanSupplier type) {
        return CTCE_TOOLS.register(name + "_spear", () ->
                new CTCESpearItem(tier, unbreakable,
                        weaponProperties(rarity, durability, unbreakable, fireResistant, noRepair),
                        CTCESpearItem.createToolProperties(),
                        CTCESpearItem.createAttributes(tier, dmg, speed),
                        module,
                        type
                )
        );
    }

    public static DeferredItem<CTCEUniverseSwordItem> rgtrUniverseSword(boolean unbreakable, BooleanSupplier module, BooleanSupplier type) {
        return CTCE_TOOLS.register("universe_sword", () ->
                new CTCEUniverseSwordItem(CTCEToolTiers.CATALYST, unbreakable,
                        weaponProperties(Rarity.EPIC, CTCEToolTiers.CATALYST.getUses(), unbreakable, true, true),
                        CTCEUniverseSwordItem.createToolProperties(),
                        CTCEUniverseSwordItem.createAttributes(CTCEToolTiers.CATALYST, 499, 8f),
                        module,
                        type
                )
        );
    }

    public static DeferredItem<CataclysticPaxel> rgtrPaxel(BooleanSupplier module, BooleanSupplier type) {
        return CTCE_TOOLS.register("cataclystic_paxel", () -> new CataclysticPaxel(
                CTCEToolTiers.CATALYST, new CataclysticPaxel.Properties().fireResistant().setNoRepair().rarity(Rarity.EPIC).stacksTo(1),
                new Tool(List.of(Tool.Rule.overrideSpeed(BlockTags.MINEABLE_WITH_PICKAXE, 27F)), 10.0F, 15),
                CataclysticPaxel.createAttributes(CTCEToolTiers.CATALYST, 39, 9f),
                module, type
        ));
    }

    public static DeferredItem<CTCEPickaxeItem> rgtrPickaxe(BooleanSupplier module, BooleanSupplier type) {
        return CTCE_TOOLS.register("cataclystic_pickaxe", () ->
                new CTCEPickaxeItem(CTCEToolTiers.CATALYST, new CTCEPickaxeItem.Properties()
                        .fireResistant()
                        .setNoRepair()
                        .rarity(Rarity.EPIC)
                        .stacksTo(1),
                        new Tool(List.of(Tool.Rule.overrideSpeed(BlockTags.MINEABLE_WITH_PICKAXE, 9f)), 10.0F, 15),
                        CTCEPickaxeItem.createAttributes(CTCEToolTiers.CATALYST, 11, 4f),
                        module,
                        type)
        );
    }

    public static DeferredItem<CTCEShovelItem> rgtrShovel(BooleanSupplier module, BooleanSupplier type) {
        return CTCE_TOOLS.register("cataclystic_shovel", () ->
                new CTCEShovelItem(CTCEToolTiers.CATALYST, new CTCEShovelItem.Properties()
                        .fireResistant()
                        .setNoRepair()
                        .rarity(Rarity.EPIC)
                        .stacksTo(1),
                        new Tool(List.of(Tool.Rule.overrideSpeed(BlockTags.MINEABLE_WITH_SHOVEL, 9F)), 10.0F, 15),
                        CTCEShovelItem.createAttributes(CTCEToolTiers.CATALYST, 11, 4f),
                        module,
                        type
                )
        );
    }

    public static DeferredItem<CTCEAxeItem> rgtrAxe(BooleanSupplier module, BooleanSupplier type) {
        return CTCE_TOOLS.register("cataclystic_axe", () ->
                new CTCEAxeItem(CTCEToolTiers.CATALYST,
                        new CTCEAxeItem.Properties(),
                        new Tool(List.of(Tool.Rule.overrideSpeed(BlockTags.MINEABLE_WITH_AXE, 7F)), 10.0F, 15),
                        CTCEAxeItem.createAttributes(CTCEToolTiers.CATALYST, 11, -3f),
                        module,
                        type
                )
        );
    }

    public static DeferredItem<CTCEHoeItem> rgtrHoe(BooleanSupplier module, BooleanSupplier type) {
        return CTCE_TOOLS.register("cataclystic_hoe", () ->
                new CTCEHoeItem(CTCEToolTiers.CATALYST,
                        CTCEHoeItem.createAttributes(CTCEToolTiers.CATALYST, 11, -3f),
                        new CTCEHoeItem.Properties(),
                        module,
                        type
                )
        );
    }

    public static DeferredItem<CTCEShieldItem> rgtrShield(BooleanSupplier module, BooleanSupplier type) {
        return CTCE_TOOLS.register("cataclystic_shield", () ->
                new CTCEShieldItem(new CTCEShieldItem.Properties()
                        .fireResistant()
                        .setNoRepair()
                        .rarity(Rarity.EPIC)
                        .stacksTo(1),
                        module,
                        type
                )
        );
    }
}
