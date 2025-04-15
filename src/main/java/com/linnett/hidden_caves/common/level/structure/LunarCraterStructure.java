package com.linnett.hidden_caves.common.level.structure;

import com.b04ka.cavelib.structure.AbstractCaveGenerationStructure;
import com.b04ka.cavelib.structure.piece.CanyonStructurePiece;
import com.b04ka.cavelib.structure.piece.CavernStructurePiece;
import com.linnett.hidden_caves.common.block.HCBlockRegistry;
import com.linnett.hidden_caves.common.level.biome.LunarCraterBiome;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureType;

public class LunarCraterStructure extends AbstractCaveGenerationStructure {
    public static final MapCodec<LunarCraterStructure> CODEC = simpleCodec((settings) -> new LunarCraterStructure(settings));
    protected LunarCraterStructure(Structure.StructureSettings settings) {
        super(settings, LunarCraterBiome.LUNAR_CRATER);
    }

    private static final int BOWL_WIDTH_RADIUS = 100;
    private static final int BOWL_HEIGHT_RADIUS = 60;
    public static final int BOWL_Y_CENTER = -10;

    @Override
    protected StructurePiece createPiece(BlockPos offset, BlockPos center, int heightBlocks, int widthBlocks, RandomState randomState) {
        return new LunarCraterStructurePiece(offset, center, heightBlocks, widthBlocks);
    }

    @Override
    public int getGenerateYHeight(WorldgenRandom random, int x, int y) {
        return BOWL_Y_CENTER;
    }

    @Override
    public int getWidthRadius(WorldgenRandom random) {
        return 100;
    }

    @Override
    public int getHeightRadius(WorldgenRandom random, int seaLevel) {
        return 90;
    }

    @Override
    public StructureType<?> type() {
        return HCStructureRegistry.LUNAR_CRATER_STRUCTURE.get();
    }
}