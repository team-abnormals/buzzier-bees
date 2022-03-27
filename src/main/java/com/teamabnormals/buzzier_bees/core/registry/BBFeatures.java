package com.teamabnormals.buzzier_bees.core.registry;

import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.core.Holder;
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
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

import java.util.List;

public class BBFeatures {

	public static final class BBConfiguredFeatures {
		public static final Holder<ConfiguredFeature<?, ?>> FLOWER_WHITE_CLOVER = register("flower_white_clover", Feature.FLOWER, new RandomPatchConfiguration(32, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BBBlocks.WHITE_CLOVER.get())))));
		public static final Holder<ConfiguredFeature<?, ?>> FLOWER_PINK_CLOVER = register("flower_pink_clover", Feature.FLOWER, new RandomPatchConfiguration(32, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BBBlocks.PINK_CLOVER.get())))));
		public static final Holder<ConfiguredFeature<?, ?>> FLOWER_BUTTERCUP = register("flower_buttercup", Feature.FLOWER, new RandomPatchConfiguration(64, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BBBlocks.BUTTERCUP.get())))));

		public static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<?, ?>> register(String name, F feature, FC config) {
			return BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(BuzzierBees.MOD_ID, name), new ConfiguredFeature<>(feature, config));
		}
	}

	public static final class BBPlacedFeatures {
		public static final Holder<PlacedFeature> FLOWER_WHITE_CLOVER = register("flower_white_clover", BBConfiguredFeatures.FLOWER_WHITE_CLOVER, RarityFilter.onAverageOnceEvery(64), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
		public static final Holder<PlacedFeature> FLOWER_PINK_CLOVER = register("flower_pink_clover", BBConfiguredFeatures.FLOWER_PINK_CLOVER, RarityFilter.onAverageOnceEvery(64), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
		public static final Holder<PlacedFeature> FLOWER_BUTTERCUP = register("flower_buttercup", BBConfiguredFeatures.FLOWER_BUTTERCUP, RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

		public static Holder<PlacedFeature> register(String name, Holder<? extends ConfiguredFeature<?, ?>> configuredFeature, PlacementModifier... placementModifiers) {
			return register(name, configuredFeature, List.of(placementModifiers));
		}

		public static Holder<PlacedFeature> register(String name, Holder<? extends ConfiguredFeature<?, ?>> configuredFeature, List<PlacementModifier> placementModifiers) {
			return BuiltinRegistries.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(BuzzierBees.MOD_ID, name), new PlacedFeature(Holder.hackyErase(configuredFeature), placementModifiers));
		}
	}
}
