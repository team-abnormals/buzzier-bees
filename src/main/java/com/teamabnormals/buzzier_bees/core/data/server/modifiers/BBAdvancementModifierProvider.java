package com.teamabnormals.buzzier_bees.core.data.server.modifiers;

import com.teamabnormals.blueprint.common.advancement.modification.AdvancementModifierProvider;
import com.teamabnormals.blueprint.common.advancement.modification.modifiers.CriteriaModifier;
import com.teamabnormals.blueprint.common.advancement.modification.modifiers.EffectsChangedModifier;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import com.teamabnormals.buzzier_bees.core.registry.BBEntityTypes;
import com.teamabnormals.buzzier_bees.core.registry.BBItems;
import com.teamabnormals.buzzier_bees.core.registry.BBMobEffects;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.BredAnimalsTrigger;
import net.minecraft.advancements.critereon.ConsumeItemTrigger;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.MobEffectsPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collection;

public class BBAdvancementModifierProvider extends AdvancementModifierProvider {
	private static final EntityType<?>[] BREEDABLE_ANIMALS = new EntityType[]{BBEntityTypes.MOOBLOOM.get()};

	public BBAdvancementModifierProvider(DataGenerator dataGenerator) {
		super(dataGenerator, BuzzierBees.MOD_ID);
	}

	@Override
	protected void registerEntries() {
		this.entry("nether/all_effects").selects("nether/all_effects").addModifier(new EffectsChangedModifier("all_effects", false, MobEffectsPredicate.effects().and(MobEffects.LUCK).and(MobEffects.UNLUCK).and(BBMobEffects.SUNNY.get())));
		this.entry("nether/all_potions").selects("nether/all_potions").addModifier(new EffectsChangedModifier("all_effects", false, MobEffectsPredicate.effects().and(MobEffects.LUCK).and(MobEffects.UNLUCK)));

		CriteriaModifier.Builder balancedDiet = CriteriaModifier.builder(this.modId);
		Collection<RegistryObject<Item>> items = BBItems.HELPER.getDeferredRegister().getEntries();
		items.forEach(item -> {
			if (item.get().isEdible()) {
				balancedDiet.addCriterion(item.get().getRegistryName().getPath(), ConsumeItemTrigger.TriggerInstance.usedItem(item.get()));
			}
		});
		this.entry("husbandry/balanced_diet").selects("husbandry/balanced_diet").addModifier(balancedDiet.requirements(RequirementsStrategy.AND).build());

		CriteriaModifier.Builder breedAllAnimals = CriteriaModifier.builder(this.modId);
		for (EntityType<?> entityType : BREEDABLE_ANIMALS) {
			breedAllAnimals.addCriterion(entityType.getRegistryName().getPath(), BredAnimalsTrigger.TriggerInstance.bredAnimals(EntityPredicate.Builder.entity().of(entityType)));
		}
		this.entry("husbandry/bred_all_animals").selects("husbandry/bred_all_animals").addModifier(breedAllAnimals.requirements(RequirementsStrategy.AND).build());

	}
}