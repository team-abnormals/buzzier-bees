package com.bagel.buzzierbees.core.registry;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class BBFoods {
	public static Food CRYSTALLIZED_HONEY  = new Food.Builder().hunger(1).saturation(1.5F).fastToEat().setAlwaysEdible().effect(new EffectInstance(Effects.SPEED, 160, 1), 0.8F).build();
	public static Food BEE_SOUP            = new Food.Builder().hunger(3).saturation(2.3F).effect(new EffectInstance(Effects.SLOWNESS, 240, 2), 0.5F).build();
	public static Food CLOVER_HONEY_BOTTLE = new Food.Builder().hunger(6).saturation(0.2F).setAlwaysEdible().effect(new EffectInstance(Effects.INSTANT_HEALTH, 20, 1), 0.8F).build();
	public static Food STICKY_HONEY_WAND   = new Food.Builder().hunger(6).saturation(0.1F).setAlwaysEdible().build();
	public static Food HONEY_APPLE         = new Food.Builder().hunger(5).saturation(0.4F).build();
	public static Food HONEY_BREAD         = new Food.Builder().hunger(6).saturation(0.8F).build();
	public static Food GLAZED_PORKCHOP	   = new Food.Builder().hunger(9).saturation(0.8F).build();
}
