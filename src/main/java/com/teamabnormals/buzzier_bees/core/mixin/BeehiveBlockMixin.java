package com.teamabnormals.buzzier_bees.core.mixin;

import net.minecraft.world.level.block.BeehiveBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BeehiveBlock.class)
public class BeehiveBlockMixin extends Block {

	public BeehiveBlockMixin(Properties properties) {
		super(properties);
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(BeehiveBlock.FACING, rot.rotate(state.getValue(BeehiveBlock.FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return mirrorIn == Mirror.NONE ? state : state.rotate(mirrorIn.getRotation(state.getValue(BeehiveBlock.FACING)));
	}
}
