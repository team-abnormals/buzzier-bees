package com.bagel.buzzierbees.core.registry;

import com.bagel.buzzierbees.core.util.DataUtils;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

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
		DataUtils.registerFlammable(BBBlocks.VERTICAL_HIVE_PLANKS.get(), 5, 20);
		DataUtils.registerFlammable(BBBlocks.HIVE_VERTICAL_SLAB.get(), 5, 20);
		DataUtils.registerFlammable(BBBlocks.HIVE_BOOKSHELF.get(), 5, 20);
	}
	
	public static void setupRenderLayer()
	{
		RenderTypeLookup.setRenderLayer(BBBlocks.HONEY_LAMP.get(),RenderType.getTranslucent());

		//Doors and Trapdoors
		RenderTypeLookup.setRenderLayer(BBBlocks.HIVE_DOOR.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HIVE_TRAPDOOR.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HIVE_LADDER.get(),RenderType.getCutout());

		//Flowers
		RenderTypeLookup.setRenderLayer(BBBlocks.WHITE_CLOVER.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(BBBlocks.PINK_CLOVER.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(BBBlocks.CARTWHEEL.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(BBBlocks.VIOLET.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(BBBlocks.COLUMBINE.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(BBBlocks.JOLYCE.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(BBBlocks.BLUEBELL.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(BBBlocks.DAYBLOOM.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(BBBlocks.BIRD_OF_PARADISE.get(),RenderType.getCutoutMipped());

		//Potted Flowers
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_WHITE_CLOVER.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_PINK_CLOVER.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_CARTWHEEL.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_VIOLET.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_COLUMBINE.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_JOLYCE.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_BLUEBELL.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_DAYBLOOM.get(),RenderType.getCutoutMipped());
	}
}

