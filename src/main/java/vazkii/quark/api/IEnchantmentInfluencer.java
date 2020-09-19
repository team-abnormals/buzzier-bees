package vazkii.quark.api;

import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.item.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

/**
 * Implement on a Block to make it influence matrix enchanting
 */
public interface IEnchantmentInfluencer {

	@Nullable DyeColor getEnchantmentInfluenceColor(IBlockReader world, BlockPos pos, BlockState state);
	
}