package net.radzratz.catalystcore.common.compat.ponder.scenes.phials;


import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.radzratz.catalystcore.client.items.CTCEItems;

public class MyceliumSporeEvent
{
    public static void myceliumSpore(SceneBuilder scene, SceneBuildingUtil util)
    {
        scene.title("mycelium_spores", "Extracting Mycelium Spores");

        scene.configureBasePlate(0, 0, 5);
        scene.setSceneOffsetY(-0.5f);

        scene.world().showSection(util.select().fromTo(0, 0, 0, 12, 4, 12), Direction.DOWN);
        scene.idle(40); // arranque más dinámico

        // Escena 1
        scene.overlay()
                .showText(80) // ~4 segundos de texto
                .text("This might be a bit tricky, as the first step is finding a single Mycelium block.")
                .placeNearTarget()
                .pointAt(util.vector().of(0, 2, 0));

        scene.idle(80); // completa 8s
        scene.addKeyframe();

        // Escena 2
        scene.overlay()
                .showText(60)
                .text("Once we find or create a single block of these, we may now proceed.")
                .placeNearTarget()
                .pointAt(util.vector().of(2, 2, 0));

        scene.idle(80);
        scene.addKeyframe();

        // Escena 3
        scene.overlay()
                .showText(100)
                .text("You will hold a Brush in your right hand and a Reinforced Phial in your left hand.\nRight-click the Mycelium to extract some of its spores.")
                .placeNearTarget()
                .pointAt(util.vector().of(0, 3, 0));

        scene.overlay().showControls(
                util.vector().of(3, 1.2, 3),
                Pointing.RIGHT, 80
        ).rightClick().withItem(new ItemStack(Items.BRUSH));

        scene.overlay().showControls(
                util.vector().of(2, 1.2, 3),
                Pointing.LEFT, 80
        ).rightClick().withItem(new ItemStack(CTCEItems.REINFORCED_BOTTLE.get()));

        scene.idle(80);
        scene.addKeyframe();

        // Escena 4: Drop
        scene.world().createItemEntity(
                util.vector().of(2, 2, 1.5),
                util.vector().of(0, 0.01, 0),
                new ItemStack(CTCEItems.MYCELIUM_BOTTLE.get())
        );

        scene.idle(60);
        scene.addKeyframe();

        // Escena 5: Final
        scene.overlay()
                .showText(80)
                .text("These spores are quite useful for multiple things.\nSo gather as much as you can.")
                .placeNearTarget()
                .pointAt(util.vector().of(2, 1, 2));

        scene.idle(80);
        scene.markAsFinished();
    }
}
