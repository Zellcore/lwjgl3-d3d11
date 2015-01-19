package org.lwjgl.d3d11;

public enum D3D_INCLUDE_TYPE {

    D3D_INCLUDE_LOCAL(0),
    D3D_INCLUDE_SYSTEM( D3D_INCLUDE_LOCAL.value + 1 ),
    D3D10_INCLUDE_LOCAL(D3D_INCLUDE_LOCAL.value),
    D3D10_INCLUDE_SYSTEM(D3D_INCLUDE_SYSTEM.value),
    D3D_INCLUDE_FORCE_DWORD(0x7fffffff);

    public final int value;

    D3D_INCLUDE_TYPE(int value) {
        this.value = value;
    }

}
