package com.bagel.buzzierbees.common.blocks.pistons;

import net.minecraft.util.IStringSerializable;

public enum NewPistonType implements IStringSerializable {
    DEFAULT("normal"),
    SLIMY("sticky"),
    HONEY("honey");

    private final String name;

    private NewPistonType(String p_i49335_3_) {
        this.name = p_i49335_3_;
    }

    public String toString() {
        return this.name;
    }

    public String getName() {
        return this.name;
    }
}
