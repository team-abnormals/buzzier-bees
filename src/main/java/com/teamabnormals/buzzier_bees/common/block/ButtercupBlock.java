package com.teamabnormals.buzzier_bees.common.block;

import com.teamabnormals.blueprint.core.util.MathUtil;
import com.teamabnormals.buzzier_bees.core.registry.BBParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;

public class ButtercupBlock extends FlowerBlock {

	public ButtercupBlock(Holder<MobEffect> stewEffect, int stewEffectDuration, Properties properties) {
		super(stewEffect, stewEffectDuration, properties);
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
		addParticles(level, pos, rand);
	}

	public static void addParticles(Level level, BlockPos pos, RandomSource rand) {
		if (level.getGameTime() % 2 == 0) {
			for (int i = 0; i < 2 + rand.nextInt(5); i++) {
				double x = pos.getX() + 0.5D + MathUtil.makeNegativeRandomly(rand.nextFloat() * 0.25F, rand);
				double y = pos.getY() + 0.5D;
				double z = pos.getZ() + 0.5D + MathUtil.makeNegativeRandomly(rand.nextFloat() * 0.05F, rand);
				level.addParticle(BBParticleTypes.BUTTERCUP_BLOOM.get(), x, y, z, MathUtil.makeNegativeRandomly(0.01D, rand), 0.05D + MathUtil.makeNegativeRandomly(0.02D, rand), MathUtil.makeNegativeRandomly(0.01D, rand));
			}
		}
	}
}