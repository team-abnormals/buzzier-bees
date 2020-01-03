package com.bagel.buzzierbees.common.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder("buzzierbees")
public class ScentedCandleTileEntity extends TileEntity implements ITickableTileEntity {

    public ScentedCandleTileEntity() {
		super(ModTileEntities.SCENTED_CANDLE.get());
	}

    @Override
    public void tick() {
        BlockState blockstate = this.world.getBlockState(this.pos);
        double d0 = (double)(blockstate.get(ScentedCandleBlock.CANDLES) * 0.5 + 1);
        for (LivingEntity entity : world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(pos).grow(d0))) {
    	   if (entity.getActivePotionEffect(((ScentedCandleBlock)blockstate.getBlock()).candleEffectInstance) == null || (entity.getActivePotionEffect(((ScentedCandleBlock)blockstate.getBlock()).candleEffectInstance).getDuration() <= 25))  {
    		   entity.addPotionEffect(new EffectInstance(((ScentedCandleBlock)blockstate.getBlock()).candleEffectInstance, ((ScentedCandleBlock)blockstate.getBlock()).duration, ((ScentedCandleBlock)blockstate.getBlock()).level, true, true)); 
    	   }
        }
    }
}
