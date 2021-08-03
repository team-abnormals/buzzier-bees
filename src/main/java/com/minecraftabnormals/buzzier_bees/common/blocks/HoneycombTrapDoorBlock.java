package com.minecraftabnormals.buzzier_bees.common.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.TrapDoorBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.properties.Half;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class HoneycombTrapDoorBlock extends TrapDoorBlock {
	public HoneycombTrapDoorBlock(AbstractBlock.Properties properties) {
		super(properties);
	}

	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		state = state.cycle(OPEN);
		worldIn.setBlock(pos, state, 2);
		if (state.getValue(WATERLOGGED)) {
			worldIn.getLiquidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
		}

		this.playSound(player, worldIn, pos, state.getValue(OPEN));
		return ActionResultType.sidedSuccess(worldIn.isClientSide);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		BlockState blockstate = this.defaultBlockState();
		FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
		Direction direction = context.getClickedFace();
		if (!context.replacingClickedOnBlock() && direction.getAxis().isHorizontal()) {
			blockstate = blockstate.setValue(FACING, direction).setValue(HALF, context.getClickLocation().y - (double) context.getClickedPos().getY() > 0.5D ? Half.TOP : Half.BOTTOM);
		} else {
			blockstate = blockstate.setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(HALF, direction == Direction.UP ? Half.BOTTOM : Half.TOP);
		}

		return blockstate.setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
	}

	@Override
	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
	}
}