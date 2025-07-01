package net.radzratz.catalystcore.common.compat.ponder.scenes.phials;

import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.element.ElementLink;
import net.createmod.ponder.api.element.EntityElement;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.radzratz.catalystcore.client.items.CTCEItems;

public class DragonBloodEvent
{
    public static void dragonBlood(SceneBuilder scene, SceneBuildingUtil util)
    {
        scene.title("dragon_blood", "Extracting the Dragon's Blood");

        scene.configureBasePlate(0, 0, 13);
        scene.setSceneOffsetY(-0.5f);
        scene.scaleSceneView(0.7f);
        scene.showBasePlate();

        scene.world().showSection(
                util.select().fromTo(0, 0, 0, 12, 4, 12),
                Direction.DOWN
        );

        scene.idle(10);

        ElementLink<EntityElement> dragon = scene.world().createEntity(level ->
        {
            EnderDragon entity = new EnderDragon(EntityType.ENDER_DRAGON, level);
            entity.setPos(6.5, 5, 6.5);
            entity.setInvulnerable(true);
            return entity;
        });

        scene.idle(10);

        scene.overlay()
                .showText(80)
                .text("To get Dragon's Blood we must venture into The End to get this Item. So be ready!")
                .placeNearTarget()
                .pointAt(util.vector().of(6.5, 5.5, 6.5));

        scene.idle(80);
        scene.addKeyframe();

        scene.overlay()
                .showText(60)
                .text("To extract the drake's blood, hold the God's Bain Dagger on your Right Hand and the Reinforced Phial on your Left Hand")
                .placeNearTarget()
                .pointAt(util.vector().of(7.5, 3, 7));

        scene.overlay().showControls(
                util.vector().of(7.8, 6.5, 6.5),
                Pointing.RIGHT, 60
        ).rightClick().withItem(new ItemStack(Items.DIAMOND_SWORD));

        scene.overlay().showControls(
                util.vector().of(5.2, 6.5, 6.5),
                Pointing.LEFT, 60
        ).rightClick().withItem(new ItemStack(CTCEItems.REINFORCED_BOTTLE.get()));

        scene.idle(80);
        scene.addKeyframe();

        scene.overlay()
                .showText(50)
                .text("Once we strike the beast, we'll obtain the Dragon's Blood.")
                .placeNearTarget()
                .pointAt(util.vector().of(6.5, 5.5, 6.5));

        scene.idle(20);

        scene.world().createItemEntity(
                util.vector().of(6.5, 6.5, 6.5),
                util.vector().of(0, 0.03, 0),
                new ItemStack(CTCEItems.DRAGON_BLOOD.get())
        );

        scene.idle(60);
        scene.addKeyframe();

        scene.overlay()
                .showText(60)
                .text("This blood is extremely rare and powerful. Use it wisely.")
                .placeNearTarget()
                .pointAt(util.vector().of(6.5, 5.5, 6.5));

        scene.idle(60);
        scene.markAsFinished();
    }
}
