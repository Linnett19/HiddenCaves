package com.linnett.hidden_caves.common.level.biome;

import com.b04ka.cavelib.deprecated.BiomeGenerationConfig;
import com.b04ka.cavelib.deprecated.BiomeGenerationNoiseCondition;
import com.linnett.hidden_caves.HiddenCaves;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.dimension.LevelStem;

public class UndergroundRiverBiome {
    public static final ResourceKey<Biome> UNDERGROUND_RIVER = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(HiddenCaves.MODID, "underground_river"));

    public static final BiomeGenerationNoiseCondition UNDERGROUND_RIVER_CONDITION = new BiomeGenerationNoiseCondition.Builder()
            .dimensions(LevelStem.OVERWORLD.location().toString()).distanceFromSpawn(400).continentalness(0.6F, 1F).depth(0.2F, 1F).build();

    public static void init(){
        BiomeGenerationConfig.addBiome(UNDERGROUND_RIVER, BiomeGenerationConfig.PRIMORDIAL_CAVES_CONDITION);
    }
}
