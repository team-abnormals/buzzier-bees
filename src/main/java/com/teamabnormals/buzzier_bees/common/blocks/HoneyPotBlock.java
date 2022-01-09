package com.teamabnormals.buzzier_bees.common.blocks;

import com.mojang.datafixers.util.Pair;
import com.teamabnormals.buzzier_bees.core.registry.BBItems;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.*;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.Random;

public class HoneyPotBlock extends Block implements WorldlyContainerHolder {
	public static final IntegerProperty HONEY_LEVEL = IntegerProperty.create("honey_level", 0, 4);
	private static final VoxelShape[] SHAPES = Util.make(new VoxelShape[5], (levels) -> {
		for (int i = 0; i < 5; i++) {
			int level = i >= 1 ? i + 1 : i;
			levels[i] = Shapes.join(Shapes.block(), Block.box(3.0D, Math.max(2, level * 3), 3.0D, 13.0D, 16.0D, 13.0D), BooleanOp.ONLY_FIRST);
		}
	});

	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
		ItemStack stack = player.getItemInHand(handIn);
		Pair<ItemStack, Integer> output = getOutput(stack, state.getValue(HONEY_LEVEL));
		ItemStack outputItem = output.getFirst();

		if (outputItem != ItemStack.EMPTY) {
			player.swing(handIn);
			if (outputItem.getItem() == Items.HONEY_BLOCK) {
				attemptEmpty(state, worldIn, pos, output);
			} else {
				if (!player.getAbilities().instabuild) {
					stack.shrink(1);
					if (stack.isEmpty()) {
						player.setItemInHand(handIn, outputItem);
					} else if (!player.getInventory().add(outputItem)) {
						player.drop(outputItem, false);
					}
				}
			}

			if (!worldIn.isClientSide) {
				worldIn.setBlockAndUpdate(pos, state.setValue(HONEY_LEVEL, output.getSecond()));
			}
			worldIn.playSound(null, pos, SoundEvents.HONEY_BLOCK_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
			return InteractionResult.sidedSuccess(worldIn.isClientSide());
		}

		return InteractionResult.PASS;
	}

	private static BlockState attemptEmpty(BlockState state, Level world, BlockPos pos, Pair<ItemStack, Integer> output) {
		if (!world.isClientSide) {
			double d0 = (double) (world.random.nextFloat() * 0.7F) + (double) 0.15F;
			double d1 = (double) (world.random.nextFloat() * 0.7F) + (double) 0.060000002F + 0.6D;
			double d2 = (double) (world.random.nextFloat() * 0.7F) + (double) 0.15F;
			ItemEntity itementity = new ItemEntity(world, (double) pos.getX() + d0, (double) pos.getY() + d1, (double) pos.getZ() + d2, output.getFirst());
			itementity.setDefaultPickUpDelay();
			world.addFreshEntity(itementity);
		}

		return state;
	}

	private static Pair<ItemStack, Integer> getOutput(ItemStack input, int level) {
		Item item = input.getItem();
		Pair<ItemStack, Integer> output = Pair.of(ItemStack.EMPTY, level);

		boolean notEmpty = level > 0;
		boolean notFull = level < 4;

		if (item == Items.GLASS_BOTTLE && notEmpty)
			output = createItemStack(Items.HONEY_BOTTLE, level - 1);

		else if (item == Items.BREAD && notEmpty)
			output = createItemStack(BBItems.HONEY_BREAD.get(), level - 1);

		else if (item == Items.APPLE && notEmpty)
			output = createItemStack(BBItems.HONEY_APPLE.get(), level - 1);

		else if (item == Items.COOKED_PORKCHOP && notEmpty)
			output = createItemStack(BBItems.GLAZED_PORKCHOP.get(), level - 1);

		else if (item == BBItems.HONEY_WAND.get() && notEmpty)
			output = createItemStack(BBItems.STICKY_HONEY_WAND.get(), level - 1);

		else if (item == Items.AIR && level == 4)
			output = createItemStack(Items.HONEY_BLOCK, level - 4);

		else if (item == Items.HONEY_BOTTLE && notFull)
			output = createItemStack(Items.GLASS_BOTTLE, level + 1);

		else if (item == BBItems.STICKY_HONEY_WAND.get() && notFull)
			output = createItemStack(BBItems.HONEY_WAND.get(), level + 1);

		else if (item == Items.HONEY_BLOCK && level == 0)
			output = createItemStack(Items.AIR, level + 4);

		else if (item == Items.HONEYCOMB && notFull) {
			Random rand = new Random();
			int amount = rand.nextInt(3) == 0 ? 1 : 0;
			output = createItemStack(Items.AIR, level + amount);
		}

		return output;
	}

