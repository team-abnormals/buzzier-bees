package com.teamabnormals.buzzier_bees.core.other.tags;

import com.teamabnormals.blueprint.core.util.TagUtil;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class BBBlockTags {
	public static final TagKey<Block> FLOWER_BLACKLIST = blockTag("flower_blacklist");

	private static TagKey<Block> blockTag(String name) {
		return TagUtil.blockTag(BuzzierBees.MOD_ID, name);
	}
}