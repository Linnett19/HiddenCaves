package com.linnett.hidden_caves.common.particle.custom;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

public class MoonDrop extends TextureSheetParticle {

    private boolean fromRoof;
    private float rotSpeed;
    private boolean hasBounced = false;

    public MoonDrop(ClientLevel world, double x, double y, double z, boolean fromRoof, SpriteSet sprites) {
        super(world, x, y, z);
        this.pickSprite(sprites);
        this.fromRoof = fromRoof;
        this.gravity = fromRoof ? 0.001F : -0.001F;
        this.lifetime = 200 + random.nextInt(200);
        this.quadSize = 0.3F;
        this.rotSpeed = ((float) Math.random() - 0.5F) * 0.2F;
        this.roll = (float) Math.random() * ((float) Math.PI * 2F);
        this.xd = 0;
        this.yd = 0.1F;
        this.zd = 0;
    }

    @Override
    public void tick() {
        super.tick();
        this.oRoll = this.roll;
        this.roll += (float) Math.PI * this.rotSpeed * 2.0F;

        if (this.onGround) {
            if (!hasBounced) {
                this.yd = 0.1F;
                this.hasBounced = true;
                this.lifetime = this.age + 10;
            } else {
                this.remove();
            }
        }

        this.move(this.xd, this.yd, this.zd);
        this.yd -= (double) this.gravity;
    }


    private boolean hasCollided() {
        return this.yo != this.y && this.onGround;
    }

    @Override
    public void render(VertexConsumer consumer, Camera camera, float partialTick) {
        super.render(consumer, camera, partialTick);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Factory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Factory(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Override
        public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            boolean fromRoof = worldIn.random.nextBoolean();
            MoonDrop particle = new MoonDrop(worldIn, x, y, z, fromRoof, spriteSet);
            return particle;
        }
    }
}


