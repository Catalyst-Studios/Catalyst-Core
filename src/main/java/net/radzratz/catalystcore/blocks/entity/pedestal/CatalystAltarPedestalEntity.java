package net.radzratz.catalystcore.blocks.entity.pedestal;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.radzratz.catalystcore.blocks.entity.CatalystBlockEntities;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CatalystAltarPedestalEntity extends BlockEntity
{
    public final ItemStackHandler interior_holder = new ItemStackHandler(1)
    {
        @Override
        protected int getStackLimit(int slot, @NotNull ItemStack stack)
        {
            return 1;
        }

        @Override
        protected void onContentsChanged(int slot)
        {
            setChanged();
            assert level != null;
            if(!level.isClientSide())
            {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };
    private float rotation;

    public CatalystAltarPedestalEntity(BlockPos pos, BlockState blockState)
    {
        super(CatalystBlockEntities.PEDESTAL_ALTAR.get(), pos, blockState);
    }

    public float getRenderingRotation()
    {
        rotation += 0.5f;
        if(rotation >= 360)
        {
            rotation = 0;
        }
        return rotation;
    }

    public void clearContents()
    {
        interior_holder.setStackInSlot(0, ItemStack.EMPTY);
    }

    public void drops()
    {
        SimpleContainer inv = new SimpleContainer(interior_holder.getSlots());
        for(int i = 0; i <interior_holder.getSlots(); i++)
        {
            inv.setItem(i, interior_holder.getStackInSlot(i));
        }
        assert this.level != null;
        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries)
    {
        super.saveAdditional(tag, registries);
        tag.put("interior_holder", interior_holder.serializeNBT(registries));
    }

    @Override
    protected void loadAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries)
    {
        super.loadAdditional(tag, registries);
        interior_holder.deserializeNBT(registries, tag.getCompound("interior_holder"));
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket()
    {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag(HolderLookup.@NotNull Provider pRegistries)
    {
        return saveWithoutMetadata(pRegistries);
    }
}
