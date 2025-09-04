package com.teamabnormals.buzzier_bees.core.registry;

import com.teamabnormals.blueprint.core.util.item.CreativeModeTabContentsPopulator;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import com.teamabnormals.buzzier_bees.common.item.BugBottleItem;
import com.teamabnormals.buzzier_bees.common.item.CuringItem;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import com.teamabnormals.buzzier_bees.core.other.tags.BBBannerPatternTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BannerPatternItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;

import static net.minecraft.world.item.CreativeModeTabs.*;
import static net.minecraft.world.item.crafting.Ingredient.of;

public class BBItems {
	public static final ItemSubRegistryHelper ITEMS = BuzzierBees.REGISTRY_HELPER.getItemSubHelper();

	public static final DeferredItem<CuringItem> HONEY_APPLE = ITEMS.createItem("honey_apple", () -> new CuringItem(new Item.Properties().food(BBFoods.HONEY_APPLE), MobEffects.LEVITATION, MobEffects.POISON));
	public static final DeferredItem<CuringItem> HONEY_BREAD = ITEMS.createItem("honey_bread", () -> new CuringItem(new Item.Properties().food(BBFoods.HONEY_BREAD), MobEffects.MOVEMENT_SLOWDOWN, MobEffects.POISON));
	public static final DeferredItem<CuringItem> GLAZED_PORKCHOP = ITEMS.createItem("glazed_porkchop", () -> new CuringItem(new Item.Properties().food(BBFoods.GLAZED_PORKCHOP), MobEffects.DIG_SLOWDOWN, MobEffects.POISON));

	public static final DeferredItem<BugBottleItem> BOTTLE_OF_SILVERFISH = ITEMS.createItem("silverfish_bottle", () -> new BugBottleItem(EntityType.SILVERFISH, new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(1)));
	public static final DeferredItem<BugBottleItem> BOTTLE_OF_ENDERMITE = ITEMS.createItem("endermite_bottle", () -> new BugBottleItem(EntityType.ENDERMITE, new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(1)));
	public static final DeferredItem<BugBottleItem> BOTTLE_OF_BEE = ITEMS.createItem("bee_bottle", () -> new BugBottleItem(EntityType.BEE, new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(1)));

	public static final DeferredItem<Item> FOUR_LEAF_CLOVER = ITEMS.createItem("four_leaf_clover", () -> new Item(new Item.Properties()));
	public static final DeferredItem<BannerPatternItem> HONEYCOMB_BANNER_PATTERN = ITEMS.createItem("honeycomb_banner_pattern", () -> new BannerPatternItem(BBBannerPatternTags.PATTERN_ITEM_HONEYCOMB, new Item.Properties().stacksTo(1)));

	public static final DeferredItem<DeferredSpawnEggItem> GRIZZLY_BEAR_SPAWN_EGG = ITEMS.createSpawnEggItem("grizzly_bear", BBEntityTypes.GRIZZLY_BEAR::get, 0x523021, 0x926A4B);
	public static final DeferredItem<DeferredSpawnEggItem> MOOBLOOM_SPAWN_EGG = ITEMS.createSpawnEggItem("moobloom", BBEntityTypes.MOOBLOOM::get, 0xDBA436, 0xDCDCDC);

	public static void setupTabEditors() {
		CreativeModeTabContentsPopulator.mod(BuzzierBees.MOD_ID)
				.tab(FOOD_AND_DRINKS)
				.addItemsAfter(of(Items.APPLE), HONEY_APPLE)
				.addItemsAfter(of(Items.BREAD), HONEY_BREAD)
				.addItemsAfter(of(Items.COOKED_PORKCHOP), GLAZED_PORKCHOP)
				.tab(INGREDIENTS)
				.addItemsAfter(of(Items.GLOBE_BANNER_PATTERN), HONEYCOMB_BANNER_PATTERN)
				.addItemsAfter(of(Items.PHANTOM_MEMBRANE), FOUR_LEAF_CLOVER)
				.tab(TOOLS_AND_UTILITIES)
				.addItemsBefore(of(Items.FISHING_ROD), () -> Items.GLASS_BOTTLE, BOTTLE_OF_BEE, BOTTLE_OF_SILVERFISH, BOTTLE_OF_ENDERMITE)
				.tab(SPAWN_EGGS)
				.addSpawnEggsAlphabetically(MOOBLOOM_SPAWN_EGG);
	}

	public static class BBFoods {
		public static final FoodProperties HONEY_APPLE = new FoodProperties.Builder().nutrition(5).saturationModifier(0.4F).build();
		public static final FoodProperties HONEY_BREAD = new FoodProperties.Builder().nutrition(6).saturationModifier(0.8F).build();
		public static final FoodProperties GLAZED_PORKCHOP = new FoodProperties.Builder().nutrition(9).saturationModifier(0.8F).build();
	}
}