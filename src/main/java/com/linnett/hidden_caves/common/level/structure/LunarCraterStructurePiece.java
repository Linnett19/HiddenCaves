package com.linnett.hidden_caves.common.level.structure;

import com.b04ka.cavelib.misc.ACMath;
import com.b04ka.cavelib.structure.piece.CanyonStructurePiece;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import org.apache.commons.lang3.mutable.MutableBoolean;

public class LunarCraterStructurePiece extends CanyonStructurePiece {

    public LunarCraterStructurePiece(BlockPos chunkCorner, BlockPos holeCenter, int bowlHeight, int bowlRadius) {
        super(chunkCorner, holeCenter, bowlHeight, bowlRadius * 2, Blocks.STONE, Blocks.SANDSTONE);
    }

    @Override
    public void postProcess(WorldGenLevel level, StructureManager featureManager, ChunkGenerator chunkGen, RandomSource random, BoundingBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
        int cornerX = this.chunkCorner.getX();
        int cornerY = this.chunkCorner.getY();
        int cornerZ = this.chunkCorner.getZ();
        BlockPos.MutableBlockPos carve = new BlockPos.MutableBlockPos();
        BlockPos.MutableBlockPos carveAbove = new BlockPos.MutableBlockPos();
        BlockPos.MutableBlockPos carveBelow = new BlockPos.MutableBlockPos();
        carve.set(cornerX, cornerY, cornerZ);
        carveAbove.set(cornerX, cornerY, cornerZ);
        carveBelow.set(cornerX, cornerY, cornerZ);

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                MutableBoolean doFloor = new MutableBoolean(false);
                for (int y = 15; y >= 0; y--) {
                    carve.set(cornerX + x, Mth.clamp(cornerY + y, level.getMinBuildHeight(), level.getMaxBuildHeight()), cornerZ + z);
                    carveAbove.set(carve.getX(), carve.getY() + 1, carve.getZ());

                    if (inCircle(carve) && !checkedGetBlock(level, carve).is(Blocks.BEDROCK)) {
                        checkedSetBlock(level, carve, Blocks.CAVE_AIR.defaultBlockState());
                        carveBelow.set(carve.getX(), carve.getY() - 1, carve.getZ());
                        doFloor.setTrue();
                    }
                }
            }
        }
    }

    private boolean inCircle(BlockPos carve) {
        double plateauHeight = calculatePlateauHeight(carve.getX(), carve.getZ(), 5, false);
        double distToCenterXZ = carve.distToLowCornerSqr(this.holeCenter.getX(), carve.getY(), this.holeCenter.getZ());

        if (carve.getY() < (int) plateauHeight) {
            return false;
        }

        double ceilingNoise = (1.0F + ACMath.sampleNoise2D(carve.getX() + 9000, carve.getZ() - 9000, 120)) * 5
                + (1.0F + ACMath.sampleNoise2D(carve.getX() + 3000, carve.getZ() + 2000, 40)) * 2;
        double wallNoise = 0.95F + ACMath.sampleNoise2D(carve.getX() + 9000, carve.getZ() - 9000, 120) * 0.05F;
        double celingHeightScaled = this.height * 0.6F - ceilingNoise;
        float yDome = (float) Math.pow(Math.abs(this.holeCenter.getY() - carve.getY()) / (float) height, 4);
        double yDist = smoothMin(1F - yDome, 1.0F, 0.2F);

        return distToCenterXZ < yDist * (radius * wallNoise) * radius
                && carve.getY() < this.holeCenter.getY() + celingHeightScaled;
    }

    public static double calculatePlateauHeight(int x, int z, int curvedTop, boolean terrainNoise) {
        int plateauSize = 48;
        float plateauNoise = (0.5F + ACMath.sampleNoise2D(x, z, 100)) * 0.5F;
        double slightTerrainNoise = terrainNoise ?
                ACMath.sampleNoise2D(x + 9000, z - 9000, 150) + ACMath.sampleNoise2D(x + 18000, z + 9000, 60) : 0;

        float step1 = stepFunction(Mth.sqrt(plateauNoise), 5);
        float step2 = stepFunction(Mth.sqrt(plateauNoise), 10);

        return -48 + step1 * plateauSize + step2 * curvedTop + slightTerrainNoise;
    }

    private static float stepFunction(float value, int steps) {
        return Math.round(value * steps) / (float) steps;
    }

    private static double smoothMin(double a, double b, double k) {
        double h = Math.max(k - Math.abs(a - b), 0.0) / k;
        return Math.min(a, b) - h * h * k * 0.25;
    }
}
