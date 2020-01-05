package com.bagel.buzzierbees.common.items;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.stats.Stats;
import net.minecraft.world.World;

public class CuringItem extends Item {
	private final Effect cure;
	
	public CuringItem(Properties properties, Effect cure) {
		super(properties);
		this.cure = cure;
	}

	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
	      super.onItemUseFinish(stack, worldIn, entityLiving);
	      if (entityLiving instanceof ServerPlayerEntity) {
	         ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)entityLiving;
	         CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
	         serverplayerentity.addStat(Stats.ITEM_USED.get(this));
	      }

	      if (!worldIn.isRemote) {
	         entityLiving.removePotionEffect(cure);
	      }
		return stack;
	   }
}
