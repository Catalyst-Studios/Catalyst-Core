package net.radzratz.catalystcore.common.datagen.langs.en;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.radzratz.catalystcore.CatalystCore;

import static net.radzratz.catalystcore.common.registries.CTCEItemRegistry.*;
import static net.radzratz.catalystcore.common.registries.CTCEBlockRegistry.*;
import static net.radzratz.catalystcore.common.util.CTCEUtils.allToolEntries;

public class CTCELocalizationEN_US extends LanguageProvider {
    public CTCELocalizationEN_US(PackOutput output) {
        this(output, "en_us");
    }

    protected CTCELocalizationEN_US(PackOutput output, String locale) {
        super(output, CatalystCore.id, locale);
    }

    @Override
    protected void addTranslations() {
        addItemTranslation();
        addWeaponTranslation();
        addDescTranslation();
        addBlockTranslation();
        addTabTranslation();
        addMiscTranslation();
        addPonderTranslation();
    }

    private void addItemTranslation() {
        add(CODEX.get(), "Universe Codex");

        add(MALICIOUS_EYE.get(), "Malicious Eye");
        add(FULL_CATALYST.get(), "Catalyst");
        add(ARES.get(), "A.R.E.S");
        add(ETERNAL_VORTEX.get(), "Eternal Vortex");
        add(MAGIC_ANOMALY.get(), "Magic Anomaly");
        add(RIFT.get(), "Crystallized Rift");
        add(CONTAINER.get(), "Quantum Universe Container");
        add(TREASURE.get(), "Explorer's Treasure");
        add(BLACK_HOLE.get(), "Black Hole");
        add(CHOCOLATE_BAR.get(), "Shaped Shifted Quantum Chocolate Bar");
        add(COSMIC_SHATTERER.get(), "Cosmic Shatterer");
        add(FORBIDDEN_ORB.get(), "Forbidden Artifact");
        add(CHAOS_CRYSTAL.get(), "Fractured Chaotic Crystal");
        add(SPIRIT_AGGLOMERATIO.get(), "Spirit Agglomeratio");
        add(COSMIC_ABOMINATION.get(), "Cosmic Abomination");
        add(ICAROS.get(), "ICar-Os Device");
        add(ANCIENT_RELIC.get(), "Divine Relic");
        add(UNSTABLE_QUANTUM_STAR.get(), "Unstable Quantum Star");

        add(FRAME_CATALYST.get(), "Empty Catalyst");
        add(SELF_AWARE_CHIP.get(), "Self-Aware Nano Chip");
        add(COMET_SHARD.get(), "Comet Shard");
        add(CONTAINER_INACTIVE.get(), "Empty Containment Cell");
        add(WATER_ORB.get(), "Oceans Orb");
        add(END_CORE.get(), "End Nucleus");
        add(NETHER_CORE.get(), "Nether Nucleus");
        add(OVERWORLD_CORE.get(), "Overworld Nucleus");
        add(LAVA_ORB.get(), "Magmatic Orb");
        add(WARDEN_CORE.get(), "Warden Core");
        add(ELDER_CORE.get(), "Elder Guardian Core");
        add(WITHER_CORE.get(), "Withered Core");
        add(DRAGON_CORE.get(), "Dragon Core");
        add(EMPTY_CRYSTAL.get(), "Empty Crystal");
        add(FIRE_CRYSTAL.get(), "Fire Crystal");
        add(WATER_CRYSTAL.get(), "Water Crystal");
        add(EARTH_CRYSTAL.get(), "Earth Crystal");
        add(WIND_CRYSTAL.get(), "Wind Crystal");
        add(LIGHT_CRYSTAL.get(), "Light Crystal");
        add(DARKNESS_CRYSTAL.get(), "Darkness Crystal");
        add(ANTIMATTER.get(), "Antimatter Cell");
        add(CONCENTRATED_RADIANCE.get(), "Concentrated Radiance");
        add(BOUND_VOIDSPAWN.get(), "Bound Voidspawn");
        add(EMPTY_CONTAINMENT_VESSEL.get(), "Empty Containment Vessel");

        add(RUNIC_EMPTY.get(), "Runic Blank Slate");
        add(RUNIC_ARCANE.get(), "Runic Arcane Slate");
        add(RUNIC_BLOOD.get(), "Runic Blood Slate");
        add(INFECTED_SLATE.get(), "Infected Slate");
        add(RUNIC_ENDER.get(), "Runic Ender Slate");
        add(RUNIC_ESOTHERICAL.get(), "Esotherical Runic Slate");

        add(REINFORCED_BOTTLE.get(), "Reinforced Empty Phial");
        add(TORCHFLOWER_BOTTLE.get(), "Reinforced Phial of Torch Flower Essence");
        add(DRAGON_BLOOD.get(), "Reinforced Phial of Dragon's Blood");
        add(WITHERED_BOTTLE.get(), "Reinforced Phial of Withered Essence");
        add(SCULK_BOTTLE.get(), "Reinforced Phial of Sculk Biomass");
        add(MYCELIUM_BOTTLE.get(), "Reinforced Phial of Mycelium Spores");
        add(LIFE_ESSENCE_BOTTLE.get(), "Reinforced Phial of Life Essence");
        add(PIXIE_BOTTLE.get(), "Reinforced Phial of Pixie Essence");

        add(PENTAGRAM.get(), "Pentagram");
        add(BURRITO.get(), "Cataclystic Burrito™");
    }

