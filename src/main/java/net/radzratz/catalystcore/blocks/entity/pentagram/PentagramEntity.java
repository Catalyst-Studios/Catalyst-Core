package net.radzratz.catalystcore.blocks.entity.pentagram;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.radzratz.catalystcore.recipes.CatalystRecipeTypes;
import net.radzratz.catalystcore.recipes.pentagram.PentagramJsonRecipe;
import net.radzratz.catalystcore.recipes.pentagram.PentagramContainer;
import net.radzratz.catalystcore.recipes.pentagram.debug.PentagramRecipeManager;
import net.radzratz.catalystcore.recipes.pentagram.debug.PentagramRecipes;
import net.radzratz.catalystcore.sound.CatalystSounds;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PentagramEntity extends Entity
{
    private static final EntityDataAccessor<Boolean> FADING_OUT =
            SynchedEntityData.defineId(PentagramEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> FADE_TICKS =
            SynchedEntityData.defineId(PentagramEntity.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Float> CIRCLE_ROTATION =
            SynchedEntityData.defineId(PentagramEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> STATIC_CIRCLE_SCALE =
            SynchedEntityData.defineId(PentagramEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> INNER_CIRCLE_SCALE =
            SynchedEntityData.defineId(PentagramEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> OUTER_CIRCLE_SCALE =
            SynchedEntityData.defineId(PentagramEntity.class, EntityDataSerializers.FLOAT);

    private static final EntityDataAccessor<Float> INNER_CIRCLE_ANGLE =
            SynchedEntityData.defineId(PentagramEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> OUTER_CIRCLE_ANGLE =
            SynchedEntityData.defineId(PentagramEntity.class, EntityDataSerializers.FLOAT);

    private int recipeCooldown = 0;
    public boolean isFadingOut = false;
    public int fadeOutTicks = 0;
    public static final int MAX_FADE_TICKS = 20;
    private boolean hasPlayedPlaceSound = false;

    public static final float MAX_INNER_SCALE = 1.0f;
    public static final float MAX_OUTER_SCALE = 1.2f;
    public static final float MAX_STATIC_SCALE = 0.6f;

    public PentagramEntity(EntityType<? extends PentagramEntity> type, Level level)
    {
        super(type, level);
        @SuppressWarnings("unused")
        float eyeHeight = 0.0F;
        this.setBoundingBox(AABB.ofSize(this.position(), 1.0D, 0.1D, 1.0D));

        if(!level.isClientSide)
        {
            level.playSound(
                    null,
                    this.blockPosition(),
                    CatalystSounds.PENTAGRAM_CRAFT.get(),
                    SoundSource.BLOCKS,
                    1.0f,
                    1.0f
            );
        }

        this.entityData.set(CIRCLE_ROTATION, 0.0f);
        this.entityData.set(STATIC_CIRCLE_SCALE, 0.0f);
        this.entityData.set(INNER_CIRCLE_SCALE, 0.0f);
        this.entityData.set(OUTER_CIRCLE_SCALE, 0.0f);

        this.entityData.set(INNER_CIRCLE_ANGLE, 0.0f);
        this.entityData.set(OUTER_CIRCLE_ANGLE, 0.0f);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder)
    {
        builder.define(FADING_OUT, false);
        builder.define(FADE_TICKS, 0);

        builder.define(CIRCLE_ROTATION, 0.0f);
        builder.define(STATIC_CIRCLE_SCALE, 0.0f);
        builder.define(INNER_CIRCLE_SCALE, 0.0f);
        builder.define(OUTER_CIRCLE_SCALE, 0.0f);

        builder.define(INNER_CIRCLE_ANGLE, 0.0f);
        builder.define(OUTER_CIRCLE_ANGLE, 0.0f);
    }

    @Override
    protected void readAdditionalSaveData(@NotNull CompoundTag tag)
    {
    }

    @Override
    protected void addAdditionalSaveData(@NotNull CompoundTag tag)
    {
    }

    @Override
    public boolean isPickable()
    {
        return true;
    }

    @Override
    public @NotNull InteractionResult interactAt(@NotNull Player player,
                                                 @NotNull Vec3 vec,
                                                 @NotNull InteractionHand hand)
    {
        if(!this.level().isClientSide && player.isShiftKeyDown() && hand == InteractionHand.MAIN_HAND)
        {
            if(!isFadingOut)
            {
                isFadingOut = true;
                fadeOutTicks = 0;

                this.entityData.set(FADING_OUT, true);
                this.entityData.set(FADE_TICKS, 0);

                this.level().playSound(
                        null,
                        this.blockPosition(),
                        CatalystSounds.PENTAGRAM_PLACE.get(),
                        SoundSource.BLOCKS,
                        1.0f,
                        1.0f
                );
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    public float getStaticCircleScale()
    {
        return this.entityData.get(STATIC_CIRCLE_SCALE);
    }

    public float getInnerCircleAngle()
    {
        return this.entityData.get(INNER_CIRCLE_ANGLE);
    }

    public float getOuterCircleAngle()
    {
        return this.entityData.get(OUTER_CIRCLE_ANGLE);
    }

    public float getCircleRotation()
    {
        return this.entityData.get(CIRCLE_ROTATION);
    }

    public float getInnerCircleScale()
    {
        return this.entityData.get(INNER_CIRCLE_SCALE);
    }

    public float getOuterCircleScale()
    {
        return this.entityData.get(OUTER_CIRCLE_SCALE);
    }

    public boolean isFadingOutClient()
    {
        return this.entityData.get(FADING_OUT);
    }

    public int getFadeOutTicksClient()
    {
        return this.entityData.get(FADE_TICKS);
    }

    public float getVisualScale(float partialTicks)
    {
        if(level().isClientSide && isFadingOutClient())
        {
            float t = (getFadeOutTicksClient() + partialTicks) / MAX_FADE_TICKS;
            return 1.0f - t;
        }

        if(this.tickCount < MAX_FADE_TICKS)
        {
            return (this.tickCount + partialTicks) / MAX_FADE_TICKS;
        }
        return 1.0f;
    }

    public float getAlpha(float partialTicks)
    {
        if(level().isClientSide && isFadingOutClient())
        {
            float t = (getFadeOutTicksClient() + partialTicks) / MAX_FADE_TICKS;
            return 1.0f - t;
        }

        if(this.tickCount < MAX_FADE_TICKS)
        {
            return (this.tickCount + partialTicks) / MAX_FADE_TICKS;
        }
        return 1.0f;
    }

    @SuppressWarnings("unused")
    public boolean isHasPlayedPlaceSound()
    {
        return hasPlayedPlaceSound;
    }

    @SuppressWarnings("unused")
    public void setHasPlayedPlaceSound(boolean hasPlayedPlaceSound)
    {
        this.hasPlayedPlaceSound = hasPlayedPlaceSound;
    }

    ///Keeping this in here, as this was used before the JSON and Legacy unification
    ///If JSON and Legacy unification blows up in any given moment
    ///This is what was used and should prevent any further issues
    @SuppressWarnings("unused")
    /*    @Override
    public void tick()
    {
        super.tick();

        if(this.level() instanceof ServerLevel serverLevel)
        {
            AABB centerBox = new AABB(
                    this.getX() - 0.25, this.getY(), this.getZ() - 0.25,
                    this.getX() + 0.25, this.getY() + 1.0, this.getZ() + 0.25
            );

            List<ItemEntity> nearbyItems = serverLevel.getEntitiesOfClass(ItemEntity.class, centerBox);

            PentagramRecipes recipe = PentagramRecipeManager.matchRecipe(serverLevel, nearbyItems);
            if(recipe != null)
            {
                for(ItemStack needed : recipe.getInputs())
                {
                    int remaining = needed.getCount();

                    for(ItemEntity itemEntity : nearbyItems)
                    {
                        ItemStack stack = itemEntity.getItem();
                        if(ItemStack.isSameItemSameComponents(stack, needed))
                        {
                            int consume = Math.min(stack.getCount(), remaining);
                            stack.shrink(consume);
                            remaining -= consume;
                            if(remaining <= 0) break;
                        }
                    }
                }
                ItemEntity result = new ItemEntity(serverLevel, this.getX(), this.getY() + 0.5, this.getZ(), recipe.getResult());
                serverLevel.addFreshEntity(result);
            }
        }
    }*/

    ///This the JSON and Legacy unification
    @Override
    public void tick()
    {
        super.tick();

        if(!this.level().isClientSide && !hasPlayedPlaceSound)
        {
            this.level().playSound(
                    null,
                    this.blockPosition(),
                    CatalystSounds.PENTAGRAM_PLACE.get(),
                    SoundSource.BLOCKS,
                    2f,
                    1.0f
            );
            hasPlayedPlaceSound = true;
        }

        if(recipeCooldown > 0)
        {
            recipeCooldown--;
            return;
        }

        if(isFadingOut)
        {
            fadeOutTicks++;
            this.entityData.set(FADE_TICKS, fadeOutTicks);
            if(fadeOutTicks >= MAX_FADE_TICKS)
            {
                this.discard();
            }
            return;
        }

        if(!this.level().isClientSide)
        {
            float staticScale = Math.min(MAX_STATIC_SCALE, getStaticCircleScale() + 0.005f);

            float innerScale = Math.min(MAX_INNER_SCALE, getInnerCircleScale() + 0.01f);
            float outerScale = Math.min(MAX_OUTER_SCALE, getOuterCircleScale() + 0.008f);

            float innerAngle = 45.0f * (float)Math.sin(this.tickCount * 0.02f);
            float outerAngle = -45.0f * (float)Math.sin(this.tickCount * 0.02f);

            float rotation = (getCircleRotation() + 0.5f) % 360;

            this.entityData.set(STATIC_CIRCLE_SCALE, staticScale);
            this.entityData.set(INNER_CIRCLE_SCALE, innerScale);
            this.entityData.set(OUTER_CIRCLE_SCALE, outerScale);
            this.entityData.set(INNER_CIRCLE_ANGLE, innerAngle);
            this.entityData.set(OUTER_CIRCLE_ANGLE, outerAngle);
            this.entityData.set(CIRCLE_ROTATION, rotation);
        }

        if(!(this.level() instanceof ServerLevel serverLevel)) return;

        AABB centerBox = new AABB(
                this.getX() - 0.25, this.getY(), this.getZ() - 0.25,
                this.getX() + 0.25, this.getY() + 1.0, this.getZ() + 0.25
        );

        List<ItemEntity> nearbyItems = new ArrayList<>(serverLevel.getEntitiesOfClass(ItemEntity.class, centerBox).stream()
                .filter(e -> e.isAlive() && !e.getItem().isEmpty())
                .toList());

        PentagramContainer container = new PentagramContainer(nearbyItems);
        Optional<RecipeHolder<PentagramJsonRecipe>> jsonRecipe = serverLevel.getRecipeManager()
                .getRecipeFor(CatalystRecipeTypes.PENTAGRAM_TYPE.get(), container, serverLevel);

        if(jsonRecipe.isPresent())
        {
            processJsonRecipe(serverLevel, nearbyItems, jsonRecipe.get().value());
            this.recipeCooldown = 20;
            return;
        }

        PentagramRecipes legacyRecipe = PentagramRecipeManager.matchRecipe(serverLevel, nearbyItems);
        if(legacyRecipe != null)
        {
            processLegacyRecipe(serverLevel, nearbyItems, legacyRecipe);
            this.recipeCooldown = 20;
        }
    }

    private void processJsonRecipe(ServerLevel level,
                                   List<ItemEntity> items,
                                   PentagramJsonRecipe recipe)
    {
        List<Ingredient> ingredients = new ArrayList<>(recipe.getIngredients());

        /*System.out.println("Found Recipe" + level + items + recipe);*/

        for(Ingredient ing : ingredients)
        {
            for(ItemEntity entity : items)
            {
                ItemStack stack = entity.getItem();

                if(!stack.isEmpty() && ing.test(stack))
                {
                    ItemStack one = stack.copy();
                    one.setCount(1);
                    /*System.out.println("Converting" + ingredients + items + entity);*/
                    break;
                }
            }
        }

        level.playSound(
                null,
                this.blockPosition(),
                CatalystSounds.PENTAGRAM_CRAFT.get(),
                SoundSource.BLOCKS,
                1.0f,
                1.0f
        );

        for(ItemEntity entity : items)
        {
            entity.discard();
            /*System.out.println("Discarded" + items + entity + "Found Result");*/
        }

        ItemStack result = recipe.getResultItem(level.registryAccess());
        if(!result.isEmpty())
        {
            level.addFreshEntity(new ItemEntity(level, this.getX(), this.getY() + 0.5, this.getZ(), result.copy()));
            /*System.out.println("Recipe" + recipe + result + level + "Done!");*/
        }
        /*System.out.println("Item Yeet Event!" + level + items + recipe + result + "Recipe Finished Correctly");*/
    }

    ///Debug only
    private void processLegacyRecipe(ServerLevel level, List<ItemEntity> items, PentagramRecipes recipe)
    {
        for(ItemStack needed : recipe.getInputs())
        {
            int remaining = needed.getCount();
            for(ItemEntity itemEntity : items)
            {
                ItemStack stack = itemEntity.getItem();
                if(ItemStack.isSameItemSameComponents(stack, needed))
                {
                    level.playSound(
                            null,
                            this.blockPosition(),
                            CatalystSounds.PENTAGRAM_CRAFT.get(),
                            SoundSource.BLOCKS,
                            1.0f,
                            1.0f
                    );

                    int consume = Math.min(stack.getCount(), remaining);

                    stack.shrink(consume);
                    remaining -= consume;
                    if(remaining <= 0) break;
                }
            }
        }
        level.addFreshEntity(new ItemEntity(
                level, this.getX(), this.getY() + 0.5, this.getZ(), recipe.getResult()
        ));
    }

}