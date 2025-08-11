package net.radzratz.catalystcore.client.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.radzratz.catalystcore.client.blocks.entity.CTCEBlockEntities;
import net.radzratz.catalystcore.client.blocks.entity.cauldron.CatalystCauldronBlockEntity;
import net.radzratz.catalystcore.client.items.tags.CTCETags;
import net.radzratz.catalystcore.common.recipes.cauldron.util.enums.CauldronMode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("all")
public class CTCECauldron extends BaseEntityBlock
{
    public static final VoxelShape SHAPE = Shapes.or(
            Block.box(2, 0, 2, 14, 2, 14),
            Block.box(2, 2, 2, 3, 10, 14),
            Block.box(13, 2, 2, 14, 10, 14),
            Block.box(3, 2, 2, 13, 10, 3),
            Block.box(3, 2, 13, 13, 10, 14)
    );

    public static final MapCodec<CTCECauldron> CODEC = simpleCodec(CTCECauldron::new);

    public CTCECauldron(Properties properties)
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
    protected boolean canBeReplaced(@NotNull BlockState state, @NotNull BlockPlaceContext useContext)
    {
        return false;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state)
    {
        return new CatalystCauldronBlockEntity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> type)
    {
        return level.isClientSide ? null : createTickerHelper(type, CTCEBlockEntities.CAULDRON.get(), CatalystCauldronBlockEntity::tick);
    }

