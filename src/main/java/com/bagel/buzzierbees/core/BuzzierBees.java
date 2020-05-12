package com.bagel.buzzierbees.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.bagel.buzzierbees.common.dispenser.BeeBottleDispenseBehavior;
import com.bagel.buzzierbees.common.dispenser.BugBottleDispenseBehavior;
import com.bagel.buzzierbees.core.registry.BBBlockData;
import com.bagel.buzzierbees.core.registry.BBBlocks;
import com.bagel.buzzierbees.core.registry.BBEffects;
import com.bagel.buzzierbees.core.registry.BBEntities;
import com.bagel.buzzierbees.core.registry.BBFeatures;
import com.bagel.buzzierbees.core.registry.BBItems;
import com.bagel.buzzierbees.core.registry.BBVillagers;
import com.bagel.buzzierbees.core.util.BlockColorManager;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("buzzierbees")
@EventBusSubscriber(modid = "buzzierbees")
public class BuzzierBees
{
	public static final String MODID = "buzzierbees";
	public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MODID);
	
    public BuzzierBees() {
    	IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	
    	REGISTRY_HELPER.getDeferredBlockRegister().register(modEventBus);
    	REGISTRY_HELPER.getDeferredItemRegister().register(modEventBus);
    	REGISTRY_HELPER.getDeferredEntityRegister().register(modEventBus);
    	REGISTRY_HELPER.getDeferredTileEntityRegister().register(modEventBus);

    	BBEffects.EFFECTS.register(modEventBus);
    	BBEffects.POTIONS.register(modEventBus);
    	BBVillagers.PROFESSIONS.register(modEventBus);
    	BBVillagers.POI_TYPES.register(modEventBus);
    	
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::replaceBeehivePOI);
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
			modEventBus.addListener(EventPriority.LOWEST, this::setupClient);
			modEventBus.addListener(EventPriority.LOWEST, this::registerItemColors);
		});
    }
    
    public void initSetupClient()
	{
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupClient);
	}
    
    private void setupClient(final FMLClientSetupEvent event) {
		BBBlockData.setupRenderLayer();
		BBEntities.registerRendering();
		BlockColorManager.registerBlockColors();
	}
    
    @OnlyIn(Dist.CLIENT)
	private void registerItemColors(ColorHandlerEvent.Item event) {
		REGISTRY_HELPER.processSpawnEggColors(event);
	}
    
    private void setup(final FMLCommonSetupEvent event)
    {
    	BBBlockData.registerCompostables();
    	BBBlockData.registerFlammables();
    	BBEffects.addBrewingRecipes();
		BBFeatures.addFeatures();
		BBEntities.addEntitySpawns();
		BBVillagers.init();
    	
    	DispenserBlock.registerDispenseBehavior(BBItems.BOTTLE_OF_BEE.get(), new BeeBottleDispenseBehavior());
    	DispenserBlock.registerDispenseBehavior(BBItems.BOTTLE_OF_SILVERFISH.get(), new BugBottleDispenseBehavior());
    	DispenserBlock.registerDispenseBehavior(BBItems.BOTTLE_OF_ENDERMITE.get(), new BugBottleDispenseBehavior());
    	
        //DispenserBlock.registerDispenseBehavior(ModBlocks.CRYSTALLIZED_HONEY_BLOCK.get().asItem(), new ShulkerBoxDispenseBehavior());
    }

    private void replaceBeehivePOI(final FMLCommonSetupEvent event) {
    	
    	final ImmutableList<Block> BEEHIVES = ImmutableList.of(
				Blocks.BEEHIVE,
				BBBlocks.ACACIA_BEEHIVE.get(),
				BBBlocks.BIRCH_BEEHIVE.get(),
				BBBlocks.SPRUCE_BEEHIVE.get(),
				BBBlocks.DARK_OAK_BEEHIVE.get(),
				BBBlocks.JUNGLE_BEEHIVE.get(),
				BBBlocks.CRIMSON_BEEHIVE.get(),
				BBBlocks.WARPED_BEEHIVE.get(),
				
				BBBlocks.ROSEWOOD_BEEHIVE.get(),
				BBBlocks.YUCCA_BEEHIVE.get(),
				BBBlocks.KOUSA_BEEHIVE.get(),
				BBBlocks.ASPEN_BEEHIVE.get(),
				BBBlocks.WILLOW_BEEHIVE.get(),
				BBBlocks.WISTERIA_BEEHIVE.get(),
				BBBlocks.MAPLE_BEEHIVE.get(),
				BBBlocks.BAMBOO_BEEHIVE.get(),
				BBBlocks.POISE_BEEHIVE.get(),
				BBBlocks.DRIFTWOOD_BEEHIVE.get(),
				BBBlocks.RIVER_BEEHIVE.get(),
				
				BBBlocks.SNAKE_BLOCK_BEEHIVE.get(),
				
				BBBlocks.BOP_FIR_BEEHIVE.get(),
				BBBlocks.BOP_DEAD_BEEHIVE.get(),
				BBBlocks.BOP_PALM_BEEHIVE.get(),
				BBBlocks.BOP_MAGIC_BEEHIVE.get(),
				BBBlocks.BOP_CHERRY_BEEHIVE.get(),
				BBBlocks.BOP_UMBRAN_BEEHIVE.get(),
				BBBlocks.BOP_WILLOW_BEEHIVE.get(),
				BBBlocks.BOP_REDWOOD_BEEHIVE.get(),
				BBBlocks.BOP_HELLBARK_BEEHIVE.get(),
				BBBlocks.BOP_MAHOGANY_BEEHIVE.get(),
				BBBlocks.BOP_JACARANDA_BEEHIVE.get()
    			);
    	
    	// TILE ENTITY //
    	
    	Set<Block> validBlocks = new HashSet<>(TileEntityType.BEEHIVE.validBlocks);
    	validBlocks.addAll(BEEHIVES);
    	TileEntityType.BEEHIVE.validBlocks = validBlocks;
    	
    	// MORE POI STUFF //
    	
    	Set<BlockState> poiStates = BEEHIVES.stream().flatMap((map) -> { return map.getStateContainer().getValidStates().stream();	
    	}).collect(ImmutableSet.toImmutableSet());
    	PointOfInterestType.BEEHIVE.blockStates = poiStates;
    	    	
    	// BEEHIVE POI TYPE //
    	
    	Map<BlockState, PointOfInterestType> pointOfInterestTypeMap = new HashMap<>();
        BEEHIVES.stream().forEach(block -> block.getStateContainer().getValidStates().forEach(state -> pointOfInterestTypeMap.put(state, PointOfInterestType.BEEHIVE)));
        PointOfInterestType.POIT_BY_BLOCKSTATE.putAll(pointOfInterestTypeMap);
	}
}
