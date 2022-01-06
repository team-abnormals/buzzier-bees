package com.teamabnormals.buzzier_bees.core;

import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import com.teamabnormals.buzzier_bees.client.model.GrizzlyBearModel;
import com.teamabnormals.buzzier_bees.client.render.GrizzlyBearRenderer;
import com.teamabnormals.buzzier_bees.client.render.MoobloomRenderer;
import com.teamabnormals.buzzier_bees.core.data.server.BBAdvancementProvider;
import com.teamabnormals.buzzier_bees.core.data.server.BBLootModifiersProvider;
import com.teamabnormals.buzzier_bees.core.data.server.tags.*;
import com.teamabnormals.buzzier_bees.core.other.BBCompat;
import com.teamabnormals.buzzier_bees.core.registry.*;
import net.minecraft.client.model.CowModel;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod(BuzzierBees.MOD_ID)
@EventBusSubscriber(modid = BuzzierBees.MOD_ID)
public class BuzzierBees {
	public static final String MOD_ID = "buzzier_bees";
	public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MOD_ID);

	public BuzzierBees() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		ModLoadingContext context = ModLoadingContext.get();
		MinecraftForge.EVENT_BUS.register(this);

		REGISTRY_HELPER.register(bus);
		BBPaintings.PAINTINGS.register(bus);
		BBParticles.PARTICLES.register(bus);
		BBEffects.POTIONS.register(bus);
		BBEffects.MOB_EFFECTS.register(bus);

		bus.addListener(this::commonSetup);
		bus.addListener(this::clientSetup);
		bus.addListener(this::dataSetup);

		bus.addListener(this::registerLayerDefinitions);
		bus.addListener(this::registerRenderers);

		context.registerConfig(ModConfig.Type.COMMON, BBConfig.COMMON_SPEC);
	}

	private void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			BBCompat.registerCompat();
			BBEffects.registerRecipes();
			BBEntities.registerEntitySpawns();
		});
	}

	private void clientSetup(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			BBCompat.registerRenderLayers();
			BBItems.registerItemProperties();
		});
	}

	private void dataSetup(GatherDataEvent event) {
		DataGenerator dataGenerator = event.getGenerator();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

		if (event.includeServer()) {
			BBBlockTagsProvider blockTagGen = new BBBlockTagsProvider(dataGenerator, existingFileHelper);
			dataGenerator.addProvider(blockTagGen);
			dataGenerator.addProvider(new BBItemTagsGenerator(dataGenerator, blockTagGen, existingFileHelper));
			dataGenerator.addProvider(new BBEntityTypeTagsProvider(dataGenerator, existingFileHelper));
			dataGenerator.addProvider(new BBAdvancementProvider(dataGenerator, existingFileHelper));
			dataGenerator.addProvider(BBLootModifiersProvider.createProvider(dataGenerator));
		}
	}

	private void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(GrizzlyBearRenderer.MODEL_LAYER_LOCATION, GrizzlyBearModel::createLayerDefinition);
		event.registerLayerDefinition(MoobloomRenderer.MODEL_LAYER_LOCATION, CowModel::createBodyLayer);
	}

	private void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(BBEntities.MOOBLOOM.get(), MoobloomRenderer::new);
		event.registerEntityRenderer(BBEntities.GRIZZLY_BEAR.get(), GrizzlyBearRenderer::new);
	}
}
