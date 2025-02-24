package com.linnett.hidden_caves.common.level.features;

import com.linnett.hidden_caves.HiddenCaves;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registries.FEATURE, HiddenCaves.MODID);


    public static final DeferredHolder<Feature<?>, RiverGrassFeature> RIVER_GRASS_FEATURE = FEATURES.register("river_grass", () -> new RiverGrassFeature(NoneFeatureConfiguration.CODEC));
}

