package com.bagel.buzzierbees.client.model;

import com.bagel.buzzierbees.common.entities.FlyEntity;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

/**
 * FlyModel - MCVinnyq
 * Created using Tabula 7.1.0
 */

public class FlyModel<T extends FlyEntity> extends AgeableModel<T> {
    public ModelRenderer body;
    public ModelRenderer leftAntenna;
    public ModelRenderer rightAntenna;
    public ModelRenderer rightWing;
    public ModelRenderer leftWing;
    public ModelRenderer Mouth;
    public ModelRenderer frontLegs;
    public ModelRenderer middleLegs;
    public ModelRenderer backLegs;

    public FlyModel() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.leftAntenna = new ModelRenderer(this, 0, 0);
        this.leftAntenna.setRotationPoint(0.5F, -2.5F, -3.0F);
        this.leftAntenna.addBox(0.0F, -3.0F, 0.0F, 3, 3, 0, 0.0F);
        this.setRotateAngle(leftAntenna, 0.0F, 0.0F, 0.2181661564992912F);
        this.backLegs = new ModelRenderer(this, 16, 4);
        this.backLegs.setRotationPoint(0.0F, 2.5F, 2.0F);
        this.backLegs.addBox(-2.5F, 0.0F, 0.0F, 5, 2, 0, 0.0F);
        this.frontLegs = new ModelRenderer(this, 16, 0);
        this.frontLegs.setRotationPoint(0.0F, 2.5F, -2.0F);
        this.frontLegs.addBox(-2.5F, 0.0F, 0.0F, 5, 2, 0, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.body.addBox(-2.5F, -2.5F, -3.0F, 5, 5, 6, 0.0F);
        this.rightWing = new ModelRenderer(this, 0, 12);
        this.rightWing.setRotationPoint(-2.5F, -0.5F, 0.0F);
        this.rightWing.addBox(-4.0F, 0.0F, -2.0F, 4, 0, 4, 0.0F);
        this.rightAntenna = new ModelRenderer(this, 0, 3);
        this.rightAntenna.setRotationPoint(-0.5F, -2.5F, -3.0F);
        this.rightAntenna.addBox(-3.0F, -3.0F, 0.0F, 3, 3, 0, 0.0F);
        this.setRotateAngle(rightAntenna, 0.0F, 0.0F, -0.2181661564992912F);
        this.leftWing = new ModelRenderer(this, 0, 16);
        this.leftWing.setRotationPoint(2.5F, -0.5F, 0.0F);
        this.leftWing.addBox(0.0F, 0.0F, -2.0F, 4, 0, 4, 0.0F);
        this.Mouth = new ModelRenderer(this, 12, 11);
        this.Mouth.setRotationPoint(0.0F, 0.5F, -3.0F);
        this.Mouth.addBox(0.0F, 0.0F, -3.0F, 0, 3, 3, 0.0F);
        this.setRotateAngle(Mouth, 0.20943951023931953F, 0.0F, 0.0F);
        this.middleLegs = new ModelRenderer(this, 16, 2);
        this.middleLegs.setRotationPoint(0.0F, 2.5F, 0.0F);
        this.middleLegs.addBox(-2.5F, 0.0F, 0.0F, 5, 2, 0, 0.0F);
        this.body.addChild(this.leftAntenna);
        this.body.addChild(this.backLegs);
        this.body.addChild(this.frontLegs);
        this.body.addChild(this.rightWing);
        this.body.addChild(this.rightAntenna);
        this.body.addChild(this.leftWing);
        this.body.addChild(this.Mouth);
        this.body.addChild(this.middleLegs);
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        this.body.render(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

	@Override
	protected Iterable<ModelRenderer> getHeadParts() {
		return ImmutableList.of();
	}

	@Override
	protected Iterable<ModelRenderer> getBodyParts() {
		return ImmutableList.of(this.body);
	}

	@Override
	public void setRotationAngles(T fly, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.rightWing.rotateAngleX = 0.0f;
		this.leftAntenna.rotateAngleX = 0.0f;
		this.rightAntenna.rotateAngleX = 0.0f;
		if (fly.onGround) {
			this.rightWing.rotateAngleY = -0.2618f;
			this.rightWing.rotateAngleZ = 0.0f;
			this.leftWing.rotateAngleX = 0.0f;
			this.leftWing.rotateAngleY = 0.2618f;
			this.leftWing.rotateAngleZ = 0.0f;
			this.frontLegs.rotateAngleX = 0.0f;
			this.middleLegs.rotateAngleX = 0.0f;
			this.backLegs.rotateAngleX = 0.0f;
		}
		else {
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
