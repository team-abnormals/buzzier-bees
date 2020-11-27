package com.minecraftabnormals.buzzier_bees.core.registry;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class BBFeatures {
	public static final BlockClusterFeatureConfig WHITE_CLOVER_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BBBlocks.WHITE_CLOVER.get().getDefaultState()), SimpleBlockPlacer.field_236447_c_)).tries(32).build();
	public static final BlockClusterFeatureConfig PINK_CLOVER_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BBBlocks.PINK_CLOVER.get().getDefaultState()), SimpleBlockPlacer.field_236447_c_)).tries(32).build();
	public static final BlockClusterFeatureConfig BUTTERCUP_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BBBlocks.BUTTERCUP.get().getDefaultState()), SimpleBlockPlacer.field_236447_c_)).tries(32).build();

	private static void addShortFlower(BlockClusterFeatureConfig config, Biome biomeIn, int frequency) {
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.FLOWER.withConfiguration(config).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(frequency))));
	}

	public static void registerFeatures() {
		ForgeRegistries.BIOMES.getValues().forEach(BBFeatures::generate);
	}

	public static void generate(Biome biome) {
		if (biome == Biomes.FOREST || biome == Biomes.WOODED_HILLS || biome == Biomes.FLOWER_FOREST)
			addShortFlower(WHITE_CLOVER_CONFIG, biome, 3);
		if (biome == Biomes.FLOWER_FOREST || biome == Biomes.BIRCH_FOREST || biome == Biomes.BIRCH_FOREST_HILLS || biome == Biomes.TALL_BIRCH_FOREST || biome == Biomes.TALL_BIRCH_HILLS)
			addShortFlower(PINK_CLOVER_CONFIG, biome, 3);
		if (biome == Biomes.FLOWER_FOREST || biome == Biomes.SUNFLOWER_PLAINS)
			addShortFlower(BUTTERCUP_CONFIG, biome, 4);
	}
}
