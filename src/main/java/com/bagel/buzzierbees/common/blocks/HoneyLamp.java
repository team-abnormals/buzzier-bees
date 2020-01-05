package com.bagel.buzzierbees.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.EndRodBlock;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class HoneyLamp extends EndRodBlock {
    protected static final VoxelShape HONEY_LAMP_VERTICAL_AABB = Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D);
    protected static final VoxelShape HONEY_LAMP_NS_AABB = Block.makeCuboidShape(5.0D, 5.0D, 0.0D, 11.0D, 11.0D, 16.0D);
    protected static final VoxelShape HONEY_LAMP_EW_AABB = Block.makeCuboidShape(0.0D, 5.0D, 5.0D, 16.0D, 11.0D, 11.0D);

    protected HoneyLamp(Properties p_i48404_1_) {
        super(p_i48404_1_);
    }

    public VoxelShape getShape(BlockState blockState, IBlockReader blockReader, BlockPos blockPos, ISelectionContext selectionContext) {
        switch(((Direction)blockState.get(FACING)).getAxis()) {
            case X:
            default:
                return HONEY_LAMP_EW_AABB;
            case Z:
                return HONEY_LAMP_NS_AABB;
            case Y:
                return HONEY_LAMP_VERTICAL_AABB;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState blockState, World worldIn, BlockPos blockPos, Random random) {
        Direction lvt_5_1_ = (Direction)blockState.get(FACING);
        double lvt_6_1_ = (double)blockPos.getX() + 0.55D - (double)(random.nextFloat() * 0.1F);
        double lvt_8_1_ = (double)blockPos.getY() + 0.55D - (double)(random.nextFloat() * 0.1F);
        double lvt_10_1_ = (double)blockPos.getZ() + 0.55D - (double)(random.nextFloat() * 0.1F);
        double lvt_12_1_ = (double)(0.4F - (random.nextFloat() + random.nextFloat()) * 0.4F);
        if (random.nextInt(5) == 0) {
            worldIn.addParticle(ParticleTypes.field_229429_ai_, lvt_6_1_ + (double)lvt_5_1_.getXOffset() * lvt_12_1_, lvt_8_1_ + (double)lvt_5_1_.getYOffset() * lvt_12_1_, lvt_10_1_ + (double)lvt_5_1_.getZOffset() * lvt_12_1_, random.nextGaussian() * 0.005D, random.nextGaussian() * 0.005D, random.nextGaussian() * 0.005D);
        }

    }
}
