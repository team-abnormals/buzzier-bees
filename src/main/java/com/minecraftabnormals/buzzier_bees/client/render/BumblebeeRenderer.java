package com.minecraftabnormals.buzzier_bees.client.render;

import com.minecraftabnormals.buzzier_bees.client.model.BumblebeeModel;
import com.minecraftabnormals.buzzier_bees.common.entities.BumblebeeEntity;
import com.minecraftabnormals.buzzier_bees.core.BuzzierBees;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class BumblebeeRenderer extends MobRenderer<BumblebeeEntity, BumblebeeModel<BumblebeeEntity>> {
	public BumblebeeRenderer(EntityRendererManager renderManager) {
		super(renderManager, new BumblebeeModel<>(), 0.3F);
	}

	@Override
	public ResourceLocation getEntityTexture(BumblebeeEntity	 bee) {
		return new ResourceLocation(BuzzierBees.MOD_ID, "textures/entity/bumblebee/bumblebee.png");
	}
	
	@Override
	protected void preRenderCallback(BumblebeeEntity fly, MatrixStack matrixStack, float partialTickTime) {
	    matrixStack.scale(1.0F, 1.0F, 1.0F);
	}
}
