package com.teamabnormals.buzzier_bees.core;

import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import com.teamabnormals.buzzier_bees.client.model.GrizzlyBearModel;
import com.teamabnormals.buzzier_bees.client.render.entity.GrizzlyBearRenderer;
import com.teamabnormals.buzzier_bees.client.render.entity.MoobloomRenderer;
import com.teamabnormals.buzzier_bees.core.data.server.BBAdvancementProvider;
import com.teamabnormals.buzzier_bees.core.data.server.BBDatapackBuiltinEntriesProvider;
import com.teamabnormals.buzzier_bees.core.data.server.modifiers.BBAdvancementModifierProvider;
import com.teamabnormals.buzzier_bees.core.data.server.modifiers.BBLootModifierProvider;
import com.teamabnormals.buzzier_bees.core.data.server.tags.*;
import com.teamabnormals.buzzier_bees.core.other.BBClientCompat;
import com.teamabnormals.buzzier_bees.core.other.BBCompat;
import com.teamabnormals.buzzier_bees.core.other.BBModelLayers;
import com.teamabnormals.buzzier_bees.core.registry.*;
import net.minecraft.client.model.CowModel;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
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

import java.util.concurrent.CompletableFuture;

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

		bus.addListener(this::commonSetup);
		bus.addListener(this::clientSetup);
		bus.addListener(this::dataSetup);

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			BBBlocks.setupTabEditors();
			BBItems.setupTabEditors();
			bus.addListener(this::registerLayerDefinitions);
			bus.addListener(this::registerRenderers);
		});

		context.registerConfig(ModConfig.Type.COMMON, BBConfig.COMMON_SPEC);
	}

	private void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			BBCompat.registerCompat();
			BBMobEffects.registerRecipes();
		});
	}

	private void clientSetup(FMLClientSetupEvent event) {
		event.enqueueWork(BBClientCompat::registerClientCompat);
	}

	private void dataSetup(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();
		CompletableFuture<Provider> provider = event.getLookupProvider();
		ExistingFileHelper helper = event.getExistingFileHelper();

		boolean includeServer = event.includeServer();
		BBBlockTagsProvider blockTags = new BBBlockTagsProvider(output, provider, helper);
		generator.addProvider(includeServer, blockTags);
		generator.addProvider(includeServer, new BBItemTagsProvider(output, provider, blockTags.contentsGetter(), helper));
		generator.addProvider(includeServer, new BBEntityTypeTagsProvider(output, provider, helper));
		generator.addProvider(includeServer, new BBBiomeTagsProvider(output, provider, helper));
		generator.addProvider(includeServer, new BBBannerPatternTagsProvider(output, provider, helper));
		generator.addProvider(includeServer, new BBPaintingVariantTagsProvider(output, provider, helper));
		generator.addProvider(includeServer, BBAdvancementProvider.create(output, provider, helper));
		generator.addProvider(includeServer, new BBLootModifierProvider(output, provider));
		generator.addProvider(includeServer, new BBAdvancementModifierProvider(output, provider));
		generator.addProvider(includeServer, new BBDatapackBuiltinEntriesProvider(output, provider));
	}

	@OnlyIn(Dist.CLIENT)
	private void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(BBModelLayers.GRIZZLY_BEAR, GrizzlyBearModel::createBodyLayer);
		event.registerLayerDefinition(BBModelLayers.MOOBLOOM, CowModel::createBodyLayer);
	}

	@OnlyIn(Dist.CLIENT)
	private void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(BBEntityTypes.MOOBLOOM.get(), MoobloomRenderer::new);
		event.registerEntityRenderer(BBEntityTypes.GRIZZLY_BEAR.get(), GrizzlyBearRenderer::new);
	}
}
