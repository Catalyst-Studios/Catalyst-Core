package net.radzratz.catalystcore.common.compat.modopedia.registry;

import com.google.gson.JsonElement;
import net.radzratz.catalystcore.common.compat.modopedia.entries.mainentries.base.*;
import net.radzratz.catalystcore.common.compat.modopedia.entries.mainentries.phials.MDragonPhial;
import net.radzratz.catalystcore.common.compat.modopedia.entries.mainentries.phials.MReinforcedPhial;
import net.radzratz.catalystcore.common.compat.modopedia.entries.mainentries.phials.MTorchFlowerPhial;
import net.radzratz.catalystcore.common.compat.modopedia.entries.mainentries.phials.MWitheredPhial;

import java.util.function.BiConsumer;

public class MEntriesRegistry
{
    public static void registerMainEntries(BiConsumer<String, JsonElement> output)
    {
        MCatalystEntry.registerCatalystEntry(output);
        MAresEntry.registerAresEntry(output);
        MChocolateBarEntry.registerFracturedEntry(output);
        MCosmicAbominationEntry.registerAbominationEntry(output);
        MSpiritAgglomeratioEntry.registerFracturedEntry(output);
        MCosmicShattererEntry.registerShattererEntry(output);
        MFracturedCrystalEntry.registerFracturedEntry(output);
        MMagicAnomalyEntry.registerAnomalyEntry(output);
        MICarosEntry.registerICarosEntry(output);
    }

    public static void registerPhialEntries(BiConsumer<String, JsonElement> output)
    {
        MReinforcedPhial.registerReinforcedPhial(output);
        MTorchFlowerPhial.registerTorchFlowerPhial(output);
        MDragonPhial.registerDragonBloodPhial(output);
        MWitheredPhial.registerWitheredPhial(output);
    }
}
