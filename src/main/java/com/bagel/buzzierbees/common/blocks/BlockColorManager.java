package com.bagel.buzzierbees.common.blocks;

import com.bagel.buzzierbees.common.BuzzierBees;

import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.world.GrassColors;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = BuzzierBees.MODID, bus = Bus.MOD)
public class BlockColorManager
{
	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public static void onBlockColorsInit(ColorHandlerEvent.Block event)
	{
		final BlockColors blockColors = event.getBlockColors();

		//registers the colors for blocks that changes colors based on biome
		blockColors.register((p_210225_0_, p_210225_1_, p_210225_2_, p_210225_3_) ->
		{
			return p_210225_1_ != null && p_210225_2_ != null ? BiomeColors.func_228358_a_(p_210225_1_, p_210225_2_) : GrassColors.get(0.5D, 1.0D);
		}, ModBlocks.PINK_CLOVER, ModBlocks.WHITE_CLOVER);
	}
}
