package com.minecraftabnormals.buzzier_bees.core.other;

import com.minecraftabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.tags.ItemTags;

public class BBTags {

	public static class Blocks {
		public static final INamedTag<Block> FLOWER_BLACKLIST = BlockTags.bind(BuzzierBees.MOD_ID + ":flower_blacklist");
	}

	public static class Items {
		public static final INamedTag<Item> DYED_CANDLES = ItemTags.bind(BuzzierBees.MOD_ID + ":dyed_candles");
	}

	public static class EntityTypes {
		public static final INamedTag<EntityType<?>> MOOBLOOM_HOSTILES = EntityTypeTags.bind(BuzzierBees.MOD_ID + ":moobloom_hostiles");
	}
}
