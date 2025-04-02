package com.teamabnormals.buzzier_bees.core.other;

import com.teamabnormals.buzzier_bees.client.model.GrizzlyBearModel;
import com.teamabnormals.buzzier_bees.client.render.entity.GrizzlyBearRenderer;
import com.teamabnormals.buzzier_bees.client.render.entity.MoobloomRenderer;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import com.teamabnormals.buzzier_bees.core.registry.BBEntityTypes;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = BuzzierBees.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class BBModelLayers {
	public static final ModelLayerLocation GRIZZLY_BEAR = register("grizzly_bear");
	public static final ModelLayerLocation MOOBLOOM = register("moobloom");

	public static ModelLayerLocation register(String name) {
		return register(name, "main");
	}

	public static ModelLayerLocation register(String name, String layer) {
		return new ModelLayerLocation(BuzzierBees.location(name), layer);
	}

	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(BBModelLayers.GRIZZLY_BEAR, GrizzlyBearModel::createBodyLayer);
		event.registerLayerDefinition(BBModelLayers.MOOBLOOM, CowModel::createBodyLayer);
	}

	@SubscribeEvent
	public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(BBEntityTypes.MOOBLOOM.get(), MoobloomRenderer::new);
		event.registerEntityRenderer(BBEntityTypes.GRIZZLY_BEAR.get(), GrizzlyBearRenderer::new);
	}
}
