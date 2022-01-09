package com.teamabnormals.buzzier_bees.client.render;

import com.teamabnormals.buzzier_bees.client.model.GrizzlyBearModel;
import com.teamabnormals.buzzier_bees.common.entities.GrizzlyBearEntity;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import com.teamabnormals.buzzier_bees.core.registry.BBEntities;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class GrizzlyBearRenderer extends MobRenderer<GrizzlyBearEntity, GrizzlyBearModel<GrizzlyBearEntity>> {
	public GrizzlyBearRenderer(EntityRendererProvider.Context context) {
		super(context, new GrizzlyBearModel<>(context.bakeLayer(BBEntities.GRIZZLY_BEAR_LAYER)), 0.9F);
	}

	@Override
	public ResourceLocation getTextureLocation(GrizzlyBearEntity bear) {
		if (bear.isSleeping())
			return new ResourceLocation(BuzzierBees.MOD_ID, "textures/entity/grizzly_bear/grizzly_bear_sleeping.png");
		return new ResourceLocation(BuzzierBees.MOD_ID, "textures/entity/grizzly_bear/grizzly_bear.png");
	}
}
