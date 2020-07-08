package com.bagel.buzzier_bees.common.items;

import com.bagel.buzzier_bees.core.other.BBCriteriaTriggers;
import com.bagel.buzzier_bees.core.registry.BBItems;
import com.google.common.collect.ImmutableList;
import com.teamabnormals.abnormals_core.common.advancement.EmptyTrigger;

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

	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
		super.onItemUseFinish(stack, worldIn, entityLiving);
		if (entityLiving instanceof ServerPlayerEntity) {
			ServerPlayerEntity serverplayerentity = (ServerPlayerEntity) entityLiving;
			CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
			serverplayerentity.addStat(Stats.ITEM_USED.get(this));
			if(!worldIn.isRemote() && entityLiving.isPotionActive(counteredEffects.get(0).getPotion())) {
				this.getTrigger().trigger(serverplayerentity); 
			}
		}

		if (!worldIn.isRemote) {
			for (int i = 0; i < counteredEffects.size(); ++i) {
				Effect effect = counteredEffects.get(i).getPotion();
				entityLiving.removePotionEffect(effect);
			}
	    }

		return stack;
	}
}
