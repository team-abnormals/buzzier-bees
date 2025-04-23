package com.teamabnormals.buzzier_bees.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class PottedButtercupBlock extends FlowerPotBlock {

	public PottedButtercupBlock(Block potted, BlockBehaviour.Properties properties) {
		super(potted, properties);
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
		ButtercupBlock.addParticles(level, pos, rand);
	}
}