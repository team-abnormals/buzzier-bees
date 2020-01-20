package com.bagel.buzzierbees.core.registry;

import net.minecraft.block.ComposterBlock;

public class ModCompostables {
	public static void registerCompostables() {
		ComposterBlock.registerCompostable(0.65F, ModBlocks.CARTWHEEL.get());
		ComposterBlock.registerCompostable(0.65F, ModBlocks.VIOLET.get());		
		ComposterBlock.registerCompostable(0.65F, ModBlocks.JOLYCE.get());
		ComposterBlock.registerCompostable(0.65F, ModBlocks.COLUMBINE.get());
		ComposterBlock.registerCompostable(0.65F, ModBlocks.PINK_CLOVER.get());		
		ComposterBlock.registerCompostable(0.65F, ModBlocks.WHITE_CLOVER.get());
		ComposterBlock.registerCompostable(0.65F, ModBlocks.DAYBLOOM.get());
		ComposterBlock.registerCompostable(0.65F, ModBlocks.BLUEBELL.get());		
		ComposterBlock.registerCompostable(0.65F, ModBlocks.BIRD_OF_PARADISE.get());
	}
}

