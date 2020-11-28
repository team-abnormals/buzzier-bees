package com.minecraftabnormals.buzzier_bees.client.render;

import com.minecraftabnormals.buzzier_bees.client.render.layer.MoobloomButtercupLayer;
import com.minecraftabnormals.buzzier_bees.common.entities.MoobloomEntity;
import com.minecraftabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.CowModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MoobloomRenderer extends MobRenderer<MoobloomEntity, CowModel<MoobloomEntity>> {

	public MoobloomRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new CowModel<>(), 0.7F);
		this.addLayer(new MoobloomButtercupLayer<>(this));
	}

	public ResourceLocation getEntityTexture(MoobloomEntity entity) {
		return new ResourceLocation(BuzzierBees.MODID, "textures/entity/cow/moobloom.png");
	}
}