package com.bagel.buzzierbees.common.blocks.stickyblocks;

import com.bagel.buzzierbees.common.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HoneyBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class NewCloverHoneyBlock extends HoneyBlock {
    protected static final VoxelShape shape = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);

    public NewCloverHoneyBlock(Properties p_i225762_1_) {
        super(p_i225762_1_);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState p_220071_1_, IBlockReader p_220071_2_, BlockPos p_220071_3_, ISelectionContext p_220071_4_) {
        return shape;
    }

    @Override
    public boolean isSlimeBlock(BlockState state) {
        return false;
    }

    @Override
    public boolean isStickyBlock(BlockState state) {
        return true;
    }

    @Override
    public boolean canStickTo(BlockState state, BlockState other) {
        if (state.getBlock() == ModBlocks.CLOVER_HONEY_BLOCK && other.getBlock() == ModBlocks.SLIME_BLOCK)  return false;
        if (state.getBlock() == ModBlocks.SLIME_BLOCK && other.getBlock() == ModBlocks.CLOVER_HONEY_BLOCK)  return false;
        return state.isStickyBlock() || other.isStickyBlock();
    }

    //TODO: Honey's sliding and sticking abilities
}