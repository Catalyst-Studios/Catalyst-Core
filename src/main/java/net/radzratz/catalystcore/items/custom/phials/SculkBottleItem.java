package net.radzratz.catalystcore.items.custom.phials;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.radzratz.catalystcore.items.CatalystItems;
import net.radzratz.catalystcore.util.config.CatalystConfig;

@EventBusSubscriber
public class SculkBottleItem
{
    @SubscribeEvent
    public static void onBlockRightClick(PlayerInteractEvent.RightClickBlock event)
    {
        if(!CatalystConfig.CONFIG.phials.phialEvents.isTrue()) return;

        if(!CatalystConfig.CONFIG.phials.sculkPhial.isTrue()) return;

        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        BlockState blockState = level.getBlockState(pos);
        Player player = event.getEntity();
        ItemStack mainHand = player.getMainHandItem();
        ItemStack offHand = player.getOffhandItem();

        boolean mainIsBrush = mainHand.is(Items.GOLDEN_SHOVEL);
        boolean offIsBottle = offHand.is(CatalystItems.REINFORCED_BOTTLE.get());

        boolean validBlock = blockState.is(Blocks.SCULK) || blockState.is(Blocks.SCULK_SENSOR)
                || blockState.is(Blocks.SCULK_CATALYST) || blockState.is(Blocks.SCULK_SHRIEKER);

        if(mainIsBrush && offIsBottle && validBlock)
        {
            if(!level.isClientSide)
            {
                offHand.shrink(1);

                ItemStack extract = new ItemStack(CatalystItems.SCULK_BOTTLE.get());
                if(!player.getInventory().add(extract))
                {
                    player.drop(extract, false);
                }

                level.playSound(null, pos, SoundEvents.BOTTLE_FILL, SoundSource.PLAYERS, 1.0F, 1.0F);
            }

            event.setCanceled(true);
            event.setCancellationResult(InteractionResult.SUCCESS);
        }
    }
}
