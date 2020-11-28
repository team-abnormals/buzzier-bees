package com.minecraftabnormals.buzzier_bees.core.other;

import com.minecraftabnormals.buzzier_bees.core.BuzzierBees;
import com.minecraftabnormals.buzzier_bees.core.other.BBCompat.CompatMods;
import com.minecraftabnormals.buzzier_bees.core.registry.BBBlocks;
import com.minecraftabnormals.buzzier_bees.core.registry.BBItems;
import com.minecraftabnormals.buzzier_bees.core.registry.BBVillagers;
import com.teamabnormals.abnormals_core.core.utils.TradeUtils.EmeraldsForItemsTrade;
import com.teamabnormals.abnormals_core.core.utils.TradeUtils.ItemsForEmeraldsAndItemsTrade;
import com.teamabnormals.abnormals_core.core.utils.TradeUtils.ItemsForEmeraldsTrade;
import net.minecraft.block.Blocks;
import net.minecraft.entity.merchant.villager.VillagerTrades.ITrade;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = BuzzierBees.MODID)
public class BBTrades {

	@SubscribeEvent
	public static void onWandererTradesEvent(WandererTradesEvent event) {
		List<ITrade> trades = event.getGenericTrades();
		trades.add(new ItemsForEmeraldsTrade(BBBlocks.PINK_CLOVER.get(), 1, 1, 6, 1));
		trades.add(new ItemsForEmeraldsTrade(BBBlocks.WHITE_CLOVER.get(), 1, 1, 6, 1));
	}

