package net.radzratz.catalystcore.common.compat.ponder;

import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.radzratz.catalystcore.common.registries.CTCEItemRegistry;
import net.radzratz.catalystcore.common.compat.ponder.scenes.phials.*;

public class CTCEScenes {
    public static void register(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        PonderSceneRegistrationHelper<Object> blockHelper = helper.withKeyFunction(block -> BuiltInRegistries.BLOCK.getDefaultKey());

        PonderSceneRegistrationHelper<Item> itemHelper = helper.withKeyFunction(BuiltInRegistries.ITEM::getKey);

        itemHelper.forComponents(CTCEItemRegistry.TORCHFLOWER_BOTTLE.get()).addStoryBoard("torch_flower_essence", TorchFlowerEssenceEvent::torchFlowerEssence);
        itemHelper.forComponents(CTCEItemRegistry.SCULK_BOTTLE.get()).addStoryBoard("sculk_biomass", SculkBiomassEvent::sculkBiomass);
        itemHelper.forComponents(CTCEItemRegistry.WITHERED_BOTTLE.get()).addStoryBoard("wither_essence", WitherEssenceEvent::witherEssence);
        itemHelper.forComponents(CTCEItemRegistry.DRAGON_BLOOD.get()).addStoryBoard("dragon_blood", DragonBloodEvent::dragonBlood);
        itemHelper.forComponents(CTCEItemRegistry.MYCELIUM_BOTTLE.get()).addStoryBoard("mycelium_spores", MyceliumSporeEvent::myceliumSpore);
    }
}
