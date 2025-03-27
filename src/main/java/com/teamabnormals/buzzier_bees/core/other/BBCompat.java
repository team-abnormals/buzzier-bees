package com.teamabnormals.buzzier_bees.core.other;

import com.teamabnormals.buzzier_bees.common.dispenser.BeeBottleDispenseBehavior;
import com.teamabnormals.buzzier_bees.common.dispenser.BugBottleDispenseBehavior;
import com.teamabnormals.buzzier_bees.core.registry.BBItems;
import net.minecraft.world.level.block.DispenserBlock;

public class BBCompat {

	public static void registerCompat() {
		registerDispenseBehaviors();
	}

	private static void registerDispenseBehaviors() {
		DispenserBlock.registerBehavior(BBItems.BOTTLE_OF_BEE.get(), new BeeBottleDispenseBehavior());
		DispenserBlock.registerBehavior(BBItems.BOTTLE_OF_SILVERFISH.get(), new BugBottleDispenseBehavior());
		DispenserBlock.registerBehavior(BBItems.BOTTLE_OF_ENDERMITE.get(), new BugBottleDispenseBehavior());
	}
}
