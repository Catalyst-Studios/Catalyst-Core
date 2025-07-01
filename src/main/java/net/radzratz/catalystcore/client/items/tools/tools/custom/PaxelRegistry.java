package net.radzratz.catalystcore.client.items.tools.tools.custom;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import net.radzratz.catalystcore.client.items.tags.CTCETags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PaxelRegistry extends DiggerItem
{
    protected static final Map<Block, BlockState> FLATTENABLES = new HashMap<>();

    static
    {
        FLATTENABLES.put(Blocks.GRASS_BLOCK, Blocks.DIRT_PATH.defaultBlockState());
        FLATTENABLES.put(Blocks.DIRT, Blocks.DIRT_PATH.defaultBlockState());
        FLATTENABLES.put(Blocks.PODZOL, Blocks.DIRT_PATH.defaultBlockState());
        FLATTENABLES.put(Blocks.COARSE_DIRT, Blocks.DIRT_PATH.defaultBlockState());
        FLATTENABLES.put(Blocks.MYCELIUM, Blocks.DIRT_PATH.defaultBlockState());
        FLATTENABLES.put(Blocks.ROOTED_DIRT, Blocks.DIRT_PATH.defaultBlockState());
    }

    public PaxelRegistry(Tier pTier, Properties pProperties)
    {
        super(pTier, CTCETags.Blocks.PAXEL_MINEABLE, pProperties);
    }

    protected Optional<BlockState> evaluateNewBlockState(Level level,
                                                         BlockPos pos,
                                                         @Nullable Player player,
                                                         BlockState state,
                                                         UseOnContext context)
    {
        Optional<BlockState> stripped = Optional.ofNullable(state.getToolModifiedState(context, ItemAbilities.AXE_STRIP, false));
        if(stripped.isPresent())
        {
            level.playSound(player, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
            return stripped;
        }

        Optional<BlockState> scraped = Optional.ofNullable(state.getToolModifiedState(context, ItemAbilities.AXE_SCRAPE, false));
        if(scraped.isPresent())
        {
            level.playSound(player, pos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0F, 1.0F);
            return scraped;
        }

        Optional<BlockState> waxOff = Optional.ofNullable(state.getToolModifiedState(context, ItemAbilities.AXE_WAX_OFF, false));
        if(waxOff.isPresent())
        {
            level.playSound(player, pos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0F, 1.0F);
            return waxOff;
        }

        Optional<BlockState> flattened = Optional.ofNullable(FLATTENABLES.get(state.getBlock()));
        if(flattened.isPresent())
        {
            level.playSound(player, pos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
            return flattened;
        }

        Optional<BlockState> doused = Optional.ofNullable(state.getToolModifiedState(context, ItemAbilities.SHOVEL_DOUSE, false));
        if(doused.isPresent())
        {
            level.playSound(player, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, 1.0F);
            return doused;
        }

        return Optional.empty();
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context)
    {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        BlockState state = level.getBlockState(pos);

        if(hasShield(context))
        {
            return InteractionResult.PASS;
        }

        Optional<BlockState> newState = evaluateNewBlockState(level, pos, player, state, context);
        if(newState.isPresent())
        {
            ItemStack stack = context.getItemInHand();

            if(player instanceof ServerPlayer)
            {
                CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)player, pos, stack);
            }

            level.setBlock(pos, newState.get(), 11);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, newState.get()));

            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        if(context.getClickedFace() != Direction.DOWN && level.isEmptyBlock(pos.above()))
        {
            if(state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.DIRT_PATH) || state.is(Blocks.DIRT))
            {
                level.setBlock(pos, Blocks.FARMLAND.defaultBlockState(), 11);
                level.playSound(player, pos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                return InteractionResult.sidedSuccess(level.isClientSide);
            }
        }

        return super.useOn(context);
    }

    private boolean hasShield(UseOnContext context)
    {
        Player player = context.getPlayer();
        return context.getHand() == InteractionHand.MAIN_HAND &&
                player != null &&
                player.getOffhandItem().is(Items.SHIELD) &&
                !player.isSecondaryUseActive();
    }

    @Override
    public boolean canPerformAction(@NotNull ItemStack stack, @NotNull ItemAbility itemAbility)
    {
        return  ItemAbilities.DEFAULT_AXE_ACTIONS.contains(itemAbility) ||
                ItemAbilities.DEFAULT_SHOVEL_ACTIONS.contains(itemAbility) ||
                ItemAbilities.DEFAULT_PICKAXE_ACTIONS.contains(itemAbility) ||
                ItemAbilities.DEFAULT_SWORD_ACTIONS.contains(itemAbility) ||
                ItemAbilities.DEFAULT_HOE_ACTIONS.contains(itemAbility);
    }
}
