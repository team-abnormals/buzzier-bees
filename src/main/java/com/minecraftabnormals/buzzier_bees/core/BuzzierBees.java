package com.minecraftabnormals.buzzier_bees.core;

import com.minecraftabnormals.buzzier_bees.core.other.BBCompat;
import com.minecraftabnormals.buzzier_bees.core.registry.*;
import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BuzzierBees.MODID)
@EventBusSubscriber(modid = BuzzierBees.MODID)
public class BuzzierBees {
	public static final String MODID = "buzzier_bees";
	public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MODID);

	public BuzzierBees() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		MinecraftForge.EVENT_BUS.register(this);

		REGISTRY_HELPER.getDeferredBlockRegister().register(modEventBus);
		REGISTRY_HELPER.getDeferredItemRegister().register(modEventBus);
		REGISTRY_HELPER.getDeferredEntityRegister().register(modEventBus);
		REGISTRY_HELPER.getDeferredTileEntityRegister().register(modEventBus);

		BBBanners.PAINTINGS.register(modEventBus);
		BBParticles.PARTICLES.register(modEventBus);
		BBEffects.POTIONS.register(modEventBus);
		BBEffects.EFFECTS.register(modEventBus);
		BBVillagers.PROFESSIONS.register(modEventBus);
		BBVillagers.POI_TYPES.register(modEventBus);

		modEventBus.addListener(this::setupCommon);
		DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
			modEventBus.addListener(this::setupClient);
			modEventBus.addListener(this::registerItemColors);
		});

		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, BBConfig.COMMON_SPEC);
	}

	private void setupCommon(final FMLCommonSetupEvent event) {
		DeferredWorkQueue.runLater(() -> {
			BBCompat.registerCompostables();
			BBCompat.registerFlammables();
			BBCompat.registerDispenserBehaviors();
			BBBanners.registerBanners();
			BBEffects.registerRecipes();
			BBFeatures.registerFeatures();
			BBEntities.registerEntitySpawns();
			BBEntities.registerAttributes();
			BBVillagers.registerVillagers();
		});
	}

	private void setupClient(final FMLClientSetupEvent event) {
		DeferredWorkQueue.runLater(() -> {
			BBEntities.registerRendering();
			BBCompat.setupRenderLayer();
			BBItems.setupItemProperties();
		});
	}

	@OnlyIn(Dist.CLIENT)
	private void registerItemColors(ColorHandlerEvent.Item event) {
		REGISTRY_HELPER.processSpawnEggColors(event);
	}
}
