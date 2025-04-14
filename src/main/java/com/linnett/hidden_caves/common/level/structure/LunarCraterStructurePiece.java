package com.linnett.hidden_caves.common.level.structure;

import com.b04ka.cavelib.misc.ACMath;
import com.b04ka.cavelib.structure.piece.CanyonStructurePiece;
import net.minecraft.core.BlockPos;
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
        super(chunkCorner, holeCenter, bowlHeight, bowlRadius * 2, Blocks.STONE, Blocks.SANDSTONE); // Увеличиваем радиус
    }

    public void postProcess(WorldGenLevel level, StructureManager featureManager, ChunkGenerator chunkGen, RandomSource random, BoundingBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
        int cornerX = this.chunkCorner.getX();
        int cornerY = this.chunkCorner.getY();
        int cornerZ = this.chunkCorner.getZ();
        BlockPos.MutableBlockPos carve = new BlockPos.MutableBlockPos();
        BlockPos.MutableBlockPos carveBelow = new BlockPos.MutableBlockPos();
        carve.set(cornerX, cornerY, cornerZ);

        // Обновленная генерация для большей плавности, убираем случайные столбы
        for (int x = 0; x < 24; x++) { // Увеличиваем ширину пещеры
            for (int z = 0; z < 24; z++) { // Увеличиваем глубину пещеры
                MutableBoolean doFloor = new MutableBoolean(false);
                for (int y = 30; y >= 0; y--) { // Увеличиваем высоту пещеры
                    carve.set(cornerX + x, Mth.clamp(cornerY + y, level.getMinBuildHeight(), level.getMaxBuildHeight()), cornerZ + z);
                    if (inCircle(carve) && !checkedGetBlock(level, carve).is(Blocks.BEDROCK)) {
                        checkedSetBlock(level, carve, Blocks.CAVE_AIR.defaultBlockState());
                        carveBelow.set(carve.getX(), carve.getY() - 1, carve.getZ());
                        doFloor.setTrue();
                    } else if (doFloor.isTrue()) {
                        break;
                    }
                }
                if (doFloor.isTrue() && !checkedGetBlock(level, carveBelow).isAir()) {
                    decorateFloor(level, random, carveBelow);
                    doFloor.setFalse();
                }
            }
        }
    }

    private boolean inCircle(BlockPos.MutableBlockPos carve) {
        float pillarNoise = (ACMath.sampleNoise3D(carve.getX(), (int) (carve.getY() * 0.4F), carve.getZ(), 30) + 1.0F) * 0.5F;
        float verticalNoise = (ACMath.sampleNoise2D(carve.getX(), carve.getZ(), 50) + 1.0F) * 0.15F - (ACMath.smin(ACMath.sampleNoise2D(carve.getX(), carve.getZ(), 20), -0.5F, 0.1F) + 0.5F) * 0.5F;

        double distToCenter = carve.distToLowCornerSqr(this.holeCenter.getX(), carve.getY(), this.holeCenter.getZ());
        float f = getHeightOf(carve);
        float f1 = (float) Math.pow(canyonStep(f, 10), 2.5F);

        float rawHeight = Math.abs(this.holeCenter.getY() - carve.getY()) / (float) (height * 0.5F);
        float reverseRawHeight = 1F - rawHeight;
        double yDist = ACMath.smin((float) Math.pow(reverseRawHeight, 0.4F), 1.0F, 0.1F);

        double targetRadius = (yDist * (radius * pillarNoise * f1) * 0.9F); // ← менше множення

        return distToCenter < targetRadius && rawHeight < 1.0F - verticalNoise;
    }


    private float getHeightOf(BlockPos.MutableBlockPos carve) {
        int halfHeight = this.height / 2;
        if (carve.getY() > this.holeCenter.getY() + halfHeight + 1 || carve.getY() < this.holeCenter.getY() - halfHeight) {
            return 0.0F;
        } else {
            return 1F - ((this.holeCenter.getY() + halfHeight - carve.getY()) / (float) (height * 2));
        }
    }

    private float canyonStep(float heightScale, int scaleTo) {
        int clampTo100 = (int) ((heightScale) * scaleTo * scaleTo);
        return Mth.clamp((float) (Math.round(clampTo100 / (float) scaleTo)) / (float) scaleTo, 0F, 1F);
    }
}


