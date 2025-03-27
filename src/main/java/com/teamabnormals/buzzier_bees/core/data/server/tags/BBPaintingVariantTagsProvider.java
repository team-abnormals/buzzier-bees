package com.teamabnormals.buzzier_bees.core.data.server.tags;

import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import com.teamabnormals.buzzier_bees.core.registry.datapack.BBPaintingVariants;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PaintingVariantTagsProvider;
import net.minecraft.tags.PaintingVariantTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class BBPaintingVariantTagsProvider extends PaintingVariantTagsProvider {
	public BBPaintingVariantTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
		super(output, provider, BuzzierBees.MOD_ID, existingFileHelper);
	}

	@Override
	public void addTags(HolderLookup.Provider provider) {
		this.tag(PaintingVariantTags.PLACEABLE).add(BBPaintingVariants.CANDLE);
	}
}