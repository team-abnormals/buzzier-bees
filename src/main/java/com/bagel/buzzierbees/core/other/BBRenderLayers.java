package com.bagel.buzzierbees.core.other;

import com.bagel.buzzierbees.core.registry.BBBlocks;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class BBRenderLayers {
	
	public static void setupRenderLayer()
	{
		RenderTypeLookup.setRenderLayer(BBBlocks.HONEY_LAMP.get(),RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(BBBlocks.HONEY_POT.get(),RenderType.getTranslucent());

		RenderTypeLookup.setRenderLayer(BBBlocks.HIVE_DOOR.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HIVE_TRAPDOOR.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HIVE_LADDER.get(),RenderType.getCutout());

		RenderTypeLookup.setRenderLayer(BBBlocks.WHITE_CLOVER.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.PINK_CLOVER.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.CARTWHEEL.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.VIOLET.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.COLUMBINE.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.DIANTHUS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.BLUEBELL.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.YELLOW_HIBISCUS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.ORANGE_HIBISCUS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.RED_HIBISCUS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.PINK_HIBISCUS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.MAGENTA_HIBISCUS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.PURPLE_HIBISCUS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.BIRD_OF_PARADISE.get(),RenderType.getCutout());

		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_WHITE_CLOVER.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_PINK_CLOVER.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_CARTWHEEL.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_VIOLET.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_COLUMBINE.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_DIANTHUS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_BLUEBELL.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_YELLOW_HIBISCUS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_ORANGE_HIBISCUS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_RED_HIBISCUS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_PINK_HIBISCUS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_MAGENTA_HIBISCUS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_PURPLE_HIBISCUS.get(),RenderType.getCutout());
		
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_BIRD_OF_PARADISE.get(),RenderType.getCutout());
	}
}

