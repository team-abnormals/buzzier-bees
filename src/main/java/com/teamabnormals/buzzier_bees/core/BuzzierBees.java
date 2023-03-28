package com.teamabnormals.buzzier_bees.core;

import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import com.teamabnormals.buzzier_bees.client.model.GrizzlyBearModel;
import com.teamabnormals.buzzier_bees.client.render.entity.GrizzlyBearRenderer;
import com.teamabnormals.buzzier_bees.client.render.entity.MoobloomRenderer;
import com.teamabnormals.buzzier_bees.core.data.server.BBAdvancementProvider;
import com.teamabnormals.buzzier_bees.core.data.server.modifiers.BBAdvancementModifierProvider;
import com.teamabnormals.buzzier_bees.core.data.server.modifiers.BBBiomeModifierProvider;
import com.teamabnormals.buzzier_bees.core.data.server.modifiers.BBLootModifierProvider;
import com.teamabnormals.buzzier_bees.core.data.server.tags.*;
import com.teamabnormals.buzzier_bees.core.other.BBClientCompat;
import com.teamabnormals.buzzier_bees.core.other.BBCompat;
import com.teamabnormals.buzzier_bees.core.other.BBModelLayers;
import com.teamabnormals.buzzier_bees.core.registry.*;
import com.teamabnormals.buzzier_bees.core.registry.BBFeatures.BBConfiguredFeatures;
import com.teamabnormals.buzzier_bees.core.registry.BBFeatures.BBPlacedFeatures;
import net.minecraft.client.model.CowModel;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BuzzierBees.MOD_ID)
public class BuzzierBees {
	public static final String MOD_ID = "buzzier_bees";
	public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MOD_ID);

	public BuzzierBees() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		ModLoadingContext context = ModLoadingContext.get();
		MinecraftForge.EVENT_BUS.register(this);

		REGISTRY_HELPER.register(bus);
		BBPaintingVariants.PAINTING_VARIANTS.register(bus);
		BBParticleTypes.PARTICLE_TYPES.register(bus);
		BBMobEffects.POTIONS.register(bus);
		BBMobEffects.MOB_EFFECTS.register(bus);
		BBBannerPatterns.BANNER_PATTERNS.register(bus);
		BBPlacedFeatures.PLACED_FEATURES.register(bus);
		BBConfiguredFeatures.CONFIGURED_FEATURES.register(bus);

		bus.addListener(this::commonSetup);
		bus.addListener(this::clientSetup);
		bus.addListener(this::dataSetup);

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			bus.addListener(this::registerLayerDefinitions);
			bus.addListener(this::registerRenderers);
		});

		context.registerConfig(ModConfig.Type.COMMON, BBConfig.COMMON_SPEC);
	}

	private void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			BBCompat.registerCompat();
			BBMobEffects.registerRecipes();
			BBEntityTypes.registerEntitySpawns();
		});
	}

	private void clientSetup(FMLClientSetupEvent event) {
		event.enqueueWork(BBClientCompat::registerClientCompat);
	}

	private void dataSetup(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

		boolean includeServer = event.includeServer();
		BBBlockTagsProvider blockTags = new BBBlockTagsProvider(generator, existingFileHelper);
		generator.addProvider(includeServer, blockTags);
		generator.addProvider(includeServer, new BBItemTagsProvider(generator, blockTags, existingFileHelper));
		generator.addProvider(includeServer, new BBEntityTypeTagsProvider(generator, existingFileHelper));
		generator.addProvider(includeServer, new BBBiomeTagsProvider(generator, existingFileHelper));
		generator.addProvider(includeServer, new BBBannerPatternTagsProvider(generator, existingFileHelper));
		generator.addProvider(includeServer, new BBPaintingVariantTagsProvider(generator, existingFileHelper));
		generator.addProvider(includeServer, new BBAdvancementProvider(generator, existingFileHelper));
		generator.addProvider(includeServer, new BBLootModifierProvider(generator));
		generator.addProvider(includeServer, new BBAdvancementModifierProvider(generator));
		generator.addProvider(includeServer, BBBiomeModifierProvider.create(generator, existingFileHelper));
	}

	@OnlyIn(Dist.CLIENT)
	private void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(BBModelLayers.GRIZZLY_BEAR, GrizzlyBearModel::createLayerDefinition);
		event.registerLayerDefinition(BBModelLayers.MOOBLOOM, CowModel::createBodyLayer);
	}

	@OnlyIn(Dist.CLIENT)
	private void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(BBEntityTypes.MOOBLOOM.get(), MoobloomRenderer::new);
		event.registerEntityRenderer(BBEntityTypes.GRIZZLY_BEAR.get(), GrizzlyBearRenderer::new);
	}
}
