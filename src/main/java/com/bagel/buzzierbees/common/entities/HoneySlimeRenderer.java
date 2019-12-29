package com.bagel.buzzierbees.common.entities;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SlimeGelLayer;
import net.minecraft.client.renderer.entity.model.SlimeModel;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HoneySlimeRenderer extends MobRenderer<HoneySlimeEntity, SlimeModel<HoneySlimeEntity>> {
   private static final ResourceLocation HONEY_SLIME_TEXTURES = new ResourceLocation("buzzierbees:textures/entity/honey_slime.png");

   public HoneySlimeRenderer(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new SlimeModel<>(16), 0.25F);
      this.addLayer(new SlimeGelLayer<>(this));
   }

   public void func_225623_a_(HoneySlimeEntity p_225623_1_, float p_225623_2_, float p_225623_3_, MatrixStack p_225623_4_, IRenderTypeBuffer p_225623_5_, int p_225623_6_) {
      this.shadowSize = 0.25F * (float)p_225623_1_.getSlimeSize();
      super.func_225623_a_(p_225623_1_, p_225623_2_, p_225623_3_, p_225623_4_, p_225623_5_, p_225623_6_);
   }

   @SuppressWarnings("unused")
   protected void func_225620_a_(HoneySlimeEntity p_225620_1_, MatrixStack p_225620_2_, float p_225620_3_) {
      float f = 0.999F;
      p_225620_2_.func_227862_a_(0.999F, 0.999F, 0.999F);
      p_225620_2_.func_227861_a_(0.0D, (double)0.001F, 0.0D);
      float f1 = (float)p_225620_1_.getSlimeSize();
      float f2 = MathHelper.lerp(p_225620_3_, p_225620_1_.prevSquishFactor, p_225620_1_.squishFactor) / (f1 * 0.5F + 1.0F);
      float f3 = 1.0F / (f2 + 1.0F);
      p_225620_2_.func_227862_a_(f3 * f1, 1.0F / f3 * f1, f3 * f1);
   }

   public ResourceLocation getEntityTexture(HoneySlimeEntity entity) {
      return HONEY_SLIME_TEXTURES;
   }
}
