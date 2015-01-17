package org.lwjgl.d3d11;

public interface D3D11_CREATE_DEVICE_FLAG {

    int D3D11_CREATE_DEVICE_SINGLETHREADED = 0x01;
    int D3D11_CREATE_DEVICE_DEBUG = 0x2;
    int D3D11_CREATE_DEVICE_SWITCH_TO_REF = 0x4;
    int D3D11_CREATE_DEVICE_PREVENT_INTERNAL_THREADING_OPTIMIZATIONS = 0x8;
    int D3D11_CREATE_DEVICE_BGRA_SUPPORT = 0x20;
    int D3D11_CREATE_DEVICE_DEBUGGABLE = 0x40;
    int D3D11_CREATE_DEVICE_PREVENT_ALTERING_LAYER_SETTINGS_FROM_REGISTRY = 0x80;
    int D3D11_CREATE_DEVICE_DISABLE_GPU_TIMEOUT = 0x100;
    int D3D11_CREATE_DEVICE_VIDEO_SUPPORT = 0x800;

}