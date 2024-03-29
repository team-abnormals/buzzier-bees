package com.teamabnormals.buzzier_bees.client.render.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.teamabnormals.buzzier_bees.client.model.GrizzlyBearModel;
import com.teamabnormals.buzzier_bees.common.entity.animal.GrizzlyBear;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.EquipmentSlot;

public class GrizzlyBearHeldItemLayer extends RenderLayer<GrizzlyBear, GrizzlyBearModel<GrizzlyBear>> {
	private final ItemInHandRenderer itemInHandRenderer;

	public GrizzlyBearHeldItemLayer(RenderLayerParent<GrizzlyBear, GrizzlyBearModel<GrizzlyBear>> parent, ItemInHandRenderer renderer) {
		super(parent);
		this.itemInHandRenderer = renderer;
	}

	public void render(PoseStack stack, MultiBufferSource buffer, int packedLight, GrizzlyBear bear, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		boolean sleeping = bear.isSleeping();

		stack.pushPose();
		ModelPart head = this.getParentModel().getHead();
		stack.mulPose(Vector3f.YP.rotationDegrees(netHeadYaw));
		stack.mulPose(Vector3f.XP.rotationDegrees(headPitch));
		stack.translate(0.0D, 0.325F, -0.3125F);
		stack.translate(head.x / 16.0F, head.y / 16.0F, head.z / 16.0F);

		stack.mulPose(Vector3f.XP.rotationDegrees(90.0F));

		if (sleeping) {
			stack.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
		}

		this.itemInHandRenderer.renderItem(bear, bear.getItemBySlot(EquipmentSlot.MAINHAND), ItemTransforms.TransformType.GROUND, false, stack, buffer, packedLight);
		stack.popPose();
	}
}