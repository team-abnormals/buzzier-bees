package com.teamabnormals.buzzier_bees.core.other.tags;

import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.entity.BannerPattern;

public class BBBannerPatternTags {
	public static final TagKey<BannerPattern> PATTERN_ITEM_HONEYCOMB = bannerPatternTag("pattern_item/honeycomb");

	private static TagKey<BannerPattern> bannerPatternTag(String name) {
		return TagKey.create(Registry.BANNER_PATTERN_REGISTRY, new ResourceLocation(BuzzierBees.MOD_ID, name));
	}
}
