package com.bagel.buzzierbees.common.blocks;

import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import net.minecraft.block.WoodType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Set;

public class ModWoodType extends WoodType {
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private static final Set<WoodType> woodTypes = new ObjectArraySet();
    public static final WoodType HIVE_TYPE = func_227047_a_(new ModWoodType("hive"));
    private final String name;

    protected ModWoodType(String typeName) {
        super(typeName);
        name = typeName;
    }

    private static WoodType func_227047_a_(WoodType p_227047_0_) {
        woodTypes.add(p_227047_0_);
        return p_227047_0_;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public String func_227048_b_() {
        return this.name;
    }
}
