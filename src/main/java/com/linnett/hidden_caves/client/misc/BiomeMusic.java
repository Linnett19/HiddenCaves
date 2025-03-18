package com.linnett.hidden_caves.client.misc;

import com.linnett.hidden_caves.common.level.biome.LunarCraterBiome;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.biome.Biome;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BiomeMusic {
    private static final BiomeMusic instance = new BiomeMusic();
    private final List<BiomeMusicEntry> musicEntries = new ArrayList<>();

    public static BiomeMusic getInstance() {
        return instance;
    }

    public BiomeMusic registerMusic(BiomeMusicEntry entry) {
        this.musicEntries.add(entry);
        return this;
    }

    public static void initAll() {
        BiomeMusic biomeMusic = BiomeMusic.getInstance();
        biomeMusic.registerMusic(new BiomeMusicEntry.Builder(LunarCraterBiome.LUNAR_CRATER)
                .sound(SoundEvents.MUSIC_DISC_13)
                .chance(0.3F)
                .build()
        );
    }

    public List<BiomeMusicEntry> getAllMusic() {
        return this.musicEntries;
    }

    @Nullable
    public BiomeMusicEntry getMusicForBiome(Holder<Biome> biome) {
        for (BiomeMusicEntry entry : this.musicEntries) {
            if (biome.is(entry.getBiome())) {
                return entry;
            }
        }
        return null;
    }
}