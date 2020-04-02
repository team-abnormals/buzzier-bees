package com.bagel.buzzierbees.common.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.SlimeBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class NewSlimeBlock extends SlimeBlock {
    public NewSlimeBlock(Properties properties) {
        super(properties);
    }

    /*public void onFallenUpon(World world, BlockPos pos, Entity entity, float p_180658_4_) {
        if (entity.isSuppressingBounce()) {
            super.onFallenUpon(world, pos, entity, p_180658_4_);
        } else {
        	entity.onLivingFall(p_180658_4_, 0.0F);
        }

    }*/

    public void onLanded(IBlockReader p_176216_1_, Entity entity) {
        if (entity.isSuppressingBounce()) {
            super.onLanded(p_176216_1_, entity);
        } else {
            this.func_226946_a_(entity);
        }

    }

    private void func_226946_a_(Entity entity) {
        Vec3d lvt_2_1_ = entity.getMotion();
        if (lvt_2_1_.y < 0.0D) {
            double lvt_3_1_ = entity instanceof LivingEntity ? 1.0D : 0.8D;
            entity.setMotion(lvt_2_1_.x, -lvt_2_1_.y * lvt_3_1_, lvt_2_1_.z);
        }

    }

    public void onEntityWalk(World world, BlockPos pos, Entity entity) {
        double lvt_4_1_ = Math.abs(entity.getMotion().y);
        if (lvt_4_1_ < 0.1D && !entity.isSteppingCarefully()) {
            double lvt_6_1_ = 0.4D + lvt_4_1_ * 0.2D;
            entity.setMotion(entity.getMotion().mul(lvt_6_1_, 1.0D, lvt_6_1_));
        }

        super.onEntityWalk(world, pos, entity);
    }

    @Override
    public boolean isSlimeBlock(BlockState state) {
        return true;
    }

    @Override
    public boolean isStickyBlock(BlockState state) {
        return true;
    }

    @Override
    public boolean canStickTo(BlockState state, BlockState other) {
        //if (state.getBlock() == ModBlocks.HONEY_BLOCK.get() && other.getBlock() == ModBlocks.SLIME_BLOCK.get())         return false;
        //if (state.getBlock() == ModBlocks.SLIME_BLOCK.get() && other.getBlock() == ModBlocks.HONEY_BLOCK.get())         return false;
        //if (state.getBlock() == ModBlocks.CLOVER_HONEY_BLOCK.get() && other.getBlock() == ModBlocks.SLIME_BLOCK.get())  return false;
        //if (state.getBlock() == ModBlocks.SLIME_BLOCK.get() && other.getBlock() == ModBlocks.CLOVER_HONEY_BLOCK.get())  return false;
        return state.isStickyBlock() || other.isStickyBlock();
    }
}
