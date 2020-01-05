package com.bagel.buzzierbees.common.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;

//TODO: Fix bug, that limits your ability to face flower in pot
public class PottedCartwheelBlock extends FlowerPotBlock {
	@SuppressWarnings("deprecation")
	public PottedCartwheelBlock(Block block, Properties properties) {
		super(block, properties);
		// TODO Auto-generated constructor stub
	}

	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing());
	}
	
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}
}
