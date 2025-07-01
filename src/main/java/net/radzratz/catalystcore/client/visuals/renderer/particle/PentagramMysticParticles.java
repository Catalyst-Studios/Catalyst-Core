package net.radzratz.catalystcore.client.visuals.renderer.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.phys.Vec3;
import net.radzratz.catalystcore.common.util.config.CTCEConfig;
import org.jetbrains.annotations.NotNull;

public class PentagramMysticParticles extends TextureSheetParticle
{
    private final SpriteSet sprites;
    private final float rotationSpeed;
    private final Vec3 centerPos;
    private final float baseSize;

    public PentagramMysticParticles(ClientLevel level,
                                    Vec3 centerPos,
                                    double x,
                                    double y,
                                    double z,
                                    SpriteSet sprites)
    {
        super(level, x, y, z);
        this.centerPos = centerPos;
        this.sprites = sprites;

        this.lifetime = 60 + random.nextInt(40);
        this.baseSize = 0.15f + random.nextFloat() * 0.1f;
        this.quadSize = this.baseSize;
        this.rotationSpeed = (random.nextFloat() - 0.5f) * 0.1f;

        this.setSpriteFromAge(sprites);
        this.alpha = 0.1f;

        Vec3 direction = centerPos.subtract(x, y, z).normalize();
        double speed = 0.02 + random.nextDouble() * 0.01;

        this.xd = direction.x * speed + (random.nextDouble() - 0.5) * 0.01;
        this.yd = direction.y * speed + random.nextDouble() * 0.01;
        this.zd = direction.z * speed + (random.nextDouble() - 0.5) * 0.01;
    }

    @Override
    public void tick()
    {
        if(!CTCEConfig.CONFIG.pentagram.enablePentagramParticles.get())
        {
            this.remove();
            return;
        }

        super.tick();

        if(this.age < 10)
        {
            this.alpha = Math.min(0.8f, this.age * 0.08f);
        }

        this.oRoll = this.roll;
        this.roll += this.rotationSpeed;
        this.setSpriteFromAge(sprites);

        float progress = this.age / (float)this.lifetime;

        Vec3 toCenter = centerPos.subtract(x, y, z).normalize().scale(0.01 * (1 - progress));
        this.xd += toCenter.x;
        this.yd += toCenter.y * 0.7;
        this.zd += toCenter.z;

        this.quadSize = this.baseSize * (0.5f + 0.5f * (float)Math.sin(progress * Math.PI));

        if(progress > 0.8f)
        {
            this.alpha = 0.8f * (1 - (progress - 0.8f) / 0.2f);
        }
    }

    @Override
    public @NotNull ParticleRenderType getRenderType()
    {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @SuppressWarnings("unused")
    public static class Provider implements ParticleProvider<SimpleParticleType>
    {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites)
        {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(@NotNull SimpleParticleType type,
                                       @NotNull ClientLevel level,
                                       double x,
                                       double y,
                                       double z,
                                       double xSpeed,
                                       double ySpeed,
                                       double zSpeed)
        {
            Vec3 center = new Vec3(x, y, z);
            return new PentagramMysticParticles(level, center, x, y, z, this.sprites);
        }
    }
}