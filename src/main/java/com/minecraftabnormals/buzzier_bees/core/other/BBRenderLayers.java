package com.minecraftabnormals.buzzier_bees.core.other;

import com.minecraftabnormals.buzzier_bees.core.registry.BBBlocks;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class BBRenderLayers {

	public static void setupRenderLayer() {
		RenderTypeLookup.setRenderLayer(BBBlocks.HONEY_LAMP.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(BBBlocks.HONEY_POT.get(), RenderType.getTranslucent());

		RenderTypeLookup.setRenderLayer(BBBlocks.WHITE_CLOVER.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.PINK_CLOVER.get(), RenderType.getCutout());

		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_WHITE_CLOVER.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_PINK_CLOVER.get(), RenderType.getCutout());
	}
}
