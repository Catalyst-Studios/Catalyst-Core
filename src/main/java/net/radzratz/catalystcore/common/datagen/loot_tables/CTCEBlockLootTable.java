package net.radzratz.catalystcore.common.datagen.loot_tables;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.radzratz.catalystcore.client.blocks.CTCEBlocks;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class CTCEBlockLootTable extends BlockLootSubProvider
{
    public CTCEBlockLootTable(HolderLookup.Provider registries)
    {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate()
    {
        dropSelf(CTCEBlocks.CATALYST_ALTAR_PEDESTAL.get());
        dropSelf(CTCEBlocks.REINFORCED_GLASS.get());
        dropSelf(CTCEBlocks.CAULDRON.get());
    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops)
    {
        HolderLookup.RegistryLookup<Enchantment> registryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock, LootItem.lootTableItem(item)
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                .apply(ApplyBonusCount.addOreBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE))));
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks()
    {
        return CTCEBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }

}
