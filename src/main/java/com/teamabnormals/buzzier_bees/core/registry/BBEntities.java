package com.teamabnormals.buzzier_bees.core.registry;

import com.teamabnormals.blueprint.core.util.registry.EntitySubRegistryHelper;
import com.teamabnormals.buzzier_bees.client.model.GrizzlyBearModel;
import com.teamabnormals.buzzier_bees.client.render.GrizzlyBearRenderer;
import com.teamabnormals.buzzier_bees.client.render.MoobloomRenderer;
import com.teamabnormals.buzzier_bees.common.entities.GrizzlyBearEntity;
import com.teamabnormals.buzzier_bees.common.entities.MoobloomEntity;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BBEntities {
	public static final EntitySubRegistryHelper HELPER = BuzzierBees.REGISTRY_HELPER.getEntitySubHelper();

	public static final RegistryObject<EntityType<GrizzlyBearEntity>> GRIZZLY_BEAR = HELPER.createLivingEntity("grizzly_bear", GrizzlyBearEntity::new, MobCategory.CREATURE, 1.4F, 1.4F);
	public static final RegistryObject<EntityType<MoobloomEntity>> MOOBLOOM = HELPER.createLivingEntity("moobloom", MoobloomEntity::new, MobCategory.CREATURE, 0.9F, 1.4F);

	public static final ModelLayerLocation GRIZZLY_BEAR_LAYER = new ModelLayerLocation(new ResourceLocation(BuzzierBees.MOD_ID, "grizzly_bear"), "main");
	public static final ModelLayerLocation MOOBLOOM_LAYER = new ModelLayerLocation(new ResourceLocation(BuzzierBees.MOD_ID, "moobloom"), "main");

	public static void registerEntitySpawns() {
		SpawnPlacements.register(MOOBLOOM.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
	}

	@SubscribeEvent
	public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(GRIZZLY_BEAR_LAYER, GrizzlyBearModel::createBodyLayer);
		event.registerLayerDefinition(MOOBLOOM_LAYER, CowModel::createBodyLayer);
	}

	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(BBEntities.GRIZZLY_BEAR.get(), GrizzlyBearRenderer::new);
		event.registerEntityRenderer(BBEntities.MOOBLOOM.get(), MoobloomRenderer::new);
	}

	@SubscribeEvent
	public static void onEntityAttributes(EntityAttributeCreationEvent event) {
		event.put(GRIZZLY_BEAR.get(), GrizzlyBearEntity.registerAttributes().build());
		event.put(MOOBLOOM.get(), MoobloomEntity.createAttributes().build());
	}
}
