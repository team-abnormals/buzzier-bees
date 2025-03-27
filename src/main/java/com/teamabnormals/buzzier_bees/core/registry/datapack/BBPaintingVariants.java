package com.teamabnormals.buzzier_bees.core.registry.datapack;

import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.decoration.PaintingVariant;

public class BBPaintingVariants {

	public static final ResourceKey<PaintingVariant> CANDLE = create("candle");

	public static void bootstrap(BootstrapContext<PaintingVariant> context) {
		register(context, CANDLE, 2, 3);
	}

	private static ResourceKey<PaintingVariant> create(String name) {
		return ResourceKey.create(Registries.PAINTING_VARIANT, BuzzierBees.location(name));
	}

	private static void register(BootstrapContext<PaintingVariant> context, ResourceKey<PaintingVariant> key, int width, int height) {
		context.register(key, new PaintingVariant(width, height, key.location()));
	}
}