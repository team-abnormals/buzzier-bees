package com.bagel.buzzierbees.common.items;

import com.google.common.collect.ImmutableList;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.stats.Stats;
import net.minecraft.world.World;

import java.util.Iterator;

public class CuringItem extends Item {
	private final ImmutableList<EffectInstance> counteredEffects;
	
	public CuringItem(Properties properties, EffectInstance... counteredEffectsIn) {
		super(properties);
		counteredEffects = ImmutableList.copyOf(counteredEffectsIn);
	}

	@SuppressWarnings("rawtypes")
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
		super.onItemUseFinish(stack, worldIn, entityLiving);
		if (entityLiving instanceof ServerPlayerEntity) {
			ServerPlayerEntity serverplayerentity = (ServerPlayerEntity) entityLiving;
			CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
			serverplayerentity.addStat(Stats.ITEM_USED.get(this));
		}

		if (!worldIn.isRemote) {
			Iterator entityEffects = entityLiving.getActivePotionEffects().iterator();
			while (entityEffects.hasNext()) {
				EffectInstance entityEffect = (EffectInstance) entityEffects.next();
				Iterator effectsIterator = counteredEffects.iterator();
				while (effectsIterator.hasNext()) {
					EffectInstance counteredEffectInstance = (EffectInstance) effectsIterator.next();
					Effect counteredEffect = counteredEffectInstance.getPotion();
					if (entityEffect.getPotion() == counteredEffect) {
						entityLiving.removePotionEffect(counteredEffect);
					}
				}
			}
		}
		return stack;
	}
}
