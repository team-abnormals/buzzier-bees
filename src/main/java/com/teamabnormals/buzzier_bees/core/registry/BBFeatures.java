package com.teamabnormals.buzzier_bees.core.registry;

import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;
import java.util.function.Supplier;

public class BBFeatures {

	public static final class BBConfiguredFeatures {
		public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_WHITE_CLOVER = createKey("flower_white_clover");
		public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_PINK_CLOVER = createKey("flower_pink_clover");
		public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_BUTTERCUP = createKey("flower_buttercup");

		public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
			register(context, FLOWER_WHITE_CLOVER, () -> new ConfiguredFeature<>(Feature.FLOWER, new RandomPatchConfiguration(32, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BBBlocks.WHITE_CLOVER.get()))))));
			register(context, FLOWER_PINK_CLOVER, () -> new ConfiguredFeature<>(Feature.FLOWER, new RandomPatchConfiguration(32, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BBBlocks.PINK_CLOVER.get()))))));
			register(context, FLOWER_BUTTERCUP, () -> new ConfiguredFeature<>(Feature.FLOWER, new RandomPatchConfiguration(64, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BBBlocks.BUTTERCUP.get()))))));
		}

		public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
			return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(BuzzierBees.MOD_ID, name));
		}

		public static void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, Supplier<? extends ConfiguredFeature<?, ?>> configuredFeature) {
			context.register(key, configuredFeature.get());
		}
	}

	public static final class BBPlacedFeatures {
		public static final ResourceKey<PlacedFeature> FLOWER_WHITE_CLOVER = createKey("flower_white_clover");
		public static final ResourceKey<PlacedFeature> FLOWER_PINK_CLOVER = createKey("flower_pink_clover");
		public static final ResourceKey<PlacedFeature> FLOWER_BUTTERCUP = createKey("flower_buttercup");

		public static void bootstrap(BootstapContext<PlacedFeature> context) {
			register(context, FLOWER_WHITE_CLOVER, BBConfiguredFeatures.FLOWER_WHITE_CLOVER, RarityFilter.onAverageOnceEvery(64), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
			register(context, FLOWER_PINK_CLOVER, BBConfiguredFeatures.FLOWER_PINK_CLOVER, RarityFilter.onAverageOnceEvery(64), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
			register(context, FLOWER_BUTTERCUP, BBConfiguredFeatures.FLOWER_BUTTERCUP, RarityFilter.onAverageOnceEvery(16), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
		}

		public static ResourceKey<PlacedFeature> createKey(String name) {
			return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(BuzzierBees.MOD_ID, name));
		}

		public static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureHolder, PlacementModifier... modifiers) {
			context.register(key, new PlacedFeature(context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(configuredFeatureHolder), List.of(modifiers)));
		}
	}
}
