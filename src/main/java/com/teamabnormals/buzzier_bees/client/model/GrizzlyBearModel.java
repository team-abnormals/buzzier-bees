package com.teamabnormals.buzzier_bees.client.model;

import com.teamabnormals.buzzier_bees.common.entity.animal.GrizzlyBear;
import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class GrizzlyBearModel<T extends GrizzlyBear> extends QuadrupedModel<T> {
	public ModelPart right_ear;
	public ModelPart left_ear;

	public GrizzlyBearModel(ModelPart root) {
		super(root, true, 25.0F, 6.8F, 2.7F, 3.0F, 49);
		this.right_ear = this.head.getChild("right_ear");
		this.left_ear = this.head.getChild("left_ear");
	}

	public static LayerDefinition createLayerDefinition() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition root = meshdefinition.getRoot();
		PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 47).addBox(-6.5F, -6.0F, -9.0F, 13.0F, 11.0F, 9.0F, false).texOffs(0, 13).addBox(-3.5F, 0.0F, -12.0F, 7.0F, 5.0F, 3.0F, false), PartPose.offsetAndRotation(0.0F, 9.0F, -16.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition rightEar = head.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(18, 0).addBox(-4.0F, -4.0F, -1.0F, 4.0F, 4.0F, 2.0F, false), PartPose.offsetAndRotation(-4.0F, -4.0F, -5.0F, 0.0F, 0.2618F, 0.0F));
		PartDefinition leftEar = head.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(18, 0).addBox(0.0F, -4.0F, -1.0F, 4.0F, 4.0F, 2.0F, true), PartPose.offsetAndRotation(4.0F, -4.0F, -5.0F, 0.0F, -0.2618F, 0.0F));
		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, -23.0F, -6.0F, 18.0F, 16.0F, 31.0F, false), PartPose.offsetAndRotation(0.0F, 24.0F, -10.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition rightHindLeg = root.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 7.0F, 6.0F, false), PartPose.offsetAndRotation(-5.0F, 17.0F, 11.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition leftHindLeg = root.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 7.0F, 6.0F, true), PartPose.offsetAndRotation(5.0F, 17.0F, 11.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition rightFrontLeg = root.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 7.0F, 6.0F, false), PartPose.offsetAndRotation(-5.0F, 17.0F, -10.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition leftFrontLeg = root.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 7.0F, 6.0F, true), PartPose.offsetAndRotation(5.0F, 17.0F, -10.0F, 0.0F, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 128, 128);
	}
}