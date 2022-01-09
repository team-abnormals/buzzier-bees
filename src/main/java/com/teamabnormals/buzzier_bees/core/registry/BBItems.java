package com.teamabnormals.buzzier_bees.core.registry;

import com.teamabnormals.blueprint.common.item.BlueprintBannerPatternItem;
import com.teamabnormals.blueprint.common.item.BlueprintSpawnEggItem;
import com.teamabnormals.blueprint.core.api.banner.BannerManager;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import com.teamabnormals.buzzier_bees.common.items.*;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BBItems {
	public static final ItemSubRegistryHelper HELPER = BuzzierBees.REGISTRY_HELPER.getItemSubHelper();

	public static final RegistryObject<Item> HONEY_APPLE = HELPER.createItem("honey_apple", () -> new CuringItem(new Item.Properties().food(Foods.HONEY_APPLE).tab(CreativeModeTab.TAB_FOOD), new MobEffectInstance(MobEffects.LEVITATION), new MobEffectInstance(MobEffects.POISON)));
	public static final RegistryObject<Item> HONEY_BREAD = HELPER.createItem("honey_bread", () -> new CuringItem(new Item.Properties().food(Foods.HONEY_BREAD).tab(CreativeModeTab.TAB_FOOD), new MobEffectInstance(MobEffects.BAD_OMEN), new MobEffectInstance(MobEffects.POISON)));
	public static final RegistryObject<Item> GLAZED_PORKCHOP = HELPER.createItem("glazed_porkchop", () -> new CuringItem(new Item.Properties().food(Foods.GLAZED_PORKCHOP).tab(CreativeModeTab.TAB_FOOD), new MobEffectInstance(MobEffects.DIG_SLOWDOWN), new MobEffectInstance(MobEffects.POISON)));

	public static final RegistryObject<Item> HONEY_WAND = HELPER.createItem("honey_wand", () -> new HoneyWandItem(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS)));
	public static final RegistryObject<Item> STICKY_HONEY_WAND = HELPER.createItem("sticky_honey_wand", () -> new StickyHoneyWandItem(new Item.Properties().craftRemainder(BBItems.HONEY_WAND.get()).food(Foods.STICKY_HONEY_WAND).stacksTo(1).tab(CreativeModeTab.TAB_TOOLS)));

	public static final RegistryObject<Item> BOTTLE_OF_SILVERFISH = HELPER.createItem("silverfish_bottle", () -> new BugBottleItem(EntityType.SILVERFISH, new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(1).tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> BOTTLE_OF_ENDERMITE = HELPER.createItem("endermite_bottle", () -> new BugBottleItem(EntityType.ENDERMITE, new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(1).tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> BOTTLE_OF_BEE = HELPER.createItem("bee_bottle", () -> new BeeBottleItem(EntityType.BEE, new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(1).tab(CreativeModeTab.TAB_MISC)));

	public static final RegistryObject<Item> FOUR_LEAF_CLOVER = HELPER.createItem("four_leaf_clover", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_BREWING)));
	public static final RegistryObject<Item> HONEYCOMB_BANNER_PATTERN = HELPER.createItem("honeycomb_banner_pattern", () -> new BlueprintBannerPatternItem(Banners.HONEYCOMB, new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1)));

	public static final RegistryObject<BlueprintSpawnEggItem> GRIZZLY_BEAR_SPAWN_EGG = HELPER.createSpawnEggItem("grizzly_bear", BBEntities.GRIZZLY_BEAR::get, 0x523021, 0x926A4B);
	public static final RegistryObject<BlueprintSpawnEggItem> MOOBLOOM_SPAWN_EGG = HELPER.createSpawnEggItem("moobloom", BBEntities.MOOBLOOM::get, 0xDBA436, 0xDCDCDC);

	public static class Foods {
		public static final FoodProperties STICKY_HONEY_WAND = new FoodProperties.Builder().nutrition(6).saturationMod(0.1F).alwaysEat().build();
		public static final FoodProperties HONEY_APPLE = new FoodProperties.Builder().nutrition(5).saturationMod(0.4F).build();
		public static final FoodProperties HONEY_BREAD = new FoodProperties.Builder().nutrition(6).saturationMod(0.8F).build();
		public static final FoodProperties GLAZED_PORKCHOP = new FoodProperties.Builder().nutrition(9).saturationMod(0.8F).build();
	}

	public static class Banners {
		public static final BannerPattern HONEYCOMB = BannerManager.createPattern("mca", "honeycomb", "hny");
	}

	public static void registerItemProperties() {
		ItemProperties.register(BOTTLE_OF_BEE.get(), new ResourceLocation("angry"), (stack, world, entity, num) -> {
			CompoundTag compoundnbt = stack.getTag();
			if (compoundnbt != null && compoundnbt.contains("AngerTime")) {
				return (compoundnbt.getInt("AngerTime") > 0) ? 2 : 1;
			}
			return 1;
		});
	}
}