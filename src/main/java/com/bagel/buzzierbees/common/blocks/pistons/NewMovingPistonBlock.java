package com.bagel.buzzierbees.common.blocks.pistons;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.PistonType;
import net.minecraft.tileentity.PistonTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameters;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class NewMovingPistonBlock extends ContainerBlock {
    public static final DirectionProperty FACING = PistonHeadBlock.FACING;
    public static final EnumProperty<PistonType> TYPE = PistonHeadBlock.TYPE;

    public NewMovingPistonBlock(Block.Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(TYPE, PistonType.DEFAULT));
    }

    @Nullable
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return null;
    }

    public static NewPistonTileEntity createTilePiston(BlockState p_196343_0_, Direction p_196343_1_, boolean p_196343_2_, boolean p_196343_3_) {
        return new NewPistonTileEntity(p_196343_0_, p_196343_1_, p_196343_2_, p_196343_3_);
    }

    @SuppressWarnings("deprecation")
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof NewPistonTileEntity) {
                ((NewPistonTileEntity)tileentity).clearPistonTileEntity();
            }

        }
    }

    /**
     * Called after a player destroys this Block - the posiiton pos may no longer hold the state indicated.
     */
    public void onPlayerDestroy(IWorld worldIn, BlockPos pos, BlockState state) {
        BlockPos blockpos = pos.offset(state.get(FACING).getOpposite());
        BlockState blockstate = worldIn.getBlockState(blockpos);
        if (blockstate.getBlock() instanceof NewPistonBlock && blockstate.get(NewPistonBlock.EXTENDED)) {
            worldIn.removeBlock(blockpos, false);
        }

    }

    @SuppressWarnings("deprecation")
    public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return false;
    }

    @SuppressWarnings("deprecation")
    public boolean func_229869_c_(BlockState p_229869_1_, IBlockReader p_229869_2_, BlockPos p_229869_3_) {
        return false;
    }

    @SuppressWarnings("deprecation")
    public ActionResultType func_225533_a_(BlockState p_225533_1_, World p_225533_2_, BlockPos p_225533_3_, PlayerEntity p_225533_4_, Hand p_225533_5_, BlockRayTraceResult p_225533_6_) {
        if (!p_225533_2_.isRemote && p_225533_2_.getTileEntity(p_225533_3_) == null) {
            p_225533_2_.removeBlock(p_225533_3_, false);
            return ActionResultType.CONSUME;
        } else {
            return ActionResultType.PASS;
        }
    }

    @SuppressWarnings("deprecation")
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        NewPistonTileEntity pistontileentity = this.func_220170_a(builder.getWorld(), builder.assertPresent(LootParameters.POSITION));
        return pistontileentity == null ? Collections.emptyList() : pistontileentity.getPistonState().getDrops(builder);
    }

    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.empty();
    }

    @SuppressWarnings("deprecation")
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        NewPistonTileEntity pistontileentity = this.func_220170_a(worldIn, pos);
        return pistontileentity != null ? pistontileentity.getCollisionShape(worldIn, pos) : VoxelShapes.empty();
    }

    @Nullable
    private NewPistonTileEntity func_220170_a(IBlockReader p_220170_1_, BlockPos p_220170_2_) {
        TileEntity tileentity = p_220170_1_.getTileEntity(p_220170_2_);
        return tileentity instanceof NewPistonTileEntity ? (NewPistonTileEntity)tileentity : null;
    }

    @SuppressWarnings("deprecation")
    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
        return ItemStack.EMPTY;
    }

    @SuppressWarnings("deprecation")
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @SuppressWarnings("deprecation")
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, TYPE);
    }

    @SuppressWarnings("deprecation")
    public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
        return false;
    }
}
