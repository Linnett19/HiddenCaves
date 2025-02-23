package com.linnett.hidden_caves.common.block.custon_blocks;

import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import com.mojang.serialization.MapCodec;

public class RiverGrassBlock extends BushBlock {
    public static final MapCodec<RiverGrassBlock> CODEC = simpleCodec(RiverGrassBlock::new);

    public RiverGrassBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public MapCodec<? extends BushBlock> codec() {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState state, net.minecraft.world.level.BlockGetter level, net.minecraft.core.BlockPos pos, CollisionContext context) {
        return Shapes.block();
    }
}

