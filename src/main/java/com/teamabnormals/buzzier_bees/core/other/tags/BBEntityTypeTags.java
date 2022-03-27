package com.teamabnormals.buzzier_bees.core.other.tags;

import com.teamabnormals.blueprint.core.util.TagUtil;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class BBEntityTypeTags {
	public static final TagKey<EntityType<?>> MOOBLOOM_HOSTILES = entityTypeTag("moobloom_hostiles");

	private static TagKey<EntityType<?>> entityTypeTag(String name) {
		return TagUtil.entityTypeTag(BuzzierBees.MOD_ID, name);
	}
}
