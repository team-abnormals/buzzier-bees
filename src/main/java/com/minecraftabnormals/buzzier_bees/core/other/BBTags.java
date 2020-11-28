package com.minecraftabnormals.buzzier_bees.core.other;

import com.minecraftabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.tags.ItemTags;

public class BBTags {

	static class Blocks {
		public static final INamedTag<Block> FLOWER_BLACKLIST = BlockTags.makeWrapperTag(BuzzierBees.MODID + ":flower_blacklist");
	}

	static class Items {
		public static final INamedTag<Item> DYED_CANDLES 	= ItemTags.makeWrapperTag(BuzzierBees.MODID + ":dyed_candles");
		public static final INamedTag<Item> SCENTED_CANDLES = ItemTags.makeWrapperTag(BuzzierBees.MODID + ":scented_candles");
	}
}
