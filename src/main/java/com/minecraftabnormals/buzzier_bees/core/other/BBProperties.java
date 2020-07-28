package com.minecraftabnormals.buzzier_bees.core.other;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BBProperties {
	public static final Block.Properties FLOWER = AbstractBlock.Properties.create(Material.PLANTS).notSolid().doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT);
	public static final Block.Properties CANDLE = AbstractBlock.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD);
	public static final Block.Properties POT    = AbstractBlock.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().notSolid();
}
