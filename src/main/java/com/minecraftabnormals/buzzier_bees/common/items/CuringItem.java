package com.minecraftabnormals.buzzier_bees.common.items;

import com.google.common.collect.ImmutableList;
import com.minecraftabnormals.abnormals_core.common.advancement.EmptyTrigger;
import com.minecraftabnormals.buzzier_bees.core.other.BBCriteriaTriggers;
import com.minecraftabnormals.buzzier_bees.core.registry.BBItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.stats.Stats;
import net.minecraft.world.World;

public class CuringItem extends Item {
	private final ImmutableList<EffectInstance> counteredEffects;

	public CuringItem(Properties properties, EffectInstance... counteredEffectsIn) {
		super(properties);
		counteredEffects = ImmutableList.copyOf(counteredEffectsIn);
	}

	public EmptyTrigger getTrigger() {
		Item item = this.getItem();
		if (item == BBItems.HONEY_APPLE.get()) {
			return BBCriteriaTriggers.HONEY_APPLE_CURE;
		} else if (item == BBItems.HONEY_BREAD.get()) {
			return BBCriteriaTriggers.HONEY_BREAD_CURE;
		} else if (item == BBItems.GLAZED_PORKCHOP.get()) {
			return BBCriteriaTriggers.GLAZED_PORKCHOP_CURE;
		}
		return null;
	}

	public ItemStack finishUsingItem(ItemStack stack, World worldIn, LivingEntity entityLiving) {
		super.finishUsingItem(stack, worldIn, entityLiving);
		if (entityLiving instanceof ServerPlayerEntity) {
			ServerPlayerEntity serverplayerentity = (ServerPlayerEntity) entityLiving;
			CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
			serverplayerentity.awardStat(Stats.ITEM_USED.get(this));
			if (!worldIn.isClientSide() && entityLiving.hasEffect(counteredEffects.get(0).getEffect())) {
				this.getTrigger().trigger(serverplayerentity);
			}
		}

		if (!worldIn.isClientSide) {
			for (int i = 0; i < counteredEffects.size(); ++i) {
				Effect effect = counteredEffects.get(i).getEffect();
				entityLiving.removeEffect(effect);
			}
		}

		return stack;
	}
}
