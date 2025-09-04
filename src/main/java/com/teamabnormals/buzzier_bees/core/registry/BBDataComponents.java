package com.teamabnormals.buzzier_bees.core.registry;

import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.component.CustomData;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BBDataComponents {
	public static final DeferredRegister<DataComponentType<?>> COMPONENTS = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, BuzzierBees.MOD_ID);

	public static final DeferredHolder<DataComponentType<?>, DataComponentType<CustomData>> BOTTLE_ENTITY_DATA = COMPONENTS.register("bottle_entity_data", () -> DataComponentType.<CustomData>builder().persistent(CustomData.CODEC).networkSynchronized(CustomData.STREAM_CODEC).build());
}