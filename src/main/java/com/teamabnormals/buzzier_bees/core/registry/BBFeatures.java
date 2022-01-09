package com.teamabnormals.buzzier_bees.core.registry;

import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BuzzierBees.MOD_ID)
public class BBFeatures {

	@SubscribeEvent
	public static void addFeatures(BiomeLoadingEvent event) {
		ResourceLocation biome = event.getName();

		if (DataUtil.matchesKeys(biome, Biomes.FOREST, Biomes.FLOWER_FOREST))
			event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Placed.WHITE_CLOVER_PATCH);

		if (DataUtil.matchesKeys(biome, Biomes.FLOWER_FOREST, Biomes.BIRCH_FOREST, Biomes.OLD_GROWTH_BIRCH_FOREST))
			event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Placed.PINK_CLOVER_PATCH);

		if (DataUtil.matchesKeys(biome, Biomes.FLOWER_FOREST, Biomes.SUNFLOWER_PLAINS)) {
			event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Placed.BUTTERCUP_PATCH);
			event.getSpawns().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(BBEntities.MOOBLOOM.get(), 30, 2, 4));
		}
	}

	public static final class Configs {
		public static final RandomPatchConfiguration WHITE_CLOVER_CONFIG = FeatureUtils.simpleRandomPatchConfiguration(32, Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(BBBlocks.WHITE_CLOVER.get()))).onlyWhenEmpty());
		public static final RandomPatchConfiguration PINK_CLOVER_CONFIG = FeatureUtils.simpleRandomPatchConfiguration(32, Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(BBBlocks.PINK_CLOVER.get()))).onlyWhenEmpty());
		public static final RandomPatchConfiguration BUTTERCUP_CONFIG = FeatureUtils.simpleRandomPatchConfiguration(32, Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(BBBlocks.BUTTERCUP.get()))).onlyWhenEmpty());
	}

	public static final class Configured {
		public static final ConfiguredFeature<?, ?> WHITE_CLOVER_PATCH = Feature.FLOWER.configured(Configs.WHITE_CLOVER_CONFIG);
		public static final ConfiguredFeature<?, ?> PINK_CLOVER_PATCH = Feature.FLOWER.configured(Configs.PINK_CLOVER_CONFIG);
		public static final ConfiguredFeature<?, ?> BUTTERCUP_PATCH = Feature.FLOWER.configured(Configs.BUTTERCUP_CONFIG);

		private static <FC extends FeatureConfiguration> void register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
			Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(BuzzierBees.MOD_ID, name), configuredFeature);
		}

		public static void registerConfiguredFeatures() {
			register("white_clover_patch", WHITE_CLOVER_PATCH);
			register("pink_clover_patch", PINK_CLOVER_PATCH);
			register("buttercup_patch", BUTTERCUP_PATCH);
		}
	}

	public static final class Placed {
		public static final PlacedFeature WHITE_CLOVER_PATCH = Configured.WHITE_CLOVER_PATCH.placed(RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
		public static final PlacedFeature PINK_CLOVER_PATCH = Configured.PINK_CLOVER_PATCH.placed(RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
		public static final PlacedFeature BUTTERCUP_PATCH = Configured.BUTTERCUP_PATCH.placed(RarityFilter.onAverageOnceEvery(24), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

		public static void register(String name, PlacedFeature placedFeature) {
			Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(BuzzierBees.MOD_ID, name), placedFeature);
		}

		public static void registerPlacedFeatures() {
			register("white_clover_patch", WHITE_CLOVER_PATCH);
			register("pink_clover_patch", PINK_CLOVER_PATCH);
			register("buttercup_patch", BUTTERCUP_PATCH);
		}
	}
}
