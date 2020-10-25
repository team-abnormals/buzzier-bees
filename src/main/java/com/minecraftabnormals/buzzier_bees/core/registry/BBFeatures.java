package com.minecraftabnormals.buzzier_bees.core.registry;

import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
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
	
	public static void registerFeatures() {
        ForgeRegistries.BIOMES.getValues().forEach(BBFeatures::generate);
    }

    public static void generate(Biome biome) {
        if (biome.getCategory() == Biome.Category.FOREST) {
        	if (biome == Biomes.FOREST || biome == Biomes.WOODED_HILLS || biome == Biomes.FLOWER_FOREST) 
            	addShortFlower(BBBlocks.WHITE_CLOVER.get().getDefaultState(), biome, 3);
            if (biome == Biomes.FLOWER_FOREST || biome == Biomes.BIRCH_FOREST || biome == Biomes.BIRCH_FOREST_HILLS || biome == Biomes.TALL_BIRCH_FOREST || biome == Biomes.TALL_BIRCH_HILLS)
            	addShortFlower(BBBlocks.PINK_CLOVER.get().getDefaultState(), biome, 3);
        }
    }
}
