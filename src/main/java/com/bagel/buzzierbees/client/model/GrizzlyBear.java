package com.bagel.buzzierbees.client.model;
/*package com.bagel.buzzierbees.client.model;
//Made with Blockbench
//Paste this code into your mod.

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class GrizzlyBear extends EntityModel {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer leg0;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;

	public GrizzlyBear() {
		textureWidth = 80;
		textureHeight = 80;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 10.0F, -16.0F);
		head.cubeList.add(new ModelBox(head, 0, 45, -4.5F, -3.0F, -1.0F, 9, 7, 6, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 24, 45, -2.5F, 1.0F, -4.0F, 5, 3, 3, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 48, 2.5F, -5.0F, 1.0F, 2, 2, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 48, -4.5F, -5.0F, 1.0F, 2, 2, 1, 0.0F, true));

		body = new ModelRenderer(this);
		body.setRotationPoint(2.0F, 9.0F, 12.0F);
		body.cubeList.add(new ModelBox(body, 0, 24, -8.0F, -3.0F, -10.0F, 12, 10, 11, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 0, -9.0F, -4.0F, -23.0F, 14, 11, 13, 0.0F, false));

		leg0 = new ModelRenderer(this);
		leg0.setRotationPoint(4.5F, 14.0F, 6.0F);
		leg0.cubeList.add(new ModelBox(leg0, 57, 0, -3.0F, 2.0F, -1.0F, 4, 8, 6, 0.0F, false));

		leg1 = new ModelRenderer(this);
		leg1.setRotationPoint(-4.5F, 14.0F, 6.0F);
		leg1.cubeList.add(new ModelBox(leg1, 57, 0, -1.0F, 2.0F, -1.0F, 4, 8, 6, 0.0F, false));

		leg2 = new ModelRenderer(this);
		leg2.setRotationPoint(3.5F, 14.0F, -7.0F);
		leg2.cubeList.add(new ModelBox(leg2, 42, 1, -2.0F, 2.0F, -2.0F, 4, 8, 4, 0.0F, false));

		leg3 = new ModelRenderer(this);
		leg3.setRotationPoint(-3.5F, 16.0F, -7.0F);
		leg3.cubeList.add(new ModelBox(leg3, 41, 1, -2.0F, 0.0F, -2.0F, 4, 8, 4, 0.0F, false));
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		head.render(f5);
		body.render(f5);
		leg0.render(f5);
		leg1.render(f5);
		leg2.render(f5);
		leg3.render(f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void func_225597_a_(Entity p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_,
			float p_225597_5_, float p_225597_6_) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void func_225598_a_(MatrixStack p_225598_1_, IVertexBuilder p_225598_2_, int p_225598_3_, int p_225598_4_,
			float p_225598_5_, float p_225598_6_, float p_225598_7_, float p_225598_8_) {
		// TODO Auto-generated method stub
		
	}
}*/

