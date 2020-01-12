package com.bagel.buzzierbees.common.items;

import com.bagel.buzzierbees.common.entities.ModEntities;
import com.bagel.buzzierbees.common.BuzzierBees;

import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems
{
	@ObjectHolder(BuzzierBees.MODID + ":wax")
	public static Item WAX;
	@ObjectHolder(BuzzierBees.MODID + ":crystallized_honey")
	public static Item CRYSTALLIZED_HONEY;
	//@ObjectHolder(BuzzierBees.MODID + ":crystallized_clover_honey")
	//public static Item CRYSTALLIZED_CLOVER_HONEY;
	@ObjectHolder(BuzzierBees.MODID + ":hive_sign")
	public static Item HIVE_SIGN;

	//Food Items
	@ObjectHolder(BuzzierBees.MODID + ":bee_soup")
	public static Item BEE_SOUP;
	@ObjectHolder(BuzzierBees.MODID + ":clover_leaf")
	public static Item CLOVER_LEAF;
	@ObjectHolder(BuzzierBees.MODID + ":four_leaf_clover")
	public static Item FOUR_LEAF_CLOVER;
	@ObjectHolder(BuzzierBees.MODID + ":clover_honey_bottle")
	public static Item CLOVER_HONEY_BOTTLE;
	@ObjectHolder(BuzzierBees.MODID + ":honey_apple")
	public static Item HONEY_APPLE;
	@ObjectHolder(BuzzierBees.MODID + ":honey_bread")
	public static Item HONEY_BREAD;
	@ObjectHolder(BuzzierBees.MODID + ":glazed_ham")
	public static Item GLAZED_HAM;

	//Spawn Eggs
	@ObjectHolder(BuzzierBees.MODID + ":honey_slime_spawn_egg")
	public static Item HONEY_SLIME_SPAWN_EGG;

	//Tools
	@ObjectHolder(BuzzierBees.MODID + ":honey_wand")
	public static Item HONEY_WAND;
	@ObjectHolder(BuzzierBees.MODID + ":sticky_honey_wand")
	public static Item STICKY_HONEY_WAND;

	//Food
	public static Food CRYSTALLIZED_HONEY_FOOD;
	public static Food BEE_SOUP_FOOD;
	public static Food CLOVER_HONEY_BOTTLE_FOOD;
	public static Food STICKY_HONEY_WAND_FOOD;
	public static Food HONEY_APPLE_FOOD;
	public static Food HONEY_BREAD_FOOD;
	public static Food GLAZED_HAM_FOOD;
		
	static {
		CRYSTALLIZED_HONEY_FOOD 	= (new Food.Builder()).hunger(1).saturation(1.5F).fastToEat().setAlwaysEdible().effect(new EffectInstance(Effects.SPEED, 160, 1), 0.8F).build();
		BEE_SOUP_FOOD 				= (new Food.Builder()).hunger(3).saturation(2.3F).effect(new EffectInstance(Effects.SLOWNESS, 240, 2), 0.5F).build();
		//CLOVER_HONEY_BOTTLE_FOOD 	= (new Food.Builder()).hunger(6).saturation(0.2F).setAlwaysEdible().effect(new EffectInstance(Effects.INSTANT_HEALTH, 20, 1), 0.8F).build();
		STICKY_HONEY_WAND_FOOD 		= (new Food.Builder()).hunger(6).saturation(0.1F).setAlwaysEdible().build();
		HONEY_APPLE_FOOD 			= (new Food.Builder()).hunger(5).saturation(0.4F).build();
		HONEY_BREAD_FOOD 			= (new Food.Builder()).hunger(6).saturation(0.8F).build();
		GLAZED_HAM_FOOD				= (new Food.Builder()).hunger(9).saturation(0.8F).build();
	}
	
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
	{    	
      	WAX 				= registerItem(new Item(new Item.Properties().group(ItemGroup.MATERIALS)), "wax");
    	CLOVER_LEAF 		= registerItem(new Item(new Item.Properties().group(ItemGroup.MISC)), "clover_leaf");
    	FOUR_LEAF_CLOVER 	= registerItem(new Item(new Item.Properties().group(ItemGroup.BREWING)), "four_leaf_clover");
		//HIVE_SIGN 	= registerItem(new SignItem(new Item.Properties().group(ItemGroup.DECORATIONS), ModBlocks.HIVE_SIGN, ModBlocks.HIVE_WALL_SIGN), "hive_sign");

    	//Cut Content
    	//CRYSTALLIZED_CLOVER_HONEY = registerItem(new Item(new Item.Properties().food(CRYSTALLIZED_HONEY_FOOD).group(ItemGroup.FOOD)), "crystallized_clover_honey");

      	//Food
		CRYSTALLIZED_HONEY 	= registerItem(new Item(new Item.Properties().food(CRYSTALLIZED_HONEY_FOOD).group(ItemGroup.FOOD)), "crystallized_honey");
		BEE_SOUP 			= registerItem(new BeeSoupItem(new Item.Properties().maxStackSize(1).food(BEE_SOUP_FOOD).group(ItemGroup.FOOD)), "bee_soup");
		//CLOVER_HONEY_BOTTLE = registerItem(new CloverHoneyBottleItem(new Item.Properties().containerItem(Items.GLASS_BOTTLE).maxStackSize(16).food(CLOVER_HONEY_BOTTLE_FOOD).group(ItemGroup.FOOD)), "clover_honey_bottle");
		HONEY_APPLE 		= registerItem(new CuringItem(new Item.Properties().food(HONEY_APPLE_FOOD).group(ItemGroup.FOOD), new EffectInstance[]{new EffectInstance(Effects.LEVITATION), new EffectInstance(Effects.POISON)}), "honey_apple");
		HONEY_BREAD 		= registerItem(new CuringItem(new Item.Properties().food(HONEY_BREAD_FOOD).group(ItemGroup.FOOD), new EffectInstance[]{new EffectInstance(Effects.HUNGER), new EffectInstance(Effects.POISON)}), "honey_bread");
		//GLAZED_HAM		= registerItem(new CuringItem(new Item.Properties().food(GLAZED_HAM_FOOD).group(ItemGroup.FOOD), new EffectInstance[]{new EffectInstance(Effects.WEAKNESS), new EffectInstance(Effects.POISON)}), "glazed_ham"); TODO: Texture (model file is already in), proper food

		//Tools
		HONEY_WAND = registerItem(new HoneyWandItem(new Item.Properties().maxStackSize(1).group(ItemGroup.TOOLS)), "honey_wand");
		STICKY_HONEY_WAND = registerItem(new StickyHoneyWandItem(new Item.Properties().containerItem(HONEY_WAND).food(STICKY_HONEY_WAND_FOOD).maxStackSize(1).group(ItemGroup.TOOLS)), "sticky_honey_wand");
		//Spawn Eggs
		HONEY_SLIME_SPAWN_EGG = registerItem(new ModSpawnEggItem(() -> ModEntities.HONEY_SLIME,16361240, 16361240, new Item.Properties().group(ItemGroup.MISC)), "honey_slime_spawn_egg");
		}
	
    public static Item registerItem(Item item, String name)
    {
    	item.setRegistryName(BuzzierBees.MODID, name);
        ForgeRegistries.ITEMS.register(item);
        return item;
    }

}