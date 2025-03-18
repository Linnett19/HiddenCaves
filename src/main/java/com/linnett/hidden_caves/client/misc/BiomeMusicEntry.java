package com.linnett.hidden_caves.client.misc;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.biome.Biome;

public class BiomeMusicEntry {
    private final Music music;
    private final ResourceKey<Biome> biome;
    private final float playChance;

    public BiomeMusicEntry(ResourceKey<Biome> biome, Holder<SoundEvent> soundEvent, int minDelay, int maxDelay,  float playChance) {
        this.music = new Music(soundEvent, minDelay, maxDelay, false);
        this.biome = biome;
        this.playChance = playChance;
    }

    public Music getMusic() {
        return this.music;
    }

    public ResourceKey<Biome> getBiome() {
        return this.biome;
    }

    public boolean shouldPlay() {
        return RandomSource.create().nextFloat() < this.playChance;
    }

    public static class Builder {
        private final ResourceKey<Biome> biome;
        private Holder<SoundEvent> sound;
        private int minDelay = 6000;
        private int maxDelay = 12000;
        private float playChance = 1.0F;

        public Builder(ResourceKey<Biome> biome) {
            this.biome = biome;
        }

        public Builder sound(Holder<SoundEvent> soundEvent) {
            this.sound = soundEvent;
            return this;
        }

        public Builder minDelay(int min) {
            this.minDelay = min;
            return this;
        }

        public Builder maxDelay(int max) {
            this.maxDelay = max;
            return this;
        }

        public Builder chance(float chance) {
            this.playChance = Mth.clamp(chance, 0.0F, 1.0F);
            return this;
        }

        public BiomeMusicEntry build() {
            if (this.sound == null) {
                throw new IllegalStateException("Sound event cannot be null");
            }
            return new BiomeMusicEntry(this.biome, this.sound, this.minDelay, this.maxDelay, this.playChance);
        }
    }
}
