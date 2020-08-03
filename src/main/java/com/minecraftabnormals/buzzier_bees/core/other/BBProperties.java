package com.minecraftabnormals.buzzier_bees.core.other;

import java.util.function.ToIntFunction;

import com.minecraftabnormals.buzzier_bees.common.blocks.CandleBlock;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BBProperties {
	public static final Block.Properties FLOWER 	= AbstractBlock.Properties.create(Material.PLANTS).notSolid().doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT);
	public static final Block.Properties FLOWER_POT = AbstractBlock.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().notSolid();

	public static final Block.Properties CANDLE 		= AbstractBlock.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).setLightLevel(getCandleLightLevel(11)).notSolid().sound(SoundType.WOOD);
	public static final Block.Properties SOUL_CANDLE 	= AbstractBlock.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).setLightLevel(getCandleLightLevel(7)).notSolid().sound(SoundType.WOOD);

	private static ToIntFunction<BlockState> getCandleLightLevel(int base) {
		return (state) -> {
			return !state.get(CandleBlock.WATERLOGGED) ? (base + state.get(CandleBlock.CANDLES)) : 0;
		};
	}
}
