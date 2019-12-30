package com.bagel.buzzierbees.common.items;

import net.minecraft.block.BeehiveBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class HoneyWandItem extends Item {
	
	public HoneyWandItem(Item.Properties properties) {
		super(properties);
		// TODO Auto-generated constructor stub
	}
	
	public ActionResultType onItemUse(ItemUseContext context) {
	      World world = context.getWorld();
	      BlockPos blockpos = context.getPos();
	      BlockState blockstate = world.getBlockState(blockpos);
	      
	      if (blockstate.getBlock() instanceof BeehiveBlock && (context.getFace() == blockstate.get(BeehiveBlock.field_226872_b_)) && blockstate.get(BeehiveBlock.field_226873_c_) == 5) {
	    	  PlayerEntity playerentity = context.getPlayer();
	    	  Hand hand = context.getHand();
	    	  BlockState blockstate2 = null;
	    	  
	    	  ItemStack newStack = new ItemStack(ModItems.STICKY_HONEY_WAND);
	    	  world.playSound((PlayerEntity)null, blockpos, SoundEvents.field_226135_eP_, SoundCategory.NEUTRAL, 1.0F, 1.0F);
	    	  blockstate2 = blockstate.with(BeehiveBlock.field_226873_c_, Integer.valueOf(0));
	    	  if (blockstate2 != null) {
	    		  if (!world.isRemote) {
	    			  world.setBlockState(blockpos, blockstate2, 11);
	    			  if (playerentity != null) {
	    				  context.getItem().damageItem(1, playerentity, (p_220040_1_) -> {p_220040_1_.sendBreakAnimation(context.getHand());});
	    				  int previousDurability = context.getItem().getDamage();
	    				  newStack.damageItem(previousDurability, playerentity, (p_220040_1_) -> {p_220040_1_.sendBreakAnimation(context.getHand());});
	    				  playerentity.setHeldItem(hand, newStack);	  
	    			  }  
	    		  }  
	    	  }
	    	  return ActionResultType.SUCCESS;
	    	  } else {
	    		  return ActionResultType.PASS;  
	    	  }
	      }
}
