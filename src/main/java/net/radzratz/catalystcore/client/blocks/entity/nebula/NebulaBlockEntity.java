package net.radzratz.catalystcore.client.blocks.entity.nebula;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.FloatTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.radzratz.catalystcore.client.blocks.entity.CTCEBlockEntities;
import net.radzratz.catalystcore.client.visuals.particle.CTCEParticles;

/*
KubeJS
        let targetState = Block.getBlock('catalystcore:nebula_eterna').defaultBlockState();
        level.setBlock(targetPos, targetState, 3);
        let targetBE = level.getBlockEntity(targetPos);
        
        if(targetBE)
        {
            targetBE.active = true;
            targetBE.mode = 2;
            targetBE.customRadius = 4.0;
            targetBE.customStrength = 2.5;
            targetBE.starDensity = 0.8;
            targetBE.transitionTicks = 100;
            
            targetBE.colorA = [0.9, 0.1, 0.2]; // Rojo
            targetBE.colorB = [0.1, 0.5, 1.0]; // Azul

            targetBE.setChanged();
            level.sendBlockUpdated(targetPos, targetState, targetState, 3);
        }
*/
@SuppressWarnings("null")
public class NebulaBlockEntity extends BlockEntity
{
    //give @p catalystcore:nebula_eterna[minecraft:block_entity_data={id:"catalystcore:nebula_eterna", active:true, customRadius:3.5f, customStrength:2.0f, mode:2, progress:0.0f, transitionSpeed:0.005f, transitionTicks:200, starDensity:0.8f, colorA:[0.7f, 0.25f, 0.95f], colorB:[0.2f, 0.6f, 1.0f]}] 1
    public float customRadius = 1.0f;
    public float customStrength = 1.5f;
    public boolean active = true;

    // Operational Modes:
    // 1: normal mode (Default fixed colorA)
    // 2: transition between colorA and colorB)
    // 3: Black Hole Transition mode
    public int mode = 1;
    public float[] colorA = new float[]{0.7f, 0.25f, 0.95f};
    public float[] colorB = new float[]{0.2f, 0.6f, 1.0f};
    public float progress = 0.0f;
    public float transitionSpeed = 0.005f;
    public int transitionTicks = 200;
    public float starDensity = 0.5f;
    private boolean pingPongForward = true;

    public NebulaBlockEntity(BlockPos pos, BlockState state)
    {
        super(CTCEBlockEntities.NEBULA_ETERNA.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, NebulaBlockEntity entity) {
        if(!entity.active) return;

        float step = entity.transitionTicks > 0 ? (1.0f / (float) entity.transitionTicks) : entity.transitionSpeed;

        if(entity.mode == 2)
        {
            if(entity.pingPongForward)
            {
                entity.progress += step;
                if(entity.progress >= 1.0f)
                {
                    entity.progress = 1.0f;
                    entity.pingPongForward = false;
                }
            }
            else
            {
                entity.progress -= step;
                if(entity.progress <= 0.0f)
                {
                    entity.progress = 0.0f;
                    entity.pingPongForward = true;
                }
            }
        }
        else if(entity.mode == 3)
        {
            if(entity.progress < 1.0f)
            {
                entity.progress = Math.min(1.0f, entity.progress + step);
            }
        }

        if(level.isClientSide())
        {
            if(level.random.nextInt(2) == 0)
            { 
                double radius = 15.0; 
                double pX = pos.getX() + 0.5 + (level.random.nextDouble() - 0.5) * radius;
                double pY = pos.getY() + 0.5 + (level.random.nextDouble() - 0.5) * radius;
                double pZ = pos.getZ() + 0.5 + (level.random.nextDouble() - 0.5) * radius;

                level.addParticle(CTCEParticles.PENTAGRAM_PARTICLE.get(), 
                        pX, pY, pZ, 
                        pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
            }
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries)
    {
        super.saveAdditional(tag, registries);
        tag.putFloat("customRadius", this.customRadius);
        tag.putFloat("customStrength", this.customStrength);
        tag.putBoolean("active", this.active);
        tag.putInt("mode", this.mode);
        tag.putFloat("progress", this.progress);
        tag.putFloat("transitionSpeed", this.transitionSpeed);
        tag.putInt("transitionTicks", this.transitionTicks);
        tag.putFloat("starDensity", this.starDensity);

        ListTag tagA = new ListTag();
        tagA.add(FloatTag.valueOf(colorA[0]));
        tagA.add(FloatTag.valueOf(colorA[1]));
        tagA.add(FloatTag.valueOf(colorA[2]));
        tag.put("colorA", tagA);

        ListTag tagB = new ListTag();
        tagB.add(FloatTag.valueOf(colorB[0]));
        tagB.add(FloatTag.valueOf(colorB[1]));
        tagB.add(FloatTag.valueOf(colorB[2]));
        tag.put("colorB", tagB);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries)
    {
        super.loadAdditional(tag, registries);
        if(tag.contains("customRadius")) this.customRadius = tag.getFloat("customRadius");
        if(tag.contains("customStrength")) this.customStrength = tag.getFloat("customStrength");
        if(tag.contains("active")) this.active = tag.getBoolean("active");
        if(tag.contains("mode")) this.mode = tag.getInt("mode");
        if(tag.contains("progress")) this.progress = tag.getFloat("progress");
        if(tag.contains("transitionSpeed")) this.transitionSpeed = tag.getFloat("transitionSpeed");
        if(tag.contains("transitionTicks")) this.transitionTicks = tag.getInt("transitionTicks");
        if(tag.contains("maxTicks")) this.transitionTicks = tag.getInt("maxTicks");
        if(tag.contains("starDensity")) this.starDensity = tag.getFloat("starDensity");

        if(tag.contains("colorA", Tag.TAG_LIST))
        {
            ListTag tagA = tag.getList("colorA", Tag.TAG_FLOAT);
            if(tagA.size() >= 3)
            {
                this.colorA = new float[]{tagA.getFloat(0), tagA.getFloat(1), tagA.getFloat(2)};
            }
        }

        if(tag.contains("colorB", Tag.TAG_LIST))
        {
            ListTag tagB = tag.getList("colorB", Tag.TAG_FLOAT);
            if(tagB.size() >= 3)
            {
                this.colorB = new float[]{tagB.getFloat(0), tagB.getFloat(1), tagB.getFloat(2)};
            }
        }
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries)
    {
        CompoundTag tag = super.getUpdateTag(registries);
        saveAdditional(tag, registries);
        return tag;
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket()
    {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}