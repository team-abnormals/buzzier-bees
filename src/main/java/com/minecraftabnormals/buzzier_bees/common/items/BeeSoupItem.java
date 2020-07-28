package com.minecraftabnormals.buzzier_bees.common.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class BeeSoupItem extends Item {

	public BeeSoupItem(Properties properties) {
		super(properties);
	}
	
	@Override
	public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }
	
	@Override
	public SoundEvent getDrinkSound() {
	   return SoundEvents.ITEM_HONEY_BOTTLE_DRINK;
	}

	@Override
	public SoundEvent getEatSound() {
	   return SoundEvents.ITEM_HONEY_BOTTLE_DRINK;
	}
	
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
	      ItemStack itemstack = super.onItemUseFinish(stack, worldIn, entityLiving);
	      return entityLiving instanceof PlayerEntity && ((PlayerEntity)entityLiving).abilities.isCreativeMode ? itemstack : new ItemStack(Items.BOWL);
	}
}