	private static Pair<ItemStack, Integer> createItemStack(Item item, int level) {
		return Pair.of(new ItemStack(item), level);
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(HONEY_LEVEL);
	}

	public HoneyPotBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(HONEY_LEVEL, 0));
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return SHAPES[state.getValue(HONEY_LEVEL)];
	}

	@Override
	public VoxelShape getInteractionShape(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return Shapes.block();
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return SHAPES[state.getValue(HONEY_LEVEL)];
	}

	@Override
	public boolean hasAnalogOutputSignal(BlockState state) {
		return true;
	}

	@Override
	public int getAnalogOutputSignal(BlockState blockState, Level worldIn, BlockPos pos) {
		return blockState.getValue(HONEY_LEVEL);
	}

	public WorldlyContainer getContainer(BlockState state, LevelAccessor world, BlockPos pos) {
		int i = state.getValue(HONEY_LEVEL);
		if (i == 4) {
			return new HoneyPotBlock.FullInventory(state, world, pos, new ItemStack(Items.HONEY_BLOCK));
		} else {
			return i < 7 ? new PartialInventory(state, world, pos) : new EmptyInventory();
		}
	}

	static class EmptyInventory extends SimpleContainer implements WorldlyContainer {
		public EmptyInventory() {
			super(0);
		}

		public int[] getSlotsForFace(Direction side) {
			return new int[0];
		}

		public boolean canPlaceItemThroughFace(int index, ItemStack itemStackIn, @Nullable Direction direction) {
			return false;
		}

		public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
			return false;
		}
	}

	static class FullInventory extends SimpleContainer implements WorldlyContainer {
		private final BlockState state;
		private final LevelAccessor world;
		private final BlockPos pos;
		private boolean extracted;

		public FullInventory(BlockState state, LevelAccessor world, BlockPos pos, ItemStack stack) {
			super(stack);
			this.state = state;
			this.world = world;
			this.pos = pos;
		}

		public int getMaxStackSize() {
			return 1;
		}

		public int[] getSlotsForFace(Direction side) {
			return side == Direction.DOWN ? new int[]{0} : new int[0];
		}

		public boolean canPlaceItemThroughFace(int index, ItemStack itemStackIn, @Nullable Direction direction) {
			return false;
		}

		public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
			return !this.extracted && direction == Direction.DOWN && stack.getItem() == Items.HONEY_BLOCK;
		}

		public void setChanged() {
			world.setBlock(pos, state.setValue(HONEY_LEVEL, Integer.valueOf(0)), 3);
			this.extracted = true;
		}
	}

	static class PartialInventory extends SimpleContainer implements WorldlyContainer {
		private final BlockState state;
		private final LevelAccessor world;
		private final BlockPos pos;
		private boolean inserted;

		public PartialInventory(BlockState state, LevelAccessor world, BlockPos pos) {
			super(1);
			this.state = state;
			this.world = world;
			this.pos = pos;
		}

		public int getMaxStackSize() {
			return 1;
		}

		public int[] getSlotsForFace(Direction side) {
			return side == Direction.UP ? new int[]{0} : new int[0];
		}

		public boolean canPlaceItemThroughFace(int index, ItemStack itemStackIn, @Nullable Direction direction) {
			Item item = itemStackIn.getItem();
			return !this.inserted && direction == Direction.UP && (item == Items.HONEYCOMB || item == Items.HONEY_BLOCK);
		}

		public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
			return false;
		}

		public void setChanged() {
			ItemStack itemstack = this.getItem(0);
			if (!itemstack.isEmpty()) {
				this.inserted = true;
				attemptInteract(state, pos, (Level) world, itemstack);
				this.removeItemNoUpdate(0);
			}
		}

		private boolean attemptInteract(BlockState state, BlockPos pos, Level world, ItemStack stack) {
			int level = state.getValue(HONEY_LEVEL);
			if (stack.getItem() == Items.HONEYCOMB && level < 4) {
				if (world.random.nextInt(3) == 0) {
					world.setBlockAndUpdate(pos, state.setValue(HONEY_LEVEL, level + 1));
					return true;
				}
			} else if (stack.getItem() == Items.HONEY_BLOCK && level == 0) {
				world.setBlockAndUpdate(pos, state.setValue(HONEY_LEVEL, level + 4));
				return true;
			}
			return false;
		}
	}
}
