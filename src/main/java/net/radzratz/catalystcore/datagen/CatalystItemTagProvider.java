package net.radzratz.catalystcore.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.radzratz.catalystcore.CatalystCore;
import net.radzratz.catalystcore.items.CatalystItems;
import net.radzratz.catalystcore.util.tag.CatalystTags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosTags;

import java.util.concurrent.CompletableFuture;

public class CatalystItemTagProvider extends ItemTagsProvider
{
    public CatalystItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                                   CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(output, lookupProvider, blockTags, CatalystCore.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider)
    {
        ///Items
        tag(CatalystTags.Items.CATALYST_ITEMS)
                ///End Game
                .add(CatalystItems.FULL_CATALYST.asItem())
                .add(CatalystItems.BLOOD_VORTEX.asItem())
                .add(CatalystItems.MAGIC_ANOMALY.asItem())
                .add(CatalystItems.ANTIMATTER.asItem())
                .add(CatalystItems.RIFT.asItem())
                .add(CatalystItems.CONTAINER.asItem())
                .add(CatalystItems.MALICIOUS_EYE.asItem())
                .add(CatalystItems.TREASURE.asItem())
                .add(CatalystItems.BLACK_HOLE.asItem())
                .add(CatalystItems.CHOCOLATE_BAR.asItem())
                .add(CatalystItems.COSMIC_SHATTERER.asItem())
                .add(CatalystItems.FORBIDDEN_ORB.asItem())
                .add(CatalystItems.CHAOS_CRYSTAL.asItem())
                ///Mid Game
                .add(CatalystItems.FRAME_CATALYST.asItem())
                .add(CatalystItems.PENTAGRAM.asItem())
                .add(CatalystItems.SELF_AWARE_CHIP.asItem())
                .add(CatalystItems.COMET_SHARD.asItem())
                .add(CatalystItems.CONTAINER_INACTIVE.asItem())
                .add(CatalystItems.WATER_ORB.asItem())
                .add(CatalystItems.LAVA_ORB.asItem())
                .add(CatalystItems.END_CORE.asItem())
                .add(CatalystItems.NETHER_CORE.asItem())
                .add(CatalystItems.OVERWORLD_CORE.asItem());

        tag(CatalystTags.Items.CATALYST_TOOLS)
                .add(CatalystItems.CATALYST_BIG_BONK.get())
                .add(CatalystItems.CATALYST_BROADSWORD.get())
                .add(CatalystItems.CATALYST_GLADIUS.get())
                .add(CatalystItems.CATALYST_RAPIER.get())
                .add(CatalystItems.CATALYST_GREATSWORD.get())
                .add(CatalystItems.CATALYST_HALBERD.get())
                .add(CatalystItems.CATALYST_KATAR.get())
                .add(CatalystItems.CATALYST_PAXEL.asItem())
                .add(CatalystItems.CATALYST_PICKAXE.get())
                .add(CatalystItems.CATALYST_AXE.asItem())
                .add(CatalystItems.CATALYST_SCYTHE.get())
                .add(CatalystItems.CATALYST_SHIELD.get())
                .add(CatalystItems.CATALYST_SHOVEL.get())
                .add(CatalystItems.CATALYST_ULFBERHT.get())
                .add(CatalystItems.CATALYST_BATTLEAXE.get())
                .add(CatalystItems.CATALYST_ZWEIHANDER.get());

        tag(Tags.Items.ENCHANTABLES)
                .add(CatalystItems.CATALYST_GREATSWORD.asItem())
                .add(CatalystItems.CATALYST_ZWEIHANDER.asItem())
                .add(CatalystItems.CATALYST_ULFBERHT.asItem())
                .add(CatalystItems.CATALYST_GLADIUS.asItem())
                .add(CatalystItems.CATALYST_SCYTHE.asItem())
                .add(CatalystItems.CATALYST_RAPIER.asItem())
                .add(CatalystItems.CATALYST_KATAR.asItem())
                .add(CatalystItems.CATALYST_BIG_BONK.asItem())
                .add(CatalystItems.CATALYST_BROADSWORD.asItem())
                .add(CatalystItems.CATALYST_HALBERD.asItem())
                .add(CatalystItems.CATALYST_PICKAXE.asItem())
                .add(CatalystItems.CATALYST_AXE.asItem())
                .add(CatalystItems.CATALYST_PAXEL.asItem())
                .add(CatalystItems.CATALYST_BATTLEAXE.get())
                .add(CatalystItems.CATALYST_SHOVEL.asItem());

        ///Weapons
        tag(Tags.Items.MELEE_WEAPON_TOOLS)
                .add(CatalystItems.CATALYST_GREATSWORD.asItem())
                .add(CatalystItems.CATALYST_ZWEIHANDER.asItem())
                .add(CatalystItems.CATALYST_ULFBERHT.asItem())
                .add(CatalystItems.CATALYST_GLADIUS.asItem())
                .add(CatalystItems.CATALYST_SCYTHE.asItem())
                .add(CatalystItems.CATALYST_RAPIER.asItem())
                .add(CatalystItems.CATALYST_KATAR.asItem())
                .add(CatalystItems.CATALYST_BIG_BONK.asItem())
                .add(CatalystItems.CATALYST_BROADSWORD.asItem())
                .add(CatalystItems.CATALYST_HALBERD.asItem())
                .add(CatalystItems.CATALYST_PAXEL.asItem())
                .add(CatalystItems.CATALYST_PICKAXE.asItem())
                .add(CatalystItems.CATALYST_AXE.asItem())
                .add(CatalystItems.CATALYST_BATTLEAXE.get())
                .add(CatalystItems.CATALYST_SHOVEL.asItem());

        tag(ItemTags.SWORDS)
                .add(CatalystItems.CATALYST_GREATSWORD.asItem())
                .add(CatalystItems.CATALYST_ZWEIHANDER.asItem())
                .add(CatalystItems.CATALYST_ULFBERHT.asItem())
                .add(CatalystItems.CATALYST_GLADIUS.asItem())
                .add(CatalystItems.CATALYST_SCYTHE.asItem())
                .add(CatalystItems.CATALYST_KATAR.asItem())
                .add(CatalystItems.CATALYST_BROADSWORD.asItem())
                .add(CatalystItems.CATALYST_PAXEL.asItem())
                .add(CatalystItems.CATALYST_RAPIER.asItem())
                .add(CatalystItems.CATALYST_HALBERD.asItem());

        tag(ItemTags.AXES).add(CatalystItems.CATALYST_BATTLEAXE.get());

        tag(Tags.Items.TOOLS_SHIELD).add(CatalystItems.CATALYST_SHIELD.asItem());

        tag(CatalystTags.Items.CATALYST_HAMMER).add(CatalystItems.CATALYST_BIG_BONK.asItem());

        ///Tools
        tag(Tags.Items.TOOLS)
                .add(CatalystItems.CATALYST_PAXEL.asItem())
                .add(CatalystItems.CATALYST_PICKAXE.asItem())
                .add(CatalystItems.CATALYST_AXE.asItem())
                .add(CatalystItems.CATALYST_SHOVEL.asItem());

        tag(ItemTags.PICKAXES).add(CatalystItems.CATALYST_PICKAXE.asItem());
        tag(ItemTags.SHOVELS).add(CatalystItems.CATALYST_SHOVEL.asItem());
        tag(ItemTags.AXES).add(CatalystItems.CATALYST_AXE.asItem());

        tag(ItemTags.SHOVELS).add(CatalystItems.CATALYST_PAXEL.asItem());
        tag(ItemTags.AXES).add(CatalystItems.CATALYST_PAXEL.asItem());
        tag(ItemTags.PICKAXES).add(CatalystItems.CATALYST_PAXEL.asItem());
        tag(ItemTags.HOES).add(CatalystItems.CATALYST_PAXEL.asItem());

        ///Curios
        tag(CuriosTags.createItemTag("catalyst")).add(CatalystItems.FULL_CATALYST.get());
    }
}