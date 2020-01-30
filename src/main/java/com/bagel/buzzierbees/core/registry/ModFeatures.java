package com.bagel.buzzierbees.core.registry;

import com.bagel.buzzierbees.common.blocks.CartwheelBlock;
import com.google.common.collect.ImmutableList;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
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
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModFeatures {
	public static final DefaultFlowersFeature DEFAULT_FLOWERS_FEATURE = new DefaultFlowersFeature(BlockClusterFeatureConfig::func_227300_a_);

	public static DefaultFlowersFeature PINK_CLOVER_FEATURE;
	public static DefaultFlowersFeature WHITE_CLOVER_FEATURE;

	@SubscribeEvent
	public static void registerFeatures(RegistryEvent.Register<Item> event)
	{
		registerFlowerFeature(ModBlocks.CARTWHEEL.get().getDefaultState().with(CartwheelBlock.FACING, Direction.NORTH), 		"cartwheel_feature", 	Biomes.FLOWER_FOREST,		5);
		registerFlowerFeature(ModBlocks.DAYBLOOM.get().getDefaultState(), 		"daybloom_feature", 		Biomes.SUNFLOWER_PLAINS,	4);
		registerFlowerFeature(ModBlocks.JOLYCE.get().getDefaultState(), 		"jolyce_feature", 		Biomes.SWAMP,				5);
		registerFlowerFeature(ModBlocks.WHITE_CLOVER.get().getDefaultState(),	"white_clover_feature", 	Biomes.FOREST,				3);
		
		//TODO: Proper Clover Patch generation
		for (Biome forests : new Biome[] { Biomes.BIRCH_FOREST, Biomes.BIRCH_FOREST_HILLS, Biomes.FLOWER_FOREST, Biomes.TALL_BIRCH_FOREST}) {
			registerFlowerFeature(ModBlocks.PINK_CLOVER.get().getDefaultState(), "pink_clover_feature", forests, 3);
		}
		
		for (Biome dark_forests : new Biome[] { Biomes.DARK_FOREST, Biomes.DARK_FOREST_HILLS, Biomes.FLOWER_FOREST}) {
			registerFlowerFeature(ModBlocks.BLUEBELL.get().getDefaultState(), "bluebell_feature", dark_forests, 3);
		}

		for (Biome mountains : new Biome[] { Biomes.MOUNTAINS, Biomes.GRAVELLY_MOUNTAINS, Biomes.MODIFIED_GRAVELLY_MOUNTAINS }) {
			registerFlowerFeature(ModBlocks.COLUMBINE.get().getDefaultState(), "columbine_feature", mountains, 4);
		}
		
		for (Biome taigas : new Biome[] { Biomes.GIANT_SPRUCE_TAIGA, Biomes.GIANT_SPRUCE_TAIGA_HILLS, Biomes.TAIGA, Biomes.FLOWER_FOREST, Biomes.TAIGA_MOUNTAINS}) {
			registerFlowerFeature(ModBlocks.VIOLET.get().getDefaultState(), "violet_feature", taigas, 4);
		}

		for (Biome savannah : new Biome[] { Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU, Biomes.SHATTERED_SAVANNA, Biomes.SHATTERED_SAVANNA_PLATEAU}) {
			registerFlowerFeature(Blocks.ALLIUM.getDefaultState(), "allium_feature", savannah, 4);
		}
		
		for (Biome jungles : new Biome[] { Biomes.JUNGLE, Biomes.MODIFIED_JUNGLE, Biomes.JUNGLE_HILLS }) {
			registerDoubleFlowersFeature(ModBlocks.BIRD_OF_PARADISE.get().getDefaultState(), "bird_of_paradise_feature", jungles, 5);
		}		
	}
	
	private static void registerFlowerFeature(BlockState blockState, String name, Biome biomeIn, int frequency)
	{
		BlockClusterFeatureConfig config = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(blockState), new SimpleBlockPlacer())).func_227315_a_(32).func_227322_d_();
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, DEFAULT_FLOWERS_FEATURE.withConfiguration(config).func_227228_a_(Placement.COUNT_HEIGHTMAP_DOUBLE.func_227446_a_(new FrequencyConfig(frequency))));
	}
	
	public static void registerDoubleFlowersFeature(BlockState blockState, String name, Biome biomeIn, int frequency) {
		BlockClusterFeatureConfig config = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(blockState), new DoublePlantBlockPlacer())).func_227315_a_(64).func_227317_b_().func_227322_d_();
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_RANDOM_SELECTOR.withConfiguration(new MultipleWithChanceRandomFeatureConfig(ImmutableList.of(Feature.RANDOM_PATCH.withConfiguration(config)), 0)).func_227228_a_(Placement.COUNT_HEIGHTMAP_32.func_227446_a_(new FrequencyConfig(frequency))));
	}
}
