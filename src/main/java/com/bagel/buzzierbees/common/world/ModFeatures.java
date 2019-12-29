package com.bagel.buzzierbees.common.world;

import com.bagel.buzzierbees.common.blocks.ModBlocks;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.world.biome.Biome;
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
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModFeatures {
	private static final Logger LOGGER = LogManager.getLogger();

	/*private static BlockState CARTWHEEL_BLOCKSTATE;
	private static BlockState BLUEBELL_BLOCKSTATE;
	private static BlockState DAYBLOOM_BLOCKSTATE;
	private static BlockState VIOLET_BLOCKSTATE;
	private static BlockState JOLYCE_BLOCKSTATE;
	private static BlockState COLUMBINE_BLOCKSTATE;
	private static BlockState BIRD_OF_PARADISE_BLOCKSTATE;
	private static BlockState WHITE_CLOVER_BLOCKSTATE;
	private static BlockState PINK_CLOVER_BLOCKSTATE;

	private static BlockClusterFeatureConfig CARTWHEEL_CONFIG;
	private static BlockClusterFeatureConfig BLUEBELL_CONFIG;
	private static BlockClusterFeatureConfig DAYBLOOM_CONFIG;
	private static BlockClusterFeatureConfig VIOLET_CONFIG;
	private static BlockClusterFeatureConfig JOLYCE_CONFIG;
	private static BlockClusterFeatureConfig COLUMBINE_CONFIG;
	private static BlockClusterFeatureConfig BIRD_OF_PARADISE_CONFIG;
	private static BlockClusterFeatureConfig WHITE_CLOVER_CONFIG;
	private static BlockClusterFeatureConfig PINK_CLOVER_CONFIG;*/

	public static BlockClusterFeatureConfig field_226724_L_;

	public static DefaultFlowersFeature CARTWHEEL_FEATURE;
	public static DefaultFlowersFeature BLUEBELL_FEATURE;
	public static DefaultFlowersFeature VIOLET_FEATURE;
	public static DefaultFlowersFeature COLUMBINE_FEATURE;
	public static DefaultFlowersFeature JOLYCE_FEATURE;
	public static DefaultFlowersFeature DAYBLOOM_FEATURE;
	public static DefaultFlowersFeature BIRD_OF_PARADISE_FEATURE;

	public static Feature<?> CLOVER_FEATURE;

	@SubscribeEvent
	public static void registerFeatures(RegistryEvent.Register<Item> event)
	{
		/*CARTWHEEL_BLOCKSTATE = ModBlocks.CARTWHEEL.getDefaultState();
		BLUEBELL_BLOCKSTATE = ModBlocks.CARTWHEEL.getDefaultState();
		DAYBLOOM_BLOCKSTATE = ModBlocks.CARTWHEEL.getDefaultState();
		VIOLET_BLOCKSTATE = ModBlocks.CARTWHEEL.getDefaultState();
		JOLYCE_BLOCKSTATE = ModBlocks.CARTWHEEL.getDefaultState();
		COLUMBINE_BLOCKSTATE = ModBlocks.CARTWHEEL.getDefaultState();
		BIRD_OF_PARADISE_BLOCKSTATE = ModBlocks.CARTWHEEL.getDefaultState();

		CARTWHEEL_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(CARTWHEEL_BLOCKSTATE), new SimpleBlockPlacer())).func_227315_a_(32).func_227322_d_();
		BLUEBELL_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(CARTWHEEL_BLOCKSTATE), new SimpleBlockPlacer())).func_227315_a_(32).func_227322_d_();
		DAYBLOOM_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(CARTWHEEL_BLOCKSTATE), new SimpleBlockPlacer())).func_227315_a_(32).func_227322_d_();
		VIOLET_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(CARTWHEEL_BLOCKSTATE), new SimpleBlockPlacer())).func_227315_a_(32).func_227322_d_();

		LOGGER.info("NOT HERE");
		CARTWHEEL_FEATURE = registerFeature("cartwheel_feature", new DefaultFlowersFeature(BlockClusterFeatureConfig::func_227300_a_));
		BLUEBELL_FEATURE = registerFeature("bluebell_feature", new DefaultFlowersFeature(BlockClusterFeatureConfig::func_227300_a_));
		VIOLET_FEATURE = registerFeature("violet_feature", new DefaultFlowersFeature(BlockClusterFeatureConfig::func_227300_a_));
		COLUMBINE_FEATURE = registerFeature("columbine_feature", new DefaultFlowersFeature(BlockClusterFeatureConfig::func_227300_a_));
		JOLYCE_FEATURE = registerFeature("jolyce_feature", new DefaultFlowersFeature(BlockClusterFeatureConfig::func_227300_a_));
		DAYBLOOM_FEATURE = registerFeature("daybloom_feature", new DefaultFlowersFeature(BlockClusterFeatureConfig::func_227300_a_));
		BIRD_OF_PARADISE_FEATURE = registerFeature("bird_of_paradise_feature", new DefaultFlowersFeature(BlockClusterFeatureConfig::func_227300_a_));*/



		for (Biome biome : ForgeRegistries.BIOMES) {
			CARTWHEEL_FEATURE = registerFlowerFeature(ModBlocks.CARTWHEEL.getDefaultState(), "cartwheel_feature", biome);
			BLUEBELL_FEATURE = registerFlowerFeature(ModBlocks.BLUEBELL.getDefaultState(), "bluebell_feature", biome);
			VIOLET_FEATURE = registerFlowerFeature(ModBlocks.VIOLET.getDefaultState(), "violet_feature", biome);
			COLUMBINE_FEATURE = registerFlowerFeature(ModBlocks.COLUMBINE.getDefaultState(), "columbine_feature", biome);
			JOLYCE_FEATURE = registerFlowerFeature(ModBlocks.JOLYCE.getDefaultState(), "jolyce_feature", biome);
			DAYBLOOM_FEATURE = registerFlowerFeature(ModBlocks.DAYBLOOM.getDefaultState(), "daybloom_feature", biome);
			addDoubleFlowers(biome);
		}
	}

	private static DefaultFlowersFeature registerFlowerFeature(BlockState blockState, String name, Biome biomeIn)
	{
		BlockClusterFeatureConfig config = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(blockState), new SimpleBlockPlacer())).func_227315_a_(32).func_227322_d_();
		DefaultFlowersFeature feature = new DefaultFlowersFeature(BlockClusterFeatureConfig::func_227300_a_);
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, feature.func_225566_b_(config).func_227228_a_(Placement.COUNT_HEIGHTMAP_DOUBLE.func_227446_a_(new FrequencyConfig(5))));
		return feature;
	}

	public static void addDoubleFlowers(Biome biomeIn) {
		field_226724_L_ = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.BIRD_OF_PARADISE.getDefaultState()), new DoublePlantBlockPlacer())).func_227315_a_(64).func_227317_b_().func_227322_d_();
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_RANDOM_SELECTOR.func_225566_b_(new MultipleWithChanceRandomFeatureConfig(ImmutableList.of(Feature.field_227248_z_.func_225566_b_(field_226724_L_)), 0)).func_227228_a_(Placement.COUNT_HEIGHTMAP_32.func_227446_a_(new FrequencyConfig(5))));
	}

	private static <C extends IFeatureConfig, F extends Feature<C>> F registerFeature(String name, F feature) {
		feature.setRegistryName(name);
		ForgeRegistries.FEATURES.register(feature);
		return feature;
	}
}
