package com.bagel.buzzierbees.client.model;

import com.bagel.buzzierbees.common.entities.AbstractBearEntity;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

/**
 * ModelBear - Booper
 * Created using Tabula 7.0.0
 */
public class BearModel<T extends AbstractBearEntity> extends AgeableModel<T> {
    public ModelRenderer chest;
    public ModelRenderer butt;
    public ModelRenderer left_arm;
    public ModelRenderer right_arm;
    public ModelRenderer head;
    public ModelRenderer left_leg;
    public ModelRenderer right_leg;
    public ModelRenderer snout;
    public ModelRenderer left_ear;
    public ModelRenderer right_ear;

    public BearModel() {
        this.textureWidth = 80;
        this.textureHeight = 80;
        this.snout = new ModelRenderer(this, 24, 45);
        this.snout.setRotationPoint(0.0F, 2.5F, -6.0F);
        this.snout.addBox(-2.5F, -1.5F, -3.0F, 5, 3, 3, 0.0F);
        this.right_ear = new ModelRenderer(this, 0, 48);
        this.right_ear.setRotationPoint(-3.5F, -3.0F, -4.0F);
        this.right_ear.addBox(-1.0F, -2.0F, 0.0F, 2, 2, 1, 0.0F);
        this.chest = new ModelRenderer(this, 0, 0);
        this.chest.setRotationPoint(0.0F, 10.5F, -3.5F);
        this.chest.addBox(-7.0F, -5.5F, -6.5F, 14, 11, 13, 0.0F);
        this.butt = new ModelRenderer(this, 0, 24);
        this.butt.setRotationPoint(0.0F, 0.5F, 7.0F);
        this.butt.addBox(-6.0F, -5.0F, -0.5F, 12, 10, 11, 0.0F);
        this.left_arm = new ModelRenderer(this, 42, 1);
        this.left_arm.mirror = true;
        this.left_arm.setRotationPoint(3.5F, 6.0F, -3.0F);
        this.left_arm.addBox(-2.0F, -0.5F, -2.0F, 4, 8, 4, 0.0F);
        this.right_arm = new ModelRenderer(this, 42, 1);
        this.right_arm.setRotationPoint(-3.5F, 6.0F, -3.0F);
        this.right_arm.addBox(-2.0F, -0.5F, -2.0F, 4, 8, 4, 0.0F);
        this.head = new ModelRenderer(this, 0, 45);
        this.head.setRotationPoint(0.0F, 0.0F, -6.5F);
        this.head.addBox(-4.5F, -3.0F, -6.0F, 9, 7, 6, 0.0F);
        this.left_leg = new ModelRenderer(this, 57, 0);
        this.left_leg.mirror = true;
        this.left_leg.setRotationPoint(3.5F, 5.0F, 6.0F);
        this.left_leg.addBox(-2.0F, 0.0F, -3.5F, 4, 8, 6, 0.0F);
        this.left_ear = new ModelRenderer(this, 0, 48);
        this.left_ear.setRotationPoint(3.5F, -3.0F, -4.0F);
        this.left_ear.addBox(-1.0F, -2.0F, 0.0F, 2, 2, 1, 0.0F);
        this.right_leg = new ModelRenderer(this, 57, 0);
        this.right_leg.setRotationPoint(-3.5F, 5.0F, 6.0F);
        this.right_leg.addBox(-2.0F, 0.0F, -3.5F, 4, 8, 6, 0.0F);
        this.head.addChild(this.snout);
        this.head.addChild(this.right_ear);
        this.chest.addChild(this.butt);
        this.chest.addChild(this.left_arm);
        this.chest.addChild(this.right_arm);
        this.chest.addChild(this.head);
        this.butt.addChild(this.left_leg);
        this.head.addChild(this.left_ear);
        this.butt.addChild(this.right_leg);
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        this.chest.render(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
    
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
        this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        this.right_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.left_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.right_arm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.left_arm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }

	@Override
	protected Iterable<ModelRenderer> getHeadParts() {
		return ImmutableList.of(this.head);
	}

	@Override
	protected Iterable<ModelRenderer> getBodyParts() {
		return ImmutableList.of(this.chest, this.butt);
	}
}
