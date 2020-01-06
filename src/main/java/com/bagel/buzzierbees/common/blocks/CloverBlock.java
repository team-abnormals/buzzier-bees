 package com.bagel.buzzierbees.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Effect;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.ILightReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;
//with(PATCH, Boolean.valueOf(true)
public class CloverBlock extends FlowerBlock implements IGrowable, IBlockColor {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_0_1;

    protected static final VoxelShape SHAPE_ONE = net.minecraft.block.Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);
    protected static final VoxelShape SHAPE_TWO = net.minecraft.block.Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D);

    public CloverBlock(Effect p_i49984_1_, Properties p_i49984_3_) {
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
        if (state.get(AGE) == 0) {
            int i = Math.min(1, state.get(AGE) + 1);
            world.setBlockState(blockPos, state.with(AGE, Integer.valueOf(i)), 1);
        } else {
            BlockState cloverPatchState  = this.stateContainer.getBaseState().with(AGE, Integer.valueOf(0));
            //BlockState cloverFlowerState = this.stateContainer.getBaseState().with(AGE, Integer.valueOf(1));

            label:
            for(int x = 0; x < 64; ++x) {
                BlockPos newBlockPos = blockPos;

                for(int y = 0; y < x / 16; ++y) {
                    newBlockPos = newBlockPos.add(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
                    if (cloverPatchState.isValidPosition(world, newBlockPos) && world.isAirBlock(newBlockPos)) {
                        world.setBlockState(newBlockPos, cloverPatchState);
                        break label;
                    }
                }
            }
        }
    }
    
    @SuppressWarnings("deprecation")
	public ActionResultType func_225533_a_(BlockState state, World worldIn, BlockPos pos, PlayerEntity entity, Hand hand, BlockRayTraceResult p_225533_6_) {
        ItemStack itemstack = entity.getHeldItem(hand);
        int age = state.get(AGE);
        if (age >= 1) {
           if (itemstack.getItem() == Items.SHEARS) {
              worldIn.playSound(entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.NEUTRAL, 1.0F, 1.0F);
              spawnAsEntity(worldIn, pos, new ItemStack(this, 1));
              itemstack.damageItem(1, entity, (p_226874_1_) -> {
                 p_226874_1_.sendBreakAnimation(hand);
              });
              worldIn.setBlockState(pos, state.with(AGE, 0), 1);
              return ActionResultType.SUCCESS;
           }
        }
        return super.func_225533_a_(state, worldIn, pos, entity, hand, p_225533_6_);
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


	@Override
	public int getColor(BlockState p_getColor_1_, ILightReader p_getColor_2_, BlockPos p_getColor_3_,
			int p_getColor_4_) {
		// TODO Auto-generated method stub
		return 0;
	}


}
