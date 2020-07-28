package com.minecraftabnormals.buzzier_bees.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SoulCandleBlock extends CandleBlock implements IWaterLoggable {
	
	protected static final VoxelShape[] SHAPES 	= new VoxelShape[] { 
			Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 10.0D, 10.0D),
			Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 10.0D, 13.0D),
			Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 10.0D, 13.0D),
			Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 10.0D, 13.0D)};
	
	public SoulCandleBlock(Properties properties) {
		super(properties);
	}

	@Override
	public int getLightValue(BlockState state, IBlockReader access, BlockPos pos) {
		return this.isInBadEnvironment(state) ? 0 : state.getLightValue() + (7 + (1 * state.get(CANDLES)));
	}

	@Override
	public float getEnchantPowerBonus(BlockState state, IWorldReader world, BlockPos pos) {
		return (0.2F * state.get(CANDLES));
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPES[state.get(CANDLES) - 1];
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, World worldIn, BlockPos pos, Random rand) {
		if (state.get(WATERLOGGED) == false && state.get(LIT)) {
			double x = pos.getX();
			double y = pos.getY();
			double z = pos.getZ();

			if (state.get(CANDLES) == 1) {
				worldIn.addParticle(ParticleTypes.SMOKE, x + 0.5D, y + 0.75D, z + 0.5D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x + 0.5D, y + 0.75D, z + 0.5D, 0.002D, 0.01D, 0.002D);
			}
			if (state.get(CANDLES) == 2 && state.get(FACING) == Direction.NORTH) {
				worldIn.addParticle(ParticleTypes.SMOKE, x + 0.5625D, y + 0.75D, z + 0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x + 0.5625D, y + 0.75D, z + 0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x + 0.375D, y + 0.625D, z + 0.625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x + 0.375D, y + 0.625D, z + 0.625D, 0.002D, 0.01D, 0.002D);
			}
			if (state.get(CANDLES) == 2 && state.get(FACING) == Direction.EAST) {
				worldIn.addParticle(ParticleTypes.SMOKE, x + 0.6875D, y + 0.75D, z + 0.5625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x + 0.6875D, y + 0.75D, z + 0.5625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x + 0.375D, y + 0.625D, z + 0.375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x + 0.375D, y + 0.625D, z + 0.375D, 0.002D, 0.01D, 0.002D);
			}
			if (state.get(CANDLES) == 2 && state.get(FACING) == Direction.SOUTH) {
				worldIn.addParticle(ParticleTypes.SMOKE, x + 0.4375D, y + 0.75D, z + 0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x + 0.4375D, y + 0.75D, z + 0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x + 0.625D, y + 0.625D, z + 0.375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x + 0.625D, y + 0.625D, z + 0.375D, 0.002D, 0.01D, 0.002D);
			}
			if (state.get(CANDLES) == 2 && state.get(FACING) == Direction.WEST) {
				worldIn.addParticle(ParticleTypes.SMOKE, x + 0.3125D, y + 0.75D, z + 0.4375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x + 0.3125D, y + 0.75D, z + 0.4375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x + 0.625D, y + 0.625D, z + 0.625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x + 0.625D, y + 0.625D, z + 0.625D, 0.002D, 0.01D, 0.002D);
			}
			if (state.get(CANDLES) == 3 && state.get(FACING) == Direction.NORTH) {
				worldIn.addParticle(ParticleTypes.SMOKE, x + 0.6875D, y + 0.75D, z + 0.375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x + 0.6875D, y + 0.75D, z + 0.375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x + 0.5D, y + 0.6875D, z + 0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x + 0.5D, y + 0.6875D, z + 0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x + 0.3125D, y + 0.625D, z + 0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x + 0.3125D, y + 0.625D, z + 0.3125D, 0.002D, 0.01D, 0.002D);
			}
			if (state.get(CANDLES) == 3 && state.get(FACING) == Direction.EAST) {
				worldIn.addParticle(ParticleTypes.SMOKE, x + 0.625D, y + 0.75D, z + 0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x + 0.625D, y + 0.75D, z + 0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x + 0.3125D, y + 0.6875D, z + 0.5D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x + 0.3125D, y + 0.6875D, z + 0.5D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x + 0.6875D, y + 0.625D, z + 0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x + 0.6875D, y + 0.625D, z + 0.3125D, 0.002D, 0.01D, 0.002D);
			}
			if (state.get(CANDLES) == 3 && state.get(FACING) == Direction.SOUTH) {
				worldIn.addParticle(ParticleTypes.SMOKE, x + 0.3125D, y + 0.75D, z + 0.625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x + 0.3125D, y + 0.75D, z + 0.625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x + 0.5D, y + 0.6875D, z + 0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x + 0.5D, y + 0.6875D, z + 0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x + 0.6875D, y + 0.625D, z + 0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x + 0.6875D, y + 0.625D, z + 0.6875D, 0.002D, 0.01D, 0.002D);
			}
			if (state.get(CANDLES) == 3 && state.get(FACING) == Direction.WEST) {
				worldIn.addParticle(ParticleTypes.SMOKE, x + 0.375D, y + 0.75D, z + 0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x + 0.375D, y + 0.75D, z + 0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x + 0.6875D, y + 0.6875D, z + 0.5D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x + 0.6875D, y + 0.6875D, z + 0.5D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x + 0.3125D, y + 0.625D, z + 0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x + 0.3125D, y + 0.625D, z + 0.6875D, 0.002D, 0.01D, 0.002D);
			}
			if (state.get(CANDLES) == 4 && state.get(FACING) == Direction.NORTH) {
				worldIn.addParticle(ParticleTypes.SMOKE, x + 0.6875D, y + 0.75D, z + 0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x + 0.6875D, y + 0.75D, z + 0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x + 0.625D, y + 0.6875D, z + 0.625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x + 0.625D, y + 0.6875D, z + 0.625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x + 0.3125D, y + 0.625D, z + 0.375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x + 0.3125D, y + 0.625D, z + 0.375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x + 0.3125D, y + 0.4375D, z + 0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x + 0.3125D, y + 0.4375D, z + 0.6875D, 0.002D, 0.01D, 0.002D);
			}
			if (state.get(CANDLES) == 4 && state.get(FACING) == Direction.EAST) {
				worldIn.addParticle(ParticleTypes.SMOKE, x + 0.6875D, y + 0.75D, z + 0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x + 0.6875D, y + 0.75D, z + 0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x + 0.375D, y + 0.6875D, z + 0.625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x + 0.375D, y + 0.6875D, z + 0.625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x + 0.625D, y + 0.625D, z + 0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x + 0.625D, y + 0.625D, z + 0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x + 0.3125D, y + 0.4375D, z + 0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x + 0.3125D, y + 0.4375D, z + 0.3125D, 0.002D, 0.01D, 0.002D);
			}
			if (state.get(CANDLES) == 4 && state.get(FACING) == Direction.SOUTH) {
				worldIn.addParticle(ParticleTypes.SMOKE, x + 0.3125D, y + 0.75D, z + 0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x + 0.3125D, y + 0.75D, z + 0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x + 0.375D, y + 0.6875D, z + 0.375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x + 0.375D, y + 0.6875D, z + 0.375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x + 0.6875D, y + 0.625D, z + 0.625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x + 0.6875D, y + 0.625D, z + 0.625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x + 0.6875D, y + 0.4375D, z + 0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x + 0.6875D, y + 0.4375D, z + 0.3125D, 0.002D, 0.01D, 0.002D);
			}
			if (state.get(CANDLES) == 4 && state.get(FACING) == Direction.WEST) {
				worldIn.addParticle(ParticleTypes.SMOKE, x + 0.3125D, y + 0.75D, z + 0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x + 0.3125D, y + 0.75D, z + 0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x + 0.625D, y + 0.6875D, z + 0.375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x + 0.625D, y + 0.6875D, z + 0.375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x + 0.375D, y + 0.625D, z + 0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x + 0.375D, y + 0.625D, z + 0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x + 0.6875D, y + 0.4375D, z + 0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x + 0.6875D, y + 0.4375D, z + 0.6875D, 0.002D, 0.01D, 0.002D);
			}
		}
	}
}
