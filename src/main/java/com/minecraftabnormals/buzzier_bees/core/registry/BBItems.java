package com.minecraftabnormals.buzzier_bees.core.registry;

import com.minecraftabnormals.buzzier_bees.common.items.*;
import com.minecraftabnormals.buzzier_bees.core.BuzzierBees;
import com.minecraftabnormals.buzzier_bees.core.other.BBFoods;
import com.teamabnormals.abnormals_core.common.items.AbnormalsBannerPatternItem;
import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BBItems {
	public static final RegistryHelper HELPER = BuzzierBees.REGISTRY_HELPER;

	public static final RegistryObject<Item> HONEY_APPLE = HELPER.createItem("honey_apple", () -> new CuringItem(new Item.Properties().food(BBFoods.HONEY_APPLE).group(ItemGroup.FOOD), new EffectInstance[]{new EffectInstance(Effects.LEVITATION), new EffectInstance(Effects.POISON)}));
	public static final RegistryObject<Item> HONEY_BREAD = HELPER.createItem("honey_bread", () -> new CuringItem(new Item.Properties().food(BBFoods.HONEY_BREAD).group(ItemGroup.FOOD), new EffectInstance[]{new EffectInstance(Effects.BAD_OMEN), new EffectInstance(Effects.POISON)}));
	public static final RegistryObject<Item> GLAZED_PORKCHOP = HELPER.createItem("glazed_porkchop", () -> new CuringItem(new Item.Properties().food(BBFoods.GLAZED_PORKCHOP).group(ItemGroup.FOOD), new EffectInstance[]{new EffectInstance(Effects.MINING_FATIGUE), new EffectInstance(Effects.POISON)}));

	public static final RegistryObject<Item> HONEY_WAND = HELPER.createItem("honey_wand", () -> new HoneyWandItem(new Item.Properties().maxStackSize(1).group(ItemGroup.TOOLS)));
	public static final RegistryObject<Item> STICKY_HONEY_WAND = HELPER.createItem("sticky_honey_wand", () -> new StickyHoneyWandItem(new Item.Properties().containerItem(BBItems.HONEY_WAND.get()).food(BBFoods.STICKY_HONEY_WAND).maxStackSize(1).group(ItemGroup.TOOLS)));

	public static final RegistryObject<Item> BOTTLE_OF_SILVERFISH = HELPER.createItem("silverfish_bottle", () -> new BugBottleItem(EntityType.SILVERFISH, new Item.Properties().containerItem(Items.GLASS_BOTTLE).maxStackSize(1).group(ItemGroup.MISC)));
	public static final RegistryObject<Item> BOTTLE_OF_ENDERMITE = HELPER.createItem("endermite_bottle", () -> new BugBottleItem(EntityType.ENDERMITE, new Item.Properties().containerItem(Items.GLASS_BOTTLE).maxStackSize(1).group(ItemGroup.MISC)));
	public static final RegistryObject<Item> BOTTLE_OF_BEE = HELPER.createItem("bee_bottle", () -> new BeeBottleItem(EntityType.BEE, new Item.Properties().containerItem(Items.GLASS_BOTTLE).maxStackSize(1).group(ItemGroup.MISC)));

	public static final RegistryObject<Item> FOUR_LEAF_CLOVER = HELPER.createItem("four_leaf_clover", () -> new Item(new Item.Properties().group(ItemGroup.BREWING)));
	public static final RegistryObject<Item> HONEYCOMB_BANNER_PATTERN = HELPER.createItem("honeycomb_banner_pattern", () -> new AbnormalsBannerPatternItem(BBBanners.HONEYCOMB, new Item.Properties().group(ItemGroup.MISC).maxStackSize(1)));

	public static final RegistryObject<Item> MOOBLOOM_SPAWN_EGG = HELPER.createSpawnEggItem("moobloom", () -> BBEntities.MOOBLOOM.get(), 0xDBA436, 0xDCDCDC);

	public static void setupItemProperties() {
		ItemModelsProperties.func_239418_a_(BOTTLE_OF_BEE.get(), new ResourceLocation("angry"), (stack, world, entity) -> {
			CompoundNBT compoundnbt = stack.getTag();
			if (compoundnbt != null && compoundnbt.contains("AngerTime")) {
				return (compoundnbt.getInt("AngerTime") > 0) ? 2 : 1;
			}
			return 1;
		});
	}
}