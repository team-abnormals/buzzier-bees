package com.bagel.buzzierbees.client.render;

import com.bagel.buzzierbees.client.model.FlyModel;
import com.bagel.buzzierbees.common.entities.FlyEntity;
import com.bagel.buzzierbees.core.BuzzierBees;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class FlyRenderer extends MobRenderer<FlyEntity, FlyModel<FlyEntity>> {	
//	private static final Map<List<String>, String> NAMES = Util.make(Maps.newHashMap(), (skins) -> {
//		skins.put(Arrays.asList("cameron", "cam", "cringe"), "cameron");
//	});

	public FlyRenderer(EntityRendererManager renderManager) {
		super(renderManager, new FlyModel<>(), 0.3F);
	}

	@Override
	public ResourceLocation getEntityTexture(FlyEntity bee) {
		return new ResourceLocation(BuzzierBees.MODID, "textures/entity/fly.png");
	}
	
	@Override
	protected void preRenderCallback(FlyEntity fly, MatrixStack matrixStack, float partialTickTime) {
	    matrixStack.scale(1.0F, 1.0F, 1.0F);
	}
}
