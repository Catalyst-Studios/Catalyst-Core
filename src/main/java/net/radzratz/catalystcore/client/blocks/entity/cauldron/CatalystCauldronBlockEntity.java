package net.radzratz.catalystcore.client.blocks.entity.cauldron;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.radzratz.catalystcore.client.blocks.entity.CTCEBlockEntities;
import net.radzratz.catalystcore.client.items.tags.CTCETags;
import net.radzratz.catalystcore.common.recipes.cauldron.recipe_executors.ReactionBrewingExecutor;
import net.radzratz.catalystcore.common.recipes.cauldron.recipe_executors.WaterBrewingExecutor;
import net.radzratz.catalystcore.common.recipes.cauldron.util.enums.CauldronMode;
import net.radzratz.catalystcore.common.recipes.cauldron.recipe_executors.FluidBrewingExecutor;
import net.radzratz.catalystcore.common.recipes.cauldron.recipe_executors.LavaBrewingExecutor;
import net.radzratz.catalystcore.common.recipes.cauldron.recipe_executors.UniversalBrewingExecutor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class CatalystCauldronBlockEntity extends BlockEntity
{
    private CauldronMode mode = CauldronMode.NONE;

    public static final int TANK_CAPACITY = 4000;
    public static final int INVENTORY_SIZE = 4;
    private int progress = 0;
    private boolean isProcessing = false;

    private final FluidTank fluidTank = new FluidTank(TANK_CAPACITY)
    {
        @Override
        protected void onContentsChanged()
        {
            setChanged();
            assert level != null;
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
        }
    };

    public CatalystCauldronBlockEntity(BlockPos pos, BlockState state)
    {
        super(CTCEBlockEntities.CAULDRON.get(), pos, state);
    }

    public FluidTank getFluidTank()
    {
        return fluidTank;
    }

    private final ItemStackHandler inventory = new ItemStackHandler(INVENTORY_SIZE)
    {
        @Override
        protected void onContentsChanged(int slot)
        {
            setChanged();
        }
    };

    public ItemStackHandler getInventory()
    {
        return inventory;
    }

    public CauldronMode getMode()
    {
        return mode;
    }

    public void setMode(CauldronMode newMode)
    {
        if(this.mode != newMode)
        {
            this.mode = newMode;
            setChanged();
            if(level != null)
            {
                level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
            }
        }
    }

    public static void tick(Level level,
                            BlockPos pos,
                            BlockState state,
                            CatalystCauldronBlockEntity be)
    {
        if(level.isClientSide) return;

        AABB centerBox = new AABB(
                pos.getX() + 0.375, pos.getY() + 0.1, pos.getZ() + 0.375,
                pos.getX() + 0.625, pos.getY() + 0.15, pos.getZ() + 0.625
        );

        List<ItemEntity> nearbyItems = level.getEntitiesOfClass(ItemEntity.class, centerBox);

        for(ItemEntity itemEntity : nearbyItems)
        {
            if(!itemEntity.isAlive() || itemEntity.getItem().isEmpty()) continue;

            ItemStack entityStack = itemEntity.getItem();
            ItemStack singleItem = entityStack.copyWithCount(1);

            for(int i = 0; i < be.inventory.getSlots(); i++)
            {
                if(be.inventory.getStackInSlot(i).isEmpty())
                {
                    be.inventory.setStackInSlot(i, singleItem);
                    entityStack.shrink(1);

                    if(entityStack.isEmpty())
                    {
                        itemEntity.discard();
                    }

                    break;
                }
            }

            level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 0.4f, 1.1f);
            be.setChanged();
        }

        switch (be.getMode())
        {
            case WATER_BREWING ->
            {
                if(WaterBrewingExecutor.tickWaterBrewing(be, level)) return;
            }
            case LAVA_BREWING ->
            {
                if(LavaBrewingExecutor.tickLavaBrewing(be, level)) return;
            }
            case UNIVERSAL_BREWING ->
            {
                if(UniversalBrewingExecutor.tickUniversalBrewing(be, level)) return;
            }
            case FLUID_BREWING ->
            {
                if(FluidBrewingExecutor.tickFluidBrewing(be, level)) return;
            }
            case REACTION_BREWING_ITEM ->
            {
                if(ReactionBrewingExecutor.tickReactionBrewingItem(be, level)) return;
            }
            case REACTION_BREWING_FLUID ->
            {
                if(ReactionBrewingExecutor.tickReactionBrewingFluid(be, level)) return;
            }
            case NONE ->
            {
                /// This doesn't execute any recipe and prevents the Block Entity from Ticking
            }
        }
    }

    public void resetProgressExternally()
    {
        this.progress = 0;
        this.isProcessing = false;
        setChanged();
    }

    public void incrementProgressExternally()
    {
        this.progress++;
        this.isProcessing = true;
        setChanged();
    }

    public int getProgress()
    {
        return progress;
    }

    public boolean hasValidHeatSource()
    {
        assert level != null;
        BlockState below = level.getBlockState(worldPosition.below());
        if(below.hasProperty(BlockStateProperties.LIT))
        {
            return below.is(CTCETags.Blocks.HEAT_SOURCES) && below.getValue(BlockStateProperties.LIT);
        }
        return below.is(CTCETags.Blocks.HEAT_SOURCES);
    }

    public List<ItemStack> getItemInputs()
    {
        List<ItemStack> inputs = new ArrayList<>();
        for(int i = 0; i < inventory.getSlots(); i++)
        {
            ItemStack stack = inventory.getStackInSlot(i);
            if(!stack.isEmpty())
            {
                inputs.add(stack.copy());
            }
        }
        return inputs;
    }

    public void consumeIngredients(List<Ingredient> ingredients)
    {
        for(Ingredient ingredient : ingredients)
        {
            for(int i = 0; i < inventory.getSlots(); i++)
            {
                ItemStack stack = inventory.getStackInSlot(i);
                if(ingredient.test(stack))
                {
                    inventory.extractItem(i, 1, false);
                    break;
                }
            }
        }
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider provider)
    {
        super.saveAdditional(tag, provider);

        CompoundTag tankTag = new CompoundTag();
        fluidTank.writeToNBT(provider, tankTag);
        tag.put("Tank", tankTag);

        CompoundTag inventoryTag = new CompoundTag();
        inventoryTag.putInt("Size", INVENTORY_SIZE);
        ListTag itemsList = new ListTag();
        for(int i = 0; i < inventory.getSlots(); i++)
        {
            ItemStack stack = inventory.getStackInSlot(i);
            if(!stack.isEmpty())
            {
                CompoundTag itemTag = (CompoundTag) stack.save(provider);
                itemTag.putInt("Slot", i);
                itemsList.add(itemTag);
            }
        }
        inventoryTag.put("Items", itemsList);
        tag.put("Inventory", inventoryTag);
        tag.putString("Mode", mode.name());
    }

    @Override
    public @NotNull CompoundTag getUpdateTag(HolderLookup.@NotNull Provider provider)
    {
        CompoundTag tag = super.getUpdateTag(provider);
        saveAdditional(tag, provider);
        return tag;
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket()
    {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    protected void loadAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider provider)
    {
        super.loadAdditional(tag, provider);

        if(tag.contains("Tank", Tag.TAG_COMPOUND))
        {
            fluidTank.readFromNBT(provider, tag.getCompound("Tank"));
        }

        if(tag.contains("Inventory", Tag.TAG_COMPOUND))
        {
            CompoundTag inventoryTag = tag.getCompound("Inventory");
            ListTag itemsList = inventoryTag.getList("Items", Tag.TAG_COMPOUND);
            for(int i = 0; i < itemsList.size(); i++)
            {
                CompoundTag itemTag = itemsList.getCompound(i);
                int slot = itemTag.getInt("Slot");
                if(slot >= 0 && slot < inventory.getSlots())
                {
                    ItemStack stack = ItemStack.parse(provider, itemTag).orElse(ItemStack.EMPTY);
                    inventory.setStackInSlot(slot, stack);
                }
            }
            if(tag.contains("Mode", Tag.TAG_STRING))
            {
                try
                {
                    this.mode = CauldronMode.valueOf(tag.getString("Mode"));
                }
                    catch (IllegalArgumentException ignored)
                {
                    this.mode = CauldronMode.NONE;
                }
            }
        }
    }
}
