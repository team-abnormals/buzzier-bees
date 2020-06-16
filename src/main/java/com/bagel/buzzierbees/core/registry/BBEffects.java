package com.bagel.buzzierbees.core.registry;

import com.bagel.buzzierbees.common.effects.AntiEffect;
import com.bagel.buzzierbees.core.BuzzierBees;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionBrewing;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BBEffects {
	public static final DeferredRegister<Effect> EFFECTS = new DeferredRegister<>(ForgeRegistries.POTIONS, BuzzierBees.MODID);
	public static final DeferredRegister<Potion> POTIONS = new DeferredRegister<>(ForgeRegistries.POTION_TYPES, BuzzierBees.MODID);
   
	public static RegistryObject<Effect> ANTI_SPEED  				= EFFECTS.register("anti_speed", () -> new AntiEffect(EffectType.HARMFUL, 8605753, new EffectInstance[]{new EffectInstance(Effects.SPEED)}));
	public static RegistryObject<Effect> ANTI_SLOWNESS   			= EFFECTS.register("anti_slowness", () -> new AntiEffect(EffectType.BENEFICIAL,  10851198,new EffectInstance[]{new EffectInstance(Effects.SLOWNESS)}));
	public static RegistryObject<Effect> ANTI_HASTE  				= EFFECTS.register("anti_haste", () -> new AntiEffect(EffectType.HARMFUL, 2506684, new EffectInstance[]{new EffectInstance(Effects.HASTE)}));
	public static RegistryObject<Effect> ANTI_MINING_FATIGUE 		= EFFECTS.register("anti_mining_fatigue", () -> new AntiEffect(EffectType.BENEFICIAL,  0,   new EffectInstance[]{new EffectInstance(Effects.MINING_FATIGUE)}));
	public static RegistryObject<Effect> ANTI_STRENGTH   			= EFFECTS.register("anti_strength", () -> new AntiEffect(EffectType.HARMFUL, 7134172, new EffectInstance[]{new EffectInstance(Effects.STRENGTH)}));
	public static RegistryObject<Effect> ANTI_INSTANT_HEALTH 		= EFFECTS.register("anti_instant_health", () -> new AntiEffect(EffectType.HARMFUL, 0,   new EffectInstance[]{new EffectInstance(Effects.INSTANT_HEALTH)}));
	public static RegistryObject<Effect> ANTI_INSTANT_DAMAGE 		= EFFECTS.register("anti_instant_damage", () -> new AntiEffect(EffectType.BENEFICIAL,  0,   new EffectInstance[]{new EffectInstance(Effects.INSTANT_DAMAGE)}));
	public static RegistryObject<Effect> ANTI_JUMP_BOOST 			= EFFECTS.register("anti_jump_boost", () -> new AntiEffect(EffectType.HARMFUL, 14483635,new EffectInstance[]{new EffectInstance(Effects.JUMP_BOOST)}));
	public static RegistryObject<Effect> ANTI_NAUSEA 				= EFFECTS.register("anti_nausea", () -> new AntiEffect(EffectType.BENEFICIAL,  0,   new EffectInstance[]{new EffectInstance(Effects.NAUSEA)}));
	public static RegistryObject<Effect> ANTI_REGENERATION   		= EFFECTS.register("anti_regeneration", () -> new AntiEffect(EffectType.HARMFUL, 3318612, new EffectInstance[]{new EffectInstance(Effects.REGENERATION)}));
	public static RegistryObject<Effect> ANTI_RESISTANCE 			= EFFECTS.register("anti_resistance", () -> new AntiEffect(EffectType.HARMFUL, 0,   new EffectInstance[]{new EffectInstance(Effects.RESISTANCE)}));
	public static RegistryObject<Effect> ANTI_FIRE_RESISTANCE		= EFFECTS.register("anti_fire_resistance", () -> new AntiEffect(EffectType.HARMFUL, 1795525, new EffectInstance[]{new EffectInstance(Effects.FIRE_RESISTANCE)}));
	public static RegistryObject<Effect> ANTI_WATER_BREATHING		= EFFECTS.register("anti_water_breathing", () -> new AntiEffect(EffectType.HARMFUL, 13741414,new EffectInstance[]{new EffectInstance(Effects.WATER_BREATHING)}));
	public static RegistryObject<Effect> ANTI_INVISIBILITY   		= EFFECTS.register("anti_invisibility", () -> new AntiEffect(EffectType.HARMFUL, 8420461, new EffectInstance[]{new EffectInstance(Effects.INVISIBILITY)}));
	public static RegistryObject<Effect> ANTI_BLINDNESS  			= EFFECTS.register("anti_blindness", () -> new AntiEffect(EffectType.BENEFICIAL,  14737628,new EffectInstance[]{new EffectInstance(Effects.BLINDNESS)}));
	public static RegistryObject<Effect> ANTI_NIGHT_VISION  		= EFFECTS.register("anti_night_vision", () -> new AntiEffect(EffectType.HARMFUL, 14737502,new EffectInstance[]{new EffectInstance(Effects.NIGHT_VISION)}));
	public static RegistryObject<Effect> ANTI_HUNGER 				= EFFECTS.register("anti_hunger", () -> new AntiEffect(EffectType.BENEFICIAL,  0,   new EffectInstance[]{new EffectInstance(Effects.HUNGER)}));
	public static RegistryObject<Effect> ANTI_WEAKNESS   			= EFFECTS.register("anti_weakness", () -> new AntiEffect(EffectType.BENEFICIAL,  12038839,new EffectInstance[]{new EffectInstance(Effects.WEAKNESS)}));
	public static RegistryObject<Effect> ANTI_POISON 				= EFFECTS.register("anti_poison", () -> new AntiEffect(EffectType.BENEFICIAL,  11627726,new EffectInstance[]{new EffectInstance(Effects.POISON)}));
	public static RegistryObject<Effect> ANTI_WITHER 				= EFFECTS.register("anti_wither", () -> new AntiEffect(EffectType.BENEFICIAL,  0,   new EffectInstance[]{new EffectInstance(Effects.WITHER)}));
	public static RegistryObject<Effect> ANTI_HEALTH_BOOST   		= EFFECTS.register("anti_health_boost", () -> new AntiEffect(EffectType.HARMFUL, 0,   new EffectInstance[]{new EffectInstance(Effects.HEALTH_BOOST)}));
	public static RegistryObject<Effect> ANTI_ABSORPTION 			= EFFECTS.register("anti_absorption", () -> new AntiEffect(EffectType.HARMFUL, 0,   new EffectInstance[]{new EffectInstance(Effects.ABSORPTION)}));
	public static RegistryObject<Effect> ANTI_SATURATION 			= EFFECTS.register("anti_saturation", () -> new AntiEffect(EffectType.HARMFUL, 0,   new EffectInstance[]{new EffectInstance(Effects.SATURATION)}));
	public static RegistryObject<Effect> ANTI_GLOWING				= EFFECTS.register("anti_glowing", () -> new AntiEffect(EffectType.HARMFUL, 0,   new EffectInstance[]{new EffectInstance(Effects.GLOWING)}));
	public static RegistryObject<Effect> ANTI_LEVITATION 			= EFFECTS.register("anti_levitation", () -> new AntiEffect(EffectType.BENEFICIAL,  0,   new EffectInstance[]{new EffectInstance(Effects.LEVITATION)}));
	public static RegistryObject<Effect> ANTI_LUCK   				= EFFECTS.register("anti_luck", () -> new AntiEffect(EffectType.HARMFUL, 13395711,new EffectInstance[]{new EffectInstance(Effects.LUCK)}));
	public static RegistryObject<Effect> ANTI_UNLUCK 				= EFFECTS.register("anti_unluck", () -> new AntiEffect(EffectType.BENEFICIAL,  4152242, new EffectInstance[]{new EffectInstance(Effects.UNLUCK)}));
	public static RegistryObject<Effect> ANTI_SLOW_FALLING   		= EFFECTS.register("anti_slow_falling", () -> new AntiEffect(EffectType.HARMFUL, 4142,new EffectInstance[]{new EffectInstance(Effects.SLOW_FALLING)}));
	public static RegistryObject<Effect> ANTI_CONDUIT_POWER  		= EFFECTS.register("anti_conduit_power", () -> new AntiEffect(EffectType.HARMFUL, 0,   new EffectInstance[]{new EffectInstance(Effects.CONDUIT_POWER)}));
	public static RegistryObject<Effect> ANTI_DOLPHINS_GRACE 		= EFFECTS.register("anti_dolphins_grace", () -> new AntiEffect(EffectType.HARMFUL, 0,   new EffectInstance[]{new EffectInstance(Effects.DOLPHINS_GRACE)}));
	public static RegistryObject<Effect> ANTI_BAD_OMEN   			= EFFECTS.register("anti_bad_omen", () -> new AntiEffect(EffectType.BENEFICIAL,  0,   new EffectInstance[]{new EffectInstance(Effects.BAD_OMEN)}));
	public static RegistryObject<Effect> ANTI_HERO_OF_THE_VILLAGE	= EFFECTS.register("anti_hero_of_the_village", () -> new AntiEffect(EffectType.HARMFUL, 0,   new EffectInstance[]{new EffectInstance(Effects.HERO_OF_THE_VILLAGE)}));

	public static final RegistryObject<Potion> LONG_LUCK     = POTIONS.register("long_luck", () -> new Potion(new EffectInstance[] {new EffectInstance(Effects.LUCK,      9600)}));
	public static final RegistryObject<Potion> STRONG_LUCK   = POTIONS.register("strong_luck", () -> new Potion(new EffectInstance[] {new EffectInstance(Effects.LUCK,      3600, 1)}));
    public static final RegistryObject<Potion> UNLUCK        = POTIONS.register("unluck", () -> new Potion(new EffectInstance[] {new EffectInstance(Effects.UNLUCK,    3600)}));
    public static final RegistryObject<Potion> LONG_UNLUCK   = POTIONS.register("long_unluck", () -> new Potion(new EffectInstance[] {new EffectInstance(Effects.UNLUCK,    9600)}));
    public static final RegistryObject<Potion> STRONG_UNLUCK = POTIONS.register("strong_unluck", () -> new Potion(new EffectInstance[] {new EffectInstance(Effects.UNLUCK,    3600, 1)}));
    
    public static final RegistryObject<Potion> PLACEBO                 = POTIONS.register("placebo", () -> new Potion("placebo", new EffectInstance[0]));
	public static final RegistryObject<Potion> NIGHT_VISION_CURE       = POTIONS.register("night_vision_cure", () -> new Potion("night_vision_cure",    	new EffectInstance[] {new EffectInstance(ANTI_NIGHT_VISION.get())}));
    public static final RegistryObject<Potion> INVISIBILITY_CURE       = POTIONS.register("invisibility_cure", () -> new Potion("invisibility_cure",    	new EffectInstance[] {new EffectInstance(ANTI_INVISIBILITY.get())}));
    public static final RegistryObject<Potion> LEAPING_CURE            = POTIONS.register("jump_boost_cure", () -> new Potion("jump_boost_cure",      		new EffectInstance[] {new EffectInstance(ANTI_JUMP_BOOST.get())}));
    public static final RegistryObject<Potion> FIRE_RESISTANCE_CURE    = POTIONS.register("fire_resistance_cure", () -> new Potion("fire_resistance_cure", 	new EffectInstance[] {new EffectInstance(ANTI_FIRE_RESISTANCE.get())}));
    public static final RegistryObject<Potion> SWIFTNESS_CURE          = POTIONS.register("speed_cure", () -> new Potion("speed_cure",           			new EffectInstance[] {new EffectInstance(ANTI_SPEED.get())}));
    public static final RegistryObject<Potion> SLOWNESS_CURE           = POTIONS.register("slowness_cure", () -> new Potion("slowness_cure",        		new EffectInstance[] {new EffectInstance(ANTI_SLOWNESS.get())}));
    public static final RegistryObject<Potion> WATER_BREATHING_CURE    = POTIONS.register("water_breathing_cure", () -> new Potion("water_breathing_cure", 	new EffectInstance[] {new EffectInstance(ANTI_WATER_BREATHING.get())}));
    public static final RegistryObject<Potion> POISON_CURE             = POTIONS.register("poison_cure", () -> new Potion("poison_cure",          			new EffectInstance[] {new EffectInstance(ANTI_POISON.get())}));
    public static final RegistryObject<Potion> REGENERATION_CURE       = POTIONS.register("regeneration_cure", () -> new Potion("regeneration_cure",    	new EffectInstance[] {new EffectInstance(ANTI_REGENERATION.get())}));
    public static final RegistryObject<Potion> STRENGTH_CURE           = POTIONS.register("strength_cure", () -> new Potion("strength_cure",        		new EffectInstance[] {new EffectInstance(ANTI_STRENGTH.get())}));
    public static final RegistryObject<Potion> WEAKNESS_CURE           = POTIONS.register("weakness_cure", () -> new Potion("weakness_cure",       			new EffectInstance[] {new EffectInstance(ANTI_WEAKNESS.get())}));
    public static final RegistryObject<Potion> LUCK_CURE               = POTIONS.register("luck_cure", () -> new Potion("luck_cure",            			new EffectInstance[] {new EffectInstance(ANTI_LUCK.get())}));
    public static final RegistryObject<Potion> UNLUCK_CURE             = POTIONS.register("unluck_cure", () -> new Potion("unluck_cure",          			new EffectInstance[] {new EffectInstance(ANTI_UNLUCK.get())}));
    public static final RegistryObject<Potion> SLOW_FALLING_CURE       = POTIONS.register("slow_falling_cure", () -> new Potion("slow_falling_cure",    	new EffectInstance[] {new EffectInstance(ANTI_SLOW_FALLING.get())}));
    
    public static void addBrewingRecipes() {
		ItemStack weakCure = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), WEAKNESS_CURE.get());
		BrewingRecipeRegistry.addRecipe(
				Ingredient.fromItems(Items.HONEY_BOTTLE),
				Ingredient.fromItems(Items.FERMENTED_SPIDER_EYE),
				weakCure);

		PotionBrewing.addMix(Potions.AWKWARD, BBItems.FOUR_LEAF_CLOVER.get(), Potions.LUCK);
		PotionBrewing.addMix(Potions.LUCK, Items.REDSTONE, LONG_LUCK.get());
		PotionBrewing.addMix(Potions.LUCK, Items.GLOWSTONE_DUST, STRONG_LUCK.get());
		PotionBrewing.addMix(Potions.LUCK, Items.FERMENTED_SPIDER_EYE, UNLUCK.get());
		PotionBrewing.addMix(UNLUCK.get(), Items.REDSTONE, LONG_UNLUCK.get());
		PotionBrewing.addMix(UNLUCK.get(), Items.GLOWSTONE_DUST, STRONG_UNLUCK.get());
		PotionBrewing.addMix(LONG_LUCK.get(), Items.FERMENTED_SPIDER_EYE, LONG_UNLUCK.get());
		PotionBrewing.addMix(STRONG_LUCK.get(), Items.FERMENTED_SPIDER_EYE, STRONG_UNLUCK.get());

		ItemStack placebo = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PLACEBO.get());
		BrewingRecipeRegistry.addRecipe(
				Ingredient.fromItems(Items.HONEY_BOTTLE),
				Ingredient.fromItems(Items.NETHER_WART),
				placebo);
		
		PotionBrewing.addMix(PLACEBO.get(), Items.GOLDEN_CARROT, NIGHT_VISION_CURE.get());
		PotionBrewing.addMix(NIGHT_VISION_CURE.get(), Items.FERMENTED_SPIDER_EYE, INVISIBILITY_CURE.get());
		PotionBrewing.addMix(PLACEBO.get(), Items.MAGMA_CREAM, FIRE_RESISTANCE_CURE.get());
		PotionBrewing.addMix(PLACEBO.get(), Items.RABBIT_FOOT, LEAPING_CURE.get());
		PotionBrewing.addMix(LEAPING_CURE.get(), Items.FERMENTED_SPIDER_EYE, SLOWNESS_CURE.get());
		PotionBrewing.addMix(SWIFTNESS_CURE.get(), Items.FERMENTED_SPIDER_EYE, SLOWNESS_CURE.get());
		PotionBrewing.addMix(PLACEBO.get(), Items.SUGAR, SWIFTNESS_CURE.get());
		PotionBrewing.addMix(PLACEBO.get(), Items.PUFFERFISH, WATER_BREATHING_CURE.get());
		PotionBrewing.addMix(PLACEBO.get(), Items.GHAST_TEAR, REGENERATION_CURE.get());
		PotionBrewing.addMix(PLACEBO.get(), Items.BLAZE_POWDER, STRENGTH_CURE.get());
		PotionBrewing.addMix(PLACEBO.get(), Items.PHANTOM_MEMBRANE, SLOW_FALLING_CURE.get());
		PotionBrewing.addMix(PLACEBO.get(), Items.SPIDER_EYE, POISON_CURE.get());
		PotionBrewing.addMix(PLACEBO.get(), BBItems.FOUR_LEAF_CLOVER.get(), LUCK_CURE.get());
		PotionBrewing.addMix(LUCK_CURE.get(), Items.FERMENTED_SPIDER_EYE, UNLUCK_CURE.get());
	}
}
