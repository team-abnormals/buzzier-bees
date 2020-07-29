package com.minecraftabnormals.buzzier_bees.core.other;

import com.minecraftabnormals.buzzier_bees.core.registry.BBBlocks;
import com.teamabnormals.abnormals_core.core.utils.DataUtils;

public class BBCompostables {
	
	public static void registerCompostables() {
		DataUtils.registerCompostable(BBBlocks.PINK_CLOVER.get(), 0.65F);		
		DataUtils.registerCompostable(BBBlocks.WHITE_CLOVER.get(), 0.65F);
	}
}
