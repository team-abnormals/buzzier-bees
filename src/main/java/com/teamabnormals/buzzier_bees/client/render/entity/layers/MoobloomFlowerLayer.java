package com.teamabnormals.buzzier_bees.client.render.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.teamabnormals.buzzier_bees.common.entity.animal.Moobloom;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MoobloomFlowerLayer<T extends Moobloom> extends RenderLayer<T, CowModel<T>> {

	public MoobloomFlowerLayer(RenderLayerParent<T, CowModel<T>> renderer) {
		super(renderer);
	}

	public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLightIn, T moobloom, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		if (!moobloom.isBaby() && !moobloom.isInvisible()) {
			BlockRenderDispatcher dispatcher = Minecraft.getInstance().getBlockRenderer();
			BlockState blockstate = moobloom.getFlower().defaultBlockState();
			int i = LivingEntityRenderer.getOverlayCoords(moobloom, 0.0F);
			poseStack.pushPose();
			poseStack.translate(0.2F, -0.35F, 0.5D);
			poseStack.mulPose(Vector3f.YP.rotationDegrees(-48.0F));
			poseStack.scale(-1.0F, -1.0F, 1.0F);
			poseStack.translate(-0.5D, -0.5D, -0.5D);
			dispatcher.renderSingleBlock(blockstate, poseStack, buffer, packedLightIn, i);
			poseStack.popPose();
			poseStack.pushPose();
			poseStack.translate(0.2F, -0.35F, 0.5D);
			poseStack.mulPose(Vector3f.YP.rotationDegrees(42.0F));
			poseStack.translate(0.1F, 0.0D, -0.6F);
			poseStack.mulPose(Vector3f.YP.rotationDegrees(-48.0F));
			poseStack.scale(-1.0F, -1.0F, 1.0F);
			poseStack.translate(-0.5D, -0.5D, -0.5D);
			dispatcher.renderSingleBlock(blockstate, poseStack, buffer, packedLightIn, i);
			poseStack.popPose();
			poseStack.pushPose();
			this.getParentModel().getHead().translateAndRotate(poseStack);
			poseStack.translate(0.0D, -0.7F, -0.2F);
			poseStack.mulPose(Vector3f.YP.rotationDegrees(-78.0F));
			poseStack.scale(-1.0F, -1.0F, 1.0F);
			poseStack.translate(-0.5D, -0.5D, -0.5D);
			dispatcher.renderSingleBlock(blockstate, poseStack, buffer, packedLightIn, i);
			poseStack.popPose();
		}
	}
}