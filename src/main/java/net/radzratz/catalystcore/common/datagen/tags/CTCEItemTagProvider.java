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
import net.radzratz.catalystcore.client.blocks.CTCEBlocks;
import net.radzratz.catalystcore.client.items.CTCEItems;
import net.radzratz.catalystcore.client.items.tags.CTCETags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosTags;

import java.util.concurrent.CompletableFuture;

public class CTCEItemTagProvider extends ItemTagsProvider
{
    public CTCEItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                               CompletableFuture<TagLookup<Block>> blockTags,
                               @Nullable ExistingFileHelper existingFileHelper)
    {
        super(output, lookupProvider, blockTags, CatalystCore.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider)
    {
        ///Items
        tag(CTCETags.Items.CATALYST_ITEMS)
                ///End Game
                .add(CTCEItems.FULL_CATALYST.asItem())
                .add(CTCEItems.BLOOD_VORTEX.asItem())
                .add(CTCEItems.MAGIC_ANOMALY.asItem())
                .add(CTCEItems.ANTIMATTER.asItem())
                .add(CTCEItems.RIFT.asItem())
                .add(CTCEItems.CONTAINER.asItem())
                .add(CTCEItems.MALICIOUS_EYE.asItem())
                .add(CTCEItems.TREASURE.asItem())
                .add(CTCEItems.BLACK_HOLE.asItem())
                .add(CTCEItems.CHOCOLATE_BAR.asItem())
                .add(CTCEItems.COSMIC_SHATTERER.asItem())
                .add(CTCEItems.FORBIDDEN_ORB.asItem())
                .add(CTCEItems.CHAOS_CRYSTAL.asItem())
                .add(CTCEItems.SPIRIT_AGGLOMERATIO.asItem())
                .add(CTCEItems.COSMIC_ABOMINATION.asItem())
                ///Mid Game
                .add(CTCEItems.FRAME_CATALYST.asItem())
                .add(CTCEItems.PENTAGRAM.asItem())
                .add(CTCEItems.SELF_AWARE_CHIP.asItem())
                .add(CTCEItems.COMET_SHARD.asItem())
                .add(CTCEItems.CONTAINER_INACTIVE.asItem())
                .add(CTCEItems.WATER_ORB.asItem())
                .add(CTCEItems.LAVA_ORB.asItem())
                .add(CTCEItems.END_CORE.asItem())
                .add(CTCEItems.NETHER_CORE.asItem())
                .add(CTCEItems.OVERWORLD_CORE.asItem())
                .add(CTCEItems.WARDEN_CORE.asItem())
                .add(CTCEItems.ELDER_CORE.asItem())
                .add(CTCEItems.WITHER_CORE.asItem())
                .add(CTCEItems.DRAGON_CORE.asItem())
                .add(CTCEItems.EMPTY_CRYSTAL.asItem())
                .add(CTCEItems.FIRE_CRYSTAL.asItem())
                .add(CTCEItems.WATER_CRYSTAL.asItem())
                .add(CTCEItems.EARTH_CRYSTAL.asItem())
                .add(CTCEItems.WIND_CRYSTAL.asItem())
                .add(CTCEItems.LIGHT_CRYSTAL.asItem())
                .add(CTCEItems.DARKNESS_CRYSTAL.asItem())
                ///Misc
                .add(CTCEItems.CODEX.asItem())
                .add(CTCEItems.BROKEN_PEARL.asItem())
                .add(CTCEItems.STRANGE_ROCK.asItem())
                .add(CTCEItems.DAGGER.asItem())
                .add(CTCEItems.DRAGON_SCALE.asItem())
                ///Foods
                .add(CTCEItems.BURRITO.asItem())
                ///Slates
                .add(CTCEItems.RUNIC_EMPTY.get())
                .add(CTCEItems.RUNIC_BLOOD.get())
                .add(CTCEItems.RUNIC_ARCANE.get())
                .add(CTCEItems.RUNIC_ENDER.get())
                .add(CTCEItems.INFECTED_SLATE.get())
                .add(CTCEItems.RUNIC_ESOTHERICAL.get())
                ///Bottles
                .add(CTCEItems.REINFORCED_BOTTLE.asItem())
                .add(CTCEItems.DRAGON_BLOOD.asItem())
                .add(CTCEItems.WITHERED_BOTTLE.asItem())
                .add(CTCEItems.SCULK_BOTTLE.asItem())
                .add(CTCEItems.MYCELIUM_BOTTLE.asItem())
                .add(CTCEItems.PIXIE_BOTTLE.asItem())
                .add(CTCEItems.LIFE_ESSENCE_BOTTLE.asItem())
                .add(CTCEItems.TORCHFLOWER_BOTTLE.asItem());

        tag(Tags.Items.FOODS).add(CTCEItems.BURRITO.asItem());

        tag(CTCETags.Items.CATALYST_TOOLS)
                .add(CTCEItems.CATALYST_BIG_BONK.get())
                .add(CTCEItems.CATALYST_BROADSWORD.get())
                .add(CTCEItems.CATALYST_GLADIUS.get())
                .add(CTCEItems.CATALYST_RAPIER.get())
                .add(CTCEItems.CATALYST_GREATSWORD.get())
                .add(CTCEItems.CATALYST_HALBERD.get())
                .add(CTCEItems.CATALYST_KATAR.get())
                .add(CTCEItems.CATALYST_PAXEL.asItem())
                .add(CTCEItems.CATALYST_PICKAXE.get())
                .add(CTCEItems.CATALYST_AXE.asItem())
                .add(CTCEItems.CATALYST_SCYTHE.get())
                .add(CTCEItems.CATALYST_SHIELD.get())
                .add(CTCEItems.CATALYST_SHOVEL.get())
                .add(CTCEItems.CATALYST_ULFBERHT.get())
                .add(CTCEItems.CATALYST_BATTLEAXE.get())
                .add(CTCEItems.CATALYST_ZWEIHANDER.get());

        tag(Tags.Items.ENCHANTABLES)
                .add(CTCEItems.CATALYST_GREATSWORD.asItem())
                .add(CTCEItems.CATALYST_ZWEIHANDER.asItem())
                .add(CTCEItems.CATALYST_ULFBERHT.asItem())
                .add(CTCEItems.CATALYST_GLADIUS.asItem())
                .add(CTCEItems.CATALYST_SCYTHE.asItem())
                .add(CTCEItems.CATALYST_RAPIER.asItem())
                .add(CTCEItems.CATALYST_KATAR.asItem())
                .add(CTCEItems.CATALYST_BIG_BONK.asItem())
                .add(CTCEItems.CATALYST_BROADSWORD.asItem())
                .add(CTCEItems.CATALYST_HALBERD.asItem())
                .add(CTCEItems.CATALYST_PICKAXE.asItem())
                .add(CTCEItems.CATALYST_AXE.asItem())
                .add(CTCEItems.CATALYST_PAXEL.asItem())
                .add(CTCEItems.CATALYST_BATTLEAXE.get())
                .add(CTCEItems.CATALYST_SHOVEL.asItem());

        ///Weapons
        tag(Tags.Items.MELEE_WEAPON_TOOLS)
                .add(CTCEItems.CATALYST_GREATSWORD.asItem())
                .add(CTCEItems.CATALYST_ZWEIHANDER.asItem())
                .add(CTCEItems.CATALYST_ULFBERHT.asItem())
                .add(CTCEItems.CATALYST_GLADIUS.asItem())
                .add(CTCEItems.CATALYST_SCYTHE.asItem())
                .add(CTCEItems.CATALYST_RAPIER.asItem())
                .add(CTCEItems.CATALYST_KATAR.asItem())
                .add(CTCEItems.CATALYST_BIG_BONK.asItem())
                .add(CTCEItems.CATALYST_BROADSWORD.asItem())
                .add(CTCEItems.CATALYST_HALBERD.asItem())
                .add(CTCEItems.CATALYST_PAXEL.asItem())
                .add(CTCEItems.CATALYST_PICKAXE.asItem())
                .add(CTCEItems.CATALYST_AXE.asItem())
                .add(CTCEItems.CATALYST_BATTLEAXE.get())
                .add(CTCEItems.CATALYST_SHOVEL.asItem());

        tag(ItemTags.SWORDS)
                .add(CTCEItems.CATALYST_GREATSWORD.asItem())
                .add(CTCEItems.CATALYST_ZWEIHANDER.asItem())
                .add(CTCEItems.CATALYST_ULFBERHT.asItem())
                .add(CTCEItems.CATALYST_GLADIUS.asItem())
                .add(CTCEItems.CATALYST_SCYTHE.asItem())
                .add(CTCEItems.CATALYST_KATAR.asItem())
                .add(CTCEItems.CATALYST_BROADSWORD.asItem())
                .add(CTCEItems.CATALYST_PAXEL.asItem())
                .add(CTCEItems.CATALYST_RAPIER.asItem())
                .add(CTCEItems.CATALYST_HALBERD.asItem());

        tag(ItemTags.AXES).add(CTCEItems.CATALYST_BATTLEAXE.get());

        tag(Tags.Items.TOOLS_SHIELD).add(CTCEItems.CATALYST_SHIELD.asItem());

        tag(CTCETags.Items.CATALYST_HAMMER).add(CTCEItems.CATALYST_BIG_BONK.asItem());

        ///Tools
        tag(Tags.Items.TOOLS)
                .add(CTCEItems.CATALYST_PAXEL.asItem())
                .add(CTCEItems.CATALYST_PICKAXE.asItem())
                .add(CTCEItems.CATALYST_AXE.asItem())
                .add(CTCEItems.CATALYST_SHOVEL.asItem());

        tag(ItemTags.PICKAXES).add(CTCEItems.CATALYST_PICKAXE.asItem());
        tag(ItemTags.SHOVELS).add(CTCEItems.CATALYST_SHOVEL.asItem());
        tag(ItemTags.AXES).add(CTCEItems.CATALYST_AXE.asItem());

        tag(ItemTags.SHOVELS).add(CTCEItems.CATALYST_PAXEL.asItem());
        tag(ItemTags.AXES).add(CTCEItems.CATALYST_PAXEL.asItem());
        tag(ItemTags.PICKAXES).add(CTCEItems.CATALYST_PAXEL.asItem());
        tag(ItemTags.HOES).add(CTCEItems.CATALYST_PAXEL.asItem());

        ///Curios
        tag(CuriosTags.createItemTag("catalyst")).add(CTCEItems.FULL_CATALYST.get());

        tag(Tags.Items.STORAGE_BLOCKS).add(CTCEBlocks.REINFORCED_GLASS.asItem());
        tag(Tags.Items.GLASS_BLOCKS).add(CTCEBlocks.REINFORCED_GLASS.asItem());
        tag(Tags.Items.GLASS_BLOCKS_TINTED).add(CTCEBlocks.REINFORCED_GLASS.asItem());

        tag(CTCETags.Items.CAULDRON_ACTIVATOR_WATER).add(Items.STICK);
        tag(CTCETags.Items.CAULDRON_ACTIVATOR_LAVA).add(Items.GLISTERING_MELON_SLICE);
        tag(CTCETags.Items.CAULDRON_ACTIVATOR_UNIVERSAL).add(Items.BONE);
        tag(CTCETags.Items.CAULDRON_ACTIVATOR_FLUID).add(Items.PRISMARINE);
        tag(CTCETags.Items.CAULDRON_ACTIVATOR_REACTION_ITEM).add(Items.GHAST_TEAR);
    }
}