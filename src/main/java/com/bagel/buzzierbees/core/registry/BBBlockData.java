package com.bagel.buzzierbees.core.registry;

import com.bagel.buzzierbees.core.util.DataUtils;

import net.minecraftforge.fml.ModList;

public class BBBlockData {
	public static void registerCompostables() {
		DataUtils.registerCompostable(0.65F, BBBlocks.CARTWHEEL.get());
		DataUtils.registerCompostable(0.65F, BBBlocks.VIOLET.get());		
		DataUtils.registerCompostable(0.65F, BBBlocks.JOLYCE.get());
		DataUtils.registerCompostable(0.65F, BBBlocks.COLUMBINE.get());
		DataUtils.registerCompostable(0.65F, BBBlocks.PINK_CLOVER.get());		
		DataUtils.registerCompostable(0.65F, BBBlocks.WHITE_CLOVER.get());
		DataUtils.registerCompostable(0.65F, BBBlocks.DAYBLOOM.get());
		DataUtils.registerCompostable(0.65F, BBBlocks.BLUEBELL.get());		
		DataUtils.registerCompostable(0.65F, BBBlocks.BIRD_OF_PARADISE.get());
	}
	
	public static void registerFlammables() {
		DataUtils.registerFlammable(BBBlocks.HIVE_PLANKS.get(), 5, 20);
		DataUtils.registerFlammable(BBBlocks.HIVE_SLAB.get(), 5, 20);
		DataUtils.registerFlammable(BBBlocks.HIVE_STAIRS.get(), 5, 20);
		DataUtils.registerFlammable(BBBlocks.HIVE_FENCE.get(), 5, 20);
		DataUtils.registerFlammable(BBBlocks.HIVE_FENCE_GATE.get(), 5, 20);
		
		if(ModList.get().isLoaded("quark")) {
			DataUtils.registerFlammable(BBBlocks.VERTICAL_HIVE_PLANKS.get(), 5, 20);
			DataUtils.registerFlammable(BBBlocks.HIVE_VERTICAL_SLAB.get(), 5, 20);
			DataUtils.registerFlammable(BBBlocks.HIVE_BOOKSHELF.get(), 5, 20);
		}
	}
}

