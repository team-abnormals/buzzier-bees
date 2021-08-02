package com.minecraftabnormals.buzzier_bees.core;

import com.minecraftabnormals.abnormals_core.core.annotations.ConfigKey;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import org.apache.commons.lang3.tuple.Pair;

@EventBusSubscriber(modid = BuzzierBees.MOD_ID)
public class BBConfig {

	public static class Common {

		@ConfigKey("tall_flower_duplication")
		public final ConfigValue<Boolean> tallFlowerDuplication;

		@ConfigKey("short_flower_duplication")
		public final ConfigValue<Boolean> shortFlowerDuplication;

		Common(ForgeConfigSpec.Builder builder) {
			builder.push("flowers");
			tallFlowerDuplication = builder.define("Allow bonemealing tall flowers to duplicate them", true);
			shortFlowerDuplication = builder.define("Allow bonemealing short flowers to duplicate them", true);
			builder.pop();
		}
	}

	public static final ForgeConfigSpec COMMON_SPEC;
	public static final Common COMMON;

	static {
		final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
		COMMON_SPEC = specPair.getRight();
		COMMON = specPair.getLeft();
	}
}