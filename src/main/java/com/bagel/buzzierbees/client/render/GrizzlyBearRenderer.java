package com.bagel.buzzierbees.client.render;

import com.bagel.buzzierbees.client.model.GrizzlyBearModel;
import com.bagel.buzzierbees.common.entities.GrizzlyBearEntity;
import com.bagel.buzzierbees.core.BuzzierBees;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class GrizzlyBearRenderer extends MobRenderer<GrizzlyBearEntity, GrizzlyBearModel<GrizzlyBearEntity>> {	
//	private static final Map<List<String>, String> NAMES = Util.make(Maps.newHashMap(), (skins) -> {
//		skins.put(Arrays.asList("cameron", "cam", "cringe"), "cameron");
//	});

	public GrizzlyBearRenderer(EntityRendererManager renderManager) {
		super(renderManager, new GrizzlyBearModel<>(), 0.9F);
	}

	@Override
	public ResourceLocation getEntityTexture(GrizzlyBearEntity bear) {
		return new ResourceLocation(BuzzierBees.MODID, "textures/entity/grizzly_bear.png");
	}
	
	@Override
	protected void preRenderCallback(GrizzlyBearEntity bear, MatrixStack matrixStack, float partialTickTime) {
		matrixStack.scale(1.2f, 1.2f, 1.2f);
		if(bear.isChild()) {
	        matrixStack.scale(0.6F, 0.6F, 0.6F);
	    }	
	}
}
