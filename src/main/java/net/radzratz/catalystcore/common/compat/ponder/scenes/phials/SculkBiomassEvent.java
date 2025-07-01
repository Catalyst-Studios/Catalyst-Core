package net.radzratz.catalystcore.common.compat.ponder.scenes.phials;

import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import static net.radzratz.catalystcore.client.items.CTCEItems.*;

public class SculkBiomassEvent
{
    public static void sculkBiomass(SceneBuilder scene, SceneBuildingUtil util)
    {
        scene.title("sculk_biomass", "Extracting the Sculk Biomass");

        scene.world().showSection(util.select().fromTo(0, 0, 0, 2, 2, 2), Direction.DOWN);

        scene.idle(10);

        scene.overlay()
                .showText(60)
                .text("To extract this biomass you will need to venture into the Deep Dark.")
                .placeNearTarget()
                .pointAt(util.vector().of(1, 2, 1));

        scene.idle(70);
        scene.addKeyframe();

        scene.overlay()
                .showText(60)
                .text("Hold a Golden Shovel on your Right Hand and a Reinforced Phial on your Left Hand.\n Golden Equipment has some unique traits to manipulate sculk with ease.")
                .placeNearTarget()
                .pointAt(util.vector().of(1, 1.5, 1));

        scene.overlay().showControls(util.vector().of(1, 1.9, 2), Pointing.LEFT, 60)
                .rightClick()
                .withItem(new ItemStack(Items.GOLDEN_SHOVEL));

        scene.overlay().showControls(util.vector().of(2, 2.1, 1), Pointing.RIGHT, 60)
                .rightClick()
                .withItem(new ItemStack(REINFORCED_BOTTLE.get()));

        scene.idle(80);
        scene.addKeyframe();

        scene.overlay()
                .showText(40)
                .text("We can extract the Biomass from any Sculk block, except for the veins.")
                .placeNearTarget()
                .pointAt(util.vector().of(1, 2, 1));

        scene.idle(30);
        scene.world().replaceBlocks(util.select().position(1, 1, 1), Blocks.SCULK_CATALYST.defaultBlockState(), true);
        scene.idle(30);
        scene.world().replaceBlocks(util.select().position(1, 1, 1), Blocks.SCULK_SENSOR.defaultBlockState(), true);
        scene.idle(30);
        scene.world().replaceBlocks(util.select().position(1, 1, 1), Blocks.SCULK_SHRIEKER.defaultBlockState(), true);

        scene.overlay()
                .showText(40)
                .text("But be careful to not attract unwanted attention from Shriekers.")
                .placeNearTarget()
                .pointAt(util.vector().of(1, 2, 1));

        scene.addKeyframe();

        scene.idle(60);
        scene.world().replaceBlocks(util.select().position(1, 1, 1), Blocks.SCULK.defaultBlockState(), true);

        scene.idle(40);
        scene.overlay()
                .showText(50)
                .text("And after all this, we now have a Reinforced Phial filled with Sculk Biomass")
                .placeNearTarget()
                .pointAt(util.vector().of(1, 2, 1));

        scene.world().createItemEntity(
                util.vector().of(1, 2, 1),
                util.vector().of(0, 0.01, 0),
                new ItemStack(SCULK_BOTTLE.get())
        );
        scene.world().showSection(util.select().position(1, 1, 1), Direction.UP);

        scene.idle(40);
        scene.addKeyframe();
        scene.markAsFinished();
    }
}
