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
        basicItem(CatalystItems.SPIRIT_AGGLOMERATIO.get());
        basicItem(CatalystItems.WARDEN_CORE.get());
        basicItem(CatalystItems.ELDER_CORE.get());
        basicItem(CatalystItems.EMPTY_CRYSTAL.get());
        basicItem(CatalystItems.FIRE_CRYSTAL.get());
        basicItem(CatalystItems.WATER_CRYSTAL.get());
        basicItem(CatalystItems.EARTH_CRYSTAL.get());
        basicItem(CatalystItems.WIND_CRYSTAL.get());
        basicItem(CatalystItems.LIGHT_CRYSTAL.get());
        basicItem(CatalystItems.DARKNESS_CRYSTAL.get());
        basicItem(CatalystItems.BURRITO.get());
        basicItem(CatalystItems.COSMIC_ABOMINATION.get());
        basicItem(CatalystItems.WITHER_CORE.get());
        basicItem(CatalystItems.DRAGON_CORE.get());
        basicItem(CatalystItems.REINFORCED_BOTTLE.get());
        basicItem(CatalystItems.DRAGON_BLOOD.get());
        basicItem(CatalystItems.WITHERED_BOTTLE.get());
        basicItem(CatalystItems.SCULK_BOTTLE.get());
        basicItem(CatalystItems.TORCHFLOWER_BOTTLE.get());
        basicItem(CatalystItems.CODEX.get());
        basicItem(CatalystItems.RUNIC_EMPTY.get());
        basicItem(CatalystItems.RUNIC_BLOOD.get());
        basicItem(CatalystItems.RUNIC_ARCANE.get());
        basicItem(CatalystItems.INFECTED_SLATE.get());
        basicItem(CatalystItems.MYCELIUM_BOTTLE.get());
        basicItem(CatalystItems.RUNIC_ENDER.get());
    }
}
