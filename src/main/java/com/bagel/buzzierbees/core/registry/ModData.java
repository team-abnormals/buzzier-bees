package com.bagel.buzzierbees.core.registry;

import com.bagel.buzzierbees.core.registry.util.DataUtils;

import net.minecraftforge.fml.ModList;

public class ModData {
	public static void registerCompostables() {
		DataUtils.registerCompostable(0.65F, ModBlocks.CARTWHEEL.get());
		DataUtils.registerCompostable(0.65F, ModBlocks.VIOLET.get());		
		DataUtils.registerCompostable(0.65F, ModBlocks.JOLYCE.get());
		DataUtils.registerCompostable(0.65F, ModBlocks.COLUMBINE.get());
		DataUtils.registerCompostable(0.65F, ModBlocks.PINK_CLOVER.get());		
		DataUtils.registerCompostable(0.65F, ModBlocks.WHITE_CLOVER.get());
		DataUtils.registerCompostable(0.65F, ModBlocks.DAYBLOOM.get());
		DataUtils.registerCompostable(0.65F, ModBlocks.BLUEBELL.get());		
		DataUtils.registerCompostable(0.65F, ModBlocks.BIRD_OF_PARADISE.get());
	}
	
	public static void registerFlammables() {
		DataUtils.registerFlammable(ModBlocks.HIVE_PLANKS.get(), 5, 20);
		DataUtils.registerFlammable(ModBlocks.HIVE_SLAB.get(), 5, 20);
		DataUtils.registerFlammable(ModBlocks.HIVE_STAIRS.get(), 5, 20);
		DataUtils.registerFlammable(ModBlocks.HIVE_FENCE.get(), 5, 20);
		DataUtils.registerFlammable(ModBlocks.HIVE_FENCE_GATE.get(), 5, 20);
		
		if(ModList.get().isLoaded("quark")) {
			DataUtils.registerFlammable(ModBlocks.VERTICAL_HIVE_PLANKS.get(), 5, 20);
			DataUtils.registerFlammable(ModBlocks.HIVE_VERTICAL_SLAB.get(), 5, 20);
			DataUtils.registerFlammable(ModBlocks.HIVE_BOOKSHELF.get(), 5, 20);
		}
	}
}

