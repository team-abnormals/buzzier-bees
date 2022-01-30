package com.teamabnormals.buzzier_bees.core.registry;

import com.teamabnormals.blueprint.core.util.registry.EntitySubRegistryHelper;
import com.teamabnormals.buzzier_bees.common.entity.animal.GrizzlyBearEntity;
import com.teamabnormals.buzzier_bees.common.entity.animal.MoobloomEntity;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BBEntityTypes {
	public static final EntitySubRegistryHelper HELPER = BuzzierBees.REGISTRY_HELPER.getEntitySubHelper();

	public static final RegistryObject<EntityType<GrizzlyBearEntity>> GRIZZLY_BEAR = HELPER.createLivingEntity("grizzly_bear", GrizzlyBearEntity::new, MobCategory.CREATURE, 1.4F, 1.4F);
	public static final RegistryObject<EntityType<MoobloomEntity>> MOOBLOOM = HELPER.createLivingEntity("moobloom", MoobloomEntity::new, MobCategory.CREATURE, 0.9F, 1.4F);

	public static void registerEntitySpawns() {
		SpawnPlacements.register(MOOBLOOM.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
	}

	@SubscribeEvent
	public static void onEntityAttributes(EntityAttributeCreationEvent event) {
		event.put(GRIZZLY_BEAR.get(), GrizzlyBearEntity.registerAttributes().build());
		event.put(MOOBLOOM.get(), MoobloomEntity.createAttributes().build());
	}
}
