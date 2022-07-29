package com.teamabnormals.buzzier_bees.core.data.server.tags;

import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import com.teamabnormals.buzzier_bees.core.other.tags.BBBannerPatternTags;
import com.teamabnormals.buzzier_bees.core.registry.BBBannerPatterns;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BannerPatternTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BBBannerPatternTagsProvider extends BannerPatternTagsProvider {

	public BBBannerPatternTagsProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, BuzzierBees.MOD_ID, existingFileHelper);
	}

	@Override
	public void addTags() {
		this.tag(BBBannerPatternTags.PATTERN_ITEM_HONEYCOMB).add(BBBannerPatterns.HONEYCOMB.get());
	}
}