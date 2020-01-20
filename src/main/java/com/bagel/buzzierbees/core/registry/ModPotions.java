package com.bagel.buzzierbees.core.registry;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.*;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModPotions {
    //Luck and Bad Luck Potions
    public static Potion LONG_LUCK;
    public static Potion STRONG_LUCK;
    public static Potion UNLUCK;
    public static Potion LONG_UNLUCK;
    public static Potion STRONG_UNLUCK;

    //Cures
	public static Potion PLACEBO;
    public static Potion NIGHT_VISION_CURE;
    public static Potion INVISIBILITY_CURE;
    public static Potion LEAPING_CURE;
    public static Potion FIRE_RESISTANCE_CURE;
    public static Potion SWIFTNESS_CURE;
    public static Potion SLOWNESS_CURE;
    public static Potion WATER_BREATHING_CURE;
    public static Potion REGENERATION_CURE;
    public static Potion STRENGTH_CURE;
    public static Potion WEAKNESS_CURE;
    public static Potion LUCK_CURE;
    public static Potion UNLUCK_CURE;
    public static Potion SLOW_FALLING_CURE;
    public static Potion POISON_CURE;

    @SubscribeEvent
    public static void registerPotions(RegistryEvent.Register<Potion> event)
    {
        //Luck and Bad Luck Potions
        LONG_LUCK       = registerPotion(new Potion(new EffectInstance[] {new EffectInstance(Effects.LUCK,      9600)}),   "long_luck");
        STRONG_LUCK     = registerPotion(new Potion(new EffectInstance[] {new EffectInstance(Effects.LUCK,      3600, 1)}),"strong_luck");
        UNLUCK        = registerPotion(new Potion(new EffectInstance[] {new EffectInstance(Effects.UNLUCK,    3600)}),   "unluck");
        LONG_UNLUCK   = registerPotion(new Potion(new EffectInstance[] {new EffectInstance(Effects.UNLUCK,    9600)}),   "long_unluck");
        STRONG_UNLUCK = registerPotion(new Potion(new EffectInstance[] {new EffectInstance(Effects.UNLUCK,    3600, 1)}),"strong_unluck");
        
        //Cures
    	PLACEBO                 = registerPotion(new Potion("placebo", new EffectInstance[0]), "placebo");
        NIGHT_VISION_CURE       = registerPotion(new Potion("night_vision_cure",    new EffectInstance[] {new EffectInstance(ModEffects.ANTI_NIGHT_VISION)}),       "night_vision_cure");
        INVISIBILITY_CURE       = registerPotion(new Potion("invisibility_cure",    new EffectInstance[] {new EffectInstance(ModEffects.ANTI_INVISIBILITY)}),       "invisibility_cure");
        LEAPING_CURE            = registerPotion(new Potion("jump_boost_cure",      new EffectInstance[] {new EffectInstance(ModEffects.ANTI_JUMP_BOOST)}),         "jump_boost_cure");
        FIRE_RESISTANCE_CURE    = registerPotion(new Potion("fire_resistance_cure", new EffectInstance[] {new EffectInstance(ModEffects.ANTI_FIRE_RESISTANCE)}),    "fire_resistance_cure");
        SWIFTNESS_CURE          = registerPotion(new Potion("speed_cure",           new EffectInstance[] {new EffectInstance(ModEffects.ANTI_SPEED)}),              "speed_cure");
        SLOWNESS_CURE           = registerPotion(new Potion("slowness_cure",        new EffectInstance[] {new EffectInstance(ModEffects.ANTI_SLOWNESS)}),           "slowness_cure");
        WATER_BREATHING_CURE    = registerPotion(new Potion("water_breathing_cure", new EffectInstance[] {new EffectInstance(ModEffects.ANTI_WATER_BREATHING)}),    "water_breathing_cure");
        POISON_CURE             = registerPotion(new Potion("poison_cure",          new EffectInstance[] {new EffectInstance(ModEffects.ANTI_POISON)}),             "poison_cure");
        REGENERATION_CURE       = registerPotion(new Potion("regeneration_cure",    new EffectInstance[] {new EffectInstance(ModEffects.ANTI_REGENERATION)}),       "regeneration_cure");
        STRENGTH_CURE           = registerPotion(new Potion("strength_cure",        new EffectInstance[] {new EffectInstance(ModEffects.ANTI_STRENGTH)}),           "strength_cure");
        WEAKNESS_CURE           = registerPotion(new Potion("weakness_cure",        new EffectInstance[] {new EffectInstance(ModEffects.ANTI_WEAKNESS)}),           "weakness_cure");
        LUCK_CURE               = registerPotion(new Potion("luck_cure",            new EffectInstance[] {new EffectInstance(ModEffects.ANTI_LUCK)}),               "luck_cure");
        UNLUCK_CURE           = registerPotion(new Potion("unluck_cure",          new EffectInstance[] {new EffectInstance(ModEffects.ANTI_UNLUCK)}),             "unluck_cure");
        SLOW_FALLING_CURE       = registerPotion(new Potion("slow_falling_cure",    new EffectInstance[] {new EffectInstance(ModEffects.ANTI_SLOW_FALLING)}),       "slow_falling_cure");

        //Cut Cures
        //INSTANT_HEALTH_CURE   = registerPotion(new Potion("instant_health_cure", new EffectInstance[] {new EffectInstance(ModEffects.ANTI_INSTANT_HEALTH)}), "instant_health_cure");
        //INSTANT_DAMAGE_CURE   = registerPotion(new Potion("instant_damage_cure", new EffectInstance[] {new EffectInstance(ModEffects.ANTI_INSTANT_DAMAGE)}), "instant_damage_cure");
        //HASTE_CURE            = registerPotion(new Potion("haste_cure", new EffectInstance[] {new EffectInstance(ModEffects.ANTI_SLOWNESS)}), "haste_cure");
        //MINING_FATIGUE_CURE   = registerPotion(new Potion("mining_fatigue_cure", new EffectInstance[] {new EffectInstance(ModEffects.ANTI_MINING_FATIGUE)}), "mining_fatigue_cure");
        //NAUSEA_CURE           = registerPotion(new Potion("nausea_cure", new EffectInstance[] {new EffectInstance(ModEffects.ANTI_NAUSEA)}), "nausea_cure");
        //BAD_OMEN_CURE         = registerPotion(new Potion("bad_omen_cure", new EffectInstance[] {new EffectInstance(ModEffects.ANTI_BAD_OMEN)}), "bad_omen_cure");
    }

    public static Potion registerPotion(Potion potion, String name)
    {
        potion.setRegistryName(name);
        ForgeRegistries.POTION_TYPES.register(potion);
        return potion;
    }
    
    public static void addBrewingRecipes() {
		
		ItemStack weakCure = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), ModPotions.WEAKNESS_CURE);
		BrewingRecipeRegistry.addRecipe(
				Ingredient.fromItems(Items.field_226638_pX_),
				Ingredient.fromItems(Items.FERMENTED_SPIDER_EYE),
				weakCure);

		PotionBrewing.addMix(Potions.AWKWARD, ModItems.FOUR_LEAF_CLOVER.get(), Potions.LUCK);
		PotionBrewing.addMix(Potions.LUCK, Items.REDSTONE, ModPotions.LONG_LUCK);
		PotionBrewing.addMix(Potions.LUCK, Items.GLOWSTONE_DUST, ModPotions.STRONG_LUCK);
		PotionBrewing.addMix(Potions.LUCK, Items.FERMENTED_SPIDER_EYE, ModPotions.UNLUCK);
		PotionBrewing.addMix(ModPotions.UNLUCK, Items.REDSTONE, ModPotions.LONG_UNLUCK);
		PotionBrewing.addMix(ModPotions.UNLUCK, Items.GLOWSTONE_DUST, ModPotions.STRONG_UNLUCK);
		PotionBrewing.addMix(ModPotions.LONG_LUCK, Items.FERMENTED_SPIDER_EYE, ModPotions.LONG_UNLUCK);
		PotionBrewing.addMix(ModPotions.STRONG_LUCK, Items.FERMENTED_SPIDER_EYE, ModPotions.STRONG_UNLUCK);

		//Cures Brewing
		ItemStack placebo = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), ModPotions.PLACEBO);
		BrewingRecipeRegistry.addRecipe(
				Ingredient.fromItems(Items.field_226638_pX_),
				Ingredient.fromItems(Items.NETHER_WART),
				placebo);
		PotionBrewing.addMix(ModPotions.PLACEBO, Items.GOLDEN_CARROT, ModPotions.NIGHT_VISION_CURE);
		PotionBrewing.addMix(ModPotions.NIGHT_VISION_CURE, Items.FERMENTED_SPIDER_EYE, ModPotions.INVISIBILITY_CURE);
		PotionBrewing.addMix(ModPotions.PLACEBO, Items.MAGMA_CREAM, ModPotions.FIRE_RESISTANCE_CURE);
		PotionBrewing.addMix(ModPotions.PLACEBO, Items.RABBIT_FOOT, ModPotions.LEAPING_CURE);
		PotionBrewing.addMix(ModPotions.LEAPING_CURE, Items.FERMENTED_SPIDER_EYE, ModPotions.SLOWNESS_CURE);
		PotionBrewing.addMix(ModPotions.SWIFTNESS_CURE, Items.FERMENTED_SPIDER_EYE, ModPotions.SLOWNESS_CURE);
		PotionBrewing.addMix(ModPotions.PLACEBO, Items.SUGAR, ModPotions.SWIFTNESS_CURE);
		PotionBrewing.addMix(ModPotions.PLACEBO, Items.PUFFERFISH, ModPotions.WATER_BREATHING_CURE);
		PotionBrewing.addMix(ModPotions.PLACEBO, Items.GHAST_TEAR, ModPotions.REGENERATION_CURE);
		PotionBrewing.addMix(ModPotions.PLACEBO, Items.BLAZE_POWDER, ModPotions.STRENGTH_CURE);
		PotionBrewing.addMix(ModPotions.PLACEBO, Items.PHANTOM_MEMBRANE, ModPotions.SLOW_FALLING_CURE);
		PotionBrewing.addMix(ModPotions.PLACEBO, Items.SPIDER_EYE, ModPotions.POISON_CURE);
		PotionBrewing.addMix(ModPotions.PLACEBO, ModItems.FOUR_LEAF_CLOVER.get(), ModPotions.LUCK_CURE);
		PotionBrewing.addMix(ModPotions.LUCK_CURE, Items.FERMENTED_SPIDER_EYE, ModPotions.UNLUCK_CURE);
	}
}
