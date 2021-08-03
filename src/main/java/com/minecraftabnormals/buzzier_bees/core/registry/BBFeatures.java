package com.minecraftabnormals.buzzier_bees.core.registry;

import com.minecraftabnormals.abnormals_core.core.util.DataUtil;
import com.minecraftabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BuzzierBees.MOD_ID)
public class BBFeatures {

	@SubscribeEvent
	public static void addFeatures(BiomeLoadingEvent event) {
		ResourceLocation biome = event.getName();

		if (DataUtil.matchesKeys(biome, Biomes.FOREST, Biomes.WOODED_HILLS, Biomes.FLOWER_FOREST))
			event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.WHITE_CLOVER_PATCH);

		if (DataUtil.matchesKeys(biome, Biomes.FLOWER_FOREST, Biomes.BIRCH_FOREST, Biomes.BIRCH_FOREST_HILLS, Biomes.TALL_BIRCH_FOREST, Biomes.TALL_BIRCH_HILLS))
			event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.PINK_CLOVER_PATCH);

		if (DataUtil.matchesKeys(biome, Biomes.FLOWER_FOREST, Biomes.SUNFLOWER_PLAINS)) {
			event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.BUTTERCUP_PATCH);
			event.getSpawns().addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(BBEntities.MOOBLOOM.get(), 30, 2, 4));
		}
	}

	public static final class Configs {
		public static final BlockClusterFeatureConfig WHITE_CLOVER_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BBBlocks.WHITE_CLOVER.get().defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(32).build();
		public static final BlockClusterFeatureConfig PINK_CLOVER_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BBBlocks.PINK_CLOVER.get().defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(32).build();
		public static final BlockClusterFeatureConfig BUTTERCUP_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BBBlocks.BUTTERCUP.get().defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(32).build();
	}

	public static final class Configured {
		public static final ConfiguredFeature<?, ?> WHITE_CLOVER_PATCH = Feature.FLOWER.configured(Configs.WHITE_CLOVER_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(3);
		public static final ConfiguredFeature<?, ?> PINK_CLOVER_PATCH = Feature.FLOWER.configured(Configs.PINK_CLOVER_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(3);
		public static final ConfiguredFeature<?, ?> BUTTERCUP_PATCH = Feature.FLOWER.configured(Configs.BUTTERCUP_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(4);

		private static <FC extends IFeatureConfig> void register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
			Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(BuzzierBees.MOD_ID, name), configuredFeature);
		}

		public static void registerConfiguredFeatures() {
			register("white_clover_patch", WHITE_CLOVER_PATCH);
			register("pink_clover_patch", PINK_CLOVER_PATCH);
			register("buttercup_patch", BUTTERCUP_PATCH);
		}
	}
}
