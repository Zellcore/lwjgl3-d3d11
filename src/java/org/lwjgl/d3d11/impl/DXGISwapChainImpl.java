package org.lwjgl.d3d11.impl;

import org.lwjgl.d3d11.IDXGISwapChain;

public class DXGISwapChainImpl implements IDXGISwapChain {

    private final long ptr;

    public DXGISwapChainImpl(long ptr) {
        super();
        this.ptr = ptr;
    }

    public static final native void Present0(int syncInterval, int flags);

    @Override
    public void Present(int syncInterval, int flags) {
        Present0(syncInterval, flags);
    }

}
