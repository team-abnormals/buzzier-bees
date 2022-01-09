package com.teamabnormals.buzzier_bees.client.model;

import com.teamabnormals.buzzier_bees.common.entities.GrizzlyBearEntity;
import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class GrizzlyBearModel<T extends GrizzlyBearEntity> extends QuadrupedModel<T> {
	private static final String HEAD = "head";
	private static final String LEFT_EAR = "left_ear";
	private static final String RIGHT_EAR = "right_ear";
	protected final ModelPart leftEar;
	protected final ModelPart rightEar;

	public GrizzlyBearModel(ModelPart root) {
		super(root, true, 25.0F, 6.8F, 2.7F, 3.0F, 49);
		leftEar = root.getChild(HEAD).getChild(LEFT_EAR);
		rightEar = root.getChild(HEAD).getChild(RIGHT_EAR);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition headPart = partdefinition.addOrReplaceChild(
				HEAD,
				CubeListBuilder.create()
						.texOffs(0, 47).addBox(-6.5F, -6.0F, -9.0F, 13.0F, 11.0F, 9.0F)
						.texOffs(0, 13).addBox(-3.5F, 0.0F, -12.0F, 7.0F, 5.0F, 3.0F),
				PartPose.offset(0.0F, 9.0F, -16.0F)
		);
		headPart.addOrReplaceChild(
				LEFT_EAR,
				CubeListBuilder.create()
						.texOffs(18, 0).addBox(-4.0F, -4.0F, -1.0F, 4.0F, 4.0F, 2.0F),
				PartPose.offsetAndRotation(-4.0F, -4.0F, -5.0F, 0.0F, 0.2618F, 0.0F)
		);
		headPart.addOrReplaceChild(
				RIGHT_EAR,
				CubeListBuilder.create().texOffs(18, 0).addBox(0.0F, -4.0F, -1.0F, 4.0F, 4.0F, 2.0F),
				PartPose.offsetAndRotation(4.0F, -4.0F, -5.0F, 0.0F, -0.2618F, 0.0F)
		);
		partdefinition.addOrReplaceChild(
				"body",
				CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, -23.0F, -6.0F, 18.0F, 16.0F, 31.0F),
				PartPose.offset(0.0F, 24.0F, -10.0F)
		);
		partdefinition.addOrReplaceChild(
				"right_hind_leg",
				CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 7.0F, 6.0F),
				PartPose.offset(-5.0F, 17.0F, 11.0F)
		);
		partdefinition.addOrReplaceChild(
				"left_hind_leg",
				CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 7.0F, 6.0F),
				PartPose.offset(5.0F, 17.0F, 11.0F)
		);
		partdefinition.addOrReplaceChild(
				"right_front_leg",
				CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 7.0F, 6.0F),
				PartPose.offset(-5.0F, 17.0F, -10.0F)
		);
		partdefinition.addOrReplaceChild(
				"left_front_leg",
				CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 7.0F, 6.0F),
				PartPose.offset(5.0F, 17.0F, -10.0F)
		);

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		if (entityIn.isEating()) {
			this.body.xRot = 270.0F;
		} else {
			this.body.xRot = 0.0F;
		}
	}

	public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}