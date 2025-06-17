package net.radzratz.catalystcore.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.radzratz.catalystcore.CatalystCore;
import net.radzratz.catalystcore.blocks.CatalystBlocks;
import net.radzratz.catalystcore.items.CatalystItems;
import net.radzratz.catalystcore.util.tag.CatalystTags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosTags;

import java.util.concurrent.CompletableFuture;

public class CatalystItemTagProvider extends ItemTagsProvider
{
    public CatalystItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                                   CompletableFuture<TagLookup<Block>> blockTags,
                                   @Nullable ExistingFileHelper existingFileHelper)
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
                .add(CatalystItems.SPIRIT_AGGLOMERATIO.asItem())
                .add(CatalystItems.COSMIC_ABOMINATION.asItem())
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
                .add(CatalystItems.OVERWORLD_CORE.asItem())
                .add(CatalystItems.WARDEN_CORE.asItem())
                .add(CatalystItems.ELDER_CORE.asItem())
                .add(CatalystItems.WITHER_CORE.asItem())
                .add(CatalystItems.DRAGON_CORE.asItem())
                .add(CatalystItems.EMPTY_CRYSTAL.asItem())
                .add(CatalystItems.FIRE_CRYSTAL.asItem())
                .add(CatalystItems.WATER_CRYSTAL.asItem())
                .add(CatalystItems.EARTH_CRYSTAL.asItem())
                .add(CatalystItems.WIND_CRYSTAL.asItem())
                .add(CatalystItems.LIGHT_CRYSTAL.asItem())
                .add(CatalystItems.DARKNESS_CRYSTAL.asItem())
                ///Foods
                .add(CatalystItems.CODEX.asItem())
                .add(CatalystItems.BURRITO.asItem())
                ///Slates
                .add(CatalystItems.RUNIC_EMPTY.get())
                .add(CatalystItems.RUNIC_BLOOD.get())
                .add(CatalystItems.RUNIC_ARCANE.get())
                .add(CatalystItems.RUNIC_ENDER.get())
                .add(CatalystItems.INFECTED_SLATE.get())
                ///Bottles
                .add(CatalystItems.REINFORCED_BOTTLE.asItem())
                .add(CatalystItems.DRAGON_BLOOD.asItem())
                .add(CatalystItems.WITHERED_BOTTLE.asItem())
                .add(CatalystItems.SCULK_BOTTLE.asItem())
                .add(CatalystItems.MYCELIUM_BOTTLE.asItem())
                .add(CatalystItems.TORCHFLOWER_BOTTLE.asItem());

        tag(Tags.Items.FOODS).add(CatalystItems.BURRITO.asItem());

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

        tag(Tags.Items.STORAGE_BLOCKS).add(CatalystBlocks.REINFORCED_GLASS.asItem());
        tag(Tags.Items.GLASS_BLOCKS).add(CatalystBlocks.REINFORCED_GLASS.asItem());
        tag(Tags.Items.GLASS_BLOCKS_TINTED).add(CatalystBlocks.REINFORCED_GLASS.asItem());
    }
}