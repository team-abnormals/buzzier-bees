package com.teamabnormals.buzzier_bees.core.other;

import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class BBModelLayers {
	public static final ModelLayerLocation GRIZZLY_BEAR = register("grizzly_bear");
	public static final ModelLayerLocation MOOBLOOM = register("moobloom");

	public static ModelLayerLocation register(String name) {
		return register(name, "main");
	}

	public static ModelLayerLocation register(String name, String layer) {
		return new ModelLayerLocation(new ResourceLocation(BuzzierBees.MOD_ID, name), layer);
	}
}
