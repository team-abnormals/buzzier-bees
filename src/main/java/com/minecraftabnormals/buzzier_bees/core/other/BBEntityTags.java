package com.minecraftabnormals.buzzier_bees.core.other;

import com.minecraftabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.entity.EntityType;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ITag.INamedTag;

public class BBEntityTags {
	public static final INamedTag<EntityType<?>> MOOBLOOM_HOSTILES = tag("moobloom_hostiles");

	private static INamedTag<EntityType<?>> tag(String name) {
		return EntityTypeTags.bind(BuzzierBees.MOD_ID + ":" + name);
	}
}
