package com.bagel.buzzierbees.client.model;

import java.util.Arrays;

import com.bagel.buzzierbees.common.entities.HiveBoatEntity;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HiveBoatModel extends SegmentedModel<HiveBoatEntity> {
   private final ModelRenderer[] paddles = new ModelRenderer[2];
   private final ModelRenderer noWater;
   private final ImmutableList<ModelRenderer> field_228243_f_;

   public HiveBoatModel() {
      ModelRenderer[] amodelrenderer = new ModelRenderer[]{(new ModelRenderer(this, 0, 0)).setTextureSize(128, 64), (new ModelRenderer(this, 0, 19)).setTextureSize(128, 64), (new ModelRenderer(this, 0, 27)).setTextureSize(128, 64), (new ModelRenderer(this, 0, 35)).setTextureSize(128, 64), (new ModelRenderer(this, 0, 43)).setTextureSize(128, 64)};
      amodelrenderer[0].addBox(-14.0F, -9.0F, -3.0F, 28.0F, 16.0F, 3.0F, 0.0F);
      amodelrenderer[0].setRotationPoint(0.0F, 3.0F, 1.0F);
      amodelrenderer[1].addBox(-13.0F, -7.0F, -1.0F, 18.0F, 6.0F, 2.0F, 0.0F);
      amodelrenderer[1].setRotationPoint(-15.0F, 4.0F, 4.0F);
      amodelrenderer[2].addBox(-8.0F, -7.0F, -1.0F, 16.0F, 6.0F, 2.0F, 0.0F);
      amodelrenderer[2].setRotationPoint(15.0F, 4.0F, 0.0F);
      amodelrenderer[3].addBox(-14.0F, -7.0F, -1.0F, 28.0F, 6.0F, 2.0F, 0.0F);
      amodelrenderer[3].setRotationPoint(0.0F, 4.0F, -9.0F);
      amodelrenderer[4].addBox(-14.0F, -7.0F, -1.0F, 28.0F, 6.0F, 2.0F, 0.0F);
      amodelrenderer[4].setRotationPoint(0.0F, 4.0F, 9.0F);
      amodelrenderer[0].rotateAngleX = ((float)Math.PI / 2F);
      amodelrenderer[1].rotateAngleY = ((float)Math.PI * 1.5F);
      amodelrenderer[2].rotateAngleY = ((float)Math.PI / 2F);
      amodelrenderer[3].rotateAngleY = (float)Math.PI;
      this.paddles[0] = this.func_187056_a(true);
      this.paddles[0].setRotationPoint(3.0F, -5.0F, 9.0F);
      this.paddles[1] = this.func_187056_a(false);
      this.paddles[1].setRotationPoint(3.0F, -5.0F, -9.0F);
      this.paddles[1].rotateAngleY = (float)Math.PI;
      this.paddles[0].rotateAngleZ = 0.19634955F;
      this.paddles[1].rotateAngleZ = 0.19634955F;
      this.noWater = (new ModelRenderer(this, 0, 0)).setTextureSize(128, 64);
      this.noWater.addBox(-14.0F, -9.0F, -3.0F, 28.0F, 16.0F, 3.0F, 0.0F);
      this.noWater.setRotationPoint(0.0F, -3.0F, 1.0F);
      this.noWater.rotateAngleX = ((float)Math.PI / 2F);
      Builder<ModelRenderer> builder = ImmutableList.builder();
      builder.addAll(Arrays.asList(amodelrenderer));
      builder.addAll(Arrays.asList(this.paddles));
      this.field_228243_f_ = builder.build();
   }

   @Override
   public void render(HiveBoatEntity p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
      this.func_228244_a_(p_225597_1_, 0, p_225597_2_);
      this.func_228244_a_(p_225597_1_, 1, p_225597_2_);
   }

   public ImmutableList<ModelRenderer> func_225601_a_() {
      return this.field_228243_f_;
   }

   public ModelRenderer func_228245_c_() {
      return this.noWater;
   }
   
   @Override
   public Iterable<ModelRenderer> getParts() {
		return this.getParts();
	}

   protected ModelRenderer func_187056_a(boolean p_187056_1_) {
      ModelRenderer modelrenderer = (new ModelRenderer(this, 62, p_187056_1_ ? 0 : 20)).setTextureSize(128, 64);
      modelrenderer.addBox(-1.0F, 0.0F, -5.0F, 2.0F, 2.0F, 18.0F);
      modelrenderer.addBox(p_187056_1_ ? -1.001F : 0.001F, -3.0F, 8.0F, 1.0F, 6.0F, 7.0F);
      return modelrenderer;
   }

   protected void func_228244_a_(HiveBoatEntity entity, int p_228244_2_, float p_228244_3_) {
      float f = entity.getRowingTime(p_228244_2_, p_228244_3_);
      ModelRenderer modelrenderer = this.paddles[p_228244_2_];
      modelrenderer.rotateAngleX = (float)MathHelper.clampedLerp((double)(-(float)Math.PI / 3F), (double)-0.2617994F, (double)((MathHelper.sin(-f) + 1.0F) / 2.0F));
      modelrenderer.rotateAngleY = (float)MathHelper.clampedLerp((double)(-(float)Math.PI / 4F), (double)((float)Math.PI / 4F), (double)((MathHelper.sin(-f + 1.0F) + 1.0F) / 2.0F));
      if (p_228244_2_ == 1) {
         modelrenderer.rotateAngleY = (float)Math.PI - modelrenderer.rotateAngleY;
      }

   }
}