package com.bagel.buzzierbees.common.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraftforge.registries.ForgeRegistries;

public class HangingFlowerPotBlock extends FlowerPotBlock {

	@SuppressWarnings("deprecation")
	public HangingFlowerPotBlock(Block plant, Properties properties) {
		super(plant, properties);
	}

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
    	List<ItemStack> list = new ArrayList<ItemStack>();
    	list.add(new ItemStack(Blocks.FLOWER_POT));
    	Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation("buzzierbees:orange_hibiscus"));
    	list.add(new ItemStack(block));
        return list;
     }
}
