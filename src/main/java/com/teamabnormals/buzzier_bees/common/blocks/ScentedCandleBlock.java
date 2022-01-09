package com.teamabnormals.buzzier_bees.common.blocks;

import com.teamabnormals.buzzier_bees.common.blockentity.ScentedCandleBlockEntity;
import com.teamabnormals.buzzier_bees.core.registry.BBBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEventListener;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class ScentedCandleBlock extends CandleBlock implements EntityBlock {
	public Supplier<MobEffect> candleEffectInstance;
	public int duration;
	public int amplifier;

	public ScentedCandleBlock(Supplier<MobEffect> candleEffectInstance, DyeColor color, Block.Properties properties) {
		super(color, properties);
		this.candleEffectInstance = candleEffectInstance;
		this.duration = 70;
		this.amplifier = 0;
	}

	@Override
	public float getEnchantPowerBonus(BlockState state, LevelReader world, BlockPos pos) {
		return (0.25F * state.getValue(CANDLES));
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
		return BBBlockEntities.SCENTED_CANDLE.get().create(p_153215_, p_153216_);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_153212_, BlockState p_153213_, BlockEntityType<T> p_153214_) {
		return p_153212_.isClientSide() ? null : (level, blockPos, blockState, blockEntity) -> ((ScentedCandleBlockEntity) blockEntity).tick();
	}

	@Nullable
	@Override
	public <T extends BlockEntity> GameEventListener getListener(Level p_153210_, T p_153211_) {
		return EntityBlock.super.getListener(p_153210_, p_153211_);
	}
}
