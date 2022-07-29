package com.teamabnormals.buzzier_bees.core.other;

import com.teamabnormals.blueprint.common.advancement.EmptyTrigger;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = BuzzierBees.MOD_ID)
public class BBCriteriaTriggers {
	public static final EmptyTrigger HONEY_APPLE_CURE = registerEmptyTrigger("honey_apple_cure");
	public static final EmptyTrigger HONEY_BREAD_CURE = registerEmptyTrigger("honey_bread_cure");
	public static final EmptyTrigger GLAZED_PORKCHOP_CURE = registerEmptyTrigger("glazed_porkchop_cure");

	private static EmptyTrigger registerEmptyTrigger(String name) {
		return CriteriaTriggers.register(new EmptyTrigger(new ResourceLocation(BuzzierBees.MOD_ID, name)));
	}
}
