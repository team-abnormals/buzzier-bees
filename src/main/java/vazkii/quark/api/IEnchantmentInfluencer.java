package vazkii.quark.api;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

/**
 * Implement on a Block to make it influence matrix enchanting
 */
public interface IEnchantmentInfluencer {

	@Nullable
	DyeColor getEnchantmentInfluenceColor(BlockGetter world, BlockPos pos, BlockState state);

	default int getInfluenceStack(BlockGetter world, BlockPos pos, BlockState state) {
		return 1;
	}
}