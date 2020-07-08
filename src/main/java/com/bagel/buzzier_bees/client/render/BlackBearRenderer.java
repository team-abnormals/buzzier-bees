package com.bagel.buzzier_bees.client.render;

import com.bagel.buzzier_bees.client.model.BearModel;
import com.bagel.buzzier_bees.common.entities.BlackBearEntity;
import com.bagel.buzzier_bees.core.BuzzierBees;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class BlackBearRenderer extends MobRenderer<BlackBearEntity, BearModel<BlackBearEntity>> {	
	public BlackBearRenderer(EntityRendererManager renderManager) {
		super(renderManager, new BearModel<>(), 0.8F);
	}

	@Override
	public ResourceLocation getEntityTexture(BlackBearEntity bear) {
		if (bear.isSleeping()) return new ResourceLocation(BuzzierBees.MODID, "textures/entity/bear/black_bear_sleeping.png");
		return new ResourceLocation(BuzzierBees.MODID, "textures/entity/bear/black_bear.png");
	}
	
	@Override
	protected void preRenderCallback(BlackBearEntity bear, MatrixStack matrixStack, float partialTickTime) {
		matrixStack.scale(1.1f, 1.1f, 1.1f);
		if(bear.isChild()) {
	        matrixStack.scale(0.55F, 0.55F, 0.55F);
	    }	
	}
}
