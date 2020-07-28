package com.minecraftabnormals.buzzier_bees.core.registry;
import com.minecraftabnormals.buzzier_bees.common.items.BeeBottleItem;
import com.minecraftabnormals.buzzier_bees.common.items.BugBottleItem;
import com.minecraftabnormals.buzzier_bees.common.items.CuringItem;
import com.minecraftabnormals.buzzier_bees.common.items.HoneyWandItem;
import com.minecraftabnormals.buzzier_bees.common.items.StickyHoneyWandItem;
import com.minecraftabnormals.buzzier_bees.core.BuzzierBees;
import com.minecraftabnormals.buzzier_bees.core.other.BBFoods;
import com.minecraftabnormals.abnormals_core.core.utils.RegistryHelper;

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
public class BBItems
{
	public static final RegistryHelper HELPER = BuzzierBees.REGISTRY_HELPER;
	
	public static final RegistryObject<Item> WAX 					= HELPER.createItem("wax", () -> new Item(new Item.Properties().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<Item> CRYSTALLIZED_HONEY 	= HELPER.createItem("crystallized_honey", () -> new Item(new Item.Properties().food(BBFoods.CRYSTALLIZED_HONEY).group(ItemGroup.FOOD)));
	public static final RegistryObject<Item> FOUR_LEAF_CLOVER 		= HELPER.createItem("four_leaf_clover", () -> new Item(new Item.Properties().group(ItemGroup.BREWING)));

	public static final RegistryObject<Item> HONEY_APPLE 		= HELPER.createItem("honey_apple", () -> new CuringItem(new Item.Properties().food(BBFoods.HONEY_APPLE).group(ItemGroup.FOOD), new EffectInstance[]{new EffectInstance(Effects.LEVITATION), new EffectInstance(Effects.POISON)}));
	public static final RegistryObject<Item> HONEY_BREAD 		= HELPER.createItem("honey_bread", () -> new CuringItem(new Item.Properties().food(BBFoods.HONEY_BREAD).group(ItemGroup.FOOD), new EffectInstance[]{new EffectInstance(Effects.BAD_OMEN), new EffectInstance(Effects.POISON)}));
	public static final RegistryObject<Item> GLAZED_PORKCHOP	= HELPER.createItem("glazed_porkchop", () -> new CuringItem(new Item.Properties().food(BBFoods.GLAZED_PORKCHOP).group(ItemGroup.FOOD), new EffectInstance[]{new EffectInstance(Effects.MINING_FATIGUE), new EffectInstance(Effects.POISON)}));
	
	public static final RegistryObject<Item> HONEY_WAND 		= HELPER.createItem("honey_wand", () -> new HoneyWandItem(new Item.Properties().maxStackSize(1).group(ItemGroup.TOOLS)));
	public static final RegistryObject<Item> STICKY_HONEY_WAND 	= HELPER.createItem("sticky_honey_wand", () -> new StickyHoneyWandItem(new Item.Properties().containerItem(BBItems.HONEY_WAND.get()).food(BBFoods.STICKY_HONEY_WAND).maxStackSize(1).group(ItemGroup.TOOLS)));
	
	public static final RegistryObject<Item> BOTTLE_OF_SILVERFISH 	= HELPER.createItem("silverfish_bottle", () -> new BugBottleItem(EntityType.SILVERFISH, new Item.Properties().containerItem(Items.GLASS_BOTTLE).maxStackSize(1).group(ItemGroup.MISC)));
	public static final RegistryObject<Item> BOTTLE_OF_ENDERMITE 	= HELPER.createItem("endermite_bottle", () -> new BugBottleItem(EntityType.ENDERMITE, new Item.Properties().containerItem(Items.GLASS_BOTTLE).maxStackSize(1).group(ItemGroup.MISC)));
	public static final RegistryObject<Item> BOTTLE_OF_BEE 			= HELPER.createItem("bee_bottle", () -> new BeeBottleItem(EntityType.BEE, new Item.Properties().containerItem(Items.GLASS_BOTTLE).maxStackSize(1).group(ItemGroup.MISC)));
	
//	public static final RegistryObject<Item> GRIZZLY_BEAR_SPAWN_EGG = HELPER.createSpawnEggItem("grizzly_bear", () -> BBEntities.GRIZZLY_BEAR.get(), 5122062, 4136462);
//	public static final RegistryObject<Item> BLACK_BEAR_SPAWN_EGG 	= HELPER.createSpawnEggItem("black_bear", () -> BBEntities.BLACK_BEAR.get(), 2565927, 12225639);
//	public static final RegistryObject<Item> FLY_SPAWN_EGG 			= HELPER.createSpawnEggItem("fly", () -> BBEntities.FLY.get(), 920336, 7080720);
//	public static final RegistryObject<Item> BUMBLEBEE_SPAWN_EGG 	= HELPER.createSpawnEggItem("bumblebee", () -> BBEntities.BUMBLEBEE.get(), 4400155, 15582019);
	
//	public static final RegistryObject<Item> CLOVER_HONEY_BOTTLE 		= HELPER.createItem("clover_honey_bottle", () -> new CloverHoneyBottleItem(new Item.Properties().containerItem(Items.GLASS_BOTTLE).maxStackSize(16).food(ModFoods.CLOVER_HONEY_BOTTLE).group(ItemGroup.FOOD)));
//	public static final RegistryObject<Item> CRYSTALLIZED_CLOVER_HONEY = ModUtils.createItem("crystallized_clover_honey", () -> new Item(new Item.Properties().food(ModFoods.CRYSTALLIZED_HONEY).group(ItemGroup.FOOD)));
//	public static final RegistryObject<Item> CLOVER_LEAF 	= ModUtils.createItem("clover_leaf", () -> new Item(new Item.Properties().group(ItemGroup.MISC)));
//	public static final RegistryObject<Item> BOTTLE_OF_FLY = HELPER.createItem("fly_bottle", () -> new BeeBottleItem(EntityType.BEE, new Item.Properties().containerItem(Items.GLASS_BOTTLE).maxStackSize(1).group(ItemGroup.MISC)));
//	public static final RegistryObject<Item> BOTTLE_OF_BUMBLEBEE = HELPER.createItem("bumblebee_bottle", () -> new BeeBottleItem(EntityType.BEE, new Item.Properties().containerItem(Items.GLASS_BOTTLE).maxStackSize(1).group(ItemGroup.MISC)));

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