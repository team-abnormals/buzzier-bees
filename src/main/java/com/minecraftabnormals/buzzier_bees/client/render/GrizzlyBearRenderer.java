package com.minecraftabnormals.buzzier_bees.client.render;

import com.minecraftabnormals.buzzier_bees.client.model.GrizzlyBearModel;
import com.minecraftabnormals.buzzier_bees.common.entities.GrizzlyBearEntity;
import com.minecraftabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class GrizzlyBearRenderer extends MobRenderer<GrizzlyBearEntity, GrizzlyBearModel<GrizzlyBearEntity>> {
	public GrizzlyBearRenderer(EntityRendererManager renderManager) {
		super(renderManager, new GrizzlyBearModel<>(9, 0.0F), 0.9F);
	}

	@Override
	public ResourceLocation getEntityTexture(GrizzlyBearEntity bear) {
		if (bear.isSleeping())
			return new ResourceLocation(BuzzierBees.MOD_ID, "textures/entity/grizzly_bear/grizzly_bear_sleeping.png");
		return new ResourceLocation(BuzzierBees.MOD_ID, "textures/entity/grizzly_bear/grizzly_bear.png");
	}
}
