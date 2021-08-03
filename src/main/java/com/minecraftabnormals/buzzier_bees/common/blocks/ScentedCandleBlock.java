package com.minecraftabnormals.buzzier_bees.common.blocks;

import com.minecraftabnormals.buzzier_bees.core.registry.BBTileEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.DyeColor;
import net.minecraft.potion.Effect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class ScentedCandleBlock extends CandleBlock {
	public Supplier<Effect> candleEffectInstance;
	public int duration;
	public int amplifier;

	public ScentedCandleBlock(Supplier<Effect> candleEffectInstance, DyeColor color, Block.Properties properties) {
		super(color, properties);
		this.candleEffectInstance = candleEffectInstance;
		this.duration = 70;
		this.amplifier = 0;
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
		return (0.25F * state.getValue(CANDLES));
	}
}
