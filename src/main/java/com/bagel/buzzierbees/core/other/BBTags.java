package com.bagel.buzzierbees.core.other;


import com.bagel.buzzierbees.core.BuzzierBees;

import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag.INamedTag;

public class BBTags {
	
	static class Blocks {
		public static final INamedTag<Block> FLOWER_BLACKLIST = BlockTags.makeWrapperTag(BuzzierBees.MODID + ":flower_blacklist");
	}
}
