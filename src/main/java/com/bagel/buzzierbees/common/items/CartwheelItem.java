package com.bagel.buzzierbees.common.items;

import com.bagel.buzzierbees.common.blocks.ModBlocks;
import com.bagel.buzzierbees.common.blocks.PottedCartwheelBlock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CartwheelItem extends BlockItem {

	public CartwheelItem(Block blockIn, Properties builder) {
		super(blockIn, builder);
	}

	public ActionResultType onItemUse(ItemUseContext context) {
	      World world = context.getWorld();
	      BlockPos blockpos = context.getPos();
	      BlockState blockstate = world.getBlockState(blockpos);
	      Direction direction = context.getPlacementHorizontalFacing();
	      if (blockstate.getBlock() == Blocks.FLOWER_POT) {
	    	  if (!world.isRemote) {
	    		  world.setBlockState(blockpos, ModBlocks.POTTED_CARTWHEEL.getDefaultState().with(PottedCartwheelBlock.FACING, direction), 11);
	    		  context.getItem().shrink(1);  
	    	  }
	    	  return ActionResultType.SUCCESS;  
	      } else {
	    	  return ActionResultType.PASS;  
	    	  }
	      }
	
}
