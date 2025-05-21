package net.radzratz.catalystcore.util.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class CatalystConfig
{
    public static final CatalystConfig CONFIG;
    public static final ModConfigSpec CONFIG_SPEC;

    public final PentagramConfig pentagram;
    public final ModulesConfig modules;
    public final CatalystCurios curioCompatibility;

    static
    {
        Pair<CatalystConfig, ModConfigSpec> pair = new ModConfigSpec.Builder().configure(CatalystConfig::new);
        CONFIG = pair.getLeft();
        CONFIG_SPEC = pair.getRight();
    }

    private CatalystConfig(ModConfigSpec.Builder builder)
    {
        builder.comment("Catalyst Core Main Configuration").push("general");

        this.pentagram = new PentagramConfig(builder);
        this.modules = new ModulesConfig(builder);
        this.curioCompatibility = new CatalystCurios(builder);

        builder.pop();
    }

    public static class PentagramConfig
    {
        public final ModConfigSpec.BooleanValue enablePentagramSpawn;
        public final ModConfigSpec.BooleanValue enableHardcodedRecipes;
        public final ModConfigSpec.BooleanValue enablePentagramSpecialRenderers;
        public final ModConfigSpec.BooleanValue enablePentagramParticles;
        public final ModConfigSpec.BooleanValue enablePentagramSounds;

        public PentagramConfig(ModConfigSpec.Builder builder)
        {
            builder.comment("Pentagram settings").push("pentagram");

            this.enablePentagramSpawn = builder
                    .comment("Server/Client Side - Enable Pentagram spawn [REQUIRES GAME RESTART]")
                    .translation("config.catalystcore.pentagram.entity")
                    .gameRestart()
                    .define("togglePentagramEntity", true); ///True by default

            this.enableHardcodedRecipes = builder
                    .comment("Server/Client Side - Enable Pentagram debug recipes")
                    .translation("config.catalystcore.pentagram.hardcoded_recipes")
                    .gameRestart()
                    .define("toggleDebugRecipe", false); ///False by default

            this.enablePentagramSpecialRenderers = builder
                    .comment("Client Side - Enable Pentagram renderers")
                    .translation("config.catalystcore.pentagram.special_renderers")
                    .define("toggleRenderers", true); ///True by default

            this.enablePentagramParticles = builder
                    .comment("Client Side - Enable Pentagram particles")
                    .translation("config.catalystcore.pentagram.particles")
                    .define("toggleParticles", true); ///True by default

            this.enablePentagramSounds = builder
                    .comment("Client Side - Enable Pentagram sounds")
                    .translation("config.catalystcore.pentagram.sounds")
                    .define("toggleSounds", true); ///True by default

            builder.pop();
        }
    }

    public static class ModulesConfig
    {
        public final ModConfigSpec.BooleanValue weaponsModule;
        public final ModConfigSpec.BooleanValue toolsModule;

        public ModulesConfig(ModConfigSpec.Builder builder)
        {
            ///Only Hides the tools and weapons from CatalystCore Mod
            ///It does not unregister them in any way
            ///Any existing Tool or Weapon within any world is safe and sound
            ///With any of these values set to "false"

            builder.comment("Toggle mod features/modules").push("modules");

            this.weaponsModule = builder
                    .comment("Server/Client Side - Enable weapons/armor module")
                    .translation("config.catalystcore.modules.weapons")
                    .gameRestart()
                    .define("toggleCataclysticArmouryAndWeaponry", true); ///True by default

            this.toolsModule = builder
                    .comment("Server/Client Side - Enable tools module")
                    .translation("config.catalystcore.modules.tools")
                    .gameRestart()
                    .define("toggleCataclysticTools", true); ///True by default

            builder.pop();
        }
    }

    public static class CatalystCurios
    {
        ///Catalyst Curio
        public final ModConfigSpec.BooleanValue catalystCurioEffects;

        public final ModConfigSpec.BooleanValue catalystNightVision;
        public final ModConfigSpec.BooleanValue catalystDamageBoost;
        public final ModConfigSpec.BooleanValue catalystMovementSpeed;
        public final ModConfigSpec.BooleanValue catalystSaturation;
        public final ModConfigSpec.BooleanValue catalystWaterBreathing;
        public final ModConfigSpec.BooleanValue catalystLuck;
        public final ModConfigSpec.BooleanValue catalystHealthBoost;
        public final ModConfigSpec.BooleanValue catalystFireResistance;
        public final ModConfigSpec.BooleanValue catalystRegeneration;

        public CatalystCurios(ModConfigSpec.Builder builder)
        {
            ///Dedicated Config for PackDevs who don't want any type of effects within the Catalyst Item using CuriosAPI Mod
            ///
            ///These values are set to use gameRestart() as it may or not crash with Server/Client incompatibility
            ///and im not willing to find this out honestly... so restart yer game each time ye disable these
            ///
            ///Will someone read all of this!? Dunno but hey ya reader!
            builder.comment("Catalyst Curio Effects").push("catalyst_curio");

            this.catalystCurioEffects = builder
                    .comment("Defines if All Catalyst Curio Effects are Enabled" +
                            " - " +
                            "If this option is disabled the rest of this config can be ignored" +
                            " - " +
                            "Requires Game Restart")
                    .translation("config.catalystcore.catalyst_curio.all_effects")
                    .gameRestart()
                    .define("toggleAllEffects", true); ///True by default

            this.catalystNightVision = builder
                    .comment("Defines if Night Vision Effect is enabled" + " - " + "Requires Game Restart")
                    .translation("config.catalystcore.catalyst_curio.night_vision")
                    .gameRestart()
                    .define("toggleNightVisionEffect", true); ///True by default

            this.catalystDamageBoost = builder
                    .comment("Defines if Damage Boost Effect is enabled" + " - " + "Requires Game Restart")
                    .translation("config.catalystcore.catalyst_curio.damage_boost")
                    .gameRestart()
                    .define("toggleDamageBoostEffect", true); ///True by default

            this.catalystMovementSpeed = builder
                    .comment("Defines if Movement Speed Effect is Enabled" + " - " + "Requires Game Restart")
                    .translation("config.catalystcore.catalyst_curio.movement_speed")
                    .gameRestart()
                    .define("toggleMovementSpeedEffect", true); ///True by default

            this.catalystSaturation = builder
                    .comment("Defines if Saturation Effect is Enabled" + " - " + "Requires Game Restart")
                    .translation("config.catalystcore.catalyst_curio.saturation")
                    .gameRestart()
                    .define("toggleSaturationEffect", true); ///True by default

            this.catalystWaterBreathing = builder
                    .comment("Defines if Water Breathing Effect is Enabled" + " - " + "Requires Game Restart")
                    .translation("config.catalystcore.catalyst_curio.water_breathing")
                    .gameRestart()
                    .define("toggleWaterBreathingEffect", true); ///True by default

            this.catalystLuck = builder
                    .comment("Defines if Luck Effect is Enabled" + " - " + "Requires Game Restart")
                    .translation("config.catalystcore.catalyst_curio.luck")
                    .gameRestart()
                    .define("toggleLuckEffect", true); ///True by default

            this.catalystHealthBoost = builder
                    .comment("Defines if Health Boost Effect is Enabled" + " - " + "Requires Game Restart")
                    .translation("config.catalystcore.catalyst_curio.health_boost")
                    .gameRestart()
                    .define("toggleHealthBoostEffect", true); ///True by default

            this.catalystFireResistance = builder
                    .comment("Defines if Fire Resistance Effect is Enabled" + " - " + "Requires Game Restart")
                    .translation("config.catalystcore.catalyst_curio.fire_resistance")
                    .gameRestart()
                    .define("toggleFireResistanceEffect", true); ///True by default

            this.catalystRegeneration = builder
                    .comment("Defines if Regeneration Effect is Enabled" + " - " + "Requires Game Restart")
                    .translation("config.catalystcore.catalyst_curio.regeneration")
                    .gameRestart()
                    .define("toggleRegenerationEffect", true); ///True by default

            builder.pop();
        }
    }

}
