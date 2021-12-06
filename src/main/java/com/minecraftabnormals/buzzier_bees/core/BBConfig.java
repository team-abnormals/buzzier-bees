package com.minecraftabnormals.buzzier_bees.core;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import org.apache.commons.lang3.tuple.Pair;

@EventBusSubscriber(modid = BuzzierBees.MOD_ID)
public class BBConfig {

	public static class Common {
		public final ConfigValue<Boolean> tallFlowerDuplication;
		public final ConfigValue<Boolean> shortFlowerDuplication;

		Common(ForgeConfigSpec.Builder builder) {
			builder.push("items");
			builder.push("bone_meal");
			tallFlowerDuplication = builder.comment("If tall flowers can be fertilized with Bone Meal to duplicate").define("Tall flower fertilizing", true);
			shortFlowerDuplication = builder.comment("If short flowers can be fertilized with Bone Meal to duplicate").define("Short flower fertilizing", true);
			builder.pop();
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