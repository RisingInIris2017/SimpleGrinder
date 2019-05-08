package com.rumaruka.simplegrinder.client.gui;

import net.minecraft.util.math.MathHelper;

import java.util.Arrays;
import java.util.Comparator;

public enum  MouseButton {

    LEFT_CLICK(0),
    RIGHT_CLICK(1),
    MIDDLE_CLICK(2),

    SIDE_DOWN_CLICK(3),
    SIDE_UP_CLICK(4);

    private static final MouseButton[] VALUES = values();
    private static final MouseButton[] BY_CODE = Arrays.stream(VALUES).sorted(Comparator.comparingInt(value -> value.code)).toArray(MouseButton[]::new);
    private int code;

    MouseButton(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static MouseButton byCode(int code) {
        return BY_CODE[MathHelper.abs(code % BY_CODE.length)];
    }

}
