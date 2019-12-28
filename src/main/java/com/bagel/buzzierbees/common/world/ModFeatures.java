package com.bagel.buzzierbees.common.world;

import com.bagel.buzzierbees.common.blocks.ModBlocks;

import net.minecraft.block.BlockState;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;

public class ModFeatures extends DefaultBiomeFeatures {
	private static final BlockState CARTWHEEL_STATE = ModBlocks.CARTWHEEL.getDefaultState();
	public static final BlockClusterFeatureConfig CARTWHEEL_FEATURE = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(CARTWHEEL_STATE), new SimpleBlockPlacer())).func_227315_a_(64).func_227322_d_();
}
