package com.bagel.buzzier_bees.common.tileentity;

import java.util.function.Supplier;

import com.bagel.buzzier_bees.common.blocks.ScentedCandleBlock;
import com.bagel.buzzier_bees.core.registry.BBTileEntities;

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
        BlockState blockstate = this.world.getBlockState(this.pos);
        double d0 = (double)(blockstate.get(ScentedCandleBlock.CANDLES));
        boolean water = (blockstate.get(ScentedCandleBlock.WATERLOGGED));
        ScentedCandleBlock candle = ((ScentedCandleBlock)blockstate.getBlock());
        Supplier<Effect> effect = () -> candle.candleEffectInstance.get();
        if (water != true && effect.get() != null) {
            for (LivingEntity entity : world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(pos).grow(d0))) {
         	   if (entity.getActivePotionEffect(effect.get()) == null || (entity.getActivePotionEffect(effect.get()).getDuration() <= 25))  {
         		   if (effect.get().isInstant()) {
         			   if (this.world.getGameTime() % 300 == 0) entity.addPotionEffect(new EffectInstance(effect.get(), 5, candle.level, true, true)); 
         		   } else {
         			  entity.addPotionEffect(new EffectInstance(effect.get(), candle.duration, candle.level, true, true));  
         		   }
         	   }
            }
        }
    }
}
