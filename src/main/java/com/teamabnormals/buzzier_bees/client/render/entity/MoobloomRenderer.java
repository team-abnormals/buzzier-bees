package com.teamabnormals.buzzier_bees.client.render.entity;

import com.teamabnormals.buzzier_bees.client.render.entity.layers.MoobloomFlowerLayer;
import com.teamabnormals.buzzier_bees.common.entity.animal.Moobloom;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import com.teamabnormals.buzzier_bees.core.other.BBModelLayers;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MoobloomRenderer extends MobRenderer<Moobloom, CowModel<Moobloom>> {
	public static final ResourceLocation MOOBLOOM_LOCATION = new ResourceLocation(BuzzierBees.MOD_ID, "textures/entity/cow/moobloom.png");

	public MoobloomRenderer(EntityRendererProvider.Context context) {
		super(context, new CowModel<>(context.bakeLayer(BBModelLayers.MOOBLOOM)), 0.7F);
		this.addLayer(new MoobloomFlowerLayer<>(this));
	}

	public ResourceLocation getTextureLocation(Moobloom entity) {
		return MOOBLOOM_LOCATION;
	}
}