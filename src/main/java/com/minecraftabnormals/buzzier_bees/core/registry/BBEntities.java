package com.minecraftabnormals.buzzier_bees.core.registry;

import com.minecraftabnormals.abnormals_core.core.util.registry.EntitySubRegistryHelper;
import com.minecraftabnormals.buzzier_bees.client.render.GrizzlyBearRenderer;
import com.minecraftabnormals.buzzier_bees.client.render.MoobloomRenderer;
import com.minecraftabnormals.buzzier_bees.common.entities.GrizzlyBearEntity;
import com.minecraftabnormals.buzzier_bees.common.entities.MoobloomEntity;
import com.minecraftabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BBEntities {
	public static final EntitySubRegistryHelper HELPER = BuzzierBees.REGISTRY_HELPER.getEntitySubHelper();

	public static final RegistryObject<EntityType<GrizzlyBearEntity>> GRIZZLY_BEAR = HELPER.createLivingEntity("grizzly_bear", GrizzlyBearEntity::new, EntityClassification.CREATURE, 1.4F, 1.4F);
	public static final RegistryObject<EntityType<MoobloomEntity>> MOOBLOOM = HELPER.createLivingEntity("moobloom", MoobloomEntity::new, EntityClassification.CREATURE, 0.9F, 1.4F);

	public static void registerEntitySpawns() {
		EntitySpawnPlacementRegistry.register(MOOBLOOM.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
	}

	public static void registerRendering() {
		RenderingRegistry.registerEntityRenderingHandler(GRIZZLY_BEAR.get(), GrizzlyBearRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(MOOBLOOM.get(), MoobloomRenderer::new);
	}

	@SubscribeEvent
	public static void onEntityAttributes(EntityAttributeCreationEvent event) {
		event.put(GRIZZLY_BEAR.get(), GrizzlyBearEntity.registerAttributes().create());
		event.put(MOOBLOOM.get(), MoobloomEntity.func_234188_eI_().create());
	}
}
