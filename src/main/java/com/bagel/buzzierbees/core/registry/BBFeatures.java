package com.bagel.buzzierbees.core.registry;

import java.util.Random;

import com.bagel.buzzierbees.common.blocks.CartwheelBlock;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
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
	public static final DefaultFlowersFeature DEFAULT_FLOWERS_FEATURE = new DefaultFlowersFeature(BlockClusterFeatureConfig::func_227300_a_);
	
	private static void addShortFlower(BlockState blockState, Biome biomeIn, int frequency)
	{
		BlockClusterFeatureConfig config = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(blockState), new SimpleBlockPlacer())).tries(32).build();
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, DEFAULT_FLOWERS_FEATURE.withConfiguration(config).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(frequency))));
	}
	
	public static void addDoubleFlower(BlockState blockState, Biome biomeIn, int frequency) {
		BlockClusterFeatureConfig config = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(blockState), new DoublePlantBlockPlacer())).tries(64).func_227317_b_().build();
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_RANDOM_SELECTOR.withConfiguration(new MultipleWithChanceRandomFeatureConfig(ImmutableList.of(Feature.RANDOM_PATCH.withConfiguration(config)), 0)).withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(frequency))));
	}
	
	public static void addFeatures() {
        ForgeRegistries.BIOMES.getValues().forEach(BBFeatures::generate);
    }

    public static void generate(Biome biome) {
    	Random rand = new Random();
        if (biome.getCategory() == Biome.Category.JUNGLE) {
			addDoubleFlower(BBBlocks.BIRD_OF_PARADISE.get().getDefaultState(), biome, 5);
        }
        else if (biome.getCategory() == Biome.Category.SWAMP) {
        	addShortFlower(BBBlocks.JOLYCE.get().getDefaultState(), biome,	5);
        }
        else if (biome.getCategory() == Biome.Category.SAVANNA) {
        	addShortFlower(Blocks.ALLIUM.getDefaultState(), biome, 4);
        }
        else if (biome.getCategory() == Biome.Category.TAIGA) {
        	addShortFlower(BBBlocks.VIOLET.get().getDefaultState(), biome, 4);
        }
        else if (biome.getCategory() == Biome.Category.EXTREME_HILLS) {
        	addShortFlower(BBBlocks.COLUMBINE.get().getDefaultState(), biome, 4);
        }
        else if (biome.getCategory() == Biome.Category.PLAINS) {
        	if (biome == Biomes.SUNFLOWER_PLAINS) {
        		addShortFlower(BBBlocks.DAYBLOOM.get().getDefaultState(), biome, 4);
        	}
        }
        else if (biome.getCategory() == Biome.Category.FOREST) {
            if (biome == Biomes.FLOWER_FOREST) {
            	addShortFlower(BBBlocks.CARTWHEEL.get().getDefaultState().with(CartwheelBlock.FACING, Direction.Plane.HORIZONTAL.random(rand)), biome, 5);
            	addShortFlower(BBBlocks.PINK_CLOVER.get().getDefaultState(), biome, 3);
            }
            else if (biome == Biomes.DARK_FOREST || biome == Biomes.DARK_FOREST_HILLS) {
            	addShortFlower(BBBlocks.BLUEBELL.get().getDefaultState(), biome, 3);
            }
            else if (biome == Biomes.BIRCH_FOREST || biome == Biomes.BIRCH_FOREST_HILLS || biome == Biomes.TALL_BIRCH_FOREST || biome == Biomes.TALL_BIRCH_HILLS) {
            	addShortFlower(BBBlocks.PINK_CLOVER.get().getDefaultState(), biome, 3);
            }
            else {
            	addShortFlower(BBBlocks.WHITE_CLOVER.get().getDefaultState(), biome, 3);
            }
        }
    }
	
}
