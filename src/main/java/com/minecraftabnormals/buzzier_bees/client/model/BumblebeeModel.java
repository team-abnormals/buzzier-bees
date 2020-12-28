package com.minecraftabnormals.buzzier_bees.client.model;

import com.minecraftabnormals.buzzier_bees.common.entities.BumblebeeEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

/**
 * BumblebeeModel - Dani
 * Created using Blockbench 3.5.0
 */

public class BumblebeeModel<T extends BumblebeeEntity> extends EntityModel<BumblebeeEntity> {
	private final ModelRenderer head;
	private final ModelRenderer leftWing;
	private final ModelRenderer rightWing;
	private final ModelRenderer abdomen;
	private final ModelRenderer stinger;
	private final ModelRenderer middleLegs;
	private final ModelRenderer backLegs;
	private final ModelRenderer leftAntenna;
	private final ModelRenderer rightAntenna;
	private final ModelRenderer frontLegs;

	public BumblebeeModel() {
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 14.0F, 0.0F);
		head.setTextureOffset(1, 2).addBox(-3.5F, -1.5F, -4.0F, 7.0F, 7.0F, 5.0F, 0.0F, false);

		leftWing = new ModelRenderer(this);
		leftWing.setRotationPoint(1.5F, -1.5F, -2.0F);
		head.addChild(leftWing);
		setRotationAngle(leftWing, 0.4363F, 0.0F, 0.0F);
		leftWing.setTextureOffset(0, 20).addBox(0.0F, 0.0F, 0.0F, 7.0F, 0.0F, 6.0F, 0.0F, false);

		rightWing = new ModelRenderer(this);
		rightWing.setRotationPoint(-1.5F, -1.5F, -2.0F);
		head.addChild(rightWing);
		setRotationAngle(rightWing, 0.4363F, 0.0F, 0.0F);
		rightWing.setTextureOffset(0, 14).addBox(-7.0F, 0.0F, 0.0F, 7.0F, 0.0F, 6.0F, 0.0F, false);

		abdomen = new ModelRenderer(this);
		abdomen.setRotationPoint(-0.5F, 0.0F, 1.0F);
		head.addChild(abdomen);
		setRotationAngle(abdomen, -0.2618F, 0.0F, 0.0F);
		abdomen.setTextureOffset(0, 26).addBox(-4.0F, -2.2412F, -0.9659F, 9.0F, 9.0F, 8.0F, 0.0F, false);

		stinger = new ModelRenderer(this);
		stinger.setRotationPoint(0.5F, 10.0F, -1.0F);
		abdomen.addChild(stinger);
		stinger.setTextureOffset(0, 0).addBox(-0.5F, -8.2412F, 8.0341F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		middleLegs = new ModelRenderer(this);
		middleLegs.setRotationPoint(0.5F, 6.7588F, 1.0341F);
		abdomen.addChild(middleLegs);
		setRotationAngle(middleLegs, 0.6109F, 0.0F, 0.0F);
		middleLegs.setTextureOffset(20, 2).addBox(-2.0F, -0.2588F, -0.0341F, 4.0F, 2.0F, 0.0F, 0.0F, false);

		backLegs = new ModelRenderer(this);
		backLegs.setRotationPoint(0.5F, 6.7588F, 3.0341F);
		abdomen.addChild(backLegs);
		setRotationAngle(backLegs, 0.6109F, 0.0F, 0.0F);
		backLegs.setTextureOffset(20, 4).addBox(-2.0F, -0.2588F, -0.0341F, 4.0F, 2.0F, 0.0F, 0.0F, false);

		leftAntenna = new ModelRenderer(this);
		leftAntenna.setRotationPoint(1.5F, 0.5F, -4.0F);
		head.addChild(leftAntenna);
		leftAntenna.setTextureOffset(0, 2).addBox(0.0F, -2.0F, -3.0F, 0.0F, 2.0F, 3.0F, 0.0F, false);

		rightAntenna = new ModelRenderer(this);
		rightAntenna.setRotationPoint(-1.5F, 0.5F, -4.0F);
		head.addChild(rightAntenna);
		rightAntenna.setTextureOffset(0, 0).addBox(0.0F, -2.0F, -3.0F, 0.0F, 2.0F, 3.0F, 0.0F, false);

		frontLegs = new ModelRenderer(this);
		frontLegs.setRotationPoint(0.0F, 5.5F, -3.0F);
		head.addChild(frontLegs);
		setRotationAngle(frontLegs, 0.2618F, 0.0F, 0.0F);
		frontLegs.setTextureOffset(21, 0).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 2.0F, 0.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		head.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(BumblebeeEntity bee, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.rightWing.rotateAngleX = 0.0f;
		this.leftAntenna.rotateAngleX = 0.0f;
		this.rightAntenna.rotateAngleX = 0.0f;
		if (bee.isOnGround()) {
			this.rightWing.rotateAngleY = -0.2618f;
			this.rightWing.rotateAngleZ = 0.0f;
			this.leftWing.rotateAngleX = 0.0f;
			this.leftWing.rotateAngleY = 0.2618f;
			this.leftWing.rotateAngleZ = 0.0f;
			this.frontLegs.rotateAngleX = 0.0f;
			this.middleLegs.rotateAngleX = 0.0f;
			this.backLegs.rotateAngleX = 0.0f;
		} else {
			this.rightWing.rotateAngleY = 0.0f;
			this.rightWing.rotateAngleZ = MathHelper.cos(ageInTicks * 2.1f) * 3.1415927f * 0.15f;
			this.leftWing.rotateAngleX = this.rightWing.rotateAngleX;
			this.leftWing.rotateAngleY = this.rightWing.rotateAngleY;
			this.leftWing.rotateAngleZ = -this.rightWing.rotateAngleZ;
			this.frontLegs.rotateAngleX = 0.7853982f;
			this.middleLegs.rotateAngleX = 0.7853982f;
			this.backLegs.rotateAngleX = 0.7853982f;
		}
	}
}
