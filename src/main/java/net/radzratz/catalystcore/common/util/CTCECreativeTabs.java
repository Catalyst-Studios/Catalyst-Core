package net.radzratz.catalystcore.common.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.radzratz.catalystcore.CatalystCore;
import net.radzratz.catalystcore.client.blocks.types.CTCEGlass;
import net.radzratz.catalystcore.client.custom.GravityAnomalyBlock;
import net.radzratz.catalystcore.client.custom.NebulaBlock;
import net.radzratz.catalystcore.client.items.tools.tools.*;
import net.radzratz.catalystcore.client.items.tools.weapons.*;
import net.radzratz.catalystcore.client.items.types.*;
import net.radzratz.catalystcore.common.registries.CTCEBlockRegistry;
import net.radzratz.catalystcore.common.registries.CTCEItemRegistry;
import net.radzratz.catalystcore.common.registries.CTCEToolsRegistry;

import java.util.Comparator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import static net.radzratz.catalystcore.common.util.CTCEUtils.*;

public class CTCECreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CTCE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CatalystCore.id);

    private static final String TAB = "catalystcore_tab_";

    public static final Supplier<CreativeModeTab> MATERIALS;
    public static final Supplier<CreativeModeTab> PHIALS;
    public static final Supplier<CreativeModeTab> FOODS;
    public static final Supplier<CreativeModeTab> WEAPONRY;
    public static final Supplier<CreativeModeTab> TOOLS;
    public static final Supplier<CreativeModeTab> BLOCKS;

    static {
        MATERIALS = rgtrTab(1, CTCEItemRegistry.FULL_CATALYST, "materials", tab ->
                tab.displayItems((params, output) -> {
                    acceptItems(output, CTCEEndgameItem.class);
                    acceptItems(output, CTCEMidGameItem.class);
                    output.accept(CTCEItemRegistry.PENTAGRAM.get());
                    output.accept(CTCEItemRegistry.CODEX.get());
                })
        );

        PHIALS = rgtrTab(2, CTCEItemRegistry.REINFORCED_BOTTLE, "components", tab ->
                tab.displayItems((params, output) -> {
                    acceptItems(output, CTCEPhialItem.class);
                    acceptItems(output, CTCESlateItem.class);
                })
        );

        FOODS = rgtrTab(3, CTCEItemRegistry.BURRITO, "foods", tab ->
                tab.displayItems((params, output) -> {
                    acceptItems(output, CTCEFoodItem.class);
                })
        );

        WEAPONRY = rgtrTab(4, CTCEToolsRegistry.CATALYST_GREATSWORD, "weaponry_armoury", tab ->
                tab.displayItems((params, output) -> {
                    acceptItems(output, CTCEUniverseSwordItem.class);
                    acceptItems(output, CTCESpearItem.class);
                    acceptItems(output, CTCEGreatswordItem.class);
                    acceptItems(output, CTCEZweihanderItem.class);
                    acceptItems(output, CTCELongswordItem.class);
                    acceptItems(output, CTCEBattleAxeItem.class);
                    acceptItems(output, CTCEKatarItem.class);
                    acceptItems(output, CTCEBigBonkItem.class);
                    acceptItems(output, CTCEScytheItem.class);
                    acceptItems(output, CTCERapierItem.class);
                    acceptItems(output, CTCEHalberdItem.class);
                    acceptItems(output, CTCEGladiusItem.class);
                    acceptItems(output, CTCEUlfberhtItem.class);
                    acceptItems(output, CTCEShieldItem.class);
                })
        );

        TOOLS = rgtrTab(5, CTCEToolsRegistry.CATALYST_PICKAXE, "tools", tab ->
                tab.displayItems((params, output) -> {
                    acceptItems(output, CTCEPickaxeItem.class);
                    acceptItems(output, CTCEShovelItem.class);
                    acceptItems(output, CTCEPaxelItem.class);
                    acceptItems(output, CTCEAxeItem.class);
                    acceptItems(output, CTCEHoeItem.class);
                })
        );

        BLOCKS = rgtrTab(6, CTCEBlockRegistry.REINFORCED_GLASS, "blocks", tab ->
                tab.displayItems((params, output) -> {
                    acceptBlocks(output, CTCEGlass.class);
                    acceptBlocks(output, NebulaBlock.class);
                    acceptBlocks(output, GravityAnomalyBlock.class);
                })
        );
    }

    public static CreativeModeTab.Builder base(Supplier<? extends ItemLike> icon) {
        return CreativeModeTab.builder().icon(() -> new ItemStack(icon.get()));
    }

    public static void acceptItems(CreativeModeTab.Output output, Class<?> type, Predicate<DeferredHolder<Item, ? extends Item>> extraFilter) {
        allItemEntries()
                .filter(e -> type.isInstance(e.get()))
                .filter(extraFilter)
                .sorted(Comparator.comparing(e -> e.getId().getPath()))
                .forEach(e -> output.accept(e.get()));

        allToolEntries()
                .filter(e -> type.isInstance(e.get()))
                .filter(extraFilter)
                .sorted(Comparator.comparing(e -> e.getId().getPath()))
                .forEach(e -> output.accept(e.get()));
    }

    public static void acceptItems(CreativeModeTab.Output output, Class<?> type) {
        acceptItems(output, type, e -> true);
    }

    public static void acceptBlocks(CreativeModeTab.Output output, Class<?> type, Predicate<DeferredHolder<Block, ? extends Block>> extraFilter) {
        allBlockEntries()
                .filter(e -> type.isInstance(e.get()))
                .filter(extraFilter)
                .sorted(Comparator.comparing(e -> e.getId().getPath()))
                .forEach(e -> output.accept(e.get()));
    }

    public static void acceptBlocks(CreativeModeTab.Output output, Class<?> type) {
        acceptBlocks(output, type, e -> true);
    }

    public static Supplier<CreativeModeTab> rgtrTab(int count, Supplier<? extends ItemLike> icon, String id, UnaryOperator<CreativeModeTab.Builder> operator) {
        return CTCECreativeTabs.CTCE_TABS.register(TAB + count, () -> {
            CreativeModeTab.Builder bldr = base(icon)
                    .title(Component.translatable("tab.catalystcore." + id));

            return operator.apply(bldr).build();
        });
    }

    public static void rgtr(IEventBus eventBus) {
        CTCE_TABS.register(eventBus);
    }
}