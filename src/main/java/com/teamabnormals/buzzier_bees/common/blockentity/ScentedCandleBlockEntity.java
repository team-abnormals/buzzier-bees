package com.teamabnormals.buzzier_bees.common.blockentity;

import com.teamabnormals.buzzier_bees.common.blocks.ScentedCandleBlock;
import com.teamabnormals.buzzier_bees.core.registry.BBBlockEntities;
import net.minecraft.client.renderer.texture.Tickable;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class ScentedCandleBlockEntity extends BlockEntity implements Tickable {

	public ScentedCandleBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
		super(BBBlockEntities.SCENTED_CANDLE.get(), p_155229_, p_155230_);
	}

	@Override
	public void tick() {
		BlockState state = this.level.getBlockState(this.worldPosition);
		if (state.getValue(ScentedCandleBlock.LIT)) {
			ScentedCandleBlock candle = ((ScentedCandleBlock) state.getBlock());
			MobEffect effect = candle.candleEffectInstance.get();
			if (effect != null) {
				int duration = candle.duration;
				int amplifier = candle.amplifier;
				for (LivingEntity entity : level.getEntitiesOfClass(LivingEntity.class, new AABB(this.worldPosition).inflate(state.getValue(ScentedCandleBlock.CANDLES)))) {
					MobEffectInstance activeEffect = entity.getEffect(effect);
					if (activeEffect == null || activeEffect.getDuration() <= 25) {
						boolean instant = effect.isInstantenous();
						if (instant && this.level.getGameTime() % 300 == 0) {
							entity.addEffect(new MobEffectInstance(effect, 5, amplifier, true, true));
						} else if (!instant) {
							entity.addEffect(new MobEffectInstance(effect, duration, amplifier, true, true));
						}
					}
				}
			}
		}
	}
}
