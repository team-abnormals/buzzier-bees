package com.teamabnormals.buzzier_bees.core;

import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import com.teamabnormals.buzzier_bees.core.data.server.BBAdvancementProvider;
import com.teamabnormals.buzzier_bees.core.data.server.tags.BBBlockTagsProvider;
import com.teamabnormals.buzzier_bees.core.data.server.tags.BBEntityTypeTagsProvider;
import com.teamabnormals.buzzier_bees.core.data.server.tags.BBItemTagsGenerator;
import com.teamabnormals.buzzier_bees.core.other.BBCompat;
import com.teamabnormals.buzzier_bees.core.registry.*;
import net.minecraft.data.DataGenerator;
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
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ModLoadingContext context = ModLoadingContext.get();

		REGISTRY_HELPER.register(modEventBus);
		BBPaintings.PAINTINGS.register(modEventBus);
		BBParticles.PARTICLES.register(modEventBus);
		BBEffects.POTIONS.register(modEventBus);
		BBEffects.EFFECTS.register(modEventBus);
		BBVillagers.PROFESSIONS.register(modEventBus);
		BBVillagers.POI_TYPES.register(modEventBus);
		MinecraftForge.EVENT_BUS.register(this);

		modEventBus.addListener(this::commonSetup);
		modEventBus.addListener(this::clientSetup);
		modEventBus.addListener(this::dataSetup);

		context.registerConfig(ModConfig.Type.COMMON, BBConfig.COMMON_SPEC);
	}

	private void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			BBCompat.registerCompat();
			BBEffects.registerRecipes();
			BBEntities.registerEntitySpawns();
			BBVillagers.registerVillagers();
			BBFeatures.Configured.registerConfiguredFeatures();
			BBFeatures.Placed.registerPlacedFeatures();
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
			dataGenerator.addProvider(new BBAdvancementProvider(dataGenerator));
		}
	}
}
