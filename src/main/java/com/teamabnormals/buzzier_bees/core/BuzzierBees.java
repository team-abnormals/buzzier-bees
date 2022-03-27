package com.teamabnormals.buzzier_bees.core;

import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import com.teamabnormals.buzzier_bees.client.model.GrizzlyBearModel;
import com.teamabnormals.buzzier_bees.client.render.entity.GrizzlyBearRenderer;
import com.teamabnormals.buzzier_bees.client.render.entity.MoobloomRenderer;
import com.teamabnormals.buzzier_bees.core.data.server.BBAdvancementProvider;
import com.teamabnormals.buzzier_bees.core.data.server.modifiers.BBLootModifiersProvider;
import com.teamabnormals.buzzier_bees.core.data.server.tags.BBBlockTagsProvider;
import com.teamabnormals.buzzier_bees.core.data.server.tags.BBEntityTypeTagsProvider;
import com.teamabnormals.buzzier_bees.core.data.server.tags.BBItemTagsProvider;
import com.teamabnormals.buzzier_bees.core.other.BBClientCompat;
import com.teamabnormals.buzzier_bees.core.other.BBCompat;
import com.teamabnormals.buzzier_bees.core.registry.BBEntityTypes;
import com.teamabnormals.buzzier_bees.core.registry.BBMobEffects;
import com.teamabnormals.buzzier_bees.core.registry.BBPaintings;
import com.teamabnormals.buzzier_bees.core.registry.BBParticles;
import net.minecraft.client.model.CowModel;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod(BuzzierBees.MOD_ID)
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
		BBMobEffects.POTIONS.register(bus);
		BBMobEffects.MOB_EFFECTS.register(bus);

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
			BBCompat.register();
			BBMobEffects.registerRecipes();
			BBEntityTypes.registerEntitySpawns();
		});
	}

	private void clientSetup(FMLClientSetupEvent event) {
		event.enqueueWork(BBClientCompat::register);
	}

	private void dataSetup(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper fileHelpers = event.getExistingFileHelper();

		if (event.includeServer()) {
			BBBlockTagsProvider blockTags = new BBBlockTagsProvider(generator, fileHelpers);
			generator.addProvider(blockTags);
			generator.addProvider(new BBItemTagsProvider(generator, blockTags, fileHelpers));
			generator.addProvider(new BBEntityTypeTagsProvider(generator, fileHelpers));
			generator.addProvider(new BBAdvancementProvider(generator, fileHelpers));
			generator.addProvider(new BBLootModifiersProvider(generator));
		}
	}

	@OnlyIn(Dist.CLIENT)
	private void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(GrizzlyBearRenderer.MODEL_LAYER_LOCATION, GrizzlyBearModel::createLayerDefinition);
		event.registerLayerDefinition(MoobloomRenderer.MODEL_LAYER_LOCATION, CowModel::createBodyLayer);
	}

	@OnlyIn(Dist.CLIENT)
	private void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(BBEntityTypes.MOOBLOOM.get(), MoobloomRenderer::new);
		event.registerEntityRenderer(BBEntityTypes.GRIZZLY_BEAR.get(), GrizzlyBearRenderer::new);
	}
}
