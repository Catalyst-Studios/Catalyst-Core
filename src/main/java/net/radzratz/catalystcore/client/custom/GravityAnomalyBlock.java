package net.radzratz.catalystcore.client.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.radzratz.catalystcore.client.blocks.entity.CTCEBlockEntities;
import net.radzratz.catalystcore.client.blocks.entity.anomaly.GravityAnomalyBlockEntity;
import org.jetbrains.annotations.Nullable;

public class GravityAnomalyBlock extends Block implements EntityBlock
{

    public GravityAnomalyBlock(Properties properties)
    {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {
        return new GravityAnomalyBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type)
    {
        return type == CTCEBlockEntities.ANOMALY.get() ?
                (lvl, pos, st, be) -> GravityAnomalyBlockEntity.tick(lvl, pos, st, (GravityAnomalyBlockEntity) be) :
                null;
    }

    @Override
    public MapColor getMapColor(BlockState state, BlockGetter level, BlockPos pos, MapColor defaultColor) {
        return MapColor.COLOR_BLACK;
    }

    @Override
    public float getExplosionResistance(BlockState state, BlockGetter level, BlockPos pos, Explosion explosion) {
        return 1000f;
    }

    @Override
    public SoundType getSoundType(BlockState state, LevelReader level, BlockPos pos, @Nullable Entity entity) {
        return SoundType.GLASS;
    }
}