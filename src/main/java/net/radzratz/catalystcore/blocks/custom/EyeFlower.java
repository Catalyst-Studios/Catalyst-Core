package net.radzratz.catalystcore.blocks.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class EyeFlower extends Block
{
    public EyeFlower(Properties properties)
    {
        super(properties);
    }

    @Override
    public void animateTick(@NotNull BlockState state,
                            @NotNull Level level,
                            @NotNull BlockPos pos,
                            RandomSource random)
    {
        if (random.nextInt(5) == 0)
        {
            double x = pos.getX() + random.nextDouble();
            double y = pos.getY() - 0.05;
            double z = pos.getZ() + random.nextDouble();
            level.addParticle(ParticleTypes.SPORE_BLOSSOM_AIR, x, y, z, 0.0, 0.0, 0.0);
        }
    }


    @Override
    public boolean canSurvive(@NotNull BlockState state, LevelReader level, BlockPos pos)
    {
        BlockState above = level.getBlockState(pos.above());
        return Block.canSupportCenter(level, pos.above(), Direction.DOWN);
    }

    @Override
    public @NotNull BlockState updateShape(@NotNull BlockState state,
                                           @NotNull Direction direction,
                                           @NotNull BlockState neighborState,
                                           @NotNull LevelAccessor level,
                                           @NotNull BlockPos pos,
                                           @NotNull BlockPos neighborPos) {
        return !canSurvive(state, level, pos) ? Blocks.AIR.defaultBlockState() : state;
    }

    @Override
    public PushReaction getPistonPushReaction(@NotNull BlockState state)
    {
        return PushReaction.DESTROY;
    }

    public void appendHoverText(@NotNull ItemStack stack, @NotNull Item.TooltipContext context, List<Component> tooltip, @NotNull TooltipFlag flag)
    {
        tooltip.add(Component.translatable("block.catalystcore.eye_flower.desc").withStyle(ChatFormatting.WHITE));
        super.appendHoverText(stack, context, tooltip, flag);
    }
}
