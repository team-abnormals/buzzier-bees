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

	public ItemStack finishUsingItem(ItemStack stack, World worldIn, LivingEntity entityLiving) {
		super.finishUsingItem(stack, worldIn, entityLiving);
		if (entityLiving instanceof ServerPlayerEntity) {
			ServerPlayerEntity serverplayerentity = (ServerPlayerEntity) entityLiving;
			CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
			serverplayerentity.awardStat(Stats.ITEM_USED.get(this));
		}
		if (!worldIn.isClientSide) {
			entityLiving.removeEffect(Effects.POISON);
		}
		if (entityLiving instanceof PlayerEntity && !((PlayerEntity) entityLiving).abilities.instabuild) {
			return new ItemStack(BBItems.HONEY_WAND.get());
		}
		return stack;
	}


	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		target.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 160, 4, true, true));
		if (attacker instanceof PlayerEntity && !((PlayerEntity) attacker).abilities.instabuild) {
			attacker.setItemInHand(attacker.getUsedItemHand(), new ItemStack(BBItems.HONEY_WAND.get()));
		}
		return true;
	}
}

