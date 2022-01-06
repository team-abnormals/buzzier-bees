package com.teamabnormals.buzzier_bees.core.other.tags;

import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.Tag.Named;
import net.minecraft.world.entity.EntityType;

public class BBEntityTags {
	public static final Named<EntityType<?>> MOOBLOOM_HOSTILES = tag("moobloom_hostiles");

	private static Named<EntityType<?>> tag(String name) {
		return EntityTypeTags.bind(BuzzierBees.MOD_ID + ":" + name);
	}
}
