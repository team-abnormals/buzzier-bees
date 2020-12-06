package com.minecraftabnormals.buzzier_bees.common.items;

import com.minecraftabnormals.buzzier_bees.core.registry.BBItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.world.World;

public class StickyHoneyWandItem extends Item {
	public StickyHoneyWandItem(Properties properties) {
		super(properties);
	}

	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
		super.onItemUseFinish(stack, worldIn, entityLiving);
		if (entityLiving instanceof ServerPlayerEntity) {
			ServerPlayerEntity serverplayerentity = (ServerPlayerEntity) entityLiving;
			CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
			serverplayerentity.addStat(Stats.ITEM_USED.get(this));
		}
		if (!worldIn.isRemote) {
			entityLiving.removePotionEffect(Effects.POISON);
		}
		if (entityLiving instanceof PlayerEntity && !((PlayerEntity) entityLiving).abilities.isCreativeMode) {
			return new ItemStack(BBItems.HONEY_WAND.get());
		}
		return stack;
	}


	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 160, 4, true, true));
		if (attacker instanceof PlayerEntity && !((PlayerEntity) attacker).abilities.isCreativeMode) {
			attacker.setHeldItem(attacker.getActiveHand(), new ItemStack(BBItems.HONEY_WAND.get()));
		}
		return true;
	}
}

