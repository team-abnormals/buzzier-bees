package com.teamabnormals.buzzier_bees.core;

import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.ConfigValue;
import org.apache.commons.lang3.tuple.Pair;

public class BBConfig {

	public static class Common {
		public final ConfigValue<Boolean> tallFlowerDuplication;
		public final ConfigValue<Boolean> shortFlowerDuplication;

		Common(ModConfigSpec.Builder builder) {
			builder.push("items");
			builder.push("bone_meal");
			tallFlowerDuplication = builder.comment("If tall flowers can be fertilized with Bone Meal to duplicate").define("Tall flower fertilizing", true);
			shortFlowerDuplication = builder.comment("If short flowers can be fertilized with Bone Meal to duplicate").define("Short flower fertilizing", true);
			builder.pop();
			builder.pop();
		}
	}

	public static final ModConfigSpec COMMON_SPEC;
	public static final Common COMMON;

	static {
		final Pair<Common, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(Common::new);
		COMMON_SPEC = specPair.getRight();
		COMMON = specPair.getLeft();
	}
}