package com.linnett.hidden_caves.common.particle.custom;

import com.linnett.hidden_caves.common.particle.ParticleRegistry;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;

import java.util.Random;

public class MoonCaveAmbient extends TextureSheetParticle {
    protected MoonCaveAmbient(ClientLevel level, double x, double y, double z, SpriteSet sprites) {
        super(level, x, y, z, 0.0D, 0.0D, 0.0D);
        this.alpha = 0.0F; // Полностью невидимый
        this.lifetime = 1; // Живёт 1 тик
        this.pickSprite(sprites); // Выбираем спрайт (даже если он невидимый)
    }

    @Override
    public void tick() {
        if (this.age == 0) {
            Random random = new Random();
            SimpleParticleType particleToSpawn = random.nextBoolean() ? ParticleRegistry.LIGHT_DUST.get() : ParticleRegistry.MOON_DROP.get();

            for (int i = 0; i < 1; i++) {
                this.level.addParticle(particleToSpawn, this.x, this.y, this.z, 0, 0.01, 0);
            }
        }
        this.remove();
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.NO_RENDER; // Не рендерится
    }

    public static class Factory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Factory(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new MoonCaveAmbient(world, x, y, z, sprites);
        }
    }
}
