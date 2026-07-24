package net.radzratz.catalystcore.common.datagen.models;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.radzratz.catalystcore.CatalystCore;
import net.radzratz.catalystcore.common.registries.CTCEItemRegistry;

public class CTCEModelProvider extends ItemModelProvider {
    public CTCEModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CatalystCore.id, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(CTCEItemRegistry.MALICIOUS_EYE.get());
        basicItem(CTCEItemRegistry.FULL_CATALYST.get());
        basicItem(CTCEItemRegistry.FRAME_CATALYST.get());
        basicItem(CTCEItemRegistry.PENTAGRAM.get());
        basicItem(CTCEItemRegistry.SELF_AWARE_CHIP.get());
        basicItem(CTCEItemRegistry.CONTAINER.get());
        basicItem(CTCEItemRegistry.COMET_SHARD.get());
        basicItem(CTCEItemRegistry.RIFT.get());
        basicItem(CTCEItemRegistry.ANTIMATTER.get());
        basicItem(CTCEItemRegistry.MAGIC_ANOMALY.get());
        basicItem(CTCEItemRegistry.TREASURE.get());
        basicItem(CTCEItemRegistry.BLACK_HOLE.get());
        basicItem(CTCEItemRegistry.CONTAINER_INACTIVE.get());
        basicItem(CTCEItemRegistry.WATER_ORB.get());
        basicItem(CTCEItemRegistry.END_CORE.get());
        basicItem(CTCEItemRegistry.NETHER_CORE.get());
        basicItem(CTCEItemRegistry.OVERWORLD_CORE.get());
        basicItem(CTCEItemRegistry.CHOCOLATE_BAR.get());
        basicItem(CTCEItemRegistry.COSMIC_SHATTERER.get());
        basicItem(CTCEItemRegistry.FORBIDDEN_ORB.get());
        basicItem(CTCEItemRegistry.LAVA_ORB.get());
        basicItem(CTCEItemRegistry.CHAOS_CRYSTAL.get());
        basicItem(CTCEItemRegistry.SPIRIT_AGGLOMERATIO.get());
        basicItem(CTCEItemRegistry.WARDEN_CORE.get());
        basicItem(CTCEItemRegistry.ELDER_CORE.get());
        basicItem(CTCEItemRegistry.EMPTY_CRYSTAL.get());
        basicItem(CTCEItemRegistry.FIRE_CRYSTAL.get());
        basicItem(CTCEItemRegistry.WATER_CRYSTAL.get());
        basicItem(CTCEItemRegistry.EARTH_CRYSTAL.get());
        basicItem(CTCEItemRegistry.WIND_CRYSTAL.get());
        basicItem(CTCEItemRegistry.LIGHT_CRYSTAL.get());
        basicItem(CTCEItemRegistry.DARKNESS_CRYSTAL.get());
        basicItem(CTCEItemRegistry.BURRITO.get());
        basicItem(CTCEItemRegistry.COSMIC_ABOMINATION.get());
        basicItem(CTCEItemRegistry.WITHER_CORE.get());
        basicItem(CTCEItemRegistry.DRAGON_CORE.get());
        basicItem(CTCEItemRegistry.REINFORCED_BOTTLE.get());
        basicItem(CTCEItemRegistry.DRAGON_BLOOD.get());
        basicItem(CTCEItemRegistry.WITHERED_BOTTLE.get());
        basicItem(CTCEItemRegistry.SCULK_BOTTLE.get());
        basicItem(CTCEItemRegistry.TORCHFLOWER_BOTTLE.get());
        basicItem(CTCEItemRegistry.CODEX.get());
        basicItem(CTCEItemRegistry.RUNIC_EMPTY.get());
        basicItem(CTCEItemRegistry.RUNIC_BLOOD.get());
        basicItem(CTCEItemRegistry.RUNIC_ARCANE.get());
        basicItem(CTCEItemRegistry.INFECTED_SLATE.get());
        basicItem(CTCEItemRegistry.MYCELIUM_BOTTLE.get());
        basicItem(CTCEItemRegistry.RUNIC_ENDER.get());
        basicItem(CTCEItemRegistry.LIFE_ESSENCE_BOTTLE.get());
        basicItem(CTCEItemRegistry.PIXIE_BOTTLE.get());
        basicItem(CTCEItemRegistry.RUNIC_ESOTHERICAL.get());
        basicItem(CTCEItemRegistry.ARES.get());
        basicItem(CTCEItemRegistry.ICAROS.get());
        basicItem(CTCEItemRegistry.ETERNAL_VORTEX.get());
        basicItem(CTCEItemRegistry.ANCIENT_RELIC.get());
        basicItem(CTCEItemRegistry.BOUND_VOIDSPAWN.get());
        basicItem(CTCEItemRegistry.EMPTY_CONTAINMENT_VESSEL.get());
        basicItem(CTCEItemRegistry.CONCENTRATED_RADIANCE.get());
    }
}
