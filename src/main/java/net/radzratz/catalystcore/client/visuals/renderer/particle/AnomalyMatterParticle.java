package net.radzratz.catalystcore.client.visuals.renderer.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class AnomalyMatterParticle extends TextureSheetParticle
{
    private final SpriteSet sprites;
    private final Vec3 centerPos;
    private final float baseSize;

    public AnomalyMatterParticle(ClientLevel level, Vec3 centerPos, double x, double y, double z, SpriteSet sprites) {
        super(level, x, y, z);
        this.centerPos = centerPos;
        this.sprites = sprites;

        this.lifetime = 30 + random.nextInt(20);
        this.baseSize = 0.1f + random.nextFloat() * 0.15f;
        this.quadSize = this.baseSize;

        this.setSpriteFromAge(sprites);
        
        this.rCol = 0.3f;
        this.gCol = 0.0f;
        this.bCol = 0.5f;
        this.alpha = 0.8f;

        this.xd = (random.nextDouble() - 0.5) * 0.2;
        this.yd = (random.nextDouble() - 0.5) * 0.2;
        this.zd = (random.nextDouble() - 0.5) * 0.2;
    }

    @Override
    public void tick()
    {
        super.tick();
        this.setSpriteFromAge(sprites);

        float progress = (float) this.age / this.lifetime;

        Vec3 currentPos = new Vec3(this.x, this.y, this.z);
        Vec3 toCenter = centerPos.subtract(currentPos).normalize();

        float pullStrength = 0.05f + (progress * 0.15f);

        this.xd += toCenter.x * pullStrength;
        this.yd += toCenter.y * pullStrength;
        this.zd += toCenter.z * pullStrength;

        this.xd *= 0.85;
        this.yd *= 0.85;
        this.zd *= 0.85;

        if(progress > 0.5f)
        {
            this.quadSize = this.baseSize * (1.0f - ((progress - 0.5f) * 2.0f));
        }
    }

    @Override
    public @NotNull ParticleRenderType getRenderType()
    {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

}