package com.teamabnormals.buzzier_bees.client.render;

import com.teamabnormals.buzzier_bees.client.render.layer.MoobloomButtercupLayer;
import com.teamabnormals.buzzier_bees.common.entity.MoobloomEntity;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MoobloomRenderer extends MobRenderer<MoobloomEntity, CowModel<MoobloomEntity>> {
	public static final ResourceLocation MOOBLOOM_LOCATION = new ResourceLocation(BuzzierBees.MOD_ID, "textures/entity/cow/moobloom.png");
	public static final ModelLayerLocation MODEL_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(BuzzierBees.MOD_ID, "moobloom"), "main");

	public MoobloomRenderer(EntityRendererProvider.Context context) {
		super(context, new CowModel<>(context.bakeLayer(MODEL_LAYER_LOCATION)), 0.7F);
		this.addLayer(new MoobloomButtercupLayer<>(this));
	}

	public ResourceLocation getTextureLocation(MoobloomEntity entity) {
		return MOOBLOOM_LOCATION;
	}
}