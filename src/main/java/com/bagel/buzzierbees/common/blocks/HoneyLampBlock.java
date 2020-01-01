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

public class HoneyLampBlock extends EndRodBlock {
    protected static final VoxelShape HONEY_LAMP_VERTICAL_AABB = Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D);
    protected static final VoxelShape HONEY_LAMP_NS_AABB = Block.makeCuboidShape(5.0D, 5.0D, 0.0D, 11.0D, 11.0D, 16.0D);
    protected static final VoxelShape HONEY_LAMP_EW_AABB = Block.makeCuboidShape(0.0D, 5.0D, 5.0D, 16.0D, 11.0D, 11.0D);

    protected HoneyLampBlock(Properties p_i48404_1_) {
        super(p_i48404_1_);
    }

    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        switch(((Direction)p_220053_1_.get(FACING)).getAxis()) {
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
    public void animateTick(BlockState p_180655_1_, World p_180655_2_, BlockPos p_180655_3_, Random p_180655_4_) {
        Direction lvt_5_1_ = (Direction)p_180655_1_.get(FACING);
        double lvt_6_1_ = (double)p_180655_3_.getX() + 0.55D - (double)(p_180655_4_.nextFloat() * 0.1F);
        double lvt_8_1_ = (double)p_180655_3_.getY() + 0.55D - (double)(p_180655_4_.nextFloat() * 0.1F);
        double lvt_10_1_ = (double)p_180655_3_.getZ() + 0.55D - (double)(p_180655_4_.nextFloat() * 0.1F);
        double lvt_12_1_ = (double)(0.4F - (p_180655_4_.nextFloat() + p_180655_4_.nextFloat()) * 0.4F);
        if (p_180655_4_.nextInt(5) == 0) {
            p_180655_2_.addParticle(ParticleTypes.field_229429_ai_, lvt_6_1_ + (double)lvt_5_1_.getXOffset() * lvt_12_1_, lvt_8_1_ + (double)lvt_5_1_.getYOffset() * lvt_12_1_, lvt_10_1_ + (double)lvt_5_1_.getZOffset() * lvt_12_1_, p_180655_4_.nextGaussian() * 0.005D, p_180655_4_.nextGaussian() * 0.005D, p_180655_4_.nextGaussian() * 0.005D);
        }

    }
}
