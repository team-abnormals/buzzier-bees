package com.bagel.buzzier_bees.core.registry;

import com.bagel.buzzier_bees.common.blocks.ScentedCandleBlock;
import com.bagel.buzzier_bees.common.tileentity.ScentedCandleTileEntity;
import com.bagel.buzzier_bees.core.BuzzierBees;
import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = BuzzierBees.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BBTileEntities {
	public static final RegistryHelper HELPER = BuzzierBees.REGISTRY_HELPER;

	public static final RegistryObject<TileEntityType<ScentedCandleTileEntity>> SCENTED_CANDLE = HELPER.createTileEntity("scented_candle", ScentedCandleTileEntity::new, () -> collectScentedCandles());
	
	private static Block[] collectScentedCandles() {
		return ForgeRegistries.BLOCKS.getValues().stream().filter(block -> block instanceof ScentedCandleBlock).toArray(Block[]::new);
	}
}
