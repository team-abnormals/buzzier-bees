package com.minecraftabnormals.buzzier_bees.common.blocks;

import com.google.common.base.Supplier;
import com.teamabnormals.abnormals_core.common.blocks.AbnormalsFlowerBlock;

import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;

public class ButtercupBlock extends AbnormalsFlowerBlock {
	private final Supplier<Effect> stewEffect;
	private final int stewEffectDuration;

	public ButtercupBlock(Supplier<Effect> stewEffect, int stewEffectDuration, Properties properties) {
		super(Effects.BLINDNESS, stewEffectDuration, properties);
		this.stewEffect = stewEffect;
		this.stewEffectDuration = stewEffectDuration;
	}

	@Override
	public Effect getStewEffect() {
		return this.stewEffect.get();
	}

	@Override
	public int getStewEffectDuration() {
		return this.stewEffectDuration;
	}
}