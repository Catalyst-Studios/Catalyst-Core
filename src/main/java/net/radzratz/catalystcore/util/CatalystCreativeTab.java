package net.radzratz.catalystcore.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.radzratz.catalystcore.CatalystCore;
import net.radzratz.catalystcore.blocks.CatalystBlocks;
import net.radzratz.catalystcore.items.CatalystItems;

import java.util.function.Supplier;

public class CatalystCreativeTab
{
    public static final DeferredRegister<CreativeModeTab> CATALYST_CREATIVE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CatalystCore.MOD_ID);

    ///Base Module
    @SuppressWarnings("unused")
    public static final Supplier<CreativeModeTab> CATALYST_CORE_BASE = CATALYST_CREATIVE_TAB.register("catalystcore_tab1",
            ()-> CreativeModeTab.builder()
                    .icon(()-> new ItemStack(CatalystItems.FULL_CATALYST.get()))
                    .title(Component.translatable("creativetab.catalystcore.base"))
                    .displayItems((itemDisplayParameters, output) ->
                    {
                        output.accept(CatalystItems.FULL_CATALYST);
                        output.accept(CatalystItems.ANTIMATTER);
                        output.accept(CatalystItems.MALICIOUS_EYE);
                        output.accept(CatalystItems.CONTAINER);
                        output.accept(CatalystItems.RIFT);
                        output.accept(CatalystItems.MAGIC_ANOMALY);
                        output.accept(CatalystItems.BLOOD_VORTEX);
                        output.accept(CatalystItems.TREASURE);
                        output.accept(CatalystItems.BLACK_HOLE);
                        output.accept(CatalystItems.CHOCOLATE_BAR);
                        output.accept(CatalystItems.COSMIC_SHATTERER);
                        output.accept(CatalystItems.FRAME_CATALYST);
                        output.accept(CatalystItems.SELF_AWARE_CHIP);
                        output.accept(CatalystItems.PENTAGRAM);
                        output.accept(CatalystItems.COMET_SHARD);
                        output.accept(CatalystItems.CONTAINER_INACTIVE);
                        output.accept(CatalystItems.WATER_ORB);
                        output.accept(CatalystItems.END_CORE);
                        output.accept(CatalystItems.NETHER_CORE);
                        output.accept(CatalystItems.OVERWORLD_CORE);
                        output.accept(CatalystBlocks.CATALYST_PEDESTAL);
                    }).build());

    ///Weapons Module
    @SuppressWarnings("unused")
    public static final Supplier<CreativeModeTab> CATALYST_CORE_WEAPONS = CATALYST_CREATIVE_TAB.register("catalystcore_tab2",
            ()-> CreativeModeTab.builder()
                    .icon(()-> new ItemStack(CatalystItems.CATALYST_SWORD.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(CatalystCore.MOD_ID, "catalystcore_tab1"))
                    .title(Component.translatable("creativetab.catalystcore.weapons"))
                    .displayItems((itemDisplayParameters, output) ->
                    {
                        output.accept(CatalystItems.CATALYST_SWORD);
                        output.accept(CatalystItems.CATALYST_GLADIUS);
                        output.accept(CatalystItems.CATALYST_SCYTHE);
                        output.accept(CatalystItems.CATALYST_BROADSWORD);
                        output.accept(CatalystItems.CATALYST_HALBERD);
                        output.accept(CatalystItems.CATALYST_KATAR);
                        output.accept(CatalystItems.CATALYST_BIG_BONK);
                        output.accept(CatalystItems.CATALYST_PICKAXE);
                        output.accept(CatalystItems.CATALYST_SHOVEL);
                        output.accept(CatalystItems.CATALYST_SHIELD);
                    }).build());

    public static void register(IEventBus eventBus)
    {
        CATALYST_CREATIVE_TAB.register(eventBus);
    }
}

