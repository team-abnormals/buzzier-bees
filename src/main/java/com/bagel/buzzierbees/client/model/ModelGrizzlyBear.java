package com.bagel.buzzierbees.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelBear - Booper
 * Created using Tabula 7.0.0
 */
public class ModelGrizzlyBear extends ModelBase {
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

    public ModelGrizzlyBear() {
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
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.chest.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
