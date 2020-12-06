package com.minecraftabnormals.buzzier_bees.core.registry;

import com.minecraftabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = BuzzierBees.MOD_ID)
public class BBFeatures {

	@SubscribeEvent
	public static void addFeatures(BiomeLoadingEvent event) {
		ResourceLocation biome = event.getName();

		if (isBiome(biome, Biomes.FOREST, Biomes.WOODED_HILLS, Biomes.FLOWER_FOREST))
			event.getGeneration().withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configs.WHITE_CLOVER_PATCH);

		if (isBiome(biome, Biomes.FLOWER_FOREST, Biomes.BIRCH_FOREST, Biomes.BIRCH_FOREST_HILLS, Biomes.TALL_BIRCH_FOREST, Biomes.TALL_BIRCH_HILLS))
			event.getGeneration().withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configs.PINK_CLOVER_PATCH);

		if (isBiome(biome, Biomes.FLOWER_FOREST, Biomes.SUNFLOWER_PLAINS)) {
			event.getGeneration().withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configs.BUTTERCUP_PATCH);
			event.getSpawns().withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(BBEntities.MOOBLOOM.get(), 30, 2, 4));
		}
	}

	public static boolean isBiome(ResourceLocation biome, RegistryKey<Biome>... biomes) {
		for (RegistryKey<Biome> key : biomes) if (key.getLocation().equals(biome)) return true;
		return false;
	}

	static class Configs {
		public static final BlockClusterFeatureConfig WHITE_CLOVER_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BBBlocks.WHITE_CLOVER.get().getDefaultState()), SimpleBlockPlacer.PLACER)).tries(32).build();
		public static final BlockClusterFeatureConfig PINK_CLOVER_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BBBlocks.PINK_CLOVER.get().getDefaultState()), SimpleBlockPlacer.PLACER)).tries(32).build();
		public static final BlockClusterFeatureConfig BUTTERCUP_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BBBlocks.BUTTERCUP.get().getDefaultState()), SimpleBlockPlacer.PLACER)).tries(32).build();

		public static final ConfiguredFeature<?, ?> WHITE_CLOVER_PATCH = register("pink_clover_patch", Feature.FLOWER.withConfiguration(WHITE_CLOVER_CONFIG)).withPlacement(Features.Placements.PATCH_PLACEMENT).func_242731_b(3);
		public static final ConfiguredFeature<?, ?> PINK_CLOVER_PATCH = register("white_clover_patch", Feature.FLOWER.withConfiguration(PINK_CLOVER_CONFIG)).withPlacement(Features.Placements.PATCH_PLACEMENT).func_242731_b(3);
		public static final ConfiguredFeature<?, ?> BUTTERCUP_PATCH = register("buttercup_patch", Feature.FLOWER.withConfiguration(BUTTERCUP_CONFIG)).withPlacement(Features.Placements.PATCH_PLACEMENT).func_242731_b(4);

		private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
			return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(BuzzierBees.MOD_ID, name), configuredFeature);
		}
	}
}
