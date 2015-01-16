package org.lwjgl.d3d11;

public enum DXGISwapEffect {

    DXGI_SWAP_EFFECT_DISCARD,
    DXGI_SWAP_EFFECT_SEQUENTIAL,
    DXGI_SWAP_EFFECT_FLIP_SEQUENTIAL(3);

    private final int value;

    DXGISwapEffect() {
        this.value = ordinal();
    }

    DXGISwapEffect(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
