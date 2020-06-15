package com.bagel.buzzierbees.core.other;


import com.bagel.buzzierbees.core.BuzzierBees;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

public class BBTags {
	
	static class Blocks {
		public static final Tag<Block> FLOWER_BLACKLIST = new BlockTags.Wrapper(new ResourceLocation(BuzzierBees.MODID, "flower_blacklist"));
	}

	static class Items {
		public static final Tag<Item> MODDED_POTTABLES = new ItemTags.Wrapper(new ResourceLocation(BuzzierBees.MODID, "modded_pottables"));
	}
}
