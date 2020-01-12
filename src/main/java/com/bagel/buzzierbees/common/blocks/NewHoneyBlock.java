package com.bagel.buzzierbees.common.blocks;

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
        //if (state.getBlock() == ModBlocks.HONEY_BLOCK.get() && other.getBlock() == ModBlocks.SLIME_BLOCK.get()) return false;
        //if (state.getBlock() == ModBlocks.SLIME_BLOCK.get() && other.getBlock() == ModBlocks.HONEY_BLOCK.get()) return false;
        return state.isStickyBlock() || other.isStickyBlock();
    }
}
