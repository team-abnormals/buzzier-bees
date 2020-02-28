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

// The value here should match an entry in the META-INF/mods.toml file
@Mod("buzzierbees")
@EventBusSubscriber(modid = "buzzierbees")
public class BuzzierBees
{
	public static final String MODID = "buzzierbees";
    //public static final Logger LOGGER = LogManager.getLogger(MODID);
    
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
		BBBlocks.setupRenderLayer();
		BBEntities.registerRendering();
		BBFeatures.addFeatures();
		//TileEntityRendererDispatcher.instance.func_228854_a_(ModTileEntities.PISTON.get(), new NewPistonTileEntityRenderer(TileEntityRendererDispatcher.instance));
	}
    
    private void setup(final FMLCommonSetupEvent event)
    {
    	BuzzierBeesCommonConfig.refresh();
    	BBBlockData.registerCompostables();
    	BBBlockData.registerFlammables();
    	if (BuzzierBeesCommonConfig.spawnHoneySlimes) {BBEntities.addEntitySpawns();}
        BBEffects.addBrewingRecipes();
        //DispenserBlock.registerDispenseBehavior(ModBlocks.CRYSTALLIZED_HONEY_BLOCK.get().asItem(), new ShulkerBoxDispenseBehavior());
    }

    private void replaceBeehivePOI(final FMLCommonSetupEvent event) {
    	final ImmutableList<Block> BEEHIVES = ImmutableList.of(
				Blocks.BEEHIVE,
				BBBlocks.ACACIA_BEEHIVE.get(),
				BBBlocks.BIRCH_BEEHIVE.get(),
				BBBlocks.SPRUCE_BEEHIVE.get(),
				BBBlocks.DARK_OAK_BEEHIVE.get(),
				BBBlocks.JUNGLE_BEEHIVE.get());
    	
    	Set<Block> newSet = new HashSet<>(TileEntityType.field_226985_G_.validBlocks);
    	newSet.addAll(BEEHIVES);
    	TileEntityType.field_226985_G_.validBlocks = newSet;
    	
    	final Set<BlockState> NESTS = ImmutableList.of(
				Blocks.BEEHIVE,
				BBBlocks.ACACIA_BEEHIVE.get(),
				BBBlocks.BIRCH_BEEHIVE.get(),
				BBBlocks.SPRUCE_BEEHIVE.get(),
				BBBlocks.DARK_OAK_BEEHIVE.get(),
				BBBlocks.JUNGLE_BEEHIVE.get())
				.stream().flatMap((p_221043_0_) -> {
					return p_221043_0_.getStateContainer().getValidStates().stream();
				}).collect(ImmutableSet.toImmutableSet());
    	
    	PointOfInterestType.field_226356_s_.field_221075_w = NESTS;
    	
    	Map<BlockState,PointOfInterestType> pointOfInterestTypeMap = new HashMap<>();
    	BBBlocks.SPRUCE_BEEHIVE.get().getStateContainer().getValidStates().forEach(state -> pointOfInterestTypeMap.put(state, PointOfInterestType.field_226356_s_));
    	BBBlocks.BIRCH_BEEHIVE.get().getStateContainer().getValidStates().forEach(state -> pointOfInterestTypeMap.put(state, PointOfInterestType.field_226356_s_));
    	BBBlocks.JUNGLE_BEEHIVE.get().getStateContainer().getValidStates().forEach(state -> pointOfInterestTypeMap.put(state, PointOfInterestType.field_226356_s_));
    	BBBlocks.ACACIA_BEEHIVE.get().getStateContainer().getValidStates().forEach(state -> pointOfInterestTypeMap.put(state, PointOfInterestType.field_226356_s_));
    	BBBlocks.DARK_OAK_BEEHIVE.get().getStateContainer().getValidStates().forEach(state -> pointOfInterestTypeMap.put(state, PointOfInterestType.field_226356_s_));
    	PointOfInterestType.field_221073_u.putAll(pointOfInterestTypeMap);
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
