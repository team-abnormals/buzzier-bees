package com.bagel.buzzierbees.core.util;

import java.util.Arrays;

import com.bagel.buzzierbees.core.registry.BBBlocks;
import com.teamabnormals.abnormals_core.core.utils.DataUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.world.GrassColors;
import net.minecraft.world.biome.BiomeColors;

public class BlockColorManager {
	public static void registerBlockColors() {
		BlockColors blockColors = Minecraft.getInstance().getBlockColors();
        DataUtils.registerBlockColor(blockColors, (x, world, pos, u) -> world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.get(0.5D, 1.0D), Arrays.asList(
        		BBBlocks.POTTED_GRASS, BBBlocks.HANGING_POTTED_TALL_GRASS, BBBlocks.POTTED_LARGE_FERN, BBBlocks.HANGING_POTTED_FERN, 
        		BBBlocks.HANGING_POTTED_GRASS, BBBlocks.HANGING_POTTED_TALL_GRASS, BBBlocks.HANGING_POTTED_LARGE_FERN));
    }
}
