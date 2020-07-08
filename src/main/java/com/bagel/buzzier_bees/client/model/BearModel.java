package com.bagel.buzzier_bees.client.model;

import com.bagel.buzzier_bees.common.entities.AbstractBearEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.teamabnormals.abnormals_core.core.library.endimator.EndimatorEntityModel;
import com.teamabnormals.abnormals_core.core.library.endimator.EndimatorModelRenderer;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

/**
 * BearModel - Byper
 * Created using Tabula 7.0.0
 */

public class BearModel<E extends AbstractBearEntity> extends EndimatorEntityModel<E> {
    public EndimatorModelRenderer chest;
    public EndimatorModelRenderer butt;
    public EndimatorModelRenderer left_arm;
    public EndimatorModelRenderer right_arm;
    public EndimatorModelRenderer head;
    public EndimatorModelRenderer left_leg;
    public EndimatorModelRenderer right_leg;
    public EndimatorModelRenderer snout;
    public EndimatorModelRenderer left_ear;
    public EndimatorModelRenderer right_ear;

    public BearModel() {
        this.textureWidth = 80;
        this.textureHeight = 80;
        this.chest = new EndimatorModelRenderer(this, 0, 0);
        this.chest.setRotationPoint(0.0F, 11.5F, -3.5F);
        this.chest.addBox(-7.0F, -5.5F, -6.5F, 14, 11, 13, 0.0F);
        this.head = new EndimatorModelRenderer(this, 0, 45);
        this.head.setRotationPoint(0.0F, 0.0F, -6.5F);
        this.head.addBox(-4.5F, -3.0F, -6.0F, 9, 7, 6, 0.0F);
        this.butt = new EndimatorModelRenderer(this, 0, 24);
        this.butt.setRotationPoint(0.0F, 0.5F, 7.0F);
        this.butt.addBox(-6.0F, -5.0F, -0.5F, 12, 10, 11, 0.0F);
        this.right_leg = new EndimatorModelRenderer(this, 57, 0);
        this.right_leg.mirror = true;
        this.right_leg.setRotationPoint(-3.5F, 4.5F, 13.0F);
        this.right_leg.addBox(-2.0F, 0.0F, -3.5F, 4, 8, 6, 0.0F);
        this.right_arm = new EndimatorModelRenderer(this, 42, 1);
        this.right_arm.mirror = true;
        this.right_arm.setRotationPoint(-3.5F, 5.0F, -3.0F);
        this.right_arm.addBox(-2.0F, -0.5F, -2.0F, 4, 8, 4, 0.0F);
        this.right_ear = new EndimatorModelRenderer(this, 0, 49);
        this.right_ear.setRotationPoint(-3.5F, -5.0F, -4.0F);
        this.right_ear.addBox(-1.0F, -2.0F, 0.0F, 2, 2, 1, 0.0F);
        this.setRotateAngle(right_ear, 0.0F, 0.0F, -3.141592653589793F);
        this.snout = new EndimatorModelRenderer(this, 24, 45);
        this.snout.setRotationPoint(0.0F, 2.5F, -6.0F);
        this.snout.addBox(-2.5F, -1.5F, -3.0F, 5, 3, 3, 0.0F);
        this.left_arm = new EndimatorModelRenderer(this, 42, 1);
        this.left_arm.setRotationPoint(3.5F, 5.0F, -3.0F);
        this.left_arm.addBox(-2.0F, -0.5F, -2.0F, 4, 8, 4, 0.0F);
        this.left_ear = new EndimatorModelRenderer(this, 0, 49);
        this.left_ear.mirror = true;
        this.left_ear.setRotationPoint(3.5F, -5.0F, -4.0F);
        this.left_ear.addBox(-1.0F, -2.0F, 0.0F, 2, 2, 1, 0.0F);
        this.setRotateAngle(left_ear, 0.0F, 0.0F, 3.141592653589793F);
        this.left_leg = new EndimatorModelRenderer(this, 57, 0);
        this.left_leg.setRotationPoint(3.5F, 4.5F, 13.0F);
        this.left_leg.addBox(-2.0F, 0.0F, -3.5F, 4, 8, 6, 0.0F);
        this.chest.addChild(this.head);
        this.chest.addChild(this.butt);
        this.chest.addChild(this.right_leg);
        this.chest.addChild(this.right_arm);
        this.head.addChild(this.right_ear);
        this.head.addChild(this.snout);
        this.chest.addChild(this.left_arm);
        this.head.addChild(this.left_ear);
        this.chest.addChild(this.left_leg);

        this.setDefaultBoxValues();
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
    	this.animateModel(this.entity);
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
    
    @Override
	public void animateModel(E bear) {
    	super.animateModel(bear);
    	if(entity.isEndimationPlaying(AbstractBearEntity.SLEEP_DOWN)) {
    		bear.setSleeping(false);
    	    this.setEndimationToPlay(AbstractBearEntity.SLEEP_DOWN);
    	    this.startKeyframe(40);
    	    
    	    this.rotate(this.head, 0.28F, 0.0F, 0.23F);
    	    this.rotate(this.right_arm, -1.34F, 0.0F, 0.0F);
    	    this.rotate(this.left_arm, -1.34F, 0.0F, 0.0F);
    	    this.rotate(this.right_leg, -0.56F, 0.14F, 1.26F);
    	    this.rotate(this.left_leg, -0.56F, -0.14F, -1.26F);
    	    bear.setSleeping(true);
    	    this.move(this.chest, 0F, 5F, 0F);
    	    this.move(this.right_leg, -0.5F, 0F, 0.8F);
    	    this.move(this.left_leg, -0.5F, 0F, 0.8F);
    	    
    	    this.endKeyframe();
    	}
    	if(entity.isEndimationPlaying(AbstractBearEntity.ATTACK)) {
    	    this.setEndimationToPlay(AbstractBearEntity.ATTACK);
    	    this.startKeyframe(10);
    	    
    	    this.move(this.chest, 0F, -8F, 0F);

    	    this.rotate(this.head, 0.76F, 0.0F, 0.0F);
    	    this.rotate(this.chest, -0.76F, 0.0F, 0.0F);
    	    this.rotate(this.butt, -0.26F, 0.0F, 0.0F);
    	    this.rotate(this.right_arm, -0.73F, -0.09F, 0.44F);
    	    this.rotate(this.left_arm, -0.73F, -0.09F, -0.44F);
    	    this.rotate(this.right_leg, 1.05F, 0F, 0F);
    	    this.rotate(this.left_leg, 1.05F, 0F, 0F);
    	    
    	    this.endKeyframe();
    	    this.resetKeyframe(10);

    	}
    	if(entity.isEndimationPlaying(AbstractBearEntity.SIT_DOWN)) {
    	    this.setEndimationToPlay(AbstractBearEntity.SIT_DOWN);
    	    this.startKeyframe(10);
    	    
    	    this.move(this.chest, 0F, -1F, 0F);
    	    this.move(this.head, 0F, -2F, -2F);
    	    this.move(this.butt, 0F, -0.5F, -3F);
    	    this.move(this.left_arm, 0F, -1F, 1F);
    	    this.move(this.right_arm, 0F, -1F, 1F);
    	    this.move(this.left_leg, 0F, -1F, 2F);
    	    this.move(this.right_leg, 0F, -1F, 2F);

    	    this.rotate(this.head, 1.05F, 0.0F, 0.0F);
    	    this.rotate(this.chest, -0.87F, 0.0F, 0.0F);
    	    this.rotate(this.butt, -0.7F, 0.0F, 0.0F);
    	    this.rotate(this.right_arm, 0F, 0F, -0.26F);
    	    this.rotate(this.left_arm, 0F, 0F, 0.26F);
    	    this.rotate(this.right_leg, 0F, 0F, 0.44F);
    	    this.rotate(this.left_leg, 0F, 0F, -0.44F);
    	    
    	    this.endKeyframe();
    	}

    }
    
    
    public void setRotationAngles(E entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    	super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    	
        this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
        this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        this.right_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.left_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.right_arm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.left_arm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }
}
