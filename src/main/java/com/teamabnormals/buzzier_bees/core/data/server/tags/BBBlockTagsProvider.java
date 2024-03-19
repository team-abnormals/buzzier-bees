package com.teamabnormals.buzzier_bees.core.data.server.tags;

import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import com.teamabnormals.buzzier_bees.core.other.tags.BBBlockTags;
import com.teamabnormals.buzzier_bees.core.registry.BBBlocks;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class BBBlockTagsProvider extends BlockTagsProvider {

	public BBBlockTagsProvider(PackOutput output, CompletableFuture<Provider> provider, ExistingFileHelper helper) {
		super(output, provider, BuzzierBees.MOD_ID, helper);
	}

	@Override
	public void addTags(Provider provider) {
		this.tag(BlockTags.SMALL_FLOWERS).add(BBBlocks.BUTTERCUP.get(), BBBlocks.WHITE_CLOVER.get(), BBBlocks.PINK_CLOVER.get());
		this.tag(BlockTags.FLOWER_POTS).add(BBBlocks.POTTED_BUTTERCUP.get(), BBBlocks.POTTED_WHITE_CLOVER.get(), BBBlocks.POTTED_PINK_CLOVER.get());
		this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BBBlocks.HONEYCOMB_BRICKS.get(), BBBlocks.HONEYCOMB_BRICK_SLAB.get(), BBBlocks.HONEYCOMB_BRICK_STAIRS.get(), BBBlocks.HONEYCOMB_BRICK_WALL.get(), BBBlocks.CHISELED_HONEYCOMB_BRICKS.get(), BBBlocks.HONEYCOMB_TILES.get(), BBBlocks.HONEYCOMB_TILE_SLAB.get(), BBBlocks.HONEYCOMB_TILE_STAIRS.get(), BBBlocks.HONEYCOMB_TILE_WALL.get(), BBBlocks.HONEYCOMB_DOOR.get(), BBBlocks.HONEYCOMB_TRAPDOOR.get());
		this.tag(BlockTags.CANDLES).add(BBBlocks.SOUL_CANDLE.get());
		this.tag(BlockTags.CANDLE_CAKES).add(BBBlocks.SOUL_CANDLE_CAKE.get());
		this.tag(BlockTags.PIGLIN_REPELLENTS).add(BBBlocks.SOUL_CANDLE.get(), BBBlocks.SOUL_CANDLE_CAKE.get());

		this.tag(BlockTags.DOORS).add(BBBlocks.HONEYCOMB_DOOR.get());
		this.tag(BlockTags.TRAPDOORS).add(BBBlocks.HONEYCOMB_TRAPDOOR.get());
		this.tag(BlockTags.SLABS).add(BBBlocks.HONEYCOMB_BRICK_SLAB.get(), BBBlocks.HONEYCOMB_TILE_SLAB.get());
		this.tag(BlockTags.STAIRS).add(BBBlocks.HONEYCOMB_BRICK_STAIRS.get(), BBBlocks.HONEYCOMB_TILE_STAIRS.get());
		this.tag(BlockTags.WALLS).add(BBBlocks.HONEYCOMB_BRICK_WALL.get(), BBBlocks.HONEYCOMB_TILE_WALL.get());

		this.tag(BBBlockTags.FLOWER_BLACKLIST).add(Blocks.WITHER_ROSE);
	}
}