package com.minecraftabnormals.buzzier_bees.core.other;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.minecraftabnormals.abnormals_core.core.registry.LootInjectionRegistry;
import com.minecraftabnormals.abnormals_core.core.util.BlockUtil;
import com.minecraftabnormals.abnormals_core.core.util.DataUtil;
import com.minecraftabnormals.buzzier_bees.common.blocks.CandleBlock;
import com.minecraftabnormals.buzzier_bees.common.dispenser.BeeBottleDispenseBehavior;
import com.minecraftabnormals.buzzier_bees.common.dispenser.BugBottleDispenseBehavior;
import com.minecraftabnormals.buzzier_bees.core.BuzzierBees;
import com.minecraftabnormals.buzzier_bees.core.registry.BBBlocks;
import com.minecraftabnormals.buzzier_bees.core.registry.BBItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.EndermiteEntity;
import net.minecraft.entity.monster.SilverfishEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTables;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.DispenserTileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

import java.util.Hashtable;
import java.util.function.Function;

public class BBCompat {
	public static final Hashtable<EntityType<?>, Function<Entity, ItemStack>> ENTITY_TYPE_TO_BOTTLE_MAP = new Hashtable<>();

	public static class CompatMods {
		public static final String ATMOSPHERIC = "atmospheric";
		public static final String AUTUMNITY = "autumnity";
		public static final String ENVIRONMENTAL = "environmental";
		public static final String UPGRADE_AQUATIC = "upgrade_aquatic";
		public static final String ENDERGETIC = "endergetic";
	}

	public static class CompatEffects {
		public static final ResourceLocation RELIEF = new ResourceLocation(CompatMods.ATMOSPHERIC, "relief");
		public static final ResourceLocation WORSENING = new ResourceLocation(CompatMods.ATMOSPHERIC, "worsening");
		public static final ResourceLocation PERSISTENCE = new ResourceLocation(CompatMods.ATMOSPHERIC, "persistence");
		public static final ResourceLocation FOUL_TASTE = new ResourceLocation(CompatMods.AUTUMNITY, "foul_taste");
	}

	public static void registerCompat() {
		registerLootInjectors();
		registerCompostables();
		registerFlammables();
		registerBottleEntityTypes();
		registerDispenserBehaviors();
	}

	public static void registerLootInjectors() {
		LootInjectionRegistry.LootInjector injector = new LootInjectionRegistry.LootInjector(BuzzierBees.MOD_ID);
		injector.addLootInjection(injector.buildLootPool("desert_pyramid", 1, 0), LootTables.CHESTS_DESERT_PYRAMID);
		injector.addLootInjection(injector.buildLootPool("jungle_temple", 1, 0), LootTables.CHESTS_JUNGLE_TEMPLE);
	}

	public static void registerCompostables() {
		DataUtil.registerCompostable(BBBlocks.PINK_CLOVER.get(), 0.65F);
		DataUtil.registerCompostable(BBBlocks.WHITE_CLOVER.get(), 0.65F);
		DataUtil.registerCompostable(BBItems.FOUR_LEAF_CLOVER.get(), 0.65F);
	}

	public static void registerFlammables() {
		DataUtil.registerFlammable(BBBlocks.SPRUCE_BEEHIVE.get(), 5, 20);
		DataUtil.registerFlammable(BBBlocks.BIRCH_BEEHIVE.get(), 5, 20);
		DataUtil.registerFlammable(BBBlocks.JUNGLE_BEEHIVE.get(), 5, 20);
		DataUtil.registerFlammable(BBBlocks.DARK_OAK_BEEHIVE.get(), 5, 20);
		DataUtil.registerFlammable(BBBlocks.ACACIA_BEEHIVE.get(), 5, 20);
	}

