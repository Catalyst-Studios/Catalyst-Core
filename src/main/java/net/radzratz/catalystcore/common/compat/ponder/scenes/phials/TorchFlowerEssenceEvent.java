package net.radzratz.catalystcore.common.compat.ponder.scenes.phials;

import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;

import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import static net.radzratz.catalystcore.client.items.CTCEItems.REINFORCED_BOTTLE;
import static net.radzratz.catalystcore.client.items.CTCEItems.TORCHFLOWER_BOTTLE;

public class TorchFlowerEssenceEvent
{
    public static void torchFlowerEssence(SceneBuilder scene, SceneBuildingUtil util)
    {
        scene.title("torch_flower_essence", "Extracting the Torch Flower Essence with a Phial.");

        scene.world().showSection(util.select().position(1, 1, 1), Direction.DOWN);
        scene.idle(1);
        scene.world().showSection(util.select().position(1, 0, 1), Direction.DOWN);

        scene.overlay()
                .showText(50)
                .text("First, we'll need to grow a Torchflower to extract its essence.")
                .placeNearTarget()
                .pointAt(util.vector().of(1, 2, 1));

        scene.idle(70);
        scene.addKeyframe();

        // TEXTO: Indicaciones de ítems
        scene.overlay()
                .showText(60)
                .text("Next, we're going to need some Shears and a Reinforced Phial.\n\nThe Phial goes on the Left Hand.\n\nAnd the Shears on the Right Hand.")
                .placeNearTarget()
                .pointAt(util.vector().of(1, 1.5, 1));

        scene.overlay().showControls(
                        util.vector().of(1, 1.9, 2),
                        Pointing.LEFT, 60)
                .rightClick()
                .withItem(new ItemStack(Items.SHEARS));

        scene.overlay().showControls(
                        util.vector().of(2, 2.1, 1),
                        Pointing.RIGHT, 60)
                .rightClick()
                .withItem(new ItemStack(REINFORCED_BOTTLE.get()));

        scene.idle(90);
        scene.addKeyframe();

        scene.overlay()
                .showText(40)
                .text("The plant will lose its properties and decay in the process...")
                .placeNearTarget()
                .pointAt(util.vector().of(1, 2, 1));

        scene.world().replaceBlocks(
                util.select().position(1, 1, 1),
                Blocks.DEAD_BUSH.defaultBlockState(),
                true
        );

        scene.idle(20);

        scene.world().hideSection(util.select().position(1, 1, 1), Direction.UP);

        scene.idle(50);
        scene.addKeyframe();

        scene.overlay()
                .showText(40)
                .text("So, a method to duplicate these flowers is suggested.")
                .placeNearTarget()
                .pointAt(util.vector().of(1, 2, 1));

        scene.world().replaceBlocks(
                util.select().position(1, 1, 1),
                Blocks.TORCHFLOWER.defaultBlockState(),
                false
        );

        scene.world().showSection(util.select().position(1, 1, 1), Direction.DOWN);

        scene.idle(60);
        scene.addKeyframe();

        scene.world().createItemEntity(
                util.vector().of(1, 2, 1),
                util.vector().of(0, 0.01, 0),
                new ItemStack(TORCHFLOWER_BOTTLE.get())
        );
    }
}
