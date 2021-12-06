package com.minecraftabnormals.buzzier_bees.common.items;

import com.minecraftabnormals.buzzier_bees.core.registry.BBItems;
import net.minecraft.block.BeehiveBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HoneyWandItem extends Item {

	public HoneyWandItem(Item.Properties properties) {
		super(properties);
	}

	public ActionResultType useOn(ItemUseContext context) {
		World world = context.getLevel();
		BlockPos blockpos = context.getClickedPos();
		BlockState blockstate = world.getBlockState(blockpos);

		if (blockstate.getBlock() instanceof BeehiveBlock && blockstate.getValue(BeehiveBlock.HONEY_LEVEL) == 5 && !context.getPlayer().isCrouching()) {
			PlayerEntity player = context.getPlayer();
			BlockState blockstate2 = null;
			world.playSound(null, blockpos, SoundEvents.HONEY_BLOCK_BREAK, SoundCategory.NEUTRAL, 1.0F, 1.0F);
			blockstate2 = blockstate.setValue(BeehiveBlock.HONEY_LEVEL, 0);
			if (!world.isClientSide) {
				world.setBlock(blockpos, blockstate2, 11);
				if (player != null && !player.abilities.instabuild) {
					context.getPlayer().setItemInHand(context.getHand(), new ItemStack(BBItems.STICKY_HONEY_WAND.get()));
				}
			}
			return ActionResultType.SUCCESS;
		} else {
			return ActionResultType.PASS;
		}
	}
}

