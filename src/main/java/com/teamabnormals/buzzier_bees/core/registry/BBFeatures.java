package com.teamabnormals.buzzier_bees.core.registry;

import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.Features;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.blockplacers.SimpleBlockPlacer;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BuzzierBees.MOD_ID)
public class BBFeatures {

	@SubscribeEvent
	public static void addFeatures(BiomeLoadingEvent event) {
		ResourceLocation biome = event.getName();

		if (DataUtil.matchesKeys(biome, Biomes.FOREST, Biomes.WOODED_HILLS, Biomes.FLOWER_FOREST))
			event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Configured.WHITE_CLOVER_PATCH);

		if (DataUtil.matchesKeys(biome, Biomes.FLOWER_FOREST, Biomes.BIRCH_FOREST, Biomes.BIRCH_FOREST_HILLS, Biomes.TALL_BIRCH_FOREST, Biomes.TALL_BIRCH_HILLS))
			event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Configured.PINK_CLOVER_PATCH);

		if (DataUtil.matchesKeys(biome, Biomes.FLOWER_FOREST, Biomes.SUNFLOWER_PLAINS)) {
			event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Configured.BUTTERCUP_PATCH);
			event.getSpawns().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(BBEntities.MOOBLOOM.get(), 30, 2, 4));
		}
	}

	public static final class Configs {
		public static final RandomPatchConfiguration WHITE_CLOVER_CONFIG = (new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(BBBlocks.WHITE_CLOVER.get().defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(32).build();
		public static final RandomPatchConfiguration PINK_CLOVER_CONFIG = (new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(BBBlocks.PINK_CLOVER.get().defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(32).build();
		public static final RandomPatchConfiguration BUTTERCUP_CONFIG = (new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(BBBlocks.BUTTERCUP.get().defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(32).build();
	}

	public static final class Configured {
		public static final ConfiguredFeature<?, ?> WHITE_CLOVER_PATCH = Feature.FLOWER.configured(Configs.WHITE_CLOVER_CONFIG).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE).count(3);
		public static final ConfiguredFeature<?, ?> PINK_CLOVER_PATCH = Feature.FLOWER.configured(Configs.PINK_CLOVER_CONFIG).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE).count(3);
		public static final ConfiguredFeature<?, ?> BUTTERCUP_PATCH = Feature.FLOWER.configured(Configs.BUTTERCUP_CONFIG).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE).count(4);

		private static <FC extends FeatureConfiguration> void register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
			Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(BuzzierBees.MOD_ID, name), configuredFeature);
		}

		public static void registerConfiguredFeatures() {
			register("white_clover_patch", WHITE_CLOVER_PATCH);
			register("pink_clover_patch", PINK_CLOVER_PATCH);
			register("buttercup_patch", BUTTERCUP_PATCH);
		}
	}
}
