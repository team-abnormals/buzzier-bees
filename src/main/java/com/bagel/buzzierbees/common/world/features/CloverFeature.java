package com.bagel.buzzierbees.common.world.features;

import com.bagel.buzzierbees.common.blocks.ModBlocks;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

//TODO: Proper Clover Patch generation
public class CloverFeature extends Feature<NoFeatureConfig> {
    public static final BlockState WHITE_CLOVER = ModBlocks.WHITE_CLOVER.getDefaultState();
    public static final BlockState PINK_CLOVER  = ModBlocks.PINK_CLOVER.getDefaultState();

    public CloverFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i49878_1_) {
        super(p_i49878_1_);
    }

    @Override
    public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        return false;
    }
}
