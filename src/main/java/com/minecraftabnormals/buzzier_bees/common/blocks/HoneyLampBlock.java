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

	protected static final VoxelShape HONEY_LAMP_VERTICAL_AABB = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D);
	protected static final VoxelShape HONEY_LAMP_NS_AABB = Block.box(5.0D, 5.0D, 0.0D, 11.0D, 11.0D, 16.0D);
	protected static final VoxelShape HONEY_LAMP_EW_AABB = Block.box(0.0D, 5.0D, 5.0D, 16.0D, 11.0D, 11.0D);

	public HoneyLampBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP).setValue(WATERLOGGED, true));
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader blockReader, BlockPos blockPos, ISelectionContext selectionContext) {
		switch (((Direction) blockState.getValue(FACING)).getAxis()) {
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
		Direction lvt_5_1_ = (Direction) blockState.getValue(FACING);
		double lvt_6_1_ = (double) blockPos.getX() + 0.55D - (double) (random.nextFloat() * 0.1F);
		double lvt_8_1_ = (double) blockPos.getY() + 0.55D - (double) (random.nextFloat() * 0.1F);
		double lvt_10_1_ = (double) blockPos.getZ() + 0.55D - (double) (random.nextFloat() * 0.1F);
		double lvt_12_1_ = (double) (0.4F - (random.nextFloat() + random.nextFloat()) * 0.4F);
		if (random.nextInt(5) == 0) {
			worldIn.addParticle(new ItemParticleData(ParticleTypes.ITEM, new ItemStack(Blocks.HONEY_BLOCK)), lvt_6_1_ + (double) lvt_5_1_.getStepX() * lvt_12_1_, lvt_8_1_ + (double) lvt_5_1_.getStepY() * lvt_12_1_, lvt_10_1_ + (double) lvt_5_1_.getStepZ() * lvt_12_1_, random.nextGaussian() * 0.005D, random.nextGaussian() * 0.005D, random.nextGaussian() * 0.005D);
		}
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		Direction direction = context.getClickedFace();
		BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos().relative(direction.getOpposite()));
		FluidState ifluidstate = context.getLevel().getFluidState(context.getClickedPos());
		boolean flag = ifluidstate.is(FluidTags.WATER) && ifluidstate.getAmount() == 8;
		return blockstate.getBlock() == this && blockstate.getValue(FACING) == direction ? this.defaultBlockState().setValue(FACING, direction.getOpposite()).setValue(WATERLOGGED, flag) : this.defaultBlockState().setValue(FACING, direction).setValue(WATERLOGGED, flag);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (!state.canSurvive(worldIn, currentPos)) {
			return Blocks.AIR.defaultBlockState();
		} else {
			if (state.getValue(WATERLOGGED)) {
				worldIn.getLiquidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
			}
			return super.updateShape(state, facing, facingState, worldIn, currentPos, facingPos);
		}
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING, WATERLOGGED);
	}
}
