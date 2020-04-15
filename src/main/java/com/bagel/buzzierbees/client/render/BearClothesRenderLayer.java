package com.bagel.buzzierbees.client.render;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.bagel.buzzierbees.common.entities.AbstractBearEntity;
import com.bagel.buzzierbees.core.BuzzierBees;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BearClothesRenderLayer<E extends AbstractBearEntity, M extends EntityModel<E>> extends LayerRenderer<E, M> {
	private static final Map<List<String>, String> NAMES = Util.make(Maps.newHashMap(), (skins) -> {
		skins.put(Arrays.asList("cool", "epic"), "sunglasses");
		skins.put(Arrays.asList("clout", "pog"), "goggles");
	});
	
	public BearClothesRenderLayer(IEntityRenderer<E, M> entityRenderer) {
		super(entityRenderer);
	}
	
	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, E bear, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {		
		String overlay = "";
		
		if(bear.hasCustomName()) {
			String name = bear.getName().getString().toLowerCase().trim();
			for(Map.Entry<List<String>, String> entries : NAMES.entrySet()) {
				if(entries.getKey().contains(name)) {
					overlay = entries.getValue();
				}
			}
		}
		
		if (overlay != "") {
			ResourceLocation texture = new ResourceLocation(BuzzierBees.MODID, "textures/entity/bear/overlays/"+ overlay +".png");
			Minecraft.getInstance().getTextureManager().bindTexture(texture);
			IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getEntityCutoutNoCull(texture));
			this.getEntityModel().setRotationAngles(bear, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			this.getEntityModel().render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		}
	}
	
}
