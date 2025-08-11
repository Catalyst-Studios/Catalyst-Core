package net.radzratz.catalystcore.common.compat.modopedia.registry;

import net.favouriteless.modopedia.api.datagen.BookContentOutput;
import net.minecraft.core.HolderLookup;
import net.radzratz.catalystcore.common.compat.modopedia.entries.base.base.*;
import net.radzratz.catalystcore.common.compat.modopedia.entries.base.phials.MDragonPhial;
import net.radzratz.catalystcore.common.compat.modopedia.entries.base.phials.MReinforcedPhial;
import net.radzratz.catalystcore.common.compat.modopedia.entries.base.phials.MTorchFlowerPhial;
import net.radzratz.catalystcore.common.compat.modopedia.entries.base.phials.MWitheredPhial;

public class MEntriesRegistry
{
    public static void registerMainEntries(HolderLookup.Provider registries, BookContentOutput output)
    {
        MCatalystEntry.registerCatalystEntry(registries, output);
        MAresEntry.registerAresEntry(registries, output);
        MChocolateBarEntry.registerFracturedEntry(registries, output);
        MCosmicAbominationEntry.registerAbominationEntry(registries, output);
        MSpiritAgglomeratioEntry.registerFracturedEntry(registries, output);
        MCosmicShattererEntry.registerShattererEntry(registries, output);
        MFracturedCrystalEntry.registerFracturedEntry(registries, output);
        MMagicAnomalyEntry.registerAnomalyEntry(registries, output);
        MICarosEntry.registerICarosEntry(registries, output);
        MVortexEntry.registerVortexEntry(registries, output);
    }

    public static void registerPhialEntries(HolderLookup.Provider registries, BookContentOutput output)
    {
        MReinforcedPhial.registerReinforcedPhial(registries, output);
        MTorchFlowerPhial.registerTorchFlowerPhial(registries, output);
        MDragonPhial.registerDragonBloodPhial(registries, output);
        MWitheredPhial.registerWitheredPhial(registries, output);
    }
}
