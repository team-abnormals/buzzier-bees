package com.minecraftabnormals.buzzier_bees.common.blocks;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.DyeColor;
import net.minecraft.item.FlintAndSteelItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import vazkii.quark.api.IEnchantmentInfluencer;

import javax.annotation.Nullable;
import java.util.Random;

public class CandleBlock extends Block implements IWaterLoggable, IEnchantmentInfluencer {

	public static final IntegerProperty CANDLES = IntegerProperty.create("candles", 1, 4);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final BooleanProperty LIT = BlockStateProperties.LIT;
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	private final DyeColor color;

	public static final double XZ_PARTICLE_SPEED = 0.002F;
	public static final double Y_PARTICLE_SPEED = 0.01F;

	protected static final VoxelShape[] SHAPES = new VoxelShape[]{
			Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 9.0D, 10.0D),
			Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 9.0D, 13.0D),
			Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 9.0D, 13.0D),
			Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 9.0D, 13.0D)};

	public CandleBlock(DyeColor color, Properties properties) {
		super(properties);
		this.color = color;
		this.setDefaultState(this.getDefaultState().with(CANDLES, 1).with(WATERLOGGED, true).with(LIT, true));
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		BlockState blockstate = context.getWorld().getBlockState(context.getPos());
		Direction direction = context.getPlacementHorizontalFacing();
		if (blockstate.getBlock() == this) {
			return blockstate.with(FACING, direction).with(CANDLES, Math.min(4, blockstate.get(CANDLES) + 1));
		} else {
			FluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
			boolean flag = ifluidstate.isTagged(FluidTags.WATER) && ifluidstate.getLevel() == 8;
			return this.getDefaultState().with(FACING, direction).with(WATERLOGGED, flag).with(LIT, !flag);
		}
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		if (!state.get(LIT) && player.getHeldItem(handIn).getItem() instanceof FlintAndSteelItem) {
			worldIn.playSound(player, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, worldIn.getRandom().nextFloat() * 0.4F + 0.8F);
			worldIn.setBlockState(pos, state.with(BlockStateProperties.LIT, Boolean.TRUE), 11);
			player.getHeldItem(handIn).damageItem(1, player, (p_219999_1_) -> {
				p_219999_1_.sendBreakAnimation(handIn);
			});
			return ActionResultType.func_233537_a_(worldIn.isRemote());
		} else if (state.get(LIT) && player.getHeldItem(handIn).getItem() instanceof ShovelItem) {
			if (!worldIn.isRemote()) {
				worldIn.playEvent(null, 1009, pos, 0);
			}
			player.getHeldItem(handIn).damageItem(1, player, (p_219999_1_) -> {
				p_219999_1_.sendBreakAnimation(handIn);
			});
			worldIn.setBlockState(pos, state.with(BlockStateProperties.LIT, Boolean.FALSE));
			return ActionResultType.func_233537_a_(worldIn.isRemote());
		} else {
			return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
		}
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.with(FACING, rot.rotate(state.get(FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return mirrorIn == Mirror.NONE ? state : state.rotate(mirrorIn.toRotation(state.get(FACING)));
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		return hasEnoughSolidSide(worldIn, pos.down(), Direction.UP);
	}

	@Override
	public float getEnchantPowerBonus(BlockState state, IWorldReader world, BlockPos pos) {
		return (0.1F * state.get(CANDLES));
	}

	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (!state.isValidPosition(worldIn, currentPos)) {
			return Blocks.AIR.getDefaultState();
		} else {
			if (state.get(WATERLOGGED)) {
				if (state.get(LIT))
					worldIn.setBlockState(currentPos, state.with(LIT, false), 2);
				worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
			}
			return super.updatePostPlacement(state, facing, facingState, worldIn, currentPos, facingPos);
		}
	}

	@Override
	public boolean isReplaceable(BlockState state, BlockItemUseContext useContext) {
		return useContext.getItem().getItem() == this.asItem() && state.get(CANDLES) < 4 || super.isReplaceable(state, useContext);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPES[state.get(CANDLES) - 1];
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(CANDLES, WATERLOGGED, LIT, FACING);
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
		return true;
	}

	@Override
	public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
		return type == PathType.AIR && !this.canCollide || super.allowsMovement(state, worldIn, pos, type);
	}

	public IParticleData getFlameParticle() {
		return ParticleTypes.FLAME;
	}

	public IParticleData getSmokeParticle() {
		return ParticleTypes.SMOKE;
	}

	public DyeColor getColor() {
		return this.color;
	}

	@Nullable
	public DyeColor getEnchantmentInfluenceColor(IBlockReader world, BlockPos pos, BlockState state) {
		return this.getColor();
	}

	@Override
	public int getInfluenceStack(IBlockReader world, BlockPos pos, BlockState state) {
		return state.get(CANDLES);
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {
		if (state.get(LIT)) {
			double x = pos.getX();
			double y = pos.getY();
			double z = pos.getZ();
			IParticleData smokeParticle = this.getSmokeParticle();
			IParticleData flameParticle = this.getFlameParticle();

			switch (state.get(CANDLES)) {
				case 1:
					this.addCandleParticleEffects(world, flameParticle, smokeParticle, x + 0.5D, y + 0.75D, z + 0.5D);
					break;
				case 2:
					switch (state.get(FACING)) {
						case NORTH:
							this.addCandleParticleEffects(world, flameParticle, smokeParticle, x + 0.5625D, y + 0.75D, z + 0.3125D);
							this.addCandleParticleEffects(world, flameParticle, smokeParticle, x + 0.375D, y + 0.625D, z + 0.625D);
							break;
						case EAST:
							this.addCandleParticleEffects(world, flameParticle, smokeParticle, x + 0.6875D, y + 0.75D, z + 0.5625D);
							this.addCandleParticleEffects(world, flameParticle, smokeParticle, x + 0.375D, y + 0.625D, z + 0.375D);
							break;
						case SOUTH:
							this.addCandleParticleEffects(world, flameParticle, smokeParticle, x + 0.4375D, y + 0.75D, z + 0.6875D);
							this.addCandleParticleEffects(world, flameParticle, smokeParticle, x + 0.625D, y + 0.625D, z + 0.375D);
							break;
						case WEST:
							this.addCandleParticleEffects(world, flameParticle, smokeParticle, x + 0.3125D, y + 0.75D, z + 0.4375D);
							this.addCandleParticleEffects(world, flameParticle, smokeParticle, x + 0.625D, y + 0.625D, z + 0.625D);
							break;
					}
					break;
				case 3:
					switch (state.get(FACING)) {
						case NORTH:
							this.addCandleParticleEffects(world, flameParticle, smokeParticle, x + 0.6875D, y + 0.75D, z + 0.375D);
							this.addCandleParticleEffects(world, flameParticle, smokeParticle, x + 0.5D, y + 0.6875D, z + 0.6875D);
							this.addCandleParticleEffects(world, flameParticle, smokeParticle, x + 0.3125D, y + 0.625D, z + 0.3125D);
							break;
						case EAST:
							this.addCandleParticleEffects(world, flameParticle, smokeParticle, x + 0.625D, y + 0.75D, z + 0.6875D);
							this.addCandleParticleEffects(world, flameParticle, smokeParticle, x + 0.3125D, y + 0.6875D, z + 0.5D);
							this.addCandleParticleEffects(world, flameParticle, smokeParticle, x + 0.6875D, y + 0.625D, z + 0.3125D);
							break;
						case SOUTH:
							this.addCandleParticleEffects(world, flameParticle, smokeParticle, x + 0.3125D, y + 0.75D, z + 0.625D);
							this.addCandleParticleEffects(world, flameParticle, smokeParticle, x + 0.5D, y + 0.6875D, z + 0.3125D);
							this.addCandleParticleEffects(world, flameParticle, smokeParticle, x + 0.6875D, y + 0.625D, z + 0.6875D);
							break;
						case WEST:
							this.addCandleParticleEffects(world, flameParticle, smokeParticle, x + 0.375D, y + 0.75D, z + 0.3125D);
							this.addCandleParticleEffects(world, flameParticle, smokeParticle, x + 0.6875D, y + 0.6875D, z + 0.5D);
							this.addCandleParticleEffects(world, flameParticle, smokeParticle, x + 0.3125D, y + 0.625D, z + 0.6875D);
							break;
					}
					break;
				default:
				case 4:
					switch (state.get(FACING)) {
						case NORTH:
							this.addCandleParticleEffects(world, flameParticle, smokeParticle, x + 0.6875D, y + 0.75D, z + 0.3125D);
							this.addCandleParticleEffects(world, flameParticle, smokeParticle, x + 0.625D, y + 0.6875D, z + 0.625D);
							this.addCandleParticleEffects(world, flameParticle, smokeParticle, x + 0.3125D, y + 0.625D, z + 0.375D);
							this.addCandleParticleEffects(world, flameParticle, smokeParticle, x + 0.3125D, y + 0.4375D, z + 0.6875D);
							break;
						case EAST:
							this.addCandleParticleEffects(world, flameParticle, smokeParticle, x + 0.6875D, y + 0.75D, z + 0.6875D);
							this.addCandleParticleEffects(world, flameParticle, smokeParticle, x + 0.375D, y + 0.6875D, z + 0.625D);
							this.addCandleParticleEffects(world, flameParticle, smokeParticle, x + 0.625D, y + 0.625D, z + 0.3125D);
							this.addCandleParticleEffects(world, flameParticle, smokeParticle, x + 0.3125D, y + 0.4375D, z + 0.3125D);
							break;
						case SOUTH:
							this.addCandleParticleEffects(world, flameParticle, smokeParticle, x + 0.3125D, y + 0.75D, z + 0.6875D);
							this.addCandleParticleEffects(world, flameParticle, smokeParticle, x + 0.375D, y + 0.6875D, z + 0.375D);
							this.addCandleParticleEffects(world, flameParticle, smokeParticle, x + 0.6875D, y + 0.625D, z + 0.625D);
							this.addCandleParticleEffects(world, flameParticle, smokeParticle, x + 0.6875D, y + 0.4375D, z + 0.3125D);
							break;
						case WEST:
							this.addCandleParticleEffects(world, flameParticle, smokeParticle, x + 0.3125D, y + 0.75D, z + 0.3125D);
							this.addCandleParticleEffects(world, flameParticle, smokeParticle, x + 0.625D, y + 0.6875D, z + 0.375D);
							this.addCandleParticleEffects(world, flameParticle, smokeParticle, x + 0.375D, y + 0.625D, z + 0.6875D);
							this.addCandleParticleEffects(world, flameParticle, smokeParticle, x + 0.6875D, y + 0.4375D, z + 0.6875D);
							break;
					}
					break;
			}
		}
	}

	public void addCandleParticleEffects(World world, IParticleData flameParticle, IParticleData smokePartice, double x, double y, double z) {
		world.addParticle(flameParticle, x, y, z, XZ_PARTICLE_SPEED, Y_PARTICLE_SPEED, XZ_PARTICLE_SPEED);
		world.addParticle(smokePartice, x, y, z, XZ_PARTICLE_SPEED, Y_PARTICLE_SPEED, XZ_PARTICLE_SPEED);
	}
}
