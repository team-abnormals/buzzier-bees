package com.bagel.buzzierbees.common.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class CloverBlock extends FlowerBlock implements IGrowable {
    protected static final VoxelShape SHAPE = net.minecraft.block.Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);

    public CloverBlock(Effect p_i49984_1_, Properties p_i49984_3_) {
        super(p_i49984_1_, 8, p_i49984_3_);
    }

    @Override
    public boolean canGrow(IBlockReader iBlockReader, BlockPos blockPos, BlockState blockState, boolean b) {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World world, Random random, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    public void func_225535_a_(ServerWorld serverWorld, Random random, BlockPos blockPos, BlockState blockState) {
        //CloverBlock cloverBlock = (CloverBlock)ModBlocks.CLOVER;
        //TEMP IMPLEMENTATION
        spawnAsEntity(serverWorld, blockPos, new ItemStack(this));
    }

    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        return SHAPE;
    }

    public OffsetType getOffsetType() {
        return OffsetType.NONE;
    }

}
