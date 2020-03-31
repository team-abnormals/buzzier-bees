package com.bagel.buzzierbees.core.util;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class PropertyUtils {
	public static final Block.Properties FLOWER = Block.Properties.create(Material.PLANTS).notSolid().doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT);
	public static final Block.Properties CANDLE = Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD);
	
	public static final Block.Properties POT    		= Block.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().notSolid();
	public static final Block.Properties POT_LIGHT    	= Block.Properties.create(Material.MISCELLANEOUS).lightValue(6).zeroHardnessAndResistance().notSolid();
	public static final Block.Properties POT_BRIGHT    	= Block.Properties.create(Material.MISCELLANEOUS).lightValue(14).zeroHardnessAndResistance().notSolid();
}
