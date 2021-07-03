package com.minecraftabnormals.buzzier_bees.common.blocks;

import javax.annotation.Nullable;

import com.minecraftabnormals.buzzier_bees.core.other.HoneyPotManager;
import com.mojang.datafixers.util.Pair;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ISidedInventoryProvider;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;

public class HoneyPotBlock extends Block implements ISidedInventoryProvider {
	public static final IntegerProperty HONEY_LEVEL = IntegerProperty.create("honey_level", 0, 4);
	private static final VoxelShape[] SHAPES = Util.make(new VoxelShape[5], (levels) -> {
		for (int i = 0; i < 5; i++) {
			int level = i >= 1 ? i + 1 : i;
			levels[i] = VoxelShapes.combineAndSimplify(VoxelShapes.fullCube(), Block.makeCuboidShape(3.0D, Math.max(2, level * 3), 3.0D, 13.0D, 16.0D, 13.0D), IBooleanFunction.ONLY_FIRST);
		}
	});

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		if (worldIn.isRemote)
			return ActionResultType.SUCCESS;
		
		ItemStack stack = player.getHeldItem(handIn);
		Pair<ItemStack, Integer> output = HoneyPotManager.getUsage(stack, state, worldIn.getRandom());

		if (output != null) {
			ItemStack outputItem = output.getFirst();
			player.swingArm(handIn);
			
			ItemHandlerHelper.giveItemToPlayer(player, outputItem);

			if (!worldIn.isRemote) {
				if (!player.abilities.isCreativeMode)
					stack.shrink(1);
				worldIn.setBlockState(pos, state.with(HONEY_LEVEL, output.getSecond()));
				worldIn.playSound((PlayerEntity) null, pos, SoundEvents.BLOCK_HONEY_BLOCK_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
			}
			return ActionResultType.CONSUME;
		}

		return ActionResultType.PASS;
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

	@Override
	public boolean hasComparatorInputOverride(BlockState state) {
		return true;
	}

	@Override
	public int getComparatorInputOverride(BlockState blockState, World worldIn, BlockPos pos) {
		return blockState.get(HONEY_LEVEL);
	}

	public ISidedInventory createInventory(BlockState state, IWorld world, BlockPos pos) {
		int i = state.get(HONEY_LEVEL);
		if (i == 4) {
			return new HoneyPotBlock.FullInventory(state, world, pos, new ItemStack(Items.HONEY_BLOCK));
		} else {
			return i < 7 ? new PartialInventory(state, world, pos) : new EmptyInventory();
		}
	}

	static class EmptyInventory extends Inventory implements ISidedInventory {
		public EmptyInventory() {
			super(0);
		}

		public int[] getSlotsForFace(Direction side) {
			return new int[0];
		}

		public boolean canInsertItem(int index, ItemStack itemStackIn, @Nullable Direction direction) {
			return false;
		}

		public boolean canExtractItem(int index, ItemStack stack, Direction direction) {
			return false;
		}
	}

	static class FullInventory extends Inventory implements ISidedInventory {
		private final BlockState state;
		private final IWorld world;
		private final BlockPos pos;
		private boolean extracted;

		public FullInventory(BlockState state, IWorld world, BlockPos pos, ItemStack stack) {
			super(stack);
			this.state = state;
			this.world = world;
			this.pos = pos;
		}

		public int getInventoryStackLimit() {
			return 1;
		}

		public int[] getSlotsForFace(Direction side) {
			return side == Direction.DOWN ? new int[]{0} : new int[0];
		}

		public boolean canInsertItem(int index, ItemStack itemStackIn, @Nullable Direction direction) {
			return false;
		}

		public boolean canExtractItem(int index, ItemStack stack, Direction direction) {
			return !this.extracted && direction == Direction.DOWN && stack.getItem() == Items.HONEY_BLOCK;
		}

		public void markDirty() {
			world.setBlockState(pos, state.with(HONEY_LEVEL, Integer.valueOf(0)), 3);
			this.extracted = true;
		}
	}

	static class PartialInventory extends Inventory implements ISidedInventory {
		private final BlockState state;
		private final IWorld world;
		private final BlockPos pos;
		private boolean inserted;

		public PartialInventory(BlockState state, IWorld world, BlockPos pos) {
			super(1);
			this.state = state;
			this.world = world;
			this.pos = pos;
		}

		public int getInventoryStackLimit() {
			return 1;
		}

		public int[] getSlotsForFace(Direction side) {
			return side == Direction.UP ? new int[]{0} : new int[0];
		}

		public boolean canInsertItem(int index, ItemStack itemStackIn, @Nullable Direction direction) {
			Item item = itemStackIn.getItem();
			return !this.inserted && direction == Direction.UP && (item == Items.HONEYCOMB || item == Items.HONEY_BLOCK);
		}

		public boolean canExtractItem(int index, ItemStack stack, Direction direction) {
			return false;
		}

		public void markDirty() {
			ItemStack itemstack = this.getStackInSlot(0);
			if (!itemstack.isEmpty()) {
				this.inserted = true;
				attemptInteract(state, pos, (World) world, itemstack);
				this.removeStackFromSlot(0);
			}
		}

		private boolean attemptInteract(BlockState state, BlockPos pos, World world, ItemStack stack) {
			int level = state.get(HONEY_LEVEL);
			if (stack.getItem() == Items.HONEYCOMB && level < 4) {
				if (world.rand.nextInt(3) == 0) {
					world.setBlockState(pos, state.with(HONEY_LEVEL, level + 1));
					return true;
				}
			} else if (stack.getItem() == Items.HONEY_BLOCK && level == 0) {
				world.setBlockState(pos, state.with(HONEY_LEVEL, level + 4));
				return true;
			}
			return false;
		}
	}
}
