package com.minecraftabnormals.buzzier_bees.core.other;

import com.minecraftabnormals.buzzier_bees.common.advancement.ImprovedEmptyTrigger;
import com.minecraftabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = BuzzierBees.MOD_ID)
public class BBCriteriaTriggers {
	public static final ImprovedEmptyTrigger HONEY_APPLE_CURE = CriteriaTriggers.register(new ImprovedEmptyTrigger(prefix("honey_apple_cure")));
	public static final ImprovedEmptyTrigger HONEY_BREAD_CURE = CriteriaTriggers.register(new ImprovedEmptyTrigger(prefix("honey_bread_cure")));
	public static final ImprovedEmptyTrigger GLAZED_PORKCHOP_CURE = CriteriaTriggers.register(new ImprovedEmptyTrigger(prefix("glazed_porkchop_cure")));

	private static ResourceLocation prefix(String name) {
		return new ResourceLocation(BuzzierBees.MOD_ID, name);
	}
}
