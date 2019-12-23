package com.bagel.buzzierbees.common.blocks;

import com.bagel.buzzierbees.common.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class CloverPatchBlock extends FlowerBlock implements IGrowable {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_0_1;
    protected static final VoxelShape SHAPE_ONE = net.minecraft.block.Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);
    protected static final VoxelShape SHAPE_TWO = net.minecraft.block.Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D);

    public CloverPatchBlock(Effect p_i49984_1_, Properties p_i49984_3_) {
        super(p_i49984_1_, 8, p_i49984_3_);
        this.setDefaultState(this.stateContainer.getBaseState().with(AGE, Integer.valueOf(1)));
    }

    @Override
    public boolean canGrow(IBlockReader iBlockReader, BlockPos blockPos, BlockState blockState, boolean b) {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World world, Random random, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    public void func_225535_a_(ServerWorld world, Random random, BlockPos blockPos, BlockState state) {
        CloverPatchBlock cloverBlock = (CloverPatchBlock)ModBlocks.CLOVER;
        if (state.get(AGE) == 0) {
            int i = Math.min(1, state.get(AGE) + 1);
            world.setBlockState(blockPos, state.with(AGE, Integer.valueOf(i)), 1);
        } else {
            //TEMP IMPLEMENTATION
            //It should spread clover patches around it if bonemealed...
            if(!world.isRemote) {
                BlockState blockstate = state.with(AGE, Integer.valueOf(0));
                cont:
                for(int i = 0; i < 128; ++i) {
                    BlockPos newBlockPos = blockPos;

                    for(int j = 0; j < i / 16; j++) {
                         newBlockPos = newBlockPos.add(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
                        if(Block.isOpaque(world.getBlockState(newBlockPos).getCollisionShape(world, newBlockPos))) {
                            continue cont;
                        }
                    }

                    if(blockstate.isValidPosition(world, newBlockPos) && world.isAirBlock(newBlockPos) && random.nextFloat() <= 0.10F) {
                        world.setBlockState(newBlockPos, state.with(AGE, Integer.valueOf(random.nextInt(2))));
                    }
                }
            }
            //spawnAsEntity(world, pos, new ItemStack(this));
        }
    }

    public VoxelShape getShape(BlockState state, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        switch(state.get(AGE)){
            default:
                return SHAPE_TWO;
            case 0:
                return SHAPE_ONE;
        }
    }

    public OffsetType getOffsetType() {
        return OffsetType.NONE;
    }
    
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
