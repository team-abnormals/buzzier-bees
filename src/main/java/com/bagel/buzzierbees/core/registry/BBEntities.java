package com.bagel.buzzierbees.core.registry;

import com.bagel.buzzierbees.client.render.BlackBearRenderer;
import com.bagel.buzzierbees.client.render.BumblebeeRenderer;
import com.bagel.buzzierbees.client.render.GrizzlyBearRenderer;
import com.bagel.buzzierbees.client.render.HoneySlimeRenderer;
import com.bagel.buzzierbees.common.entities.BlackBearEntity;
import com.bagel.buzzierbees.common.entities.BumblebeeEntity;
import com.bagel.buzzierbees.common.entities.GrizzlyBearEntity;
import com.bagel.buzzierbees.common.entities.HoneySlimeEntity;
import com.bagel.buzzierbees.core.BuzzierBees;
import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
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

	public static final RegistryObject<EntityType<HoneySlimeEntity>> 	HONEY_SLIME 	= HELPER.createLivingEntity("honey_slime", HoneySlimeEntity::new, EntityClassification.CREATURE, 1.02F, 1.02F);
	public static final RegistryObject<EntityType<GrizzlyBearEntity>> 	GRIZZLY_BEAR 	= HELPER.createLivingEntity("grizzly_bear", GrizzlyBearEntity::new, EntityClassification.CREATURE, 1.4F, 1.4F);
	public static final RegistryObject<EntityType<BlackBearEntity>> 	BLACK_BEAR 		= HELPER.createLivingEntity("black_bear", BlackBearEntity::new, EntityClassification.CREATURE, 1.1F, 1.1F);
	public static final RegistryObject<EntityType<BumblebeeEntity>> 	BUMBLEBEE 		= HELPER.createLivingEntity("bumblebee", BumblebeeEntity::new, EntityClassification.CREATURE, 0.5F, 0.5F);

    @OnlyIn(Dist.CLIENT)
    public static void registerRendering()
    {
        RenderingRegistry.registerEntityRenderingHandler(HONEY_SLIME.get(), HoneySlimeRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(GRIZZLY_BEAR.get(), GrizzlyBearRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(BLACK_BEAR.get(), BlackBearRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(BUMBLEBEE.get(), BumblebeeRenderer::new);
    }
    
    public static void addEntitySpawns() {
 	   ForgeRegistries.BIOMES.getValues().stream().forEach(BBEntities::processSpawning);
    }
 	
    private static void processSpawning(Biome biome) {
    }
}
