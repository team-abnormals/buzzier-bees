package com.bagel.buzzierbees.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.bagel.buzzierbees.common.blocks.CandleBlock;
import com.bagel.buzzierbees.core.registry.BBBlocks;
import com.bagel.buzzierbees.core.config.BuzzierBeesCommonConfig;
import com.bagel.buzzierbees.core.registry.BBBlockData;
import com.bagel.buzzierbees.core.registry.BBEffects;
import com.bagel.buzzierbees.core.registry.BBEntities;
import com.bagel.buzzierbees.core.registry.BBFeatures;
import com.bagel.buzzierbees.core.registry.BBItems;
import com.bagel.buzzierbees.core.registry.BBTags;
import com.bagel.buzzierbees.core.registry.BBTileEntities;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.tags.BlockTags;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("buzzierbees")
@EventBusSubscriber(modid = "buzzierbees")
public class BuzzierBees
{
	public static final String MODID = "buzzierbees";
    
    public BuzzierBees() {
    	ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, BuzzierBeesCommonConfig.spec);
    	IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	
    	BBItems.ITEMS.register(modEventBus);
    	BBEffects.EFFECTS.register(modEventBus);
    	BBEffects.POTIONS.register(modEventBus);
    	BBBlocks.BLOCKS.register(modEventBus);
    	BBTileEntities.TILE_ENTITY_TYPES.register(modEventBus);

        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::replaceBeehivePOI);
        DistExecutor.runWhenOn(Dist.CLIENT, () -> this::initSetupClient);
    }
    
    public void initSetupClient()
	{
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupClient);
	}
    
    private void setupClient(final FMLClientSetupEvent event) {
    	BuzzierBeesCommonConfig.refresh();
		BBBlockData.setupRenderLayer();
		BBEntities.registerRendering();
		BBFeatures.addFeatures();
	}
    
    private void setup(final FMLCommonSetupEvent event)
    {
    	BuzzierBeesCommonConfig.refresh();
    	BBBlockData.registerCompostables();
    	BBBlockData.registerFlammables();
    	BBEffects.addBrewingRecipes();
    	BBEntities.addEntitySpawns();
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
    	
    	Set<Block> newSet = new HashSet<>(TileEntityType.field_226985_G_.validBlocks);
    	newSet.addAll(BEEHIVES);
    	TileEntityType.field_226985_G_.validBlocks = newSet;
    	
    	final Set<BlockState> NESTS = BlockTags.BEEHIVES.getAllElements().stream().flatMap((map) -> {
    		return map.getStateContainer().getValidStates().stream();	
    	}).collect(ImmutableSet.toImmutableSet());
    	
    	PointOfInterestType.field_226356_s_.field_221075_w = NESTS;
    	
    	Map<BlockState,PointOfInterestType> map = new HashMap<>();
    	addToMap(Blocks.BEEHIVE, map);
    	addToMap(BBBlocks.ACACIA_BEEHIVE.get(), map);
    	addToMap(BBBlocks.BIRCH_BEEHIVE.get(), map);
    	addToMap(BBBlocks.SPRUCE_BEEHIVE.get(), map);
    	addToMap(BBBlocks.DARK_OAK_BEEHIVE.get(), map);
    	addToMap(BBBlocks.JUNGLE_BEEHIVE.get(), map);
    	
    	addToMap(BBBlocks.BOP_FIR_BEEHIVE.get(), map);
    	addToMap(BBBlocks.BOP_DEAD_BEEHIVE.get(), map);
    	addToMap(BBBlocks.BOP_PALM_BEEHIVE.get(), map);
    	addToMap(BBBlocks.BOP_MAGIC_BEEHIVE.get(), map);
    	addToMap(BBBlocks.BOP_CHERRY_BEEHIVE.get(), map);
    	addToMap(BBBlocks.BOP_UMBRAN_BEEHIVE.get(), map);
    	addToMap(BBBlocks.BOP_WILLOW_BEEHIVE.get(), map);
    	addToMap(BBBlocks.BOP_REDWOOD_BEEHIVE.get(), map);
    	addToMap(BBBlocks.BOP_HELLBARK_BEEHIVE.get(), map);
    	addToMap(BBBlocks.BOP_MAHOGANY_BEEHIVE.get(), map);
    	addToMap(BBBlocks.BOP_JACARANDA_BEEHIVE.get(), map);
    	PointOfInterestType.field_221073_u.putAll(map);
	}
    
    public static void addToMap(Block block, Map<BlockState,PointOfInterestType> pointOfInterestTypeMap) {
    	block.getStateContainer().getValidStates().forEach(state -> pointOfInterestTypeMap.put(state, PointOfInterestType.field_226356_s_));
    }
    
    @SubscribeEvent
    public static void entityJoinWorldEvent(EntityJoinWorldEvent event) {
    	Entity entity = event.getEntity();
    	if (entity instanceof ItemEntity && ((ItemEntity) entity).getItem().getItem().isIn(BBTags.CANDLES)) {
    		event.getWorld().getEntitiesWithinAABB(FallingBlockEntity.class, entity.getBoundingBox()).stream()
    		.filter(falling -> falling.getBlockState().getBlock() instanceof CandleBlock && entity.getPositionVec().equals(falling.getPositionVec()))
    		.findAny().ifPresent(falling -> ((ItemEntity) entity).getItem().setCount(falling.getBlockState().get(CandleBlock.CANDLES)));
    	}
    }
}
