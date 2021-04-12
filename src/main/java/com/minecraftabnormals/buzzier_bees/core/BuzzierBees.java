package com.minecraftabnormals.buzzier_bees.core;

import com.minecraftabnormals.abnormals_core.core.util.registry.RegistryHelper;
import com.minecraftabnormals.buzzier_bees.core.other.BBCompat;
import com.minecraftabnormals.buzzier_bees.core.registry.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BuzzierBees.MOD_ID)
@EventBusSubscriber(modid = BuzzierBees.MOD_ID)
public class BuzzierBees {
	public static final String MOD_ID = "buzzier_bees";
	public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MOD_ID);

	public BuzzierBees() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		MinecraftForge.EVENT_BUS.register(this);

		REGISTRY_HELPER.register(modEventBus);
		BBBanners.PAINTINGS.register(modEventBus);
		BBParticles.PARTICLES.register(modEventBus);
		BBEffects.POTIONS.register(modEventBus);
		BBEffects.EFFECTS.register(modEventBus);
		BBVillagers.PROFESSIONS.register(modEventBus);
		BBVillagers.POI_TYPES.register(modEventBus);

		modEventBus.addListener(this::setupCommon);
		DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
			modEventBus.addListener(this::setupClient);
		});

		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, BBConfig.COMMON_SPEC);
	}

	private void setupCommon(final FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			BBCompat.registerCompat();
			BBEffects.registerRecipes();
			BBEntities.registerEntitySpawns();
			BBVillagers.registerVillagers();
			BBFeatures.Configured.registerConfiguredFeatures();
		});
	}

	private void setupClient(final FMLClientSetupEvent event) {
		BBEntities.registerRendering();
		event.enqueueWork(() -> {
			BBCompat.setupRenderLayer();
			BBItems.registerItemProperties();
		});
	}
}
