package net.radzratz.catalystcore.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.radzratz.catalystcore.CatalystCore;
import net.radzratz.catalystcore.items.CatalystItems;

public class CatalystModelProvider extends ItemModelProvider
{
    public CatalystModelProvider(PackOutput output, ExistingFileHelper existingFileHelper)
    {
        super(output, CatalystCore.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels()
    {
        basicItem(CatalystItems.MALICIOUS_EYE.get());
        basicItem(CatalystItems.FULL_CATALYST.get());
        basicItem(CatalystItems.FRAME_CATALYST.get());
        basicItem(CatalystItems.PENTAGRAM.get());
        basicItem(CatalystItems.SELF_AWARE_CHIP.get());
        basicItem(CatalystItems.CONTAINER.get());
        basicItem(CatalystItems.COMET_SHARD.get());
        basicItem(CatalystItems.RIFT.get());
        basicItem(CatalystItems.ANTIMATTER.get());
        basicItem(CatalystItems.MAGIC_ANOMALY.get());
        basicItem(CatalystItems.BLOOD_VORTEX.get());
        basicItem(CatalystItems.TREASURE.get());
        basicItem(CatalystItems.BLACK_HOLE.get());
        basicItem(CatalystItems.CONTAINER_INACTIVE.get());
        basicItem(CatalystItems.WATER_ORB.get());
        basicItem(CatalystItems.END_CORE.get());
        basicItem(CatalystItems.NETHER_CORE.get());
        basicItem(CatalystItems.OVERWORLD_CORE.get());
        basicItem(CatalystItems.CHOCOLATE_BAR.get());
        basicItem(CatalystItems.COSMIC_SHATTERER.get());
        basicItem(CatalystItems.FORBIDDEN_ORB.get());
        basicItem(CatalystItems.LAVA_ORB.get());
        basicItem(CatalystItems.CHAOS_CRYSTAL.get());
    }
}
