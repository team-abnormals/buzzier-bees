package com.teamabnormals.buzzier_bees.core;

import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import com.teamabnormals.buzzier_bees.client.model.GrizzlyBearModel;
import com.teamabnormals.buzzier_bees.client.render.entity.GrizzlyBearRenderer;
import com.teamabnormals.buzzier_bees.client.render.entity.MoobloomRenderer;
import com.teamabnormals.buzzier_bees.core.data.server.*;
import com.teamabnormals.buzzier_bees.core.data.server.modifiers.BBAdvancementModifierProvider;
import com.teamabnormals.buzzier_bees.core.data.server.tags.*;
import com.teamabnormals.buzzier_bees.core.other.BBClientCompat;
import com.teamabnormals.buzzier_bees.core.other.BBCompat;
import com.teamabnormals.buzzier_bees.core.other.BBModelLayers;
import com.teamabnormals.buzzier_bees.core.registry.*;
import com.teamabnormals.gallery.core.data.client.GalleryItemModelProvider;
import net.minecraft.client.model.CowModel;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@Mod(BuzzierBees.MOD_ID)
public class BuzzierBees {
	public static final String MOD_ID = "buzzier_bees";
	public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MOD_ID);

	public BuzzierBees(IEventBus bus, ModContainer container) {
		REGISTRY_HELPER.register(bus);
		BBParticleTypes.PARTICLE_TYPES.register(bus);
		BBMobEffects.POTIONS.register(bus);
		BBMobEffects.MOB_EFFECTS.register(bus);
		BBDataComponents.COMPONENTS.register(bus);

		bus.addListener(this::commonSetup);
		bus.addListener(this::clientSetup);
		bus.addListener(this::dataSetup);

		if (FMLEnvironment.dist == Dist.CLIENT) {
			BBBlocks.setupTabEditors();
			BBItems.setupTabEditors();
			bus.addListener(this::registerLayerDefinitions);
			bus.addListener(this::registerRenderers);
		}

		container.registerConfig(ModConfig.Type.COMMON, BBConfig.COMMON_SPEC);
	}

	private void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			BBCompat.registerCompat();
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

		boolean server = event.includeServer();
		BBBlockTagsProvider blockTags = new BBBlockTagsProvider(output, provider, helper);
		generator.addProvider(server, blockTags);
		generator.addProvider(server, new BBItemTagsProvider(output, provider, blockTags.contentsGetter(), helper));
		generator.addProvider(server, new BBEntityTypeTagsProvider(output, provider, helper));
		generator.addProvider(server, new BBBiomeTagsProvider(output, provider, helper));
		generator.addProvider(server, BBAdvancementProvider.create(output, provider, helper));
		generator.addProvider(server, new BBRemolderProvider(output, provider));
		generator.addProvider(server, new BBAdvancementModifierProvider(output, provider));
		var datapack = new BBDatapackBuiltinEntriesProvider(output, provider);
		generator.addProvider(server, datapack);
		generator.addProvider(server, new BBDataMapProvider(output, provider));
		generator.addProvider(server, new BBBannerPatternTagsProvider(output, datapack.getRegistryProvider(), helper));
		generator.addProvider(server, new BBPaintingVariantTagsProvider(output, datapack.getRegistryProvider(), helper));
		generator.addProvider(server, new BBRecipeProvider(output, provider));

		boolean client = event.includeClient();
		generator.addProvider(client, new GalleryItemModelProvider(MOD_ID, output, helper, provider));
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

	public static ResourceLocation location(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}
}