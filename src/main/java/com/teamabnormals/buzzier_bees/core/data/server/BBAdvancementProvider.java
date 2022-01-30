package com.teamabnormals.buzzier_bees.core.data.server;

import com.teamabnormals.buzzier_bees.common.advancement.BBEmptyTrigger;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import com.teamabnormals.buzzier_bees.core.other.BBCriteriaTriggers;
import com.teamabnormals.buzzier_bees.core.registry.BBItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Consumer;

public class BBAdvancementProvider extends AdvancementProvider {

	public BBAdvancementProvider(DataGenerator generator, ExistingFileHelper fileHelper) {
		super(generator, fileHelper);
	}

	@Override
	protected void registerAdvancements(Consumer<Advancement> consumer, ExistingFileHelper fileHelper) {
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

	private static void createCureAdvancement(String name, ItemLike item, BBEmptyTrigger trigger, Consumer<Advancement> consumer) {
		createAdvancement(name, "husbandry", new ResourceLocation("husbandry/safely_harvest_honey"), item, FrameType.TASK, true, true, false)
				.addCriterion(name, trigger.createInstance())
				.save(consumer, BuzzierBees.MOD_ID + ":husbandry/" + name);
	}
}