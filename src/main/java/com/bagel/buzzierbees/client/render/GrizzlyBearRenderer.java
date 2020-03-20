package com.bagel.buzzierbees.client.render;

import com.bagel.buzzierbees.client.model.GrizzlyBearModel;
import com.bagel.buzzierbees.common.entities.GrizzlyBearEntity;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class GrizzlyBearRenderer extends MobRenderer<GrizzlyBearEntity, GrizzlyBearModel<GrizzlyBearEntity>> {
    private static final ResourceLocation GRIZZLY_BEAR_TEXTURE = new ResourceLocation("buzzierbees:textures/entity/grizzly_bear.png");

    public GrizzlyBearRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GrizzlyBearModel<>(), 0.9F);
    }

    protected void applyRotations(GrizzlyBearEntity entity, MatrixStack matrixStack, float f, float f1, float f2) {
        super.applyRotations(entity, matrixStack, f, f1, f2);
        if (entity.isSleeping()) {
            float lvt_6_1_ = -MathHelper.lerp(f2, entity.prevRotationPitch, entity.rotationPitch);
            matrixStack.rotate(Vector3f.XP.rotationDegrees(lvt_6_1_));
        }
    }

    @Override
    public ResourceLocation getEntityTexture(GrizzlyBearEntity grizzlyBearEntity) {
        return GRIZZLY_BEAR_TEXTURE;
    }
}
