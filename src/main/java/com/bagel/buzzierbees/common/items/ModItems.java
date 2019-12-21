package com.bagel.buzzierbees.common.items;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
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
	
	public static Food CRYSTALLIZED_HONEY_FOOD;
	
	static {
		CRYSTALLIZED_HONEY_FOOD = (new Food.Builder()).hunger(1).saturation(0.1F).fastToEat().setAlwaysEdible().effect(new EffectInstance(Effects.SPEED, 160, 1), 0.8F).build();
	}
	
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
	{
    	CRYSTALLIZED_HONEY = registerItem(new Item(new Item.Properties().food(CRYSTALLIZED_HONEY_FOOD).group(ItemGroup.MISC)), "crystallized_honey");
        WAX = registerItem(new Item(new Item.Properties().group(ItemGroup.MATERIALS)), "wax");
	}
	
    public static Item registerItem(Item item, String name)
    {
    	item.setRegistryName(name);
        ForgeRegistries.ITEMS.register(item);
        return item;
    }
}