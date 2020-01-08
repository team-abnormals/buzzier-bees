package com.bagel.buzzierbees.common.blocks.stickyblocks;

import com.bagel.buzzierbees.common.blocks.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.HoneyBlock;

public class NewHoneyBlock extends HoneyBlock {
    public NewHoneyBlock(Properties p_i225762_1_) {
        super(p_i225762_1_);
    }

    @Override
    public boolean isSlimeBlock(BlockState state) {
        return false;
    }

    @Override
    public boolean isStickyBlock(BlockState state) {
        return true;
    }

    @Override
    public boolean canStickTo(BlockState state, BlockState other) {
        if (state.getBlock() == ModBlocks.HONEY_BLOCK && other.getBlock() == ModBlocks.SLIME_BLOCK) return false;
        if (state.getBlock() == ModBlocks.SLIME_BLOCK && other.getBlock() == ModBlocks.HONEY_BLOCK) return false;
        return state.isStickyBlock() || other.isStickyBlock();
    }
}
