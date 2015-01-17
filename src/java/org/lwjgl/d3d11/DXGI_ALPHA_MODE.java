package org.lwjgl.d3d11;

public enum DXGI_ALPHA_MODE {

    DXGI_ALPHA_MODE_UNSPECIFIED, 
    DXGI_ALPHA_MODE_PREMULTIPLIED, 
    DXGI_ALPHA_MODE_STRAIGHT, 
    DXGI_ALPHA_MODE_IGNORE, 
    DXGI_ALPHA_MODE_FORCE_DWORD(0xffffffff);

    public final int value;

    DXGI_ALPHA_MODE() {
        this.value = ordinal();
    }

    DXGI_ALPHA_MODE(int val) {
        this.value = val;
    }

}
