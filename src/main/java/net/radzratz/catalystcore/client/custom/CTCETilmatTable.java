package net.radzratz.catalystcore.client.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.radzratz.catalystcore.client.blocks.entity.tilmat_table.TilmatTableBlockEntity;
import org.jetbrains.annotations.NotNull;

public class CTCETilmatTable extends Block implements EntityBlock
{
    public CTCETilmatTable(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state)
    {
        return new TilmatTableBlockEntity(pos, state);
    }

    @Override
    public void tick(@NotNull BlockState state,
                     ServerLevel level,
                     @NotNull BlockPos pos,
                     @NotNull RandomSource random)
    {
        BlockEntity be = level.getBlockEntity(pos);
        if(be instanceof TilmatTableBlockEntity ritual)
        {
            ritual.tickServer(pos);
        }
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.MODEL;
    }
}
