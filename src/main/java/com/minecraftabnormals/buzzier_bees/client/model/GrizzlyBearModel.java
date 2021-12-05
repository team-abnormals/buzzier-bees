package com.minecraftabnormals.buzzier_bees.client.model;

import com.minecraftabnormals.buzzier_bees.common.entities.GrizzlyBearEntity;
import net.minecraft.client.renderer.entity.model.QuadrupedModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class GrizzlyBearModel<T extends GrizzlyBearEntity> extends QuadrupedModel<T> {
	public ModelRenderer ear1;
	public ModelRenderer ear2;

	public GrizzlyBearModel(int p_i51063_1_, float p_i51063_2_) {
		super(p_i51063_1_, p_i51063_2_, true, 25.0F, 6.8F, 2.7F, 3.0F, 49);
		texWidth = 128;
		texHeight = 128;

		head = new ModelRenderer(this);
		head.setPos(0.0F, 9.0F, -16.0F);
		head.texOffs(0, 47).addBox(-6.5F, -6.0F, -9.0F, 13.0F, 11.0F, 9.0F, 0.0F, false);
		head.texOffs(0, 13).addBox(-3.5F, 0.0F, -12.0F, 7.0F, 5.0F, 3.0F, 0.0F, false);

		ear1 = new ModelRenderer(this);
		ear1.setPos(-4.0F, -4.0F, -5.0F);
		head.addChild(ear1);
		setRotationAngle(ear1, 0.0F, 0.2618F, 0.0F);
		ear1.texOffs(18, 0).addBox(-4.0F, -4.0F, -1.0F, 4.0F, 4.0F, 2.0F, 0.0F, false);

		ear2 = new ModelRenderer(this);
		ear2.setPos(4.0F, -4.0F, -5.0F);
		head.addChild(ear2);
		setRotationAngle(ear2, 0.0F, -0.2618F, 0.0F);
		ear2.texOffs(18, 0).addBox(0.0F, -4.0F, -1.0F, 4.0F, 4.0F, 2.0F, 0.0F, true);

		body = new ModelRenderer(this);
		body.setPos(0.0F, 24.0F, -10.0F);
		body.texOffs(0, 0).addBox(-9.0F, -23.0F, -6.0F, 18.0F, 16.0F, 31.0F, 0.0F, false);

		leg2 = new ModelRenderer(this);
		leg2.setPos(-5.0F, 17.0F, -10.0F);
		leg2.texOffs(0, 0).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 7.0F, 6.0F, 0.0F, false);

		leg3 = new ModelRenderer(this);
		leg3.setPos(5.0F, 17.0F, -10.0F);
		leg3.texOffs(0, 0).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 7.0F, 6.0F, 0.0F, true);

		leg0 = new ModelRenderer(this);
		leg0.setPos(-5.0F, 17.0F, 11.0F);
		leg0.texOffs(0, 0).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 7.0F, 6.0F, 0.0F, false);

		leg1 = new ModelRenderer(this);
		leg1.setPos(5.0F, 17.0F, 11.0F);
		leg1.texOffs(0, 0).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 7.0F, 6.0F, 0.0F, true);
	}

	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		if (entityIn.isEating()) {
			this.body.xRot = 270.0F;
		} else {
			this.body.xRot = 0.0F;
		}
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}