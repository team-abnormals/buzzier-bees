package com.bagel.buzzierbees.common.blocks;

import javax.annotation.Nullable;

import com.bagel.buzzierbees.core.registry.ModTileEntities;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.potion.Effect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class ScentedCandleBlock extends CandleBlock implements IWaterLoggable {
	public Effect candleEffectInstance;
	public int duration;
	public int level;
	public ScentedCandleBlock(Effect candleEffectInstance, int duration, int level, Block.Properties properties) {
		super(properties);
		this.candleEffectInstance = candleEffectInstance;	
		this.duration = duration;
		this.level = level;
	}

   @Override
   public boolean hasTileEntity(BlockState state) {
	   return true;
   }
   
   @Nullable
   @Override
   public TileEntity createTileEntity(BlockState state, IBlockReader world) {
	   return ModTileEntities.SCENTED_CANDLE.get().create();
   }
}
