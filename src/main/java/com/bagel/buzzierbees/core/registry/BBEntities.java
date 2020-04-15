package com.bagel.buzzierbees.core.registry;

import java.util.function.BiFunction;

import com.bagel.buzzierbees.client.render.BlackBearRenderer;
import com.bagel.buzzierbees.client.render.FlyRenderer;
import com.bagel.buzzierbees.client.render.GrizzlyBearRenderer;
import com.bagel.buzzierbees.client.render.HiveBoatRenderer;
import com.bagel.buzzierbees.client.render.HoneySlimeRenderer;
import com.bagel.buzzierbees.common.entities.BlackBearEntity;
import com.bagel.buzzierbees.common.entities.FlyEntity;
import com.bagel.buzzierbees.common.entities.GrizzlyBearEntity;
import com.bagel.buzzierbees.common.entities.HiveBoatEntity;
import com.bagel.buzzierbees.common.entities.HoneySlimeEntity;
import com.bagel.buzzierbees.core.BuzzierBees;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BBEntities
{
	public static final DeferredRegister<EntityType<?>> ENTITIES = new DeferredRegister<>(ForgeRegistries.ENTITIES, BuzzierBees.MODID);

	public static RegistryObject<EntityType<HiveBoatEntity>>  BOAT = ENTITIES.register("boat", () -> createEntity(HiveBoatEntity::new, HiveBoatEntity::new, EntityClassification.MISC, "boat", 1.375f, 0.5625f));

	public static RegistryObject<EntityType<HoneySlimeEntity>> HONEY_SLIME = ENTITIES.register("honey_slime", () -> createLivingEntity(HoneySlimeEntity::new, EntityClassification.CREATURE, "honey_slime", 1.02F, 1.02F));
	public static RegistryObject<EntityType<GrizzlyBearEntity>> GRIZZLY_BEAR = ENTITIES.register("grizzly_bear", () -> createLivingEntity(GrizzlyBearEntity::new, EntityClassification.CREATURE, "grizzly_bear", 1.4F, 1.4F));
	public static RegistryObject<EntityType<BlackBearEntity>> BLACK_BEAR = ENTITIES.register("black_bear", () -> createLivingEntity(BlackBearEntity::new, EntityClassification.CREATURE, "black_bear", 1.1F, 1.1F));
	public static RegistryObject<EntityType<FlyEntity>> FLY = ENTITIES.register("fly", () -> createLivingEntity(FlyEntity::new, EntityClassification.CREATURE, "fly", 0.4F, 0.4F));
	
    @OnlyIn(Dist.CLIENT)
    public static void registerRendering()
    {
        RenderingRegistry.registerEntityRenderingHandler((EntityType<? extends HiveBoatEntity>)BOAT.get(), HiveBoatRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler((EntityType<? extends HoneySlimeEntity>)HONEY_SLIME.get(), HoneySlimeRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler((EntityType<? extends GrizzlyBearEntity>)GRIZZLY_BEAR.get(), GrizzlyBearRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler((EntityType<? extends BlackBearEntity>)BLACK_BEAR.get(), BlackBearRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler((EntityType<? extends FlyEntity>)FLY.get(), FlyRenderer::new);
    }
    
    public static void addEntitySpawns() {
		EntitySpawnPlacementRegistry.register(HONEY_SLIME.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HoneySlimeEntity::honeySlimeCondition);
		
		FlyEntity.addSpawn();
	}
    
    private static <T extends LivingEntity> EntityType<T> createLivingEntity(EntityType.IFactory<T> factory, EntityClassification entityClassification, String name, float width, float height){
		ResourceLocation location = new ResourceLocation(BuzzierBees.MODID, name);
		EntityType<T> entity = EntityType.Builder.create(factory, entityClassification)
			.size(width, height)
			.setTrackingRange(64)
			.setShouldReceiveVelocityUpdates(true)
			.setUpdateInterval(3)
			.build(location.toString()
		);
		return entity;
	}
    
    private static <T extends Entity> EntityType<T> createEntity(EntityType.IFactory<T> factory, BiFunction<FMLPlayMessages.SpawnEntity, World, T> clientFactory, EntityClassification entityClassification, String name, float width, float height) {
		ResourceLocation location = new ResourceLocation(BuzzierBees.MODID, name);
		EntityType<T> entity = EntityType.Builder.create(factory, entityClassification)
			.size(width, height)
			.setTrackingRange(64)
			.setShouldReceiveVelocityUpdates(true)
			.setUpdateInterval(3)
			.setCustomClientFactory(clientFactory)
			.build(location.toString()
		);
		return entity;
	}
}
