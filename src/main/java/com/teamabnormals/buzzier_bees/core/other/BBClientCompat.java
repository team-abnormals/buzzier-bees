package com.teamabnormals.buzzier_bees.core.other;

import com.teamabnormals.buzzier_bees.core.registry.BBBlocks;
import com.teamabnormals.buzzier_bees.core.registry.BBDataComponents;
import com.teamabnormals.buzzier_bees.core.registry.BBItems;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.component.CustomData;

public class BBClientCompat {

	public static void register() {
		BBBlocks.setupTabEditors();
		BBItems.setupTabEditors();
		registerRenderLayers();
		registerItemProperties();
	}

	private static void registerRenderLayers() {
		ItemBlockRenderTypes.setRenderLayer(BBBlocks.HONEY_LAMP.get(), RenderType.translucent());

		ItemBlockRenderTypes.setRenderLayer(BBBlocks.WHITE_CLOVER.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(BBBlocks.PINK_CLOVER.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(BBBlocks.BUTTERCUP.get(), RenderType.cutout());

		ItemBlockRenderTypes.setRenderLayer(BBBlocks.POTTED_WHITE_CLOVER.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(BBBlocks.POTTED_PINK_CLOVER.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(BBBlocks.POTTED_BUTTERCUP.get(), RenderType.cutout());
	}

	private static void registerItemProperties() {
		ItemProperties.register(BBItems.BOTTLE_OF_BEE.get(), ResourceLocation.parse("angry"), (stack, world, entity, num) -> {
			CompoundTag tag = stack.getOrDefault(BBDataComponents.BOTTLE_ENTITY_DATA.get(), CustomData.EMPTY).copyTag();
			if (tag.contains("AngerTime") && tag.getInt("AngerTime") > 0) {
				return 2;
			}
			return 1;
		});
	}
}
