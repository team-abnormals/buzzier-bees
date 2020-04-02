package com.bagel.buzzierbees.common.items;

import com.bagel.buzzierbees.common.blocks.PottedCartwheelBlock;
import com.bagel.buzzierbees.core.registry.BBBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.world.World;

public class CartwheelItem extends BlockItem {
	@SuppressWarnings("unused")
	@Deprecated
	private final Block block;
	
	public CartwheelItem(Block blockIn, Properties builder) {
		super(blockIn, builder);
		this.block = blockIn;
	}

	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		World world = context.getWorld();
		BlockState blockstate = world.getBlockState(context.getPos());
		
		if (blockstate.getBlock() == Blocks.FLOWER_POT) {
			if (!world.isRemote) {
				world.setBlockState(context.getPos(), BBBlocks.POTTED_CARTWHEEL.get().getDefaultState().with(PottedCartwheelBlock.FACING, context.getPlacementHorizontalFacing().getOpposite()), 11);
				context.getItem().shrink(1);	
			}
			
		} else {
			ActionResultType actionresulttype = this.tryPlace(new BlockItemUseContext(context));
			return actionresulttype != ActionResultType.SUCCESS && this.isFood() ? this.onItemRightClick(context.getWorld(), context.getPlayer(), context.getHand()).getType() : actionresulttype;
		}
		return ActionResultType.PASS;	
	}
}