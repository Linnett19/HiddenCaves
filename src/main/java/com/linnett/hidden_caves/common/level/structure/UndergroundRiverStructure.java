package com.linnett.hidden_caves.common.level.structure;

import com.b04ka.cavelib.structure.AbstractCaveGenerationStructure;
import com.b04ka.cavelib.structure.piece.CanyonStructurePiece;
import com.b04ka.cavelib.structure.piece.UndergroundLakeStructurePiece;
import com.linnett.hidden_caves.common.block.HCBlockRegistry;
import com.linnett.hidden_caves.common.level.biome.UndergroundRiverBiome;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.material.Fluids;

public class UndergroundRiverStructure extends AbstractCaveGenerationStructure {
    public static final MapCodec<UndergroundRiverStructure> CODEC = simpleCodec((settings) -> new UndergroundRiverStructure(settings));

    protected UndergroundRiverStructure(Structure.StructureSettings settings) {
        super(settings, UndergroundRiverBiome.UNDERGROUND_RIVER);
    }

    @Override
    protected StructurePiece createPiece(BlockPos offset, BlockPos center, int heightBlocks, int widthBlocks, RandomState randomState) {
        return new UndergroundLakeStructurePiece(offset, center, heightBlocks, widthBlocks,
                UndergroundRiverBiome.UNDERGROUND_RIVER,
                HCBlockRegistry.MARBLE.get(),
                HCBlockRegistry.RIVER_SLATE.get(),
                Blocks.WATER,
                Fluids.WATER);
    }


    @Override
    public int getGenerateYHeight(WorldgenRandom random, int x, int y) {
        return 0;
    }

    @Override
    public int getWidthRadius(WorldgenRandom random) {
        return 100;
    }

    @Override
    public int getHeightRadius(WorldgenRandom random, int seaLevel) {
        return 70;
    }

    @Override
    public StructureType<?> type() {
        return HCStructureRegistry.UNDERGROUND_RIVER_STRUCTURE.get();
    }
}