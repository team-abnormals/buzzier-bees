package com.minecraftabnormals.buzzier_bees.core.other;

import com.minecraftabnormals.abnormals_core.core.util.TradeUtil;
import com.minecraftabnormals.abnormals_core.core.util.TradeUtil.AbnormalsTrade;
import com.minecraftabnormals.buzzier_bees.core.BuzzierBees;
import com.minecraftabnormals.buzzier_bees.core.other.tags.BBItemTags;
import com.minecraftabnormals.buzzier_bees.core.registry.BBBlocks;
import com.minecraftabnormals.buzzier_bees.core.registry.BBItems;
import com.minecraftabnormals.buzzier_bees.core.registry.BBVillagers;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BuzzierBees.MOD_ID)
public class BBTrades {

	@SubscribeEvent
	public static void onWandererTradesEvent(WandererTradesEvent event) {
		TradeUtil.addWandererTrades(event,
				new AbnormalsTrade(1, BBBlocks.PINK_CLOVER.get().asItem(), 1, 6, 1),
				new AbnormalsTrade(1, BBBlocks.WHITE_CLOVER.get().asItem(), 1, 6, 1),
				new AbnormalsTrade(1, BBBlocks.BUTTERCUP.get().asItem(), 1, 6, 1)
		);
	}

	@SubscribeEvent
	public static void onVillagerTradesEvent(VillagerTradesEvent event) {

		if (event.getType() == BBVillagers.APIARIST.get()) {
			TradeUtil.addVillagerTrades(event, TradeUtil.NOVICE,
					new AbnormalsTrade(Items.CAMPFIRE, 1, 1, 6, 2),
					new AbnormalsTrade(1, Items.GLASS_BOTTLE, 3, 12, 1),
					new AbnormalsTrade(3, Items.SHEARS, 1, 12, 1)
			);

			TradeUtil.addVillagerTrades(event, TradeUtil.APPRENTICE,
					new AbnormalsTrade(Items.HONEY_BOTTLE, 1, 3, 3, 10),
					new AbnormalsTrade(1, BBBlocks.CANDLE.get().asItem(), 4, 5, 10)
			);

			for (Item item : BBItemTags.DYED_CANDLES.getValues())
				TradeUtil.addVillagerTrades(event, TradeUtil.APPRENTICE, new AbnormalsTrade(1, item, 4, 5, 10));
			for (Item item : ItemTags.TALL_FLOWERS.getValues())
				TradeUtil.addVillagerTrades(event, TradeUtil.JOURNEYMAN, new AbnormalsTrade(item, 1, 1, 12, 15));

			TradeUtil.addVillagerTrades(event, TradeUtil.EXPERT,
					new AbnormalsTrade(15, Items.BEE_NEST, 1, 3, 20),
					new AbnormalsTrade(12, BBItems.HONEY_WAND.get(), 1, 1, 20),
					new AbnormalsTrade(13, BBItems.FOUR_LEAF_CLOVER.get(), 1, 5, 30)
			);

			TradeUtil.addVillagerTrades(event, TradeUtil.MASTER,
					new AbnormalsTrade(6, BBItems.BOTTLE_OF_BEE.get(), 1, 3, 20)
			);
		}
	}
}
