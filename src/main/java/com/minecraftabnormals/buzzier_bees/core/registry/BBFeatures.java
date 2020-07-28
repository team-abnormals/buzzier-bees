package com.minecraftabnormals.buzzier_bees.core.registry;

import com.google.common.collect.ImmutableList;

import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.DoublePlantBlockPlacer;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MultipleWithChanceRandomFeatureConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BBFeatures {
	
	private static void addShortFlower(BlockState blockState, Biome biomeIn, int frequency) {
		BlockClusterFeatureConfig config = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(blockState), SimpleBlockPlacer.field_236447_c_)).tries(32).build();
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.FLOWER.withConfiguration(config).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(frequency))));
	}
	
	public static void addDoubleFlower(BlockState blockState, Biome biomeIn, int frequency) {
		BlockClusterFeatureConfig config = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(blockState), DoublePlantBlockPlacer.field_236444_c_)).tries(64).func_227317_b_().build();
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_RANDOM_SELECTOR.withConfiguration(new MultipleWithChanceRandomFeatureConfig(ImmutableList.of(Feature.RANDOM_PATCH.withConfiguration(config)), 0)).withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(frequency))));
	}
	
	public static void addFeatures() {
        ForgeRegistries.BIOMES.getValues().forEach(BBFeatures::generate);
    }

    public static void generate(Biome biome) {
        if (biome.getCategory() == Biome.Category.FOREST) {
            if (biome == Biomes.FLOWER_FOREST) {
            	addShortFlower(BBBlocks.PINK_CLOVER.get().getDefaultState(), biome, 3);
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
