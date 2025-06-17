package net.radzratz.catalystcore.ponder;

import net.createmod.ponder.api.registration.PonderPlugin;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.createmod.ponder.api.registration.PonderTagRegistrationHelper;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class CatalystPonderPlugin implements PonderPlugin
{
    @Override
    public @NotNull String getModId()
    {
        return "catalystcore";
    }

    @Override
    public void registerTags(@NotNull PonderTagRegistrationHelper<ResourceLocation> helper)
    {
        CatalystPonderTags.register(helper);
    }

    @Override
    public void registerScenes(@NotNull PonderSceneRegistrationHelper<ResourceLocation> helper)
    {
        CatalystScenes.register(helper);
    }
}
