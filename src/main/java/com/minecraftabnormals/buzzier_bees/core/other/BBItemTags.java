package com.minecraftabnormals.buzzier_bees.core.other;

import com.minecraftabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.tags.ItemTags;

public class BBItemTags {
	public static final INamedTag<Item> CANDLE_BASE = tag("candle_base");
	public static final INamedTag<Item> CANDLE_WICK = tag("candle_wick");
	public static final INamedTag<Item> CANDLES = tag("candles");
	public static final INamedTag<Item> DYED_CANDLES = tag("dyed_candles");
	public static final INamedTag<Item> SCENTED_CANDLES = tag("scented_candles");

	public static final INamedTag<Item> VERTICAL_SLABS = quarkTag("vertical_slabs");

	private static INamedTag<Item> tag(String name) {
		return ItemTags.bind(BuzzierBees.MOD_ID + ":" + name);
	}

	private static INamedTag<Item> quarkTag(String name) {
		return ItemTags.bind("quark:" + name);
	}
}
