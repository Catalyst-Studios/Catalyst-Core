package net.radzratz.catalystcore.client.blocks.entity.anomaly;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.radzratz.catalystcore.client.blocks.entity.CTCEBlockEntities;
import net.radzratz.catalystcore.client.visuals.particle.CTCEParticles;


public class GravityAnomalyBlockEntity extends BlockEntity {

    public float customRadius = 1.0f;
    public float customStrength = 1.5f;
    public boolean active = true;
    public GravityAnomalyBlockEntity(BlockPos pos, BlockState state) {
        super(CTCEBlockEntities.ANOMALY.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, GravityAnomalyBlockEntity entity) {
        if (!entity.active) return;

        if(level.isClientSide()) //particles basically
        {

            if (level.random.nextInt(2) == 0)
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
        else //trashcan
        {
            AABB pullArea = new AABB(pos).inflate(5.0D);
            List<ItemEntity> items = level.getEntitiesOfClass(ItemEntity.class, pullArea);
            
            Vec3 centerOfAnomaly = Vec3.atCenterOf(pos);

            for(ItemEntity item : items)
            {
                Vec3 itemPos = item.position();
                double distance = itemPos.distanceTo(centerOfAnomaly);

                if(distance <= 0.7D)
                {
                    item.discard(); 
                }
                else
                {
                    Vec3 pullDirection = centerOfAnomaly.subtract(itemPos).normalize();

                    double pullStrength = 0.05D;
                    
                    pullStrength += (5.0D - distance) * 0.01D;
                    
                    Vec3 currentMotion = item.getDeltaMovement();
                    item.setDeltaMovement(currentMotion.add(pullDirection.scale(pullStrength)));

                    item.hasImpulse = true; 
                }
            }
        }
    }
}