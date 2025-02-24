package com.linnett.hidden_caves.common.level.features;

import com.linnett.hidden_caves.common.block.HCBlockRegistry;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class RiverGrassFeature extends Feature<NoneFeatureConfiguration> {

    public RiverGrassFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        RandomSource randomsource = context.random();
        WorldGenLevel level = context.level();
        BlockPos below = context.origin();
        if (!level.getBlockState(below.below()).isSolid()) {
            return false;
        }
        BlockPos.MutableBlockPos pillar = new BlockPos.MutableBlockPos();
        pillar.set(below);
        for (int i = 0; i < 3 + randomsource.nextInt(3); i++) {
            BlockPos offset = below.offset(randomsource.nextInt(8) - 4, 1, randomsource.nextInt(8) - 4);
            while (level.isEmptyBlock(offset) && offset.getY() > level.getMinBuildHeight()) {
                offset = offset.below();
            }
            if (level.getBlockState(offset).isFaceSturdy(level, offset, Direction.UP) && level.getBlockState(offset).is(HCBlockRegistry.LICHEN.get()) && level.isEmptyBlock(offset.above())) {
                if(randomsource.nextFloat() < 0.15F){
                    level.setBlock(offset.above(), HCBlockRegistry.RIVER_GRASS.get().defaultBlockState(), 3);
                }
            }
        }
        return true;
    }
}
