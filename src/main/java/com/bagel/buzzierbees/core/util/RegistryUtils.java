package com.bagel.buzzierbees.core.util;

import com.bagel.buzzierbees.common.items.ModSpawnEggItem;
import com.bagel.buzzierbees.core.registry.BBBlocks;
import com.bagel.buzzierbees.core.registry.BBItems;
import com.google.common.base.Supplier;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.RegistryObject;

public class RegistryUtils {
	
	public static <I extends Item> RegistryObject<I> createItem(String name, Supplier<? extends I> supplier) {
		RegistryObject<I> item = BBItems.ITEMS.register(name, supplier);
		return item;
	}
	
	public static RegistryObject<Item> createSpawnEggItem(String entityName, Supplier<EntityType<?>> supplier, int primaryColor, int secondaryColor) {
		RegistryObject<Item> spawnEgg = BBItems.ITEMS.register(entityName + "_spawn_egg", () -> new ModSpawnEggItem(supplier, primaryColor, secondaryColor, new Item.Properties().group(ItemGroup.MISC)));
		BBItems.SPAWN_EGGS.add(spawnEgg);
		return spawnEgg;
	}
	
	public static BlockItem createSimpleItemBlock(Block block, ItemGroup itemGroup) {
        return (BlockItem) new BlockItem(block, new Item.Properties().group(itemGroup)).setRegistryName(block.getRegistryName());
    }
	
	public static <B extends Block> RegistryObject<B> createBlock(String name, Supplier<? extends B> supplier, ItemGroup itemGroup) {
        RegistryObject<B> block = BBBlocks.BLOCKS.register(name, supplier);
        BBItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().group(itemGroup)));
        return block;
    }
	
	public static <B extends Block> RegistryObject<B> createBlockCompat(String mod, String name, Supplier<? extends B> supplier, ItemGroup itemGroup) {
		if(ModList.get().isLoaded(mod)) {
			RegistryObject<B> block = BBBlocks.BLOCKS.register(name, supplier);
			BBItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().group(itemGroup)));
			return block;
		}
		return null;
    }

    public static <B extends Block> RegistryObject<B> createBlockNoItem(String name, Supplier<? extends B> supplier) {
        RegistryObject<B> block = BBBlocks.BLOCKS.register(name, supplier);
        return block;
    }
}
