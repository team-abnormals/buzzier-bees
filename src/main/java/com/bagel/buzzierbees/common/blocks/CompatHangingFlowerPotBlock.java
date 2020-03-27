package com.bagel.buzzierbees.common.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraftforge.registries.ForgeRegistries;

public class CompatHangingFlowerPotBlock extends HangingFlowerPotBlock {
	private final Block flower;

	public CompatHangingFlowerPotBlock(Block flower, Properties properties) {
		super(flower, properties);
		this.flower = flower;
	}

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
    	List<ItemStack> list = new ArrayList<ItemStack>();
    	list.add(new ItemStack(Blocks.FLOWER_POT));
    	Block block = ForgeRegistries.BLOCKS.getValue(this.flower.getRegistryName());
    	list.add(new ItemStack(block));
        return list;
     }
}
