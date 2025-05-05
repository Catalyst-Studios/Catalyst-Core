package net.radzratz.catalystcore.util.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class CatalystConfig
{
    public static final CatalystConfig CONFIG;
    public static final ModConfigSpec CONFIG_SPEC;

    ///Hardcoded Pentagram Recipes
    public final ModConfigSpec.BooleanValue enableHardcodedPentagramRecipes;

    private CatalystConfig(ModConfigSpec.Builder builder)
    {
        builder.push("pentagram");
        enableHardcodedPentagramRecipes = builder
                .comment("Enable hardcoded Pentagram recipes (for debugging or custom functionality)")
                .translation("catalystcore.config.pentagram.enableHardcodedPentagramRecipes")
                .define("enableHardcodedPentagramRecipes", false);

        builder.pop();
    }

    static
    {
        Pair<CatalystConfig, ModConfigSpec> pair =
                new ModConfigSpec.Builder().configure(CatalystConfig::new);

        CONFIG = pair.getLeft();
        CONFIG_SPEC = pair.getRight();
    }
}
