package com.bagel.buzzierbees.core.registry;

import com.bagel.buzzierbees.core.BuzzierBees;
import com.teamabnormals.abnormals_core.common.advancement.EmptyTrigger;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = BuzzierBees.MODID)
public class BBCriteriaTriggers {
	public static final EmptyTrigger HONEY_APPLE_CURE = CriteriaTriggers.register(new EmptyTrigger(prefix("honey_apple_cure")));
	public static final EmptyTrigger HONEY_BREAD_CURE = CriteriaTriggers.register(new EmptyTrigger(prefix("honey_bread_cure")));
	public static final EmptyTrigger GLAZED_PORKCHOP_CURE = CriteriaTriggers.register(new EmptyTrigger(prefix("glazed_porkchop_cure")));
	public static final EmptyTrigger USE_CURE = CriteriaTriggers.register(new EmptyTrigger(prefix("use_cure")));
	
	private static ResourceLocation prefix(String name) {
		return new ResourceLocation(BuzzierBees.MODID, name);
	}
}
