package com.linnett.hidden_caves.common.level.biome;

import com.b04ka.cavelib.biome.CaveBiomeVisuals;
import com.b04ka.cavelib.deprecated.BiomeGenerationConfig;
import com.b04ka.cavelib.deprecated.BiomeGenerationNoiseCondition;
import com.b04ka.cavelib.deprecated.ExpandedBiomes;
import com.b04ka.cavelib.sufrace.CaveSurfaceRules;
import com.b04ka.cavelib.sufrace.SurfaceRuleConditionRegistry;
import com.linnett.hidden_caves.HiddenCaves;
import com.linnett.hidden_caves.common.block.HCBlockRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class UndergroundRiverBiome {
    public static final ResourceKey<Biome> UNDERGROUND_RIVER = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(HiddenCaves.MODID, "underground_river"));


    public static final BiomeGenerationNoiseCondition UNDERGROUND_RIVER_CONDITION = new BiomeGenerationNoiseCondition.Builder()
            .dimensions(LevelStem.OVERWORLD.location().toString()).distanceFromSpawn(400).continentalness(0.6F, 1F).depth(0.2F, 1.5F).build();

    public static SurfaceRules.RuleSource createUndergroundRiverRules() {
        SurfaceRules.RuleSource riverSlate = SurfaceRules.state(HCBlockRegistry.RIVER_SLATE.get().defaultBlockState());
        SurfaceRules.RuleSource marbel = SurfaceRules.state(HCBlockRegistry.MARBLE.get().defaultBlockState());
        SurfaceRules.RuleSource nacer = SurfaceRules.state(HCBlockRegistry.NACRE.get().defaultBlockState());
        SurfaceRules.ConditionSource nacerCondition = SurfaceRuleConditionRegistry.simplexCondition(-0.2F, 0.4F, 40, 6F, 3);
        return SurfaceRules.sequence(CaveSurfaceRules.bedrock(), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, riverSlate), SurfaceRules.ifTrue(nacerCondition, nacer), marbel);
    }

    public static void init() {
        CaveBiomeVisuals.getBuilder().setBiome(UNDERGROUND_RIVER).setAmbientLight(0.1F).build();
        CaveSurfaceRules.addRule(UNDERGROUND_RIVER, createUndergroundRiverRules());
        ExpandedBiomes.addExpandedBiome(UNDERGROUND_RIVER, LevelStem.OVERWORLD);
        BiomeGenerationConfig.addBiome(UNDERGROUND_RIVER, UNDERGROUND_RIVER_CONDITION);
    }
}
