package org.lwjgl.d3d11;

public enum D3D11_INPUT_CLASSIFICATION {

    D3D11_INPUT_PER_VERTEX_DATA,
    D3D11_INPUT_PER_INSTANCE_DATA;

    public final int value;

    D3D11_INPUT_CLASSIFICATION() {
        this.value = ordinal();
    }

}
