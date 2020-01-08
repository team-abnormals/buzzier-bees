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

    /*@Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos blockPos, Entity entity) {
        if (this.func_226935_a_(blockPos, entity)) {
            this.advancementTrigger(entity, blockPos);
            this.slideAndStick(entity);
            this.func_226934_a_(worldIn, entity);
        }

        super.onEntityCollision(state, worldIn, blockPos, entity);
    }

    private boolean func_226935_a_(BlockPos blockPos, Entity entity) {
        if (entity.onGround) {
            return false;
        } else if (entity.func_226278_cu_() > (double)blockPos.getY() + 0.9375D - 1.0E-7D) {
            return false;
        } else if (entity.getMotion().y >= -0.08D) {
            return false;
        } else {
            double lvt_3_1_ = Math.abs((double)blockPos.getX() + 0.5D - entity.func_226277_ct_());
            double lvt_5_1_ = Math.abs((double)blockPos.getZ() + 0.5D - entity.func_226281_cx_());
            double lvt_7_1_ = 0.4375D + (double)(entity.getWidth() / 2.0F);
            return lvt_3_1_ + 1.0E-7D > lvt_7_1_ || lvt_5_1_ + 1.0E-7D > lvt_7_1_;
        }
    }

    private void advancementTrigger(Entity entity, BlockPos blockPos) {
        if (entity instanceof ServerPlayerEntity && entity.world.getGameTime() % 20L == 0L) {
            CriteriaTriggers.field_229864_K_.func_227152_a_((ServerPlayerEntity)entity, entity.world.getBlockState(blockPos));
        }

    }

    private void slideAndStick(Entity entity) {
        Vec3d entityMotion = entity.getMotion();
        if (entityMotion.y < -0.13D) {
            double lvt_3_1_ = -0.05D / entityMotion.y;
            entity.setMotion(new Vec3d(entityMotion.x * lvt_3_1_, -0.05D, entityMotion.z * lvt_3_1_));
        } else {
            entity.setMotion(new Vec3d(entityMotion.x, -0.05D, entityMotion.z));
        }

        entity.fallDistance = 0.0F;
    }

    private void func_226934_a_(World worldIn, Entity entity) {
        if (isRealEntity(entity)) {
            if (worldIn.rand.nextInt(5) == 0) {
                entity.playSound(SoundEvents.field_226139_eT_, 1.0F, 1.0F);
            }

            if (!worldIn.isRemote && worldIn.rand.nextInt(5) == 0) {
                worldIn.setEntityState(entity, (byte)53);
            }
        }
    }*/

    /*private static boolean isRealEntity(Entity entity) {
        return entity instanceof LivingEntity || entity instanceof AbstractMinecartEntity || entity instanceof TNTEntity || entity instanceof BoatEntity;
    }*/

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