	public static void registerBottleEntityTypes() {
		ENTITY_TYPE_TO_BOTTLE_MAP.put(EntityType.BEE, (Entity entity) -> {
			if (entity instanceof BeeEntity) {
				BeeEntity bee = (BeeEntity) entity;
				ItemStack bottle = new ItemStack(BBItems.BOTTLE_OF_BEE.get());
				CompoundNBT tag = bottle.getOrCreateTag();
				tag.putBoolean("HasNectar", bee.hasNectar());
				tag.putBoolean("HasStung", bee.hasStung());
				tag.putInt("AngerTime", bee.getAngerTime());
				if (bee.getAngerTarget() != null) tag.putUniqueId("AngryAt", bee.getAngerTarget());
				tag.putInt("Age", bee.getGrowingAge());
				tag.putFloat("Health", bee.getHealth());
				if (bee.hasCustomName()) {
					bottle.setDisplayName(bee.getCustomName());
				}
				return bottle;
			}
			return null;
		});
		ENTITY_TYPE_TO_BOTTLE_MAP.put(EntityType.SILVERFISH, (Entity entity) -> {
			ItemStack bottle = new ItemStack(BBItems.BOTTLE_OF_SILVERFISH.get());
			if (entity.hasCustomName()) {
				bottle.setDisplayName(entity.getCustomName());
			}
			return bottle;
		});
		ENTITY_TYPE_TO_BOTTLE_MAP.put(EntityType.ENDERMITE, (Entity entity) -> {
			ItemStack bottle = new ItemStack(BBItems.BOTTLE_OF_ENDERMITE.get());
			if (entity.hasCustomName()) {
				bottle.setDisplayName(entity.getCustomName());
			}
			return bottle;
		});

	}

	public static void registerDispenserBehaviors() {
		DispenserBlock.registerDispenseBehavior(BBItems.BOTTLE_OF_BEE.get(), new BeeBottleDispenseBehavior());
		DispenserBlock.registerDispenseBehavior(BBItems.BOTTLE_OF_SILVERFISH.get(), new BugBottleDispenseBehavior());
		DispenserBlock.registerDispenseBehavior(BBItems.BOTTLE_OF_ENDERMITE.get(), new BugBottleDispenseBehavior());

		DataUtil.registerAlternativeDispenseBehavior(Items.GLASS_BOTTLE, (source, stack) -> !BlockUtil.getEntitiesAtOffsetPos(source, CreatureEntity.class, e -> ENTITY_TYPE_TO_BOTTLE_MAP.containsKey(e.getType())).isEmpty(), new DefaultDispenseItemBehavior() {
			@Override
			protected ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
				Entity entity = BlockUtil.getEntitiesAtOffsetPos(source, Entity.class, e -> ENTITY_TYPE_TO_BOTTLE_MAP.containsKey(e.getType())).get(0);
				ItemStack bottled = ENTITY_TYPE_TO_BOTTLE_MAP.get(entity.getType()).apply(entity);
				stack.shrink(1);
				if (stack.isEmpty()) {
					stack = bottled.copy();
				} else if (source.<DispenserTileEntity>getBlockTileEntity().addItemStack(bottled.copy()) < 0) {
					new DefaultDispenseItemBehavior().dispense(source, bottled.copy());
				}
				source.getWorld().playSound(null, source.getBlockPos(), SoundEvents.ITEM_BOTTLE_FILL_DRAGONBREATH, SoundCategory.NEUTRAL, 1.0F, 1.0F);
				source.getWorld().removeEntity(entity);
				return stack;
			}
		});
		DataUtil.registerAlternativeDispenseBehavior(Items.FLINT_AND_STEEL, (source, stack) -> {
			BlockState state = BlockUtil.getStateAtOffsetPos(source);
			return state.getBlock() instanceof CandleBlock && !state.get(BlockStateProperties.LIT);
		}, new DefaultDispenseItemBehavior() {
			@Override
			protected ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
				World world = source.getWorld();
				world.setBlockState(BlockUtil.offsetPos(source), BlockUtil.getStateAtOffsetPos(source).with(BlockStateProperties.LIT, true));
				if (stack.attemptDamageItem(1, world.rand, null)) {
					stack.setCount(0);
				}
				return stack;
			}
		});
	}

	public static void setupRenderLayer() {
		RenderTypeLookup.setRenderLayer(BBBlocks.HONEY_LAMP.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(BBBlocks.HONEY_POT.get(), RenderType.getTranslucent());

		RenderTypeLookup.setRenderLayer(BBBlocks.WHITE_CLOVER.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.PINK_CLOVER.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.BUTTERCUP.get(), RenderType.getCutout());

		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_WHITE_CLOVER.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_PINK_CLOVER.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_BUTTERCUP.get(), RenderType.getCutout());
	}
}
