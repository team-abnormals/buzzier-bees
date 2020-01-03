package com.bagel.buzzierbees.common.world;

import com.bagel.buzzierbees.common.blocks.ModBlocks;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.DoublePlantBlockPlacer;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModFeatures {
	public static DefaultFlowersFeature CARTWHEEL_FEATURE;
	public static DefaultFlowersFeature BLUEBELL_FEATURE;
	public static DefaultFlowersFeature VIOLET_FEATURE;
	public static DefaultFlowersFeature COLUMBINE_FEATURE;
	public static DefaultFlowersFeature JOLYCE_FEATURE;
	public static DefaultFlowersFeature DAYBLOOM_FEATURE;
	public static MultipleWithChanceRandomFeature BIRD_OF_PARADISE_FEATURE;
	public static DefaultFlowersFeature PINK_CLOVER_FEATURE;
	public static DefaultFlowersFeature WHITE_CLOVER_FEATURE;

	@SubscribeEvent
	public static void registerFeatures(RegistryEvent.Register<Item> event)
	{
		CARTWHEEL_FEATURE = registerFlowerFeature(ModBlocks.CARTWHEEL.getDefaultState(), "cartwheel_feature", Biomes.FLOWER_FOREST, 2);
		COLUMBINE_FEATURE = registerFlowerFeature(ModBlocks.COLUMBINE.getDefaultState(), "columbine_feature", Biomes.FLOWER_FOREST, 4);
		DAYBLOOM_FEATURE = registerFlowerFeature(ModBlocks.DAYBLOOM.getDefaultState(), "daybloom_feature", Biomes.SUNFLOWER_PLAINS, 4);
		
		PINK_CLOVER_FEATURE = registerFlowerFeature(ModBlocks.PINK_CLOVER.getDefaultState(), "pink_clover_feature", Biomes.PLAINS, 1);
		WHITE_CLOVER_FEATURE = registerFlowerFeature(ModBlocks.WHITE_CLOVER.getDefaultState(), "white_clover_feature", Biomes.PLAINS, 1);

		for (Biome biome : new Biome[] { Biomes.PLAINS, Biomes.SUNFLOWER_PLAINS, Biomes.FLOWER_FOREST }) {
			BLUEBELL_FEATURE = registerFlowerFeature(ModBlocks.BLUEBELL.getDefaultState(), "bluebell_feature", biome, 2);
			VIOLET_FEATURE = registerFlowerFeature(ModBlocks.VIOLET.getDefaultState(), "violet_feature", biome, 4);
			JOLYCE_FEATURE = registerFlowerFeature(ModBlocks.JOLYCE.getDefaultState(), "jolyce_feature", biome, 1);
		}

		registerDoubleFlowersFeature(Biomes.JUNGLE);
	}

	private static DefaultFlowersFeature registerFlowerFeature(BlockState blockState, String name, Biome biomeIn, int frequency)
	{
		BlockClusterFeatureConfig config = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(blockState), new SimpleBlockPlacer())).func_227315_a_(32).func_227322_d_();
		DefaultFlowersFeature feature = new DefaultFlowersFeature(BlockClusterFeatureConfig::func_227300_a_);
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, feature.func_225566_b_(config).func_227228_a_(Placement.COUNT_HEIGHTMAP_DOUBLE.func_227446_a_(new FrequencyConfig(frequency))));
		return feature;
	}
	
	public static void registerDoubleFlowersFeature(Biome biomeIn) {
		BlockClusterFeatureConfig config = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.BIRD_OF_PARADISE.getDefaultState()), new DoublePlantBlockPlacer())).func_227315_a_(64).func_227317_b_().func_227322_d_();
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_RANDOM_SELECTOR.func_225566_b_(new MultipleWithChanceRandomFeatureConfig(ImmutableList.of(Feature.field_227248_z_.func_225566_b_(config)), 0)).func_227228_a_(Placement.COUNT_HEIGHTMAP_32.func_227446_a_(new FrequencyConfig(1))));
	}

	public static void addClover(BlockState blockState, String name, Biome biomeIn) {
		BlockClusterFeatureConfig config = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(blockState), new DoublePlantBlockPlacer())).func_227315_a_(64).func_227317_b_().func_227322_d_();
		DefaultFlowersFeature feature = new DefaultFlowersFeature(BlockClusterFeatureConfig::func_227300_a_);
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, feature.func_225566_b_(config).func_227228_a_(Placement.COUNT_HEIGHTMAP_DOUBLE.func_227446_a_(new FrequencyConfig(1))));
	}
}
