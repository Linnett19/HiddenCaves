package com.linnett.hidden_caves.common.level.structure;

import com.b04ka.cavelib.misc.ACMath;
import com.b04ka.cavelib.structure.piece.CanyonStructurePiece;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import org.apache.commons.lang3.mutable.MutableBoolean;

public class UndergroundOceanStructurePiece extends CanyonStructurePiece {
    public UndergroundOceanStructurePiece(BlockPos chunkCorner, BlockPos holeCenter, int bowlHeight, int bowlRadius, Block surroundCornerOfLiquid, Block floor) {
        super(chunkCorner, holeCenter, bowlHeight, bowlRadius, surroundCornerOfLiquid, floor);
    }

    @Override
    public void postProcess(WorldGenLevel level, StructureManager featureManager, ChunkGenerator chunkGen, RandomSource random, BoundingBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
        int cornerX = this.chunkCorner.getX();
        int cornerY = this.chunkCorner.getY();
        int cornerZ = this.chunkCorner.getZ();
        BlockPos.MutableBlockPos carve = new BlockPos.MutableBlockPos();
        BlockPos.MutableBlockPos carveBelow = new BlockPos.MutableBlockPos();
        carve.set(cornerX, cornerY, cornerZ);
        int minY = 30;
        int maxY = 60;
        int waterLevel = 40;

        if (maxY > waterLevel) {
            maxY = waterLevel - 20;
        }

        for (int x = 0; x < 16; ++x) {
            for (int z = 0; z < 16; ++z) {
                MutableBoolean doFloor = new MutableBoolean(false);
                for (int y = 15; y >= 0; --y) {
                    carve.set(cornerX + x, Mth.clamp(cornerY + y, level.getMinBuildHeight(), level.getMaxBuildHeight()), cornerZ + z);

                    if (carve.getY() < minY || carve.getY() > maxY) {
                        continue;
                    }

                    if (this.inCircle(carve) && !this.checkedGetBlock(level, carve).is(Blocks.BEDROCK)) {
                        this.checkedSetBlock(level, carve, Blocks.CAVE_AIR.defaultBlockState());
                        this.surroundCornerOfLiquid(level, carve);
                        carveBelow.set(carve.getX(), carve.getY() - 1, carve.getZ());
                        doFloor.setTrue();
                    } else if (doFloor.isTrue()) {
                        break;
                    }
                }
                if (doFloor.isTrue() && !this.checkedGetBlock(level, carveBelow).isAir()) {
                    this.decorateFloor(level, random, carveBelow);
                    doFloor.setFalse();
                }
            }
        }
    }


    private void surroundCornerOfLiquid(WorldGenLevel level, BlockPos.MutableBlockPos center) {
        BlockPos.MutableBlockPos offset = new BlockPos.MutableBlockPos();
        Direction[] var4 = Direction.values();
        int var5 = var4.length;
        for (int var6 = 0; var6 < var5; ++var6) {
            Direction dir = var4[var6];
            offset.set(center);
            offset.move(dir);
            BlockState state = this.checkedGetBlock(level, offset);
            if (!state.getFluidState().isEmpty()) {
                this.checkedSetBlock(level, offset, this.surroundCornerOfLiquid.defaultBlockState());
            }
        }
    }

    private boolean inCircle(BlockPos.MutableBlockPos carve) {
        float pillarNoise = (ACMath.sampleNoise3D(carve.getX(), (int) ((float) carve.getY() * 0.2F), carve.getZ(), 15.0F) + 1.0F) * 0.4F;

        float verticalNoise = (ACMath.sampleNoise2D(carve.getX(), carve.getZ(), 30.0F) + 1.0F) * 0.1F -
                (ACMath.smin(ACMath.sampleNoise2D(carve.getX(), carve.getZ(), 10.0F), -0.5F, 0.1F) + 0.5F) * 0.2F;

        double distToCenter = carve.distToLowCornerSqr((double) this.holeCenter.getX(), (double) carve.getY(), (double) this.holeCenter.getZ());

        float f = this.getHeightOf(carve);
        float f1 = (float) Math.pow((double) this.canyonStep(f, 10), 1.2);

        float rawHeight = (float) Math.abs(this.holeCenter.getY() - carve.getY()) / ((float) this.height * 0.2F);

        float reverseRawHeight = 1.0F - rawHeight;
        double yDist = (double) ACMath.smin((float) Math.pow((double) reverseRawHeight, 0.5F), 1.0F, 0.1F);

        double targetRadius = yDist * (double) ((float) this.radius * pillarNoise * f1) * (double) this.radius;

        return distToCenter < targetRadius && rawHeight < 1.0F - verticalNoise;
    }

    private float getHeightOf(BlockPos.MutableBlockPos carve) {
        int halfHeight = this.height / 2;

        float heightFactor = 1.0F - (float) Math.abs(carve.getY() - this.holeCenter.getY()) / (float) (this.height * 0.12F);

        return heightFactor > 0.2F ? heightFactor : 0.0F;
    }

    private float canyonStep(float heightScale, int scaleTo) {
        int clampTo100 = (int) (heightScale * (float) scaleTo * (float) scaleTo);

        return Mth.clamp((float) Math.round((float) clampTo100 / (float) scaleTo) / (float) scaleTo, 0.0F, 1.0F);
    }
}
