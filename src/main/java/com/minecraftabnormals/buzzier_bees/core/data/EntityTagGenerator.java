package com.minecraftabnormals.buzzier_bees.core.data;

import com.minecraftabnormals.buzzier_bees.core.BuzzierBees;
import com.minecraftabnormals.buzzier_bees.core.other.BBBlockTags;
import com.minecraftabnormals.buzzier_bees.core.other.BBEntityTags;
import com.minecraftabnormals.buzzier_bees.core.other.BBItemTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.EntityTypeTagsProvider;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class EntityTagGenerator extends EntityTypeTagsProvider {

	public EntityTagGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, BuzzierBees.MOD_ID, existingFileHelper);
	}

	@Override
	public void addTags() {
		this.tag(BBEntityTags.MOOBLOOM_HOSTILES).add(EntityType.ZOMBIE, EntityType.ZOMBIE_VILLAGER, EntityType.STRAY, EntityType.DROWNED);
	}
}