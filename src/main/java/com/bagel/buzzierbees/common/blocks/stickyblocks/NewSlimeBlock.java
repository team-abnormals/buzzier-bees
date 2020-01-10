package com.bagel.buzzierbees.common.blocks.stickyblocks;

import com.bagel.buzzierbees.common.blocks.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlimeBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class NewSlimeBlock extends SlimeBlock {
    public NewSlimeBlock(Properties p_i48330_1_) {
        super(p_i48330_1_);
    }

    public void onFallenUpon(World p_180658_1_, BlockPos p_180658_2_, Entity p_180658_3_, float p_180658_4_) {
        if (p_180658_3_.func_226272_bl_()) {
            super.onFallenUpon(p_180658_1_, p_180658_2_, p_180658_3_, p_180658_4_);
        } else {
            p_180658_3_.func_225503_b_(p_180658_4_, 0.0F);
        }

    }

    public void onLanded(IBlockReader p_176216_1_, Entity p_176216_2_) {
        if (p_176216_2_.func_226272_bl_()) {
            super.onLanded(p_176216_1_, p_176216_2_);
        } else {
            this.func_226946_a_(p_176216_2_);
        }

    }

    private void func_226946_a_(Entity p_226946_1_) {
        Vec3d lvt_2_1_ = p_226946_1_.getMotion();
        if (lvt_2_1_.y < 0.0D) {
            double lvt_3_1_ = p_226946_1_ instanceof LivingEntity ? 1.0D : 0.8D;
            p_226946_1_.setMotion(lvt_2_1_.x, -lvt_2_1_.y * lvt_3_1_, lvt_2_1_.z);
        }

    }

    public void onEntityWalk(World p_176199_1_, BlockPos p_176199_2_, Entity p_176199_3_) {
        double lvt_4_1_ = Math.abs(p_176199_3_.getMotion().y);
        if (lvt_4_1_ < 0.1D && !p_176199_3_.func_226271_bk_()) {
            double lvt_6_1_ = 0.4D + lvt_4_1_ * 0.2D;
            p_176199_3_.setMotion(p_176199_3_.getMotion().mul(lvt_6_1_, 1.0D, lvt_6_1_));
        }

        super.onEntityWalk(p_176199_1_, p_176199_2_, p_176199_3_);
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
        if (state.getBlock() == ModBlocks.HONEY_BLOCK && other.getBlock() == ModBlocks.SLIME_BLOCK)         return false;
        if (state.getBlock() == ModBlocks.SLIME_BLOCK && other.getBlock() == ModBlocks.HONEY_BLOCK)         return false;
        if (state.getBlock() == ModBlocks.CLOVER_HONEY_BLOCK && other.getBlock() == ModBlocks.SLIME_BLOCK)  return false;
        if (state.getBlock() == ModBlocks.SLIME_BLOCK && other.getBlock() == ModBlocks.CLOVER_HONEY_BLOCK)  return false;
        return state.isStickyBlock() || other.isStickyBlock();
    }
}
