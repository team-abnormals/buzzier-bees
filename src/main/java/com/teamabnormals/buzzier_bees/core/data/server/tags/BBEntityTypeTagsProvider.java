package com.teamabnormals.buzzier_bees.core.data.server.tags;

import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import com.teamabnormals.buzzier_bees.core.other.tags.BBEntityTypeTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BBEntityTypeTagsProvider extends EntityTypeTagsProvider {

	public BBEntityTypeTagsProvider(DataGenerator generator, ExistingFileHelper fileHelper) {
		super(generator, BuzzierBees.MOD_ID, fileHelper);
	}

	@Override
	public void addTags() {
		this.tag(BBEntityTypeTags.MOOBLOOM_HOSTILES).add(EntityType.ZOMBIE, EntityType.ZOMBIE_VILLAGER, EntityType.STRAY, EntityType.DROWNED);
	}
}