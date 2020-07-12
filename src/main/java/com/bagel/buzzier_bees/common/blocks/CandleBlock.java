package com.bagel.buzzier_bees.common.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@SuppressWarnings("deprecation")
public class CandleBlock extends Block implements IWaterLoggable {
	
	public static final IntegerProperty CANDLES 	= IntegerProperty.create("candles", 1, 4);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final BooleanProperty LIT 		= BooleanProperty.create("lit");
	public static final DirectionProperty FACING 	= HorizontalBlock.HORIZONTAL_FACING;
	
	protected static final VoxelShape[] SHAPES 	= new VoxelShape[] { 
			Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 10.0D, 10.0D),
			Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 10.0D, 13.0D),
			Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 10.0D, 13.0D),
			Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 10.0D, 13.0D)};
	
	public CandleBlock(Properties properties) {
		super(properties);
    	this.setDefaultState(this.getDefaultState().with(CANDLES, 1).with(WATERLOGGED, true).with(LIT, true));
    }
	
	@Override
	public int getLightValue(BlockState state, IBlockReader access, BlockPos pos) {
		return this.isInBadEnvironment(state) ? 0 : super.getLightValue(state, access, pos) + (11 + (1 * state.get(CANDLES)));	
	}
	
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		BlockState blockstate = context.getWorld().getBlockState(context.getPos());
		Direction direction = context.getPlacementHorizontalFacing();
		if (blockstate.getBlock() == this) {
			return blockstate.with(FACING, direction).with(CANDLES, Integer.valueOf(Math.min(4, blockstate.get(CANDLES) + 1)));
		} else {
			FluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
			boolean flag = ifluidstate.isTagged(FluidTags.WATER) && ifluidstate.getLevel() == 8;
			return this.getDefaultState().with(FACING, direction).with(WATERLOGGED, flag);
		}
	}
	
	public boolean isInBadEnvironment(BlockState state) {
		return state.get(WATERLOGGED);	
	}
	
	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.with(FACING, rot.rotate(state.get(FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return mirrorIn == Mirror.NONE ? state : state.rotate(mirrorIn.toRotation(state.get(FACING)));
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		return hasEnoughSolidSide(worldIn, pos.down(), Direction.UP);
	}

	@Override
	public float getEnchantPowerBonus(BlockState state, IWorldReader world, BlockPos pos) {
		return (0.1F * state.get(CANDLES));	
	}

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
	
	public boolean isReplaceable(BlockState state, BlockItemUseContext useContext) {
		return useContext.getItem().getItem() == this.asItem() && state.get(CANDLES) < 4 ? true : super.isReplaceable(state, useContext);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPES[state.get(CANDLES) - 1];
	}
	
	public FluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);	
	}
	
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(CANDLES, WATERLOGGED, LIT, FACING);	
	}
	
	public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
		return true;
	}
	
	public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
		return type == PathType.AIR && !this.field_235688_at_ ? true : super.allowsMovement(state, worldIn, pos, type);	
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, World worldIn, BlockPos pos, Random rand) {
		if(state.get(WATERLOGGED) == false && state.get(LIT)) {
			double x = pos.getX();
			double y = pos.getY();
			double z = pos.getZ();
			
			if(state.get(CANDLES) == 1) {
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.5D, y+0.75D, z+0.5D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.5D, y+0.75D, z+0.5D, 0.002D, 0.01D, 0.002D);
			}
			if(state.get(CANDLES) == 2 && state.get(FACING) == Direction.NORTH) {
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.5625D, y+0.75D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.5625D, y+0.75D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.375D, y+0.625D, z+0.625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.375D, y+0.625D, z+0.625D, 0.002D, 0.01D, 0.002D);
			}
			if(state.get(CANDLES) == 2 && state.get(FACING) == Direction.EAST) {
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.6875D, y+0.75D, z+0.5625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.6875D, y+0.75D, z+0.5625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.375D, y+0.625D, z+0.375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.375D, y+0.625D, z+0.375D, 0.002D, 0.01D, 0.002D);
			}
			if(state.get(CANDLES) == 2 && state.get(FACING) == Direction.SOUTH) {
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.4375D, y+0.75D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.4375D, y+0.75D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.625D, y+0.625D, z+0.375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.625D, y+0.625D, z+0.375D, 0.002D, 0.01D, 0.002D);
			}
			if(state.get(CANDLES) == 2 && state.get(FACING) == Direction.WEST) {
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.3125D, y+0.75D, z+0.4375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.3125D, y+0.75D, z+0.4375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.625D, y+0.625D, z+0.625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.625D, y+0.625D, z+0.625D, 0.002D, 0.01D, 0.002D);
			}
			if(state.get(CANDLES) == 3 && state.get(FACING) == Direction.NORTH) {
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.6875D, y+0.75D, z+0.375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.6875D, y+0.75D, z+0.375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.5D, y+0.6875D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.5D, y+0.6875D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.3125D, y+0.625D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.3125D, y+0.625D, z+0.3125D, 0.002D, 0.01D, 0.002D);
			}
			if(state.get(CANDLES) == 3 && state.get(FACING) == Direction.EAST) {
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.625D, y+0.75D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.625D, y+0.75D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.3125D, y+0.6875D, z+0.5D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.3125D, y+0.6875D, z+0.5D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.6875D, y+0.625D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.6875D, y+0.625D, z+0.3125D, 0.002D, 0.01D, 0.002D);
			}
			if(state.get(CANDLES) == 3 && state.get(FACING) == Direction.SOUTH) {
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.3125D, y+0.75D, z+0.625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.3125D, y+0.75D, z+0.625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.5D, y+0.6875D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.5D, y+0.6875D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.6875D, y+0.625D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.6875D, y+0.625D, z+0.6875D, 0.002D, 0.01D, 0.002D);
			}
			if(state.get(CANDLES) == 3 && state.get(FACING) == Direction.WEST) {
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.375D, y+0.75D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.375D, y+0.75D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.6875D, y+0.6875D, z+0.5D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.6875D, y+0.6875D, z+0.5D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.3125D, y+0.625D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.3125D, y+0.625D, z+0.6875D, 0.002D, 0.01D, 0.002D);
			}
			if(state.get(CANDLES) == 4 && state.get(FACING) == Direction.NORTH) {
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.6875D, y+0.75D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.6875D, y+0.75D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.625D, y+0.6875D, z+0.625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.625D, y+0.6875D, z+0.625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.3125D, y+0.625D, z+0.375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.3125D, y+0.625D, z+0.375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.3125D, y+0.4375D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.3125D, y+0.4375D, z+0.6875D, 0.002D, 0.01D, 0.002D);
			}
			if(state.get(CANDLES) == 4 && state.get(FACING) == Direction.EAST) {
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.6875D, y+0.75D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.6875D, y+0.75D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.375D, y+0.6875D, z+0.625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.375D, y+0.6875D, z+0.625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.625D, y+0.625D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.625D, y+0.625D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.3125D, y+0.4375D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.3125D, y+0.4375D, z+0.3125D, 0.002D, 0.01D, 0.002D);
			}
			if(state.get(CANDLES) == 4 && state.get(FACING) == Direction.SOUTH) {
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.3125D, y+0.75D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.3125D, y+0.75D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.375D, y+0.6875D, z+0.375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.375D, y+0.6875D, z+0.375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.6875D, y+0.625D, z+0.625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.6875D, y+0.625D, z+0.625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.6875D, y+0.4375D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.6875D, y+0.4375D, z+0.3125D, 0.002D, 0.01D, 0.002D);
			}
			if(state.get(CANDLES) == 4 && state.get(FACING) == Direction.WEST) {
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.3125D, y+0.75D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.3125D, y+0.75D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.625D, y+0.6875D, z+0.375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.625D, y+0.6875D, z+0.375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.375D, y+0.625D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.375D, y+0.625D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.6875D, y+0.4375D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.6875D, y+0.4375D, z+0.6875D, 0.002D, 0.01D, 0.002D);
			}
		}
	}
}
