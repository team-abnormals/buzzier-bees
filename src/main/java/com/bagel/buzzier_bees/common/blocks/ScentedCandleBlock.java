package com.bagel.buzzier_bees.common.blocks;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.bagel.buzzier_bees.core.registry.BBTileEntities;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class ScentedCandleBlock extends CandleBlock implements IWaterLoggable {
	public Supplier<Effect> candleEffectInstance;
	public int duration;
	public int level;
	
	public ScentedCandleBlock(Supplier<Effect> candleEffectInstance, Block.Properties properties) {
		super(properties);
		this.candleEffectInstance = candleEffectInstance;	
		this.duration = 70;
		this.level = 0;
	}

   @Override
   public boolean hasTileEntity(BlockState state) {
	   return true;
   }
   
   @Nullable
   @Override
   public TileEntity createTileEntity(BlockState state, IBlockReader world) {
	   return BBTileEntities.SCENTED_CANDLE.get().create();
   }
   
   @Override
   public float getEnchantPowerBonus(BlockState state, IWorldReader world, BlockPos pos) {
	   return (0.25F * state.get(CANDLES));	
   }
   
   @Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		return ActionResultType.FAIL;	
	}
}
