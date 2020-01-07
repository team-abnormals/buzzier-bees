package com.bagel.buzzierbees.common.items;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.BeehiveBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HoneyWandItem extends Item {
	
	public HoneyWandItem(Item.Properties properties) {
		super(properties);
	}

	public ActionResultType onItemUse(ItemUseContext context) {
	      World world = context.getWorld();
	      BlockPos blockpos = context.getPos();
	      BlockState blockstate = world.getBlockState(blockpos);
	      if (blockstate.getBlock() instanceof BeehiveBlock && (context.getFace() == blockstate.get(BeehiveBlock.field_226872_b_)) && blockstate.get(BeehiveBlock.field_226873_c_) == 5) {
	    	  PlayerEntity playerentity = context.getPlayer();
	    	  BlockState blockstate2 = null;
	    	  world.playSound((PlayerEntity)null, blockpos, SoundEvents.field_226135_eP_, SoundCategory.NEUTRAL, 1.0F, 1.0F);
	    	  blockstate2 = blockstate.with(BeehiveBlock.field_226873_c_, Integer.valueOf(0));
	    	  if (blockstate2 != null) {
	    		  if (!world.isRemote) {
	    			  world.setBlockState(blockpos, blockstate2, 11);
	    			  if (playerentity != null) {
						  context.getPlayer().setHeldItem(context.getPlayer().getActiveHand(), new ItemStack(ModItems.STICKY_HONEY_WAND));
	    				  //context.getItem().damageItem(1, playerentity, (p_220040_1_) -> {p_220040_1_.sendBreakAnimation(context.getHand());});
	    			  }
	    		  }  
	    	  }
	    	  return ActionResultType.SUCCESS;
	    	  } else {
	    		  return ActionResultType.PASS;  
	    	  }
	      }

}

