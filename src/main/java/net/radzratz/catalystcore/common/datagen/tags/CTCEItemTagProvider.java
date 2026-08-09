package net.radzratz.catalystcore.common.datagen.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.radzratz.catalystcore.CatalystCore;
import net.radzratz.catalystcore.client.items.tags.CTCETags;
import net.radzratz.catalystcore.client.items.tools.*;
import net.radzratz.catalystcore.client.items.tools.tools.*;
import net.radzratz.catalystcore.client.items.tools.weapons.*;
import net.radzratz.catalystcore.common.registries.CTCEBlockRegistry;
import net.radzratz.catalystcore.common.registries.CTCEItemRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosTags;

import java.util.concurrent.CompletableFuture;

import static net.radzratz.catalystcore.common.util.CTCEUtils.allToolEntries;

public class CTCEItemTagProvider extends ItemTagsProvider {
    public CTCEItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, CatalystCore.id, existingFileHelper);
    }

    private void tagByClass(Boolean optional, TagKey<Item> tagKey, Class<?>... types) {
        for (Class<?> type : types) {
            if (optional) {
                allToolEntries()
                        .filter(e -> type.isInstance(e.get()))
                        .forEach(e -> tag(tagKey).addOptional(BuiltInRegistries.ITEM.getKey(e.get())));
            } else {
                allToolEntries()
                        .filter(e -> type.isInstance(e.get()))
                        .forEach(e -> tag(tagKey).add(e.get()));
            }
        }
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(Tags.Items.FOODS).add(CTCEItemRegistry.BURRITO.asItem());

        Class<?>[] ENCHANTABLE_MELEE = {
                CTCESwordItem.class, CTCEBattleAxeItem.class, CTCEShovelItem.class,
                CTCEPickaxeItem.class, CTCEAxeItem.class, CTCEHoeItem.class, CTCEPaxelItem.class,
        };
        tagByClass(false, Tags.Items.ENCHANTABLES, ENCHANTABLE_MELEE);
        tagByClass(false, Tags.Items.MELEE_WEAPON_TOOLS, ENCHANTABLE_MELEE);

        tagByClass(false, ItemTags.SWORDS, CTCESwordItem.class);
        tagByClass(false, CTCETags.Items.HAMMER, CTCEBigBonkItem.class);
        tagByClass(false, CTCETags.Items.TOOL_HAMMER, CTCEBigBonkItem.class);
        tagByClass(true, CTCETags.Items.MALUM_SCYTHE, CTCEScytheItem.class);
        tagByClass(false, CTCETags.Items.SCYTHE, CTCEScytheItem.class);
        tagByClass(false, CTCETags.Items.TOOL_SCYTHE, CTCEScytheItem.class);
        tagByClass(false, CTCETags.Items.ZWEIHANDER, CTCEZweihanderItem.class);
        tagByClass(false, CTCETags.Items.TOOL_ZWEIHANDER, CTCEZweihanderItem.class);
        tagByClass(false, CTCETags.Items.LONGSWORD, CTCELongswordItem.class);
        tagByClass(false, CTCETags.Items.TOOL_LONGSWORD, CTCELongswordItem.class);
        tagByClass(false, CTCETags.Items.GREATSWORD, CTCEGreatswordItem.class, CTCEUniverseSwordItem.class);
        tagByClass(false, CTCETags.Items.TOOL_GREATSWORD, CTCEGreatswordItem.class, CTCEUniverseSwordItem.class);
        tagByClass(false, CTCETags.Items.RAPIER, CTCERapierItem.class);
        tagByClass(false, CTCETags.Items.TOOL_RAPIER, CTCERapierItem.class);
        tagByClass(false, CTCETags.Items.KATAR, CTCEKatarItem.class);
        tagByClass(false, CTCETags.Items.TOOL_KATAR, CTCEKatarItem.class);
        tagByClass(false, CTCETags.Items.HALBERD, CTCEHalberdItem.class);
        tagByClass(false, CTCETags.Items.TOOL_HALBERD, CTCEHalberdItem.class);
        tagByClass(false, CTCETags.Items.SPEAR, CTCESpearItem.class);
        tagByClass(false, CTCETags.Items.TOOL_SPEAR, CTCESpearItem.class);
        tagByClass(false, CTCETags.Items.BATTLEAXE, CTCEBattleAxeItem.class);
        tagByClass(false, CTCETags.Items.TOOL_BATTLEAXE, CTCEBattleAxeItem.class);
        tagByClass(false, CTCETags.Items.GLADIUS, CTCEGladiusItem.class);
        tagByClass(false, CTCETags.Items.TOOL_GLADIUS, CTCEGladiusItem.class);
        tagByClass(false, CTCETags.Items.ULFBERHT, CTCEUlfberhtItem.class);
        tagByClass(false, CTCETags.Items.TOOL_ULFBERHT, CTCEUlfberhtItem.class);

        tagByClass(false, ItemTags.AXES, CTCEBattleAxeItem.class, CTCEAxeItem.class, CTCEPaxelItem.class);
        tagByClass(false, ItemTags.SHOVELS, CTCEShovelItem.class, CTCEPaxelItem.class);
        tagByClass(false, ItemTags.PICKAXES, CTCEPickaxeItem.class, CTCEPaxelItem.class);
        tagByClass(false, ItemTags.HOES, CTCEHoeItem.class, CTCEPaxelItem.class);
        tagByClass(false, Tags.Items.TOOLS_SHIELD, CTCEShieldItem.class);

        tagByClass(false, Tags.Items.TOOLS, CTCEPaxelItem.class, CTCEHoeItem.class, CTCEPickaxeItem.class, CTCEAxeItem.class, CTCEShovelItem.class);

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