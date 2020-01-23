package com.bagel.buzzierbees.client.render;

import com.bagel.buzzierbees.client.model.HiveBoatModel;
import com.bagel.buzzierbees.common.entities.HiveBoatEntity;
import com.bagel.buzzierbees.core.BuzzierBees;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HiveBoatRenderer extends EntityRenderer<HiveBoatEntity> {
   private static final ResourceLocation[] BOAT_TEXTURES = new ResourceLocation[]{new ResourceLocation(BuzzierBees.MODID, "textures/entity/boat/hive.png")};
   protected final HiveBoatModel field_76998_a = new HiveBoatModel();

   public HiveBoatRenderer(EntityRendererManager renderManagerIn) {
      super(renderManagerIn);
      this.shadowSize = 0.8F;
   }
   
   @Override
   public void render(HiveBoatEntity p_225623_1_, float p_225623_2_, float p_225623_3_, MatrixStack p_225623_4_, IRenderTypeBuffer p_225623_5_, int p_225623_6_) {
      p_225623_4_.push();
      p_225623_4_.translate(0.0D, 0.375D, 0.0D);
      p_225623_4_.rotate(Vector3f.YP.rotationDegrees(180.0F - p_225623_2_));
      float f = (float)p_225623_1_.getTimeSinceHit() - p_225623_3_;
      float f1 = p_225623_1_.getDamageTaken() - p_225623_3_;
      if (f1 < 0.0F) {
         f1 = 0.0F;
      }

      if (f > 0.0F) {
         p_225623_4_.rotate(Vector3f.XP.rotationDegrees(MathHelper.sin(f) * f * f1 / 10.0F * (float)p_225623_1_.getForwardDirection()));
      }

      float f2 = p_225623_1_.getRockingAngle(p_225623_3_);
      if (!MathHelper.epsilonEquals(f2, 0.0F)) {
         p_225623_4_.rotate(new Quaternion(new Vector3f(1.0F, 0.0F, 1.0F), p_225623_1_.getRockingAngle(p_225623_3_), true));
      }

      p_225623_4_.scale(-1.0F, -1.0F, 1.0F);
      p_225623_4_.rotate(Vector3f.YP.rotationDegrees(90.0F));
      this.field_76998_a.render(p_225623_1_, p_225623_3_, 0.0F, -0.1F, 0.0F, 0.0F);
      IVertexBuilder ivertexbuilder = p_225623_5_.getBuffer(this.field_76998_a.getRenderType(this.getEntityTexture(p_225623_1_)));
      this.field_76998_a.render(p_225623_4_, ivertexbuilder, p_225623_6_, OverlayTexture.DEFAULT_LIGHT, 1.0F, 1.0F, 1.0F, 1.0F);
      IVertexBuilder ivertexbuilder1 = p_225623_5_.getBuffer(RenderType.waterMask());
      this.field_76998_a.func_228245_c_().render(p_225623_4_, ivertexbuilder1, p_225623_6_, OverlayTexture.DEFAULT_LIGHT);
      p_225623_4_.pop();
      super.render(p_225623_1_, p_225623_2_, p_225623_3_, p_225623_4_, p_225623_5_, p_225623_6_);
   }

   @Override
   public ResourceLocation getEntityTexture(HiveBoatEntity entity) {
      return BOAT_TEXTURES[entity.getBoatType().ordinal()];
   }
}
