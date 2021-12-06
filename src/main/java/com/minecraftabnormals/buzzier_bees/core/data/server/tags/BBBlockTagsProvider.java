package com.minecraftabnormals.buzzier_bees.core.data.server.tags;

import com.minecraftabnormals.buzzier_bees.core.BuzzierBees;
import com.minecraftabnormals.buzzier_bees.core.other.tags.BBBlockTags;
import com.minecraftabnormals.buzzier_bees.core.registry.BBBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BBBlockTagsProvider extends BlockTagsProvider {

	public BBBlockTagsProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, BuzzierBees.MOD_ID, existingFileHelper);
	}

	@Override
	public void addTags() {
		this.tag(BlockTags.BEEHIVES).add(BBBlocks.BIRCH_BEEHIVE.get(), BBBlocks.SPRUCE_BEEHIVE.get(), BBBlocks.JUNGLE_BEEHIVE.get(), BBBlocks.ACACIA_BEEHIVE.get(), BBBlocks.DARK_OAK_BEEHIVE.get(), BBBlocks.CRIMSON_BEEHIVE.get(), BBBlocks.WARPED_BEEHIVE.get());
		this.tag(BlockTags.SMALL_FLOWERS).add(BBBlocks.BUTTERCUP.get(), BBBlocks.WHITE_CLOVER.get(), BBBlocks.PINK_CLOVER.get());
		this.tag(BlockTags.FLOWER_POTS).add(BBBlocks.POTTED_BUTTERCUP.get(), BBBlocks.POTTED_WHITE_CLOVER.get(), BBBlocks.POTTED_PINK_CLOVER.get());
		this.tag(BlockTags.PIGLIN_REPELLENTS).add(BBBlocks.SOUL_CANDLE.get());

		this.tag(BlockTags.DOORS).add(BBBlocks.HONEYCOMB_DOOR.get());
		this.tag(BlockTags.TRAPDOORS).add(BBBlocks.HONEYCOMB_TRAPDOOR.get());
		this.tag(BlockTags.SLABS).add(BBBlocks.HONEYCOMB_BRICK_SLAB.get(), BBBlocks.HONEYCOMB_TILE_SLAB.get());
		this.tag(BlockTags.STAIRS).add(BBBlocks.HONEYCOMB_BRICK_STAIRS.get(), BBBlocks.HONEYCOMB_TILE_STAIRS.get());
		this.tag(BlockTags.WALLS).add(BBBlocks.HONEYCOMB_BRICK_WALL.get(), BBBlocks.HONEYCOMB_TILE_WALL.get());
		this.tag(BBBlockTags.VERTICAL_SLABS).add(BBBlocks.HONEYCOMB_BRICK_VERTICAL_SLAB.get(), BBBlocks.HONEYCOMB_TILE_VERTICAL_SLAB.get());

		this.tag(BBBlockTags.CANDLES).add(BBBlocks.CANDLE.get(), BBBlocks.SOUL_CANDLE.get(), BBBlocks.ENDER_CANDLE.get(), BBBlocks.CURSED_CANDLE.get()).addTag(BBBlockTags.DYED_CANDLES).addTag(BBBlockTags.SCENTED_CANDLES);
		this.tag(BBBlockTags.DYED_CANDLES).add(BBBlocks.WHITE_CANDLE.get(), BBBlocks.ORANGE_CANDLE.get(), BBBlocks.MAGENTA_CANDLE.get(), BBBlocks.LIGHT_BLUE_CANDLE.get(), BBBlocks.YELLOW_CANDLE.get(), BBBlocks.LIME_CANDLE.get(), BBBlocks.PINK_CANDLE.get(), BBBlocks.GRAY_CANDLE.get(), BBBlocks.LIGHT_GRAY_CANDLE.get(), BBBlocks.CYAN_CANDLE.get(), BBBlocks.PURPLE_CANDLE.get(), BBBlocks.BLUE_CANDLE.get(), BBBlocks.BROWN_CANDLE.get(), BBBlocks.GREEN_CANDLE.get(), BBBlocks.RED_CANDLE.get(), BBBlocks.BLACK_CANDLE.get());
		this.tag(BBBlockTags.SCENTED_CANDLES).add(BBBlocks.DANDELION_SCENTED_CANDLE.get(), BBBlocks.POPPY_SCENTED_CANDLE.get(), BBBlocks.BLUE_ORCHID_SCENTED_CANDLE.get(), BBBlocks.ALLIUM_SCENTED_CANDLE.get(), BBBlocks.AZURE_BLUET_SCENTED_CANDLE.get(), BBBlocks.RED_TULIP_SCENTED_CANDLE.get(), BBBlocks.ORANGE_TULIP_SCENTED_CANDLE.get(), BBBlocks.WHITE_TULIP_SCENTED_CANDLE.get(), BBBlocks.PINK_TULIP_SCENTED_CANDLE.get(), BBBlocks.OXEYE_DAISY_SCENTED_CANDLE.get(), BBBlocks.CORNFLOWER_SCENTED_CANDLE.get(), BBBlocks.LILY_OF_THE_VALLEY_SCENTED_CANDLE.get(), BBBlocks.WITHER_ROSE_SCENTED_CANDLE.get(), BBBlocks.WHITE_CLOVER_SCENTED_CANDLE.get(), BBBlocks.PINK_CLOVER_SCENTED_CANDLE.get(), BBBlocks.BUTTERCUP_SCENTED_CANDLE.get(), BBBlocks.CARTWHEEL_SCENTED_CANDLE.get(), BBBlocks.BLUEBELL_SCENTED_CANDLE.get(), BBBlocks.VIOLET_SCENTED_CANDLE.get(), BBBlocks.DIANTHUS_SCENTED_CANDLE.get(), BBBlocks.RED_LOTUS_FLOWER_SCENTED_CANDLE.get(), BBBlocks.WHITE_LOTUS_FLOWER_SCENTED_CANDLE.get(), BBBlocks.YELLOW_HIBISCUS_SCENTED_CANDLE.get(), BBBlocks.ORANGE_HIBISCUS_SCENTED_CANDLE.get(), BBBlocks.RED_HIBISCUS_SCENTED_CANDLE.get(), BBBlocks.PINK_HIBISCUS_SCENTED_CANDLE.get(), BBBlocks.MAGENTA_HIBISCUS_SCENTED_CANDLE.get(), BBBlocks.PURPLE_HIBISCUS_SCENTED_CANDLE.get(), BBBlocks.WARM_MONKEY_BRUSH_SCENTED_CANDLE.get(), BBBlocks.HOT_MONKEY_BRUSH_SCENTED_CANDLE.get(), BBBlocks.SCALDING_MONKEY_BRUSH_SCENTED_CANDLE.get(), BBBlocks.WATER_HYACINTH_SCENTED_CANDLE.get(), BBBlocks.GILIA_SCENTED_CANDLE.get(), BBBlocks.YUCCA_FLOWER_SCENTED_CANDLE.get(), BBBlocks.PINK_SEAROCKET_SCENTED_CANDLE.get(), BBBlocks.WHITE_SEAROCKET_SCENTED_CANDLE.get(), BBBlocks.AUTUMN_CROCUS_SCENTED_CANDLE.get());
		this.tag(BBBlockTags.FLOWER_BLACKLIST).add(Blocks.WITHER_ROSE);
	}
}