import net.minecraft.client.renderer.entity.model.QuadrupedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.passive.PolarBearEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GrizzlyBear<T extends PolarBearEntity> extends QuadrupedModel<T> {
    public GrizzlyBear() {
        super(12, 0.0F, true, 16.0F, 4.0F, 2.25F, 2.0F, 24);
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.headModel = new ModelRenderer(this, 0, 0);
        this.headModel.func_228301_a_(-3.5F, -3.0F, -3.0F, 7.0F, 7.0F, 7.0F, 0.0F);
        this.headModel.setRotationPoint(0.0F, 10.0F, -16.0F);
        this.headModel.setTextureOffset(0, 44).func_228301_a_(-2.5F, 1.0F, -6.0F, 5.0F, 3.0F, 3.0F, 0.0F);
        this.headModel.setTextureOffset(26, 0).func_228301_a_(-4.5F, -4.0F, -1.0F, 2.0F, 2.0F, 1.0F, 0.0F);
        ModelRenderer lvt_1_1_ = this.headModel.setTextureOffset(26, 0);
        lvt_1_1_.mirror = true;
        lvt_1_1_.func_228301_a_(2.5F, -4.0F, -1.0F, 2.0F, 2.0F, 1.0F, 0.0F);
        this.field_78148_b = new ModelRenderer(this);
        this.field_78148_b.setTextureOffset(0, 19).func_228301_a_(-5.0F, -13.0F, -7.0F, 14.0F, 14.0F, 11.0F, 0.0F);
        this.field_78148_b.setTextureOffset(39, 0).func_228301_a_(-4.0F, -25.0F, -7.0F, 12.0F, 12.0F, 10.0F, 0.0F);
        this.field_78148_b.setRotationPoint(-2.0F, 9.0F, 12.0F);
        this.field_78149_c = new ModelRenderer(this, 50, 22);
        this.field_78149_c.func_228301_a_(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 8.0F, 0.0F);
        this.field_78149_c.setRotationPoint(-3.5F, 14.0F, 6.0F);
        this.field_78146_d = new ModelRenderer(this, 50, 22);
        this.field_78146_d.func_228301_a_(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 8.0F, 0.0F);
        this.field_78146_d.setRotationPoint(3.5F, 14.0F, 6.0F);
        this.field_78147_e = new ModelRenderer(this, 50, 40);
        this.field_78147_e.func_228301_a_(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 6.0F, 0.0F);
        this.field_78147_e.setRotationPoint(-2.5F, 14.0F, -7.0F);
        this.field_78144_f = new ModelRenderer(this, 50, 40);
        this.field_78144_f.func_228301_a_(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 6.0F, 0.0F);
        this.field_78144_f.setRotationPoint(2.5F, 14.0F, -7.0F);
        --this.field_78149_c.rotationPointX;
        ++this.field_78146_d.rotationPointX;
        ModelRenderer var10000 = this.field_78149_c;
        var10000.rotationPointZ += 0.0F;
        var10000 = this.field_78146_d;
        var10000.rotationPointZ += 0.0F;
        --this.field_78147_e.rotationPointX;
        ++this.field_78144_f.rotationPointX;
        --this.field_78147_e.rotationPointZ;
        --this.field_78144_f.rotationPointZ;
    }

    public void func_225597_a_(T p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
        super.func_225597_a_(p_225597_1_, p_225597_2_, p_225597_3_, p_225597_4_, p_225597_5_, p_225597_6_);
        float lvt_7_1_ = p_225597_4_ - (float)p_225597_1_.ticksExisted;
        float lvt_8_1_ = p_225597_1_.getStandingAnimationScale(lvt_7_1_);
        lvt_8_1_ *= lvt_8_1_;
        float lvt_9_1_ = 1.0F - lvt_8_1_;
        this.field_78148_b.rotateAngleX = 1.5707964F - lvt_8_1_ * 3.1415927F * 0.35F;
        this.field_78148_b.rotationPointY = 9.0F * lvt_9_1_ + 11.0F * lvt_8_1_;
        this.field_78147_e.rotationPointY = 14.0F * lvt_9_1_ - 6.0F * lvt_8_1_;
        this.field_78147_e.rotationPointZ = -8.0F * lvt_9_1_ - 4.0F * lvt_8_1_;
        ModelRenderer var10000 = this.field_78147_e;
        var10000.rotateAngleX -= lvt_8_1_ * 3.1415927F * 0.45F;
        this.field_78144_f.rotationPointY = this.field_78147_e.rotationPointY;
        this.field_78144_f.rotationPointZ = this.field_78147_e.rotationPointZ;
        var10000 = this.field_78144_f;
        var10000.rotateAngleX -= lvt_8_1_ * 3.1415927F * 0.45F;
        if (this.isChild) {
            this.headModel.rotationPointY = 10.0F * lvt_9_1_ - 9.0F * lvt_8_1_;
            this.headModel.rotationPointZ = -16.0F * lvt_9_1_ - 7.0F * lvt_8_1_;
        } else {
            this.headModel.rotationPointY = 10.0F * lvt_9_1_ - 14.0F * lvt_8_1_;
            this.headModel.rotationPointZ = -16.0F * lvt_9_1_ - 3.0F * lvt_8_1_;
        }

        var10000 = this.headModel;
        var10000.rotateAngleX += lvt_8_1_ * 3.1415927F * 0.15F;
    }
}