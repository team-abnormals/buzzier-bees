package com.bagel.buzzierbees.common.blocks.pistons;

import com.bagel.buzzierbees.common.blocks.ModBlocks;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.PistonBlock;
import net.minecraft.block.PistonHeadBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockModelRenderer;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.state.properties.PistonType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class NewPistonTileEntityRenderer extends TileEntityRenderer<NewPistonTileEntity> {
    private BlockRendererDispatcher blockRenderer = Minecraft.getInstance().getBlockRendererDispatcher();

    public NewPistonTileEntityRenderer(TileEntityRendererDispatcher p_i226012_1_) {
        super(p_i226012_1_);
    }

    public void func_225616_a_(NewPistonTileEntity p_225616_1_, float p_225616_2_, MatrixStack p_225616_3_, IRenderTypeBuffer p_225616_4_, int p_225616_5_, int p_225616_6_) {
        World world = p_225616_1_.getWorld();
        if (world != null) {
            BlockPos blockpos = p_225616_1_.getPos().offset(p_225616_1_.getMotionDirection().getOpposite());
            BlockState blockstate = p_225616_1_.getPistonState();
            if (!blockstate.isAir() && !(p_225616_1_.getProgress(p_225616_2_) >= 1.0F)) {
                BlockModelRenderer.enableCache();
                p_225616_3_.func_227860_a_();
                p_225616_3_.func_227861_a_((double)p_225616_1_.getOffsetX(p_225616_2_), (double)p_225616_1_.getOffsetY(p_225616_2_), (double)p_225616_1_.getOffsetZ(p_225616_2_));
                if (blockstate.getBlock() == ModBlocks.PISTON_HEAD && p_225616_1_.getProgress(p_225616_2_) <= 4.0F) {
                    blockstate = blockstate.with(PistonHeadBlock.SHORT, Boolean.valueOf(true));
                    this.func_228876_a_(blockpos, blockstate, p_225616_3_, p_225616_4_, world, false, p_225616_6_);
                } else if (p_225616_1_.shouldPistonHeadBeRendered() && !p_225616_1_.isExtending()) {
                    PistonType pistontype = blockstate.getBlock() == ModBlocks.SLIMY_PISTON ? PistonType.STICKY : PistonType.DEFAULT;
                    BlockState blockstate1 = ModBlocks.PISTON_HEAD.getDefaultState().with(NewPistonHeadBlock.TYPE, pistontype).with(NewPistonHeadBlock.FACING, blockstate.get(PistonBlock.FACING));
                    blockstate1 = blockstate1.with(NewPistonHeadBlock.SHORT, Boolean.valueOf(p_225616_1_.getProgress(p_225616_2_) >= 0.5F));
                    this.func_228876_a_(blockpos, blockstate1, p_225616_3_, p_225616_4_, world, false, p_225616_6_);
                    BlockPos blockpos1 = blockpos.offset(p_225616_1_.getMotionDirection());
                    p_225616_3_.func_227865_b_();
                    p_225616_3_.func_227860_a_();
                    blockstate = blockstate.with(NewPistonBlock.EXTENDED, Boolean.valueOf(true));
                    this.func_228876_a_(blockpos1, blockstate, p_225616_3_, p_225616_4_, world, true, p_225616_6_);
                } else {
                    this.func_228876_a_(blockpos, blockstate, p_225616_3_, p_225616_4_, world, false, p_225616_6_);
                }

                p_225616_3_.func_227865_b_();
                BlockModelRenderer.disableCache();
            }
        }
    }

    private void func_228876_a_(BlockPos p_228876_1_, BlockState p_228876_2_, MatrixStack p_228876_3_, IRenderTypeBuffer p_228876_4_, World p_228876_5_, boolean p_228876_6_, int p_228876_7_) {
        net.minecraft.client.renderer.RenderType.func_228661_n_().stream().filter(t -> RenderTypeLookup.canRenderInLayer(p_228876_2_, t)).forEach(rendertype -> {
            net.minecraftforge.client.ForgeHooksClient.setRenderLayer(rendertype);
            IVertexBuilder ivertexbuilder = p_228876_4_.getBuffer(rendertype);
            if (blockRenderer == null) blockRenderer = Minecraft.getInstance().getBlockRendererDispatcher();
            this.blockRenderer.getBlockModelRenderer().func_228802_a_(p_228876_5_, this.blockRenderer.getModelForState(p_228876_2_), p_228876_2_, p_228876_1_, p_228876_3_, ivertexbuilder, p_228876_6_, new Random(), p_228876_2_.getPositionRandom(p_228876_1_), p_228876_7_);
        });
        net.minecraftforge.client.ForgeHooksClient.setRenderLayer(null);
    }
}
