package com.teamabnormals.buzzier_bees.core.data.server.tags;

import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import com.teamabnormals.buzzier_bees.core.other.tags.BBBiomeTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BBBiomeTagsProvider extends BiomeTagsProvider {

	public BBBiomeTagsProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, BuzzierBees.MOD_ID, existingFileHelper);
	}

	@Override
	public void addTags() {
		this.tag(BBBiomeTags.HAS_MOOBLOOM).addTag(BBBiomeTags.HAS_BUTTERCUP);
		this.tag(BBBiomeTags.HAS_BUTTERCUP).add(Biomes.SUNFLOWER_PLAINS);
		this.tag(BBBiomeTags.HAS_WHITE_CLOVER).add(Biomes.FOREST, Biomes.WINDSWEPT_FOREST);
		this.tag(BBBiomeTags.HAS_PINK_CLOVER).add(Biomes.BIRCH_FOREST, Biomes.OLD_GROWTH_BIRCH_FOREST);
	}
}