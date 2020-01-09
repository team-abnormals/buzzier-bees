package com.bagel.buzzierbees.common.blocks.stickyblocks;

import com.bagel.buzzierbees.common.blocks.ModBlocks;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HoneyBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class NewCloverHoneyBlock extends HoneyBlock {
    protected static final VoxelShape shape = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);

    public NewCloverHoneyBlock(Properties p_i225762_1_) {
        super(p_i225762_1_);
    }

    private static boolean func_226937_c_(Entity p_226937_0_) {
        return p_226937_0_ instanceof LivingEntity || p_226937_0_ instanceof AbstractMinecartEntity || p_226937_0_ instanceof TNTEntity || p_226937_0_ instanceof BoatEntity;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState p_220071_1_, IBlockReader p_220071_2_, BlockPos p_220071_3_, ISelectionContext p_220071_4_) {
        return shape;
    }

    public void onEntityCollision(BlockState p_196262_1_, World p_196262_2_, BlockPos p_196262_3_, Entity p_196262_4_) {
        if (this.func_226935_a_(p_196262_3_, p_196262_4_)) {
            this.func_226933_a_(p_196262_4_, p_196262_3_);
            this.func_226938_d_(p_196262_4_);
            this.func_226934_a_(p_196262_2_, p_196262_4_);
        }

        super.onEntityCollision(p_196262_1_, p_196262_2_, p_196262_3_, p_196262_4_);
    }

    private boolean func_226935_a_(BlockPos p_226935_1_, Entity p_226935_2_) {
        if (p_226935_2_.onGround) {
            return false;
        }
        else return true;
        /*} else if (p_226935_2_.func_226278_cu_() > (double)p_226935_1_.getY() + 0.9375D - 1.0E-7D) {
            return false;
        } else if (p_226935_2_.getMotion().y >= -0.08D) {
            return false;
        } else {
            double lvt_3_1_ = Math.abs((double)p_226935_1_.getX() + 0.5D - p_226935_2_.func_226277_ct_());
            double lvt_5_1_ = Math.abs((double)p_226935_1_.getZ() + 0.5D - p_226935_2_.func_226281_cx_());
            double lvt_7_1_ = 0.4375D + (double)(p_226935_2_.getWidth() / 2.0F);
            return lvt_3_1_ + 1.0E-7D > lvt_7_1_ || lvt_5_1_ + 1.0E-7D > lvt_7_1_;
        }*/
    }

    private void func_226933_a_(Entity p_226933_1_, BlockPos p_226933_2_) {
        if (p_226933_1_ instanceof ServerPlayerEntity && p_226933_1_.world.getGameTime() % 20L == 0L) {
            CriteriaTriggers.field_229864_K_.func_227152_a_((ServerPlayerEntity)p_226933_1_, p_226933_1_.world.getBlockState(p_226933_2_));
        }

    }

    private void func_226938_d_(Entity p_226938_1_) {
        Vec3d lvt_2_1_ = p_226938_1_.getMotion();
        if (lvt_2_1_.y < -0.13D) {
            double lvt_3_1_ = -0.05D / lvt_2_1_.y;
            p_226938_1_.setMotion(new Vec3d(lvt_2_1_.x * lvt_3_1_, -0.05D, lvt_2_1_.z * lvt_3_1_));
        } else {
            p_226938_1_.setMotion(new Vec3d(lvt_2_1_.x, -0.05D, lvt_2_1_.z));
        }

        p_226938_1_.fallDistance = 0.0F;
    }

    private void func_226934_a_(World p_226934_1_, Entity p_226934_2_) {
        if (func_226937_c_(p_226934_2_)) {
            if (p_226934_1_.rand.nextInt(5) == 0) {
                p_226934_2_.playSound(SoundEvents.field_226139_eT_, 1.0F, 1.0F);
            }

            if (!p_226934_1_.isRemote && p_226934_1_.rand.nextInt(5) == 0) {
                p_226934_1_.setEntityState(p_226934_2_, (byte)53);
            }
        }

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