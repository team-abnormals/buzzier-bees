package com.bagel.buzzier_bees.client.render;

import com.bagel.buzzier_bees.client.model.BearModel;
import com.bagel.buzzier_bees.common.entities.GrizzlyBearEntity;
import com.bagel.buzzier_bees.core.BuzzierBees;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class GrizzlyBearRenderer extends MobRenderer<GrizzlyBearEntity, BearModel<GrizzlyBearEntity>> {	
	public GrizzlyBearRenderer(EntityRendererManager renderManager) {
		super(renderManager, new BearModel<>(), 0.9F);
	}

	@Override
	public ResourceLocation getEntityTexture(GrizzlyBearEntity bear) {
		if (bear.isSleeping()) return new ResourceLocation(BuzzierBees.MODID, "textures/entity/bear/grizzly_bear_sleeping.png");
		return new ResourceLocation(BuzzierBees.MODID, "textures/entity/bear/grizzly_bear.png");
	}
	
	@Override
	protected void preRenderCallback(GrizzlyBearEntity bear, MatrixStack matrixStack, float partialTickTime) {
		matrixStack.scale(1.3f, 1.3f, 1.3f);
		if(bear.isChild()) {
	        matrixStack.scale(0.65F, 0.65F, 0.65F);
	    }	
	}
}
