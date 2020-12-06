package com.minecraftabnormals.buzzier_bees.common.blocks;

import com.google.common.base.Supplier;
import com.minecraftabnormals.abnormals_core.common.blocks.AbnormalsFlowerBlock;
import com.minecraftabnormals.buzzier_bees.core.registry.BBParticles;
import net.minecraft.block.BlockState;
import net.minecraft.potion.Effect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class ButtercupBlock extends AbnormalsFlowerBlock {

	public ButtercupBlock(Supplier<Effect> stewEffect, int stewEffectDuration, Properties properties) {
		super(stewEffect, stewEffectDuration, properties);
	}

	@Override
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {
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