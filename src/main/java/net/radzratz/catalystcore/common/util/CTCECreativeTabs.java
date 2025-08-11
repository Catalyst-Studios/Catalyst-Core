package net.radzratz.catalystcore.common.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.radzratz.catalystcore.CatalystCore;
import net.radzratz.catalystcore.client.blocks.CTCEBlocks;
import net.radzratz.catalystcore.client.items.CTCEItems;
import net.radzratz.catalystcore.common.util.config.ICTCEItem;

import java.util.function.Supplier;

public class CTCECreativeTabs
{
    public static final DeferredRegister<CreativeModeTab> CATALYST_CREATIVE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CatalystCore.id);

    private static boolean shouldShowInCreative(Item item)
    {
        if(item instanceof ICTCEItem ghostItem)
        {
            return ghostItem.shouldAppear();
        }
        return true;
    }

    ///Base Module
    @SuppressWarnings("unused")
    public static final Supplier<CreativeModeTab> CATALYST_CORE_BASE = CATALYST_CREATIVE_TAB.register("catalystcore_tab1",
            ()-> CreativeModeTab.builder()
                    .icon(()-> new ItemStack(CTCEItems.FULL_CATALYST.get()))
                    .title(Component.translatable("creativetab.catalystcore.base"))
                    .displayItems((itemDisplayParameters, output) ->
                    {
                        output.accept(CTCEItems.FULL_CATALYST);
                        output.accept(CTCEItems.ARES);
                        output.accept(CTCEItems.MALICIOUS_EYE);
                        output.accept(CTCEItems.CONTAINER);
                        output.accept(CTCEItems.RIFT);
                        output.accept(CTCEItems.MAGIC_ANOMALY);
                        output.accept(CTCEItems.ICAROS);
                        output.accept(CTCEItems.ETERNAL_VORTEX);
                        output.accept(CTCEItems.TREASURE);
                        output.accept(CTCEItems.BLACK_HOLE);
                        output.accept(CTCEItems.CHOCOLATE_BAR);
                        output.accept(CTCEItems.COSMIC_SHATTERER);
                        output.accept(CTCEItems.FORBIDDEN_ORB);
                        output.accept(CTCEItems.CHAOS_CRYSTAL);
                        output.accept(CTCEItems.SPIRIT_AGGLOMERATIO);
                        output.accept(CTCEItems.COSMIC_ABOMINATION);
                        output.accept(CTCEItems.ANCIENT_RELIC);
                        output.accept(CTCEItems.FRAME_CATALYST);
                        output.accept(CTCEItems.SELF_AWARE_CHIP);
                        output.accept(CTCEItems.PENTAGRAM);
                        output.accept(CTCEItems.COMET_SHARD);
                        output.accept(CTCEItems.CONTAINER_INACTIVE);
                        output.accept(CTCEItems.ANTIMATTER);
                        output.accept(CTCEItems.LAVA_ORB);
                        output.accept(CTCEItems.WATER_ORB);
                        output.accept(CTCEItems.END_CORE);
                        output.accept(CTCEItems.NETHER_CORE);
                        output.accept(CTCEItems.OVERWORLD_CORE);
                        output.accept(CTCEItems.WARDEN_CORE);
                        output.accept(CTCEItems.ELDER_CORE);
                        output.accept(CTCEItems.WITHER_CORE);
                        output.accept(CTCEItems.DRAGON_CORE);
                        output.accept(CTCEItems.EMPTY_CRYSTAL);
                        output.accept(CTCEItems.FIRE_CRYSTAL);
                        output.accept(CTCEItems.WATER_CRYSTAL);
                        output.accept(CTCEItems.EARTH_CRYSTAL);
                        output.accept(CTCEItems.WIND_CRYSTAL);
                        output.accept(CTCEItems.LIGHT_CRYSTAL);
                        output.accept(CTCEItems.DARKNESS_CRYSTAL);
                    }).build());

    /// Components
    @SuppressWarnings("unused")
    public static final Supplier<CreativeModeTab> CATALYST_CORE_COMPONENTS = CATALYST_CREATIVE_TAB.register("catalystcore_tab4",
            ()-> CreativeModeTab.builder()
                    .icon(()-> new ItemStack(CTCEItems.REINFORCED_BOTTLE.get()))
                    .title(Component.translatable("creativetab.catalystcore.components"))
                    .displayItems((itemDisplayParameters, output) ->
                    {
                        output.accept(CTCEItems.BROKEN_PEARL);
                        output.accept(CTCEItems.DAGGER);
                        output.accept(CTCEItems.STRANGE_ROCK);
                        output.accept(CTCEItems.DRAGON_SCALE);
                        output.accept(CTCEItems.REINFORCED_BOTTLE);
                        output.accept(CTCEItems.DRAGON_BLOOD);
                        output.accept(CTCEItems.WITHERED_BOTTLE);
                        output.accept(CTCEItems.SCULK_BOTTLE);
                        output.accept(CTCEItems.MYCELIUM_BOTTLE);
                        output.accept(CTCEItems.TORCHFLOWER_BOTTLE);
                        output.accept(CTCEItems.PIXIE_BOTTLE);
                        output.accept(CTCEItems.LIFE_ESSENCE_BOTTLE);
                        output.accept(CTCEItems.RUNIC_EMPTY);
                        output.accept(CTCEItems.RUNIC_BLOOD);
                        output.accept(CTCEItems.RUNIC_ARCANE);
                        output.accept(CTCEItems.RUNIC_ENDER);
                        output.accept(CTCEItems.INFECTED_SLATE);
                        output.accept(CTCEItems.RUNIC_ESOTHERICAL);
                    }).build());

    /// Foods
    @SuppressWarnings("unused")
    public static final Supplier<CreativeModeTab> CATALYST_CORE_FOODS = CATALYST_CREATIVE_TAB.register("catalystcore_tab5",
            ()-> CreativeModeTab.builder()
                    .icon(()-> new ItemStack(CTCEItems.BURRITO.get()))
                    .title(Component.translatable("creativetab.catalystcore.foods"))
                    .displayItems((itemDisplayParameters, output) ->
                    {
                        output.accept(CTCEItems.BURRITO);
                    }).build());

    ///Weapons Module
    @SuppressWarnings("unused")
    public static final Supplier<CreativeModeTab> CATALYST_CORE_WEAPONS = CATALYST_CREATIVE_TAB.register("catalystcore_tab2",
            ()-> CreativeModeTab.builder()
                    .icon(()-> new ItemStack(CTCEItems.CATALYST_GREATSWORD.get()))
                    .title(Component.translatable("creativetab.catalystcore.weapons"))
                    .displayItems((itemDisplayParameters, output) ->
                    {
                        ///Heavy Weapons
                        if(shouldShowInCreative(CTCEItems.UNIVERSE_SWORD.get()))
                        {
                            output.accept(CTCEItems.UNIVERSE_SWORD);
                        }
                        if(shouldShowInCreative(CTCEItems.CATALYST_GREATSWORD.get()))
                        {
                            output.accept(CTCEItems.CATALYST_GREATSWORD);
                        }
                        if(shouldShowInCreative(CTCEItems.CATALYST_ZWEIHANDER.get()))
                        {
                            output.accept(CTCEItems.CATALYST_ZWEIHANDER);
                        }
                        if(shouldShowInCreative(CTCEItems.CATALYST_HALBERD.get()))
                        {
                            output.accept(CTCEItems.CATALYST_HALBERD);
                        }
                        if(shouldShowInCreative(CTCEItems.CATALYST_BIG_BONK.get()))
                        {
                            output.accept(CTCEItems.CATALYST_BIG_BONK);
                        }
                        ///Medium Weapons
                        if(shouldShowInCreative(CTCEItems.CATALYST_GLADIUS.get()))
                        {
                            output.accept(CTCEItems.CATALYST_GLADIUS);
                        }
                        if(shouldShowInCreative(CTCEItems.CATALYST_RAPIER.get()))
                        {
                            output.accept(CTCEItems.CATALYST_RAPIER);
                        }
                        if(shouldShowInCreative(CTCEItems.CATALYST_SCYTHE.get()))
                        {
                            output.accept(CTCEItems.CATALYST_SCYTHE);
                        }
                        if(shouldShowInCreative(CTCEItems.CATALYST_BROADSWORD.get()))
                        {
                            output.accept(CTCEItems.CATALYST_BROADSWORD);
                        }
                        if(shouldShowInCreative(CTCEItems.CATALYST_ULFBERHT.get()))
                        {
                            output.accept(CTCEItems.CATALYST_ULFBERHT);
                        }
                        if(shouldShowInCreative(CTCEItems.CATALYST_BATTLEAXE.get()))
                        {
                            output.accept(CTCEItems.CATALYST_BATTLEAXE);
                        }
                        ///Light Weapons
                        if(shouldShowInCreative(CTCEItems.CATALYST_KATAR.get()))
                        {
                            output.accept(CTCEItems.CATALYST_KATAR);
                        }
                        ///Defense
                        if(shouldShowInCreative(CTCEItems.CATALYST_SHIELD.get()))
                        {
                            output.accept(CTCEItems.CATALYST_SHIELD);
                        }
                    }).build());

    ///Tools Module
    @SuppressWarnings("unused")
    public static final Supplier<CreativeModeTab> CATALYST_CORE_TOOLS = CATALYST_CREATIVE_TAB.register("catalystcore_tab3",
            ()-> CreativeModeTab.builder()
                    .icon(()-> new ItemStack(CTCEItems.CATALYST_PICKAXE.get()))
                    .title(Component.translatable("creativetab.catalystcore.tools"))
                    .displayItems((itemDisplayParameters, output) ->
                    {
                        if(shouldShowInCreative(CTCEItems.CATALYST_PAXEL.get()))
                        {
                            output.accept(CTCEItems.CATALYST_PAXEL.get());
                        }
                        if(shouldShowInCreative(CTCEItems.CATALYST_PICKAXE.get()))
                        {
                            output.accept(CTCEItems.CATALYST_PICKAXE.get());
                        }
                        if(shouldShowInCreative(CTCEItems.CATALYST_AXE.get()))
                        {
                            output.accept(CTCEItems.CATALYST_AXE.get());
                        }
                        if(shouldShowInCreative(CTCEItems.CATALYST_SHOVEL.get()))
                        {
                            output.accept(CTCEItems.CATALYST_SHOVEL.get());
                        }
                    }).build());

    /// Blocks
    @SuppressWarnings("unused")
    public static final Supplier<CreativeModeTab> CATALYST_CORE_BLOCKS = CATALYST_CREATIVE_TAB.register("catalystcore_tab6",
            ()-> CreativeModeTab.builder()
                    .icon(()-> new ItemStack(CTCEBlocks.REINFORCED_GLASS.get()))
                    .title(Component.translatable("creativetab.catalystcore.blocks"))
                    .displayItems((itemDisplayParameters, output) ->
                    {
                        output.accept(CTCEBlocks.CATALYST_ALTAR_PEDESTAL);
                        output.accept(CTCEBlocks.CAULDRON);
                        output.accept(CTCEBlocks.REINFORCED_GLASS);
                    }).build());

    public static void register(IEventBus eventBus)
    {
        CATALYST_CREATIVE_TAB.register(eventBus);
    }
}

