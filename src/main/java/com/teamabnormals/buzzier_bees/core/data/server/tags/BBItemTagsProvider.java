package com.teamabnormals.buzzier_bees.core.data.server.tags;

import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import com.teamabnormals.buzzier_bees.core.other.tags.BBItemTags;
import com.teamabnormals.buzzier_bees.core.registry.BBBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

import static com.teamabnormals.buzzier_bees.core.registry.BBItems.*;

public class BBItemTagsProvider extends ItemTagsProvider {

	public BBItemTagsProvider(PackOutput output, CompletableFuture<Provider> provider, CompletableFuture<TagsProvider.TagLookup<Block>> lookup, ExistingFileHelper helper) {
		super(output, provider, lookup, BuzzierBees.MOD_ID, helper);
	}

	@Override
	public void addTags(HolderLookup.Provider provider) {
		this.copy(BlockTags.SMALL_FLOWERS, ItemTags.SMALL_FLOWERS);
		this.copy(BlockTags.SLABS, ItemTags.SLABS);
		this.copy(BlockTags.STAIRS, ItemTags.STAIRS);
		this.copy(BlockTags.WALLS, ItemTags.WALLS);
		this.copy(BlockTags.CANDLES, ItemTags.CANDLES);
		this.tag(ItemTags.PIGLIN_REPELLENTS).add(BBBlocks.SOUL_CANDLE.asItem());

		this.copy(BlockTags.DOORS, ItemTags.DOORS);
		this.copy(BlockTags.TRAPDOORS, ItemTags.TRAPDOORS);

		this.tag(BBItemTags.GRIZZLY_BEAR_FOOD).add(Items.SALMON, Items.COOKED_SALMON, Items.SALMON_BUCKET);

		this.tag(ItemTags.MEAT).add(GLAZED_PORKCHOP.get());
		this.tag(Tags.Items.FOODS).add(GLAZED_PORKCHOP.get()).addTag(BBItemTags.FOODS_PASTRY);
		this.tag(Tags.Items.FOODS_FRUIT).add(HONEY_APPLE.get());
		this.tag(BBItemTags.FOODS_PASTRY).add(HONEY_BREAD.get());
		this.tag(Tags.Items.ANIMAL_FOODS).addTag(BBItemTags.GRIZZLY_BEAR_FOOD);
	}
}