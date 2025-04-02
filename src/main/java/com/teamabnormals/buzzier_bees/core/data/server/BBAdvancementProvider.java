package com.teamabnormals.buzzier_bees.core.data.server;

import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import com.teamabnormals.buzzier_bees.core.registry.BBItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootContext.EntityTarget;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.AdvancementProvider.AdvancementGenerator;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class BBAdvancementProvider implements AdvancementGenerator {

	public static AdvancementProvider create(PackOutput output, CompletableFuture<Provider> provider, ExistingFileHelper helper) {
		return new AdvancementProvider(output, provider, helper, List.of(new BBAdvancementProvider()));
	}

	@Override
	public void generate(Provider provider, Consumer<AdvancementHolder> consumer, ExistingFileHelper helper) {
		createAdvancement("four_leaf_clover", "adventure", ResourceLocation.withDefaultNamespace("adventure/sleep_in_bed"), BBItems.FOUR_LEAF_CLOVER.get(), AdvancementType.CHALLENGE, true, true, false)
				.addCriterion("four_leaf_clover", InventoryChangeTrigger.TriggerInstance.hasItems(BBItems.FOUR_LEAF_CLOVER.get()))
				.save(consumer, BuzzierBees.MOD_ID + ":adventure/four_leaf_clover");

		createCureAdvancement("use_glazed_porkchop", BBItems.GLAZED_PORKCHOP.get(), MobEffects.DIG_SLOWDOWN, consumer);
		createCureAdvancement("use_honey_bread", BBItems.HONEY_BREAD.get(), MobEffects.MOVEMENT_SLOWDOWN, consumer);
		createCureAdvancement("use_honey_apple", BBItems.HONEY_APPLE.get(), MobEffects.LEVITATION, consumer);
	}

	private static Advancement.Builder createAdvancement(String name, String category, ResourceLocation parent, ItemLike icon, AdvancementType frame, boolean showToast, boolean announceToChat, boolean hidden) {
		return Advancement.Builder.advancement().parent(Advancement.Builder.advancement().build(parent)).display(icon,
				Component.translatable("advancements." + BuzzierBees.MOD_ID + "." + category + "." + name + ".title"),
				Component.translatable("advancements." + BuzzierBees.MOD_ID + "." + category + "." + name + ".description"),
				null, frame, showToast, announceToChat, hidden);
	}

	private static void createCureAdvancement(String name, ItemLike item, Holder<MobEffect> curedEffect, Consumer<AdvancementHolder> consumer) {
		createAdvancement(name, "husbandry", ResourceLocation.withDefaultNamespace("husbandry/safely_harvest_honey"), item, AdvancementType.TASK, true, true, false)
				.addCriterion(name, CriteriaTriggers.CONSUME_ITEM.createCriterion(new ConsumeItemTrigger.TriggerInstance(
						Optional.of(ContextAwarePredicate.create(LootItemEntityPropertyCondition.hasProperties(EntityTarget.THIS, EntityPredicate.Builder.entity().effects(MobEffectsPredicate.Builder.effects().and(curedEffect)).build()).build())),
						Optional.of(ItemPredicate.Builder.item().of(item).build()))
				))
				.save(consumer, BuzzierBees.MOD_ID + ":husbandry/" + name);
	}
}