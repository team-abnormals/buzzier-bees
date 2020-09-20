package com.minecraftabnormals.buzzier_bees.core.registry;

import com.minecraftabnormals.buzzier_bees.client.render.BlackBearRenderer;
import com.minecraftabnormals.buzzier_bees.client.render.BumblebeeRenderer;
import com.minecraftabnormals.buzzier_bees.client.render.GrizzlyBearRenderer;
import com.minecraftabnormals.buzzier_bees.common.entities.BlackBearEntity;
import com.minecraftabnormals.buzzier_bees.common.entities.BumblebeeEntity;
import com.minecraftabnormals.buzzier_bees.common.entities.GrizzlyBearEntity;
import com.minecraftabnormals.buzzier_bees.core.BuzzierBees;
import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.world.biome.Biome;
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

    @OnlyIn(Dist.CLIENT)
    public static void registerRendering() {
        RenderingRegistry.registerEntityRenderingHandler(GRIZZLY_BEAR.get(), GrizzlyBearRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(BLACK_BEAR.get(), BlackBearRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(BUMBLEBEE.get(), BumblebeeRenderer::new);
    }
    
    public static void init() {
    	registerAttributes();
    	addEntitySpawns();
    }
    
    private static void registerAttributes() {
    	GlobalEntityTypeAttributes.put(GRIZZLY_BEAR.get(), GrizzlyBearEntity.registerAttributes().create());
    	GlobalEntityTypeAttributes.put(BLACK_BEAR.get(), BlackBearEntity.registerAttributes().create());
    	GlobalEntityTypeAttributes.put(BUMBLEBEE.get(), BumblebeeEntity.registerAttributes().create());
    }
    
    private static void addEntitySpawns() {
 	   ForgeRegistries.BIOMES.getValues().stream().forEach(BBEntities::processSpawning);
    }
 	
    private static void processSpawning(Biome biome) {
    }
}
