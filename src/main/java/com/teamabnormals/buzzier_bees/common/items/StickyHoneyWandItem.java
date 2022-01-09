package com.teamabnormals.buzzier_bees.common.items;

import com.teamabnormals.buzzier_bees.core.registry.BBItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class StickyHoneyWandItem extends Item {
	public StickyHoneyWandItem(Properties properties) {
		super(properties);
	}

	public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entityLiving) {
		super.finishUsingItem(stack, worldIn, entityLiving);
		if (entityLiving instanceof ServerPlayer) {
			ServerPlayer serverplayerentity = (ServerPlayer) entityLiving;
			CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
			serverplayerentity.awardStat(Stats.ITEM_USED.get(this));
		}
		if (!worldIn.isClientSide) {
			entityLiving.removeEffect(MobEffects.POISON);
		}
		if (entityLiving instanceof Player && !((Player) entityLiving).getAbilities().instabuild) {
			return new ItemStack(BBItems.HONEY_WAND.get());
		}
		return stack;
	}


	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 160, 4, true, true));
		if (attacker instanceof Player && !((Player) attacker).getAbilities().instabuild) {
			attacker.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(BBItems.HONEY_WAND.get()));
		}
		return true;
	}
}

