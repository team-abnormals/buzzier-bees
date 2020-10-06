package vazkii.quark.api;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public interface IVariableEnchantmentInfluencer extends IEnchantmentInfluencer {

	int getInfluenceStack(IBlockReader world, BlockPos pos, BlockState state);

}