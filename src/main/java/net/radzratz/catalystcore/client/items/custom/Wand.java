package net.radzratz.catalystcore.client.items.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.radzratz.catalystcore.client.blocks.CTCEBlocks;
import net.radzratz.catalystcore.client.entities.CTCEEntities;
import net.radzratz.catalystcore.client.entities.tilmat_table.CatalystTilmatEntity;
import org.jetbrains.annotations.NotNull;

public class Wand extends Item
{
    public Wand(Properties properties)
    {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context)
    {
        Level level = context.getLevel();
        BlockPos clickedPos = context.getClickedPos();
        Player player = context.getPlayer();
        ItemStack stack = context.getItemInHand();

        if(level.isClientSide)
        {
            return InteractionResult.SUCCESS;
        }

        for(int dx = -1; dx <= 1; dx++)
        {
            for(int dy = -1; dy <= 1; dy++)
            {
                for(int dz = -1; dz <= 1; dz++)
                {
                    BlockPos center = clickedPos.offset(-dx, -dy, -dz);

                    if(checkBookshelfStructure(level, center))
                    {
                        LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(level);
                        if(lightning != null)
                        {
                            lightning.setPos(center.getX() + 0.5, center.getY(), center.getZ() + 0.5);
                            level.addFreshEntity(lightning);
                        }
                        if(level instanceof ServerLevel server)
                        {
                            CatalystTilmatEntity circle = new CatalystTilmatEntity(CTCEEntities.TILMAT_TABLE.get(), server);
                            circle.setPos(center.getX() + 0.5, center.getY(), center.getZ() + 0.5);
                            server.addFreshEntity(circle);
                        }

                        level.setBlock(center, CTCEBlocks.TILMAT_TABLE.get().defaultBlockState(), 3);
                        level.scheduleTick(center, CTCEBlocks.TILMAT_TABLE.get(), 1);

                        if(player != null && !player.isCreative())
                        {
                            stack.shrink(1);
                        }

                        return InteractionResult.CONSUME;
                    }
                }
            }
        }

        return InteractionResult.PASS;
    }

    private boolean checkBookshelfStructure(Level level, BlockPos center)
    {
        for(int x = -1; x <= 1; x++)
        {
            for(int y = -1; y <= 1; y++)
            {
                for(int z = -1; z <= 1; z++)
                {
                    BlockPos offset = center.offset(x, y, z);
                    if(x == 0 && y == 0 && z == 0) continue;

                    BlockState state = level.getBlockState(offset);
                    if(!state.is(Blocks.BOOKSHELF))
                    {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
