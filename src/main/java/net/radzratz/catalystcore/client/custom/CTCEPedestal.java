package net.radzratz.catalystcore.client.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.radzratz.catalystcore.client.blocks.entity.pedestal.CatalystAltarPedestalEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CTCEPedestal extends BaseEntityBlock
{
    public static final VoxelShape SHAPE = Block.box(2,0,2,14,10,14);

    public static final MapCodec<CTCEPedestal> CODEC = simpleCodec(CTCEPedestal::new);

    public CTCEPedestal(Properties properties)
    {
        super(properties);
    }

    @Override
    protected @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context)
    {
        return SHAPE;
    }

    @Override
    protected @NotNull MapCodec<? extends BaseEntityBlock> codec()
    {
        return CODEC;
    }

    @Override
    protected @NotNull RenderShape getRenderShape(@NotNull BlockState state)
    {
        return RenderShape.MODEL;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState)
    {
        return new CatalystAltarPedestalEntity(blockPos, blockState);
    }

    @Override
    protected void onRemove(BlockState state, @NotNull Level level, @NotNull BlockPos pos, BlockState newState, boolean movedByPiston)
    {
        if(state.getBlock() != newState.getBlock())
        {
            if(level.getBlockEntity(pos) instanceof CatalystAltarPedestalEntity pedestalBlockEntity)
            {
                pedestalBlockEntity.drops();
                level.updateNeighbourForOutputSignal(pos, this);
            }
        }
        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    @Override
    protected @NotNull ItemInteractionResult useItemOn(@NotNull ItemStack stack, @NotNull BlockState state, Level level, @NotNull BlockPos pos,
                                                       @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult)
    {
        if(level.getBlockEntity(pos) instanceof CatalystAltarPedestalEntity pedestalEntity)
        {
            if(pedestalEntity.interior_holder.getStackInSlot(0).isEmpty() && !stack.isEmpty())
            {
            pedestalEntity.interior_holder.insertItem(0, stack.copy(), false);
            stack.shrink(1);
            level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS,1f,2f);
            }
            else if(stack.isEmpty())
            {
                ItemStack stackPedestal = pedestalEntity.interior_holder.extractItem(0,1,false);
                player.setItemInHand(InteractionHand.MAIN_HAND, stackPedestal);
                pedestalEntity.clearContents();
                level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS,1f,1f);
            }
        }
        return ItemInteractionResult.SUCCESS;
    }
}
