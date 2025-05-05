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

    @SuppressWarnings("unused")
    public static final Supplier<CreativeModeTab> CATALYST_CORE = CATALYST_CREATIVE_TAB.register("eternal_ores_tab",
            ()-> CreativeModeTab.builder().icon(()-> new ItemStack(CatalystItems.FULL_CATALYST.get()))
                    .withTabsAfter(ResourceLocation.fromNamespaceAndPath(CatalystCore.MOD_ID, "catalystcore"))
                    .title(Component.translatable("creativetab.catalystcore.catalyst_core"))
                    .displayItems((itemDisplayParameters, output) ->
                    {
                        output.accept(CatalystItems.FULL_CATALYST);
                        output.accept(CatalystItems.FRAME_CATALYST);
                        output.accept(CatalystItems.ANTIMATTER);
                        output.accept(CatalystItems.MALICIOUS_EYE);
                        output.accept(CatalystItems.PENTAGRAM);
                        output.accept(CatalystItems.SELF_AWARE_CHIP);
                        output.accept(CatalystItems.CONTAINER);
                        output.accept(CatalystItems.COMET_SHARD);
                        output.accept(CatalystItems.RIFT);
                        output.accept(CatalystItems.MAGIC_ANOMALY);
                        output.accept(CatalystItems.BLOOD_VORTEX);
                        output.accept(CatalystItems.CATALYST_SWORD);
                        output.accept(CatalystItems.CATALYST_SCYTHE);
                        output.accept(CatalystItems.CATALYST_BROADSWORD);
                        output.accept(CatalystItems.CATALYST_KATAR);
                        output.accept(CatalystItems.CATALYST_BIG_BONK);
                        output.accept(CatalystItems.CATALYST_PICKAXE);
                        output.accept(CatalystItems.CATALYST_SHOVEL);
                        output.accept(CatalystItems.CATALYST_SHIELD);
                        output.accept(CatalystBlocks.CATALYST_PEDESTAL);
                        output.accept(CatalystBlocks.EYE_FLOWER);
                    }).build());

    public static void register(IEventBus eventBus)
    {
        CATALYST_CREATIVE_TAB.register(eventBus);
    }
}

