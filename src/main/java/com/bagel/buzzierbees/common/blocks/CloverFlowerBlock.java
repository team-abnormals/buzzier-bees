package com.bagel.buzzierbees.common.blocks;

import javafx.beans.property.BooleanProperty;
import net.minecraft.block.*;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.potion.Effect;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class CloverFlowerBlock extends FlowerBlock implements IGrowable {

    public CloverFlowerBlock(Effect p_i49984_1_, Properties p_i49984_3_) {
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
        spawnAsEntity(serverWorld, blockPos, new ItemStack(this));
    }

    protected boolean isValidGround(BlockState blockState, IBlockReader reader, BlockPos blockPos) {
        return super.isValidGround(blockState, reader, blockPos);
    }

}
