package org.lwjgl.d3d11.impl;

import org.lwjgl.d3d11.IDXGISwapChain;

public class DXGISwapChainImpl extends DXGIDeviceSubObjectImpl implements IDXGISwapChain {

    public DXGISwapChainImpl(long ptr) {
        super(ptr);
    }

    public static final native void nPresent(int syncInterval, int flags);

    @Override
    public void Present(int syncInterval, int flags) {
        nPresent(syncInterval, flags);
    }

}
