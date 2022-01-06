package com.teamabnormals.buzzier_bees.core.registry;

import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BuzzierBees.MOD_ID)
public class BBFeatures {

	@SubscribeEvent
	public static void addFeatures(BiomeLoadingEvent event) {
		ResourceLocation biome = event.getName();

		if (DataUtil.matchesKeys(biome, Biomes.FOREST, Biomes.WINDSWEPT_FOREST))
			event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BBPlacedFeatures.FLOWER_WHITE_CLOVER);

		if (DataUtil.matchesKeys(biome, Biomes.BIRCH_FOREST, Biomes.OLD_GROWTH_BIRCH_FOREST))
			event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BBPlacedFeatures.FLOWER_PINK_CLOVER);

		if (DataUtil.matchesKeys(biome, Biomes.SUNFLOWER_PLAINS)) {
			event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BBPlacedFeatures.FLOWER_BUTTERCUP);
			event.getSpawns().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(BBEntities.MOOBLOOM.get(), 40, 2, 4));
		}
	}

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
