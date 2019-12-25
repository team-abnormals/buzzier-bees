package com.bagel.buzzierbees.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.FallingBlock;
import net.minecraft.util.math.shapes.VoxelShape;

//TODO...
public class CloverHoneyBlock extends FallingBlock {
    protected static final VoxelShape field_226930_a_ = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D);

    public CloverHoneyBlock(Properties p_i48440_1_) {
        super(p_i48440_1_);
    }
}
