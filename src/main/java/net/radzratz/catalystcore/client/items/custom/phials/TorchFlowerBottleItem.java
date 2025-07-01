package net.radzratz.catalystcore.client.items.custom.phials;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.radzratz.catalystcore.client.items.CTCEItems;
import net.radzratz.catalystcore.common.util.config.CTCEConfig;

@EventBusSubscriber
public class TorchFlowerBottleItem
{
    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event)
    {
        if(!CTCEConfig.CONFIG.phials.phialEvents.isTrue()) return;

        if(!CTCEConfig.CONFIG.phials.torchFlowerPhial.isTrue()) return;

        Player player = event.getEntity();
        Level level = player.level();
        BlockPos pos = event.getPos();

        BlockState blockState = level.getBlockState(pos);

        if(!blockState.is(Blocks.TORCHFLOWER)) return;

        ItemStack mainHand = player.getMainHandItem();
        ItemStack offHand = player.getOffhandItem();

        boolean mainIsShears = mainHand.is(Items.SHEARS);
        boolean offIsBottle = offHand.is(CTCEItems.REINFORCED_BOTTLE.get());

        if(mainIsShears && offIsBottle)
        {
            if(!level.isClientSide)
            {
                offHand.shrink(1);

                ItemStack extract = new ItemStack(CTCEItems.TORCHFLOWER_BOTTLE.get());
                if(!player.getInventory().add(extract))
                {
                    player.drop(extract, false);
                }

                level.setBlockAndUpdate(pos, Blocks.DEAD_BUSH.defaultBlockState());

                level.playSound(null, pos, SoundEvents.BOTTLE_FILL, SoundSource.PLAYERS, 1.0F, 1.0F);

                mainHand.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
            }

            event.setCanceled(true);
            event.setCancellationResult(InteractionResult.SUCCESS);
        }
    }
}
