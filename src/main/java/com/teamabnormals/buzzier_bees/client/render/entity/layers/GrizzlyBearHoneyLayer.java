package com.teamabnormals.buzzier_bees.client.render.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.teamabnormals.buzzier_bees.client.model.GrizzlyBearModel;
import com.teamabnormals.buzzier_bees.common.entity.animal.GrizzlyBear;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GrizzlyBearHoneyLayer extends RenderLayer<GrizzlyBear, GrizzlyBearModel<GrizzlyBear>> {
	public static final ResourceLocation HONEY = new ResourceLocation(BuzzierBees.MOD_ID, "textures/entity/grizzly_bear/bear_overlay_honey.png");

	public GrizzlyBearHoneyLayer(RenderLayerParent<GrizzlyBear, GrizzlyBearModel<GrizzlyBear>> parent) {
		super(parent);
	}

	@Override
	public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, GrizzlyBear entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		if (entity.isHoney()) {
			VertexConsumer builder = bufferIn.getBuffer(RenderType.entityCutoutNoCull(HONEY));
			this.getParentModel().setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			this.getParentModel().renderToBuffer(matrixStackIn, builder, packedLightIn, LivingEntityRenderer.getOverlayCoords(entity, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
		}
	}
}
