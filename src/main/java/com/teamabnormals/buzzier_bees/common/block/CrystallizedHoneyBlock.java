package com.teamabnormals.buzzier_bees.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class CrystallizedHoneyBlock extends Block {
	public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

	public CrystallizedHoneyBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(POWERED, false));
	}

	@Override
	public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos neighborPos, boolean isMoving) {
		boolean hasSignal = level.hasNeighborSignal(pos);
		if (hasSignal != state.getValue(POWERED)) {
			level.setBlock(pos, state.setValue(POWERED, hasSignal), 3);
			level.destroyBlock(pos, true);
		}
	}

	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
		if (!level.isClientSide() && entity instanceof LivingEntity) {
			level.destroyBlock(pos, true);
		}
	}

	@Override
	public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
		if (!level.isClientSide() && entity instanceof LivingEntity) {
			level.destroyBlock(pos, true);
		}

		super.fallOn(level, state, pos, entity, fallDistance);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(POWERED);
	}
}
