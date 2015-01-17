package org.lwjgl.d3d11;

public enum DXGI_SWAP_EFFECT {

    DXGI_SWAP_EFFECT_DISCARD,
    DXGI_SWAP_EFFECT_SEQUENTIAL,
    DXGI_SWAP_EFFECT_FLIP_SEQUENTIAL(3);

    public final int value;

    DXGI_SWAP_EFFECT() {
        this.value = ordinal();
    }

    DXGI_SWAP_EFFECT(int value) {
        this.value = value;
    }

    public static DXGI_SWAP_EFFECT byValue(int val) {
        for (DXGI_SWAP_EFFECT f : values()) {
            if (f.value == val) {
                return f;
            }
        }
        return null;
    }

}
