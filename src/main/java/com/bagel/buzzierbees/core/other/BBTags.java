package com.bagel.buzzierbees.core.other;


import com.bagel.buzzierbees.core.BuzzierBees;

import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

public class BBTags {
	public static final Tag<Item> CANDLES = new ItemTags.Wrapper(new ResourceLocation(BuzzierBees.MODID, "candles"));
	public static final Tag<Item> DYED_CANDLES = new ItemTags.Wrapper(new ResourceLocation(BuzzierBees.MODID, "dyed_candles"));
	public static final Tag<Item> SCENTED_CANDLES = new ItemTags.Wrapper(new ResourceLocation(BuzzierBees.MODID, "scented_candles"));
	public static final Tag<Item> MODDED_POTTABLES = new ItemTags.Wrapper(new ResourceLocation(BuzzierBees.MODID, "modded_pottables"));
}
