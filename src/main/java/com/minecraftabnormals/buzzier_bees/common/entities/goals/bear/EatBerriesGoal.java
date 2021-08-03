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
	protected int ticksWaited;
	protected final GrizzlyBearEntity bear;

	public EatBerriesGoal(GrizzlyBearEntity bear, double p_i50737_2_, int p_i50737_4_, int p_i50737_5_) {
		super(bear, p_i50737_2_, p_i50737_4_, p_i50737_5_);
		this.bear = bear;
	}

	public double acceptedDistance() {
		return 2.0D;
	}

	public boolean shouldRecalculatePath() {
		return this.tryTicks % 100 == 0;
	}

	/**
	 * Return true to set given position as destination
	 */
	protected boolean isValidTarget(IWorldReader worldIn, BlockPos pos) {
		BlockState blockstate = worldIn.getBlockState(pos);
		return blockstate.getBlock() == Blocks.SWEET_BERRY_BUSH && blockstate.getValue(SweetBerryBushBlock.AGE) >= 2;
	}

	/**
	 * Keep ticking a continuous task that has already been started
	 */
	public void tick() {
		if (this.isReachedTarget()) {
			if (this.ticksWaited >= 40) {
				this.eatBerry();
			} else {
				++this.ticksWaited;
			}
		} else if (!this.isReachedTarget() && (new Random()).nextFloat() < 0.05F) {
			bear.playSound(SoundEvents.FOX_SNIFF, 1.0F, 1.0F);
		}

		super.tick();
	}

	protected void eatBerry() {
		if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(bear.level, bear)) {
			BlockState blockstate = bear.level.getBlockState(this.blockPos);
			if (blockstate.getBlock() == Blocks.SWEET_BERRY_BUSH) {
				int i = blockstate.getValue(SweetBerryBushBlock.AGE);
				blockstate.setValue(SweetBerryBushBlock.AGE, Integer.valueOf(1));
				int j = 1 + bear.level.random.nextInt(2) + (i == 3 ? 1 : 0);
				ItemStack itemstack = bear.getItemBySlot(EquipmentSlotType.MAINHAND);
				if (itemstack.isEmpty()) {
					bear.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.SWEET_BERRIES));
					--j;
				}

				if (j > 0) {
					Block.popResource(bear.level, this.blockPos, new ItemStack(Items.SWEET_BERRIES, j));
				}

				bear.playSound(SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, 1.0F, 1.0F);
				bear.level.setBlock(this.blockPos, blockstate.setValue(SweetBerryBushBlock.AGE, Integer.valueOf(1)), 2);
			}
		}
	}

	/**
	 * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
	 * method as well.
	 */
	public boolean canUse() {
		return !bear.isSleeping() && super.canUse();
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void start() {
		this.ticksWaited = 0;
		super.start();
	}
}
