package com.teamabnormals.buzzier_bees.core.registry;

import com.teamabnormals.blueprint.core.util.registry.EntitySubRegistryHelper;
import com.teamabnormals.buzzier_bees.common.entity.animal.GrizzlyBear;
import com.teamabnormals.buzzier_bees.common.entity.animal.Moobloom;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent.Operation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class BBEntityTypes {
	public static final EntitySubRegistryHelper HELPER = BuzzierBees.REGISTRY_HELPER.getEntitySubHelper();

	public static final RegistryObject<EntityType<GrizzlyBear>> GRIZZLY_BEAR = HELPER.createLivingEntity("grizzly_bear", GrizzlyBear::new, MobCategory.CREATURE, 1.4F, 1.4F);
	public static final RegistryObject<EntityType<Moobloom>> MOOBLOOM = HELPER.createLivingEntity("moobloom", Moobloom::new, MobCategory.CREATURE, 0.9F, 1.4F);

	@SubscribeEvent
	public static void registerEntityAttributes(SpawnPlacementRegisterEvent event) {
		event.register(GRIZZLY_BEAR.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, Operation.AND);
		event.register(MOOBLOOM.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, Operation.AND);
	}

	@SubscribeEvent
	public static void onEntityAttributes(EntityAttributeCreationEvent event) {
		event.put(GRIZZLY_BEAR.get(), GrizzlyBear.registerAttributes().build());
		event.put(MOOBLOOM.get(), Moobloom.createAttributes().build());
	}
}
