package com.teamabnormals.buzzier_bees.common.block;

import com.teamabnormals.blueprint.common.block.BlueprintFlowerBlock;
import com.teamabnormals.buzzier_bees.core.registry.BBParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;
import java.util.function.Supplier;

public class ButtercupBlock extends BlueprintFlowerBlock {

	public ButtercupBlock(Supplier<MobEffect> stewEffect, int stewEffectDuration, Properties properties) {
		super(stewEffect, stewEffectDuration, properties);
	}

	@Override
	public void animateTick(BlockState state, Level world, BlockPos pos, Random rand) {
		if (world.getGameTime() % 2 == 0) {
			for (int i = 0; i < 2 + rand.nextInt(5); i++) {
				double x = pos.getX() + 0.5D + randNeg(rand.nextFloat() * 0.25F, rand);
				double y = pos.getY() + 0.5D;
				double z = pos.getZ() + 0.5D + randNeg(rand.nextFloat() * 0.05F, rand);
				world.addParticle(BBParticles.BUTTERCUP_BLOOM.get(), x, y, z, randNeg(0.01D, rand), 0.05D + randNeg(0.02D, rand), randNeg(0.01D, rand));
			}
		}
	}

	private static double randNeg(double db, Random rand) {
		if (rand.nextBoolean())
			return db * -1;
		return db;
	}
}