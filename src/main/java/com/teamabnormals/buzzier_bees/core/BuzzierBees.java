package com.teamabnormals.buzzier_bees.core;

import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import com.teamabnormals.buzzier_bees.core.data.server.*;
import com.teamabnormals.buzzier_bees.core.data.server.tags.*;
import com.teamabnormals.buzzier_bees.core.other.BBClientCompat;
import com.teamabnormals.buzzier_bees.core.other.BBCompat;
import com.teamabnormals.buzzier_bees.core.registry.*;
import com.teamabnormals.gallery.core.data.client.GalleryItemModelProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@Mod(BuzzierBees.MOD_ID)
public class BuzzierBees {
	public static final String MOD_ID = "buzzier_bees";
	public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MOD_ID);

	public BuzzierBees(IEventBus bus, ModContainer container) {
		BBBlocks.BLOCKS.register(bus);
		BBItems.ITEMS.register(bus);
		BBEntityTypes.ENTITY_TYPES.register(bus);
		BBParticleTypes.PARTICLE_TYPES.register(bus);
		BBMobEffects.POTIONS.register(bus);
		BBMobEffects.MOB_EFFECTS.register(bus);
		BBDataComponents.COMPONENTS.register(bus);

		bus.addListener(this::commonSetup);
		bus.addListener(this::clientSetup);
		bus.addListener(this::dataSetup);

		container.registerConfig(ModConfig.Type.COMMON, BBConfig.COMMON_SPEC);
	}

	private void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(BBCompat::register);
	}

	private void clientSetup(FMLClientSetupEvent event) {
		event.enqueueWork(BBClientCompat::register);
	}

	private void dataSetup(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();
		CompletableFuture<Provider> provider = event.getLookupProvider();
		ExistingFileHelper helper = event.getExistingFileHelper();

		boolean server = event.includeServer();

		BBDatapackProvider datapack = new BBDatapackProvider(output, provider);
		generator.addProvider(server, datapack);
		provider = datapack.getRegistryProvider();

		BBBlockTagsProvider blockTags = new BBBlockTagsProvider(output, provider, helper);
		generator.addProvider(server, blockTags);
		generator.addProvider(server, new BBItemTagsProvider(output, provider, blockTags.contentsGetter(), helper));
		generator.addProvider(server, new BBEntityTypeTagsProvider(output, provider, helper));
		generator.addProvider(server, new BBBiomeTagsProvider(output, provider, helper));
		generator.addProvider(server, BBAdvancementProvider.create(output, provider, helper));
		generator.addProvider(server, new BBRemolderProvider(output, provider));
		generator.addProvider(server, new BBAdvancementModifierProvider(output, provider));
		generator.addProvider(server, new BBDataMapProvider(output, provider));
		generator.addProvider(server, new BBBannerPatternTagsProvider(output, provider, helper));
		generator.addProvider(server, new BBPaintingVariantTagsProvider(output, provider, helper));
		generator.addProvider(server, new BBRecipeProvider(output, provider));
		generator.addProvider(server, new BBLootTableProvider(output, provider));

		boolean client = event.includeClient();
		generator.addProvider(client, new GalleryItemModelProvider(MOD_ID, output, helper, provider));
	}

	public static ResourceLocation location(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}
}