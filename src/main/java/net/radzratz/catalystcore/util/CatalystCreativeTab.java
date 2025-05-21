package net.radzratz.catalystcore.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.radzratz.catalystcore.CatalystCore;
import net.radzratz.catalystcore.blocks.CatalystBlocks;
import net.radzratz.catalystcore.items.CatalystItems;
import net.radzratz.catalystcore.util.config.CatalystItemInterface;

import java.util.function.Supplier;

public class CatalystCreativeTab
{
    public static final DeferredRegister<CreativeModeTab> CATALYST_CREATIVE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CatalystCore.MOD_ID);

    private static boolean shouldShowInCreative(Item item)
    {
        if(item instanceof CatalystItemInterface ghostItem)
        {
            return ghostItem.shouldAppear();
        }
        return true;
    }

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
                        output.accept(CatalystItems.FORBIDDEN_ORB);
                        output.accept(CatalystItems.CHAOS_CRYSTAL);
                        output.accept(CatalystItems.FRAME_CATALYST);
                        output.accept(CatalystItems.SELF_AWARE_CHIP);
                        output.accept(CatalystItems.PENTAGRAM);
                        output.accept(CatalystItems.COMET_SHARD);
                        output.accept(CatalystItems.CONTAINER_INACTIVE);
                        output.accept(CatalystItems.LAVA_ORB);
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
                    .icon(()-> new ItemStack(CatalystItems.CATALYST_GREATSWORD.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(CatalystCore.MOD_ID, "catalystcore_tab1"))
                    .title(Component.translatable("creativetab.catalystcore.weapons"))
                    .displayItems((itemDisplayParameters, output) ->
                    {
                        ///Heavy Weapons
                        if(shouldShowInCreative(CatalystItems.UNIVERSE_SWORD.get()))
                        {
                            output.accept(CatalystItems.UNIVERSE_SWORD);
                        }
                        if(shouldShowInCreative(CatalystItems.CATALYST_GREATSWORD.get()))
                        {
                            output.accept(CatalystItems.CATALYST_GREATSWORD);
                        }
                        if(shouldShowInCreative(CatalystItems.CATALYST_ZWEIHANDER.get()))
                        {
                            output.accept(CatalystItems.CATALYST_ZWEIHANDER);
                        }
                        if(shouldShowInCreative(CatalystItems.CATALYST_HALBERD.get()))
                        {
                            output.accept(CatalystItems.CATALYST_HALBERD);
                        }
                        if(shouldShowInCreative(CatalystItems.CATALYST_BIG_BONK.get()))
                        {
                            output.accept(CatalystItems.CATALYST_BIG_BONK);
                        }
                        ///Medium Weapons
                        if(shouldShowInCreative(CatalystItems.CATALYST_GLADIUS.get()))
                        {
                            output.accept(CatalystItems.CATALYST_GLADIUS);
                        }
                        if(shouldShowInCreative(CatalystItems.CATALYST_RAPIER.get()))
                        {
                            output.accept(CatalystItems.CATALYST_RAPIER);
                        }
                        if(shouldShowInCreative(CatalystItems.CATALYST_SCYTHE.get()))
                        {
                            output.accept(CatalystItems.CATALYST_SCYTHE);
                        }
                        if(shouldShowInCreative(CatalystItems.CATALYST_BROADSWORD.get()))
                        {
                            output.accept(CatalystItems.CATALYST_BROADSWORD);
                        }
                        if(shouldShowInCreative(CatalystItems.CATALYST_ULFBERHT.get()))
                        {
                            output.accept(CatalystItems.CATALYST_ULFBERHT);
                        }
                        if(shouldShowInCreative(CatalystItems.CATALYST_BATTLEAXE.get()))
                        {
                            output.accept(CatalystItems.CATALYST_BATTLEAXE);
                        }
                        ///Light Weapons
                        if(shouldShowInCreative(CatalystItems.CATALYST_KATAR.get()))
                        {
                            output.accept(CatalystItems.CATALYST_KATAR);
                        }
                        ///Defense
                        if(shouldShowInCreative(CatalystItems.CATALYST_SHIELD.get()))
                        {
                            output.accept(CatalystItems.CATALYST_SHIELD);
                        }
                    }).build());

    ///Tools Module
    @SuppressWarnings("unused")
    public static final Supplier<CreativeModeTab> CATALYST_CORE_TOOLS = CATALYST_CREATIVE_TAB.register("catalyscore_tab3",
            ()-> CreativeModeTab.builder()
                    .icon(()-> new ItemStack(CatalystItems.CATALYST_PICKAXE.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(CatalystCore.MOD_ID, "catalystcore_tab2"))
                    .title(Component.translatable("creativetab.catalystcore.tools"))
                    .displayItems((itemDisplayParameters, output) ->
                    {
                        if(shouldShowInCreative(CatalystItems.CATALYST_PAXEL.get()))
                        {
                            output.accept(CatalystItems.CATALYST_PAXEL.get());
                        }
                        if(shouldShowInCreative(CatalystItems.CATALYST_PICKAXE.get()))
                        {
                            output.accept(CatalystItems.CATALYST_PICKAXE.get());
                        }
                        if(shouldShowInCreative(CatalystItems.CATALYST_AXE.get()))
                        {
                            output.accept(CatalystItems.CATALYST_AXE.get());
                        }
                        if(shouldShowInCreative(CatalystItems.CATALYST_SHOVEL.get()))
                        {
                            output.accept(CatalystItems.CATALYST_SHOVEL.get());
                        }
                    }).build());

    public static void register(IEventBus eventBus)
    {
        CATALYST_CREATIVE_TAB.register(eventBus);
    }
}

