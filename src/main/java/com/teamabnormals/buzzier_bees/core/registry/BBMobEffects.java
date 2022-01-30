package com.teamabnormals.buzzier_bees.core.registry;

import com.teamabnormals.blueprint.common.effect.BlueprintMobEffect;
import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class BBMobEffects {
	public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, BuzzierBees.MOD_ID);
	public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, BuzzierBees.MOD_ID);

	public static final RegistryObject<MobEffect> SUNNY = MOB_EFFECTS.register("sunny", () -> new BlueprintMobEffect(MobEffectCategory.BENEFICIAL, 0xFFE451));

	public static final RegistryObject<Potion> LONG_LUCK = POTIONS.register("long_luck", () -> new Potion(new MobEffectInstance(MobEffects.LUCK, 9600)));
	public static final RegistryObject<Potion> STRONG_LUCK = POTIONS.register("strong_luck", () -> new Potion(new MobEffectInstance(MobEffects.LUCK, 3600, 1)));
	public static final RegistryObject<Potion> UNLUCK = POTIONS.register("unluck", () -> new Potion(new MobEffectInstance(MobEffects.UNLUCK, 3600)));
	public static final RegistryObject<Potion> LONG_UNLUCK = POTIONS.register("long_unluck", () -> new Potion(new MobEffectInstance(MobEffects.UNLUCK, 9600)));
	public static final RegistryObject<Potion> STRONG_UNLUCK = POTIONS.register("strong_unluck", () -> new Potion(new MobEffectInstance(MobEffects.UNLUCK, 3600, 1)));

	public static void registerRecipes() {
		DataUtil.addMix(Potions.AWKWARD, BBItems.FOUR_LEAF_CLOVER.get(), Potions.LUCK);
		DataUtil.addMix(Potions.LUCK, Items.REDSTONE, LONG_LUCK.get());
		DataUtil.addMix(Potions.LUCK, Items.GLOWSTONE_DUST, STRONG_LUCK.get());
		DataUtil.addMix(Potions.LUCK, Items.FERMENTED_SPIDER_EYE, UNLUCK.get());
		DataUtil.addMix(UNLUCK.get(), Items.REDSTONE, LONG_UNLUCK.get());
		DataUtil.addMix(UNLUCK.get(), Items.GLOWSTONE_DUST, STRONG_UNLUCK.get());
		DataUtil.addMix(LONG_LUCK.get(), Items.FERMENTED_SPIDER_EYE, LONG_UNLUCK.get());
		DataUtil.addMix(STRONG_LUCK.get(), Items.FERMENTED_SPIDER_EYE, STRONG_UNLUCK.get());
	}
}
