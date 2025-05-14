package net.radzratz.catalystcore.util.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class CatalystConfig
{
    public static final CatalystConfig CONFIG;
    public static final ModConfigSpec CONFIG_SPEC;

    public final PentagramConfig pentagram;
    public final ModulesConfig modules;

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

        public ModulesConfig(ModConfigSpec.Builder builder)
        {
            builder.comment("Toggle mod features/modules").push("modules");

            this.weaponsModule = builder
                    .comment("Server/Client Side - Enable weapons/tools/armor module")
                    .translation("config.catalystcore.modules.weapons")
                    .gameRestart()
                    .define("toggleCataclysticArmouryAndWeaponry", true); ///True by default

            builder.pop();
        }
    }
}
