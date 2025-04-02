package com.teamabnormals.buzzier_bees.common.item;

import com.google.common.collect.ImmutableList;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CuringItem extends Item {
	private final ImmutableList<Holder<MobEffect>> counteredEffects;

	public CuringItem(Properties properties, Holder<MobEffect>... counteredEffects) {
		super(properties);
		this.counteredEffects = ImmutableList.copyOf(counteredEffects);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entityLiving) {
		super.finishUsingItem(stack, worldIn, entityLiving);

		if (entityLiving instanceof ServerPlayer serverPlayer) {
			CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, stack);
			serverPlayer.awardStat(Stats.ITEM_USED.get(this));
		}

		if (!worldIn.isClientSide()) {
			for (int i = 0; i < this.counteredEffects.size(); ++i) {
				Holder<MobEffect> effect = this.counteredEffects.get(i);
				entityLiving.removeEffect(effect);
			}
		}

		return stack;
	}
}
