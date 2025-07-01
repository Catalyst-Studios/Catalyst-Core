package net.radzratz.catalystcore.common.datagen.models;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.radzratz.catalystcore.CatalystCore;
import net.radzratz.catalystcore.client.items.CTCEItems;

public class CTCEModelProvider extends ItemModelProvider
{
    public CTCEModelProvider(PackOutput output, ExistingFileHelper existingFileHelper)
    {
        super(output, CatalystCore.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels()
    {
        basicItem(CTCEItems.MALICIOUS_EYE.get());
        basicItem(CTCEItems.FULL_CATALYST.get());
        basicItem(CTCEItems.FRAME_CATALYST.get());
        basicItem(CTCEItems.PENTAGRAM.get());
        basicItem(CTCEItems.SELF_AWARE_CHIP.get());
        basicItem(CTCEItems.CONTAINER.get());
        basicItem(CTCEItems.COMET_SHARD.get());
        basicItem(CTCEItems.RIFT.get());
        basicItem(CTCEItems.ANTIMATTER.get());
        basicItem(CTCEItems.MAGIC_ANOMALY.get());
        basicItem(CTCEItems.BLOOD_VORTEX.get());
        basicItem(CTCEItems.TREASURE.get());
        basicItem(CTCEItems.BLACK_HOLE.get());
        basicItem(CTCEItems.CONTAINER_INACTIVE.get());
        basicItem(CTCEItems.WATER_ORB.get());
        basicItem(CTCEItems.END_CORE.get());
        basicItem(CTCEItems.NETHER_CORE.get());
        basicItem(CTCEItems.OVERWORLD_CORE.get());
        basicItem(CTCEItems.CHOCOLATE_BAR.get());
        basicItem(CTCEItems.COSMIC_SHATTERER.get());
        basicItem(CTCEItems.FORBIDDEN_ORB.get());
        basicItem(CTCEItems.LAVA_ORB.get());
        basicItem(CTCEItems.CHAOS_CRYSTAL.get());
        basicItem(CTCEItems.SPIRIT_AGGLOMERATIO.get());
        basicItem(CTCEItems.WARDEN_CORE.get());
        basicItem(CTCEItems.ELDER_CORE.get());
        basicItem(CTCEItems.EMPTY_CRYSTAL.get());
        basicItem(CTCEItems.FIRE_CRYSTAL.get());
        basicItem(CTCEItems.WATER_CRYSTAL.get());
        basicItem(CTCEItems.EARTH_CRYSTAL.get());
        basicItem(CTCEItems.WIND_CRYSTAL.get());
        basicItem(CTCEItems.LIGHT_CRYSTAL.get());
        basicItem(CTCEItems.DARKNESS_CRYSTAL.get());
        basicItem(CTCEItems.BURRITO.get());
        basicItem(CTCEItems.COSMIC_ABOMINATION.get());
        basicItem(CTCEItems.WITHER_CORE.get());
        basicItem(CTCEItems.DRAGON_CORE.get());
        basicItem(CTCEItems.REINFORCED_BOTTLE.get());
        basicItem(CTCEItems.DRAGON_BLOOD.get());
        basicItem(CTCEItems.WITHERED_BOTTLE.get());
        basicItem(CTCEItems.SCULK_BOTTLE.get());
        basicItem(CTCEItems.TORCHFLOWER_BOTTLE.get());
        basicItem(CTCEItems.CODEX.get());
        basicItem(CTCEItems.RUNIC_EMPTY.get());
        basicItem(CTCEItems.RUNIC_BLOOD.get());
        basicItem(CTCEItems.RUNIC_ARCANE.get());
        basicItem(CTCEItems.INFECTED_SLATE.get());
        basicItem(CTCEItems.MYCELIUM_BOTTLE.get());
        basicItem(CTCEItems.RUNIC_ENDER.get());
        basicItem(CTCEItems.LIFE_ESSENCE_BOTTLE.get());
        basicItem(CTCEItems.PIXIE_BOTTLE.get());
        basicItem(CTCEItems.RUNIC_ESOTHERICAL.get());
        basicItem(CTCEItems.BROKEN_PEARL.get());
        basicItem(CTCEItems.DAGGER.get());
        basicItem(CTCEItems.STRANGE_ROCK.get());
        basicItem(CTCEItems.ARES.get());
        basicItem(CTCEItems.DRAGON_SCALE.get());
        basicItem(CTCEItems.ICAROS.get());

        handheldItem(CTCEItems.WAND_1.get());
    }
}
