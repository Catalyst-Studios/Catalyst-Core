package net.radzratz.catalystcore.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.radzratz.catalystcore.CatalystCore;
import net.radzratz.catalystcore.items.CatalystItems;

import java.util.function.Supplier;

public class CatalystCreativeTab
{
    public static final DeferredRegister<CreativeModeTab> CATALYST_CREATIVE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CatalystCore.MOD_ID);

    public static final Supplier<CreativeModeTab> CATALYST_CORE = CATALYST_CREATIVE_TAB.register("eternal_ores_tab",
            ()-> CreativeModeTab.builder().icon(()-> new ItemStack(CatalystItems.MALICIOUS_EYE.get()))
                    .withTabsAfter(ResourceLocation.fromNamespaceAndPath(CatalystCore.MOD_ID, "catalystcore"))
                    .title(Component.translatable("creativetab.catalystcore.catalyst_core"))
                    .displayItems((itemDisplayParameters, output) ->
                    {
                        output.accept(CatalystItems.FULL_CATALYST);
                        output.accept(CatalystItems.FRAME_CATALYST);
                        output.accept(CatalystItems.MALICIOUS_EYE);
                        output.accept(CatalystItems.PENTAGRAM);
                        output.accept(CatalystItems.SELF_AWARE_CHIP);
                        output.accept(CatalystItems.CATALYST_SWORD);
                        output.accept(CatalystItems.CATALYST_BROADSWORD);
                        output.accept(CatalystItems.CATALYST_PICKAXE);
                    }).build());

    public static void register(IEventBus eventBus)
    {
        CATALYST_CREATIVE_TAB.register(eventBus);
    }
}

