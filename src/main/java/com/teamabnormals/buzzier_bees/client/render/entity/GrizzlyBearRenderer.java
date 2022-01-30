package com.teamabnormals.buzzier_bees.client.render.entity;

import com.teamabnormals.buzzier_bees.client.model.GrizzlyBearModel;
import com.teamabnormals.buzzier_bees.common.entity.animal.GrizzlyBearEntity;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class GrizzlyBearRenderer extends MobRenderer<GrizzlyBearEntity, GrizzlyBearModel<GrizzlyBearEntity>> {
	public static final ResourceLocation GRIZZLY_BEAR_LOCATION = new ResourceLocation(BuzzierBees.MOD_ID, "textures/entity/grizzly_bear/grizzly_bear.png");
	public static final ResourceLocation GRIZZLY_BEAR_SLEEPING_LOCATION = new ResourceLocation(BuzzierBees.MOD_ID, "textures/entity/grizzly_bear/grizzly_bear_sleeping.png");
	public static final ModelLayerLocation MODEL_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(BuzzierBees.MOD_ID, "grizzly_bear"), "main");

	public GrizzlyBearRenderer(EntityRendererProvider.Context context) {
		super(context, new GrizzlyBearModel<>(context.bakeLayer(MODEL_LAYER_LOCATION)), 0.9F);
	}

	@Override
	public ResourceLocation getTextureLocation(GrizzlyBearEntity bear) {
		return bear.isSleeping() ? GRIZZLY_BEAR_SLEEPING_LOCATION : GRIZZLY_BEAR_LOCATION;
	}
}