	@SubscribeEvent
	public static void onVillagerTradesEvent(VillagerTradesEvent event) {
		List<ITrade> novice = event.getTrades().get(1);
		List<ITrade> apprentice = event.getTrades().get(2);
		List<ITrade> journeyman = event.getTrades().get(3);
		List<ITrade> expert = event.getTrades().get(4);
		List<ITrade> master = event.getTrades().get(5);

		if (event.getType() == BBVillagers.APIARIST.get()) {
			novice.add(new EmeraldsForItemsTrade(Items.CAMPFIRE, 1, 1, 6, 2));
			novice.add(new EmeraldsForItemsTrade(Items.GLASS_BOTTLE, 3, 1, 12, 1));
			novice.add(new ItemsForEmeraldsTrade(Items.SHEARS, 2, 1, 12, 1));

			apprentice.add(new ItemsForEmeraldsTrade(Items.HONEY_BOTTLE, 1, 3, 3, 10));

			apprentice.add(new ItemsForEmeraldsTrade(BBBlocks.CANDLE.get(), 1, 4, 5, 10));

			for (Item item : BBTags.Items.DYED_CANDLES.getAllElements()) {
				apprentice.add(new ItemsForEmeraldsTrade(item, 1, 4, 5, 10));
			}

			for (Item item : ItemTags.TALL_FLOWERS.getAllElements()) {
				journeyman.add(new EmeraldsForItemsTrade(item, 1, 1, 12, 15));
			}

			expert.add(new ItemsForEmeraldsTrade(BBItems.BOTTLE_OF_BEE.get(), 6, 1, 3, 20));
			expert.add(new ItemsForEmeraldsTrade(Blocks.BEE_NEST, 15, 1, 3, 20));
			expert.add(new ItemsForEmeraldsTrade(BBItems.HONEY_WAND.get(), 12, 1, 1, 20));
			master.add(new ItemsForEmeraldsTrade(BBItems.FOUR_LEAF_CLOVER.get(), 13, 1, 5, 30));

			master.add(new ItemsForEmeraldsAndItemsTrade(BBBlocks.ALLIUM_SCENTED_CANDLE.get()));
			master.add(new ItemsForEmeraldsAndItemsTrade(BBBlocks.AZURE_BLUET_SCENTED_CANDLE.get()));
			master.add(new ItemsForEmeraldsAndItemsTrade(BBBlocks.BLUE_ORCHID_SCENTED_CANDLE.get()));
			master.add(new ItemsForEmeraldsAndItemsTrade(BBBlocks.CORNFLOWER_SCENTED_CANDLE.get()));
			master.add(new ItemsForEmeraldsAndItemsTrade(BBBlocks.DANDELION_SCENTED_CANDLE.get()));
			master.add(new ItemsForEmeraldsAndItemsTrade(BBBlocks.LILY_OF_THE_VALLEY_SCENTED_CANDLE.get()));
			master.add(new ItemsForEmeraldsAndItemsTrade(BBBlocks.ORANGE_TULIP_SCENTED_CANDLE.get()));
			master.add(new ItemsForEmeraldsAndItemsTrade(BBBlocks.OXEYE_DAISY_SCENTED_CANDLE.get()));
			master.add(new ItemsForEmeraldsAndItemsTrade(BBBlocks.PINK_CLOVER_SCENTED_CANDLE.get()));
			master.add(new ItemsForEmeraldsAndItemsTrade(BBBlocks.PINK_TULIP_SCENTED_CANDLE.get()));
			master.add(new ItemsForEmeraldsAndItemsTrade(BBBlocks.POPPY_SCENTED_CANDLE.get()));
			master.add(new ItemsForEmeraldsAndItemsTrade(BBBlocks.RED_TULIP_SCENTED_CANDLE.get()));
			master.add(new ItemsForEmeraldsAndItemsTrade(BBBlocks.WHITE_CLOVER_SCENTED_CANDLE.get()));
			master.add(new ItemsForEmeraldsAndItemsTrade(BBBlocks.WHITE_TULIP_SCENTED_CANDLE.get()));
			master.add(new ItemsForEmeraldsAndItemsTrade(BBBlocks.WITHER_ROSE_SCENTED_CANDLE.get()));
			
			if (ModList.get().isLoaded(CompatMods.ENVIRONMENTAL)) {
				master.add(new ItemsForEmeraldsAndItemsTrade(BBBlocks.BLUEBELL_SCENTED_CANDLE.get()));
				master.add(new ItemsForEmeraldsAndItemsTrade(BBBlocks.CARTWHEEL_SCENTED_CANDLE.get()));
				master.add(new ItemsForEmeraldsAndItemsTrade(BBBlocks.RED_LOTUS_FLOWER_SCENTED_CANDLE.get()));
				master.add(new ItemsForEmeraldsAndItemsTrade(BBBlocks.WHITE_LOTUS_FLOWER_SCENTED_CANDLE.get()));
				master.add(new ItemsForEmeraldsAndItemsTrade(BBBlocks.DIANTHUS_SCENTED_CANDLE.get()));
				master.add(new ItemsForEmeraldsAndItemsTrade(BBBlocks.MAGENTA_HIBISCUS_SCENTED_CANDLE.get()));
				master.add(new ItemsForEmeraldsAndItemsTrade(BBBlocks.ORANGE_HIBISCUS_SCENTED_CANDLE.get()));
				master.add(new ItemsForEmeraldsAndItemsTrade(BBBlocks.PINK_HIBISCUS_SCENTED_CANDLE.get()));
				master.add(new ItemsForEmeraldsAndItemsTrade(BBBlocks.PURPLE_HIBISCUS_SCENTED_CANDLE.get()));
				master.add(new ItemsForEmeraldsAndItemsTrade(BBBlocks.RED_HIBISCUS_SCENTED_CANDLE.get()));
				master.add(new ItemsForEmeraldsAndItemsTrade(BBBlocks.VIOLET_SCENTED_CANDLE.get()));
				master.add(new ItemsForEmeraldsAndItemsTrade(BBBlocks.YELLOW_HIBISCUS_SCENTED_CANDLE.get()));
			}
			
			if (ModList.get().isLoaded(CompatMods.UPGRADE_AQUATIC)) {
				master.add(new ItemsForEmeraldsAndItemsTrade(BBBlocks.PINK_SEAROCKET_SCENTED_CANDLE.get()));
				master.add(new ItemsForEmeraldsAndItemsTrade(BBBlocks.WHITE_SEAROCKET_SCENTED_CANDLE.get()));
			}
			
			if (ModList.get().isLoaded(CompatMods.ATMOSPHERIC)) {
				master.add(new ItemsForEmeraldsAndItemsTrade(BBBlocks.WARM_MONKEY_BRUSH_SCENTED_CANDLE.get()));
				master.add(new ItemsForEmeraldsAndItemsTrade(BBBlocks.HOT_MONKEY_BRUSH_SCENTED_CANDLE.get()));
				master.add(new ItemsForEmeraldsAndItemsTrade(BBBlocks.WATER_HYACINTH_SCENTED_CANDLE.get()));
				master.add(new ItemsForEmeraldsAndItemsTrade(BBBlocks.GILIA_SCENTED_CANDLE.get()));
				master.add(new ItemsForEmeraldsAndItemsTrade(BBBlocks.YUCCA_FLOWER_SCENTED_CANDLE.get()));
				master.add(new ItemsForEmeraldsAndItemsTrade(BBBlocks.SCALDING_MONKEY_BRUSH_SCENTED_CANDLE.get()));
			}
			
			if (ModList.get().isLoaded(CompatMods.AUTUMNITY)) {
				master.add(new ItemsForEmeraldsAndItemsTrade(BBBlocks.AUTUMN_CROCUS_SCENTED_CANDLE.get()));
			}
		}
	}
}
