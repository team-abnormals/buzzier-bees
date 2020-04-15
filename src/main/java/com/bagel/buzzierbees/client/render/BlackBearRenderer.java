package com.bagel.buzzierbees.client.render;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.bagel.buzzierbees.client.model.BearModel;
import com.bagel.buzzierbees.common.entities.BlackBearEntity;
import com.bagel.buzzierbees.core.BuzzierBees;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;

public class BlackBearRenderer extends MobRenderer<BlackBearEntity, BearModel<BlackBearEntity>> {	
	private static final Map<List<String>, String> NAMES = Util.make(Maps.newHashMap(), (skins) -> {
		skins.put(Arrays.asList("derp", "byper"), "derp");
	});

	public BlackBearRenderer(EntityRendererManager renderManager) {
		super(renderManager, new BearModel<>(), 0.8F);
	}

	@Override
	public ResourceLocation getEntityTexture(BlackBearEntity bear) {
		String textureSuffix = "";
		
		if(bear.hasCustomName()) {
			String name = bear.getName().getString().toLowerCase().trim();
			for(Map.Entry<List<String>, String> entries : NAMES.entrySet()) {
				if(entries.getKey().contains(name)) {
					textureSuffix = "_" + entries.getValue();
				}
			}
		}
		return new ResourceLocation(BuzzierBees.MODID, "textures/entity/bear/black_bear" + textureSuffix + ".png");
	}
	
	@Override
	protected void preRenderCallback(BlackBearEntity bear, MatrixStack matrixStack, float partialTickTime) {
		matrixStack.scale(1.1f, 1.1f, 1.1f);
		if(bear.isChild()) {
	        matrixStack.scale(0.55F, 0.55F, 0.55F);
	    }	
	}
}
