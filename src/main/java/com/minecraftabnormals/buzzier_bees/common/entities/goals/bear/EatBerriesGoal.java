package com.minecraftabnormals.buzzier_bees.common.entities.goals.bear;

import com.minecraftabnormals.buzzier_bees.common.entities.GrizzlyBearEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

import java.util.Random;

public class EatBerriesGoal extends MoveToBlockGoal {
	protected int field_220731_g;
	protected final GrizzlyBearEntity bear;

	public EatBerriesGoal(GrizzlyBearEntity bear, double p_i50737_2_, int p_i50737_4_, int p_i50737_5_) {
		super(bear, p_i50737_2_, p_i50737_4_, p_i50737_5_);
		this.bear = bear;
	}

	public double getTargetDistanceSq() {
		return 2.0D;
	}

	public boolean shouldMove() {
		return this.timeoutCounter % 100 == 0;
	}

	/**
	 * Return true to set given position as destination
	 */
	protected boolean shouldMoveTo(IWorldReader worldIn, BlockPos pos) {
		BlockState blockstate = worldIn.getBlockState(pos);
		return blockstate.getBlock() == Blocks.SWEET_BERRY_BUSH && blockstate.get(SweetBerryBushBlock.AGE) >= 2;
	}

	/**
	 * Keep ticking a continuous task that has already been started
	 */
	public void tick() {
		if (this.getIsAboveDestination()) {
			if (this.field_220731_g >= 40) {
				this.eatBerry();
			} else {
				++this.field_220731_g;
			}
		} else if (!this.getIsAboveDestination() && (new Random()).nextFloat() < 0.05F) {
			bear.playSound(SoundEvents.ENTITY_FOX_SNIFF, 1.0F, 1.0F);
		}

		super.tick();
	}

	protected void eatBerry() {
		if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(bear.world, bear)) {
			BlockState blockstate = bear.world.getBlockState(this.destinationBlock);
			if (blockstate.getBlock() == Blocks.SWEET_BERRY_BUSH) {
				int i = blockstate.get(SweetBerryBushBlock.AGE);
				blockstate.with(SweetBerryBushBlock.AGE, Integer.valueOf(1));
				int j = 1 + bear.world.rand.nextInt(2) + (i == 3 ? 1 : 0);
				ItemStack itemstack = bear.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
				if (itemstack.isEmpty()) {
					bear.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.SWEET_BERRIES));
					--j;
				}

				if (j > 0) {
					Block.spawnAsEntity(bear.world, this.destinationBlock, new ItemStack(Items.SWEET_BERRIES, j));
				}

				bear.playSound(SoundEvents.ITEM_SWEET_BERRIES_PICK_FROM_BUSH, 1.0F, 1.0F);
				bear.world.setBlockState(this.destinationBlock, blockstate.with(SweetBerryBushBlock.AGE, Integer.valueOf(1)), 2);
			}
		}
	}

	/**
	 * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
	 * method as well.
	 */
	public boolean shouldExecute() {
		return !bear.isSleeping() && super.shouldExecute();
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void startExecuting() {
		this.field_220731_g = 0;
		super.startExecuting();
	}
}