    private void addWeaponTranslation() {
        allToolEntries().forEach(item -> {
            String path = item.getId().getPath();

            if (path.contains("_zweihander")) {
                add(item.get(), toDisplayName(path).substring(0, path.length() - 11) + " Zweihänder");
            } else {
                add(item.get(), toDisplayName(path));
            }
        });
    }

    private void addDescTranslation() {
        add("item.catalystcore.cataclystic_greatsword.desc", "The Universe Powers has Awoken within This Sword...");
        add("item.catalystcore.cataclystic_gladius.desc", "The Universe Powers Grants Great Balance");
        add("item.catalystcore.cataclystic_broadsword.desc", "The Universe Powers Concedes a Glorious Balance");
        add("item.catalystcore.cataclystic_katar.desc", "The Universe Powers Grant GodSpeed at Lower Damage");
        add("item.catalystcore.cataclystic_katar.desc2", "Dual Wielded Weapon");
        add("item.catalystcore.cataclystic_big_bonk.desc", "The Universe Powers Demand You to Bonk");
        add("item.catalystcore.cataclystic_scythe.desc", "The Universe Powers Whispers You to Reap and Sow");
        add("item.catalystcore.cataclystic_shield.desc", "The Universe Powers Grants You Perfect Protection");
        add("item.catalystcore.cataclystic_halberd.desc", "The Universe Powers Grants Distance with Glorious Damage");
        add("item.catalystcore.cataclystic_zweihander.desc", "The Universe Powers Grants Great Distance over Damage");
        add("item.catalystcore.cataclystic_ulfberht.desc", "The Universe Powers Grants Great Balance with Glorious Damage");
        add("item.catalystcore.cataclystic_battleaxe.desc", "The Universe Powers Grants Great Damage at Lower Speed");
        add("item.catalystcore.cataclystic_rapier.desc", "The Universe Powers Grants Speed at Lower Damage");

        add("item.catalystcore.cataclystic_pickaxe.desc", "The Universe is Craving Its Creations...");
        add("item.catalystcore.cataclystic_axe.desc", "The Universe Powers Says... WATCH OUT FOR THAT TREE!");
        add("item.catalystcore.cataclystic_shovel.desc", "The Universe Powers Says That It Can Create Paths");
        add("item.catalystcore.cataclystic_paxel.desc", "The Universe's Finest Creation...");

        add("item.catalystcore.universe_sword.desc", "Unleash It's Powers...");
    }

    private void addBlockTranslation() {
        add(CATALYST_ALTAR_PEDESTAL.get(), "Pedestal");
        add(CAULDRON.get(), "Reinforced Cauldron");
        add(REINFORCED_GLASS.get(), "Reinforced Glass");
        add(GRAVITY_ANOMALY.get(), "Gravity Anomaly");
        add(NEBULA_ETERNA.get(), "Nebula Eterna");
        add(WARP_PORTAL.get(), "Deep Warp Portal");
    }

    private void addTabTranslation() {
        add("tab.catalystcore.materials", "Catalyst Core Items");
        add("tab.catalystcore.weaponry_armoury", "Catalyst Core Weapons");
        add("tab.catalystcore.tools", "Catalyst Core Tools");
        add("tab.catalystcore.components", "Catalyst Core Components");
        add("tab.catalystcore.foods", "Catalyst Core Foods");
        add("tab.catalystcore.blocks", "Catalyst Core Blocks");
    }

    private void addMiscTranslation() {
        add("curios.slot_type.catalyst", "Catalyst");
        add("curios.identifier.catalyst", "Catalyst");
        add("entity.catalystcore.pentagram", "Pentagram");
    }

