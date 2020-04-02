package com.bagel.buzzierbees.common.blocks;

import java.util.function.Supplier;

import com.bagel.buzzierbees.core.registry.BBBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;

public class HangingFlowerPotBlock extends Block {
	protected static final VoxelShape SHAPE = Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D);
	private final Supplier<Block> flower;
	
	public HangingFlowerPotBlock(Supplier<Block> flower, Block.Properties properties) {
		super(properties);
		this.flower = flower;
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		ItemStack itemstack = player.getHeldItem(handIn);
		ResourceLocation pot = new ResourceLocation(("buzzierbees:hanging_potted_" + itemstack.getItem().getRegistryName().getPath()));
		if (ForgeRegistries.BLOCKS.containsKey(pot) && this.getBlock() == BBBlocks.HANGING_FLOWER_POT.get() && !(itemstack.getItem() == Items.SWEET_BERRIES && ModList.get().isLoaded("berry_good"))) {
			Block potBlock = ForgeRegistries.BLOCKS.getValue(pot);
			worldIn.setBlockState(pos, potBlock.getDefaultState(), 3);
			player.addStat(Stats.POT_FLOWER);
			if (!player.abilities.isCreativeMode) {
				itemstack.shrink(1);
			}
			return ActionResultType.SUCCESS;
		} else {
			if (player.getHeldItem(handIn).getItem() == Blocks.AIR.asItem()) {
				player.setHeldItem(handIn, new ItemStack(this.flower.get().asItem()));
				worldIn.setBlockState(pos, BBBlocks.HANGING_FLOWER_POT.get().getDefaultState());
			}
			return ActionResultType.CONSUME;
		}
	}
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}
	
	@Override
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return this.flower == Blocks.AIR ? new ItemStack(Blocks.FLOWER_POT) : new ItemStack(this.flower.get().asItem());
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		return facing == Direction.DOWN && !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}
}
