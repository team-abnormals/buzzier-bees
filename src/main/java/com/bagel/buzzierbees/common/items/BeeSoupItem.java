package com.bagel.buzzierbees.common.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;

public class BeeSoupItem extends Item {

	public BeeSoupItem(Item.Properties p_i50054_1_) {
		super(p_i50054_1_);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }
	
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
	      ItemStack itemstack = super.onItemUseFinish(stack, worldIn, entityLiving);
	      return entityLiving instanceof PlayerEntity && ((PlayerEntity)entityLiving).abilities.isCreativeMode ? itemstack : new ItemStack(Items.BOWL);
	}
}
