package com.minecraftabnormals.buzzier_bees.core.registry;

import com.minecraftabnormals.abnormals_core.core.util.registry.TileEntitySubRegistryHelper;
import com.minecraftabnormals.buzzier_bees.common.blocks.ScentedCandleBlock;
import com.minecraftabnormals.buzzier_bees.common.tileentity.ScentedCandleTileEntity;
import com.minecraftabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = BuzzierBees.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BBTileEntities {
	public static final TileEntitySubRegistryHelper HELPER = BuzzierBees.REGISTRY_HELPER.getTileEntitySubHelper();

	public static final RegistryObject<TileEntityType<ScentedCandleTileEntity>> SCENTED_CANDLE = HELPER.createTileEntity("scented_candle", ScentedCandleTileEntity::new, () -> collectScentedCandles());

	private static Block[] collectScentedCandles() {
		return ForgeRegistries.BLOCKS.getValues().stream().filter(block -> block instanceof ScentedCandleBlock).toArray(Block[]::new);
	}
}
