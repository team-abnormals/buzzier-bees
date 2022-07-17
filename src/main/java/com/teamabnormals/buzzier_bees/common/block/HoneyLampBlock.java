package com.teamabnormals.buzzier_bees.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EndRodBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Random;

public class HoneyLampBlock extends EndRodBlock implements SimpleWaterloggedBlock {
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	protected static final VoxelShape HONEY_LAMP_VERTICAL_AABB = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D);
	protected static final VoxelShape HONEY_LAMP_NS_AABB = Block.box(5.0D, 5.0D, 0.0D, 11.0D, 11.0D, 16.0D);
	protected static final VoxelShape HONEY_LAMP_EW_AABB = Block.box(0.0D, 5.0D, 5.0D, 16.0D, 11.0D, 11.0D);

	public HoneyLampBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP).setValue(WATERLOGGED, true));
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter blockReader, BlockPos blockPos, CollisionContext selectionContext) {
		return switch (blockState.getValue(FACING).getAxis()) {
			default -> HONEY_LAMP_EW_AABB;
			case Z -> HONEY_LAMP_NS_AABB;
			case Y -> HONEY_LAMP_VERTICAL_AABB;
		};
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState blockState, Level worldIn, BlockPos blockPos, Random random) {
		Direction lvt_5_1_ = blockState.getValue(FACING);
		double lvt_6_1_ = (double) blockPos.getX() + 0.55D - (double) (random.nextFloat() * 0.1F);
		double lvt_8_1_ = (double) blockPos.getY() + 0.55D - (double) (random.nextFloat() * 0.1F);
		double lvt_10_1_ = (double) blockPos.getZ() + 0.55D - (double) (random.nextFloat() * 0.1F);
		double lvt_12_1_ = 0.4F - (random.nextFloat() + random.nextFloat()) * 0.4F;
		if (random.nextInt(5) == 0) {
			worldIn.addParticle(new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Blocks.HONEY_BLOCK)), lvt_6_1_ + (double) lvt_5_1_.getStepX() * lvt_12_1_, lvt_8_1_ + (double) lvt_5_1_.getStepY() * lvt_12_1_, lvt_10_1_ + (double) lvt_5_1_.getStepZ() * lvt_12_1_, random.nextGaussian() * 0.005D, random.nextGaussian() * 0.005D, random.nextGaussian() * 0.005D);
		}
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Direction direction = context.getClickedFace();
		BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos().relative(direction.getOpposite()));
		FluidState ifluidstate = context.getLevel().getFluidState(context.getClickedPos());
		boolean flag = ifluidstate.is(FluidTags.WATER) && ifluidstate.getAmount() == 8;
		return blockstate.getBlock() == this && blockstate.getValue(FACING) == direction ? this.defaultBlockState().setValue(FACING, direction.getOpposite()).setValue(WATERLOGGED, flag) : this.defaultBlockState().setValue(FACING, direction).setValue(WATERLOGGED, flag);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (!state.canSurvive(worldIn, currentPos)) {
			return Blocks.AIR.defaultBlockState();
		} else {
			if (state.getValue(WATERLOGGED)) {
				worldIn.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
			}
			return super.updateShape(state, facing, facingState, worldIn, currentPos, facingPos);
		}
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, WATERLOGGED);
	}
}
