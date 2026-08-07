package net.radzratz.catalystcore.common.datagen.models;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.radzratz.catalystcore.CatalystCore;
import net.radzratz.catalystcore.client.items.tools.tools.*;
import net.radzratz.catalystcore.client.items.tools.weapons.*;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static net.radzratz.catalystcore.common.util.CTCEUtils.*;

public class CTCEModelProvider extends ItemModelProvider {
    public CTCEModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CatalystCore.id, existingFileHelper);
    }

    @SuppressWarnings("unused")
    @Override
    protected void registerModels() {
        allItemEntries().filter(entry -> !(entry.get() instanceof BlockItem))
                .forEach(entry -> basicItem(Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(entry.get()))));

        allToolEntries().forEach(entry -> {
            ResourceLocation id = Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(entry.get()));
            Item item = entry.get();

            if (item instanceof CTCEShieldItem) return;

            switch (entry.get()) {
                case CTCEGreatswordItem greatsword -> greatswordModel(id);
                case CTCELongswordItem longsword -> longswordModel(id);
                case CTCEZweihanderItem zweihander -> zweihanderModel(id);
                case CTCEUlfberhtItem ulfberht -> ulfberhtModel(id);
                case CTCEScytheItem scythe -> scytheModel(id);
                case CTCERapierItem rapier -> rapierModel(id);
                case CTCEKatarItem katar -> katarModel(id);
                case CTCEBigBonkItem bigBonk -> hammerModel(id);
                case CTCESpearItem spear -> longWeapons(id);
                case CTCEHalberdItem halberd -> halberdModel(id);
                case CTCEGladiusItem gladius -> gladiusModel(id);
                case CTCEBattleAxeItem battleaxe -> battleaxeModel(id);
                case CTCEUniverseSwordItem universeSword -> bigWeaponModel(id);

                case CTCEBroadswordItem broadsword -> handheldItem(id);
                case CTCEAxeItem axe -> handheldItem(id);
                case CTCEPickaxeItem pickaxe -> handheldItem(id);
                case CTCEPaxelItem paxel -> handheldItem(id);
                case CTCEShovelItem shovel -> handheldItem(id);
                case CTCEHoeItem hoe -> handheldItem(id);
                default -> {}
            }
        });
    }

    public @NotNull ItemModelBuilder basicItem(ResourceLocation item) {
        String itemName = item.getPath();
        String basePath = "item/";

        String[] possiblePaths = {
                basePath + itemName,
                basePath + "phials/" + itemName,
                basePath + "slates/" + itemName,
                basePath + "end_game/" + itemName,
                basePath + "cores/" + itemName,
                basePath + "orbs/" + itemName,
                basePath + "crystals/" + itemName,
                basePath + "foods/" + itemName,
                basePath + "components/" + itemName
        };

        ItemModelBuilder builder = null;

        for (String path : possiblePaths) {
            if (existingFileHelper.exists(ResourceLocation.fromNamespaceAndPath(item.getNamespace(), path), TEXTURE)) {
                builder = this.getBuilder(item.toString())
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", ResourceLocation.fromNamespaceAndPath(item.getNamespace(), path));
                break;
            }
        }

        if (builder == null) {
            builder = this.getBuilder(item.toString())
                    .parent(new ModelFile.UncheckedModelFile("item/generated"))
                    .texture("layer0", ResourceLocation.fromNamespaceAndPath(item.getNamespace(), basePath + itemName));
        }

        return builder;
    }

    public @NotNull ItemModelBuilder handheldItem(ResourceLocation item) {
        String itemName = item.getPath();
        String basePath = "item/";

        String[] possiblePaths = {
                basePath + itemName,
                basePath + "weapons/" + itemName,
                basePath + "tools/" + itemName,
        };

        ItemModelBuilder builder = null;

        for (String path : possiblePaths) {
            if (existingFileHelper.exists(ResourceLocation.fromNamespaceAndPath(item.getNamespace(), path), TEXTURE)) {
                builder = this.getBuilder(item.toString())
                        .parent(new ModelFile.UncheckedModelFile("item/handheld"))
                        .texture("layer0", ResourceLocation.fromNamespaceAndPath(item.getNamespace(), path));
                break;
            }
        }

        if (builder == null) {
            builder = this.getBuilder(item.toString())
                    .parent(new ModelFile.UncheckedModelFile("item/handheld"))
                    .texture("layer0", ResourceLocation.fromNamespaceAndPath(item.getNamespace(), basePath + itemName));
        }

        return builder;
    }

    private void applyTransform(ItemModelBuilder builder, ItemDisplayContext context, float rx, float ry, float rz, float tx, float ty, float tz, float sx, float sy, float sz) {
        builder.transforms()
                .transform(context)
                .rotation(rx, ry, rz)
                .translation(tx, ty, tz)
                .scale(sx, sy, sz)
                .end();
    }

    private void handheldWeapon(ResourceLocation item, String texturePath, float[] tpRight, float[] tpLeft, float[] fpRight, float[] fpLeft, float[] ground, float[] fixed) {
        ItemModelBuilder builder = this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/handheld"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(item.getNamespace(), texturePath));

        applyTransform(builder, ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, tpRight[0], tpRight[1], tpRight[2], tpRight[3], tpRight[4], tpRight[5], tpRight[6], tpRight[7], tpRight[8]);
        applyTransform(builder, ItemDisplayContext.THIRD_PERSON_LEFT_HAND, tpLeft[0], tpLeft[1], tpLeft[2], tpLeft[3], tpLeft[4], tpLeft[5], tpLeft[6], tpLeft[7], tpLeft[8]);
        applyTransform(builder, ItemDisplayContext.FIRST_PERSON_RIGHT_HAND, fpRight[0], fpRight[1], fpRight[2], fpRight[3], fpRight[4], fpRight[5], fpRight[6], fpRight[7], fpRight[8]);
        applyTransform(builder, ItemDisplayContext.FIRST_PERSON_LEFT_HAND, fpLeft[0], fpLeft[1], fpLeft[2], fpLeft[3], fpLeft[4], fpLeft[5], fpLeft[6], fpLeft[7], fpLeft[8]);
        if (ground != null) applyTransform(builder, ItemDisplayContext.GROUND, ground[0], ground[1], ground[2], ground[3], ground[4], ground[5], ground[6], ground[7], ground[8]);
        if (fixed != null) applyTransform(builder, ItemDisplayContext.FIXED, fixed[0], fixed[1], fixed[2], fixed[3], fixed[4], fixed[5], fixed[6], fixed[7], fixed[8]);
    }

    public void greatswordModel(ResourceLocation item) {
        handheldWeapon(item, "item/weapons/" + item.getPath(),
                new float[]{12, -90, 55, 0, 12.5f, 2.5f, 2f, 2f, 1.2f},
                new float[]{12, 90, -55, 0, 12.5f, 2.5f, 2f, 2f, 1.2f},
                new float[]{0, 90, 55, 0.75f, 8f, 1f, 1.4f, 1.4f, 1.15f},
                new float[]{0, -90, -55, 0.75f, 8f, 1f, 1.4f, 1.4f, 1.15f},
                new float[]{0, 0, 0, 0, 9.25f, 0, 2f, 2f, 1.2f},
                new float[]{0, -180, 0, 0, 0, 0, 0.95f, 0.95f, 0.95f});
    }

    public void longswordModel(ResourceLocation item) {
        handheldWeapon(item, "item/weapons/" + item.getPath(),
                new float[]{15, -90, 55, 0, 10.75f, 3f, 1.7f, 1.7f, 1.2f},
                new float[]{15, 90, -55, 0, 10.75f, 3f, 1.7f, 1.7f, 1.2f},
                new float[]{0, 90, 45, 0.75f, 8f, 1f, 1.5f, 1.5f, 1.2f},
                new float[]{0, -88, -45, 0.75f, 8f, 1f, 1.5f, 1.5f, 1.2f},
                new float[]{0, 0, 0, 0, 8.75f, 0, 1.7f, 1.7f, 1.2f},
                new float[]{0, -180, 0, 0, 0, 0, 0.95f, 0.95f, 0.95f});
    }

    public void bigWeaponModel(ResourceLocation item) {
        handheldWeapon(item, "item/weapons/" + item.getPath(),
                new float[]{0, 90, 60, 0.00f, 14.00f, 5.50f, 2.10f, 2.10f, 1.00f},
                new float[]{0, 90, 330, 0.00f, 14.00f, 5.50f, 2.10f, 2.10f, 1.00f},
                new float[]{0, 90, 50, 0.00f, 11.75f, 1.50f, 1.90f, 1.90f, 0.90f},
                new float[]{0, 280, 320, 0.00f, 11.75f, 1.00f, 1.90f, 1.90f, 0.90f},
                new float[]{0, 0, 0, 0, 3, 0, 0.6f, 0.6f, 0.6f},
                new float[]{0, 180, 0, 0, 0, 0, 0.95f, 0.95f, 0.95f});
    }

    public void ulfberhtModel(ResourceLocation item) {
        handheldWeapon(item, "item/weapons/" + item.getPath(),
                new float[]{6, 90, 30, 0.00f, 5.25f, 1.00f, 1.30f, 1.30f, 0.80f},
                new float[]{342, 270, 300, 0.00f, 5.00f, 1.25f, 1.30f, 1.30f, 0.80f},
                new float[]{20, 90, 30, 0.00f, 5.50f, 1.00f, 1.10f, 1.10f, 0.80f},
                new float[]{20, 280, 330, 0.00f, 5.50f, 1.00f, 1.10f, 1.10f, 0.80f},
                new float[]{0, 0, 0, 0, 3, 0, 0.6f, 0.6f, 0.6f},
                new float[]{0, 180, 0, 0, 0, 0, 0.95f, 0.95f, 0.95f});
    }

    public void scytheModel(ResourceLocation item) {
        handheldWeapon(item, "item/weapons/" + item.getPath(),
                new float[]{0, -90, 25, 0, 7.75f, 4.75f, 2.5f, 2.5f, 1f},
                new float[]{0, 90, -25, 0, 7.75f, 4.75f, 2.5f, 2.5f, 1f},
                new float[]{-25, -90, 25, 0.75f, 8.25f, 1f, 1.5f, 1.5f, 1f},
                new float[]{-25, 90, -25, 0.75f, 8.25f, 1f, 1.5f, 1.5f, 1f},
                new float[]{0, 0, 0, 0, 15f, 0, 2.5f, 2.5f, 1f},
                new float[]{0, -180, 0, 0, 0, 0, 0.95f, 0.95f, 0.95f});
    }

    public void hammerModel(ResourceLocation item) {
        handheldWeapon(item, "item/weapons/" + item.getPath(),
                new float[]{0, 90, 60, 0, 12.75f, 5f, 3f, 3f, 1.5f},
                new float[]{0, -90, -60, 0, 12.75f, 5f, 3f, 3f, 1.5f},
                new float[]{0, 90, 50, 0, 13.5f, 1.5f, 3f, 3f, 1.5f},
                new float[]{0, -90, -50, 0, 13.5f, 1.5f, 3f, 3f, 1.5f},
                new float[]{0, 0, 0, 0, 17.25f, 0, 3f, 3f, 1.5f},
                new float[]{0, -180, 0, 0, 0, 0, 0.95f, 0.95f, 0.95f});
    }

    public void rapierModel(ResourceLocation item) {
        handheldWeapon(item, "item/weapons/" + item.getPath(),
                new float[]{0, 90, 46, 0.00f, 8.25f, 1.50f, 1.35f, 1.35f, 0.80f},
                new float[]{0, 274, 312, 0.00f, 8.25f, 1.50f, 1.35f, 1.35f, 0.80f},
                new float[]{0, 80, 60, 0.00f, 5.25f, 1.25f, 1.25f, 1.25f, 0.80f},
                new float[]{0, 260, 300, 0.00f, 5.25f, 1.25f, 1.25f, 1.25f, 0.80f},
                new float[]{0, 0, 0, 0, 3, 0, 0.6f, 0.6f, 0.6f},
                new float[]{0, 180, 0, 0, 0, 0, 0.95f, 0.95f, 0.95f});
    }

    public void katarModel(ResourceLocation item) {
        handheldWeapon(item, "item/weapons/" + item.getPath(),
                new float[]{-40, 90, 0, 0, -2, -2, 1f, 1f, 1f},
                new float[]{-40, -90, 0, 0, -2, -2, 1f, 1f, 1f},
                new float[]{-15, 90, 0, 0.5f, 3.5f, 2.5f, 0.8f, 0.8f, 0.8f},
                new float[]{-15, -90, 0, -0.5f, 3.5f, 2.5f, 0.8f, 0.8f, 0.8f},
                new float[]{0, 0, 0, 0, 5f, 0, 1f, 1f, 1f},
                new float[]{0, -180, 0, 0, 0, 0, 0.95f, 0.95f, 0.95f});
    }

    public void longWeapons(ResourceLocation item) {
        handheldWeapon(item, "item/weapons/" + item.getPath(),
                new float[]{0, 90, 45, 0, 12f, 2.25f, 3f, 3f, 1f},
                new float[]{0, -90, -45, 0, 12f, 2.25f, 3f, 3f, 1f},
                new float[]{0, 90, 15, 0, 9f, -3f, 3f, 3f, 1f},
                new float[]{0, -90, -15, 0, 9f, -3f, 3f, 3f, 1f},
                new float[]{0, 0, 0, 0, 20f, 0, 3f, 3f, 1f},
                new float[]{0, -180, 0, 0, 0, 0, 0.85f, 0.85f, 0.85f});
    }

    public void gladiusModel(ResourceLocation item) {
        handheldWeapon(item, "item/weapons/" + item.getPath(),
                new float[]{0, -90, 55, 0.00f, 5.00f, 0.50f, 1.20f, 1.20f, 0.90f},
                new float[]{0, 90, 317, 0.00f, 5.00f, 2.00f, 1.20f, 1.20f, 0.90f},
                new float[]{0, 270, 35, 0.75f, 4.50f, 1.00f, 1.20f, 1.20f, 0.80f},
                new float[]{0, 270, 311, 0.75f, 4.50f, 1.00f, 1.20f, 1.20f, 0.80f},
                new float[]{0, 0, 0, 0, 3, 0, 0.6f, 0.6f, 0.6f},
                new float[]{0, 180, 0, 0, 0, 0, 0.95f, 0.95f, 0.95f});
    }

    public void zweihanderModel(ResourceLocation item) {
        handheldWeapon(item, "item/weapons/" + item.getPath(),
                new float[]{0, 90, 60, 0, 20.25f, 8f, 3f, 3f, 1f},
                new float[]{0, -90, -60, 0, 20.25f, 8f, 3f, 3f, 1f},
                new float[]{0, 90, 35, -0.5f, 16f, 1.5f, 3f, 3f, 1f},
                new float[]{0, -90, -35, -0.5f, 16f, 1.5f, 3f, 3f, 1f},
                new float[]{0, 0, 0, 0, 18.5f, 0, 3f, 3f, 1f},
                new float[]{0, -180, 0, 0, 0, 0, 0.95f, 0.95f, 0.95f});
    }

    public void halberdModel(ResourceLocation item) {
        handheldWeapon(item, "item/weapons/" + item.getPath(),
                new float[]{0, -90, 45, 0, 12f, 2.25f, 3f, 3f, 1f},
                new float[]{0, 90, -45, 0, 12f, 2.25f, 3f, 3f, 1f},
                new float[]{0, -90, 55, 3f, 13f, -3f, 3f, 3f, 1f},
                new float[]{0, 90, -55, 3f, 13f, -3f, 3f, 3f, 1f},
                new float[]{0, 0, 0, 0, 20f, 0, 3f, 3f, 1f},
                new float[]{0, -180, 0, 0, 0, 0, 0.85f, 0.85f, 0.85f});
    }

    public void battleaxeModel(ResourceLocation item) {
        handheldWeapon(item, "item/weapons/" + item.getPath(),
                new float[]{15, -90, 55, 0, 8.25f, 2.75f, 2f, 2f, 1.5f},
                new float[]{15, 90, -55, 0, 8.25f, 2.75f, 2f, 2f, 1.5f},
                new float[]{0, -90, 55, 0.75f, 9f, 1f, 2f, 2f, 1.5f},
                new float[]{0, 90, -55, 0.75f, 9f, 1f, 2f, 2f, 1.5f},
                new float[]{0, 0, 0, 0, 12.25f, 0, 2f, 2f, 1.5f},
                new float[]{0, -180, 0, 0, 0, 0, 0.95f, 0.95f, 0.95f});
    }
}