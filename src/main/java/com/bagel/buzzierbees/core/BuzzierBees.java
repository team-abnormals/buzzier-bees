package com.bagel.buzzierbees.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.bagel.buzzierbees.common.dispenser.BeeBottleDispenseBehavior;
import com.bagel.buzzierbees.common.dispenser.BugBottleDispenseBehavior;
import com.bagel.buzzierbees.common.items.BBSpawnEggItem;
import com.bagel.buzzierbees.common.network.MessageCAnimation;
import com.bagel.buzzierbees.core.registry.BBBlockData;
import com.bagel.buzzierbees.core.registry.BBBlocks;
import com.bagel.buzzierbees.core.registry.BBEffects;
import com.bagel.buzzierbees.core.registry.BBEntities;
import com.bagel.buzzierbees.core.registry.BBFeatures;
import com.bagel.buzzierbees.core.registry.BBItems;
import com.bagel.buzzierbees.core.registry.BBTileEntities;
import com.bagel.buzzierbees.core.registry.BBVillagers;
import com.bagel.buzzierbees.core.util.BlockColorManager;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

@Mod("buzzierbees")
@EventBusSubscriber(modid = "buzzierbees")
public class BuzzierBees
{
	public static final String MODID = "buzzierbees";
	public static BuzzierBees instance;
	public static final String NETWORK_PROTOCOL = "1";
	
	public static final SimpleChannel CHANNEL = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(BuzzierBees.MODID, "net"))
		.networkProtocolVersion(() -> NETWORK_PROTOCOL)
		.clientAcceptedVersions(NETWORK_PROTOCOL::equals)
		.serverAcceptedVersions(NETWORK_PROTOCOL::equals)
		.simpleChannel();
	
    public BuzzierBees() {
    	instance = this;
    	this.setupMessages();
    	IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	
    	BBItems.ITEMS.register(modEventBus);
    	BBEffects.EFFECTS.register(modEventBus);
    	BBEffects.POTIONS.register(modEventBus);
    	BBBlocks.BLOCKS.register(modEventBus);
    	BBEntities.ENTITIES.register(modEventBus);
    	BBTileEntities.TILE_ENTITY_TYPES.register(modEventBus);
    	BBVillagers.PROFESSIONS.register(modEventBus);
    	BBVillagers.POI_TYPES.register(modEventBus);


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
		BBBlockData.setupRenderLayer();
		BBEntities.registerRendering();
		BlockColorManager.registerBlockColors();
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
    
	void setupMessages() {
		int id = -1;
		
		CHANNEL.messageBuilder(MessageCAnimation.class, id++)
		.encoder(MessageCAnimation::serialize).decoder(MessageCAnimation::deserialize)
		.consumer(MessageCAnimation::handle)
		.add();
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
