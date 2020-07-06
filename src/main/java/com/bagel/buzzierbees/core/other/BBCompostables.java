package com.bagel.buzzierbees.core.other;

import com.bagel.buzzierbees.core.registry.BBBlocks;
import com.teamabnormals.abnormals_core.core.utils.DataUtils;

public class BBCompostables {
	
	public static void registerCompostables() {
		DataUtils.registerCompostable(0.65F, BBBlocks.PINK_CLOVER.get());		
		DataUtils.registerCompostable(0.65F, BBBlocks.WHITE_CLOVER.get());
	}
}
