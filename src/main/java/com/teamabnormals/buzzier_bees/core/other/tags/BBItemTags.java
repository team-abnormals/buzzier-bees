package com.teamabnormals.buzzier_bees.core.other.tags;

import com.teamabnormals.blueprint.core.util.TagUtil;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import javax.swing.text.html.HTML.Tag;

public class BBItemTags {
	public static final TagKey<Item> FOODS_PASTRY = TagUtil.itemTag("c", "foods/pastry");
	public static final TagKey<Item> GRIZZLY_BEAR_FOOD = itemTag("grizzly_bear_food");
	public static final TagKey<Item> ENDER_FIRE_BASE_BLOCKS = TagUtil.itemTag("endergetic", "ender_fire_base_blocks");
	public static final TagKey<Item> CUPRIC_FIRE_BASE_BLOCKS = TagUtil.itemTag("caverns_and_chasms", "cupric_fire_base_blocks");

	private static TagKey<Item> itemTag(String name) {
		return TagUtil.itemTag(BuzzierBees.MOD_ID, name);
	}
}
