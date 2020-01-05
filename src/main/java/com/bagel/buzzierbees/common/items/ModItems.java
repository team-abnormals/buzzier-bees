package com.bagel.buzzierbees.common.items;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems
{
	public static Item WAX;
	public static Item CRYSTALLIZED_HONEY;
	//public static Item CRYSTALLIZED_CLOVER_HONEY;

	//Food Items
	public static Item BEE_SOUP;
	public static Item CLOVER_LEAF;
	public static Item FOUR_LEAF_CLOVER;
	public static Item CLOVER_HONEY_BOTTLE;
	public static Item HONEY_APPLE;
	public static Item HONEY_BREAD;
	public static Item GLAZED_HAM;

	//Spawn Eggs
	public static Item HONEY_SLIME_SPAWN_EGG;

	//Tools
	public static Item HONEY_WAND;
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
		BEE_SOUP_FOOD 				= (new Food.Builder()).hunger(8).saturation(0.6F).setAlwaysEdible().effect(new EffectInstance(Effects.SLOWNESS, 240, 2), 0.5F).build();
		CLOVER_HONEY_BOTTLE_FOOD 	= (new Food.Builder()).hunger(6).saturation(2.5F).setAlwaysEdible().effect(new EffectInstance(Effects.INSTANT_HEALTH, 20, 1), 0.8F).build();
		STICKY_HONEY_WAND_FOOD 		= (new Food.Builder()).hunger(6).saturation(0.1F).setAlwaysEdible().build();
		HONEY_APPLE_FOOD 			= (new Food.Builder()).hunger(7).saturation(0.4F).build();
		HONEY_BREAD_FOOD 			= (new Food.Builder()).hunger(7).saturation(0.4F).build(); //TODO: Proper food (bagel)
		GLAZED_HAM_FOOD				= (new Food.Builder()).hunger(7).saturation(0.4F).build(); //TODO: Proper food (bagel)
	}
	
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
	{
      	WAX 				= registerItem(new Item(new Item.Properties().group(ItemGroup.MATERIALS)), "wax");
    	CLOVER_LEAF 		= registerItem(new Item(new Item.Properties().group(ItemGroup.MISC)), "clover_leaf");
    	FOUR_LEAF_CLOVER 	= registerItem(new Item(new Item.Properties().group(ItemGroup.BREWING)), "four_leaf_clover");

    	//Cut Content
    	//CRYSTALLIZED_CLOVER_HONEY = registerItem(new Item(new Item.Properties().food(CRYSTALLIZED_HONEY_FOOD).group(ItemGroup.FOOD)), "crystallized_clover_honey");

      	//Food
		CRYSTALLIZED_HONEY 	= registerItem(new Item(new Item.Properties().food(CRYSTALLIZED_HONEY_FOOD).group(ItemGroup.FOOD)), "crystallized_honey");
		BEE_SOUP 			= registerItem(new BeeSoupItem(new Item.Properties().maxStackSize(1).food(BEE_SOUP_FOOD).group(ItemGroup.FOOD)), "bee_soup");
		CLOVER_HONEY_BOTTLE = registerItem(new CloverHoneyBottleItem(new Item.Properties().containerItem(Items.GLASS_BOTTLE).maxStackSize(16).food(CLOVER_HONEY_BOTTLE_FOOD).group(ItemGroup.FOOD)), "clover_honey_bottle");
		HONEY_APPLE 		= registerItem(new CuringItem(new Item.Properties().food(HONEY_APPLE_FOOD).group(ItemGroup.FOOD), new EffectInstance[]{new EffectInstance(Effects.POISON)}), "honey_apple");
		HONEY_BREAD 		= registerItem(new CuringItem(new Item.Properties().food(HONEY_BREAD_FOOD).group(ItemGroup.FOOD), new EffectInstance[]{new EffectInstance(Effects.POISON)}), "honey_bread");
		//GLAZED_HAM		= registerItem(new CuringItem(new Item.Properties().food(GLAZED_HAM_FOOD).group(ItemGroup.FOOD), Effects.POISON), "glazed_ham"); TODO: Texture (model file is already in), proper food

		//Tools
		HONEY_WAND = registerItem(new HoneyWandItem(new Item.Properties().containerItem(HONEY_WAND).food(STICKY_HONEY_WAND_FOOD).defaultMaxDamage(128).group(ItemGroup.TOOLS)), "honey_wand");

		//Spawn Eggs
		HONEY_SLIME_SPAWN_EGG = registerItem(new HoneySlimeSpawnEggItem(15281931, 16111310, new Item.Properties().group(ItemGroup.MISC)), "honey_slime_spawn_egg");
	}
	
    public static Item registerItem(Item item, String name)
    {
    	item.setRegistryName(name);
        ForgeRegistries.ITEMS.register(item);
        return item;
    }
}