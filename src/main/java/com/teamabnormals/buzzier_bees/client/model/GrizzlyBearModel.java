package com.teamabnormals.buzzier_bees.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import com.teamabnormals.buzzier_bees.common.entity.animal.GrizzlyBear;
import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class GrizzlyBearModel<T extends GrizzlyBear> extends QuadrupedModel<T> {
	public final ModelPart rightEar;
	public final ModelPart leftEar;

	public GrizzlyBearModel(ModelPart root) {
		super(root, true, 25.0F, 6.8F, 2.7F, 3.0F, 49);
		this.rightEar = this.head.getChild("right_ear");
		this.leftEar = this.head.getChild("left_ear");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition root = meshdefinition.getRoot();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 47).addBox(-6.5F, -6.0F, -9.0F, 13.0F, 11.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 9.25F, -16.0F));
		PartDefinition right_ear = head.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(18, 0).addBox(-4.0F, -4.0F, -1.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -4.0F, -5.0F, 0.0F, 0.2618F, 0.0F));
		PartDefinition left_ear = head.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(18, 0).mirror().addBox(0.0F, -4.0F, -1.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.0F, -4.0F, -5.0F, 0.0F, -0.2618F, 0.0F));
		PartDefinition nose = head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(0, 13).addBox(-3.5F, 0.0F, -12.0F, 7.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, -8.0F, -15.5F, 18.0F, 16.0F, 31.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 9.0F, -0.5F));
		PartDefinition right_front_leg = partdefinition.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 17.0F, -10.0F));
		PartDefinition left_front_leg = partdefinition.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3.0F, 0.0F, -3.0F, 6.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.0F, 17.0F, -10.0F));
		PartDefinition right_hind_leg = partdefinition.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 17.0F, 11.0F));
		PartDefinition left_hind_leg = partdefinition.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3.0F, 0.0F, -3.0F, 6.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.0F, 17.0F, 11.0F));
		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		super.renderToBuffer(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public void prepareMobModel(T entity, float p_103662_, float p_103663_, float p_103664_) {
		super.prepareMobModel(entity, p_103662_, p_103663_, p_103664_);
	}

	@Override
	public void setupAnim(T bear, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(bear, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		this.body.xScale = 1.0F;
		this.body.yScale = 1.0F;
		this.body.zScale = 1.0F;
		if (bear.isSleeping()) {
			float scale = (float) (1.0F + Math.sin(ageInTicks / 20)) / 30.0F;
			this.body.offsetScale(new Vector3f(scale, scale, scale));
		}
	}

	public ModelPart getHead() {
		return this.head;
	}

	public ModelPart getBody() {
		return this.body;
	}
}