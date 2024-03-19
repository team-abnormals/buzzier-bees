package com.teamabnormals.buzzier_bees.core.registry;

import com.teamabnormals.blueprint.core.util.item.CreativeModeTabContentsPopulator;
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
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.RegistryObject;

import static com.teamabnormals.blueprint.core.util.item.ItemStackUtil.is;
import static net.minecraft.world.item.CreativeModeTabs.*;
import static net.minecraft.world.item.crafting.Ingredient.of;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class BBItems {
	public static final ItemSubRegistryHelper HELPER = BuzzierBees.REGISTRY_HELPER.getItemSubHelper();

	public static final RegistryObject<Item> HONEY_APPLE = HELPER.createItem("honey_apple", () -> new CuringItem(new Item.Properties().food(BBFoods.HONEY_APPLE), new MobEffectInstance(MobEffects.LEVITATION), new MobEffectInstance(MobEffects.POISON)));
	public static final RegistryObject<Item> HONEY_BREAD = HELPER.createItem("honey_bread", () -> new CuringItem(new Item.Properties().food(BBFoods.HONEY_BREAD), new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN), new MobEffectInstance(MobEffects.POISON)));
	public static final RegistryObject<Item> GLAZED_PORKCHOP = HELPER.createItem("glazed_porkchop", () -> new CuringItem(new Item.Properties().food(BBFoods.GLAZED_PORKCHOP), new MobEffectInstance(MobEffects.DIG_SLOWDOWN), new MobEffectInstance(MobEffects.POISON)));

	public static final RegistryObject<Item> BOTTLE_OF_SILVERFISH = HELPER.createItem("silverfish_bottle", () -> new BugBottleItem(EntityType.SILVERFISH, new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(1)));
	public static final RegistryObject<Item> BOTTLE_OF_ENDERMITE = HELPER.createItem("endermite_bottle", () -> new BugBottleItem(EntityType.ENDERMITE, new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(1)));
	public static final RegistryObject<Item> BOTTLE_OF_BEE = HELPER.createItem("bee_bottle", () -> new BeeBottleItem(EntityType.BEE, new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(1)));

	public static final RegistryObject<Item> FOUR_LEAF_CLOVER = HELPER.createItem("four_leaf_clover", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> HONEYCOMB_BANNER_PATTERN = HELPER.createItem("honeycomb_banner_pattern", () -> new BannerPatternItem(BBBannerPatternTags.PATTERN_ITEM_HONEYCOMB, new Item.Properties().stacksTo(1)));

	public static final RegistryObject<ForgeSpawnEggItem> GRIZZLY_BEAR_SPAWN_EGG = HELPER.createSpawnEggItem("grizzly_bear", BBEntityTypes.GRIZZLY_BEAR::get, 0x523021, 0x926A4B);
	public static final RegistryObject<ForgeSpawnEggItem> MOOBLOOM_SPAWN_EGG = HELPER.createSpawnEggItem("moobloom", BBEntityTypes.MOOBLOOM::get, 0xDBA436, 0xDCDCDC);

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
				.addItemsAlphabetically(is(SpawnEggItem.class), MOOBLOOM_SPAWN_EGG);
	}

	public static class BBFoods {
		public static final FoodProperties HONEY_APPLE = new FoodProperties.Builder().nutrition(5).saturationMod(0.4F).build();
		public static final FoodProperties HONEY_BREAD = new FoodProperties.Builder().nutrition(6).saturationMod(0.8F).build();
		public static final FoodProperties GLAZED_PORKCHOP = new FoodProperties.Builder().nutrition(9).saturationMod(0.8F).build();
	}
}