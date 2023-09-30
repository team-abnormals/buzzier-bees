package com.teamabnormals.buzzier_bees.core.registry;

import com.teamabnormals.blueprint.common.item.BlueprintBannerPatternItem;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import com.teamabnormals.buzzier_bees.common.item.BeeBottleItem;
import com.teamabnormals.buzzier_bees.common.item.BugBottleItem;
import com.teamabnormals.buzzier_bees.common.item.CuringItem;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import com.teamabnormals.buzzier_bees.core.other.tags.BBBannerPatternTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class BBItems {
	public static final ItemSubRegistryHelper HELPER = BuzzierBees.REGISTRY_HELPER.getItemSubHelper();

	public static final RegistryObject<Item> HONEY_APPLE = HELPER.createItem("honey_apple", () -> new CuringItem(new Item.Properties().food(BBFoods.HONEY_APPLE).tab(CreativeModeTab.TAB_FOOD), new MobEffectInstance(MobEffects.LEVITATION), new MobEffectInstance(MobEffects.POISON)));
	public static final RegistryObject<Item> HONEY_BREAD = HELPER.createItem("honey_bread", () -> new CuringItem(new Item.Properties().food(BBFoods.HONEY_BREAD).tab(CreativeModeTab.TAB_FOOD), new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN), new MobEffectInstance(MobEffects.POISON)));
	public static final RegistryObject<Item> GLAZED_PORKCHOP = HELPER.createItem("glazed_porkchop", () -> new CuringItem(new Item.Properties().food(BBFoods.GLAZED_PORKCHOP).tab(CreativeModeTab.TAB_FOOD), new MobEffectInstance(MobEffects.DIG_SLOWDOWN), new MobEffectInstance(MobEffects.POISON)));

	public static final RegistryObject<Item> BOTTLE_OF_SILVERFISH = HELPER.createItem("silverfish_bottle", () -> new BugBottleItem(EntityType.SILVERFISH, new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(1).tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> BOTTLE_OF_ENDERMITE = HELPER.createItem("endermite_bottle", () -> new BugBottleItem(EntityType.ENDERMITE, new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(1).tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> BOTTLE_OF_BEE = HELPER.createItem("bee_bottle", () -> new BeeBottleItem(EntityType.BEE, new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(1).tab(CreativeModeTab.TAB_MISC)));

	public static final RegistryObject<Item> FOUR_LEAF_CLOVER = HELPER.createItem("four_leaf_clover", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_BREWING)));
	public static final RegistryObject<Item> HONEYCOMB_BANNER_PATTERN = HELPER.createItem("honeycomb_banner_pattern", () -> new BlueprintBannerPatternItem(BBBannerPatternTags.PATTERN_ITEM_HONEYCOMB, new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1)));

	// public static final RegistryObject<ForgeSpawnEggItem> GRIZZLY_BEAR_SPAWN_EGG = HELPER.createSpawnEggItem("grizzly_bear", BBEntityTypes.GRIZZLY_BEAR::get, 0x523021, 0x926A4B);
	public static final RegistryObject<ForgeSpawnEggItem> MOOBLOOM_SPAWN_EGG = HELPER.createSpawnEggItem("moobloom", BBEntityTypes.MOOBLOOM::get, 0xDBA436, 0xDCDCDC);

	public static class BBFoods {
		public static final FoodProperties HONEY_APPLE = new FoodProperties.Builder().nutrition(5).saturationMod(0.4F).build();
		public static final FoodProperties HONEY_BREAD = new FoodProperties.Builder().nutrition(6).saturationMod(0.8F).build();
		public static final FoodProperties GLAZED_PORKCHOP = new FoodProperties.Builder().nutrition(9).saturationMod(0.8F).build();
	}
}