package com.bagel.buzzierbees.common.blocks;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.potion.Effect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class ScentedCandleBlock extends CandleBlock implements IWaterLoggable {
	public Effect candleEffectInstance;
	protected ScentedCandleBlock(Effect candleEffectInstance, Block.Properties properties) {
		super(properties);
		this.candleEffectInstance = candleEffectInstance;	
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
