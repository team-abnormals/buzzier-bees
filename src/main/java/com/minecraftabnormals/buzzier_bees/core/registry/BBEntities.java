package com.minecraftabnormals.buzzier_bees.core.registry;

import com.minecraftabnormals.buzzier_bees.client.render.BlackBearRenderer;
import com.minecraftabnormals.buzzier_bees.client.render.BumblebeeRenderer;
import com.minecraftabnormals.buzzier_bees.client.render.GrizzlyBearRenderer;
import com.minecraftabnormals.buzzier_bees.client.render.MoobloomRenderer;
import com.minecraftabnormals.buzzier_bees.common.entities.*;
import com.minecraftabnormals.buzzier_bees.core.BuzzierBees;
import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BBEntities
{
	public static final RegistryHelper HELPER = BuzzierBees.REGISTRY_HELPER;

	public static final RegistryObject<EntityType<GrizzlyBearEntity>> 	GRIZZLY_BEAR 	= HELPER.createLivingEntity("grizzly_bear", GrizzlyBearEntity::new, EntityClassification.CREATURE, 1.4F, 1.4F);
	public static final RegistryObject<EntityType<BlackBearEntity>> 	BLACK_BEAR 		= HELPER.createLivingEntity("black_bear", BlackBearEntity::new, EntityClassification.CREATURE, 1.1F, 1.1F);
	public static final RegistryObject<EntityType<BumblebeeEntity>> 	BUMBLEBEE 		= HELPER.createLivingEntity("bumblebee", BumblebeeEntity::new, EntityClassification.CREATURE, 0.5F, 0.5F);
	public static final RegistryObject<EntityType<MoobloomEntity>> 		MOOBLOOM 		= HELPER.createLivingEntity("moobloom", MoobloomEntity::new, EntityClassification.CREATURE, 0.9F, 1.4F);

    @OnlyIn(Dist.CLIENT)
    public static void registerRendering() {
        RenderingRegistry.registerEntityRenderingHandler(GRIZZLY_BEAR.get(), GrizzlyBearRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(BLACK_BEAR.get(), BlackBearRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(BUMBLEBEE.get(), BumblebeeRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(MOOBLOOM.get(), MoobloomRenderer::new);
    }
    
    public static void registerAttributes() {
    	GlobalEntityTypeAttributes.put(GRIZZLY_BEAR.get(), AbstractBearEntity.registerAttributes().create());
    	GlobalEntityTypeAttributes.put(BLACK_BEAR.get(), AbstractBearEntity.registerAttributes().create());
    	GlobalEntityTypeAttributes.put(BUMBLEBEE.get(), BumblebeeEntity.registerAttributes().create());
    	GlobalEntityTypeAttributes.put(MOOBLOOM.get(), MoobloomEntity.func_234188_eI_().create());
    }
    
    public static void registerEntitySpawns() {
		EntitySpawnPlacementRegistry.register(MOOBLOOM.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
		ForgeRegistries.BIOMES.getValues().forEach(BBEntities::addSpawns);
	}

	private static void addSpawns(Biome biome) {
		if (biome == Biomes.FLOWER_FOREST || biome == Biomes.SUNFLOWER_PLAINS)
			biome.getSpawns(EntityClassification.CREATURE).add(new Biome.SpawnListEntry(MOOBLOOM.get(), 30, 2, 4));
	}
}
