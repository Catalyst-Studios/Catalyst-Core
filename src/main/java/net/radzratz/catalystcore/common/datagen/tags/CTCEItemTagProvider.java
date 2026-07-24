package net.radzratz.catalystcore.common.datagen.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.radzratz.catalystcore.CatalystCore;
import net.radzratz.catalystcore.common.registries.CTCEBlockRegistry;
import net.radzratz.catalystcore.common.registries.CTCEItemRegistry;
import net.radzratz.catalystcore.common.registries.CTCEToolsRegistry;
import net.radzratz.catalystcore.client.items.tags.CTCETags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosTags;

import java.util.concurrent.CompletableFuture;

public class CTCEItemTagProvider extends ItemTagsProvider {
    public CTCEItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, CatalystCore.id, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(Tags.Items.FOODS).add(CTCEItemRegistry.BURRITO.asItem());

        tag(CTCETags.Items.CATALYST_TOOLS)
                .add(CTCEToolsRegistry.CATALYST_BIG_BONK.get())
                .add(CTCEToolsRegistry.CATALYST_BROADSWORD.get())
                .add(CTCEToolsRegistry.CATALYST_GLADIUS.get())
                .add(CTCEToolsRegistry.CATALYST_RAPIER.get())
                .add(CTCEToolsRegistry.CATALYST_GREATSWORD.get())
                .add(CTCEToolsRegistry.CATALYST_HALBERD.get())
                .add(CTCEToolsRegistry.CATALYST_KATAR.get())
                .add(CTCEToolsRegistry.CATALYST_PAXEL.asItem())
                .add(CTCEToolsRegistry.CATALYST_PICKAXE.get())
                .add(CTCEToolsRegistry.CATALYST_AXE.asItem())
                .add(CTCEToolsRegistry.CATALYST_SCYTHE.get())
                .add(CTCEToolsRegistry.CATALYST_SHIELD.get())
                .add(CTCEToolsRegistry.CATALYST_SHOVEL.get())
                .add(CTCEToolsRegistry.CATALYST_ULFBERHT.get())
                .add(CTCEToolsRegistry.CATALYST_BATTLEAXE.get())
                .add(CTCEToolsRegistry.CATALYST_ZWEIHANDER.get());

        tag(Tags.Items.ENCHANTABLES)
                .add(CTCEToolsRegistry.CATALYST_GREATSWORD.asItem())
                .add(CTCEToolsRegistry.CATALYST_ZWEIHANDER.asItem())
                .add(CTCEToolsRegistry.CATALYST_ULFBERHT.asItem())
                .add(CTCEToolsRegistry.CATALYST_GLADIUS.asItem())
                .add(CTCEToolsRegistry.CATALYST_SCYTHE.asItem())
                .add(CTCEToolsRegistry.CATALYST_RAPIER.asItem())
                .add(CTCEToolsRegistry.CATALYST_KATAR.asItem())
                .add(CTCEToolsRegistry.CATALYST_BIG_BONK.asItem())
                .add(CTCEToolsRegistry.CATALYST_BROADSWORD.asItem())
                .add(CTCEToolsRegistry.CATALYST_HALBERD.asItem())
                .add(CTCEToolsRegistry.CATALYST_PICKAXE.asItem())
                .add(CTCEToolsRegistry.CATALYST_AXE.asItem())
                .add(CTCEToolsRegistry.CATALYST_PAXEL.asItem())
                .add(CTCEToolsRegistry.CATALYST_BATTLEAXE.get())
                .add(CTCEToolsRegistry.UNIVERSE_SWORD.get())
                .add(CTCEToolsRegistry.CATALYST_SHOVEL.asItem());

        tag(Tags.Items.MELEE_WEAPON_TOOLS)
                .add(CTCEToolsRegistry.CATALYST_GREATSWORD.asItem())
                .add(CTCEToolsRegistry.CATALYST_ZWEIHANDER.asItem())
                .add(CTCEToolsRegistry.CATALYST_ULFBERHT.asItem())
                .add(CTCEToolsRegistry.CATALYST_GLADIUS.asItem())
                .add(CTCEToolsRegistry.CATALYST_SCYTHE.asItem())
                .add(CTCEToolsRegistry.CATALYST_RAPIER.asItem())
                .add(CTCEToolsRegistry.CATALYST_KATAR.asItem())
                .add(CTCEToolsRegistry.CATALYST_BIG_BONK.asItem())
                .add(CTCEToolsRegistry.CATALYST_BROADSWORD.asItem())
                .add(CTCEToolsRegistry.CATALYST_HALBERD.asItem())
                .add(CTCEToolsRegistry.CATALYST_PAXEL.asItem())
                .add(CTCEToolsRegistry.CATALYST_PICKAXE.asItem())
                .add(CTCEToolsRegistry.CATALYST_AXE.asItem())
                .add(CTCEToolsRegistry.CATALYST_BATTLEAXE.get())
                .add(CTCEToolsRegistry.UNIVERSE_SWORD.get())
                .add(CTCEToolsRegistry.CATALYST_SHOVEL.asItem());

