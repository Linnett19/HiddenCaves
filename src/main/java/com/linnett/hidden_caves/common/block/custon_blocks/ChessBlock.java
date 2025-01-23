package com.linnett.hidden_caves.common.block.custon_blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ChessBlock extends Block {

    public ChessBlock(Properties properties) {
        super(properties);
    }

    private static final VoxelShape SHAPE_POST = Block.box(4.0, 0.0, 4.0, 12.0, 12.0, 12.0);
    private static final VoxelShape SHAPE = Shapes.or(SHAPE_POST);

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return SHAPE;
    }
}
