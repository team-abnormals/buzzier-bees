package com.minecraftabnormals.buzzier_bees.core.registry;

import com.minecraftabnormals.abnormals_core.common.items.AbnormalsBannerPatternItem;
import com.minecraftabnormals.abnormals_core.common.items.AbnormalsSpawnEggItem;
import com.minecraftabnormals.abnormals_core.core.api.banner.BannerManager;
import com.minecraftabnormals.abnormals_core.core.util.registry.ItemSubRegistryHelper;
import com.minecraftabnormals.buzzier_bees.common.items.*;
import com.minecraftabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.entity.EntityType;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tileentity.BannerPattern;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BBItems {
	public static final ItemSubRegistryHelper HELPER = BuzzierBees.REGISTRY_HELPER.getItemSubHelper();

	public static final RegistryObject<Item> HONEY_APPLE = HELPER.createItem("honey_apple", () -> new CuringItem(new Item.Properties().food(Foods.HONEY_APPLE).tab(ItemGroup.TAB_FOOD), new EffectInstance(Effects.LEVITATION), new EffectInstance(Effects.POISON)));
	public static final RegistryObject<Item> HONEY_BREAD = HELPER.createItem("honey_bread", () -> new CuringItem(new Item.Properties().food(Foods.HONEY_BREAD).tab(ItemGroup.TAB_FOOD), new EffectInstance(Effects.BAD_OMEN), new EffectInstance(Effects.POISON)));
	public static final RegistryObject<Item> GLAZED_PORKCHOP = HELPER.createItem("glazed_porkchop", () -> new CuringItem(new Item.Properties().food(Foods.GLAZED_PORKCHOP).tab(ItemGroup.TAB_FOOD), new EffectInstance(Effects.DIG_SLOWDOWN), new EffectInstance(Effects.POISON)));

	public static final RegistryObject<Item> HONEY_WAND = HELPER.createItem("honey_wand", () -> new HoneyWandItem(new Item.Properties().stacksTo(1).tab(ItemGroup.TAB_TOOLS)));
	public static final RegistryObject<Item> STICKY_HONEY_WAND = HELPER.createItem("sticky_honey_wand", () -> new StickyHoneyWandItem(new Item.Properties().craftRemainder(BBItems.HONEY_WAND.get()).food(Foods.STICKY_HONEY_WAND).stacksTo(1).tab(ItemGroup.TAB_TOOLS)));

	public static final RegistryObject<Item> BOTTLE_OF_SILVERFISH = HELPER.createItem("silverfish_bottle", () -> new BugBottleItem(EntityType.SILVERFISH, new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(1).tab(ItemGroup.TAB_MISC)));
	public static final RegistryObject<Item> BOTTLE_OF_ENDERMITE = HELPER.createItem("endermite_bottle", () -> new BugBottleItem(EntityType.ENDERMITE, new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(1).tab(ItemGroup.TAB_MISC)));
	public static final RegistryObject<Item> BOTTLE_OF_BEE = HELPER.createItem("bee_bottle", () -> new BeeBottleItem(EntityType.BEE, new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(1).tab(ItemGroup.TAB_MISC)));

	public static final RegistryObject<Item> FOUR_LEAF_CLOVER = HELPER.createItem("four_leaf_clover", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_BREWING)));
	public static final RegistryObject<Item> HONEYCOMB_BANNER_PATTERN = HELPER.createItem("honeycomb_banner_pattern", () -> new AbnormalsBannerPatternItem(Banners.HONEYCOMB, new Item.Properties().tab(ItemGroup.TAB_MISC).stacksTo(1)));

	public static final RegistryObject<AbnormalsSpawnEggItem> GRIZZLY_BEAR_SPAWN_EGG = HELPER.createSpawnEggItem("grizzly_bear", BBEntities.GRIZZLY_BEAR::get, 0x523021, 0x926A4B);
	public static final RegistryObject<AbnormalsSpawnEggItem> MOOBLOOM_SPAWN_EGG = HELPER.createSpawnEggItem("moobloom", BBEntities.MOOBLOOM::get, 0xDBA436, 0xDCDCDC);

	public static class Foods {
		public static final Food STICKY_HONEY_WAND = new Food.Builder().nutrition(6).saturationMod(0.1F).alwaysEat().build();
		public static final Food HONEY_APPLE = new Food.Builder().nutrition(5).saturationMod(0.4F).build();
		public static final Food HONEY_BREAD = new Food.Builder().nutrition(6).saturationMod(0.8F).build();
		public static final Food GLAZED_PORKCHOP = new Food.Builder().nutrition(9).saturationMod(0.8F).build();
	}

	public static class Banners {
		public static final BannerPattern HONEYCOMB = BannerManager.createPattern("mca", "honeycomb", "hny");
	}

	public static void registerItemProperties() {
		ItemModelsProperties.register(BOTTLE_OF_BEE.get(), new ResourceLocation("angry"), (stack, world, entity) -> {
			CompoundNBT compoundnbt = stack.getTag();
			if (compoundnbt != null && compoundnbt.contains("AngerTime")) {
				return (compoundnbt.getInt("AngerTime") > 0) ? 2 : 1;
			}
			return 1;
		});
	}
}