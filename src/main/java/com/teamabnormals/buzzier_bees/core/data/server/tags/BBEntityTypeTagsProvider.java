package com.teamabnormals.buzzier_bees.core.data.server.tags;

import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import com.teamabnormals.buzzier_bees.core.other.tags.BBEntityTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BBEntityTypeTagsProvider extends EntityTypeTagsProvider {

	public BBEntityTypeTagsProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, BuzzierBees.MOD_ID, existingFileHelper);
	}

	@Override
	public void addTags() {
		this.tag(BBEntityTags.MOOBLOOM_HOSTILES).add(EntityType.ZOMBIE, EntityType.ZOMBIE_VILLAGER, EntityType.STRAY, EntityType.DROWNED);
	}
}