package com.minecraftabnormals.buzzier_bees.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.DyeColor;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class SoulCandleBlock extends CandleBlock {

	protected static final VoxelShape[] SHAPES = new VoxelShape[]{
			Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 10.0D, 10.0D),
			Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 10.0D, 13.0D),
			Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 10.0D, 13.0D),
			Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 10.0D, 13.0D)};

	public SoulCandleBlock(Properties properties) {
		super(null, properties);
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
	public IParticleData getFlameParticle() {
		return ParticleTypes.SOUL_FIRE_FLAME;
	}

	@Override
	public void addCandleParticleEffects(World world, IParticleData flameParticle, IParticleData smokeParticle, double x, double y, double z) {
		world.addParticle(flameParticle, x, y + 0.0625D, z, XZ_PARTICLE_SPEED, Y_PARTICLE_SPEED, XZ_PARTICLE_SPEED);
		world.addParticle(smokeParticle, x, y + 0.0625D, z, XZ_PARTICLE_SPEED, Y_PARTICLE_SPEED, XZ_PARTICLE_SPEED);
	}
}
