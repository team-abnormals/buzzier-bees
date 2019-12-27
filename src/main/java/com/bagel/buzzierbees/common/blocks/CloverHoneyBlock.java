package com.bagel.buzzierbees.common.blocks;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.item.FallingBlockEntity;
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
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import sun.security.jca.GetInstance;

import java.util.Random;
import java.util.logging.Logger;

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

    @Override
    public boolean isStickyBlock(BlockState state) {
        return true;
    }

    private static boolean func_226937_c_(Entity p_226937_0_) {
        return p_226937_0_ instanceof LivingEntity || p_226937_0_ instanceof AbstractMinecartEntity || p_226937_0_ instanceof TNTEntity || p_226937_0_ instanceof BoatEntity;
    }

    @SuppressWarnings("deprecation")
    public VoxelShape getCollisionShape(BlockState blockState, IBlockReader blockReader, BlockPos blockPos, ISelectionContext selectionContext) {
        return field_226930_a_;
    }

    public void onFallenUpon(World worldIn, BlockPos blockPos, Entity entity, float p_180658_4_) {
        entity.playSound(SoundEvents.field_226139_eT_, 1.0F, 1.0F);
        if (!worldIn.isRemote) {
            worldIn.setEntityState(entity, (byte)54);
        }

        if (entity.func_225503_b_(p_180658_4_, 0.2F)) {
            entity.playSound(this.soundType.getFallSound(), this.soundType.getVolume() * 0.5F, this.soundType.getPitch() * 0.75F);
        }

    }

    @SuppressWarnings("deprecation")
    public void onEntityCollision(BlockState blockState, World worldIn, BlockPos blockPos, Entity entity) {
        if (this.func_226935_a_(blockPos, entity)) {
            this.func_226933_a_(entity, blockPos);
            this.func_226938_d_(entity);
            this.func_226934_a_(worldIn, entity);
        }

        super.onEntityCollision(blockState, worldIn, blockPos, entity);
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

    private void func_226933_a_(Entity entity, BlockPos blockPos) {
        if (entity instanceof ServerPlayerEntity && entity.world.getGameTime() % 20L == 0L) {
            CriteriaTriggers.field_229864_K_.func_227152_a_((ServerPlayerEntity)entity, entity.world.getBlockState(blockPos));
        }

    }

    private void func_226938_d_(Entity entity) {
        Vec3d lvt_2_1_ = entity.getMotion();
        if (lvt_2_1_.y < -0.13D) {
            double lvt_3_1_ = -0.05D / lvt_2_1_.y;
            entity.setMotion(new Vec3d(lvt_2_1_.x * lvt_3_1_, -0.05D, lvt_2_1_.z * lvt_3_1_));
        } else {
            entity.setMotion(new Vec3d(lvt_2_1_.x, -0.05D, lvt_2_1_.z));
        }

        entity.fallDistance = 0.0F;
    }

    private void func_226934_a_(World worldIn, Entity entity) {
        if (func_226937_c_(entity)) {
            if (worldIn.rand.nextInt(5) == 0) {
                entity.playSound(SoundEvents.field_226139_eT_, 1.0F, 1.0F);
            }

            if (!worldIn.isRemote && worldIn.rand.nextInt(5) == 0) {
                worldIn.setEntityState(entity, (byte)53);
            }
        }

    }

    @OnlyIn(Dist.CLIENT)
    public int getDustColor(BlockState blockState) {
        return -8356741;
    }

    @Override
    public void func_225534_a_(BlockState blockState, ServerWorld serverWorld, BlockPos blockPos, Random random) {
        if (serverWorld.isAirBlock(blockPos.down()) || canFallThrough(serverWorld.getBlockState(blockPos.down())) && blockPos.getY() >= 0) {

            int east    = serverWorld.isAirBlock(blockPos.east())   ? 1 : 0;;
            int north   = serverWorld.isAirBlock(blockPos.north())  ? 1 : 0;;
            int west    = serverWorld.isAirBlock(blockPos.west())   ? 1 : 0;;
            int south   = serverWorld.isAirBlock(blockPos.south())  ? 1 : 0;;

            boolean isEastStickyBlock = serverWorld.getBlockState(blockPos.east()).isStickyBlock();
            boolean isNorthStickyBlock = serverWorld.getBlockState(blockPos.north()).isStickyBlock();
            boolean isWestStickyBlock = serverWorld.getBlockState(blockPos.west()).isStickyBlock();
            boolean isSouthStickyBlock = serverWorld.getBlockState(blockPos.south()).isStickyBlock();
            boolean isUpStickyBlock = serverWorld.getBlockState(blockPos.up()).isStickyBlock();

            if (east + north + west + south >= 3) {
                //if (!isEastStickyBlock || !isNorthStickyBlock || !isSouthStickyBlock || !isUpStickyBlock || !isWestStickyBlock) {
                FallingBlockEntity fallingblockentity = new FallingBlockEntity(serverWorld, (double)blockPos.getX() + 0.5D, (double)blockPos.getY(), (double)blockPos.getZ() + 0.5D, serverWorld.getBlockState(blockPos));
                this.onStartFalling(fallingblockentity);
                serverWorld.addEntity(fallingblockentity);
                //}
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void func_226931_a_(Entity entity) {
        func_226932_a_(entity, 5);
    }

    @OnlyIn(Dist.CLIENT)
    public static void func_226936_b_(Entity entity) {
        func_226932_a_(entity, 10);
    }

    @OnlyIn(Dist.CLIENT)
    private static void func_226932_a_(Entity entity, int p_226932_1_) {
        if (entity.world.isRemote) {
            BlockState lvt_2_1_ = ModBlocks.CLOVER_HONEY_BLOCK.getDefaultState();

            for(int lvt_3_1_ = 0; lvt_3_1_ < p_226932_1_; ++lvt_3_1_) {
                entity.world.addParticle(new BlockParticleData(ParticleTypes.BLOCK, lvt_2_1_), entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), 0.0D, 0.0D, 0.0D);
            }

        }
    }
}
