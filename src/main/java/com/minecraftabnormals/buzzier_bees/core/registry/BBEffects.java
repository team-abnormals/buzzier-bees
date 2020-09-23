package com.minecraftabnormals.buzzier_bees.core.registry;

import com.minecraftabnormals.buzzier_bees.core.BuzzierBees;

import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionBrewing;
import net.minecraft.potion.Potions;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BBEffects {
	public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTION_TYPES, BuzzierBees.MODID);
   
	public static final RegistryObject<Potion> LONG_LUCK     = POTIONS.register("long_luck", () -> new Potion(new EffectInstance[] {new EffectInstance(Effects.LUCK, 9600)}));
	public static final RegistryObject<Potion> STRONG_LUCK   = POTIONS.register("strong_luck", () -> new Potion(new EffectInstance[] {new EffectInstance(Effects.LUCK, 3600, 1)}));
    public static final RegistryObject<Potion> UNLUCK        = POTIONS.register("unluck", () -> new Potion(new EffectInstance[] {new EffectInstance(Effects.UNLUCK, 3600)}));
    public static final RegistryObject<Potion> LONG_UNLUCK   = POTIONS.register("long_unluck", () -> new Potion(new EffectInstance[] {new EffectInstance(Effects.UNLUCK, 9600)}));
    public static final RegistryObject<Potion> STRONG_UNLUCK = POTIONS.register("strong_unluck", () -> new Potion(new EffectInstance[] {new EffectInstance(Effects.UNLUCK, 3600, 1)}));
    

    public static void registerRecipes() {
		PotionBrewing.addMix(Potions.AWKWARD, BBItems.FOUR_LEAF_CLOVER.get(), Potions.LUCK);
		PotionBrewing.addMix(Potions.LUCK, Items.REDSTONE, LONG_LUCK.get());
		PotionBrewing.addMix(Potions.LUCK, Items.GLOWSTONE_DUST, STRONG_LUCK.get());
		PotionBrewing.addMix(Potions.LUCK, Items.FERMENTED_SPIDER_EYE, UNLUCK.get());
		PotionBrewing.addMix(UNLUCK.get(), Items.REDSTONE, LONG_UNLUCK.get());
		PotionBrewing.addMix(UNLUCK.get(), Items.GLOWSTONE_DUST, STRONG_UNLUCK.get());
		PotionBrewing.addMix(LONG_LUCK.get(), Items.FERMENTED_SPIDER_EYE, LONG_UNLUCK.get());
		PotionBrewing.addMix(STRONG_LUCK.get(), Items.FERMENTED_SPIDER_EYE, STRONG_UNLUCK.get());
	}
}
