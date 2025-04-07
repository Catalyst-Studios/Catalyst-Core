package net.radzratz.catalystcore.items;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Tool;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.radzratz.catalystcore.CatalystCore;
import net.radzratz.catalystcore.items.tools.CatalystPickaxe;
import net.radzratz.catalystcore.items.tools.CatalystSword;

import java.util.List;

public class CatalystItems
{
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CatalystCore.MOD_ID);

    private static DeferredItem<Item> registerCatalystCoreItems(String name)
    {
        return ITEMS.register(name, () -> new Item(new Item.Properties()));
    }

    public static final DeferredItem<Item> FULL_CATALYST = registerCatalystCoreItems("complete_catalyst");
    public static final DeferredItem<Item> FRAME_CATALYST = registerCatalystCoreItems("empty_catalyst");
    public static final DeferredItem<Item> MALICIOUS_EYE = registerCatalystCoreItems("malicious_eye");
    public static final DeferredItem<Item> SELF_AWARE_CHIP = registerCatalystCoreItems("nano_self_aware");
    public static final DeferredItem<Item> PENTAGRAM = registerCatalystCoreItems("pentagram");

    public static final DeferredItem<Item> CATALYST_INGOT = registerCatalystCoreItems("cataclystic_ingot");

    public static final DeferredItem<Item> RAW_CATALYRIUM = registerCatalystCoreItems("raw_catalyrium");
    public static final DeferredItem<Item> CALTARITE = registerCatalystCoreItems("caltarite_gem");

    public static final DeferredItem<SwordItem> CATALYST_SWORD = registerCatalystSword();
    public static final DeferredItem<SwordItem> CATALYST_BROADSWORD = registerCatalystBroadSword();
    public static final DeferredItem<PickaxeItem> CATALYST_PICKAXE = registerCatalystPickaxe();

    private static DeferredItem<SwordItem> registerCatalystSword()
    {
        return ITEMS.register("cataclystic_sword", () -> new CatalystSword(
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
    private static DeferredItem<SwordItem> registerCatalystBroadSword()
    {
        return ITEMS.register("cataclystic_broadsword", () -> new CatalystSword(
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

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
