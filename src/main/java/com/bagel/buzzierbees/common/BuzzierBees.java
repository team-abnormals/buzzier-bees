package com.bagel.buzzierbees.common;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bagel.buzzierbees.common.blocks.ModBlocks;
import com.bagel.buzzierbees.common.blocks.ModTileEntities;
import com.bagel.buzzierbees.common.entities.HoneySlimeEntity;
import com.bagel.buzzierbees.common.entities.ModEntities;
import com.bagel.buzzierbees.common.items.ModItems;
import com.bagel.buzzierbees.common.potions.ModPotions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionBrewing;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.village.PointOfInterestType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
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

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    
    
    public BuzzierBees() {
    	final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	ModTileEntities.TILE_ENTITY_TYPES.register(modEventBus);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupClient);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::replaceBeehivePOI);
        MinecraftForge.EVENT_BUS.register(this);    
    }

    void replaceBeehivePOI(final FMLCommonSetupEvent event) {
		final Set<BlockState> BEEHIVES = ImmutableList.of(
				Blocks.field_226906_mb_,
				ModBlocks.ACACIA_BEEHIVE,
				ModBlocks.BIRCH_BEEHIVE,
				ModBlocks.SPRUCE_BEEHIVE,
				ModBlocks.DARK_OAK_BEEHIVE,
				ModBlocks.JUNGLE_BEEHIVE)
				.stream().flatMap((p_221043_0_) -> {
					return p_221043_0_.getStateContainer().getValidStates().stream();
				}).collect(ImmutableSet.toImmutableSet());
    	
    	PointOfInterestType.field_226356_s_.field_221075_w = BEEHIVES;
    	
    	Map<BlockState,PointOfInterestType> pointOfInterestTypeMap = new HashMap<>();
    	ModBlocks.SPRUCE_BEEHIVE.getStateContainer().getValidStates().forEach(state -> pointOfInterestTypeMap.put(state, PointOfInterestType.field_226356_s_));
    	ModBlocks.BIRCH_BEEHIVE.getStateContainer().getValidStates().forEach(state -> pointOfInterestTypeMap.put(state, PointOfInterestType.field_226356_s_));
    	ModBlocks.JUNGLE_BEEHIVE.getStateContainer().getValidStates().forEach(state -> pointOfInterestTypeMap.put(state, PointOfInterestType.field_226356_s_));
    	ModBlocks.ACACIA_BEEHIVE.getStateContainer().getValidStates().forEach(state -> pointOfInterestTypeMap.put(state, PointOfInterestType.field_226356_s_));
    	ModBlocks.DARK_OAK_BEEHIVE.getStateContainer().getValidStates().forEach(state -> pointOfInterestTypeMap.put(state, PointOfInterestType.field_226356_s_));
    	PointOfInterestType.field_221073_u.putAll(pointOfInterestTypeMap);
	}
    
    private void setup(final FMLCommonSetupEvent event)
    {
    	final ImmutableList<Block> BEEHIVES = ImmutableList.of(
				Blocks.field_226906_mb_,
				ModBlocks.ACACIA_BEEHIVE,
				ModBlocks.BIRCH_BEEHIVE,
				ModBlocks.SPRUCE_BEEHIVE,
				ModBlocks.DARK_OAK_BEEHIVE,
				ModBlocks.JUNGLE_BEEHIVE);
    	
    	Set<Block> newSet = new HashSet<>(TileEntityType.field_226985_G_.validBlocks);
    	newSet.addAll(BEEHIVES);
    	TileEntityType.field_226985_G_.validBlocks = newSet;
        addEntitySpawns();
        addBrewingRecipes();
    }

	@OnlyIn(Dist.CLIENT)
	private void setupClient(final FMLClientSetupEvent event) {
		setupRenderLayer();
		
		//TileEntityRendererDispatcher.instance.func_228854_a_(ModTileEntities.PISTON.get(), new NewPistonTileEntityRenderer(TileEntityRendererDispatcher.instance));

		LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
	}

	private void addEntitySpawns() {
    	//Condition Registry
		EntitySpawnPlacementRegistry.register(ModEntities.HONEY_SLIME, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HoneySlimeEntity::honeySlimeCondition);

		//Spawn Registry
		Biomes.FLOWER_FOREST.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(ModEntities.HONEY_SLIME, 100, 4, 4));
	}

    private void addBrewingRecipes() {
		//TODO: Temporary Clover Honey recipe (until hive situation sorted out)
		/*BrewingRecipeRegistry.addRecipe(
				Ingredient.fromItems(Items.field_226638_pX_),
				Ingredient.fromItems(ModItems.CLOVER_LEAF),
				new ItemStack(ModItems.CLOVER_HONEY_BOTTLE));*/
		
		ItemStack weakCure = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), ModPotions.WEAKNESS_CURE);
		BrewingRecipeRegistry.addRecipe(
				Ingredient.fromItems(ModItems.CLOVER_HONEY_BOTTLE),
				Ingredient.fromItems(Items.FERMENTED_SPIDER_EYE),
				weakCure);

		PotionBrewing.addMix(Potions.AWKWARD, ModItems.FOUR_LEAF_CLOVER, Potions.LUCK);
		PotionBrewing.addMix(Potions.LUCK, Items.REDSTONE, ModPotions.LONG_LUCK);
		PotionBrewing.addMix(Potions.LUCK, Items.GLOWSTONE_DUST, ModPotions.STRONG_LUCK);
		PotionBrewing.addMix(Potions.LUCK, Items.FERMENTED_SPIDER_EYE, ModPotions.UNLUCK);
		PotionBrewing.addMix(ModPotions.UNLUCK, Items.REDSTONE, ModPotions.LONG_UNLUCK);
		PotionBrewing.addMix(ModPotions.UNLUCK, Items.GLOWSTONE_DUST, ModPotions.STRONG_UNLUCK);
		PotionBrewing.addMix(ModPotions.LONG_LUCK, Items.FERMENTED_SPIDER_EYE, ModPotions.LONG_UNLUCK);
		PotionBrewing.addMix(ModPotions.STRONG_LUCK, Items.FERMENTED_SPIDER_EYE, ModPotions.STRONG_UNLUCK);

		//Cures Brewing
		ItemStack placebo = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), ModPotions.PLACEBO);
		BrewingRecipeRegistry.addRecipe(
				Ingredient.fromItems(ModItems.CLOVER_HONEY_BOTTLE),
				Ingredient.fromItems(Items.NETHER_WART),
				placebo);
		PotionBrewing.addMix(ModPotions.PLACEBO, Items.GOLDEN_CARROT, ModPotions.NIGHT_VISION_CURE);
		PotionBrewing.addMix(ModPotions.NIGHT_VISION_CURE, Items.FERMENTED_SPIDER_EYE, ModPotions.INVISIBILITY_CURE);
		PotionBrewing.addMix(ModPotions.PLACEBO, Items.MAGMA_CREAM, ModPotions.FIRE_RESISTANCE_CURE);
		PotionBrewing.addMix(ModPotions.PLACEBO, Items.RABBIT_FOOT, ModPotions.LEAPING_CURE);
		PotionBrewing.addMix(ModPotions.LEAPING_CURE, Items.FERMENTED_SPIDER_EYE, ModPotions.SLOWNESS_CURE);
		PotionBrewing.addMix(ModPotions.SWIFTNESS_CURE, Items.FERMENTED_SPIDER_EYE, ModPotions.SLOWNESS_CURE);
		PotionBrewing.addMix(ModPotions.PLACEBO, Items.SUGAR, ModPotions.SWIFTNESS_CURE);
		PotionBrewing.addMix(ModPotions.PLACEBO, Items.PUFFERFISH, ModPotions.WATER_BREATHING_CURE);
		PotionBrewing.addMix(ModPotions.PLACEBO, Items.GHAST_TEAR, ModPotions.REGENERATION_CURE);
		PotionBrewing.addMix(ModPotions.PLACEBO, Items.BLAZE_POWDER, ModPotions.STRENGTH_CURE);
		PotionBrewing.addMix(ModPotions.PLACEBO, Items.PHANTOM_MEMBRANE, ModPotions.SLOW_FALLING_CURE);
		PotionBrewing.addMix(ModPotions.PLACEBO, Items.SPIDER_EYE, ModPotions.POISON_CURE);
		PotionBrewing.addMix(ModPotions.PLACEBO, ModItems.FOUR_LEAF_CLOVER, ModPotions.LUCK_CURE);
		PotionBrewing.addMix(ModPotions.LUCK_CURE, Items.FERMENTED_SPIDER_EYE, ModPotions.UNLUCK_CURE);
	}

    private void setupRenderLayer()
	{
		ModEntities.registerRendering();

		//RenderTypeLookup.setRenderLayer(ModBlocks.CRYSTALLIZED_HONEY_BLOCK,RenderType.func_228645_f_());
		RenderTypeLookup.setRenderLayer(ModBlocks.CLOVER_HONEY_BLOCK,RenderType.func_228645_f_());
		RenderTypeLookup.setRenderLayer(ModBlocks.HONEY_LAMP,RenderType.func_228645_f_());

		//Doors and Trapdoors
		RenderTypeLookup.setRenderLayer(ModBlocks.HIVE_DOOR,RenderType.func_228643_e_());
		RenderTypeLookup.setRenderLayer(ModBlocks.HIVE_TRAPDOOR,RenderType.func_228643_e_());

		//Flowers
		RenderTypeLookup.setRenderLayer(ModBlocks.WHITE_CLOVER,RenderType.func_228641_d_());
		RenderTypeLookup.setRenderLayer(ModBlocks.PINK_CLOVER,RenderType.func_228641_d_());
		RenderTypeLookup.setRenderLayer(ModBlocks.CARTWHEEL,RenderType.func_228641_d_());
		RenderTypeLookup.setRenderLayer(ModBlocks.VIOLET,RenderType.func_228641_d_());
		RenderTypeLookup.setRenderLayer(ModBlocks.COLUMBINE,RenderType.func_228641_d_());
		RenderTypeLookup.setRenderLayer(ModBlocks.JOLYCE,RenderType.func_228641_d_());
		RenderTypeLookup.setRenderLayer(ModBlocks.BLUEBELL,RenderType.func_228641_d_());
		RenderTypeLookup.setRenderLayer(ModBlocks.DAYBLOOM,RenderType.func_228641_d_());
		RenderTypeLookup.setRenderLayer(ModBlocks.BIRD_OF_PARADISE,RenderType.func_228641_d_());

		//Potted Flowers
		RenderTypeLookup.setRenderLayer(ModBlocks.POTTED_WHITE_CLOVER,RenderType.func_228641_d_());
		RenderTypeLookup.setRenderLayer(ModBlocks.POTTED_PINK_CLOVER,RenderType.func_228641_d_());
		RenderTypeLookup.setRenderLayer(ModBlocks.POTTED_CARTWHEEL,RenderType.func_228641_d_());
		RenderTypeLookup.setRenderLayer(ModBlocks.POTTED_VIOLET,RenderType.func_228641_d_());
		RenderTypeLookup.setRenderLayer(ModBlocks.POTTED_COLUMBINE,RenderType.func_228641_d_());
		RenderTypeLookup.setRenderLayer(ModBlocks.POTTED_JOLYCE,RenderType.func_228641_d_());
		RenderTypeLookup.setRenderLayer(ModBlocks.POTTED_BLUEBELL,RenderType.func_228641_d_());
		RenderTypeLookup.setRenderLayer(ModBlocks.POTTED_DAYBLOOM,RenderType.func_228641_d_());
	}
    


	//TODO: These methods
    /*private void enqueueIMC(final InterModEnqueueEvent event)
    {
        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }*/
}
