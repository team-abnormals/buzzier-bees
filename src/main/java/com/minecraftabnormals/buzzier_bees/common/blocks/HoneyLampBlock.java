package com.minecraftabnormals.buzzier_bees.common.blocks;

import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Random;

public class HoneyLampBlock extends EndRodBlock implements IWaterLoggable {
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	protected static final VoxelShape HONEY_LAMP_VERTICAL_AABB = Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D);
	protected static final VoxelShape HONEY_LAMP_NS_AABB = Block.makeCuboidShape(5.0D, 5.0D, 0.0D, 11.0D, 11.0D, 16.0D);
	protected static final VoxelShape HONEY_LAMP_EW_AABB = Block.makeCuboidShape(0.0D, 5.0D, 5.0D, 16.0D, 11.0D, 11.0D);

	public HoneyLampBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.UP).with(WATERLOGGED, true));
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader blockReader, BlockPos blockPos, ISelectionContext selectionContext) {
		switch (((Direction) blockState.get(FACING)).getAxis()) {
			case X:
			default:
				return HONEY_LAMP_EW_AABB;
			case Z:
				return HONEY_LAMP_NS_AABB;
			case Y:
				return HONEY_LAMP_VERTICAL_AABB;
		}
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState blockState, World worldIn, BlockPos blockPos, Random random) {
		Direction lvt_5_1_ = (Direction) blockState.get(FACING);
		double lvt_6_1_ = (double) blockPos.getX() + 0.55D - (double) (random.nextFloat() * 0.1F);
		double lvt_8_1_ = (double) blockPos.getY() + 0.55D - (double) (random.nextFloat() * 0.1F);
		double lvt_10_1_ = (double) blockPos.getZ() + 0.55D - (double) (random.nextFloat() * 0.1F);
		double lvt_12_1_ = (double) (0.4F - (random.nextFloat() + random.nextFloat()) * 0.4F);
		if (random.nextInt(5) == 0) {
			worldIn.addParticle(new ItemParticleData(ParticleTypes.ITEM, new ItemStack(Blocks.HONEY_BLOCK)), lvt_6_1_ + (double) lvt_5_1_.getXOffset() * lvt_12_1_, lvt_8_1_ + (double) lvt_5_1_.getYOffset() * lvt_12_1_, lvt_10_1_ + (double) lvt_5_1_.getZOffset() * lvt_12_1_, random.nextGaussian() * 0.005D, random.nextGaussian() * 0.005D, random.nextGaussian() * 0.005D);
		}
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		Direction direction = context.getFace();
		BlockState blockstate = context.getWorld().getBlockState(context.getPos().offset(direction.getOpposite()));
		FluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
		boolean flag = ifluidstate.isTagged(FluidTags.WATER) && ifluidstate.getLevel() == 8;
		return blockstate.getBlock() == this && blockstate.get(FACING) == direction ? this.getDefaultState().with(FACING, direction.getOpposite()).with(WATERLOGGED, flag) : this.getDefaultState().with(FACING, direction).with(WATERLOGGED, flag);
	}

	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (!state.isValidPosition(worldIn, currentPos)) {
			return Blocks.AIR.getDefaultState();
		} else {
			if (state.get(WATERLOGGED)) {
				worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
			}
			return super.updatePostPlacement(state, facing, facingState, worldIn, currentPos, facingPos);
		}
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING, WATERLOGGED);
	}
}
