package com.teamabnormals.buzzier_bees.core.other.tags;

import com.teamabnormals.blueprint.core.util.TagUtil;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class BBBiomeTags {
	public static final TagKey<Biome> HAS_MOOBLOOM = biomeTag("has_animal/moobloom");
	public static final TagKey<Biome> HAS_BUTTERCUP = biomeTag("has_feature/buttercup");
	public static final TagKey<Biome> HAS_WHITE_CLOVER = biomeTag("has_feature/white_clover");
	public static final TagKey<Biome> HAS_PINK_CLOVER = biomeTag("has_feature/pink_clover");

	private static TagKey<Biome> biomeTag(String name) {
		return TagUtil.biomeTag(BuzzierBees.MOD_ID, name);
	}
}
