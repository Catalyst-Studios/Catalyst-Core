package net.radzratz.catalystcore.common.compat.ponder;

import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.radzratz.catalystcore.client.items.CTCEItems;
import net.radzratz.catalystcore.common.compat.ponder.scenes.phials.*;

public class CTCEScenes
{
    public static void register(PonderSceneRegistrationHelper<ResourceLocation> helper)
    {
        PonderSceneRegistrationHelper<Object> blockHelper = helper.withKeyFunction(block -> BuiltInRegistries.BLOCK.getDefaultKey());

        PonderSceneRegistrationHelper<Item> itemHelper = helper.withKeyFunction(BuiltInRegistries.ITEM::getKey);

        itemHelper.forComponents(CTCEItems.TORCHFLOWER_BOTTLE.get()).addStoryBoard("torch_flower_essence", TorchFlowerEssenceEvent::torchFlowerEssence);
        itemHelper.forComponents(CTCEItems.SCULK_BOTTLE.get()).addStoryBoard("sculk_biomass", SculkBiomassEvent::sculkBiomass);
        itemHelper.forComponents(CTCEItems.WITHERED_BOTTLE.get()).addStoryBoard("wither_essence", WitherEssenceEvent::witherEssence);
        itemHelper.forComponents(CTCEItems.DRAGON_BLOOD.get()).addStoryBoard("dragon_blood", DragonBloodEvent::dragonBlood);
        itemHelper.forComponents(CTCEItems.MYCELIUM_BOTTLE.get()).addStoryBoard("mycelium_spores", MyceliumSporeEvent::myceliumSpore);
    }
}
