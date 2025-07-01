package net.radzratz.catalystcore.common.compat.curios.curio.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.radzratz.catalystcore.CatalystCore;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.CuriosDataProvider;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.concurrent.CompletableFuture;

public class CTCECuriosProvider extends CuriosDataProvider
{
    public CTCECuriosProvider(PackOutput output,
                              ExistingFileHelper fileHelper,
                              CompletableFuture<HolderLookup.Provider> registries)
    {
        super(CatalystCore.MOD_ID, output, fileHelper, registries);
    }

    @Override
    public void generate(HolderLookup.Provider registries, ExistingFileHelper fileHelper)
    {
        this.createSlot("catalyst")
                .size(1)
                .order(50)
                .icon(ResourceLocation.fromNamespaceAndPath("catalystcore", "item/catalyst_slot"))
                .dropRule(ICurio.DropRule.ALWAYS_KEEP)
                .addValidator(ResourceLocation.fromNamespaceAndPath(CuriosApi.MODID, "tag"));

        this.createEntities("catalyst")
                .addPlayer()
                .addSlots("catalyst");
    }
}
