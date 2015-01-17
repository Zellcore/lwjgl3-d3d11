package org.lwjgl.d3d11;

public enum D3D_DRIVER_TYPE {

    D3D_DRIVER_TYPE_UNKNOWN,
    D3D_DRIVER_TYPE_HARDWARE,
    D3D_DRIVER_TYPE_REFERENCE,
    D3D_DRIVER_TYPE_NULL,
    D3D_DRIVER_TYPE_SOFTWARE,
    D3D_DRIVER_TYPE_WARP; 

    public final int value;

    D3D_DRIVER_TYPE() {
        this.value = ordinal();
    }

}
