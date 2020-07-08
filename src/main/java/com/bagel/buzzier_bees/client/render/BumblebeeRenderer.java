package com.bagel.buzzier_bees.client.render;

import com.bagel.buzzier_bees.client.model.BumblebeeModel;
import com.bagel.buzzier_bees.common.entities.BumblebeeEntity;
import com.bagel.buzzier_bees.core.BuzzierBees;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class BumblebeeRenderer extends MobRenderer<BumblebeeEntity, BumblebeeModel<BumblebeeEntity>> {	
//	private static final Map<List<String>, String> NAMES = Util.make(Maps.newHashMap(), (skins) -> {
//		skins.put(Arrays.asList("cameron", "cam", "cringe"), "cameron");
//	});

	public BumblebeeRenderer(EntityRendererManager renderManager) {
		super(renderManager, new BumblebeeModel<>(), 0.3F);
	}

	@Override
	public ResourceLocation getEntityTexture(BumblebeeEntity	 bee) {
		return new ResourceLocation(BuzzierBees.MODID, "textures/entity/bumblebee/bumblebee.png");
	}
	
	@Override
	protected void preRenderCallback(BumblebeeEntity fly, MatrixStack matrixStack, float partialTickTime) {
	    matrixStack.scale(1.0F, 1.0F, 1.0F);
	}
}
