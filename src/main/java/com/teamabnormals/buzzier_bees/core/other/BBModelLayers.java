package com.teamabnormals.buzzier_bees.core.other;

import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.client.model.geom.ModelLayerLocation;

public class BBModelLayers {
	public static final ModelLayerLocation GRIZZLY_BEAR = register("grizzly_bear");
	public static final ModelLayerLocation MOOBLOOM = register("moobloom");

	public static ModelLayerLocation register(String name) {
		return register(name, "main");
	}

	public static ModelLayerLocation register(String name, String layer) {
		return new ModelLayerLocation(BuzzierBees.location(name), layer);
	}
}
