package com.teamabnormals.buzzier_bees.common.item;

import com.google.common.collect.ImmutableList;
import com.teamabnormals.buzzier_bees.common.advancement.BBEmptyTrigger;
import com.teamabnormals.buzzier_bees.core.other.BBCriteriaTriggers;
import com.teamabnormals.buzzier_bees.core.registry.BBItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CuringItem extends Item {
	private final ImmutableList<MobEffectInstance> counteredEffects;

	public CuringItem(Properties properties, MobEffectInstance... counteredEffectsIn) {
		super(properties);
		counteredEffects = ImmutableList.copyOf(counteredEffectsIn);
	}

	public BBEmptyTrigger getTrigger() {
		if (this == BBItems.HONEY_APPLE.get()) {
			return BBCriteriaTriggers.HONEY_APPLE_CURE;
		} else if (this == BBItems.HONEY_BREAD.get()) {
			return BBCriteriaTriggers.HONEY_BREAD_CURE;
		} else if (this == BBItems.GLAZED_PORKCHOP.get()) {
			return BBCriteriaTriggers.GLAZED_PORKCHOP_CURE;
		}
		return null;
	}

	public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entityLiving) {
		super.finishUsingItem(stack, worldIn, entityLiving);
		if (entityLiving instanceof ServerPlayer serverPlayer) {
			CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, stack);
			serverPlayer.awardStat(Stats.ITEM_USED.get(this));
			if (!worldIn.isClientSide() && entityLiving.hasEffect(counteredEffects.get(0).getEffect())) {
				this.getTrigger().trigger(serverPlayer);
			}
		}

		if (!worldIn.isClientSide) {
			for (int i = 0; i < counteredEffects.size(); ++i) {
				MobEffect effect = counteredEffects.get(i).getEffect();
				entityLiving.removeEffect(effect);
			}
		}

		return stack;
	}
}
