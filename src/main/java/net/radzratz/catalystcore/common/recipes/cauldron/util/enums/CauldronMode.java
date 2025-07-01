package net.radzratz.catalystcore.common.recipes.cauldron.util.enums;

public enum CauldronMode
{
    /// Cauldron is in Standby
    NONE,

    /// Activates WBExecutor Type
    WATER_BREWING,

    /// Activates LavaBrewing Type
    LAVA_BREWING,

    /// Activates UniversalBrewing Type
    UNIVERSAL_BREWING,

    /// Activates FluidBrewing Type
    FLUID_BREWING,

    /// Activates ReactionBrewing Type
    REACTION_BREWING_ITEM,
    REACTION_BREWING_FLUID;

    public boolean isBrewingMode()
    {
        return this != NONE;
    }
}
