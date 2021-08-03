package com.minecraftabnormals.buzzier_bees.common.tileentity;

import com.minecraftabnormals.buzzier_bees.common.blocks.ScentedCandleBlock;
import com.minecraftabnormals.buzzier_bees.core.registry.BBTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;

public class ScentedCandleTileEntity extends TileEntity implements ITickableTileEntity {

	public ScentedCandleTileEntity() {
		super(BBTileEntities.SCENTED_CANDLE.get());
	}

	@Override
	public void tick() {
		BlockState state = this.level.getBlockState(this.worldPosition);
		if (state.getValue(ScentedCandleBlock.LIT)) {
			ScentedCandleBlock candle = ((ScentedCandleBlock) state.getBlock());
			Effect effect = candle.candleEffectInstance.get();
			if (effect != null) {
				int duration = candle.duration;
				int amplifier = candle.amplifier;
				for (LivingEntity entity : level.getEntitiesOfClass(LivingEntity.class, new AxisAlignedBB(this.worldPosition).inflate(state.getValue(ScentedCandleBlock.CANDLES)))) {
					EffectInstance activeEffect = entity.getEffect(effect);
					if (activeEffect == null || activeEffect.getDuration() <= 25) {
						boolean instant = effect.isInstantenous();
						if (instant && this.level.getGameTime() % 300 == 0) {
							entity.addEffect(new EffectInstance(effect, 5, amplifier, true, true));
						} else if (!instant) {
							entity.addEffect(new EffectInstance(effect, duration, amplifier, true, true));
						}
					}
				}
			}
		}
	}
}
