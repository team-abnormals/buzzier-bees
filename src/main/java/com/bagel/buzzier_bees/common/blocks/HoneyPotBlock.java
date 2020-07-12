package com.bagel.buzzier_bees.common.blocks;

import com.bagel.buzzier_bees.core.registry.BBItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class HoneyPotBlock extends Block {
	public static final IntegerProperty HONEY_LEVEL = IntegerProperty.create("honey_level", 0, 4);
	private static final VoxelShape[] SHAPES = Util.make(new VoxelShape[5], (levels) -> {
	      for(int i = 0; i < 5; i++) {
	         int level = i >= 1 ? i + 1 : i;
	         levels[i] = VoxelShapes.combineAndSimplify(VoxelShapes.fullCube(), Block.makeCuboidShape(3.0D, Math.max(2, level * 3), 3.0D, 13.0D, 16.0D, 13.0D), IBooleanFunction.ONLY_FIRST);
	      }
	});
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		ItemStack itemstack = player.getHeldItem(handIn);
		Item item = itemstack.getItem();
		int honeyLevel = state.get(HONEY_LEVEL);
		
		if (item == Items.GLASS_BOTTLE && honeyLevel > 0) {
			decreaseHoney(Items.HONEY_BOTTLE, honeyLevel, itemstack, state, worldIn, pos, player, handIn, SoundEvents.ITEM_BOTTLE_FILL);
		} else if (item == Items.BREAD && honeyLevel > 0) {
			decreaseHoney(BBItems.HONEY_BREAD.get(), honeyLevel, itemstack, state, worldIn, pos, player, handIn, SoundEvents.BLOCK_HONEY_BLOCK_PLACE);
		} else if (item == Items.APPLE && honeyLevel > 0) {
			decreaseHoney(BBItems.HONEY_APPLE.get(), honeyLevel, itemstack, state, worldIn, pos, player, handIn, SoundEvents.BLOCK_HONEY_BLOCK_PLACE);
		} else if (item == Items.COOKED_PORKCHOP && honeyLevel > 0) {
			decreaseHoney(BBItems.GLAZED_PORKCHOP.get(), honeyLevel, itemstack, state, worldIn, pos, player, handIn, SoundEvents.BLOCK_HONEY_BLOCK_PLACE);
		} else if (item == Items.HONEY_BOTTLE && honeyLevel < 4) {
			increaseHoney(Items.GLASS_BOTTLE, honeyLevel, itemstack, state, worldIn, pos, player, handIn, SoundEvents.ITEM_BOTTLE_EMPTY);
		} else if (item == BBItems.HONEY_WAND.get() && honeyLevel > 0) {
			worldIn.setBlockState(pos, state.with(HONEY_LEVEL, honeyLevel - 1));
	        worldIn.playSound(player, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.BLOCK_HONEY_BLOCK_BREAK, SoundCategory.NEUTRAL, 1.0F, 1.0F);
            player.setHeldItem(handIn, new ItemStack(BBItems.STICKY_HONEY_WAND.get()));
			return ActionResultType.SUCCESS;
		} else if (item == BBItems.STICKY_HONEY_WAND.get() && honeyLevel < 4) {
			worldIn.setBlockState(pos, state.with(HONEY_LEVEL, honeyLevel + 1));
	        worldIn.playSound(player, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.BLOCK_HONEY_BLOCK_BREAK, SoundCategory.NEUTRAL, 1.0F, 1.0F);
            player.setHeldItem(handIn, new ItemStack(BBItems.HONEY_WAND.get()));
			return ActionResultType.SUCCESS;
		}  else if (item == Items.HONEYCOMB && honeyLevel < 4) {
			worldIn.setBlockState(pos, state.with(HONEY_LEVEL, honeyLevel + 1));
	        worldIn.playSound(player, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.BLOCK_HONEY_BLOCK_PLACE, SoundCategory.NEUTRAL, 1.0F, 1.0F);
			itemstack.shrink(1);
			return ActionResultType.SUCCESS;
		} else if (item == Items.HONEY_BLOCK && honeyLevel == 0) {
			worldIn.setBlockState(pos, state.with(HONEY_LEVEL, 4));
			worldIn.playSound(player, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.BLOCK_HONEY_BLOCK_PLACE, SoundCategory.NEUTRAL, 1.0F, 1.0F);
			itemstack.shrink(1);
			return ActionResultType.SUCCESS;
		} else if (honeyLevel == 4) {
			worldIn.setBlockState(pos, state.with(HONEY_LEVEL, 0));
	        worldIn.playSound(player, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.BLOCK_HONEY_BLOCK_PLACE, SoundCategory.NEUTRAL, 1.0F, 1.0F);
	        if (itemstack.isEmpty()) {
	            player.setHeldItem(handIn, new ItemStack(Items.HONEY_BLOCK));
	         } else if (!player.inventory.addItemStackToInventory(new ItemStack(Items.HONEY_BLOCK))) {
	            player.dropItem(new ItemStack(Items.HONEY_BLOCK), false);
	         }
			return ActionResultType.SUCCESS;
		} 
		
		return ActionResultType.FAIL;
	}
	
	public ActionResultType decreaseHoney(Item output, int honeyLevel, ItemStack itemstack, BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, SoundEvent soundEvent) {
		worldIn.setBlockState(pos, state.with(HONEY_LEVEL, honeyLevel - 1));
		itemstack.shrink(1);
        worldIn.playSound(player, player.getPosX(), player.getPosY(), player.getPosZ(), soundEvent, SoundCategory.NEUTRAL, 1.0F, 1.0F);
        if (itemstack.isEmpty()) {
           player.setHeldItem(handIn, new ItemStack(output));
        } else if (!player.inventory.addItemStackToInventory(new ItemStack(output))) {
           player.dropItem(new ItemStack(output), false);
        }
		return ActionResultType.SUCCESS;
	}
	
	public ActionResultType increaseHoney(Item output, int honeyLevel, ItemStack itemstack, BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, SoundEvent soundEvent) {
		worldIn.setBlockState(pos, state.with(HONEY_LEVEL, honeyLevel + 1));
		itemstack.shrink(1);
        worldIn.playSound(player, player.getPosX(), player.getPosY(), player.getPosZ(), soundEvent, SoundCategory.NEUTRAL, 1.0F, 1.0F);
        if (itemstack.isEmpty()) {
           player.setHeldItem(handIn, new ItemStack(output));
        } else if (!player.inventory.addItemStackToInventory(new ItemStack(output))) {
           player.dropItem(new ItemStack(output), false);
        }
		return ActionResultType.SUCCESS;
	}
	
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(HONEY_LEVEL);	
	}
	
	public HoneyPotBlock(Properties properties) {
		super(properties);
    	this.setDefaultState(this.getDefaultState().with(HONEY_LEVEL, 0));
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
	     return SHAPES[state.get(HONEY_LEVEL)];
	}
	
	@Override
	public VoxelShape getRaytraceShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return VoxelShapes.fullCube();
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
	      return SHAPES[state.get(HONEY_LEVEL)];
	}
}
