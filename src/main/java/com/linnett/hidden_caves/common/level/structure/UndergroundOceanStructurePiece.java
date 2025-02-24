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
        super(chunkCorner, holeCenter, bowlHeight / 2, (int) (bowlRadius * 1.5), surroundCornerOfLiquid, floor);
    }

    @Override
    public void postProcess(WorldGenLevel level, StructureManager featureManager, ChunkGenerator chunkGen, RandomSource random, BoundingBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
        int cornerX = this.chunkCorner.getX();
        int cornerY = this.chunkCorner.getY();
        int cornerZ = this.chunkCorner.getZ();

        BlockPos.MutableBlockPos carve = new BlockPos.MutableBlockPos();
        BlockPos.MutableBlockPos carveBelow = new BlockPos.MutableBlockPos();

        carve.set(cornerX, cornerY, cornerZ);

        int minY = 25;
        int maxY = 30;
        int waterLevel = 30;

        for (int x = 0; x < 16; ++x) {
            for (int z = 0; z < 16; ++z) {
                MutableBoolean doFloor = new MutableBoolean(false);
                for (int y = 15; y >= 0; --y) {
                    carve.set(cornerX + x, Mth.clamp(cornerY + y, level.getMinBuildHeight(), level.getMaxBuildHeight()), cornerZ + z);

                    if (carve.getY() < minY || carve.getY() > maxY) {
                        continue;
                    }

                    if (carve.getY() < waterLevel) {
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

        for (int x = 0; x < 16; ++x) {
            for (int z = 0; z < 16; ++z) {
                for (int y = minY; y <= maxY; ++y) {
                    carve.set(cornerX + x, y, cornerZ + z);
                    if (this.isNearEdge(carve)) {
                        this.checkedSetBlock(level, carve, Blocks.STONE.defaultBlockState());
                    }
                }
            }
        }
    }

    private boolean isNearEdge(BlockPos.MutableBlockPos carve) {
        return carve.distToLowCornerSqr((double) this.holeCenter.getX(), (double) carve.getY(), (double) this.holeCenter.getZ()) > (this.radius * 1.1);
    }





    private boolean inCircle(BlockPos.MutableBlockPos carve) {
        float pillarNoise = (ACMath.sampleNoise3D(carve.getX(), (int) ((float) carve.getY() * 0.5F), carve.getZ(), 40.0F) + 1.0F) * 0.4F;
        // pillarNoise отвечает за случайные "столбы" в пещере

        float verticalNoise = (ACMath.sampleNoise2D(carve.getX(), carve.getZ(), 20.0F) + 1.0F) * 0.1F -
                (ACMath.smin(ACMath.sampleNoise2D(carve.getX(), carve.getZ(), 10.0F), -0.5F, 0.1F) + 0.5F) * 0.3F;
        // verticalNoise создает вариации по высоте

        double distToCenter = carve.distToLowCornerSqr((double) this.holeCenter.getX(), (double) carve.getY(), (double) this.holeCenter.getZ());
        // distToCenter вычисляет расстояние до центра пещеры

        float f = this.getHeightOf(carve);
        float f1 = (float) Math.pow((double) this.canyonStep(f, 20), 2.0);
        // f1 отвечает за плавность формы

        float rawHeight = (float) Math.abs(this.holeCenter.getY() - carve.getY()) / ((float) this.height * 0.15F);
        // rawHeight регулирует изменение высоты

        float reverseRawHeight = 1.0F - rawHeight;
        double yDist = (double) ACMath.smin((float) Math.pow((double) reverseRawHeight, 0.5F), 1.0F, 0.1F);
        // yDist контролирует уменьшение высоты к краям

        double targetRadius = yDist * (double) ((float) this.radius * pillarNoise * f1) * (double) this.radius;
        // targetRadius вычисляет радиус пещеры

        return distToCenter < targetRadius && rawHeight < 1.0F - verticalNoise;
    }




    private float getHeightOf(BlockPos.MutableBlockPos carve) {
        int halfHeight = this.height / 2;
        float heightFactor = 1.0F - (float) Math.abs(carve.getY() - this.holeCenter.getY()) / (float) (this.height * 0.12F);
        return heightFactor > 0.2F ? heightFactor : 0.0F;
    }

    private void surroundCornerOfLiquid(WorldGenLevel level, BlockPos.MutableBlockPos center) {
        BlockPos.MutableBlockPos offset = new BlockPos.MutableBlockPos();
        Direction[] directions = Direction.values();
        for (Direction dir : directions) {
            offset.set(center);
            offset.move(dir);
            BlockState state = this.checkedGetBlock(level, offset);
            if (state.getFluidState().isEmpty()) {
                this.checkedSetBlock(level, offset, this.surroundCornerOfLiquid.defaultBlockState());
            }
        }
    }


    private float canyonStep(float heightScale, int scaleTo) {
        int clampTo100 = (int) (heightScale * (float) scaleTo * (float) scaleTo);

        return Mth.clamp((float) Math.round((float) clampTo100 / (float) scaleTo) / (float) scaleTo, 0.0F, 1.0F);
    }
}