package com.bagel.buzzierbees.core.util;

import com.bagel.buzzierbees.core.registry.BBBlocks;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.world.GrassColors;
import net.minecraft.world.biome.BiomeColors;

public class BlockColorManager {
	public static void registerBlockColors() {
		BlockColors blockColors = Minecraft.getInstance().getBlockColors();
		blockColors.register((x, world, pos, u) -> world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.get(0.5D, 1.0D), BBBlocks.POTTED_GRASS.get());
        blockColors.register((x, world, pos, u) -> world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.get(0.5D, 1.0D), BBBlocks.POTTED_TALL_GRASS.get());
        blockColors.register((x, world, pos, u) -> world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.get(0.5D, 1.0D), BBBlocks.POTTED_LARGE_FERN.get());
        blockColors.register((x, world, pos, u) -> world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.get(0.5D, 1.0D), BBBlocks.HANGING_POTTED_FERN.get());
        blockColors.register((x, world, pos, u) -> world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.get(0.5D, 1.0D), BBBlocks.HANGING_POTTED_GRASS.get());
        blockColors.register((x, world, pos, u) -> world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.get(0.5D, 1.0D), BBBlocks.HANGING_POTTED_TALL_GRASS.get());
        blockColors.register((x, world, pos, u) -> world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.get(0.5D, 1.0D), BBBlocks.HANGING_POTTED_LARGE_FERN.get());
        
//        ItemColors itemColors = Minecraft.getInstance().getItemColors();
//        itemColors.register((color, items) -> FoliageColors.get(0.5D, 1.0D), AtmosphericBlocks.ROSEWOOD_LEAVES.get());
//        itemColors.register((color, items) -> FoliageColors.get(0.5D, 1.0D), AtmosphericBlocks.YUCCA_LEAVES.get());
//        itemColors.register((color, items) -> FoliageColors.get(0.5D, 1.0D), AtmosphericBlocks.ROSEWOOD_LEAF_CARPET.get());
//        itemColors.register((color, items) -> FoliageColors.get(0.5D, 1.0D), AtmosphericBlocks.YUCCA_LEAF_CARPET.get());
    }
}
