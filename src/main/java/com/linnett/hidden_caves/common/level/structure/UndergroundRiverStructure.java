package com.linnett.hidden_caves.common.level.structure;

import com.b04ka.cavelib.structure.AbstractCaveGenerationStructure;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureType;

public class UndergroundRiverStructure extends AbstractCaveGenerationStructure {
    protected UndergroundRiverStructure(Structure.StructureSettings settings, ResourceKey<Biome> matchingBiome) {
        super(settings, matchingBiome);
    }

    @Override
    protected StructurePiece createPiece(BlockPos offset, BlockPos center, int heightBlocks, int widthBlocks, RandomState randomState) {
        return null;
    }

    @Override
    public int getGenerateYHeight(WorldgenRandom random, int x, int y) {
        return 0;
    }

    @Override
    public int getWidthRadius(WorldgenRandom random) {
        return 0;
    }

    @Override
    public int getHeightRadius(WorldgenRandom random, int seaLevel) {
        return 0;
    }

    @Override
    public StructureType<?> type() {
        return null;
    }
}
