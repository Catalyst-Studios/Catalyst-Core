package net.radzratz.catalystcore.common.util.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class CTCEConfig
{
    public static final CTCEConfig CONFIG;
    public static final ModConfigSpec CONFIG_SPEC;

    public final PentagramConfig pentagram;
    public final ModulesConfig modules;
    public final PhialEvents phials;
    public final CatalystCurios curioCompatibility;

    static
    {
        Pair<CTCEConfig, ModConfigSpec> pair = new ModConfigSpec.Builder().configure(CTCEConfig::new);
        CONFIG = pair.getLeft();
        CONFIG_SPEC = pair.getRight();
    }

    private CTCEConfig(ModConfigSpec.Builder builder)
    {
        builder.comment("Catalyst Core Main Configuration").push("general");

        this.pentagram = new PentagramConfig(builder);
        this.modules = new ModulesConfig(builder);
        this.phials = new PhialEvents(builder);
        this.curioCompatibility = new CatalystCurios(builder);

        builder.pop();
    }

    public static class PentagramConfig
    {
        public final ModConfigSpec.BooleanValue enablePentagramSpawn;
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
            /// This part of the config does NOT unregister any of CatalystCore Items
            ///
            /// This only "hides" them Items and toggles their functionality over CatalystCore Mod

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

    public static class PhialEvents
    {
        public final ModConfigSpec.BooleanValue phialEvents;
        public final ModConfigSpec.BooleanValue dragonPhial;
        public final ModConfigSpec.BooleanValue witheredPhial;
        public final ModConfigSpec.BooleanValue sculkPhial;
        public final ModConfigSpec.BooleanValue torchFlowerPhial;
        public final ModConfigSpec.BooleanValue myceliumPhial;

        public PhialEvents(ModConfigSpec.Builder builder)
        {
            /// This part of the config does NOT unregister any of CatalystCore Phials
            ///
            /// This only toggles their functionality over CatalystCore Mod

            builder.comment("Catalyst Phial Events").push("phials");

            this.phialEvents = builder
                    .comment("Server/Client - Enables all phial events. If this is set to false, the rest of this config can be ignored")
                    .translation("config.catalystcore.phials.all_phials")
                    .gameRestart()
                    .define("toggleAllPhialEvents", true); /// True by default

            this.dragonPhial = builder
                    .comment("Server/Client - Enables dragon phial event")
                    .translation("config.catalystcore.phials.dragon_phial")
                    .gameRestart()
                    .define("toggleDragonPhial", true); /// True by default

            this.witheredPhial = builder
                    .comment("Server/Client - Enables withered phial event")
                    .translation("config.catalystcore.phials.withered_phial")
                    .gameRestart()
                    .define("toggleWitheredPhial", true); /// True by default

            this.sculkPhial = builder
                    .comment("Server/Client - Enables sculk phial event")
                    .translation("config.catalystcore.phials.sculk_phial")
                    .gameRestart()
                    .define("toggleSculkPhial", true); /// True by default

            this.torchFlowerPhial = builder
                    .comment("Server/Client - Enables torchflower phial event")
                    .translation("config.catalystcore.phials.torchflower_phial")
                    .gameRestart()
                    .define("toggleTorchFlowerPhial", true); /// True by default

            this.myceliumPhial = builder
                    .comment("Server/Client - Enables mycelium phial event")
                    .translation("config.catalystcore.phials.mycelium_phial")
                    .gameRestart()
                    .define("toggleMyceliumPhial", true); /// True by default

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
            ///These values are set to use gameRestart() as it may or not crash with a Server/Client incompatibility result
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
