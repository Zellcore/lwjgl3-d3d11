package org.lwjgl.d3d11;

public enum DXGI_SWAP_EFFECT {

    DXGI_SWAP_EFFECT_DISCARD,
    DXGI_SWAP_EFFECT_SEQUENTIAL,
    DXGI_SWAP_EFFECT_FLIP_SEQUENTIAL(3);

    private final int value;

    DXGI_SWAP_EFFECT() {
        this.value = ordinal();
    }

    DXGI_SWAP_EFFECT(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
