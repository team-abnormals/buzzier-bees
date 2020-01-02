package com.bagel.buzzierbees.common.potions;

import com.bagel.buzzierbees.common.effects.ModEffects;
import net.minecraft.potion.*;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModPotions {
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
    public static Potion SLOW_FALLING_CURE;
    public static Potion HASTE_CURE;
    public static Potion MINING_FATIGUE_CURE;
    public static Potion NAUSEA_CURE;
    public static Potion BAD_OMEN_CURE;

    @SubscribeEvent
    public static void registerPotions(RegistryEvent.Register<Potion> event)
    {
    	PLACEBO = registerPotion(new Potion("placebo"), "placebo");
        NIGHT_VISION_CURE = registerPotion(new Potion("night_vision_cure", new EffectInstance(ModEffects.ANTI_NIGHT_VISION)), "night_vision_cure");
        INVISIBILITY_CURE = registerPotion(new Potion("invisibility_cure", new EffectInstance(ModEffects.ANTI_INVISIBILITY)), "invisibility_cure");
        LEAPING_CURE = registerPotion(new Potion("leaping_cure", new EffectInstance(ModEffects.ANTI_JUMP_BOOST)), "leaping_cure");
        FIRE_RESISTANCE_CURE = registerPotion(new Potion("fire_resistance_cure", new EffectInstance(ModEffects.ANTI_FIRE_RESISTANCE)), "fire_resistance_cure");
        SWIFTNESS_CURE = registerPotion(new Potion("speed_cure", new EffectInstance(ModEffects.ANTI_SPEED)), "swiftness_cure");
        SLOWNESS_CURE = registerPotion(new Potion("slowness_cure", new EffectInstance(ModEffects.ANTI_SLOWNESS)), "slowness_cure");
        WATER_BREATHING_CURE = registerPotion(new Potion("water_breathing_cure", new EffectInstance(ModEffects.ANTI_WATER_BREATHING)), "water_breathing_cure");
        REGENERATION_CURE = registerPotion(new Potion("regeneration_cure", new EffectInstance(ModEffects.ANTI_REGENERATION)), "regeneration_cure");
        STRENGTH_CURE = registerPotion(new Potion("strength_cure", new EffectInstance(ModEffects.ANTI_STRENGTH)), "strength_cure");
        WEAKNESS_CURE = registerPotion(new Potion("weakness_cure", new EffectInstance(ModEffects.ANTI_WEAKNESS)), "weakness_cure");
        LUCK_CURE = registerPotion(new Potion("luck_cure", new EffectInstance(ModEffects.ANTI_LUCK)), "luck_cure");
        SLOW_FALLING_CURE = registerPotion(new Potion("slow_falling_cure", new EffectInstance(ModEffects.ANTI_SLOW_FALLING)), "slow_falling_cure");
        HASTE_CURE = registerPotion(new Potion("haste_cure", new EffectInstance(ModEffects.ANTI_SLOWNESS)), "haste_cure");
        MINING_FATIGUE_CURE = registerPotion(new Potion("mining_fatigue_cure", new EffectInstance(ModEffects.ANTI_MINING_FATIGUE)), "mining_fatigue_cure");
        NAUSEA_CURE = registerPotion(new Potion("nausea_cure", new EffectInstance(ModEffects.ANTI_NAUSEA)), "nausea_cure");
        BAD_OMEN_CURE = registerPotion(new Potion("bad_omen_cure", new EffectInstance(ModEffects.ANTI_BAD_OMEN)), "bad_omen_cure");
    }

    public static Potion registerPotion(Potion potion, String name)
    {
        potion.setRegistryName(name);
        ForgeRegistries.POTION_TYPES.register(potion);
        return potion;
    }
}
