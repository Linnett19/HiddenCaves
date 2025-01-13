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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.phys.Vec3;

public class UndergroundRiverBiome {
    public static final ResourceKey<Biome> UNDERGROUND_RIVER = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(HiddenCaves.MODID, "underground_river"));


    public static final BiomeGenerationNoiseCondition UNDERGROUND_RIVER_CONDITION = new BiomeGenerationNoiseCondition.Builder()
            .dimensions(LevelStem.OVERWORLD.location().toString()).distanceFromSpawn(400).continentalness(0.6F, 1F).depth(0.2F, 1.5F).build();

    public static SurfaceRules.RuleSource createUndergroundRiverRules() {
        SurfaceRules.RuleSource riverSlate = SurfaceRules.state(HCBlockRegistry.RIVER_SLATE.get().defaultBlockState());
        SurfaceRules.RuleSource marble = SurfaceRules.state(HCBlockRegistry.MARBLE.get().defaultBlockState());
        SurfaceRules.RuleSource nacre = SurfaceRules.state(HCBlockRegistry.NACRE.get().defaultBlockState());
        SurfaceRules.RuleSource dirt = SurfaceRules.state(Blocks.DIRT.defaultBlockState());
        SurfaceRules.RuleSource packedMud = SurfaceRules.state(Blocks.PACKED_MUD.defaultBlockState());

        SurfaceRules.RuleSource dirtOrPackedMud = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.GRAVEL, -0.12D, 0.2D), packedMud),
                dirt
        );

        SurfaceRules.ConditionSource isUnderwater = SurfaceRules.waterBlockCheck(0, 0);

        SurfaceRules.ConditionSource nacreCondition = SurfaceRuleConditionRegistry.simplexCondition(-0.2F, 0.4F, 40, 6F, 3);

        return SurfaceRules.sequence(
                CaveSurfaceRules.bedrock(),

                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, riverSlate),

                SurfaceRules.ifTrue(nacreCondition, nacre), riverSlate, SurfaceRules.ifTrue(isUnderwater, dirtOrPackedMud)
        );
    }


    private static final Vec3 BLUE_LIGHT_COLOR = new Vec3(0.886, 1.0, 0.937);


    public static void init() {
        CaveBiomeVisuals.getBuilder().setBiome(UNDERGROUND_RIVER).setAmbientLight(0.05F).setFogNearness(10F).setSkyOverride(1F).setLightColorOverride(BLUE_LIGHT_COLOR).build();
        CaveSurfaceRules.addRule(UNDERGROUND_RIVER, createUndergroundRiverRules());
        ExpandedBiomes.addExpandedBiome(UNDERGROUND_RIVER, LevelStem.OVERWORLD);
        BiomeGenerationConfig.addBiome(UNDERGROUND_RIVER, UNDERGROUND_RIVER_CONDITION);
    }
}
