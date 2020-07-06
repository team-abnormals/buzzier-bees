package com.bagel.buzzierbees.common.world.features;

import java.util.Random;

import com.bagel.buzzierbees.common.blocks.CartwheelBlock;
import com.mojang.serialization.Codec;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.FlowersFeature;

public class DirectionalFlowersFeature<U extends BlockClusterFeatureConfig> extends FlowersFeature<U> {
	public DirectionalFlowersFeature(Codec<U> codec) {
		super(codec);
	}

   public boolean func_225559_a_(IWorld world, BlockPos pos, U config) {
      return !config.blacklist.contains(world.getBlockState(pos));
   }

   public int func_225560_a_(U config) {
      return config.tryCount;
   }

   public BlockPos getNearbyPos(Random rand, BlockPos pos, U config) {
      return pos.add(rand.nextInt(config.xSpread) - rand.nextInt(config.xSpread), rand.nextInt(config.ySpread) - rand.nextInt(config.ySpread), rand.nextInt(config.zSpread) - rand.nextInt(config.zSpread));
   }

   public BlockState getFlowerToPlace(Random rand, BlockPos pos, U config) {
      return config.stateProvider.getBlockState(rand, pos).with(CartwheelBlock.FACING, Direction.Plane.HORIZONTAL.random(rand));
   }
}
