package com.teamabnormals.buzzier_bees.core.data.server.tags;

import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import com.teamabnormals.buzzier_bees.core.other.tags.BBBannerPatternTags;
import com.teamabnormals.buzzier_bees.core.registry.datapack.BBBannerPatterns;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BannerPatternTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class BBBannerPatternTagsProvider extends BannerPatternTagsProvider {

	public BBBannerPatternTagsProvider(PackOutput output, CompletableFuture<Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
		super(output, provider, BuzzierBees.MOD_ID, existingFileHelper);
	}

	@Override
	public void addTags(HolderLookup.Provider provider) {
		this.tag(BBBannerPatternTags.PATTERN_ITEM_HONEYCOMB).add(BBBannerPatterns.HONEYCOMB);
	}
}