    private void addPonderTranslation() {
        add("catalystcore.ponder.torch_flower_essence.header", "Extracting the Torch Flower Essence with a Phial.");
        add("catalystcore.ponder.torch_flower_essence.text_1", "First, we'll need to grow a Torchflower to extract it's essence");
        add("catalystcore.ponder.torch_flower_essence.text_2", "Next, we're going to need some Shears and a Reinforced Phial\n The Phial goes on the Left Hand\n And the Shears on the Right Hand");
        add("catalystcore.ponder.torch_flower_essence.text_3", "The plant will lose its properties and decay in the process...");
        add("catalystcore.ponder.torch_flower_essence.text_4", "So, a method to duplicate these flowers is suggested.");

        add("catalystcore.ponder.sculk_biomass.header", "Extracting the Biomass from the Sculk.");
        add("catalystcore.ponder.sculk_biomass.text_1", "To extract this biomass you will need to venture into the Deep Dark.");
        add("catalystcore.ponder.sculk_biomass.text_2", "Hold a Golden Shovel on you Right Hand and a Reinforced Phial on your Left Hand.\n\nGolden Equipment has some unique traits to manipulate sculk with ease.");
        add("catalystcore.ponder.sculk_biomass.text_3", "We can extract the Biomass from any Sculk block, except for the veins as they're extremely delicate.");
        add("catalystcore.ponder.sculk_biomass.text_4", "But be careful to not attract unwanted attention from Shriekers.");
        add("catalystcore.ponder.sculk_biomass.text_5", "And after all this, we now have a Reinforced Phial filled with Sculk Biomass.");

        add("catalystcore.ponder.wither_essence.header", "Extracting the Withered Essence.");
        add("catalystcore.ponder.wither_essence.text_1", "Before we proceed, Catalyst Core adds a new type of glass, capable of withstanding Wither explosions.\n\nThis being the Reinforced Glass");
        add("catalystcore.ponder.wither_essence.text_2", "Another method we recommend, if its available, its the Stasis Chamber from Industrial Foregoing.");
        add("catalystcore.ponder.wither_essence.text_3", "First, to extract this essence we'll need to capture a Wither.");
        add("catalystcore.ponder.wither_essence.text_4", "You'll need a special Brush in your main hand...");
        add("catalystcore.ponder.wither_essence.text_5", "...and a Reinforced Phial in your offhand.");
        add("catalystcore.ponder.wither_essence.text_6", "Cautiously, Right Click the Wither to extract this essence.");
        add("catalystcore.ponder.wither_essence.text_7", "The final result will be Withered Essence.");

        add("catalystcore.ponder.dragon_blood.header", "Extracting the Dragon's Blood.");
        add("catalystcore.ponder.dragon_blood.text_1", "To get Dragon's Blood we must venture into The End to get this Item. So be ready!");
        add("catalystcore.ponder.dragon_blood.text_2", "To extract the drake's blood, hold the God's Bain Dagger on your Right Hand and the Reinforced Phial on your Left Hand");
        add("catalystcore.ponder.dragon_blood.text_3", "Once we strike the beast with Right Click, we'll obtain the Dragon's Blood.");
        add("catalystcore.ponder.dragon_blood.text_4", "This blood is extremely rare and powerful. Use it wisely.");

        add("catalystcore.ponder.mycelium_spores.header", "Extracting Mycelium Spores.");
        add("catalystcore.ponder.mycelium_spores.text_1", "This might be a bit tricky, as the first step is Finding a single Mycelium Block.");
        add("catalystcore.ponder.mycelium_spores.text_2", "Once we find or create a single block of these, we may now proceed.");
        add("catalystcore.ponder.mycelium_spores.text_3", "You will hold a Brush on your Right Hand and a Reinforced Phial on you Left Hand. Right Click the block of Mycelium to extract some of it's Spores");
        add("catalystcore.ponder.mycelium_spores.text_4", "These spores are quite useful for multiple things. So gather as much as you can.");

        add("catalystcore.ponder.tag.phials", "Reinforced Phials");
        add("catalystcore.ponder.tag.phials.description", "Reinforced Phials capable of extracting and storing arcane or biological essence");
        add("catalystcore.ponder.tag.slates", "Runic Slates");
        add("catalystcore.ponder.tag.slates.description", "Runic slates used for rituals or crafting");
        add("catalystcore.ponder.tag.books", "Universe Codex");
        add("catalystcore.ponder.tag.books.description", "In-Game Guide and Lore Book for CatalystCore");
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
}