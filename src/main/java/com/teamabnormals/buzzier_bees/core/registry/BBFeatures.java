package com.teamabnormals.buzzier_bees.core.registry;

import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
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

public class BBFeatures {

	public static final class BBConfiguredFeatures {
		public static final ConfiguredFeature<RandomPatchConfiguration, ?> FLOWER_WHITE_CLOVER = register("flower_white_clover", Feature.FLOWER.configured(new RandomPatchConfiguration(32, 6, 2, () -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(BBBlocks.WHITE_CLOVER.get()))).onlyWhenEmpty())));
		public static final ConfiguredFeature<RandomPatchConfiguration, ?> FLOWER_PINK_CLOVER = register("flower_pink_clover", Feature.FLOWER.configured(new RandomPatchConfiguration(32, 6, 2, () -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(BBBlocks.PINK_CLOVER.get()))).onlyWhenEmpty())));
		public static final ConfiguredFeature<RandomPatchConfiguration, ?> FLOWER_BUTTERCUP = register("flower_buttercup", Feature.FLOWER.configured(new RandomPatchConfiguration(64, 6, 2, () -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(BBBlocks.BUTTERCUP.get()))).onlyWhenEmpty())));

		private static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
			return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(BuzzierBees.MOD_ID, name), configuredFeature);
		}
	}

	public static final class BBPlacedFeatures {
		public static final PlacedFeature FLOWER_WHITE_CLOVER = register("flower_white_clover", BBConfiguredFeatures.FLOWER_WHITE_CLOVER.placed(RarityFilter.onAverageOnceEvery(64), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
		public static final PlacedFeature FLOWER_PINK_CLOVER = register("flower_pink_clover", BBConfiguredFeatures.FLOWER_PINK_CLOVER.placed(RarityFilter.onAverageOnceEvery(64), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
		public static final PlacedFeature FLOWER_BUTTERCUP = register("flower_buttercup", BBConfiguredFeatures.FLOWER_BUTTERCUP.placed(RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

		public static PlacedFeature register(String name, PlacedFeature placedFeature) {
			return Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(BuzzierBees.MOD_ID, name), placedFeature);
		}
	}
}
