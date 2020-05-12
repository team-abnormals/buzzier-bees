package com.bagel.buzzierbees.core.registry;

import com.bagel.buzzierbees.common.blocks.ScentedCandleBlock;
import com.bagel.buzzierbees.common.tileentity.ScentedCandleTileEntity;
import com.bagel.buzzierbees.core.BuzzierBees;
import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BBTileEntities {
	public static final RegistryHelper HELPER = BuzzierBees.REGISTRY_HELPER;

	public static final RegistryObject<TileEntityType<ScentedCandleTileEntity>> SCENTED_CANDLE = HELPER.createTileEntity("scented_candle", ScentedCandleTileEntity::new, () -> collectScentedCandles());
	
	private static Block[] collectScentedCandles() {
		return ForgeRegistries.BLOCKS.getValues().stream().filter(block -> block instanceof ScentedCandleBlock).toArray(Block[]::new);
	}
}
