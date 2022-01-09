package com.teamabnormals.buzzier_bees.common.items;

import com.teamabnormals.buzzier_bees.core.registry.BBItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BeehiveBlock;
import net.minecraft.world.level.block.state.BlockState;

public class HoneyWandItem extends Item {

	public HoneyWandItem(Item.Properties properties) {
		super(properties);
	}

	public InteractionResult useOn(UseOnContext context) {
		Level world = context.getLevel();
		BlockPos blockpos = context.getClickedPos();
		BlockState blockstate = world.getBlockState(blockpos);

		if (blockstate.getBlock() instanceof BeehiveBlock && blockstate.getValue(BeehiveBlock.HONEY_LEVEL) == 5 && !context.getPlayer().isCrouching()) {
			Player player = context.getPlayer();
			BlockState blockstate2 = null;
			world.playSound(null, blockpos, SoundEvents.HONEY_BLOCK_BREAK, SoundSource.NEUTRAL, 1.0F, 1.0F);
			blockstate2 = blockstate.setValue(BeehiveBlock.HONEY_LEVEL, 0);
			if (!world.isClientSide) {
				world.setBlock(blockpos, blockstate2, 11);
				if (player != null && !player.getAbilities().instabuild) {
					context.getPlayer().setItemInHand(context.getHand(), new ItemStack(BBItems.STICKY_HONEY_WAND.get()));
				}
			}
			return InteractionResult.SUCCESS;
		} else {
			return InteractionResult.PASS;
		}
	}
}

