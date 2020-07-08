package com.bagel.buzzier_bees.core.other;

import com.bagel.buzzier_bees.core.registry.BBBlocks;
import com.teamabnormals.abnormals_core.core.utils.DataUtils;

public class BBCompostables {
	
	public static void registerCompostables() {
		DataUtils.registerCompostable(0.65F, BBBlocks.PINK_CLOVER.get());		
		DataUtils.registerCompostable(0.65F, BBBlocks.WHITE_CLOVER.get());
	}
}
