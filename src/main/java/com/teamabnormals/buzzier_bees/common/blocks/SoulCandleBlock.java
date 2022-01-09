package com.teamabnormals.buzzier_bees.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SoulCandleBlock extends CandleBlock {

	protected static final VoxelShape[] SHAPES = new VoxelShape[]{
			Block.box(6.0D, 0.0D, 6.0D, 10.0D, 10.0D, 10.0D),
			Block.box(3.0D, 0.0D, 3.0D, 13.0D, 10.0D, 13.0D),
			Block.box(3.0D, 0.0D, 3.0D, 13.0D, 10.0D, 13.0D),
			Block.box(3.0D, 0.0D, 3.0D, 13.0D, 10.0D, 13.0D)};

	public SoulCandleBlock(Properties properties) {
		super(null, properties);
	}

	@Override
	public float getEnchantPowerBonus(BlockState state, LevelReader world, BlockPos pos) {
		return (0.2F * state.getValue(CANDLES));
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return SHAPES[state.getValue(CANDLES) - 1];
	}

	@Override
	public ParticleOptions getFlameParticle() {
		return ParticleTypes.SOUL_FIRE_FLAME;
	}

	@Override
	public void addCandleParticleEffects(Level world, ParticleOptions flameParticle, ParticleOptions smokeParticle, double x, double y, double z) {
		world.addParticle(flameParticle, x, y + 0.0625D, z, XZ_PARTICLE_SPEED, Y_PARTICLE_SPEED, XZ_PARTICLE_SPEED);
		world.addParticle(smokeParticle, x, y + 0.0625D, z, XZ_PARTICLE_SPEED, Y_PARTICLE_SPEED, XZ_PARTICLE_SPEED);
	}
}
