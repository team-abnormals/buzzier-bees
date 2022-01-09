package com.teamabnormals.buzzier_bees.core.data.server;

import com.google.common.collect.Sets;
import com.google.gson.GsonBuilder;
import com.teamabnormals.buzzier_bees.common.advancement.ImprovedEmptyTrigger;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import com.teamabnormals.buzzier_bees.core.other.BBCriteriaTriggers;
import com.teamabnormals.buzzier_bees.core.registry.BBItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;
import java.util.function.Consumer;

public class BBAdvancementProvider extends AdvancementProvider {
	private final Path PATH;
	public static final Logger LOGGER = LogManager.getLogger();

	public BBAdvancementProvider(DataGenerator generator) {
		super(generator);
		PATH = generator.getOutputFolder();
	}

	@Override
	public void run(HashCache cache) {
		Set<ResourceLocation> set = Sets.newHashSet();
		Consumer<Advancement> consumer = (advancement) -> {
			if (!set.add(advancement.getId())) {
				throw new IllegalStateException("Duplicate advancement " + advancement.getId());
			} else {
				Path path1 = getPath(PATH, advancement);
				try {
					DataProvider.save((new GsonBuilder()).setPrettyPrinting().create(), cache, advancement.deconstruct().serializeToJson(), path1);
				} catch (IOException ioexception) {
					LOGGER.error("Couldn't save advancement {}", path1, ioexception);
				}
			}
		};

		new BBAdvancements().accept(consumer);
	}

	private static Path getPath(Path path, Advancement advancement) {
		return path.resolve("data/" + advancement.getId().getNamespace() + "/advancements/" + advancement.getId().getPath() + ".json");
	}

	public static class BBAdvancements implements Consumer<Consumer<Advancement>> {

		@Override
		public void accept(Consumer<Advancement> consumer) {
			createAdvancement("four_leaf_clover", "adventure", new ResourceLocation("adventure/sleep_in_bed"), BBItems.FOUR_LEAF_CLOVER.get(), FrameType.CHALLENGE, true, true, false)
					.addCriterion("four_leaf_clover", InventoryChangeTrigger.TriggerInstance.hasItems(BBItems.FOUR_LEAF_CLOVER.get()))
					.save(consumer, BuzzierBees.MOD_ID + ":adventure/four_leaf_clover");

			createCureAdvancement("use_glazed_porkchop", BBItems.GLAZED_PORKCHOP.get(), BBCriteriaTriggers.GLAZED_PORKCHOP_CURE, consumer);
			createCureAdvancement("use_honey_bread", BBItems.HONEY_BREAD.get(), BBCriteriaTriggers.HONEY_BREAD_CURE, consumer);
			createCureAdvancement("use_honey_apple", BBItems.HONEY_APPLE.get(), BBCriteriaTriggers.HONEY_APPLE_CURE, consumer);
		}

		private static Advancement.Builder createAdvancement(String name, String category, ResourceLocation parent, ItemLike icon, FrameType frame, boolean showToast, boolean announceToChat, boolean hidden) {
			return Advancement.Builder.advancement().parent(Advancement.Builder.advancement().build(parent)).display(icon,
					new TranslatableComponent("advancements." + BuzzierBees.MOD_ID + "." + category + "." + name + ".title"),
					new TranslatableComponent("advancements." + BuzzierBees.MOD_ID + "." + category + "." + name + ".description"),
					null, frame, showToast, announceToChat, hidden);
		}

		private static void createCureAdvancement(String name, ItemLike item, ImprovedEmptyTrigger trigger, Consumer<Advancement> consumer) {
			createAdvancement(name, "husbandry", new ResourceLocation("husbandry/safely_harvest_honey"), item, FrameType.TASK, true, true, false)
					.addCriterion(name, new ImprovedEmptyTrigger.Instance(trigger.getId()))
					.save(consumer, BuzzierBees.MOD_ID + ":husbandry/" + name);
		}
	}
}