package net.radzratz.catalystcore.client.items.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.radzratz.catalystcore.client.entities.pentagram.PentagramEntity;
import net.radzratz.catalystcore.client.entities.CTCEEntities;
import net.radzratz.catalystcore.client.sound.CTCESounds;
import net.radzratz.catalystcore.common.util.config.CTCEConfig;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class PentagramItem extends Item
{
    public PentagramItem(Properties properties)
    {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext context)
    {
        boolean pentagramSpawnEnabled = CTCEConfig.CONFIG.pentagram.enablePentagramSpawn.get();

        if(!pentagramSpawnEnabled)
        {
            if(context.getPlayer() != null)
            {
                context.getPlayer().displayClientMessage(
                        Component.literal("Pentagram spawning is currently disabled. Change requires game restart."),
                        true
                );
            }
            return InteractionResult.FAIL;
        }

        Level level = context.getLevel();
        if(level.isClientSide)
        {
            return InteractionResult.SUCCESS;
        }

        BlockPos pos = context.getClickedPos();
        @SuppressWarnings("unused")
        Direction face = context.getClickedFace();
        ItemStack stack = context.getItemInHand();

        double x = pos.getX() + 0.5D;
        double y = pos.getY() + 1.0D;
        double z = pos.getZ() + 0.5D;

        level.playSound(
                null,
                pos,
                CTCESounds.PENTAGRAM_PLACE.get(),
                SoundSource.BLOCKS,
                1.0f,
                1.0f
        );

        PentagramEntity pentagram = new PentagramEntity(CTCEEntities.PENTAGRAM.get(), level);
        pentagram.moveTo(x, y, z, 0, 0);
        level.addFreshEntity(pentagram);

        if(!Objects.requireNonNull(context.getPlayer()).isCreative())
        {
            stack.shrink(1);
        }

        return InteractionResult.SUCCESS;
    }
}
