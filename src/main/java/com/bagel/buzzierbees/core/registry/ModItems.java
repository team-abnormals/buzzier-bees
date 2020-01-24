package com.bagel.buzzierbees.core.registry;
import java.util.List;

import com.bagel.buzzierbees.common.items.BeeSoupItem;
import com.bagel.buzzierbees.common.items.CuringItem;
import com.bagel.buzzierbees.common.items.HoneyWandItem;
import com.bagel.buzzierbees.common.items.StickyHoneyWandItem;
import com.bagel.buzzierbees.core.BuzzierBees;
import com.bagel.buzzierbees.core.registry.util.RegistryUtils;
import com.google.common.collect.Lists;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems
{
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, BuzzierBees.MODID);
	public static final List<RegistryObject<Item>> SPAWN_EGGS = Lists.newArrayList();
	
	public static RegistryObject<Item> WAX = RegistryUtils.createItem("wax", () -> new Item(new Item.Properties().group(ItemGroup.MATERIALS)));
	//public static RegistryObject<Item> CLOVER_LEAF 	= ModUtils.createItem("clover_leaf", () -> new Item(new Item.Properties().group(ItemGroup.MISC)));
	public static RegistryObject<Item> FOUR_LEAF_CLOVER 	= RegistryUtils.createItem("four_leaf_clover", () -> new Item(new Item.Properties().group(ItemGroup.BREWING)));
	
	//public static RegistryObject<Item> HIVE_SIGN 	= ModUtils.createItem("hive_sign", () -> new SignItem(new Item.Properties().group(ItemGroup.DECORATIONS), null, null));
	
	//public static RegistryObject<Item> CRYSTALLIZED_CLOVER_HONEY = ModUtils.createItem("crystallized_clover_honey", () -> new Item(new Item.Properties().food(ModFoods.CRYSTALLIZED_HONEY).group(ItemGroup.FOOD)));
	public static RegistryObject<Item> CRYSTALLIZED_HONEY 	= RegistryUtils.createItem("crystallized_honey", () -> new Item(new Item.Properties().food(ModFoods.CRYSTALLIZED_HONEY).group(ItemGroup.FOOD)));
	
	public static RegistryObject<Item> BEE_SOUP 			= RegistryUtils.createItem("bee_soup", () -> new BeeSoupItem(new Item.Properties().maxStackSize(1).food(ModFoods.BEE_SOUP).group(ItemGroup.FOOD)));
	//public static RegistryObject<Item> CLOVER_HONEY_BOTTLE 	= RegistryUtils.createItem("clover_honey_bottle", () -> new CloverHoneyBottleItem(new Item.Properties().containerItem(Items.GLASS_BOTTLE).maxStackSize(16).food(ModFoods.CLOVER_HONEY_BOTTLE).group(ItemGroup.FOOD)));
	public static RegistryObject<Item> HONEY_APPLE 			= RegistryUtils.createItem("honey_apple", () -> new CuringItem(new Item.Properties().food(ModFoods.HONEY_APPLE).group(ItemGroup.FOOD), new EffectInstance[]{new EffectInstance(Effects.LEVITATION), new EffectInstance(Effects.POISON)}));
	public static RegistryObject<Item> HONEY_BREAD 			= RegistryUtils.createItem("honey_bread", () -> new CuringItem(new Item.Properties().food(ModFoods.HONEY_BREAD).group(ItemGroup.FOOD), new EffectInstance[]{new EffectInstance(Effects.BAD_OMEN), new EffectInstance(Effects.POISON)}));
	public static RegistryObject<Item> GLAZED_PORKCHOP		= RegistryUtils.createItem("glazed_porkchop", () -> new CuringItem(new Item.Properties().food(ModFoods.GLAZED_PORKCHOP).group(ItemGroup.FOOD), new EffectInstance[]{new EffectInstance(Effects.MINING_FATIGUE), new EffectInstance(Effects.POISON)}));
	
	//public static RegistryObject<Item> HIVE_BOAT = RegistryUtils.createItem("hive_boat", () -> new HiveBoatItem(HiveBoatEntity.Type.HIVE, new Item.Properties().group(ItemGroup.TRANSPORTATION)));
	
	public static RegistryObject<Item> HONEY_WAND = RegistryUtils.createItem("honey_wand", () -> new HoneyWandItem(new Item.Properties().maxStackSize(1).group(ItemGroup.TOOLS)));
	public static RegistryObject<Item> STICKY_HONEY_WAND = RegistryUtils.createItem("sticky_honey_wand", () -> new StickyHoneyWandItem(new Item.Properties().containerItem(ModItems.HONEY_WAND.get()).food(ModFoods.STICKY_HONEY_WAND).maxStackSize(1).group(ItemGroup.TOOLS)));
	public static RegistryObject<Item> HONEY_SLIME_SPAWN_EGG = RegistryUtils.createSpawnEggItem("honey_slime", () -> ModEntities.HONEY_SLIME, 16361240, 16361240);
}