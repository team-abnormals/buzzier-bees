package com.bagel.buzzierbees.client.model;

import com.bagel.buzzierbees.common.entities.GrizzlyBearEntity;
import com.google.common.collect.ImmutableList;

import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class GrizzlyBearModel<T extends GrizzlyBearEntity> extends AgeableModel<T> {
    public ModelRenderer CHEST;
    public ModelRenderer BUTT;
    public ModelRenderer LEFT_ARM;
    public ModelRenderer RIGHT_ARM;
    public ModelRenderer HEAD;
    public ModelRenderer LEFT_LEG;
    public ModelRenderer RIGHT_LEG;
    public ModelRenderer SNOUT;
    public ModelRenderer LEFT_EAR;
    public ModelRenderer RIGHT_EAR;

    public GrizzlyBearModel() {
        this.textureWidth = 80;
        this.textureHeight = 80;

        this.SNOUT = new ModelRenderer(this, 24, 45);
        this.SNOUT.setRotationPoint(0.0F, 2.5F, -6.0F);
        this.SNOUT.addBox(-2.5F, -1.5F, -3.0F, 5, 3, 3);

        this.RIGHT_EAR = new ModelRenderer(this, 0, 48);
        this.RIGHT_EAR.mirror = true;
        this.RIGHT_EAR.setRotationPoint(-3.5F, -3.0F, -4.0F);
        this.RIGHT_EAR.addBox(-1.0F, -2.0F, 0.0F, 2, 2, 1);

        this.LEFT_EAR = new ModelRenderer(this, 0, 48);
        this.LEFT_EAR.setRotationPoint(3.5F, -3.0F, -4.0F);
        this.LEFT_EAR.addBox(-1.0F, -2.0F, 0.0F, 2, 2, 1);

        this.HEAD = new ModelRenderer(this, 0, 45);
        this.HEAD.setRotationPoint(0.0F, 10.5F, -9.5F);
        this.HEAD.addBox(-4.5F, -3.0F, -6.0F, 9, 7, 6);

        this.CHEST = new ModelRenderer(this, 0, 0);
        this.CHEST.setRotationPoint(0.0F, 10.5F, -3.5F);
        this.CHEST.addBox(-7.0F, -5.5F, -6.5F, 14, 11, 13);
        
        this.BUTT = new ModelRenderer(this, 0, 24);
        this.BUTT.setRotationPoint(0.0F, 0.5F, 7.0F);
        this.BUTT.addBox(-6.0F, -5.0F, -0.5F, 12, 10, 11);

        this.LEFT_ARM = new ModelRenderer(this, 41, 1);
        this.LEFT_ARM.mirror = true;
        this.LEFT_ARM.setRotationPoint(-3.5F, 16.0F, -7.0F);
        this.LEFT_ARM.addBox(-2.0F, 0.0F, -1F, 4, 8, 4);

        this.RIGHT_ARM = new ModelRenderer(this, 41, 1);
        this.RIGHT_ARM.setRotationPoint(3.5F, 16.0F, -7.0F);
        this.RIGHT_ARM.addBox(-2.0F, 0.0F, -1F, 4, 8, 4);

        this.LEFT_LEG = new ModelRenderer(this, 57, 0);
        this.LEFT_LEG.mirror = true;
        this.LEFT_LEG.setRotationPoint(4.5F, 16.0F, 6.0F);
        this.LEFT_LEG.addBox(-3.0F, 0.0F, 0F, 4, 8, 6);

        this.RIGHT_LEG = new ModelRenderer(this, 57, 0);
        this.RIGHT_LEG.setRotationPoint(-4.5F, 16.0F, 6.0F);
        this.RIGHT_LEG.addBox(-1.0F, 0.0F, 0F, 4, 8, 6);

        this.HEAD.addChild(this.SNOUT);
        this.HEAD.addChild(this.RIGHT_EAR);
        this.HEAD.addChild(this.LEFT_EAR);

        this.CHEST.addChild(this.BUTT);
    }

    public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        //this.field_217119_h.rotateAngleX = 1.5707964F;
        //this.field_217124_m.rotateAngleX = -0.05235988F;
        this.RIGHT_ARM.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.RIGHT_LEG.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.LEFT_ARM.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.LEFT_LEG.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

        this.HEAD.setRotationPoint(0.0F, 10.5F, -9.5F);
        this.HEAD.rotateAngleY = 0.0F;
        this.HEAD.rotateAngleZ = entityIn.func_213475_v(partialTick);

        this.LEFT_ARM.showModel = true;
        this.RIGHT_ARM.showModel = true;
        this.LEFT_LEG.showModel = true;
        this.RIGHT_LEG.showModel = true;

        this.LEFT_ARM.setRotationPoint(-3.5F, 16.0F, -7.0F);
        this.RIGHT_ARM.setRotationPoint(3.5F, 16.0F, -7.0F);
        this.LEFT_LEG.setRotationPoint(4.5F, 16.0F, 6.0F);
        this.RIGHT_LEG.setRotationPoint(-4.5F, 16.0F, 6.0F);

        //this.field_217119_h.setRotationPoint(0.0F, 16.0F, -6.0F);
        //this.field_217119_h.rotateAngleZ = 0.0F;
        //this.field_217120_i.setRotationPoint(-5.0F, 17.5F, 7.0F);
        //this.field_217121_j.setRotationPoint(-1.0F, 17.5F, 7.0F);
    }

    @Override
    public void setRotationAngles(T entityIn, float v, float v1, float v2, float v3, float v4) {
        if (!entityIn.isSleeping()) {
            this.HEAD.rotateAngleX = v4 * 0.017453292F;
            this.HEAD.rotateAngleY = v4 * 0.017453292F;
        }

        /*float lvt_7_1_ = v2 - (float)entityIn.ticksExisted;
        float lvt_8_1_ = entityIn.getStandingAnimationScale(lvt_7_1_);
        lvt_8_1_ *= lvt_8_1_;
        float lvt_9_1_ = 1.0F - lvt_8_1_;

        this.HEAD.rotateAngleX = v4 * 0.017453292F;
        this.HEAD.rotateAngleY = v3 * 0.017453292F;

        this.RIGHT_LEG.rotateAngleX = MathHelper.cos(v * 0.6662F) * 1.4F * v1;
        this.LEFT_LEG.rotateAngleX  = MathHelper.cos(v * 0.6662F + (float)Math.PI) * 1.4F * v1;
        this.RIGHT_ARM.rotateAngleX = MathHelper.cos(v * 0.6662F + (float)Math.PI) * 1.4F * v1;
        this.LEFT_ARM.rotateAngleX  = MathHelper.cos(v * 0.6662F) * 1.4F * v1;

        if (entityIn.isSleeping()) {
            if (this.isChild) {
                this.HEAD.rotationPointY = 10.0F * lvt_9_1_ - lvt_8_1_;
                this.HEAD.rotationPointZ = -16.0F * lvt_9_1_ - lvt_8_1_;
            } else {
                this.HEAD.rotationPointY = 10.0F * lvt_9_1_ - lvt_8_1_;
                this.HEAD.rotationPointZ = -11.0F * lvt_9_1_ - lvt_8_1_;
            }
        }*/
    }

    @Override
    protected Iterable<ModelRenderer> getBodyParts() {
            return ImmutableList.of(this.CHEST, this.LEFT_LEG, this.RIGHT_LEG, this.LEFT_ARM, this.RIGHT_ARM);
    }

    @Override
    protected Iterable<ModelRenderer> getHeadParts() {
        return ImmutableList.of(this.HEAD);
    }
}
