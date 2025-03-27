package com.teamabnormals.buzzier_bees.core.data.server.tags;

import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import com.teamabnormals.buzzier_bees.core.other.tags.BBBlockTags;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

import static com.teamabnormals.buzzier_bees.core.registry.BBBlocks.*;

public class BBBlockTagsProvider extends BlockTagsProvider {

	public BBBlockTagsProvider(PackOutput output, CompletableFuture<Provider> provider, ExistingFileHelper helper) {
		super(output, provider, BuzzierBees.MOD_ID, helper);
	}

	@Override
	public void addTags(Provider provider) {
		this.tag(BlockTags.SMALL_FLOWERS).add(BUTTERCUP.get(), WHITE_CLOVER.get(), PINK_CLOVER.get());
		this.tag(BlockTags.FLOWER_POTS).add(POTTED_BUTTERCUP.get(), POTTED_WHITE_CLOVER.get(), POTTED_PINK_CLOVER.get());
		this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(HONEYCOMB_BRICKS.get(), HONEYCOMB_BRICK_SLAB.get(), HONEYCOMB_BRICK_STAIRS.get(), HONEYCOMB_BRICK_WALL.get(), CHISELED_HONEYCOMB_BRICKS.get(), HONEYCOMB_TILES.get(), HONEYCOMB_TILE_SLAB.get(), HONEYCOMB_TILE_STAIRS.get(), HONEYCOMB_TILE_WALL.get(), HONEYCOMB_DOOR.get(), HONEYCOMB_TRAPDOOR.get());
		this.tag(BlockTags.CANDLES).add(SOUL_CANDLE.get(), ENDER_CANDLE.get(), CUPRIC_CANDLE.get());
		this.tag(BlockTags.CANDLE_CAKES).add(SOUL_CANDLE_CAKE.get(), ENDER_CANDLE_CAKE.get(), CUPRIC_CANDLE_CAKE.get());
		this.tag(BlockTags.PIGLIN_REPELLENTS).add(SOUL_CANDLE.get(), SOUL_CANDLE_CAKE.get());

		this.tag(BlockTags.DOORS).add(HONEYCOMB_DOOR.get());
		this.tag(BlockTags.TRAPDOORS).add(HONEYCOMB_TRAPDOOR.get());
		this.tag(BlockTags.SLABS).add(HONEYCOMB_BRICK_SLAB.get(), HONEYCOMB_TILE_SLAB.get());
		this.tag(BlockTags.STAIRS).add(HONEYCOMB_BRICK_STAIRS.get(), HONEYCOMB_TILE_STAIRS.get());
		this.tag(BlockTags.WALLS).add(HONEYCOMB_BRICK_WALL.get(), HONEYCOMB_TILE_WALL.get());

		this.tag(BBBlockTags.FLOWER_BLACKLIST).add(Blocks.WITHER_ROSE);
	}
}