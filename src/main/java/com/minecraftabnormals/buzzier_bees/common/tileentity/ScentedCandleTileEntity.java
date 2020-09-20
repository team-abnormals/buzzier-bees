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
		BlockState state = this.world.getBlockState(this.pos);
		if (state.get(ScentedCandleBlock.LIT)) {
			ScentedCandleBlock candle = ((ScentedCandleBlock) state.getBlock());
			Effect effect = candle.candleEffectInstance.get();
			if (effect != null) {
				int duration = candle.duration;
				int level = candle.level;
				for (LivingEntity entity : world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(this.pos).grow(state.get(ScentedCandleBlock.CANDLES)))) {
					EffectInstance activeEffect = entity.getActivePotionEffect(effect);
					if (activeEffect == null || activeEffect.getDuration() <= 25) {
						boolean instant = effect.isInstant();
						if (instant && this.world.getGameTime() % 300 == 0) {
							entity.addPotionEffect(new EffectInstance(effect, 5, level, true, true));
						} else if (!instant) {
							entity.addPotionEffect(new EffectInstance(effect, duration, level, true, true));
						}
					}
				}
			}
		}
	}
}
