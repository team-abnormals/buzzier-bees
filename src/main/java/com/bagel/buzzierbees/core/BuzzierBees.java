package com.bagel.buzzierbees.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.bagel.buzzierbees.common.dispenser.BeeBottleDispenseBehavior;
import com.bagel.buzzierbees.common.dispenser.BugBottleDispenseBehavior;
import com.bagel.buzzierbees.common.items.BBSpawnEggItem;
import com.bagel.buzzierbees.core.config.BBConfig;
import com.bagel.buzzierbees.core.registry.BBBlocks;
import com.bagel.buzzierbees.core.registry.BBEffects;
import com.bagel.buzzierbees.core.registry.BBEntities;
import com.bagel.buzzierbees.core.registry.BBFeatures;
import com.bagel.buzzierbees.core.registry.BBItems;
import com.bagel.buzzierbees.core.registry.BBTileEntities;
import com.bagel.buzzierbees.core.registry.other.BBData;
import com.bagel.buzzierbees.core.registry.other.FloristData;
import com.bagel.buzzierbees.core.util.BlockColorManager;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
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
    	ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, BBConfig.SERVER_SPEC);
    	IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	
    	BBItems.ITEMS.register(modEventBus);
    	BBEffects.EFFECTS.register(modEventBus);
    	BBEffects.POTIONS.register(modEventBus);
    	BBBlocks.BLOCKS.register(modEventBus);
    	BBEntities.ENTITIES.register(modEventBus);
    	BBTileEntities.TILE_ENTITY_TYPES.register(modEventBus);

        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::replaceBeehivePOI);
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
			modEventBus.addListener(EventPriority.LOWEST, this::registerItemColors);
			modEventBus.addListener(EventPriority.LOWEST, this::setupClient);
		});
    }
    
    public void initSetupClient()
	{
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupClient);
	}
    
    private void setupClient(final FMLClientSetupEvent event) {
    	BBConfig.refresh();
		BBData.setupRenderLayer();
		FloristData.setupRenderLayer();
		BBEntities.registerRendering();
		BlockColorManager.registerBlockColors();
	}
    
    public void modConfig(final ModConfig.ModConfigEvent event)
	{
		ModConfig config = event.getConfig();
		if (config.getSpec() == BBConfig.SERVER_SPEC)
			BBConfig.refresh();
	}
    
    private void setup(final FMLCommonSetupEvent event)
    {
    	BBConfig.refresh();
    	BBData.registerCompostables();
    	BBData.registerFlammables();
    	BBEffects.addBrewingRecipes();
		BBFeatures.addFeatures();
		BBEntities.addEntitySpawns();
    	
    	DispenserBlock.registerDispenseBehavior(BBItems.BOTTLE_OF_BEE.get(), new BeeBottleDispenseBehavior());
    	DispenserBlock.registerDispenseBehavior(BBItems.BOTTLE_OF_SILVERFISH.get(), new BugBottleDispenseBehavior());
    	DispenserBlock.registerDispenseBehavior(BBItems.BOTTLE_OF_ENDERMITE.get(), new BugBottleDispenseBehavior());
    	
        //DispenserBlock.registerDispenseBehavior(ModBlocks.CRYSTALLIZED_HONEY_BLOCK.get().asItem(), new ShulkerBoxDispenseBehavior());
    }
    
    /*
     * Temporary fix for forge bug
     * RegistryObject#isPresent causes a null pointer when it's false :crying: thanks forge
     * @author - Luke Tonon (SmellyModder)
     */
    @OnlyIn(Dist.CLIENT)
    private void registerItemColors(ColorHandlerEvent.Item event) {
        for(RegistryObject<Item> items : BBItems.SPAWN_EGGS) {
            if(ObfuscationReflectionHelper.getPrivateValue(RegistryObject.class, items, "value") != null) {
                Item item = items.get();
                if(item instanceof BBSpawnEggItem) {
                    event.getItemColors().register((itemColor, itemsIn) -> {
                        return ((BBSpawnEggItem) item).getColor(itemsIn);
                    }, item);
                }
            }
        }
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
    	addToMap(BBBlocks.CRIMSON_BEEHIVE.get(), map);
    	addToMap(BBBlocks.WARPED_BEEHIVE.get(), map);
    	
    	addToMap(BBBlocks.ROSEWOOD_BEEHIVE.get(), map);
    	addToMap(BBBlocks.YUCCA_BEEHIVE.get(), map);
    	addToMap(BBBlocks.KOUSA_BEEHIVE.get(), map);
    	addToMap(BBBlocks.ASPEN_BEEHIVE.get(), map);
    	addToMap(BBBlocks.WILLOW_BEEHIVE.get(), map);
    	addToMap(BBBlocks.WISTERIA_BEEHIVE.get(), map);
    	addToMap(BBBlocks.MAPLE_BEEHIVE.get(), map);
    	addToMap(BBBlocks.BAMBOO_BEEHIVE.get(), map);
    	addToMap(BBBlocks.POISE_BEEHIVE.get(), map);
    	addToMap(BBBlocks.DRIFTWOOD_BEEHIVE.get(), map);
    	addToMap(BBBlocks.RIVER_BEEHIVE.get(), map);
    	
    	addToMap(BBBlocks.SNAKE_BLOCK_BEEHIVE.get(), map);
    	
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
}
