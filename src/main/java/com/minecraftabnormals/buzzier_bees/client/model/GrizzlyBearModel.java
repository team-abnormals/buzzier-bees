package com.minecraftabnormals.buzzier_bees.client.model;

import com.minecraftabnormals.buzzier_bees.common.entities.GrizzlyBearEntity;
import net.minecraft.client.renderer.entity.model.QuadrupedModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class GrizzlyBearModel<T extends GrizzlyBearEntity> extends QuadrupedModel<T> {
	public ModelRenderer ear1;
	public ModelRenderer ear2;

	public GrizzlyBearModel(int p_i51063_1_, float p_i51063_2_) {
		super(p_i51063_1_, p_i51063_2_, true, 25.0F, 6.8F, 2.7F, 3.0F, 49);
		textureWidth = 128;
		textureHeight = 128;

		headModel = new ModelRenderer(this);
		headModel.setRotationPoint(0.0F, 9.0F, -16.0F);
		headModel.setTextureOffset(0, 47).addBox(-6.5F, -6.0F, -9.0F, 13.0F, 11.0F, 9.0F, 0.0F, false);
		headModel.setTextureOffset(0, 13).addBox(-3.5F, 0.0F, -12.0F, 7.0F, 5.0F, 3.0F, 0.0F, false);

		ear1 = new ModelRenderer(this);
		ear1.setRotationPoint(-4.0F, -4.0F, -5.0F);
		headModel.addChild(ear1);
		setRotationAngle(ear1, 0.0F, 0.2618F, 0.0F);
		ear1.setTextureOffset(18, 0).addBox(-4.0F, -4.0F, -1.0F, 4.0F, 4.0F, 2.0F, 0.0F, false);

		ear2 = new ModelRenderer(this);
		ear2.setRotationPoint(4.0F, -4.0F, -5.0F);
		headModel.addChild(ear2);
		setRotationAngle(ear2, 0.0F, -0.2618F, 0.0F);
		ear2.setTextureOffset(18, 0).addBox(0.0F, -4.0F, -1.0F, 4.0F, 4.0F, 2.0F, 0.0F, true);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, -10.0F);
		body.setTextureOffset(0, 0).addBox(-9.0F, -23.0F, -6.0F, 18.0F, 16.0F, 31.0F, 0.0F, false);

		legFrontRight = new ModelRenderer(this);
		legFrontRight.setRotationPoint(-5.0F, 17.0F, -10.0F);
		legFrontRight.setTextureOffset(0, 0).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 7.0F, 6.0F, 0.0F, false);

		legFrontLeft = new ModelRenderer(this);
		legFrontLeft.setRotationPoint(5.0F, 17.0F, -10.0F);
		legFrontLeft.setTextureOffset(0, 0).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 7.0F, 6.0F, 0.0F, true);

		legBackRight = new ModelRenderer(this);
		legBackRight.setRotationPoint(-5.0F, 17.0F, 11.0F);
		legBackRight.setTextureOffset(0, 0).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 7.0F, 6.0F, 0.0F, false);

		legBackLeft = new ModelRenderer(this);
		legBackLeft.setRotationPoint(5.0F, 17.0F, 11.0F);
		legBackLeft.setTextureOffset(0, 0).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 7.0F, 6.0F, 0.0F, true);
	}

	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		this.body.rotateAngleX = 0.0F;
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}