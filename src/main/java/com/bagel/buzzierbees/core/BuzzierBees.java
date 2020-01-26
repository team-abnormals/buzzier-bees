package com.bagel.buzzierbees.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.bagel.buzzierbees.core.registry.ModBlocks;
import com.bagel.buzzierbees.core.registry.ModCompostables;
import com.bagel.buzzierbees.core.registry.ModEffects;
import com.bagel.buzzierbees.core.registry.ModEntities;
import com.bagel.buzzierbees.core.registry.ModItems;
import com.bagel.buzzierbees.core.registry.ModTileEntities;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("buzzierbees")
@EventBusSubscriber(modid = "buzzierbees")
public class BuzzierBees
{
	public static final String MODID = "buzzierbees";
    //public static final Logger LOGGER = LogManager.getLogger(MODID);
    
    public BuzzierBees() {
    	IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	
    	ModItems.ITEMS.register(modEventBus);
    	ModEffects.EFFECTS.register(modEventBus);
    	ModEffects.POTIONS.register(modEventBus);
    	ModBlocks.BLOCKS.register(modEventBus);
    	ModTileEntities.TILE_ENTITY_TYPES.register(modEventBus);
    	
    	
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::replaceBeehivePOI);
        DistExecutor.runWhenOn(Dist.CLIENT, () -> this::initSetupClient);
    }
    
    public void initSetupClient()
	{
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupClient);
	}
    
    private void setupClient(final FMLClientSetupEvent event) {
		ModBlocks.setupRenderLayer();
		//TileEntityRendererDispatcher.instance.func_228854_a_(ModTileEntities.PISTON.get(), new NewPistonTileEntityRenderer(TileEntityRendererDispatcher.instance));
	}
    
    private void setup(final FMLCommonSetupEvent event)
    {
    	ModCompostables.registerCompostables();
        ModEntities.addEntitySpawns();
        ModEffects.addBrewingRecipes();
        //DispenserBlock.registerDispenseBehavior(ModBlocks.CRYSTALLIZED_HONEY_BLOCK.get().asItem(), new ShulkerBoxDispenseBehavior());
    }

    private void replaceBeehivePOI(final FMLCommonSetupEvent event) {
    	final ImmutableList<Block> BEEHIVES = ImmutableList.of(
				Blocks.BEEHIVE,
				ModBlocks.ACACIA_BEEHIVE.get(),
				ModBlocks.BIRCH_BEEHIVE.get(),
				ModBlocks.SPRUCE_BEEHIVE.get(),
				ModBlocks.DARK_OAK_BEEHIVE.get(),
				ModBlocks.JUNGLE_BEEHIVE.get());
    	
    	Set<Block> newSet = new HashSet<>(TileEntityType.field_226985_G_.validBlocks);
    	newSet.addAll(BEEHIVES);
    	TileEntityType.field_226985_G_.validBlocks = newSet;
    	
    	final Set<BlockState> NESTS = ImmutableList.of(
				Blocks.BEEHIVE,
				ModBlocks.ACACIA_BEEHIVE.get(),
				ModBlocks.BIRCH_BEEHIVE.get(),
				ModBlocks.SPRUCE_BEEHIVE.get(),
				ModBlocks.DARK_OAK_BEEHIVE.get(),
				ModBlocks.JUNGLE_BEEHIVE.get())
				.stream().flatMap((p_221043_0_) -> {
					return p_221043_0_.getStateContainer().getValidStates().stream();
				}).collect(ImmutableSet.toImmutableSet());
    	
    	PointOfInterestType.field_226356_s_.field_221075_w = NESTS;
    	
    	Map<BlockState,PointOfInterestType> pointOfInterestTypeMap = new HashMap<>();
    	ModBlocks.SPRUCE_BEEHIVE.get().getStateContainer().getValidStates().forEach(state -> pointOfInterestTypeMap.put(state, PointOfInterestType.field_226356_s_));
    	ModBlocks.BIRCH_BEEHIVE.get().getStateContainer().getValidStates().forEach(state -> pointOfInterestTypeMap.put(state, PointOfInterestType.field_226356_s_));
    	ModBlocks.JUNGLE_BEEHIVE.get().getStateContainer().getValidStates().forEach(state -> pointOfInterestTypeMap.put(state, PointOfInterestType.field_226356_s_));
    	ModBlocks.ACACIA_BEEHIVE.get().getStateContainer().getValidStates().forEach(state -> pointOfInterestTypeMap.put(state, PointOfInterestType.field_226356_s_));
    	ModBlocks.DARK_OAK_BEEHIVE.get().getStateContainer().getValidStates().forEach(state -> pointOfInterestTypeMap.put(state, PointOfInterestType.field_226356_s_));
    	PointOfInterestType.field_221073_u.putAll(pointOfInterestTypeMap);
	}
}
