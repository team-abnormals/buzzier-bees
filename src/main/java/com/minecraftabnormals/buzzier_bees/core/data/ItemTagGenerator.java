package com.minecraftabnormals.buzzier_bees.core.data;

import com.minecraftabnormals.buzzier_bees.core.BuzzierBees;
import com.minecraftabnormals.buzzier_bees.core.other.BBBlockTags;
import com.minecraftabnormals.buzzier_bees.core.other.BBItemTags;
import com.minecraftabnormals.buzzier_bees.core.registry.BBBlocks;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Items;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemTagGenerator extends ItemTagsProvider {

	public ItemTagGenerator(DataGenerator generator, BlockTagsProvider blockTagsProvider, ExistingFileHelper existingFileHelper) {
		super(generator, blockTagsProvider, BuzzierBees.MOD_ID, existingFileHelper);
	}

	@Override
	public void addTags() {
		this.copy(BlockTags.SMALL_FLOWERS, ItemTags.SMALL_FLOWERS);
		this.copy(BlockTags.SLABS, ItemTags.SLABS);
		this.copy(BlockTags.STAIRS, ItemTags.STAIRS);
		this.copy(BlockTags.WALLS, ItemTags.WALLS);
		this.copy(BBBlockTags.VERTICAL_SLABS, BBItemTags.VERTICAL_SLABS);

		this.copy(BlockTags.DOORS, ItemTags.DOORS);
		this.copy(BlockTags.TRAPDOORS, ItemTags.TRAPDOORS);
		this.copy(BlockTags.PIGLIN_REPELLENTS, ItemTags.PIGLIN_REPELLENTS);

		this.tag(BBItemTags.CANDLE_BASE).add(Items.HONEYCOMB);
		this.tag(BBItemTags.CANDLE_WICK).add(Items.STRING);
		this.copy(BBBlockTags.CANDLES, BBItemTags.CANDLES);
		this.copy(BBBlockTags.DYED_CANDLES, BBItemTags.DYED_CANDLES);
		this.copy(BBBlockTags.SCENTED_CANDLES, BBItemTags.SCENTED_CANDLES);
	}
}