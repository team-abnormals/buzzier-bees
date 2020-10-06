package com.minecraftabnormals.buzzier_bees.core.other;

import java.util.function.ToIntFunction;

import com.minecraftabnormals.buzzier_bees.common.blocks.CandleBlock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

public class BBProperties {
	public static final Block.Properties FLOWER 	= Block.Properties.create(Material.PLANTS).notSolid().doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT);
	public static final Block.Properties FLOWER_POT = Block.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().notSolid();

	public static final Block.Properties HONEYCOMB_BRICKS 	= Block.Properties.create(Material.CLAY, MaterialColor.ADOBE).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.0F, 6.0F).sound(SoundType.CORAL);
	public static final Block.Properties WAX_BLOCK 			= Block.Properties.create(Material.CORAL, MaterialColor.YELLOW).slipperiness(0.95F).hardnessAndResistance(0.3F).sound(SoundType.CORAL).harvestTool(ToolType.HOE);
	public static final Block.Properties CRYSTALLIZED_HONEY = Block.Properties.create(Material.CAKE).notSolid().slipperiness(0.98F).hardnessAndResistance(0.3F).sound(SoundType.GLASS);
	
	public static final Block.Properties CANDLE 		= Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).setLightLevel(getCandleLightLevel(11)).notSolid().sound(SoundType.WOOD);
	public static final Block.Properties SOUL_CANDLE 	= Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).setLightLevel(getCandleLightLevel(7)).notSolid().sound(SoundType.WOOD);

	private static ToIntFunction<BlockState> getCandleLightLevel(int base) {
		return (state) -> {
			return state.get(CandleBlock.LIT) ? (base + state.get(CandleBlock.CANDLES)) : 0;
		};
	}
}
