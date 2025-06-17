package net.radzratz.catalystcore.ponder;

import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.radzratz.catalystcore.items.CatalystItems;
import net.radzratz.catalystcore.ponder.phials.SculkBiomassEvent;
import net.radzratz.catalystcore.ponder.phials.TorchFlowerEssenceEvent;

public class CatalystScenes
{
    public static void register(PonderSceneRegistrationHelper<ResourceLocation> helper)
    {
        PonderSceneRegistrationHelper<Object> blockHelper = helper.withKeyFunction(block -> BuiltInRegistries.BLOCK.getDefaultKey());

        PonderSceneRegistrationHelper<Item> itemHelper = helper.withKeyFunction(BuiltInRegistries.ITEM::getKey);

        itemHelper.forComponents(CatalystItems.TORCHFLOWER_BOTTLE.get()).addStoryBoard("torch_flower_essence", TorchFlowerEssenceEvent::torchFlowerEssence);
        itemHelper.forComponents(CatalystItems.SCULK_BOTTLE.get()).addStoryBoard("sculk_biomass", SculkBiomassEvent::sculkBiomass);
    }
}
