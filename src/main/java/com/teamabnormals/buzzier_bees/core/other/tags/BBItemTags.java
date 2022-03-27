package com.teamabnormals.buzzier_bees.core.other.tags;

import com.teamabnormals.blueprint.core.util.TagUtil;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class BBItemTags {
	public static final TagKey<Item> GRIZZLY_BEAR_FOOD = itemTag("grizzly_bear_food");

	private static TagKey<Item> itemTag(String name) {
		return TagUtil.itemTag(BuzzierBees.MOD_ID, name);
	}
}
