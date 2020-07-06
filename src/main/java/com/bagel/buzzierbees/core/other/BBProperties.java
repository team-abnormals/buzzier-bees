package com.bagel.buzzierbees.core.other;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BBProperties {
	public static final Block.Properties FLOWER = Block.Properties.create(Material.PLANTS).notSolid().doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT);
	public static final Block.Properties CANDLE = Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD);
	public static final Block.Properties POT    		= Block.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().notSolid();
}
