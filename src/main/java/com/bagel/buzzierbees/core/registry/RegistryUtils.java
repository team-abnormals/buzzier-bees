package com.bagel.buzzierbees.core.registry;

import com.bagel.buzzierbees.common.items.ModSpawnEggItem;
import com.google.common.base.Supplier;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.RegistryObject;

public class RegistryUtils {
	
	public static <I extends Item> RegistryObject<I> createItem(String name, Supplier<? extends I> supplier) {
		RegistryObject<I> item = ModItems.ITEMS.register(name, supplier);
		return item;
	}
	
	public static RegistryObject<Item> createSpawnEggItem(String entityName, Supplier<EntityType<?>> supplier, int primaryColor, int secondaryColor) {
		RegistryObject<Item> spawnEgg = ModItems.ITEMS.register(entityName + "_spawn_egg", () -> new ModSpawnEggItem(supplier, primaryColor, secondaryColor, new Item.Properties().group(ItemGroup.MISC)));
		ModItems.SPAWN_EGGS.add(spawnEgg);
		return spawnEgg;
	}
	
	public static BlockItem createSimpleItemBlock(Block block, ItemGroup itemGroup) {
        return (BlockItem) new BlockItem(block, new Item.Properties().group(itemGroup)).setRegistryName(block.getRegistryName());
    }
	
	public static <B extends Block> RegistryObject<B> createBlock(String name, Supplier<? extends B> supplier, ItemGroup itemGroup) {
        RegistryObject<B> block = ModBlocks.BLOCKS.register(name, supplier);
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().group(itemGroup)));
        return block;
    }
	
	public static <B extends Block> RegistryObject<B> createBlockCompat(String mod, String name, Supplier<? extends B> supplier, ItemGroup itemGroup) {
		if(ModList.get().isLoaded(mod)) {
			RegistryObject<B> block = ModBlocks.BLOCKS.register(name, supplier);
			ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().group(itemGroup)));
			return block;
		}
		return null;
    }

    public static <B extends Block> RegistryObject<B> createBlockNoItem(String name, Supplier<? extends B> supplier) {
        RegistryObject<B> block = ModBlocks.BLOCKS.register(name, supplier);
        return block;
    }
	
	public static final Block.Properties FLOWER 		= Block.Properties.create(Material.PLANTS).func_226896_b_().doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT);
	public static final Block.Properties CANDLE = Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).sound(SoundType.WOOD);
	public static final Block.Properties POT    		= Block.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().func_226896_b_();
}
