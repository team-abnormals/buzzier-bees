package com.bagel.buzzierbees.core.other;

import com.bagel.buzzierbees.core.registry.BBBlocks;
import com.teamabnormals.abnormals_core.core.utils.DataUtils;

public class BBCompostables {
	
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
}
