package com.teamabnormals.buzzier_bees.core.other.tags;

import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag.Named;
import net.minecraft.world.item.Item;

public class BBItemTags {
	public static final Named<Item> GRIZZLY_BEAR_FOOD = tag("grizzly_bear_food");
	public static final Named<Item> VERTICAL_SLABS = quarkTag("vertical_slabs");

	private static Named<Item> tag(String name) {
		return ItemTags.bind(BuzzierBees.MOD_ID + ":" + name);
	}

	private static Named<Item> quarkTag(String name) {
		return ItemTags.bind("quark:" + name);
	}
}
