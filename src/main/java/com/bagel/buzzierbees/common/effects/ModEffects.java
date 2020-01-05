package com.bagel.buzzierbees.common.effects;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEffects {
    public static Effect ANTI_SPEED;
    public static Effect ANTI_SLOWNESS;
    public static Effect ANTI_HASTE;
    public static Effect ANTI_MINING_FATIGUE;
    public static Effect ANTI_STRENGTH;
    public static Effect ANTI_JUMP_BOOST;
    public static Effect ANTI_NAUSEA;
    public static Effect ANTI_REGENERATION;
    public static Effect ANTI_RESISTANCE;
    public static Effect ANTI_FIRE_RESISTANCE;
    public static Effect ANTI_WATER_BREATHING;
    public static Effect ANTI_INVISIBILITY;
    public static Effect ANTI_BLINDNESS;
    public static Effect ANTI_NIGHT_VISION;
    public static Effect ANTI_HUNGER;
    public static Effect ANTI_WEAKNESS;
    public static Effect ANTI_POISON;
    public static Effect ANTI_WITHER;
    public static Effect ANTI_HEALTH_BOOST;
    public static Effect ANTI_ABSORPTION;
    public static Effect ANTI_SATURATION;
    public static Effect ANTI_GLOWING;
    public static Effect ANTI_LEVITATION;
    public static Effect ANTI_LUCK;
    public static Effect ANTI_UNLUCK;
    public static Effect ANTI_SLOW_FALLING;
    public static Effect ANTI_BAD_OMEN;
    public static Effect ANTI_INSTANT_HEALTH;
    public static Effect ANTI_INSTANT_DAMAGE;
    public static Effect ANTI_HERO_OF_THE_VILLAGE;
    public static Effect ANTI_DOLPHINS_GRACE;
    public static Effect ANTI_CONDUIT_POWER;

    @SubscribeEvent
    public static void registerEffects(RegistryEvent.Register<Effect> event)
    {
        ANTI_SPEED              = registerEffect(new AntiEffect(EffectType.HARMFUL,     8605753,     new EffectInstance[]{new EffectInstance(Effects.SPEED)}),              "anti_speed");
        ANTI_SLOWNESS           = registerEffect(new AntiEffect(EffectType.BENEFICIAL,  10851198,    new EffectInstance[]{new EffectInstance(Effects.SLOWNESS)}),           "anti_slowness");
        ANTI_HASTE              = registerEffect(new AntiEffect(EffectType.HARMFUL,     2506684,     new EffectInstance[]{new EffectInstance(Effects.HASTE)}),              "anti_haste");
        ANTI_MINING_FATIGUE     = registerEffect(new AntiEffect(EffectType.BENEFICIAL,  0,           new EffectInstance[]{new EffectInstance(Effects.MINING_FATIGUE)}),     "anti_mining_fatigue");
        ANTI_STRENGTH           = registerEffect(new AntiEffect(EffectType.HARMFUL,     7134172,     new EffectInstance[]{new EffectInstance(Effects.STRENGTH)}),           "anti_strength");
        ANTI_INSTANT_HEALTH     = registerEffect(new AntiEffect(EffectType.HARMFUL,     0,           new EffectInstance[]{new EffectInstance(Effects.INSTANT_HEALTH)}),     "anti_instant_health");
        ANTI_INSTANT_DAMAGE     = registerEffect(new AntiEffect(EffectType.BENEFICIAL,  0,           new EffectInstance[]{new EffectInstance(Effects.INSTANT_DAMAGE)}),     "anti_instant_damage");
        ANTI_JUMP_BOOST         = registerEffect(new AntiEffect(EffectType.HARMFUL,     14483635,    new EffectInstance[]{new EffectInstance(Effects.JUMP_BOOST)}),         "anti_jump_boost");
        ANTI_NAUSEA             = registerEffect(new AntiEffect(EffectType.BENEFICIAL,  0,           new EffectInstance[]{new EffectInstance(Effects.NAUSEA)}),             "anti_nausea");
        ANTI_REGENERATION       = registerEffect(new AntiEffect(EffectType.HARMFUL,     3318612,     new EffectInstance[]{new EffectInstance(Effects.REGENERATION)}),       "anti_regeneration");
        ANTI_RESISTANCE         = registerEffect(new AntiEffect(EffectType.HARMFUL,     0,           new EffectInstance[]{new EffectInstance(Effects.RESISTANCE)}),         "anti_resistance");
        ANTI_FIRE_RESISTANCE    = registerEffect(new AntiEffect(EffectType.HARMFUL,     1795525,     new EffectInstance[]{new EffectInstance(Effects.FIRE_RESISTANCE)}),    "anti_fire_resistance");
        ANTI_WATER_BREATHING    = registerEffect(new AntiEffect(EffectType.HARMFUL,     13741414,    new EffectInstance[]{new EffectInstance(Effects.WATER_BREATHING)}),    "anti_water_breathing");
        ANTI_INVISIBILITY       = registerEffect(new AntiEffect(EffectType.HARMFUL,     8420461,     new EffectInstance[]{new EffectInstance(Effects.INVISIBILITY)}),       "anti_invisibility");
        ANTI_BLINDNESS          = registerEffect(new AntiEffect(EffectType.BENEFICIAL,  14737628,    new EffectInstance[]{new EffectInstance(Effects.BLINDNESS)}),          "anti_blindness");
        ANTI_NIGHT_VISION       = registerEffect(new AntiEffect(EffectType.HARMFUL,     14737502,    new EffectInstance[]{new EffectInstance(Effects.NIGHT_VISION)}),       "anti_night_vision");
        ANTI_HUNGER             = registerEffect(new AntiEffect(EffectType.BENEFICIAL,  0,           new EffectInstance[]{new EffectInstance(Effects.HUNGER)}),             "anti_hunger");
        ANTI_WEAKNESS           = registerEffect(new AntiEffect(EffectType.BENEFICIAL,  12038839,    new EffectInstance[]{new EffectInstance(Effects.WEAKNESS)}),           "anti_weakness");
        ANTI_POISON             = registerEffect(new AntiEffect(EffectType.BENEFICIAL,  11627726,    new EffectInstance[]{new EffectInstance(Effects.POISON)}),             "anti_poison");
        ANTI_WITHER             = registerEffect(new AntiEffect(EffectType.BENEFICIAL,  0,           new EffectInstance[]{new EffectInstance(Effects.WITHER)}),             "anti_wither");
        ANTI_HEALTH_BOOST       = registerEffect(new AntiEffect(EffectType.HARMFUL,     0,           new EffectInstance[]{new EffectInstance(Effects.HEALTH_BOOST)}),       "anti_health_boost");
        ANTI_ABSORPTION         = registerEffect(new AntiEffect(EffectType.HARMFUL,     0,           new EffectInstance[]{new EffectInstance(Effects.ABSORPTION)}),         "anti_absorption");
        ANTI_SATURATION         = registerEffect(new AntiEffect(EffectType.HARMFUL,     0,           new EffectInstance[]{new EffectInstance(Effects.SATURATION)}),         "anti_saturation");
        ANTI_GLOWING            = registerEffect(new AntiEffect(EffectType.HARMFUL,     0,           new EffectInstance[]{new EffectInstance(Effects.GLOWING)}),            "anti_glowing");
        ANTI_LEVITATION         = registerEffect(new AntiEffect(EffectType.BENEFICIAL,  0,           new EffectInstance[]{new EffectInstance(Effects.LEVITATION)}),         "anti_levitation");
        ANTI_LUCK               = registerEffect(new AntiEffect(EffectType.HARMFUL,     13395711,    new EffectInstance[]{new EffectInstance(Effects.LUCK)}),               "anti_luck");
        ANTI_UNLUCK             = registerEffect(new AntiEffect(EffectType.BENEFICIAL,  4152242,     new EffectInstance[]{new EffectInstance(Effects.UNLUCK)}),             "anti_unluck");
        ANTI_SLOW_FALLING       = registerEffect(new AntiEffect(EffectType.HARMFUL,     4142,        new EffectInstance[]{new EffectInstance(Effects.SLOW_FALLING)}),       "anti_slow_falling");
        ANTI_CONDUIT_POWER      = registerEffect(new AntiEffect(EffectType.HARMFUL,     0,           new EffectInstance[]{new EffectInstance(Effects.CONDUIT_POWER)}),      "anti_conduit_power");
        ANTI_DOLPHINS_GRACE     = registerEffect(new AntiEffect(EffectType.HARMFUL,     0,           new EffectInstance[]{new EffectInstance(Effects.DOLPHINS_GRACE)}),     "anti_dolphins_grace");
        ANTI_BAD_OMEN           = registerEffect(new AntiEffect(EffectType.BENEFICIAL,  0,           new EffectInstance[]{new EffectInstance(Effects.BAD_OMEN)}),           "anti_bad_omen");
        ANTI_HERO_OF_THE_VILLAGE= registerEffect(new AntiEffect(EffectType.HARMFUL,     0,           new EffectInstance[]{new EffectInstance(Effects.HERO_OF_THE_VILLAGE)}),"anti_hero_of_the_village");

    }

    public static Effect registerEffect(Effect effect, String name)
    {
        effect.setRegistryName(name);
        ForgeRegistries.POTIONS.register(effect);
        return effect;
    }
}
