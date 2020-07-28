package com.minecraftabnormals.buzzier_bees.core.other;

import com.minecraftabnormals.buzzier_bees.core.BuzzierBees;
import com.minecraftabnormals.abnormals_core.common.advancement.EmptyTrigger;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = BuzzierBees.MODID)
public class BBCriteriaTriggers {
	public static final EmptyTrigger HONEY_APPLE_CURE = CriteriaTriggers.register(new EmptyTrigger(prefix("honey_apple_cure")));
	public static final EmptyTrigger HONEY_BREAD_CURE = CriteriaTriggers.register(new EmptyTrigger(prefix("honey_bread_cure")));
	public static final EmptyTrigger GLAZED_PORKCHOP_CURE = CriteriaTriggers.register(new EmptyTrigger(prefix("glazed_porkchop_cure")));
	
	private static ResourceLocation prefix(String name) {
		return new ResourceLocation(BuzzierBees.MODID, name);
	}
}
