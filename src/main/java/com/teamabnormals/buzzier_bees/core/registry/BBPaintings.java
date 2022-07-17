package com.teamabnormals.buzzier_bees.core.registry;

import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.world.entity.decoration.Motive;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BBPaintings {
	public static final DeferredRegister<Motive> PAINTINGS = DeferredRegister.create(ForgeRegistries.PAINTING_TYPES, BuzzierBees.MOD_ID);

	public static final RegistryObject<Motive> CANDLE = PAINTINGS.register("candle", () -> new Motive(32, 48));
}
