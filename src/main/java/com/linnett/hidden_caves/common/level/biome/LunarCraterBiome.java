package com.linnett.hidden_caves.common.level.biome;

import com.b04ka.cavelib.biome.CaveBiomeVisuals;
import com.b04ka.cavelib.deprecated.BiomeGenerationConfig;
import com.b04ka.cavelib.deprecated.BiomeGenerationNoiseCondition;
import com.b04ka.cavelib.deprecated.ExpandedBiomes;
import com.b04ka.cavelib.sufrace.CaveSurfaceRules;
import com.linnett.hidden_caves.HiddenCaves;
import com.linnett.hidden_caves.common.block.HCBlockRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.phys.Vec3;

import static com.b04ka.cavelib.sufrace.CaveSurfaceRules.bedrock;

public class LunarCraterBiome {
    public static final ResourceKey<Biome> LUNAR_CRATER = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(HiddenCaves.MODID, "lunar_crater"));

    public static final BiomeGenerationNoiseCondition LUNAR_CRATER_CONDITION = new BiomeGenerationNoiseCondition.Builder()
            .dimensions(LevelStem.OVERWORLD.location().toString()).distanceFromSpawn(400).continentalness(0.6F, 1F).depth(0.2F, 1.5F).build();

    public static SurfaceRules.RuleSource createLunarCraterRules() {
        SurfaceRules.RuleSource moon = SurfaceRules.state(HCBlockRegistry.MOON_STONE.get().defaultBlockState());
        return SurfaceRules.sequence(bedrock(), moon);
    }

    private static final Vec3 MOON_LIGHT_COLOR = new Vec3(0.5059, 0.4667, 0.5843);

    public static void init() {
        CaveBiomeVisuals.getBuilder()
                .setBiome(LUNAR_CRATER)
                .setLightColorOverride(MOON_LIGHT_COLOR)
                .setSkyOverride(1F)
                .setFogNearness(0.5F)
                .build();

        CaveSurfaceRules.addRule(LUNAR_CRATER, createLunarCraterRules());
        ExpandedBiomes.addExpandedBiome(LUNAR_CRATER, LevelStem.OVERWORLD);
        BiomeGenerationConfig.addBiome(LUNAR_CRATER, LUNAR_CRATER_CONDITION);
    }

}
