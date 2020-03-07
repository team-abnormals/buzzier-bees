package com.bagel.buzzierbees.common.effects;

import com.bagel.buzzierbees.core.registry.BBCriteriaTriggers;
import com.google.common.collect.ImmutableList;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.*;

public class AntiEffect extends InstantEffect {
    private final ImmutableList<EffectInstance> counteredEffects;

    public AntiEffect(EffectType typeIn, int liquidColorIn, EffectInstance... counteredEffectsIn) {
        super(typeIn, liquidColorIn);
        counteredEffects = ImmutableList.copyOf(counteredEffectsIn);
    }

    @Override
    public void performEffect(LivingEntity entityLiving, int amplifier) {
    	if (entityLiving instanceof ServerPlayerEntity) {
			ServerPlayerEntity serverplayerentity = (ServerPlayerEntity) entityLiving;
			if(!serverplayerentity.getEntityWorld().isRemote() && entityLiving.isPotionActive(counteredEffects.get(0).getPotion())) {
				BBCriteriaTriggers.USE_CURE.trigger(serverplayerentity); 
			}
		}
    	
    	for (int i = 0; i < counteredEffects.size(); ++i) {
			Effect effect = counteredEffects.get(i).getPotion();
			entityLiving.removePotionEffect(effect);
		}
    }
}