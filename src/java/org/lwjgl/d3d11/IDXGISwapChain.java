package org.lwjgl.d3d11;

public interface IDXGISwapChain extends IDXGIDeviceSubObject {

    void Present(int syncInterval, int flags);

}
