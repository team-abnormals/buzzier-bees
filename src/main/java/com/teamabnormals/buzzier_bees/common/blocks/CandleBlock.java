package com.teamabnormals.buzzier_bees.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import vazkii.quark.api.IEnchantmentInfluencer;

import javax.annotation.Nullable;
import java.util.Random;

public class CandleBlock extends Block implements SimpleWaterloggedBlock, IEnchantmentInfluencer {

	public static final IntegerProperty CANDLES = IntegerProperty.create("candles", 1, 4);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final BooleanProperty LIT = BlockStateProperties.LIT;
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
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
	public BlockState getStateForPlacement(BlockPlaceContext context) {
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
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
		if (!state.getValue(LIT) && player.getItemInHand(handIn).getItem() instanceof FlintAndSteelItem) {
			worldIn.playSound(player, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, worldIn.getRandom().nextFloat() * 0.4F + 0.8F);
			worldIn.setBlock(pos, state.setValue(BlockStateProperties.LIT, Boolean.TRUE), 11);
			player.getItemInHand(handIn).hurtAndBreak(1, player, (p_219999_1_) -> {
				p_219999_1_.broadcastBreakEvent(handIn);
			});
			return InteractionResult.sidedSuccess(worldIn.isClientSide());
		} else if (state.getValue(LIT) && player.getItemInHand(handIn).getItem() instanceof ShovelItem) {
			if (!worldIn.isClientSide()) {
				worldIn.levelEvent(null, 1009, pos, 0);
			}
			player.getItemInHand(handIn).hurtAndBreak(1, player, (p_219999_1_) -> {
				p_219999_1_.broadcastBreakEvent(handIn);
			});
			worldIn.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.LIT, Boolean.FALSE));
			return InteractionResult.sidedSuccess(worldIn.isClientSide());
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
	public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
		return canSupportCenter(worldIn, pos.below(), Direction.UP);
	}

	@Override
	public float getEnchantPowerBonus(BlockState state, LevelReader world, BlockPos pos) {
		return (0.1F * state.getValue(CANDLES));
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
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
	public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
		return useContext.getItemInHand().getItem() == this.asItem() && state.getValue(CANDLES) < 4 || super.canBeReplaced(state, useContext);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return SHAPES[state.getValue(CANDLES) - 1];
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(CANDLES, WATERLOGGED, LIT, FACING);
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return true;
	}

	@Override
	public boolean isPathfindable(BlockState state, BlockGetter worldIn, BlockPos pos, PathComputationType type) {
		return type == PathComputationType.AIR && !this.hasCollision || super.isPathfindable(state, worldIn, pos, type);
	}

	public ParticleOptions getFlameParticle() {
		return ParticleTypes.FLAME;
	}

	public ParticleOptions getSmokeParticle() {
		return ParticleTypes.SMOKE;
	}

	public DyeColor getColor() {
		return this.color;
	}

	@Nullable
	public DyeColor getEnchantmentInfluenceColor(BlockGetter world, BlockPos pos, BlockState state) {
		return this.getColor();
	}

	@Override
	public int getInfluenceStack(BlockGetter world, BlockPos pos, BlockState state) {
		return state.getValue(CANDLES);
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, Level world, BlockPos pos, Random rand) {
		if (state.getValue(LIT)) {
			double x = pos.getX();
			double y = pos.getY();
			double z = pos.getZ();
			ParticleOptions smokeParticle = this.getSmokeParticle();
			ParticleOptions flameParticle = this.getFlameParticle();

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

	public void addCandleParticleEffects(Level world, ParticleOptions flameParticle, ParticleOptions smokePartice, double x, double y, double z) {
		world.addParticle(flameParticle, x, y, z, XZ_PARTICLE_SPEED, Y_PARTICLE_SPEED, XZ_PARTICLE_SPEED);
		world.addParticle(smokePartice, x, y, z, XZ_PARTICLE_SPEED, Y_PARTICLE_SPEED, XZ_PARTICLE_SPEED);
	}
}
