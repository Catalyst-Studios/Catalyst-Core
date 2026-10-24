package net.radzratz.catalystcore.client.blocks.entity.warp;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.radzratz.catalystcore.common.registries.CTCEBlockEntityRegistry;
import net.radzratz.catalystcore.common.registries.CTCEParticleRegistry;
import net.radzratz.catalystcore.common.util.config.CTCEConfig;

@SuppressWarnings("null")
public class WarpBlockEntity extends BlockEntity
{
    public float width = 2.0f;
    public float height = 3.0f;
    public boolean active = true;
    public Direction.Axis axis = Direction.Axis.Z;

    public WarpBlockEntity(BlockPos pos, BlockState state)
    {
        super(CTCEBlockEntityRegistry.WARP_PORTAL.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, WarpBlockEntity entity)
    {
        if(!entity.active) return;

        if(level.isClientSide())
        {
            if(level.random.nextInt(3) == 0)
            {
                double pX = pos.getX() + 0.5 + (level.random.nextDouble() - 0.5) * entity.width;
                double pY = pos.getY() + 0.5 + (level.random.nextDouble() - 0.5) * entity.height;
                double pZ = pos.getZ() + 0.5;

                level.addParticle(CTCEParticleRegistry.PENTAGRAM_PARTICLE.get(),
                        pX, pY, pZ,
                        pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
            }
        }
        else
        {
            boolean killEnabled = CTCEConfig.CONFIG.warp.enableKillEffect.get();
            boolean lightningEnabled = CTCEConfig.CONFIG.warp.enableLightningEffect.get();

            if(!killEnabled && !lightningEnabled) return;

            double killDist = CTCEConfig.CONFIG.warp.killDistance.get();
            double lightningDist = CTCEConfig.CONFIG.warp.lightningDistance.get();

            double maxSearchDist = Math.max(killEnabled ? killDist : 0, lightningEnabled ? lightningDist : 0);
            if(maxSearchDist <= 0) return;

            Vec3 centerPos = Vec3.atCenterOf(pos);
            AABB searchBox = new AABB(pos).inflate(maxSearchDist);
            List<Player> players = level.getEntitiesOfClass(Player.class, searchBox);

            double killDistSqr = killDist * killDist;
            double lightningDistSqr = lightningDist * lightningDist;

            long gameTime = level.getGameTime();
            int waveCycle = 40;
            int waveDuration = 10;
            boolean isWaveActive = (gameTime % waveCycle < waveDuration) && (gameTime % 2 == 0);

            for(Player player : players)
            {
                if(!player.isAlive() || player.isCreative() || player.isSpectator()) continue;

                double distSqr = player.distanceToSqr(centerPos);

                if(killEnabled && distSqr <= killDistSqr)
                {
                    DamageSource source = level.damageSources().genericKill();
                    player.setHealth(0);
                    player.die(source);
                }
                else if(lightningEnabled && distSqr <= lightningDistSqr && isWaveActive)
                {
                    player.invulnerableTime = 0;
                    for(int i = 0; i < 3; i++)
                    {
                        double offsetX = (level.random.nextDouble() - 0.5) * 2.0;
                        double offsetZ = (level.random.nextDouble() - 0.5) * 2.0;

                        LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(level);
                        if(lightning != null)
                        {
                            lightning.moveTo(player.getX() + offsetX, player.getY(), player.getZ() + offsetZ);
                            level.addFreshEntity(lightning);
                        }
                    }

                    float damage = CTCEConfig.CONFIG.warp.lightningDamage.get().floatValue();
                    player.hurt(level.damageSources().lightningBolt(), damage);

                    if(ThreadLocalRandom.current().nextFloat() < 0.75f)
                    {
                        Vec3 pushDir = player.position().subtract(centerPos).normalize().scale(0.7);
                        player.setDeltaMovement(player.getDeltaMovement().add(pushDir.x, 0.35, pushDir.z));
                        player.hurtMarked = true;

                        if(level instanceof ServerLevel serverLevel)
                        {
                            serverLevel.sendParticles(ParticleTypes.EXPLOSION, 
                                    player.getX(), player.getY() + 1.0, player.getZ(), 
                                    1, 0.0, 0.0, 0.0, 0.0);
                        }
                        level.playSound(null, player.getX(), player.getY(), player.getZ(), 
                                SoundEvents.GENERIC_EXPLODE.value(), SoundSource.BLOCKS, 1.0f, 1.2f);
                    }
                }
            }
        }
    }

    public void setConfiguration(float width, float height, Direction.Axis axis)
    {
        this.width = width;
        this.height = height;
        this.axis = axis;
        this.setChanged();
        if(this.level != null && !this.level.isClientSide())
        {
            this.level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    public void setAxis(Direction.Axis axis)
    {
        this.axis = axis;
        this.setChanged();
        if(this.level != null && !this.level.isClientSide())
        {
            this.level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries)
    {
        super.saveAdditional(tag, registries);
        tag.putFloat("width", this.width);
        tag.putFloat("height", this.height);
        tag.putBoolean("active", this.active);
        tag.putString("axis", this.axis.getName());
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries)
    {
        super.loadAdditional(tag, registries);
        if(tag.contains("width")) this.width = tag.getFloat("width");
        if(tag.contains("height")) this.height = tag.getFloat("height");
        if(tag.contains("active")) this.active = tag.getBoolean("active");
        if(tag.contains("axis"))
        {
            Direction.Axis axis = Direction.Axis.byName(tag.getString("axis"));
            this.axis = (axis != null) ? axis : Direction.Axis.Z;
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

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt, HolderLookup.Provider registries)
    {
        super.onDataPacket(net, pkt, registries);
        CompoundTag tag = pkt.getTag();
        if(tag != null)
        {
            loadAdditional(tag, registries);
        }
    }

    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider registries)
    {
        loadAdditional(tag, registries);
    }

    public float getWidth()
    {
        return width;
    }

    public float getHeight()
    {
        return height;
    }
    
}