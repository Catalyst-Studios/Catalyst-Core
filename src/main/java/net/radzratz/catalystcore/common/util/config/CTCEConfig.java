package net.radzratz.catalystcore.common.util.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class CTCEConfig {
    public static final CTCEConfig CONFIG;
    public static final ModConfigSpec CONFIG_SPEC;

    public final PentagramConfig pentagram;
    public final ModulesConfig modules;
    public final Phials phials;
    public final CatalystCurios curioCompatibility;
    public final AnomalyConfig anomaly;
    public final NebulaConfig nebula;
    public final WarpConfig warp;

    static {
        Pair<CTCEConfig, ModConfigSpec> pair = new ModConfigSpec.Builder().configure(CTCEConfig::new);
        CONFIG = pair.getLeft();
        CONFIG_SPEC = pair.getRight();
    }

    private CTCEConfig(ModConfigSpec.Builder builder) {
        builder.comment("Catalyst Core Main Configuration").push("general");

        this.pentagram = new PentagramConfig(builder);
        this.modules = new ModulesConfig(builder);
        this.phials = new Phials(builder);
        this.curioCompatibility = new CatalystCurios(builder);
        this.anomaly = new AnomalyConfig(builder);
        this.nebula = new NebulaConfig(builder);
        this.warp = new WarpConfig(builder);

        builder.pop();
    }

    public static class PentagramConfig {
        public final ModConfigSpec.BooleanValue enablePentagramSpawn;
        public final ModConfigSpec.BooleanValue enablePentagramSpecialRenderers;
        public final ModConfigSpec.BooleanValue enablePentagramParticles;
        public final ModConfigSpec.BooleanValue enablePentagramSounds;

        public PentagramConfig(ModConfigSpec.Builder builder) {
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

    public static class ModulesConfig {
        public final ModConfigSpec.BooleanValue weaponry;
        public final ModConfigSpec.BooleanValue battleAxeIron;
        public final ModConfigSpec.BooleanValue battleAxeDiamond;
        public final ModConfigSpec.BooleanValue battleAxeNetherite;
        public final ModConfigSpec.BooleanValue battleAxeCataclystic;
        public final ModConfigSpec.BooleanValue broadswordCataclystic;
        public final ModConfigSpec.BooleanValue gladiusIron;
        public final ModConfigSpec.BooleanValue gladiusDiamond;
        public final ModConfigSpec.BooleanValue gladiusNetherite;
        public final ModConfigSpec.BooleanValue gladiusCataclystic;
        public final ModConfigSpec.BooleanValue greatSwordIron;
        public final ModConfigSpec.BooleanValue greatSwordDiamond;
        public final ModConfigSpec.BooleanValue greatSwordNetherite;
        public final ModConfigSpec.BooleanValue greatSwordCataclystic;
        public final ModConfigSpec.BooleanValue halberdIron;
        public final ModConfigSpec.BooleanValue halberdDiamond;
        public final ModConfigSpec.BooleanValue halberdNetherite;
        public final ModConfigSpec.BooleanValue halberdCataclystic;
        public final ModConfigSpec.BooleanValue hammerIron;
        public final ModConfigSpec.BooleanValue hammerDiamond;
        public final ModConfigSpec.BooleanValue hammerNetherite;
        public final ModConfigSpec.BooleanValue hammerCataclystic;
        public final ModConfigSpec.BooleanValue katarIron;
        public final ModConfigSpec.BooleanValue katarDiamond;
        public final ModConfigSpec.BooleanValue katarNetherite;
        public final ModConfigSpec.BooleanValue katarCataclystic;
        public final ModConfigSpec.BooleanValue longswordIron;
        public final ModConfigSpec.BooleanValue longswordDiamond;
        public final ModConfigSpec.BooleanValue longswordNetherite;
        public final ModConfigSpec.BooleanValue longswordCataclystic;
        public final ModConfigSpec.BooleanValue rapierIron;
        public final ModConfigSpec.BooleanValue rapierDiamond;
        public final ModConfigSpec.BooleanValue rapierNetherite;
        public final ModConfigSpec.BooleanValue rapierCataclystic;
        public final ModConfigSpec.BooleanValue scytheIron;
        public final ModConfigSpec.BooleanValue scytheDiamond;
        public final ModConfigSpec.BooleanValue scytheNetherite;
        public final ModConfigSpec.BooleanValue scytheCataclystic;
        public final ModConfigSpec.BooleanValue shield;
        public final ModConfigSpec.BooleanValue spearCataclystic;
        public final ModConfigSpec.BooleanValue ulfberhtIron;
        public final ModConfigSpec.BooleanValue ulfberhtDiamond;
        public final ModConfigSpec.BooleanValue ulfberhtNetherite;
        public final ModConfigSpec.BooleanValue ulfberhtCataclystic;
        public final ModConfigSpec.BooleanValue universeSword;
        public final ModConfigSpec.BooleanValue zweihanderIron;
        public final ModConfigSpec.BooleanValue zweihanderDiamond;
        public final ModConfigSpec.BooleanValue zweihanderNetherite;
        public final ModConfigSpec.BooleanValue zweihanderCataclystic;

        public final ModConfigSpec.BooleanValue tools;
        public final ModConfigSpec.BooleanValue axe;
        public final ModConfigSpec.BooleanValue hoe;
        public final ModConfigSpec.BooleanValue paxel;
        public final ModConfigSpec.BooleanValue pickaxe;
        public final ModConfigSpec.BooleanValue shovel;

        public ModulesConfig(ModConfigSpec.Builder builder) {
            builder.comment("Catalyst Tools, Weaponry and Armoury").push("tools_and_weaponry");

            this.weaponry = weaponAndTool(builder, "catalyst_weaponry_module");

            this.battleAxeIron = weaponAndTool(builder, "iron_battle_axe");
            this.battleAxeDiamond = weaponAndTool(builder, "diamond_battle_axe");
            this.battleAxeNetherite = weaponAndTool(builder, "netherite_battle_axe");
            this.battleAxeCataclystic = weaponAndTool(builder, "cataclystic_battle_axe");

            this.broadswordCataclystic = weaponAndTool(builder, "cataclystic_broadsword");

            this.gladiusIron = weaponAndTool(builder, "iron_gladius");
            this.gladiusDiamond = weaponAndTool(builder, "diamond_gladius");
            this.gladiusNetherite = weaponAndTool(builder, "netherite_gladius");
            this.gladiusCataclystic = weaponAndTool(builder, "cataclystic_gladius");

            this.greatSwordIron = weaponAndTool(builder, "iron_greatsword");
            this.greatSwordDiamond = weaponAndTool(builder, "diamond_greatsword");
            this.greatSwordNetherite = weaponAndTool(builder, "netherite_greatsword");
            this.greatSwordCataclystic = weaponAndTool(builder, "cataclystic_greatsword");

            this.halberdIron = weaponAndTool(builder, "iron_halberd");
            this.halberdDiamond = weaponAndTool(builder, "diamond_halberd");
            this.halberdNetherite = weaponAndTool(builder, "netherite_halberd");
            this.halberdCataclystic = weaponAndTool(builder, "cataclystic_halberd");

            this.hammerIron = weaponAndTool(builder, "iron_hammer");
            this.hammerDiamond = weaponAndTool(builder, "diamond_hammer");
            this.hammerNetherite = weaponAndTool(builder, "netherite_hammer");
            this.hammerCataclystic = weaponAndTool(builder, "cataclystic_hammer");

            this.katarIron = weaponAndTool(builder, "iron_katar");
            this.katarDiamond = weaponAndTool(builder, "diamond_katar");
            this.katarNetherite = weaponAndTool(builder, "netherite_katar");
            this.katarCataclystic = weaponAndTool(builder, "cataclystic_katar");

            this.longswordIron = weaponAndTool(builder, "cataclystic_iron");
            this.longswordDiamond = weaponAndTool(builder, "cataclystic_diamond");
            this.longswordNetherite = weaponAndTool(builder, "cataclystic_netherite");
            this.longswordCataclystic = weaponAndTool(builder, "cataclystic_longsword");

            this.rapierIron = weaponAndTool(builder, "iron_rapier");
            this.rapierDiamond = weaponAndTool(builder, "diamond_rapier");
            this.rapierNetherite = weaponAndTool(builder, "netherite_rapier");
            this.rapierCataclystic = weaponAndTool(builder, "cataclystic_rapier");

            this.scytheIron = weaponAndTool(builder, "iron_scythe");
            this.scytheDiamond = weaponAndTool(builder, "diamond_scythe");
            this.scytheNetherite = weaponAndTool(builder, "netherite_scythe");
            this.scytheCataclystic = weaponAndTool(builder, "cataclystic_scythe");

            this.shield = weaponAndTool(builder, "cataclystic_shield");

            this.spearCataclystic = weaponAndTool(builder, "cataclystic_spear");

            this.ulfberhtIron = weaponAndTool(builder, "iron_ulfberht");
            this.ulfberhtDiamond = weaponAndTool(builder, "diamond_ulfberht");
            this.ulfberhtNetherite = weaponAndTool(builder, "netherite_ulfberht");
            this.ulfberhtCataclystic = weaponAndTool(builder, "cataclystic_ulfberht");

            this.universeSword = weaponAndTool(builder, "universe_sword");

            this.zweihanderIron = weaponAndTool(builder, "iron_zweihander");
            this.zweihanderDiamond = weaponAndTool(builder, "diamond_zweihander");
            this.zweihanderNetherite = weaponAndTool(builder, "netherite_zweihander");
            this.zweihanderCataclystic = weaponAndTool(builder, "cataclystic_zweihander");

            builder.pop();
            builder.push("tools");

            this.tools = weaponAndTool(builder, "catalyst_tools_module");
            this.axe = weaponAndTool(builder, "cataclystic_axe");
            this.hoe = weaponAndTool(builder, "cataclystic_hoe");
            this.paxel = weaponAndTool(builder, "cataclystic_paxel");
            this.pickaxe = weaponAndTool(builder, "cataclystic_pickaxe");
            this.shovel = weaponAndTool(builder, "cataclystic_shovel");

            builder.pop();
        }

        private ModConfigSpec.BooleanValue weaponAndTool(ModConfigSpec.Builder bldr, String name) {
            String id = toDisplayName(name);
            String path = toPathName(name);

            return bldr.comment("[Server] Defines if " + id + " is enabled")
                    .gameRestart()
                    .define("is" + path + "Enabled", true);
        }
    }

    public static class Phials {
        public final ModConfigSpec.BooleanValue phialEvents;
        public final ModConfigSpec.BooleanValue dragonPhialEvent;
        public final ModConfigSpec.BooleanValue dragonPhial;
        public final ModConfigSpec.BooleanValue witheredPhialEvent;
        public final ModConfigSpec.BooleanValue witheredPhial;
        public final ModConfigSpec.BooleanValue sculkPhialEvent;
        public final ModConfigSpec.BooleanValue sculkPhial;
        public final ModConfigSpec.BooleanValue torchFlowerPhialEvent;
        public final ModConfigSpec.BooleanValue torchFlowerPhial;
        public final ModConfigSpec.BooleanValue myceliumPhialEvent;
        public final ModConfigSpec.BooleanValue myceliumPhial;
        public final ModConfigSpec.BooleanValue lifeEssencePhialEvent;
        public final ModConfigSpec.BooleanValue lifeEssencePhial;
        public final ModConfigSpec.BooleanValue pixiePhial;

        public Phials(ModConfigSpec.Builder builder) {
            builder.comment("Catalyst Phials").push("phials");

            this.phialEvents = builder
                    .comment("[Server/Client] - Enables all phial events. If this is set to false, the rest of this config can be ignored")
                    .gameRestart()
                    .define("arePhialEventsEnabled", true);

            this.dragonPhialEvent = phialEvent(builder, "dragon_phial");
            this.dragonPhial = phialToggle(builder, "dragon_phial");

            this.witheredPhialEvent = phialEvent(builder, "withered_phial");
            this.witheredPhial = phialToggle(builder, "withered_phial");

            this.sculkPhialEvent = phialEvent(builder, "sculk_phial");
            this.sculkPhial = phialToggle(builder, "sculk_phial");

            this.torchFlowerPhialEvent = phialEvent(builder, "torch_flower_phial");
            this.torchFlowerPhial = phialToggle(builder, "torch_flower_phial");

            this.myceliumPhialEvent = phialEvent(builder, "mycelium_phial");
            this.myceliumPhial = phialToggle(builder, "mycelium_phial");

            this.lifeEssencePhialEvent = phialEvent(builder, "life_essence_phial");
            this.lifeEssencePhial = phialToggle(builder, "life_essence_phial");

            this.pixiePhial = phialToggle(builder, "pixie_phial");

            builder.pop();
        }

        private ModConfigSpec.BooleanValue phialEvent(ModConfigSpec.Builder bldr, String phialName) {
            String phial = toDisplayName(phialName);
            String phialPath = toPathName(phialName);

            return bldr.comment("[Server/Client] Defines if " + phial + " Event is Enabled")
                    .worldRestart()
                    .define("is" + phialPath + "EventEnabled", true);
        }

        private ModConfigSpec.BooleanValue phialToggle(ModConfigSpec.Builder bldr, String phialName) {
            String phial = toDisplayName(phialName);
            String phialPath = toPathName(phialName);

            return bldr.comment("[Server] Defines if " + phial + " item is enabled")
                    .gameRestart()
                    .define("is" + phialPath + "Enabled", true);
        }
    }

    public static class CatalystCurios {
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

        public CatalystCurios(ModConfigSpec.Builder builder) {
            builder.comment("Catalyst Curio Effects").push("catalyst_curio");

            this.catalystCurioEffects = builder
                    .comment("Defines if All Catalyst Curio Effects are Enabled" +
                            " - " +
                            "If this option is disabled the rest of this config can be ignored" +
                            " - " +
                            "Requires Game Restart")
                    .translation("config.catalystcore.catalyst_curio.all_effects")
                    .gameRestart()
                    .define("toggleAllEffects", true);

            this.catalystNightVision = builder
                    .comment("Defines if Night Vision Effect is enabled" + " - " + "Requires Game Restart")
                    .translation("config.catalystcore.catalyst_curio.night_vision")
                    .gameRestart()
                    .define("toggleNightVisionEffect", true);

            this.catalystDamageBoost = builder
                    .comment("Defines if Damage Boost Effect is enabled" + " - " + "Requires Game Restart")
                    .translation("config.catalystcore.catalyst_curio.damage_boost")
                    .gameRestart()
                    .define("toggleDamageBoostEffect", true);

            this.catalystMovementSpeed = builder
                    .comment("Defines if Movement Speed Effect is Enabled" + " - " + "Requires Game Restart")
                    .translation("config.catalystcore.catalyst_curio.movement_speed")
                    .gameRestart()
                    .define("toggleMovementSpeedEffect", true);

            this.catalystSaturation = builder
                    .comment("Defines if Saturation Effect is Enabled" + " - " + "Requires Game Restart")
                    .translation("config.catalystcore.catalyst_curio.saturation")
                    .gameRestart()
                    .define("toggleSaturationEffect", true);

            this.catalystWaterBreathing = builder
                    .comment("Defines if Water Breathing Effect is Enabled" + " - " + "Requires Game Restart")
                    .translation("config.catalystcore.catalyst_curio.water_breathing")
                    .gameRestart()
                    .define("toggleWaterBreathingEffect", true);

            this.catalystLuck = builder
                    .comment("Defines if Luck Effect is Enabled" + " - " + "Requires Game Restart")
                    .translation("config.catalystcore.catalyst_curio.luck")
                    .gameRestart()
                    .define("toggleLuckEffect", true);

            this.catalystHealthBoost = builder
                    .comment("Defines if Health Boost Effect is Enabled" + " - " + "Requires Game Restart")
                    .translation("config.catalystcore.catalyst_curio.health_boost")
                    .gameRestart()
                    .define("toggleHealthBoostEffect", true);

            this.catalystFireResistance = builder
                    .comment("Defines if Fire Resistance Effect is Enabled" + " - " + "Requires Game Restart")
                    .translation("config.catalystcore.catalyst_curio.fire_resistance")
                    .gameRestart()
                    .define("toggleFireResistanceEffect", true);

            this.catalystRegeneration = builder
                    .comment("Defines if Regeneration Effect is Enabled" + " - " + "Requires Game Restart")
                    .translation("config.catalystcore.catalyst_curio.regeneration")
                    .gameRestart()
                    .define("toggleRegenerationEffect", true);

            builder.pop();
        }
    }

    public static class AnomalyConfig
    {
        public final ModConfigSpec.BooleanValue enableAnomalyShader;

        public AnomalyConfig(ModConfigSpec.Builder builder)
        {
            builder.comment("Gravity Anomaly Settings").push("anomaly");

            this.enableAnomalyShader = builder
                    .comment("Client Side - Enable the visual black hole shader effect for the Gravity Anomaly block")
                    .translation("config.catalystcore.anomaly.shader")
                    .define("enableAnomalyShader", true);

            builder.pop();
        }
    }

    public static class NebulaConfig
    {
        public final ModConfigSpec.BooleanValue enableNebulaShader;

        public NebulaConfig(ModConfigSpec.Builder builder)
        {
            builder.comment("Nebula Eterna Settings").push("nebula");

            this.enableNebulaShader = builder
                    .comment("Client Side - Enable the visual effect for the Nebula block")
                    .translation("config.catalystcore.nebula.shader")
                    .define("enableNebulaShader", true);

            builder.pop();
        }
    }

    public static class WarpConfig
    {
        public final ModConfigSpec.BooleanValue enableWarpShader;
        public final ModConfigSpec.ConfigValue<Integer> range;

        public final ModConfigSpec.BooleanValue enableKillEffect;
        public final ModConfigSpec.DoubleValue killDistance;
        public final ModConfigSpec.BooleanValue enableLightningEffect;
        public final ModConfigSpec.DoubleValue lightningDistance;
        public final ModConfigSpec.DoubleValue lightningDamage;

        public WarpConfig(ModConfigSpec.Builder builder)
        {
            builder.comment("Warp Portal Settings").push("warp");

            this.enableWarpShader = builder
                    .comment("Client Side - Enable the visual effect for the Warp block")
                    .translation("config.catalystcore.warp.shader")
                    .define("enableWarpShader", true);

            this.range = builder
                    .comment("Client Side - Range of the shader")
                    .translation("config.catalystcore.warp.shader")
                    .define("range", 40);

            this.enableKillEffect = builder
                    .comment("Server Side - Enable killing players when near the Warp Portal")
                    .define("enableKillEffect", true);

            this.killDistance = builder
                    .comment("Server Side - Distance in blocks to kill players")
                    .defineInRange("killDistance", 5.0d, 0.0d, 15.0d);

            this.enableLightningEffect = builder
                    .comment("Server Side - Enable spawning 3 lightnings per tick on nearby players")
                    .define("enableLightningEffect", true);

            this.lightningDistance = builder
                    .comment("Server Side - Distance in blocks to spawn lightnings on players")
                    .defineInRange("lightningDistance", 15.0d, 0.0d, 30.0d);

            this.lightningDamage = builder
                .comment("Server Side - Damage dealt by the lightning wave")
                .defineInRange("lightningDamage", 10.0d, 0.0d, 1000.0d);

            builder.pop();
        }
    }

    private static String toDisplayName(String snakeCase) {
        String[] parts = snakeCase.split("_");
        StringBuilder sb = new StringBuilder();
        for (String part : parts) {
            if (part.isEmpty()) continue;
            if (!sb.isEmpty()) sb.append(' ');
            sb.append(Character.toUpperCase(part.charAt(0))).append(part.substring(1));
        }
        return sb.toString();
    }

    private static String toPathName(String snakeCase) {
        return toDisplayName(snakeCase).replace(" ", "");
    }
}
