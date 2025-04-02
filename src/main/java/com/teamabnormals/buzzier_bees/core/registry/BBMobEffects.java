package com.teamabnormals.buzzier_bees.core.registry;

import com.teamabnormals.blueprint.common.effect.BlueprintMobEffect;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(modid = BuzzierBees.MOD_ID)
public class BBMobEffects {
	public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, BuzzierBees.MOD_ID);
	public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(BuiltInRegistries.POTION, BuzzierBees.MOD_ID);

	public static final DeferredHolder<MobEffect, BlueprintMobEffect> SUNNY = MOB_EFFECTS.register("sunny", () -> new BlueprintMobEffect(MobEffectCategory.BENEFICIAL, 0xFFE451));

	public static final DeferredHolder<Potion, Potion> LONG_LUCK = POTIONS.register("long_luck", () -> new Potion(new MobEffectInstance(MobEffects.LUCK, 9600)));
	public static final DeferredHolder<Potion, Potion> STRONG_LUCK = POTIONS.register("strong_luck", () -> new Potion(new MobEffectInstance(MobEffects.LUCK, 3600, 1)));
	public static final DeferredHolder<Potion, Potion> UNLUCK = POTIONS.register("unluck", () -> new Potion(new MobEffectInstance(MobEffects.UNLUCK, 3600)));
	public static final DeferredHolder<Potion, Potion> LONG_UNLUCK = POTIONS.register("long_unluck", () -> new Potion(new MobEffectInstance(MobEffects.UNLUCK, 9600)));
	public static final DeferredHolder<Potion, Potion> STRONG_UNLUCK = POTIONS.register("strong_unluck", () -> new Potion(new MobEffectInstance(MobEffects.UNLUCK, 3600, 1)));

	@SubscribeEvent
	public static void registerBrewingRecipes(RegisterBrewingRecipesEvent event) {
		event.getBuilder().addMix(Potions.AWKWARD, BBItems.FOUR_LEAF_CLOVER.get(), Potions.LUCK);
		event.getBuilder().addMix(Potions.LUCK, Items.REDSTONE, LONG_LUCK);
		event.getBuilder().addMix(Potions.LUCK, Items.GLOWSTONE_DUST, STRONG_LUCK);
		event.getBuilder().addMix(Potions.LUCK, Items.FERMENTED_SPIDER_EYE, UNLUCK);
		event.getBuilder().addMix(UNLUCK, Items.REDSTONE, LONG_UNLUCK);
		event.getBuilder().addMix(UNLUCK, Items.GLOWSTONE_DUST, STRONG_UNLUCK);
		event.getBuilder().addMix(LONG_LUCK, Items.FERMENTED_SPIDER_EYE, LONG_UNLUCK);
		event.getBuilder().addMix(STRONG_LUCK, Items.FERMENTED_SPIDER_EYE, STRONG_UNLUCK);
	}
}
