package com.bagel.buzzierbees.common.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BushBlock;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CandleBlock extends BushBlock implements IWaterLoggable {
   public static final IntegerProperty CANDLES 		= BlockStateProperties.PICKLES_1_4;
   public static final BooleanProperty WATERLOGGED 	= BlockStateProperties.WATERLOGGED;
   public static final DirectionProperty FACING 	= HorizontalBlock.HORIZONTAL_FACING;

   protected static final VoxelShape ONE_SHAPE 		= Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 9.0D, 10.0D);
   protected static final VoxelShape TWO_SHAPE 		= Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 9.0D, 13.0D);
   protected static final VoxelShape THREE_SHAPE 	= Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 9.0D, 13.0D);
   protected static final VoxelShape FOUR_SHAPE 	= Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 9.0D, 13.0D);

   protected CandleBlock(Block.Properties properties) {
      super(properties);
      this.setDefaultState(this.getDefaultState().with(CANDLES, 1).with(WATERLOGGED, true));
    }
   
   @SuppressWarnings("deprecation")
   public int getLightValue(BlockState state) {
      return this.isInBadEnvironment(state) ? 0 : super.getLightValue(state) + 14;
   }

   @Nullable
   public BlockState getStateForPlacement(BlockItemUseContext context) {
	      BlockState blockstate = context.getWorld().getBlockState(context.getPos());
	      Direction direction = context.getPlacementHorizontalFacing();
	      if (blockstate.getBlock() == this) {
	         return blockstate.with(FACING, direction).with(CANDLES, Integer.valueOf(Math.min(4, blockstate.get(CANDLES) + 1)));
	      } else {
	         IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
	         boolean flag = ifluidstate.isTagged(FluidTags.WATER) && ifluidstate.getLevel() == 8;
	         return this.getDefaultState().with(FACING, direction).with(WATERLOGGED, flag);
	      }
	   }

   private boolean isInBadEnvironment(BlockState p_204901_1_) {
      return p_204901_1_.get(WATERLOGGED);
   }

   protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
      return !state.getCollisionShape(worldIn, pos).project(Direction.UP).isEmpty();
   }

   public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
      BlockPos blockpos = pos.down();
      return this.isValidGround(worldIn.getBlockState(blockpos), worldIn, blockpos);
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

   @SuppressWarnings("deprecation")
   public boolean isReplaceable(BlockState state, BlockItemUseContext useContext) {
      return useContext.getItem().getItem() == this.asItem() && state.get(CANDLES) < 4 ? true : super.isReplaceable(state, useContext);
   }

   public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
      switch(state.get(CANDLES)) {
      case 1:
      default:
         return ONE_SHAPE;
      case 2:
         return TWO_SHAPE;
      case 3:
         return THREE_SHAPE;
      case 4:
         return FOUR_SHAPE;
      }
   }
   
   @SuppressWarnings("deprecation")
   public IFluidState getFluidState(BlockState state) {
      return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
   }

   protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
      builder.add(CANDLES, WATERLOGGED, FACING);
   }
   
   @Override
   @OnlyIn(Dist.CLIENT)
   public void animateTick(BlockState state, World worldIn, BlockPos pos, Random rand) {
	   if(state.get(WATERLOGGED) == false) {
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
