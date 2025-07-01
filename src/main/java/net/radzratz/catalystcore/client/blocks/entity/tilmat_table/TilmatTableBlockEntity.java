package net.radzratz.catalystcore.client.blocks.entity.tilmat_table;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.radzratz.catalystcore.client.blocks.CTCEBlocks;
import net.radzratz.catalystcore.client.blocks.entity.CTCEBlockEntities;

import java.util.Objects;

public class TilmatTableBlockEntity extends BlockEntity
{
    private int ritualTimer = 400;

    public TilmatTableBlockEntity(BlockPos pos, BlockState state)
    {
        super(CTCEBlockEntities.TILMAT_TABLE.get(), pos, state);
    }

    public void tickServer(BlockPos pos)
    {
        if(level == null || level.isClientSide) return;

        if(!isStructureIntact())
        {

            if(level instanceof ServerLevel server)
            {
                server.explode(
                        null,
                        pos.getX() + 0.5,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5,
                        10.0f,
                        Level.ExplosionInteraction.BLOCK
                );
            }

            ritualTimer = 0;
            level.removeBlock(pos, false);
            return;
        }

        ritualTimer--;

        if(ritualTimer == 200)
        {
            spawnCircleAt(worldPosition.below());
        }
        if(ritualTimer == 300)
        {
            spawnCircleAt(worldPosition);
        }
        if(ritualTimer == 400)
        {
            spawnCircleAt(worldPosition.above());
        }
        if(ritualTimer == 500)
        {
            LightningBolt bolt = EntityType.LIGHTNING_BOLT.create(level);
            if(bolt != null)
            {
                bolt.setPos(worldPosition.getX() + 0.5, worldPosition.getY(), worldPosition.getZ() + 0.5);
                level.addFreshEntity(bolt);
            }
        }

        if(ritualTimer <= 0)
        {
            completeRitual();
        }
            else
        {
            level.scheduleTick(worldPosition, getBlockState().getBlock(), 1);
        }
    }

    private void completeRitual()
    {
        destroyBookshelves(level, worldPosition);
        assert level != null;
        level.setBlock(worldPosition, CTCEBlocks.TILMAT_TABLE.get().defaultBlockState(), 3);
    }

    private boolean isStructureIntact()
    {
        for(int x = -1; x <= 1; x++)
        {
            for(int y = -1; y <= 1; y++)
            {
                for(int z = -1; z <= 1; z++)
                {
                    if(x == 0 && y == 0 && z == 0) continue;

                    BlockPos offset = worldPosition.offset(x, y, z);
                    BlockState state = Objects.requireNonNull(getLevel()).getBlockState(offset);
                    if (!state.is(Blocks.BOOKSHELF))
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public int getRitualTimer()
    {
        return ritualTimer;
    }

    private void spawnCircleAt(BlockPos pos)
    {
        assert level != null;
        ((ServerLevel) level).sendParticles(ParticleTypes.ENCHANT,
                pos.getX() + 0.5,
                pos.getY() + 0.1,
                pos.getZ() + 0.5,
                10,
                0.3,
                0.01,
                0.3,
                0.01);
    }

    private void destroyBookshelves(Level level, BlockPos center)
    {
        for(int x = -1; x <= 1; x++)
        {
            for(int y = -1; y <= 1; y++)
            {
                for(int z = -1; z <= 1; z++)
                {
                    BlockPos offset = center.offset(x, y, z);
                    if(x == 0 && y == 0 && z == 0) continue;
                    if(level.getBlockState(offset).is(Blocks.BOOKSHELF))
                    {
                        level.destroyBlock(offset, false);
                    }
                }
            }
        }
    }
}
