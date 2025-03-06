package com.linnett.hidden_caves.common.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;

import java.util.concurrent.ThreadLocalRandom;

public class LightDust extends TextureSheetParticle {
    private final SpriteSet sprites;
    private final int windType;

    protected LightDust(ClientLevel level, double x, double y, double z, int windType, SpriteSet sprites) {
        super(level, x, y, z, 0.0D, 0.0D, 0.0D);
        this.friction = 0.98F;
        this.sprites = sprites;
        this.windType = windType;

        this.xd = (random.nextFloat() - 0.5F) * 0.1F;
        this.yd = 0.05F;
        this.zd = (random.nextFloat() - 0.5F) * 0.1F;

        this.quadSize *= (0.5F + random.nextFloat() * 0.6F);

        this.lifetime = 100 + random.nextInt(50);

        this.alpha = 1.0F;

        // Яркий неоновый цвет, например, зеленый для эффекта свечения
        this.setColor(1.0F, 1.0F, 1.0F); // Зеленый неоновый цвет (можно изменить на другой)

        this.setSpriteFromAge(sprites);
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteFromAge(sprites);

        float lifeProgress = (float) this.age / this.lifetime;

        // Плавное уменьшение альфа-канала для исчезновения
        this.alpha = Mth.clamp(1.0F - lifeProgress, 0.0F, 1.0F);

        this.xd += (random.nextFloat() - 0.5F) * 0.02F;
        this.zd += (random.nextFloat() - 0.5F) * 0.02F;
        this.yd = 0.05F + lifeProgress * 0.05F;

        if (this.alpha <= 0.01F) {
            this.remove();
        }
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    // Можно добавить метод для освещенности, имитируя светящиеся частицы
    @Override
    public int getLightColor(float tint) {
        int lightValue = super.getLightColor(tint);
        return lightValue | 240; // Добавляем яркость, чтобы имитировать светящийся эффект
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Provider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Override
        public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            int windType = ThreadLocalRandom.current().nextInt(3);
            return new LightDust(worldIn, x, y, z, windType, spriteSet);
        }
    }
}

