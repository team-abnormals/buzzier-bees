package com.bagel.buzzierbees.common.blocks;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FallingBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

//TODO...
public class CloverHoneyBlock extends FallingBlock {
    protected static final VoxelShape field_226930_a_ = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D);

    public CloverHoneyBlock(Properties p_i48440_1_) {
        super(p_i48440_1_);
    }

    @OnlyIn(Dist.CLIENT)
    @SuppressWarnings("deprecation")
    public boolean isSideInvisible(BlockState p_200122_1_, BlockState p_200122_2_, Direction p_200122_3_) {
        return p_200122_2_.getBlock() == this ? true : super.isSideInvisible(p_200122_1_, p_200122_2_, p_200122_3_);
    }

    @OnlyIn(Dist.CLIENT)
    public int getDustColor(BlockState p_189876_1_) {
        return -8356741;
    }

    private static boolean func_226937_c_(Entity p_226937_0_) {
        return p_226937_0_ instanceof LivingEntity || p_226937_0_ instanceof AbstractMinecartEntity || p_226937_0_ instanceof TNTEntity || p_226937_0_ instanceof BoatEntity;
    }

    public VoxelShape getCollisionShape(BlockState p_220071_1_, IBlockReader p_220071_2_, BlockPos p_220071_3_, ISelectionContext p_220071_4_) {
        return field_226930_a_;
    }

    public void onFallenUpon(World p_180658_1_, BlockPos p_180658_2_, Entity p_180658_3_, float p_180658_4_) {
        p_180658_3_.playSound(SoundEvents.field_226139_eT_, 1.0F, 1.0F);
        if (!p_180658_1_.isRemote) {
            p_180658_1_.setEntityState(p_180658_3_, (byte)54);
        }

        if (p_180658_3_.func_225503_b_(p_180658_4_, 0.2F)) {
            p_180658_3_.playSound(this.soundType.getFallSound(), this.soundType.getVolume() * 0.5F, this.soundType.getPitch() * 0.75F);
        }

    }

    @SuppressWarnings("deprecation")
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
        } else if (p_226935_2_.func_226278_cu_() > (double)p_226935_1_.getY() + 0.9375D - 1.0E-7D) {
            return false;
        } else if (p_226935_2_.getMotion().y >= -0.08D) {
            return false;
        } else {
            double lvt_3_1_ = Math.abs((double)p_226935_1_.getX() + 0.5D - p_226935_2_.func_226277_ct_());
            double lvt_5_1_ = Math.abs((double)p_226935_1_.getZ() + 0.5D - p_226935_2_.func_226281_cx_());
            double lvt_7_1_ = 0.4375D + (double)(p_226935_2_.getWidth() / 2.0F);
            return lvt_3_1_ + 1.0E-7D > lvt_7_1_ || lvt_5_1_ + 1.0E-7D > lvt_7_1_;
        }
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

    @OnlyIn(Dist.CLIENT)
    public static void func_226931_a_(Entity p_226931_0_) {
        func_226932_a_(p_226931_0_, 5);
    }

    @OnlyIn(Dist.CLIENT)
    public static void func_226936_b_(Entity p_226936_0_) {
        func_226932_a_(p_226936_0_, 10);
    }

    @OnlyIn(Dist.CLIENT)
    private static void func_226932_a_(Entity p_226932_0_, int p_226932_1_) {
        if (p_226932_0_.world.isRemote) {
            BlockState lvt_2_1_ = Blocks.field_226907_mc_.getDefaultState();

            for(int lvt_3_1_ = 0; lvt_3_1_ < p_226932_1_; ++lvt_3_1_) {
                p_226932_0_.world.addParticle(new BlockParticleData(ParticleTypes.BLOCK, lvt_2_1_), p_226932_0_.func_226277_ct_(), p_226932_0_.func_226278_cu_(), p_226932_0_.func_226281_cx_(), 0.0D, 0.0D, 0.0D);
            }

        }
    }
}
