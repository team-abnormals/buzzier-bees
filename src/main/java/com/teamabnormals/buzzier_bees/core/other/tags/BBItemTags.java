package com.teamabnormals.buzzier_bees.core.other.tags;

import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag.Named;
import net.minecraft.world.item.Item;

public class BBItemTags {
	public static final Named<Item> GRIZZLY_BEAR_FOOD = tag("grizzly_bear_food");

	public static final Named<Item> CANDLE_BASE = tag("candle_base");
	public static final Named<Item> CANDLE_WICK = tag("candle_wick");
	public static final Named<Item> CANDLES = tag("candles");
	public static final Named<Item> DYED_CANDLES = tag("dyed_candles");
	public static final Named<Item> SCENTED_CANDLES = tag("scented_candles");

	public static final Named<Item> VERTICAL_SLABS = quarkTag("vertical_slabs");

	private static Named<Item> tag(String name) {
		return ItemTags.bind(BuzzierBees.MOD_ID + ":" + name);
	}

	private static Named<Item> quarkTag(String name) {
		return ItemTags.bind("quark:" + name);
	}
}
