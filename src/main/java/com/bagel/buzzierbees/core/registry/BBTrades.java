package com.bagel.buzzierbees.core.registry;

import java.util.List;

import com.bagel.buzzierbees.core.BuzzierBees;
import com.bagel.buzzierbees.core.util.TradeUtils.EmeraldsForItemsTrade;
import com.bagel.buzzierbees.core.util.TradeUtils.ItemsForEmeraldsTrade;

import net.minecraft.block.Blocks;
import net.minecraft.entity.merchant.villager.VillagerTrades.ITrade;
import net.minecraft.item.Items;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BuzzierBees.MODID)
public class BBTrades {
	
	@SubscribeEvent
	public static void onWandererTradesEvent(WandererTradesEvent event) {
		List<ITrade> trades = event.getGenericTrades();
		trades.add(new ItemsForEmeraldsTrade(BBBlocks.CARTWHEEL.get(), 2, 1, 5, 1));
		trades.add(new ItemsForEmeraldsTrade(BBBlocks.PINK_CLOVER.get(), 1, 1, 6, 1));
		trades.add(new ItemsForEmeraldsTrade(BBBlocks.WHITE_CLOVER.get(), 1, 1, 6, 1));
		trades.add(new ItemsForEmeraldsTrade(BBBlocks.VIOLET.get(), 1, 1, 12, 1));
		trades.add(new ItemsForEmeraldsTrade(BBBlocks.BLUEBELL.get(), 1, 1, 8, 1));
		trades.add(new ItemsForEmeraldsTrade(BBBlocks.COLUMBINE.get(), 1, 1, 7, 1));
		trades.add(new ItemsForEmeraldsTrade(BBBlocks.DIANTHUS.get(), 1, 1, 8, 1));

		trades.add(new ItemsForEmeraldsTrade(BBBlocks.YELLOW_HIBISCUS.get(), 1, 1, 12, 1));
		trades.add(new ItemsForEmeraldsTrade(BBBlocks.ORANGE_HIBISCUS.get(), 1, 1, 12, 1));
		trades.add(new ItemsForEmeraldsTrade(BBBlocks.RED_HIBISCUS.get(), 1, 1, 12, 1));
		trades.add(new ItemsForEmeraldsTrade(BBBlocks.PINK_HIBISCUS.get(), 1, 1, 12, 1));
		trades.add(new ItemsForEmeraldsTrade(BBBlocks.MAGENTA_HIBISCUS.get(), 1, 1, 12, 1));
		trades.add(new ItemsForEmeraldsTrade(BBBlocks.PURPLE_HIBISCUS.get(), 1, 1, 12, 1));
	}
	
	
//	ItemsForEmeralds -> GET Items, GIVE Emeralds.
//	(Output item, Emerald cost, Amount of Item, Trade Limit, Experience)
//	
//	EmeraldsForItems -> GET Emeralds, GIVE Items
//	(Input item, Amount of Item, Emerald return, Trade Limit, Experience)
	
	@SubscribeEvent
	public static void onVillagerTradesEvent(VillagerTradesEvent event) {
		List<ITrade> novice = event.getTrades().get(1);
		List<ITrade> apprentice = event.getTrades().get(2);
		List<ITrade> journeyman = event.getTrades().get(3);
		List<ITrade> expert = event.getTrades().get(4);
		List<ITrade> master = event.getTrades().get(5);
		
		if(event.getType() == BBVillagers.APIARIST.get()) {
			novice.add(new EmeraldsForItemsTrade(Items.HONEY_BOTTLE, 1, 1, 8, 20));
			novice.add(new EmeraldsForItemsTrade(Items.HONEYCOMB, 1, 1, 8, 20));
			novice.add(new ItemsForEmeraldsTrade(BBItems.BEE_SOUP.get(), 1, 1, 8, 20));
			
			apprentice.add(new ItemsForEmeraldsTrade(BBBlocks.CANDLE.get(), 1, 1, 8, 20));
			apprentice.add(new ItemsForEmeraldsTrade(BBBlocks.RED_CANDLE.get(), 1, 1, 8, 20));
			
			journeyman.add(new EmeraldsForItemsTrade(BBBlocks.DIANTHUS.get(), 1, 1, 8, 20));
			journeyman.add(new EmeraldsForItemsTrade(BBBlocks.COLUMBINE.get(), 1, 1, 8, 20));
			journeyman.add(new EmeraldsForItemsTrade(BBBlocks.VIOLET.get(), 1, 1, 8, 20));
			journeyman.add(new EmeraldsForItemsTrade(BBBlocks.YELLOW_HIBISCUS.get(), 1, 1, 8, 20));
			
			expert.add(new EmeraldsForItemsTrade(Blocks.ROSE_BUSH, 1, 1, 8, 20));
			expert.add(new EmeraldsForItemsTrade(Blocks.LILAC, 1, 1, 8, 20));
			expert.add(new EmeraldsForItemsTrade(BBBlocks.BIRD_OF_PARADISE.get(), 1, 1, 8, 20));
			
			master.add(new EmeraldsForItemsTrade(BBItems.BOTTLE_OF_BEE.get(), 1, 1, 8, 20));
			master.add(new ItemsForEmeraldsTrade(BBItems.HONEY_WAND.get(), 12, 1, 1, 20));
		}
    }
}
