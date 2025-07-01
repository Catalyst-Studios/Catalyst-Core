package net.radzratz.catalystcore.common.compat.ponder.scenes.phials;

import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.element.ElementLink;
import net.createmod.ponder.api.element.EntityElement;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.radzratz.catalystcore.client.items.CTCEItems;

public class WitherEssenceEvent
{
    public static void witherEssence(SceneBuilder scene, SceneBuildingUtil util)
    {
        scene.title("wither_essence", "Extracting the Withered Essence");
        scene.world().showSection(util.select().fromTo(0, 0, 0, 4, 4, 4), Direction.DOWN);
        scene.idle(10);

        scene.overlay()
                .showText(60)
                .text("Before we proceed, Catalyst Core adds a new type of glass, capable of withstanding Wither explosions.\n\nThis being the Reinforced Glass")
                .placeNearTarget()
                .pointAt(util.vector().of(2, 1, 2));
        scene.idle(80);

        scene.overlay()
                .showText(60)
                .text("Another method we recommend, if its available, its the Stasis Chamber from Industrial Foregoing.")
                .placeNearTarget()
                .pointAt(util.vector().of(2, 1, 2));
        scene.idle(80);

        scene.addKeyframe();

        ElementLink<EntityElement> wither = scene.world().createEntity(level ->
        {
            WitherBoss entity = new WitherBoss(EntityType.WITHER, level);
            entity.setPos(2.5, 1.0, 2.5);
            entity.setYRot(180.0f);
            entity.setXRot(0.0F);
            entity.setInvulnerable(true);
            return entity;
        });

        scene.idle(10);

        scene.overlay()
                .showText(80)
                .text("First, to extract this essence we'll need to capture a Wither.")
                .placeNearTarget()
                .pointAt(util.vector().of(2, 2, 2));
        scene.idle(90);
        scene.addKeyframe();

        scene.overlay()
                .showText(70)
                .text("You'll need a special Brush in your main hand...")
                .placeNearTarget()
                .pointAt(util.vector().of(2.5, 3.0, 2));

        scene.overlay().showControls(util.vector().of(2.5, 3.5, 2), Pointing.RIGHT, 60)
                .rightClick()
                .withItem(new ItemStack(Items.BRUSH));

        scene.idle(60);

        scene.overlay()
                .showText(70)
                .text("...and a Reinforced Phial in your offhand.")
                .placeNearTarget()
                .pointAt(util.vector().of(1.5, 3.0, 2));

        scene.overlay().showControls(util.vector().of(1.5, 3.5, 2), Pointing.LEFT, 60)
                .rightClick()
                .withItem(new ItemStack(CTCEItems.REINFORCED_BOTTLE.get()));

        scene.idle(80);
        scene.addKeyframe();

        scene.overlay()
                .showText(50)
                .text("Cautiously, Right Click the Wither to extract this essence.")
                .placeNearTarget()
                .pointAt(util.vector().of(2, 2, 2));

        scene.idle(20);

        scene.world().createItemEntity(
                util.vector().of(2, 3, 2),
                util.vector().of(0, 0.05, 0),
                new ItemStack(CTCEItems.WITHERED_BOTTLE.get())
        );

        scene.idle(60);
        scene.addKeyframe();

        scene.overlay()
                .showText(60)
                .text("The final result will be Withered Essence.")
                .placeNearTarget()
                .pointAt(util.vector().of(2, 2, 2));

        scene.idle(60);
        scene.markAsFinished();
    }
}
