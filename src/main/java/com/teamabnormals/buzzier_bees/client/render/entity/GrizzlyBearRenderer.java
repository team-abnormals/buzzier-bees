package com.teamabnormals.buzzier_bees.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.teamabnormals.buzzier_bees.client.model.GrizzlyBearModel;
import com.teamabnormals.buzzier_bees.client.render.entity.layers.GrizzlyBearHeldItemLayer;
import com.teamabnormals.buzzier_bees.client.render.entity.layers.GrizzlyBearHoneyLayer;
import com.teamabnormals.buzzier_bees.common.entity.animal.GrizzlyBear;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import com.teamabnormals.buzzier_bees.core.other.BBModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class GrizzlyBearRenderer extends MobRenderer<GrizzlyBear, GrizzlyBearModel<GrizzlyBear>> {
	public static final ResourceLocation GRIZZLY_BEAR_LOCATION = new ResourceLocation(BuzzierBees.MOD_ID, "textures/entity/grizzly_bear/grizzly_bear.png");
	public static final ResourceLocation GRIZZLY_BEAR_SLEEPING_LOCATION = new ResourceLocation(BuzzierBees.MOD_ID, "textures/entity/grizzly_bear/grizzly_bear_sleeping.png");

	public GrizzlyBearRenderer(EntityRendererProvider.Context context) {
		super(context, new GrizzlyBearModel<>(context.bakeLayer(BBModelLayers.GRIZZLY_BEAR)), 0.9F);
		this.addLayer(new GrizzlyBearHoneyLayer(this));
		this.addLayer(new GrizzlyBearHeldItemLayer(this, context.getItemInHandRenderer()));
	}

	@Override
	public ResourceLocation getTextureLocation(GrizzlyBear bear) {
		return bear.isSleeping() ? GRIZZLY_BEAR_SLEEPING_LOCATION : GRIZZLY_BEAR_LOCATION;
	}

	@Override
	protected void setupRotations(GrizzlyBear bear, PoseStack poseStack, float p_114019_, float p_114020_, float p_114021_) {
		super.setupRotations(bear, poseStack, p_114019_, p_114020_, p_114021_);
		if (bear.isSleeping()) {
			poseStack.translate(0.75F, 0.575F, 0.3F);
			poseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
		} else {
		}
	}
}
