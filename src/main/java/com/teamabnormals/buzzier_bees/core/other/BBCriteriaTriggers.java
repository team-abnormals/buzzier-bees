package com.teamabnormals.buzzier_bees.core.other;

import com.teamabnormals.buzzier_bees.common.advancement.BBEmptyTrigger;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.resources.ResourceLocation;

public class BBCriteriaTriggers {
	public static final BBEmptyTrigger HONEY_APPLE_CURE = registerEmptyTrigger("honey_apple_cure");
	public static final BBEmptyTrigger HONEY_BREAD_CURE = registerEmptyTrigger("honey_bread_cure");
	public static final BBEmptyTrigger GLAZED_PORKCHOP_CURE = registerEmptyTrigger("glazed_porkchop_cure");

	private static BBEmptyTrigger registerEmptyTrigger(String name) {
		return CriteriaTriggers.register(new BBEmptyTrigger(new ResourceLocation(BuzzierBees.MOD_ID, name)));
	}
}
