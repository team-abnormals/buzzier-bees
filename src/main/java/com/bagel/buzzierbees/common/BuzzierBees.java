package com.bagel.buzzierbees.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bagel.buzzierbees.common.blocks.ModBlocks;
import com.bagel.buzzierbees.common.blocks.ModTileEntities;
import com.bagel.buzzierbees.common.entities.ModEntities;
import com.bagel.buzzierbees.common.items.HoneyWandItem;
import com.bagel.buzzierbees.common.items.ModItems;
import com.bagel.buzzierbees.common.potions.ModPotions;

import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.PotionBrewing;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("buzzierbees")
public class BuzzierBees
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
	public static final String MODID = "buzzierbees";

    public BuzzierBees() {
    	final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	ModTileEntities.TILE_ENTITY_TYPES.register(modEventBus);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupClient);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
        addBrewingRecipes();
    }
    

    private void addBrewingRecipes() {		
		//Temporary Clover Honey recipe (until we get hive situation sorted out)
		BrewingRecipeRegistry.addRecipe(
				Ingredient.fromItems(Items.field_226638_pX_),
				Ingredient.fromItems(ModItems.CLOVER_LEAF),
				new ItemStack(ModItems.CLOVER_HONEY_BOTTLE));
		
		ItemStack weakCure = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), ModPotions.WEAKNESS_CURE);
		BrewingRecipeRegistry.addRecipe(
				Ingredient.fromItems(ModItems.CLOVER_HONEY_BOTTLE),
				Ingredient.fromItems(Items.FERMENTED_SPIDER_EYE),
				weakCure);

		ItemStack placebo = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), ModPotions.PLACEBO);
		BrewingRecipeRegistry.addRecipe(
				Ingredient.fromItems(ModItems.CLOVER_HONEY_BOTTLE),
				Ingredient.fromItems(Items.NETHER_WART),
				placebo);

		PotionBrewing.addMix(Potions.AWKWARD, ModItems.FOUR_LEAF_CLOVER, Potions.LUCK);
		PotionBrewing.addMix(Potions.LUCK, Items.REDSTONE, ModPotions.LONG_LUCK);
		PotionBrewing.addMix(Potions.LUCK, Items.GLOWSTONE_DUST, ModPotions.STRONG_LUCK);
		PotionBrewing.addMix(Potions.LUCK, Items.FERMENTED_SPIDER_EYE, ModPotions.BAD_LUCK);
		PotionBrewing.addMix(ModPotions.BAD_LUCK, Items.REDSTONE, ModPotions.LONG_UNLUCK);
		PotionBrewing.addMix(ModPotions.BAD_LUCK, Items.GLOWSTONE_DUST, ModPotions.STRONG_UNLUCK);
		PotionBrewing.addMix(ModPotions.LONG_LUCK, Items.FERMENTED_SPIDER_EYE, ModPotions.LONG_UNLUCK);
		PotionBrewing.addMix(ModPotions.STRONG_LUCK, Items.FERMENTED_SPIDER_EYE, ModPotions.STRONG_UNLUCK);

		//Cures Brewing
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
    
    @OnlyIn(Dist.CLIENT)
    private void setupClient(final FMLClientSetupEvent event) {
    	ModItems.HONEY_WAND.addPropertyOverride(new ResourceLocation(MODID, "sticky"),
                (stack, world, entity) -> {
                	CompoundNBT nbt = stack.getOrCreateTag(); 
    				return entity != null && nbt.getBoolean(HoneyWandItem.STICKY_KEY) ? 1.0F : 0.0F;
                });
    	
    	setupRenderLayer();

        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
    }

    private void setupRenderLayer()
	{
		ModEntities.registerRendering();

		RenderTypeLookup.setRenderLayer(ModBlocks.CRYSTALLIZED_HONEY_BLOCK,RenderType.func_228645_f_());
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
