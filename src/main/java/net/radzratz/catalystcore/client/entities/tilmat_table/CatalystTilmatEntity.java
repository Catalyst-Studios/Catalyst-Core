package net.radzratz.catalystcore.client.entities.tilmat_table;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class CatalystTilmatEntity extends Entity
{
    private int ticksAlive = 0;

    public CatalystTilmatEntity(EntityType<?> type, Level level)
    {
        super(type, level);
        this.noPhysics = true;
    }

    @Override
    public boolean fireImmune()
    {
        return true;
    }

    @Override
    public void tick()
    {
        super.tick();
        ticksAlive++;

        if(ticksAlive == 100)
        {
            playStartSound();
        }

        if(ticksAlive >= 300)
        {
            playEndSound();
            discard();
        }
    }

    private void playStartSound()
    {
        level().playSound(
                null,
                blockPosition(),
                SoundEvents.BEACON_ACTIVATE,
                SoundSource.BLOCKS,
                1.0f,
                1.0f
        );
    }

    private void playEndSound()
    {
        level().playSound(
                null,
                blockPosition(),
                SoundEvents.WITHER_AMBIENT,
                SoundSource.BLOCKS,
                1.0f,
                1.0f
        );
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {}

    @Override
    protected void readAdditionalSaveData(@NotNull CompoundTag tag) {}

    @Override
    protected void addAdditionalSaveData(@NotNull CompoundTag tag) {}
}