        tag(ItemTags.SWORDS)
                .add(CTCEToolsRegistry.CATALYST_GREATSWORD.asItem())
                .add(CTCEToolsRegistry.CATALYST_ZWEIHANDER.asItem())
                .add(CTCEToolsRegistry.CATALYST_ULFBERHT.asItem())
                .add(CTCEToolsRegistry.CATALYST_GLADIUS.asItem())
                .add(CTCEToolsRegistry.CATALYST_SCYTHE.asItem())
                .add(CTCEToolsRegistry.CATALYST_KATAR.asItem())
                .add(CTCEToolsRegistry.CATALYST_BROADSWORD.asItem())
                .add(CTCEToolsRegistry.CATALYST_PAXEL.asItem())
                .add(CTCEToolsRegistry.CATALYST_RAPIER.asItem())
                .add(CTCEToolsRegistry.UNIVERSE_SWORD.get())
                .add(CTCEToolsRegistry.CATALYST_HALBERD.asItem());

        tag(ItemTags.AXES).add(CTCEToolsRegistry.CATALYST_BATTLEAXE.get());

        tag(Tags.Items.TOOLS_SHIELD).add(CTCEToolsRegistry.CATALYST_SHIELD.asItem());

        tag(CTCETags.Items.CATALYST_HAMMER).add(CTCEToolsRegistry.CATALYST_BIG_BONK.asItem());

        tag(Tags.Items.TOOLS)
                .add(CTCEToolsRegistry.CATALYST_PAXEL.asItem())
                .add(CTCEToolsRegistry.CATALYST_PICKAXE.asItem())
                .add(CTCEToolsRegistry.CATALYST_AXE.asItem())
                .add(CTCEToolsRegistry.CATALYST_SHOVEL.asItem());

        tag(ItemTags.PICKAXES).add(CTCEToolsRegistry.CATALYST_PICKAXE.asItem());
        tag(ItemTags.SHOVELS).add(CTCEToolsRegistry.CATALYST_SHOVEL.asItem());
        tag(ItemTags.AXES).add(CTCEToolsRegistry.CATALYST_AXE.asItem());

        tag(ItemTags.SHOVELS).add(CTCEToolsRegistry.CATALYST_PAXEL.asItem());
        tag(ItemTags.AXES).add(CTCEToolsRegistry.CATALYST_PAXEL.asItem());
        tag(ItemTags.PICKAXES).add(CTCEToolsRegistry.CATALYST_PAXEL.asItem());
        tag(ItemTags.HOES).add(CTCEToolsRegistry.CATALYST_PAXEL.asItem());

        tag(CuriosTags.createItemTag("catalyst")).add(CTCEItemRegistry.FULL_CATALYST.get());

        tag(Tags.Items.STORAGE_BLOCKS).add(CTCEBlockRegistry.REINFORCED_GLASS.asItem());
        tag(Tags.Items.GLASS_BLOCKS).add(CTCEBlockRegistry.REINFORCED_GLASS.asItem());
        tag(Tags.Items.GLASS_BLOCKS_TINTED).add(CTCEBlockRegistry.REINFORCED_GLASS.asItem());

        tag(CTCETags.Items.CAULDRON_ACTIVATOR_WATER).add(Items.STICK);
        tag(CTCETags.Items.CAULDRON_ACTIVATOR_LAVA).add(Items.GLISTERING_MELON_SLICE);
        tag(CTCETags.Items.CAULDRON_ACTIVATOR_UNIVERSAL).add(Items.BONE);
        tag(CTCETags.Items.CAULDRON_ACTIVATOR_FLUID).add(Items.PRISMARINE);
        tag(CTCETags.Items.CAULDRON_ACTIVATOR_REACTION_ITEM).add(Items.GHAST_TEAR);
        tag(CTCETags.Items.CAULDRON_ACTIVATOR_REACTION_FLUID).add(Items.FLINT);
    }
}