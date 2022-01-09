package com.teamabnormals.buzzier_bees.common.items;

import com.google.common.collect.ImmutableList;
import com.teamabnormals.buzzier_bees.common.advancement.ImprovedEmptyTrigger;
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

	public ImprovedEmptyTrigger getTrigger() {
		Item item = this.asItem();
		if (item == BBItems.HONEY_APPLE.get()) {
			return BBCriteriaTriggers.HONEY_APPLE_CURE;
		} else if (item == BBItems.HONEY_BREAD.get()) {
			return BBCriteriaTriggers.HONEY_BREAD_CURE;
		} else if (item == BBItems.GLAZED_PORKCHOP.get()) {
			return BBCriteriaTriggers.GLAZED_PORKCHOP_CURE;
		}
		return null;
	}

	public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entityLiving) {
		super.finishUsingItem(stack, worldIn, entityLiving);
		if (entityLiving instanceof ServerPlayer) {
			ServerPlayer serverplayerentity = (ServerPlayer) entityLiving;
			CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
			serverplayerentity.awardStat(Stats.ITEM_USED.get(this));
			if (!worldIn.isClientSide() && entityLiving.hasEffect(counteredEffects.get(0).getEffect())) {
				this.getTrigger().trigger(serverplayerentity);
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
