package com.bagel.buzzierbees.core.registry;

import com.bagel.buzzierbees.common.world.features.DirectionalFlowersFeature;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.DoublePlantBlockPlacer;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.DefaultFlowersFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MultipleWithChanceRandomFeatureConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BBFeatures {
	public static final DefaultFlowersFeature DEFAULT_FLOWERS_FEATURE = new DefaultFlowersFeature(BlockClusterFeatureConfig::deserialize);
	public static final DirectionalFlowersFeature DIRECTIONAL_FLOWERS_FEATURE = new DirectionalFlowersFeature(BlockClusterFeatureConfig::deserialize);
	
	private static void addShortFlower(BlockState blockState, Biome biomeIn, int frequency)
	{
		BlockClusterFeatureConfig config = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(blockState), new SimpleBlockPlacer())).tries(32).build();
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, DEFAULT_FLOWERS_FEATURE.withConfiguration(config).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(frequency))));
	}
	
	private static void addDirectionalFlower(BlockState blockState, Biome biomeIn, int frequency)
	{
		BlockClusterFeatureConfig config = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(blockState), new SimpleBlockPlacer())).tries(32).build();
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, DIRECTIONAL_FLOWERS_FEATURE.withConfiguration(config).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(frequency))));
	}
	
	public static void addDoubleFlower(BlockState blockState, Biome biomeIn, int frequency) {
		BlockClusterFeatureConfig config = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(blockState), new DoublePlantBlockPlacer())).tries(64).func_227317_b_().build();
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_RANDOM_SELECTOR.withConfiguration(new MultipleWithChanceRandomFeatureConfig(ImmutableList.of(Feature.RANDOM_PATCH.withConfiguration(config)), 0)).withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(frequency))));
	}
	
	public static void addFeatures() {
        ForgeRegistries.BIOMES.getValues().forEach(BBFeatures::generate);
    }

    public static void generate(Biome biome) {
        if (biome.getCategory() == Biome.Category.JUNGLE) {
			addDoubleFlower(BBBlocks.BIRD_OF_PARADISE.get().getDefaultState(), biome, 5);
			addShortFlower(BBBlocks.YELLOW_HIBISCUS.get().getDefaultState(), biome, 1);
			addShortFlower(BBBlocks.ORANGE_HIBISCUS.get().getDefaultState(), biome, 1);
			addShortFlower(BBBlocks.RED_HIBISCUS.get().getDefaultState(), biome, 1);
			addShortFlower(BBBlocks.PINK_HIBISCUS.get().getDefaultState(), biome, 1);
			addShortFlower(BBBlocks.MAGENTA_HIBISCUS.get().getDefaultState(), biome, 1);
			addShortFlower(BBBlocks.PURPLE_HIBISCUS.get().getDefaultState(), biome, 1);
        }
        if (biome.getCategory() == Biome.Category.SWAMP) {
        	addShortFlower(BBBlocks.DIANTHUS.get().getDefaultState(), biome,	5);
        }
        if (biome.getCategory() == Biome.Category.SAVANNA) {
        	addShortFlower(Blocks.ALLIUM.getDefaultState(), biome, 4);
        }
        if (biome.getCategory() == Biome.Category.TAIGA) {
        	addShortFlower(BBBlocks.VIOLET.get().getDefaultState(), biome, 4);
        }
        if (biome.getCategory() == Biome.Category.EXTREME_HILLS) {
        	addShortFlower(BBBlocks.COLUMBINE.get().getDefaultState(), biome, 4);
        }
        if (biome.getCategory() == Biome.Category.FOREST) {
            if (biome == Biomes.FLOWER_FOREST) {
            	addDirectionalFlower(BBBlocks.CARTWHEEL.get().getDefaultState(), biome, 5);
            	addShortFlower(BBBlocks.PINK_CLOVER.get().getDefaultState(), biome, 3);
            }
            if (biome == Biomes.DARK_FOREST || biome == Biomes.DARK_FOREST_HILLS) {
            	addShortFlower(BBBlocks.BLUEBELL.get().getDefaultState(), biome, 3);
            }
            if (biome == Biomes.BIRCH_FOREST || biome == Biomes.BIRCH_FOREST_HILLS || biome == Biomes.TALL_BIRCH_FOREST || biome == Biomes.TALL_BIRCH_HILLS) {
            	addShortFlower(BBBlocks.PINK_CLOVER.get().getDefaultState(), biome, 3);
            }
            else {
            	addShortFlower(BBBlocks.WHITE_CLOVER.get().getDefaultState(), biome, 3);
            }
        }
    }
	
}
