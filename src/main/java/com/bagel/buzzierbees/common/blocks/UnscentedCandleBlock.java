package com.bagel.buzzierbees.common.blocks;

import java.util.Random;

import com.bagel.buzzierbees.core.registry.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class UnscentedCandleBlock extends CandleBlock implements IWaterLoggable {
	public UnscentedCandleBlock(Block.Properties properties) {
		super(properties);
    	this.setDefaultState(this.getDefaultState().with(CANDLES, 1).with(WATERLOGGED, true));
    }
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		int candles = state.get(CANDLES);
		Direction facing = state.get(FACING);
		boolean waterlogged = state.get(WATERLOGGED);
		BlockState candleOutput = candleOutput(player.getHeldItem(handIn).getItem(), this);
		if (candleOutput != null && candleOutput.getBlock() != this) {
			if (!player.abilities.isCreativeMode) {
				player.getHeldItem(handIn).shrink(1);
			}
			worldIn.setBlockState(pos, candleOutput.with(CANDLES, candles).with(FACING, facing).with(WATERLOGGED, waterlogged));
			return ActionResultType.SUCCESS;	    
		} else {
			return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);	
		}	
	}
	
	public BlockState candleOutput(Item item, Block block) {
		if (item == Blocks.DANDELION.asItem()) { return ModBlocks.DANDELION_SCENTED_CANDLE.get().getDefaultState(); }
		if (item == Blocks.POPPY.asItem()) { return ModBlocks.POPPY_SCENTED_CANDLE.get().getDefaultState(); }
		if (item == Blocks.BLUE_ORCHID.asItem()) { return ModBlocks.BLUE_ORCHID_SCENTED_CANDLE.get().getDefaultState(); }
		if (item == Blocks.ALLIUM.asItem()) { return ModBlocks.ALLIUM_SCENTED_CANDLE.get().getDefaultState(); }
		if (item == Blocks.AZURE_BLUET.asItem()) { return ModBlocks.AZURE_BLUET_SCENTED_CANDLE.get().getDefaultState(); }
		if (item == Blocks.RED_TULIP.asItem()) { return ModBlocks.RED_TULIP_SCENTED_CANDLE.get().getDefaultState(); }
		if (item == Blocks.ORANGE_TULIP.asItem()) { return ModBlocks.ORANGE_TULIP_SCENTED_CANDLE.get().getDefaultState(); }
		if (item == Blocks.WHITE_TULIP.asItem()) { return ModBlocks.WHITE_TULIP_SCENTED_CANDLE.get().getDefaultState(); }
		if (item == Blocks.PINK_TULIP.asItem()) { return ModBlocks.PINK_TULIP_SCENTED_CANDLE.get().getDefaultState(); }
		if (item == Blocks.OXEYE_DAISY.asItem()) { return ModBlocks.OXEYE_DAISY_SCENTED_CANDLE.get().getDefaultState(); }
		if (item == Blocks.CORNFLOWER.asItem()) { return ModBlocks.CORNFLOWER_SCENTED_CANDLE.get().getDefaultState(); }
		if (item == Blocks.LILY_OF_THE_VALLEY.asItem()) { return ModBlocks.LILY_OF_THE_VALLEY_SCENTED_CANDLE.get().getDefaultState(); }
		if (item == Blocks.WITHER_ROSE.asItem()) { return ModBlocks.WITHER_ROSE_SCENTED_CANDLE.get().getDefaultState(); }
		if (item == ModBlocks.CARTWHEEL.get().asItem()) { return ModBlocks.CARTWHEEL_SCENTED_CANDLE.get().getDefaultState(); }
		if (item == ModBlocks.PINK_CLOVER.get().asItem()) { return ModBlocks.PINK_CLOVER_SCENTED_CANDLE.get().getDefaultState(); }
		if (item == ModBlocks.WHITE_CLOVER.get().asItem()) { return ModBlocks.WHITE_CLOVER_SCENTED_CANDLE.get().getDefaultState(); }
		if (item == ModBlocks.VIOLET.get().asItem()) { return ModBlocks.VIOLET_SCENTED_CANDLE.get().getDefaultState(); }
		if (item == ModBlocks.BLUEBELL.get().asItem()) { return ModBlocks.BLUEBELL_SCENTED_CANDLE.get().getDefaultState(); }
		if (item == ModBlocks.JOLYCE.get().asItem()) { return ModBlocks.JOLYCE_SCENTED_CANDLE.get().getDefaultState(); }
		if (item == ModBlocks.COLUMBINE.get().asItem()) { return ModBlocks.COLUMBINE_SCENTED_CANDLE.get().getDefaultState(); }
		return null;
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, World worldIn, BlockPos pos, Random rand) {
		if(state.get(WATERLOGGED) == false) {
			double x = pos.getX();
			double y = pos.getY();
			double z = pos.getZ();
			
			if(state.get(CANDLES) == 1) {
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.5D, y+0.75D, z+0.5D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.5D, y+0.75D, z+0.5D, 0.002D, 0.01D, 0.002D);
			}
			if(state.get(CANDLES) == 2 && state.get(FACING) == Direction.NORTH) {
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.5625D, y+0.75D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.5625D, y+0.75D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.375D, y+0.625D, z+0.625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.375D, y+0.625D, z+0.625D, 0.002D, 0.01D, 0.002D);
			}
			if(state.get(CANDLES) == 2 && state.get(FACING) == Direction.EAST) {
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.6875D, y+0.75D, z+0.5625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.6875D, y+0.75D, z+0.5625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.375D, y+0.625D, z+0.375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.375D, y+0.625D, z+0.375D, 0.002D, 0.01D, 0.002D);
			}
			if(state.get(CANDLES) == 2 && state.get(FACING) == Direction.SOUTH) {
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.4375D, y+0.75D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.4375D, y+0.75D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.625D, y+0.625D, z+0.375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.625D, y+0.625D, z+0.375D, 0.002D, 0.01D, 0.002D);
			}
			if(state.get(CANDLES) == 2 && state.get(FACING) == Direction.WEST) {
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.3125D, y+0.75D, z+0.4375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.3125D, y+0.75D, z+0.4375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.625D, y+0.625D, z+0.625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.625D, y+0.625D, z+0.625D, 0.002D, 0.01D, 0.002D);
			}
			if(state.get(CANDLES) == 3 && state.get(FACING) == Direction.NORTH) {
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.6875D, y+0.75D, z+0.375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.6875D, y+0.75D, z+0.375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.5D, y+0.6875D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.5D, y+0.6875D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.3125D, y+0.625D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.3125D, y+0.625D, z+0.3125D, 0.002D, 0.01D, 0.002D);
			}
			if(state.get(CANDLES) == 3 && state.get(FACING) == Direction.EAST) {
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.625D, y+0.75D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.625D, y+0.75D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.3125D, y+0.6875D, z+0.5D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.3125D, y+0.6875D, z+0.5D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.6875D, y+0.625D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.6875D, y+0.625D, z+0.3125D, 0.002D, 0.01D, 0.002D);
			}
			if(state.get(CANDLES) == 3 && state.get(FACING) == Direction.SOUTH) {
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.3125D, y+0.75D, z+0.625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.3125D, y+0.75D, z+0.625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.5D, y+0.6875D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.5D, y+0.6875D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.6875D, y+0.625D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.6875D, y+0.625D, z+0.6875D, 0.002D, 0.01D, 0.002D);
			}
			if(state.get(CANDLES) == 3 && state.get(FACING) == Direction.WEST) {
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.375D, y+0.75D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.375D, y+0.75D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.6875D, y+0.6875D, z+0.5D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.6875D, y+0.6875D, z+0.5D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.3125D, y+0.625D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.3125D, y+0.625D, z+0.6875D, 0.002D, 0.01D, 0.002D);
			}
			if(state.get(CANDLES) == 4 && state.get(FACING) == Direction.NORTH) {
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.6875D, y+0.75D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.6875D, y+0.75D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.625D, y+0.6875D, z+0.625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.625D, y+0.6875D, z+0.625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.3125D, y+0.625D, z+0.375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.3125D, y+0.625D, z+0.375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.3125D, y+0.4375D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.3125D, y+0.4375D, z+0.6875D, 0.002D, 0.01D, 0.002D);
			}
			if(state.get(CANDLES) == 4 && state.get(FACING) == Direction.EAST) {
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.6875D, y+0.75D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.6875D, y+0.75D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.375D, y+0.6875D, z+0.625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.375D, y+0.6875D, z+0.625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.625D, y+0.625D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.625D, y+0.625D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.3125D, y+0.4375D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.3125D, y+0.4375D, z+0.3125D, 0.002D, 0.01D, 0.002D);
			}
			if(state.get(CANDLES) == 4 && state.get(FACING) == Direction.SOUTH) {
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.3125D, y+0.75D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.3125D, y+0.75D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.375D, y+0.6875D, z+0.375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.375D, y+0.6875D, z+0.375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.6875D, y+0.625D, z+0.625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.6875D, y+0.625D, z+0.625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.6875D, y+0.4375D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.6875D, y+0.4375D, z+0.3125D, 0.002D, 0.01D, 0.002D);
			}
			if(state.get(CANDLES) == 4 && state.get(FACING) == Direction.WEST) {
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.3125D, y+0.75D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.3125D, y+0.75D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.625D, y+0.6875D, z+0.375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.625D, y+0.6875D, z+0.375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.375D, y+0.625D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.375D, y+0.625D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.6875D, y+0.4375D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.6875D, y+0.4375D, z+0.6875D, 0.002D, 0.01D, 0.002D);
			}
		}
	}
}