    @Override
    public void onRemove(BlockState state, @NotNull Level level, @NotNull BlockPos pos, BlockState newState, boolean isMoving)
    {
        if(!state.is(newState.getBlock()))
        {
            BlockEntity be = level.getBlockEntity(pos);
            if(be instanceof CatalystCauldronBlockEntity cauldron)
            {
                ItemStackHandler inv = cauldron.getInventory();
                for(int i = 0; i < inv.getSlots(); i++)
                {
                    ItemStack stack = inv.getStackInSlot(i);
                    if(!stack.isEmpty())
                    {
                        Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), stack);
                    }
                }
            }
        }

        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Override
    protected @NotNull ItemInteractionResult useItemOn(@NotNull ItemStack stack, @NotNull BlockState state, Level level, @NotNull BlockPos pos,
                                                       @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult)
    {
        if(level.isClientSide) return ItemInteractionResult.SUCCESS;

        if(!(level.getBlockEntity(pos) instanceof CatalystCauldronBlockEntity cauldron))
        {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }

        /// -----------------------
        /// Fluid Bucket
        /// -----------------------
        if(stack.getItem() instanceof BucketItem bucketItem)
        {
            return handleBucketInteraction(bucketItem, stack, player, hand, cauldron, level, pos);
        }

        /// -----------------------
        /// Empty Hand, to get Items out
        /// -----------------------
        if(stack.isEmpty())
        {
            for(int i = CatalystCauldronBlockEntity.INVENTORY_SIZE - 1; i >= 0; i--)
            {
                ItemStack stored = cauldron.getInventory().getStackInSlot(i);
                if(!stored.isEmpty())
                {
                    ItemStack extracted = cauldron.getInventory().extractItem(i, 1, false);
                    if(!player.addItem(extracted))
                    {
                        Containers.dropItemStack(level, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, extracted);
                    }

                    level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 0.5F, 1.0F);
                    level.sendBlockUpdated(pos, state, state, Block.UPDATE_ALL);
                    return ItemInteractionResult.SUCCESS;
                }
            }
        }

        /// -----------------------
        /// Sets Recipe Type and Deactivates the Block Entity
        /// -----------------------
        if(!stack.isEmpty())
        {
            if(stack.is(CTCETags.Items.CAULDRON_ACTIVATOR_WATER))
            {
                cauldron.setMode(CauldronMode.WATER_BREWING);
                player.displayClientMessage(Component.literal("§bCauldron mode has been set to: Water Brewing"), true);
                level.playSound(null, pos, SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS, 1.0f, 1.0f);
                return ItemInteractionResult.SUCCESS;
            }
                else if(stack.is(CTCETags.Items.CAULDRON_ACTIVATOR_LAVA))
            {
                cauldron.setMode(CauldronMode.LAVA_BREWING);
                player.displayClientMessage(Component.literal("§6Cauldron mode has been set to: Lava Brewing "), true);
                level.playSound(null, pos, SoundEvents.LAVA_AMBIENT, SoundSource.BLOCKS, 1.0f, 1.0f);
                return ItemInteractionResult.SUCCESS;
            }
                  else if(stack.is(CTCETags.Items.CAULDRON_ACTIVATOR_UNIVERSAL))
            {
                cauldron.setMode(CauldronMode.UNIVERSAL_BREWING);
                player.displayClientMessage(Component.literal("§6Cauldron mode has been set to: Universal Brewing "), true);
                level.playSound(null, pos, SoundEvents.END_GATEWAY_SPAWN, SoundSource.BLOCKS, 0.3f, 1.0f);
                return ItemInteractionResult.SUCCESS;
            }
                  else if(stack.is(CTCETags.Items.CAULDRON_ACTIVATOR_FLUID))
            {
                cauldron.setMode(CauldronMode.FLUID_BREWING);
                player.displayClientMessage(Component.literal("§6Cauldron mode has been set to: Fluid Brewing "), true);
                level.playSound(null, pos, SoundEvents.WATER_AMBIENT, SoundSource.BLOCKS, 1.0f, 1.0f);
                return ItemInteractionResult.SUCCESS;
            }
                 else if(stack.is(CTCETags.Items.CAULDRON_ACTIVATOR_REACTION_ITEM))
           {
                 cauldron.setMode(CauldronMode.REACTION_BREWING_ITEM);
                 player.displayClientMessage(Component.literal("§6Cauldron mode has been set to: Reaction/Item Brewing"), true);
                 level.playSound(null, pos, SoundEvents.WITHER_HURT, SoundSource.BLOCKS, 0.2f, 1.0f);
                 return ItemInteractionResult.SUCCESS;
           }
                 else if(stack.is(CTCETags.Items.CAULDRON_ACTIVATOR_REACTION_FLUID))
           {
                 cauldron.setMode(CauldronMode.REACTION_BREWING_FLUID);
                 player.displayClientMessage(Component.literal("§6Cauldron mode has been set to: Reaction/Fluid Brewing"), true);
                 level.playSound(null, pos, SoundEvents.WITHER_HURT, SoundSource.BLOCKS, 0.2f, 1.0f);
                 return ItemInteractionResult.SUCCESS;
           }
                else if(cauldron.getMode() == CauldronMode.NONE)
            {
                player.displayClientMessage(Component.literal("§7Cauldron is currently Inactive. Use the correct Item to set it's mode"), true);
            }
        }

        /// -----------------------
        /// Item in hand, to insert Items
        /// -----------------------
        for(int i = 0; i < CatalystCauldronBlockEntity.INVENTORY_SIZE; i++)
        {
            if(cauldron.getInventory().getStackInSlot(i).isEmpty())
            {
                ItemStack toInsert = stack.copyWithCount(1);
                cauldron.getInventory().setStackInSlot(i, toInsert);

                if(!player.isCreative())
                {
                    stack.shrink(1);
                }

                level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 0.5F, 1.0F);
                level.sendBlockUpdated(pos, state, state, Block.UPDATE_ALL);
                return ItemInteractionResult.SUCCESS;
            }
        }

        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }


    private ItemInteractionResult handleBucketInteraction(BucketItem bucketItem, ItemStack stack, Player player, InteractionHand hand,
                                                          CatalystCauldronBlockEntity cauldron, Level level, BlockPos pos)
    {
        if(bucketItem.content != Fluids.EMPTY)
        {
            FluidStack toInsert = new FluidStack(bucketItem.content, FluidType.BUCKET_VOLUME);
            if(cauldron.getFluidTank().fill(toInsert, IFluidHandler.FluidAction.SIMULATE) == FluidType.BUCKET_VOLUME)
            {
                cauldron.getFluidTank().fill(toInsert, IFluidHandler.FluidAction.EXECUTE);
                if(!player.isCreative())
                {
                    player.setItemInHand(hand, new ItemStack(Items.BUCKET));
                }
                level.playSound(null, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0f, 1.0f);
                return ItemInteractionResult.SUCCESS;
            }
        }
            else
            {
            FluidStack drained = cauldron.getFluidTank().drain(FluidType.BUCKET_VOLUME, IFluidHandler.FluidAction.EXECUTE);
            if(!drained.isEmpty())
            {
                Item bucketFilled = drained.getFluid().getBucket();
                if(!player.isCreative())
                {
                    stack.shrink(1);
                    if(stack.isEmpty())
                    {
                        player.setItemInHand(hand, new ItemStack(bucketFilled));
                    }
                        else
                        {
                        player.getInventory().add(new ItemStack(bucketFilled));
                    }
                }
                level.playSound(null, pos, SoundEvents.BUCKET_FILL, SoundSource.BLOCKS, 1.0f, 1.0f);
                return ItemInteractionResult.SUCCESS;
            }
        }


        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    private ItemInteractionResult handleItemInteraction(ItemStack stack, Player player, InteractionHand hand, CatalystCauldronBlockEntity cauldron,
                                                        Level level, BlockPos pos, BlockState state)
    {
        for(int i = 0; i < CatalystCauldronBlockEntity.INVENTORY_SIZE; i++)
        {
            if(cauldron.getInventory().getStackInSlot(i).isEmpty())
            {

                ItemStack toInsert = stack.copyWithCount(1);

                cauldron.getInventory().setStackInSlot(i, toInsert);

                if(!player.isCreative())
                {
                    stack.shrink(1);
                }

                level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 0.5F, 1.0F);
                level.sendBlockUpdated(pos, state, state, Block.UPDATE_ALL);

                return ItemInteractionResult.SUCCESS;
            }
        }

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }
}