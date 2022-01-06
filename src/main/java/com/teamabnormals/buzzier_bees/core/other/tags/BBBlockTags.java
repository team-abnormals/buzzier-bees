package com.teamabnormals.buzzier_bees.core.other.tags;

import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag.Named;
import net.minecraft.world.level.block.Block;

public class BBBlockTags {
	public static final Named<Block> FLOWER_BLACKLIST = tag("flower_blacklist");
	public static final Named<Block> VERTICAL_SLABS = quarkTag("vertical_slabs");

	private static Named<Block> tag(String name) {
		return BlockTags.bind(BuzzierBees.MOD_ID + ":" + name);
	}

	private static Named<Block> quarkTag(String name) {
		return BlockTags.bind("quark:" + name);
	}
}