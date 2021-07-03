package com.minecraftabnormals.buzzier_bees.core.other;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nullable;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.minecraftabnormals.buzzier_bees.common.blocks.HoneyPotBlock;
import com.minecraftabnormals.buzzier_bees.core.BuzzierBees;
import com.mojang.datafixers.util.Pair;

import net.minecraft.block.BlockState;
import net.minecraft.client.resources.JsonReloadListener;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;

/***
 * This class is responsible for listening to changes in the Honey Pot json
 * files and generating Honey Pot usages based on that. It is registered as a
 * json listener in { {@link BBEvents#addHoneyPotReloadListener()}.
 *
 */
public class HoneyPotManager extends JsonReloadListener {

	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
	private static final String FOLDER = BuzzierBees.MOD_ID + "-honey_pot";
	private static final Logger LOGGER = LogManager.getLogger();

	private static HoneyPotManager instance;

	private Map<ResourceLocation, HoneyPotUsage> usages;

	public HoneyPotManager() {
		super(GSON, FOLDER);

		usages = new HashMap<>();
	}

	@Override
	protected void apply(Map<ResourceLocation, JsonElement> objectIn, IResourceManager resourceManagerIn,
			IProfiler profilerIn) {

		for (Entry<ResourceLocation, JsonElement> entry : objectIn.entrySet()) {
			ResourceLocation rl = entry.getKey();
			JsonElement elem = entry.getValue();
			try {
				if (elem.isJsonObject())
					usages.put(rl, deserialize(JSONUtils.getJsonObject(elem, "top element")));
			} catch (JsonParseException e) {
				LOGGER.error("Parsing error loading honey pot usage {}", rl, e);
			}
		}

	}

	/***
	 * Get the output for the honey pot.
	 * 
	 * @param stack    The input.
	 * @param honeypot The block state of the clicked honey pot.
	 * @param rand     A RNG.
	 * @return A pair with the output item stack and the updated honey level for the
	 *         honey pot, or null if the input is invalid or the updated honey level
	 *         would be illegal.
	 */
	@Nullable
	public Pair<ItemStack, Integer> get(ItemStack stack, BlockState honeypot, Random rand) {
		for (HoneyPotUsage usage : usages.values())
			if (usage.input.test(stack)) {
				int level = honeypot.get(HoneyPotBlock.HONEY_LEVEL);
				int updatedLevel = level + usage.level;
				if (HoneyPotBlock.HONEY_LEVEL.getAllowedValues().contains(updatedLevel))
					return Pair.of(usage.output.copy(), rand.nextFloat() <= usage.chance ? updatedLevel : level);
				else
					return null;
			}
		return null;
	}

	/***
	 * See {@link HoneyPotManager#get()}.
	 */
	@Nullable
	public static Pair<ItemStack, Integer> getUsage(ItemStack stack, BlockState honeypot, Random rand) {
		return getInstance().get(stack, honeypot, rand);
	}

	/***
	 * Get the singleton HoneyPotManager object.
	 */
	public static HoneyPotManager getInstance() {
		if (instance == null)
			instance = new HoneyPotManager();
		return instance;
	}

	private static HoneyPotUsage deserialize(JsonObject json) {
		HoneyPotUsage usage = new HoneyPotUsage();
		usage.input = Ingredient.deserialize(JSONUtils.getJsonObject(json, "input"));
		usage.output = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "output"), true);
		usage.level = JSONUtils.getInt(json, "level");
		if (usage.level < -4 || usage.level > 4)
			throw new JsonSyntaxException("Member level does not meet requirement -4 < level < 4");
		usage.chance = JSONUtils.getFloat(json, "chance");

		return usage;
	}

	private static class HoneyPotUsage {
		private Ingredient input;
		private ItemStack output;
		private int level;
		private float chance;
	}

}
