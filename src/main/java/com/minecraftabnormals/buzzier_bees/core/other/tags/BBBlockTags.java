package com.minecraftabnormals.buzzier_bees.core.other.tags;

import com.minecraftabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag.INamedTag;

public class BBBlockTags {
	public static final INamedTag<Block> CANDLES = tag("candles");
	public static final INamedTag<Block> DYED_CANDLES = tag("dyed_candles");
	public static final INamedTag<Block> SCENTED_CANDLES = tag("scented_candles");
	public static final INamedTag<Block> FLOWER_BLACKLIST = tag("flower_blacklist");

	public static final INamedTag<Block> VERTICAL_SLABS = quarkTag("vertical_slabs");

	private static INamedTag<Block> tag(String name) {
		return BlockTags.bind(BuzzierBees.MOD_ID + ":" + name);
	}

	private static INamedTag<Block> quarkTag(String name) {
		return BlockTags.bind("quark:" + name);
	}
}