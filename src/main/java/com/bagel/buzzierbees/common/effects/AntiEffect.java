package com.bagel.buzzierbees.common.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.InstantEffect;

public class AntiEffect extends InstantEffect {
    private final Effect counteredEffect;

    protected AntiEffect(Effect countered, EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
        counteredEffect = countered;
    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
        entityLivingBaseIn.removePotionEffect(counteredEffect);
    }
}
