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
	public static final DirectionProperty FACING = HorizontalBlock.FACING;
	private final DyeColor color;

	public static final double XZ_PARTICLE_SPEED = 0.002F;
	public static final double Y_PARTICLE_SPEED = 0.01F;

	protected static final VoxelShape[] SHAPES = new VoxelShape[]{
			Block.box(6.0D, 0.0D, 6.0D, 10.0D, 9.0D, 10.0D),
			Block.box(3.0D, 0.0D, 3.0D, 13.0D, 9.0D, 13.0D),
			Block.box(3.0D, 0.0D, 3.0D, 13.0D, 9.0D, 13.0D),
			Block.box(3.0D, 0.0D, 3.0D, 13.0D, 9.0D, 13.0D)};

	public CandleBlock(DyeColor color, Properties properties) {
		super(properties);
		this.color = color;
		this.registerDefaultState(this.defaultBlockState().setValue(CANDLES, 1).setValue(WATERLOGGED, true).setValue(LIT, true));
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
		Direction direction = context.getHorizontalDirection();
		if (blockstate.getBlock() == this) {
			return blockstate.setValue(FACING, direction).setValue(CANDLES, Math.min(4, blockstate.getValue(CANDLES) + 1));
		} else {
			FluidState ifluidstate = context.getLevel().getFluidState(context.getClickedPos());
			boolean flag = ifluidstate.is(FluidTags.WATER) && ifluidstate.getAmount() == 8;
			return this.defaultBlockState().setValue(FACING, direction).setValue(WATERLOGGED, flag).setValue(LIT, !flag);
		}
	}

	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		if (!state.getValue(LIT) && player.getItemInHand(handIn).getItem() instanceof FlintAndSteelItem) {
			worldIn.playSound(player, pos, SoundEvents.FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, worldIn.getRandom().nextFloat() * 0.4F + 0.8F);
			worldIn.setBlock(pos, state.setValue(BlockStateProperties.LIT, Boolean.TRUE), 11);
			player.getItemInHand(handIn).hurtAndBreak(1, player, (p_219999_1_) -> {
				p_219999_1_.broadcastBreakEvent(handIn);
			});
			return ActionResultType.sidedSuccess(worldIn.isClientSide());
		} else if (state.getValue(LIT) && player.getItemInHand(handIn).getItem() instanceof ShovelItem) {
			if (!worldIn.isClientSide()) {
				worldIn.levelEvent(null, 1009, pos, 0);
			}
			player.getItemInHand(handIn).hurtAndBreak(1, player, (p_219999_1_) -> {
				p_219999_1_.broadcastBreakEvent(handIn);
			});
			worldIn.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.LIT, Boolean.FALSE));
			return ActionResultType.sidedSuccess(worldIn.isClientSide());
		} else {
			return super.use(state, worldIn, pos, player, handIn, hit);
		}
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return mirrorIn == Mirror.NONE ? state : state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Override
	public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {
		return canSupportCenter(worldIn, pos.below(), Direction.UP);
	}

	@Override
	public float getEnchantPowerBonus(BlockState state, IWorldReader world, BlockPos pos) {
		return (0.1F * state.getValue(CANDLES));
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (!state.canSurvive(worldIn, currentPos)) {
			return Blocks.AIR.defaultBlockState();
		} else {
			if (state.getValue(WATERLOGGED)) {
				if (state.getValue(LIT))
					worldIn.setBlock(currentPos, state.setValue(LIT, false), 2);
				worldIn.getLiquidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
			}
			return super.updateShape(state, facing, facingState, worldIn, currentPos, facingPos);
		}
	}

	@Override
	public boolean canBeReplaced(BlockState state, BlockItemUseContext useContext) {
		return useContext.getItemInHand().getItem() == this.asItem() && state.getValue(CANDLES) < 4 || super.canBeReplaced(state, useContext);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPES[state.getValue(CANDLES) - 1];
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(CANDLES, WATERLOGGED, LIT, FACING);
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
		return true;
	}

	@Override
	public boolean isPathfindable(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
		return type == PathType.AIR && !this.hasCollision || super.isPathfindable(state, worldIn, pos, type);
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
		return state.getValue(CANDLES);
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {
		if (state.getValue(LIT)) {
			double x = pos.getX();
			double y = pos.getY();
			double z = pos.getZ();
			IParticleData smokeParticle = this.getSmokeParticle();
			IParticleData flameParticle = this.getFlameParticle();

			switch (state.getValue(CANDLES)) {
				case 1:
					this.addCandleParticleEffects(world, flameParticle, smokeParticle, x + 0.5D, y + 0.75D, z + 0.5D);
					break;
				case 2:
					switch (state.getValue(FACING)) {
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
					switch (state.getValue(FACING)) {
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
					switch (state.getValue(FACING)) {
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
