package com.bagel.buzzierbees.core.registry;

import com.bagel.buzzierbees.core.util.DataUtils;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class BBBlockData {
	public static void registerCompostables() {
		DataUtils.registerCompostable(0.65F, BBBlocks.CARTWHEEL.get());
		DataUtils.registerCompostable(0.65F, BBBlocks.VIOLET.get());		
		DataUtils.registerCompostable(0.65F, BBBlocks.DIANTHUS.get());
		DataUtils.registerCompostable(0.65F, BBBlocks.COLUMBINE.get());
		DataUtils.registerCompostable(0.65F, BBBlocks.PINK_CLOVER.get());		
		DataUtils.registerCompostable(0.65F, BBBlocks.WHITE_CLOVER.get());
		DataUtils.registerCompostable(0.65F, BBBlocks.BLUEBELL.get());		
		
		DataUtils.registerCompostable(0.65F, BBBlocks.YELLOW_HIBISCUS.get());
		DataUtils.registerCompostable(0.65F, BBBlocks.ORANGE_HIBISCUS.get());
		DataUtils.registerCompostable(0.65F, BBBlocks.RED_HIBISCUS.get());
		DataUtils.registerCompostable(0.65F, BBBlocks.PINK_HIBISCUS.get());
		DataUtils.registerCompostable(0.65F, BBBlocks.MAGENTA_HIBISCUS.get());
		DataUtils.registerCompostable(0.65F, BBBlocks.PURPLE_HIBISCUS.get());

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

		RenderTypeLookup.setRenderLayer(BBBlocks.HIVE_DOOR.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(BBBlocks.HIVE_TRAPDOOR.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(BBBlocks.HIVE_LADDER.get(),RenderType.getCutoutMipped());

		RenderTypeLookup.setRenderLayer(BBBlocks.WHITE_CLOVER.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(BBBlocks.PINK_CLOVER.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(BBBlocks.CARTWHEEL.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(BBBlocks.VIOLET.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(BBBlocks.COLUMBINE.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(BBBlocks.DIANTHUS.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(BBBlocks.BLUEBELL.get(),RenderType.getCutoutMipped());
		
		RenderTypeLookup.setRenderLayer(BBBlocks.YELLOW_HIBISCUS.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(BBBlocks.ORANGE_HIBISCUS.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(BBBlocks.RED_HIBISCUS.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(BBBlocks.PINK_HIBISCUS.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(BBBlocks.MAGENTA_HIBISCUS.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(BBBlocks.PURPLE_HIBISCUS.get(),RenderType.getCutoutMipped());
		
		RenderTypeLookup.setRenderLayer(BBBlocks.BIRD_OF_PARADISE.get(),RenderType.getCutoutMipped());

		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_WHITE_CLOVER.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_PINK_CLOVER.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_CARTWHEEL.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_VIOLET.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_COLUMBINE.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_DIANTHUS.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_BLUEBELL.get(),RenderType.getCutoutMipped());
		
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_YELLOW_HIBISCUS.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_ORANGE_HIBISCUS.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_RED_HIBISCUS.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_PINK_HIBISCUS.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_MAGENTA_HIBISCUS.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_PURPLE_HIBISCUS.get(),RenderType.getCutoutMipped());
	}
}

